package my.marketing.list;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        CustomerList customers = new CustomerList();
        ContactList contactsListOne = new ContactList("Arvonta1");
        //Data sisään
        List<Contact> lista = new ArrayList<>();
        lista.add(new Contact("0402202", 3));
        lista.add(new Contact("091234", 2));
        lista.add(new Contact("riku@riku.fi", 2));
        lista.add(new Contact("arto@arto.fi", 3));
        lista.add(new Contact("+358-50-456 1234", 4));
        lista.add(new Contact("(358)-0400 123 4567", 5));
        lista.add(new Contact("358401234567", 6));
        lista.add(new Contact("0407378716", 7));
        lista.add(new Contact("riku@rikunauski.fi", 7));
        //lisätään koetapaukset arvonta-listalle
        contactsListOne.addContactToList(lista);
        //puhdistaa ja luokittelee
        contactsListOne.cleanAndClassify(lista);

        //luodaan kokeeksi olemassa olevia testiasiakkaita
        customers.addNewCustomer("0407378716", Type.phone, 45);

        //contactien vienti asiakkaiksi - ei vielä vertaile olemassaoleviin
        System.out.println("xxxxx");
        for (Integer key : contactsListOne.keySet()) {
            int n = contactsListOne.get(key).size();
            if (n == 1) {
                Contact two = contactsListOne.getContact(key, 0);
                customers.addNewCustomer(two.getRow(), two.getType(), two.getInsertid());
            } else {
                for (int i = 0; i < n; i++) {
                    Contact one = contactsListOne.getContact(key, i);
                    if (i == 0) {
                        customers.addNewCustomer(one.getRow(), one.getType(), one.getInsertid());
                    }
                    customers.updateExistingCustomer(customers.getCustomer(one.getInsertid()), one.getRow(), one.getType());
                }
            }
        }

        customers.print();
        System.out.println("xxxxx");

    }
}
