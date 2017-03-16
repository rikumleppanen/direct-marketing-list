package my.marketing.list;

import java.util.ArrayList;
import java.util.List;

public class CustomerList {

    private List<Customer> custolist;

    public CustomerList() {
        this.custolist = new ArrayList<>();
    }

    public void addNewCustomer(String row, Type type) {
        if (type == Type.phone) {
            this.custolist.add(new Customer().setNumberC(row));
        }
        if (type == Type.email) {
            this.custolist.add(new Customer().setEmailC(row));
        }

    }

    public boolean existsCustomer(String row) {
        for (Customer one : custolist) {
            if (one.getEmail().equals(row) && one.getNumber().equals(row)) {
                return true;
            }
        }
        return false;
    }

    public boolean isCustomer(String row) {
        for (Customer one : custolist) {
            if (one.getEmail().equals(row) || one.getNumber().equals(row)) {
                return true;
            }
        }
        return false;
    }

    public Customer getCustomer(String row) {
        for (Customer one : custolist) {
            if (one.getEmail().equals(row) || one.getNumber().equals(row)) {
                return one;
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

    public void print() {
        for (Customer one : custolist) {
            System.out.println(one.getNumber() + " " + one.getEmail());
        }
    }
}
