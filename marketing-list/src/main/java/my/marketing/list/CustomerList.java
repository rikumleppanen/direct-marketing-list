package my.marketing.list;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CustomerList {

    private final List<Customer> custolist;

    public CustomerList() {
        this.custolist = new ArrayList<>();
    }

    public void addNewCustomer(String row, Type type, int id) {
        if (type == Type.phone || type == Type.foreign) {
            this.custolist.add(new Customer().setNumberC(row).setID(id));
        }
        if (type == Type.email) {
            this.custolist.add(new Customer().setEmailC(row).setID(id));
        }
    }

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

    public Customer getCustomer(int id) {
        for (Customer one : custolist) {
            if (one.getID() == id) {
                return one;
            }
        }
        return null;
    }
    public List<Customer> getCustomers() {
        return this.custolist;
    }

    public List<Consent> getConsentList(Customer cu) {
        for (Customer one : custolist) {
            if (one.equals(cu)) {
                return one.getConsentList();
            }
        }
        return null;
    }

    public void createAndUpdate(Integer key, ContactList list) {
        StateKeeper eye = findState(key, list);
        for (int i = 0; i < eye.size(); i++) {
            //System.out.println(eye.getState(i) + " " + eye.getContactRow(i).getRow() + " " + eye.getCustomer());
            if (eye.getCustomer() == 0) {
                int idnum = eye.getContactRow(i).getInsertid();
                Customer foundCustomer = find(key, list);
                if (foundCustomer != null) {
                    updateExistingCustomer(getCustomer(idnum), eye.getContactRow(i).getRow(), eye.getContactRow(i).getType());
                } else {
                    addNewCustomer(eye.getContactRow(i).getRow(), eye.getContactRow(i).getType(), idnum);
                }
            } else if (eye.getState(i) == State.notFound) {
                int cusno = eye.getCustomer();
                updateExistingCustomer(getCustomer(cusno), eye.getContactRow(i).getRow(), eye.getContactRow(i).getType());
            }
        }

    }

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

    public StateKeeper findState(Integer key, ContactList list) {
        StateKeeper eye = new StateKeeper();
        int n = list.get(key).size();
        int k = 0;
        eye.setN(n);
        for (Contact row : list.get(key)) {
            if (row.getState() == State.sameEmail) {
                eye.set(isEmailSame(row), row, k, row.getState());
                k++;
            }
            if (row.getState() == State.sameNumber) {
                eye.set(isNumberSame(row), row, k, row.getState());
                k++;
            }
            if (row.getState() == State.notFound) {
                Customer foundCustomer = find(key, list);
                if (foundCustomer != null) {
                    eye.set(foundCustomer, row, k, row.getState());
                    k++;
                } else {
                    eye.set(row, k, row.getState());
                    k++;
                }
            }
        }
        return eye;
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
            System.out.println(one.getNumber() + " " + one.getEmail() + " " + one.getID() + " " + one.hashCodeEmail() + " " + one.hashCodeNumber());
//            for (Consent con : getConsentList(one)) {
//                System.out.println(con.getRow() + " " + con.getType() + " " + con.getTimestamp());
//            }
        }
    }
}
