package fi.marketing.list.logic.lists;

import fi.marketing.list.logic.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

/**
 * CustomerList Class keeps record of the list of customers.
 */
public class CustomerList {

    private final List<Customer> custolist;

    /**
     * There might be several CustomerLists for each marketing product here.
     */
    public CustomerList() {
        this.custolist = new ArrayList<>();
    }

    /**
     * If a contact (with just an email or phone number as well) is not matched
     * by any other customer, then a new customer is created.
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
     * For existing customers we can add or update the current Customer card.
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

    /**
     * A customer can be found by the email, phone or similar.
     *
     * @param row is the email, phone number or similar in string format.
     * @return a customer is returned
     */
    public Customer getCustomer(String row) {
        for (Customer one : custolist) {
            if (one.getEmail().equals(row) || one.getNumber().equals(row)) {
                return one;
            }
        }
        return null;
    }

    /**
     * A customer can be found by the insertId of the uploaded contact list.
     *
     * @param id is the insertId of the upladed contact. This id can be
     * generated from the source system where the contacts were obtained.
     * @return a customer is returned
     */
    public Customer getCustomerInsertId(int id) {
        for (Customer one : custolist) {
            if (one.getInsertId() == id) {
                return one;
            }
        }
        return null;
    }

    /**
     * A customer can be found by the system's customer number.
     *
     * @param id is the system's id for a individual customer cart.
     * @return a customer is returned
     */
    public Customer getCustomerCusnoId(UUID id) {
        for (Customer one : custolist) {
            if (one.getCusnoId() == id) {
                return one;
            }
        }
        return null;
    }

    /**
     * A list of customers can be obtained.
     *
     * @return a list of customers in the list.
     */
    public List<Customer> getCustomers() {
        return this.custolist;
    }

    /**
     * A number of customers can be obtained.
     *
     * @return a number of customers in the list.
     */
    public int numberOfCustomers() {
        return this.custolist.size();
    }

    /**
     * A number of customers can be obtained.
     *
     * @param cu is the customer we want to have consents of.
     * @return a list of consents of a searched customer.
     */
    public List<Consent> getConsentList(Customer cu) {
        for (Customer one : custolist) {
            if (one.equals(cu)) {
                return one.getConsentList();
            }
        }
        return null;
    }

    /**
     * Contact data is turned into new customers, updated both information that
     * was not yet available and updated for consents if the information was
     * already similar with the customer.
     *
     * @param key is the key of ContactList, each key can have one to many rows
     * of contact data
     * @param list is the ContactList that we are comparing to these customers
     */
    public void createAndUpdate(Integer key, ContactList list) {
        StateKeeper eye = findState(key, list);
        for (int i = 0; i < eye.size(); i++) {
            //System.out.println(eye.getState(i) + " " + eye.getContactRow(i).getRow() + " " + eye.getCustomer());
            if (eye.getCustomer() == null) {
                //Cases 1, 2, 3, 4, 5
                addACustomerOrANewRow(key, i, eye, list);
            } else if (eye.getState(i) == State.notFound) {
                //Cases 11, 12
                UUID cusno = eye.getCustomer();
                updateExistingCustomer(getCustomerCusnoId(cusno), eye.getContactRow(i).getRow(), eye.getContactRow(i).getType());
            } else if (eye.isIdenticalRow() == true) {
                //Cases 6, 7, 8, 9, 10
                UUID cusno = eye.getCustomer();
                updateExistingCustomer(getCustomerCusnoId(cusno), eye.getContactRow(i).getRow(), eye.getContactRow(i).getType());
            }
        }
    }

    /**
     * Customers can be created and found by customer number or insertation
     * number.
     *
     * @param key is the key of ContactList, each key can have one to many rows
     * of contact data
     * @param i is the number of row in StateKeeper tables
     * @param eye is the StateKeeper panel that keeps track of each contact row
     * and their status
     * @param list is the ContactList that we are comparing to these customers
     */
    public void addACustomerOrANewRow(Integer key, Integer i, StateKeeper eye, ContactList list) {
        int idnum = eye.getContactRow(i).getInsertid();
        Customer foundCustomer = find(key, list);
        if (foundCustomer != null) {
            updateExistingCustomer(getCustomerInsertId(idnum), eye.getContactRow(i).getRow(), eye.getContactRow(i).getType());
        } else {
            addNewCustomer(eye.getContactRow(i).getRow(), eye.getContactRow(i).getType(), idnum);
        }
    }

    /**
     * Contacts are labeled after comparing them to customer list.
     *
     * @param key is the key of ContactList, each key can have one to many rows
     * of contact data.
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
     * @return gives a StateKeeper object which entails all the contact info of
     * a contact
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
     * the same with a customer.
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
     * customer list.
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

    /**
     * A customer can be found from the ContactList if at least one hashCode is
     * same with customers hashCodes.
     *
     * @param key is the key that indicates one or several rows of inserted
     * contact info.
     * @param list is the ContactList we want to compare with the existing
     * customers.
     * @return a customer if ContactList have a same contact info according to
     * hashCode search.
     */
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

    /**
     * A customer's phone number can be found by comparing the contact row.
     *
     * @param row is the contact row
     * @return is true if the phone number is the same in customer list compared
     * to contact list.
     */
    public boolean existsNumber(String row) {
        for (Customer one : custolist) {
            if (one.getNumber().equals(row)) {
                return true;
            }
        }
        return false;
    }

    /**
     * The number of emails can be obtained from customer list.
     *
     * @return is the number of emails found in the customer list
     */
    public int getNumberOfEmails() {
        int count = 0;
        for (Customer one : custolist) {
            if (one.hashCodeEmail() != 0) {
                count++;
            }
        }
        return count;
    }

    /**
     * The number of phone numbers can be obtained from customer list.
     *
     * @return is the number of phone numbers found in the customer list
     */
    public int getNumberOfPhoneNumbers() {
        int count = 0;
        for (Customer one : custolist) {
            if (one.hashCodeNumber() != 0) {
                count++;
            }
        }
        return count;
    }

    /**
     * The email of a customer can be set from the contact row.
     *
     * @param one is the customer we want to manipulate
     * @param row is the contact row that we want to insert into customer cart
     */
    public void setEmail(Customer one, String row) {
        if (one != null) {
            one.setEmail(row);
        }
    }

    /**
     * The phone number of a customer can be set from the contact row.
     *
     * @param one is the customer we want to manipulate
     * @param row is the contact row that we want to insert into customer cart
     */
    public void setNumber(Customer one, String row) {
        if (one != null) {
            one.setNumber(row);
        }
    }

    /**
     * The email of contact can be compared to the customers' emails.
     *
     * @param row is the contact row that we want to compare.
     * @return is the object of Customer if hashCode equals with the row.
     */
    public Customer isEmailSame(Contact row) {
        for (Customer one : custolist) {
            if (one.hashCodeEmail() == row.hashCodeEmail()) {
                return one;
            }
        }
        return null;
    }

    /**
     * The phone number of contact can be compared to the customers' phone
     * numbers.
     *
     * @param row is the contact row that we want to compare.
     * @return is the object of Customer if hashCode equals with the row.
     */
    public Customer isNumberSame(Contact row) {
        for (Customer one : custolist) {
            if (one.hashCodeNumber() == row.hashCodeNumber()) {
                return one;
            }
        }
        return null;
    }

    /**
     * All the Consents from the CustomerList can be obtained by the type of
     * contact.
     *
     * @param type is the type of consent, for instance email or phone number
     * @return is the list of Consents
     */
    public List<Consent> getAllConsentsList(Type type) {
        List<Consent> cons = new ArrayList<>();
        for (Customer one : custolist) {
            cons.add(one.getMaxConsent(type));
        }
        return cons;
    }

//    /**
//     * The contents of customer list can be printed.
//     */
//    public void print() {
//        for (Customer one : custolist) {
//            System.out.println(one.getNumber() + " " + one.getEmail() + " " + one.getInsertId() + " " + one.hashCodeEmail() + " " + one.hashCodeNumber());
//        }
//    }
}
