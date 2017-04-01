package fi.marketing.list;

import fi.marketing.list.logic.Consent;
import fi.marketing.list.logic.lists.CustomerList;
import fi.marketing.list.logic.lists.ContactList;
import fi.marketing.list.logic.Customer;
import fi.marketing.list.logic.lists.MarketingList;
import fi.marketing.list.logic.Type;
import fi.marketing.list.ui.*;
import java.util.List;
import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {

        //Shall read the data from a txt file     
        FileReader just = new FileReader();
        just.read("koe1.txt");
        //Lets take contacts out of the FileReader
        List contacts = just.getList();

        //Lets create a special ContactList where individual contact rows are bundled together by insertations
        ContactList contactsFromChannelOne = new ContactList("ChannelONE");
        System.out.println("There is " + just.getNumberOfRows() + " rows of contact data to be put on the list " + contactsFromChannelOne.getNameOfContactList());
        //lets add all the given data to our ContactLit
        contactsFromChannelOne.addContactToList(contacts);
        //next we will clean and classify the contacts within ContactList
        contactsFromChannelOne.cleanAndClassify(contacts);

        //Lets create Customers that are already in the customer list to be matched with fresh contact data
        CustomerList customers = new CustomerList();
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
        System.out.println("ZZZZ");

        //Lets give state-labels to contacts to make it easer to create and update the customers
        for (Integer key : contactsFromChannelOne.keySet()) {
            customers.searchAndLabel(key, contactsFromChannelOne);
        }
        //Lets create and update customers according to the contents of contact list
        System.out.println("rrrr");
        for (Integer key : contactsFromChannelOne.keySet()) {
            customers.createAndUpdate(key, contactsFromChannelOne);
        }
        System.out.println("wwwww");
        customers.print();
        System.out.println("dddd");
        //contactsFromChannelOne.print();
        System.out.println("dddd");
        //Lets create a special Marketing List that will be the basis for a campaign
        MarketingList spring = new MarketingList("SpringCampaingEmail");
        //Lets be specific and say we want to use just emails in our spring campaign
        List<Customer> custolist = customers.getCustomers();
        spring.addToCampaign(Type.phone, custolist);
        spring.print();

        //Lets transfer the SpringCampaignEmail-list to a txt
        FileWriter writer = new FileWriter();
        writer.write(spring.getName() + ".txt", spring);
        System.out.println("How many rows were saved to the " + spring.getName() + " : " + writer.getRowCount());
        
        //UI
        SwingUtilities.invokeLater(new UserInterface());
        
        //Testing timestamps
        Consent alpha = new Consent("112", Type.phone);
        try {
            Thread.sleep(1000);                 //1000 milliseconds is one second.
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        Consent beta = new Consent("3456",Type.phone);
        System.out.println(alpha.getTimestamp().before(beta.getTimestamp()));
        System.out.println(alpha.getTimestamp());
        alpha.setNewTimestamp();
        System.out.println(alpha.getTimestamp());
        System.out.println(alpha.getTimestamp().before(beta.getTimestamp()));
    }
}
