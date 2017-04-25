package fi.marketing.list;

import fi.marketing.list.logic.lists.CustomerList;
import fi.marketing.list.logic.Type;
import fi.marketing.list.ui.*;

public class Main {

    public static void main(String[] args) {
        CustomerList customers = new CustomerList();
        loadingExistingCustomers(customers);

        Operator op = new Operator(customers);
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DashboardUI(op).setVisible(true);
            }
        });
    }

    public static void loadingExistingCustomers(CustomerList customers) {
        customers.addNewCustomer("a4@a4.fi", Type.email, 40);
        customers.addNewCustomer("0507378716", Type.phone, 50);
        customers.addNewCustomer("uc6@uc6.fi", Type.email, 60);
        customers.addNewCustomer("070345678", Type.phone, 70);
        customers.addNewCustomer("0812345673", Type.phone, 80);
        customers.updateExistingCustomer(customers.getCustomerInsertId(80), "uc8@uc8.fi", Type.email);
        customers.addNewCustomer("0912334342", Type.phone, 90);
        customers.updateExistingCustomer(customers.getCustomerInsertId(90), "auc9@auc9.com", Type.email);
        customers.addNewCustomer("102343434", Type.phone, 100);
        customers.updateExistingCustomer(customers.getCustomerInsertId(100), "auc10@auc10.fi", Type.email);
        customers.addNewCustomer("1111122222", Type.phone, 110);
        customers.updateExistingCustomer(customers.getCustomerInsertId(110), "cuc11@cuc11.com", Type.email);
        customers.addNewCustomer("0403456789", Type.phone, 120);
        customers.updateExistingCustomer(customers.getCustomerInsertId(120), "cuc12cuc@cuc12cuc12.fi", Type.email);
    }
}
