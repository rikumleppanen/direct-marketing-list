package fi.marketing.list.logic.lists;

import fi.marketing.list.logic.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

/**
 * CustomerList Class keeps record of the individual customers. There might be
 * several customer lists for instance if there are organization- or
 * product-wide customer lists.
 */
public class CustomerList {

    private final List<Customer> custolist;

    public CustomerList() {
        this.custolist = new ArrayList<>();
    }

    /**
     * Customers can be generated with the Customer()
     *
     * @param row is the contact row i.e. email or phone number
     * @param type is the type of contact row so as to know whether the row is
     * email or number
     * @param id is the insertation number given by the source system or the
     * file that is downloaded to the system
     */
    public void addNewCustomer(String row, Type type, int id) {
        if (type == Type.phone || type == Type.foreign) {
            this.custolist.add(new Customer().setNumberC(row).setID(id));
        }
        if (type == Type.email) {
            this.custolist.add(new Customer().setEmailC(row).setID(id));
        }
    }

    /**
     * For existing customers we can add or change the current information
     *
     * @param customer is the existing customer number
     * @param row is the contact row i.e. email or phone number
     * @param type is the type of contact row so as to know whether the row is
     * email or number
     */
    public void updateExistingCustomer(Customer customer, String row, Type type) {
        if (type == Type.phone || type == Type.foreign) {
            setNumber(customer, row);
        }
        if (type == Type.email) {
            setEmail(customer, row);
        }
    }

    public Customer getCustomer(String row) {
        for (Customer one : custolist) {
            if (one.getEmail().equals(row) || one.getNumber().equals(row)) {
                return one;
            }
        }
        return null;
    }

    public Customer getCustomerInsertId(int id) {
        for (Customer one : custolist) {
            if (one.getInsertId() == id) {
                return one;
            }
        }
        return null;
    }

    public Customer getCustomerCusnoId(UUID id) {
        for (Customer one : custolist) {
            if (one.getCusnoId() == id) {
                return one;
            }
        }
        return null;
    }

    public List<Customer> getCustomers() {
        return this.custolist;
    }

    public int numberOfCustomers() {
        return this.custolist.size();
    }

    public List<Consent> getConsentList(Customer cu) {
        for (Customer one : custolist) {
            if (one.equals(cu)) {
                return one.getConsentList();
            }
        }
        return null;
    }

    /**
     * Customers can be created and found by customer number or insertation
     * number.
     *
     * @param key is the key of ContactList, each key can have one to many rows
     * of contact data
     * @param list is the ContactList that we are comparing to these customers.
     */
    public void createAndUpdate(Integer key, ContactList list) {
        StateKeeper eye = findState(key, list);
        for (int i = 0; i < eye.size(); i++) {
            //System.out.println(eye.getState(i) + " " + eye.getContactRow(i).getRow() + " " + eye.getCustomer());
            if (eye.getCustomer() == null) {
                int idnum = eye.getContactRow(i).getInsertid();
                Customer foundCustomer = find(key, list);
                if (foundCustomer != null) {
                    updateExistingCustomer(getCustomerInsertId(idnum), eye.getContactRow(i).getRow(), eye.getContactRow(i).getType());
                } else {
                    addNewCustomer(eye.getContactRow(i).getRow(), eye.getContactRow(i).getType(), idnum);
                }
            } else if (eye.getState(i) == State.notFound) {
                UUID cusno = eye.getCustomer();
                updateExistingCustomer(getCustomerCusnoId(cusno), eye.getContactRow(i).getRow(), eye.getContactRow(i).getType());
            }
        }
    }

    /**
     * Contacts are labeled after comparing them to customer list.
     *
     * @param key is the key of ContactList, each key can have one to many rows
     * of contact data
     * @param list is the ContactList that we are comparing to these customers.
     */
    public void searchAndLabel(Integer key, ContactList list) {
        for (Contact row : list.get(key)) {
            if (isEmailSame(row) != null) {
                row.setStateAndTagCustomer(State.sameEmail, isEmailSame(row));
            } else if (isNumberSame(row) != null) {
                row.setStateAndTagCustomer(State.sameNumber, isNumberSame(row));
            } else {
                row.setState(State.notFound);
            }
        }
    }

    /**
     * Contacts are labeled and sorted so as to prepare data to be converted to
     * customers or updating the existing ones.
     *
     * @param key is the key of ContactList, each key can have one to many rows
     * of contact data
     * @param list is the ContactList that we are comparing to these customers.
     */
    public StateKeeper findState(Integer key, ContactList list) {
        StateKeeper eye = new StateKeeper();
        int n = list.get(key).size();
        int k = 0;
        eye.setN(n);
        for (Contact row : list.get(key)) {
            if (row.getState() == State.sameEmail || row.getState() == State.sameNumber) {
                stateEmailOrNumber(row, eye, k);
                k++;
            }
            if (row.getState() == State.notFound) {
                stateNotFound(row, eye, k, key, list);
                k++;
            }
        }
        return eye;
    }

    /**
     * Contact rows are added to StateKeeper object if email or phone number is
     * the same with a customer
     *
     * @param row is the contact row i.e. email or phone number
     * @param sk is the StateKeeper where contact rows are added
     * @param k is table number in StateKeeper object
     */
    public void stateEmailOrNumber(Contact row, StateKeeper sk, int k) {
        if (row.getState() == State.sameEmail) {
            sk.set(isEmailSame(row), row, k, row.getState());
        }
        if (row.getState() == State.sameNumber) {
            sk.set(isNumberSame(row), row, k, row.getState());
        }
    }

    /**
     * Contact rows are added to StateKeeper object if they are not found in the
     * customer list
     *
     * @param row is the contact row i.e. email or phone number
     * @param sk is the StateKeeper where contact rows are added
     * @param k is table number in StateKeeper object
     * @param key is the key number in the Map of contactList
     * @param list is the ContactList itself
     */
    public void stateNotFound(Contact row, StateKeeper sk, int k, int key, ContactList list) {
        Customer foundCustomer = find(key, list);
        if (foundCustomer != null) {
            sk.set(foundCustomer, row, k, row.getState());
        } else {
            sk.set(row, k, row.getState());
        }
    }

    public Customer find(Integer key, ContactList list) {
        for (Customer one : custolist) {
            Iterator<Contact> iter = list.get(key).iterator();
            while (iter.hasNext()) {
                Contact row = iter.next();
                if (row.hashCode() == one.hashCode() || row.hashCodeEmail() == one.hashCodeEmail() || row.hashCodeNumber() == one.hashCodeNumber()) {
                    return one;
                }
            }
        }
        return null;
    }

    public boolean existsNumber(String row) {
        for (Customer one : custolist) {
            if (one.getNumber().equals(row)) {
                return true;
            }
        }
        return false;
    }

    public int getNumberOfEmails() {
        int count = 0;
        for (Customer one : custolist) {
            if (one.hashCodeEmail() != 0) {
                count++;
            }
        }
        return count;
    }

    public int getNumberOfPhoneNumbers() {
        int count = 0;
        for (Customer one : custolist) {
            if (one.hashCodeNumber() != 0) {
                count++;
            }
        }
        return count;
    }

    public void setEmail(Customer one, String row) {
        if (one != null) {
            one.setEmail(row);
        }
    }

    public void setNumber(Customer one, String row) {
        if (one != null) {
            one.setNumber(row);
        }
    }

    public Customer isEmailSame(Contact row) {
        for (Customer one : custolist) {
            if (one.hashCodeEmail() == row.hashCodeEmail()) {
                return one;
            }
        }
        return null;
    }

    public Customer isNumberSame(Contact row) {
        for (Customer one : custolist) {
            if (one.hashCodeNumber() == row.hashCodeNumber()) {
                return one;
            }
        }
        return null;
    }

    public List<Consent> getAllConsentsList(Type type) {
        List<Consent> cons = new ArrayList<>();
        for (Customer one : custolist) {
            cons.add(one.getMaxConsent(type));
        }
        return cons;
    }

    public void print() {
        for (Customer one : custolist) {
            System.out.println(one.getNumber() + " " + one.getEmail() + " " + one.getInsertId() + " " + one.hashCodeEmail() + " " + one.hashCodeNumber());
        }
    }
}
