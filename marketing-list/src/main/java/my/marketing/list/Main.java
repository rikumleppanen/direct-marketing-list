package my.marketing.list;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        CustomerList customers = new CustomerList();
        ContactList contactsListOne = new ContactList("Arvonta1");
        //Data sisään        
        FileReader just = new FileReader();
        just.read("koe.txt");
        List lista = just.getList();
        System.out.println("There is " + just.getNumberOfRows() + " rows of contact data to be put on the list " + contactsListOne.getNameOfContactList());
        //lisätään koetapaukset arvonta-listalle
        contactsListOne.addContactToList(lista);
        //puhdistaa ja luokittelee
        contactsListOne.cleanAndClassify(lista);

        //luodaan kokeeksi olemassa olevia testiasiakkaita
        customers.addNewCustomer("0407378716", Type.phone, 45);
        customers.addNewCustomer("0504561234", Type.phone, 101);
        customers.updateExistingCustomer(customers.getCustomer(101), "ahakutti@hotmail.com", Type.email);

        //contactien vienti asiakkaiksi - ei vielä täydellinen
        System.out.println("xxxxx");
        for (Integer key : contactsListOne.keySet()) {
            customers.sortAndCreate2(key, contactsListOne);
        }

        customers.print();
        System.out.println("xxxxx");
        contactsListOne.print();
        Consent lupa = new Consent("01023", Type.phone);
        System.out.println(lupa.getTimestamp());

    }
}
