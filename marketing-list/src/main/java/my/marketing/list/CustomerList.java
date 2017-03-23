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
        if (type == Type.phone && customer.getNumber() == "") {
            setNumber(customer, row);
        } else if (type == Type.email && customer.getEmail() == "") {
            setEmail(customer, row);
        } else if (type == Type.foreign && customer.getNumber() == "") {
            setNumber(customer, row);
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
    
    public List<Consent> getConsentList(Customer cu) {
        for(Customer one : custolist) {
            if(one.equals(cu)) {
                return one.getConsentList();
            }
        }
        return null;
    }

    public void sortAndCreate2(Integer key, ContactList list) {
        StateKeeper eye = searchAndLabel(key, list);
        if (eye.isIdentical() == true) {
            //update
        } else if (eye.isSameEmail() == true) {
            //sp:t on samat mutta eri puhelinnumerot
            //eye.getCustomer().setID(-10);

        } else if (eye.isSameNumber() == true) {
            //numerot samat mutta eri sp:t
            eye.getCustomer().setID(-10);
        } else if (eye.isNotFound() == true) {
            //ei vastinetta, luodaan uusi asiakas
            int idnum = eye.getContactRow(0).getInsertid();
            addNewCustomer(eye.getContactRow(0).getRow(), eye.getContactRow(0).getType(), idnum);
            //System.out.println(eye.getCustomer().getID());
            if (eye.size() > 1) {
                updateExistingCustomer(getCustomer(idnum), eye.getContactRow(1).getRow(), eye.getContactRow(1).getType());
            }
        }
    }

    public StateKeeper searchAndLabel(Integer key, ContactList list) {
        StateKeeper eye = new StateKeeper();
        for (Customer one : custolist) {
            Iterator<Contact> iter = list.get(key).iterator();
            int n = list.get(key).size();
            eye.setN(n);
            int k = 0;
            while (iter.hasNext()) {
                Contact row = iter.next();
                if (row.hashCode() == one.hashCode()) {
                    eye.set(one, row, k);
                    eye.setTrueIdentical();
                    k++;
                } else if (row.hashCodeEmail() == one.hashCodeEmail()) {
                    eye.set(one, row, k);
                    eye.setTrueSameEmail();
                    k++;
                } else if (row.hashCodeNumber() == one.hashCodeNumber()) {
                    eye.set(one, row, k);
                    eye.setTrueSameNumber();
                    k++;
                } else {
                    eye.set(one, row, k);
                    eye.setNotFound();
                    k++;
                }
            }
        }
        return eye;
    }

    public void sortAndCreate(Integer key, ContactList list, Contact row) {
        Customer foundCustomer = find(key, list);
        if (foundCustomer != null) {
            updateExistingCustomer(foundCustomer, row.getRow(), row.getType());
            foundCustomer.setID(-1); //koeluontoisesti tämä vain tehdään jatkossa lupien update
        }
        addNewCustomer(row.getRow(), row.getType(), row.getInsertid());
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
        one.setEmail(row);
    }

    public void setNumber(Customer one, String row) {
        one.setNumber(row);
    }

    public void print() {
        for (Customer one : custolist) {
            //System.out.println(one.getNumber() + " " + one.getEmail() + " " + one.getID() + " " + one.hashCodeEmail() + " " + one.hashCodeNumber());
            for(Consent con : getConsentList(one)) {
                System.out.println(con.getRow() + " " + con.getType() +" "+ con.getTimestamp());
            }
        }
    }
}
