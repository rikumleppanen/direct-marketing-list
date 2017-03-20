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
        Map<Integer, List<Contact>> mappi = new HashMap<>(); //tästä pian luovutaan
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
        //lisätään listalle
        contactsListOne.addContactToList(lista);
        //
        contactsListOne.cleanAndClassify(lista);
        
        //lajittelu; vastaa siis ContactListille vientiä
        for (Contact item : lista) {
            if (!mappi.containsKey(item.getInsertid())) {
                mappi.put(item.getInsertid(), new ArrayList<>());
                mappi.get(item.getInsertid()).add(item);
            } else {
                mappi.get(item.getInsertid()).add(item);
            }
        }
        //vertailu     

        customers.addNewCustomer("0407378716", Type.phone, 45);

        for (Integer key : mappi.keySet()) {
            for (Contact one : mappi.get(key)) {
                int j = mappi.get(key).size();
                if (j == 1) {
                    if (customers.customerIsCustomer(one.getRow()) == true) {
                        continue;//update luvat
                    } else {
                        if (customers.rowIsCustomer(one.getRow()) == true) {
                            continue; //uber.updateExistingCustomer(uber.getCustomer(one.getRow()), one.getRow(), one.getType());
                        } else {
                            if (customers.existsCustomerB(one.getRow()) == true) {
                                //uber.updateExistingCustomer(uber.getCustomer(one.getRow()), one.getRow(), one.getType());
                            }
                        }
                    }
                    customers.addNewCustomer(one.getRow(), one.getType(), one.getInsertid());

                } else {
                    if (customers.existsCustomer(one.getInsertid()) == true) {
                        //yhdistää 2 contactia yhteen customeriin
                        customers.updateExistingCustomer(customers.getCustomer(one.getInsertid()), one.getRow(), one.getType());
                    } else if (customers.rowIsCustomer(one.getRow())) {
                        //uber.updateExistingCustomer(uber.getCustomer(one.getRow()), one.getRow(), one.getType());
                    } else {
                        customers.addNewCustomer(one.getRow(), one.getType(), one.getInsertid());
                    }
                }
            }
        }

        customers.print();

        for (Integer key : mappi.keySet()) {
            System.out.println(key);
        }

        for (Integer key : mappi.keySet()) {
            for (Contact one : mappi.get(key)) {
                System.out.println(one.getInsertid() + " " + one.getRow() + " " + one.getType());
            }
        }
    }
}
