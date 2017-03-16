package my.marketing.list;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        //Data sisään
        Map<Integer, List<Contact>> mappi = new HashMap<>();
        List<Contact> lista = new ArrayList<>();
        lista.add(new Contact("0402202", 1));
        lista.add(new Contact("091234", 2));
        lista.add(new Contact("riku@riku.fi", 2));
        lista.add(new Contact("arto@arto.fi", 3));
        lista.add(new Contact("+358-50-456 1234", 4));
        lista.add(new Contact("(358)-0400 123 4567", 5));
        lista.add(new Contact("358401234567", 6));
        //luokittelu ja puhdistus (pitäiskö olla eka puhdistus ja sitten luokittelu?)
        for (Contact item : lista) {
            if (item.isEmail() == true) {
                if (item.isValidEmailAddress() == true) {
                    item.setType(Type.email);
                } else {
                    item.setType(Type.unknown);
                }
            } else {
                if (item.inNumberFormat() == true) {
                    item.setType(Type.phone);
                } else {
                    item.emailClean();
                    if (item.inNumberFormat() == true) {
                        item.setType(Type.phone);
                    } else {
                        item.setType(Type.foreign);
                    }
                }
            }
        }
        //lajittelu
        for (Contact item : lista) {
            if (!mappi.containsKey(item.getInsertid())) {
                mappi.put(item.getInsertid(), new ArrayList<>());
                mappi.get(item.getInsertid()).add(item);
            } else {
                mappi.get(item.getInsertid()).add(item);
            }
        }
        //vertailu
        List<Customer> custo = new ArrayList<>();
        CustomerList uber = new CustomerList();
        for (Integer key : mappi.keySet()) {
            for (Contact one : mappi.get(key)) {
                if (mappi.get(key).size() == 1) {
                    uber.addNewCustomer(one.getRow(), one.getType());
                } else {

                }

//                    if (uber.existsNumber(one.getRow()) == true) {
//                        uber.setEmail(uber.getCustomer(one.getRow()), one.getRow());
//                    }
            }
        }

        uber.print();

        for (Integer key : mappi.keySet()) {
            System.out.println(key);
        }

        for (Integer key : mappi.keySet()) {
            for (Contact one : mappi.get(key)) {
                System.out.println(one.getInsertid() + " " + one.getRow() + " " + one.getType());
            }
        }

        for (Integer key : mappi.keySet()) {

        }
    }

}
