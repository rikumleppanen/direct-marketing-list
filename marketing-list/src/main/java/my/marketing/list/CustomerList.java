package my.marketing.list;

import java.util.ArrayList;
import java.util.List;

public class CustomerList {

    private List<Customer> custolist;

    public CustomerList() {
        this.custolist = new ArrayList<>();
    }

    public void addNewCustomer(String row, Type type, int id) {
        if (type == Type.phone) {
            this.custolist.add(new Customer().setNumberC(row).setID(id));

        }
        if (type == Type.email) {
            this.custolist.add(new Customer().setEmailC(row).setID(id));
        }

    }

    public void updateExistingCustomer(Customer customer, String row, Type type) {
        if (type == Type.phone && customer.getNumber() == "") {
            setNumber(customer, row);
        }
        if (type == Type.email && customer.getEmail() == "") {
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

//    JÄIN TÄHÄN VERTAILUJA Varten tarvittaneen hashcodet ja object equal-vertailut?
    public boolean existsCustomerB(String row) {
        for (Customer one : custolist) {
            if ((one.getEmail() != null || one.getNumber() != null) && (one.getEmail().equals(row) == true || one.getNumber().equals(row) == true)) {
                return true;
            }
        }
        return false;
    }

    public boolean customerIsCustomer(String row) {
        for (Customer one : custolist) {
            if (one.getEmail().equals(row) || one.getNumber().equals(row)) {
                return true;
            }
        }
        return false;
    }

    public boolean rowIsCustomer(String row) {
        for (Customer one : custolist) {
            if (one.getEmail().equals(row) || one.getNumber().equals(row)) {
                return true;
            }
        }
        return false;
    }

    public boolean existsCustomer(int id) {
        for (Customer one : custolist) {
            if (one.getID() == id) {
                return true;
            }
        }
        return false;
    }

    public Customer getCustomer(int id) {
        for (Customer one : custolist) {
            if (one.getID() == id) {
                return one;
            }
        }
        return null;
    }
//    public Customer getCustomer(String row) {
//        for (Customer one : custolist) {
//            if (one.getEmail().equals(row) || one.getNumber().equals(row)) {
//                return one;
//            }
//        }
//        return null;
//    }

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
            System.out.println(one.getNumber() + " " + one.getEmail() + one.getID());
        }
    }
}
