package fi.marketing.list;

import fi.marketing.list.logic.Customer;
import fi.marketing.list.logic.Type;
import fi.marketing.list.logic.lists.ContactList;
import fi.marketing.list.logic.lists.CustomerList;
import fi.marketing.list.logic.lists.MarketingList;
import java.util.List;

public class Operator {

    CustomerList customers;
    int contactCount;
    int writtenCount;

    public Operator(CustomerList cust) {
        customers = cust;
        contactCount = 0;
        writtenCount = 0;
    }

    public void setContactCountNumber(int number) {
        this.contactCount = number;
    }

    public int getContactCountNumber() {
        return contactCount;
    }

    public int getWrittenCount() {
        return writtenCount;
    }

    public void setWrittenCount(int writtenCount) {
        this.writtenCount = writtenCount;
    }

    public CustomerList getCustomers() {
        return this.customers;
    }

    public void matchAContactListToCustomers(String filename, String listname) {
        FileReader just = new FileReader();
        just.read(filename);
        List contacts = just.getList();
        setContactCountNumber(just.getNumberOfRows());
        ContactList contactsFromASource = new ContactList(listname);
        //lets add all the given data to our ContactLit
        contactsFromASource.addContactToList(contacts);
        //next we will clean and classify the contacts within ContactList
        contactsFromASource.cleanAndClassify(contacts);
        //Lets give state-labels to contacts to make it easer to create and update the customers
        for (Integer key : contactsFromASource.keySet()) {
            customers.searchAndLabel(key, contactsFromASource);
        }
        //Lets create and update customers according to the contents of contact list
        for (Integer key : contactsFromASource.keySet()) {
            customers.createAndUpdate(key, contactsFromASource);
        }
    }

    public String printTime(long startTime, long endTime) {
        String text = "";
        if (endTime - startTime <= 1000) {
            text = "The cleaning and matching process took " + (endTime - startTime) + " milliseconds.";
        } else {
            text = "The cleaning and matching process took " + (endTime - startTime) / 1000 + " seconds.";
        }
        return text;
    }

    public MarketingList createAMarketingList(String filename, String listname, Type type) {

        MarketingList spring = new MarketingList(listname);
        //Lets be specific and say we want to use just emails in our spring campaign
        List<Customer> custolist = customers.getCustomers();

        if (type == Type.phone) {
            spring.addToCampaign(Type.phone, custolist);
        } else if (type == Type.email) {
            spring.addToCampaign(Type.email, custolist);
        }

        FileWriter writer = new FileWriter();
        writer.write(spring.getName() + ".txt", spring);
        setWrittenCount(writer.getRowCount());
        return spring;
        //System.out.println("How many rows were saved to the " + spring.getName() + " : " + writer.getRowCount());
        //UI
        //SwingUtilities.invokeLater(new UserInterface(customers));
//                        SwingUtilities.invokeLater(new Runnable() {
//                            public void run() {
//                                //Turn off metal's use of bold fonts
//                                UIManager.put("swing.boldMetal", Boolean.FALSE);
//                                new ResultArea(spring).setVisible(true);
//                            }
//                        });
//                        break;
    }

}
