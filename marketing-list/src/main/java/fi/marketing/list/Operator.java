package fi.marketing.list;

import fi.marketing.list.logic.Customer;
import fi.marketing.list.logic.Type;
import fi.marketing.list.logic.lists.ContactList;
import fi.marketing.list.logic.lists.CustomerList;
import fi.marketing.list.logic.lists.MarketingList;
import java.io.File;
import java.io.InputStream;
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

    public void matchAContactListToCustomers(FileReader just, String listname) {
        List contacts = just.getList();
        setContactCountNumber(just.getNumberOfRows());
        ContactList contactsFromASource = new ContactList(listname);
        contactsFromASource.addContactToList(contacts);
        contactsFromASource.cleanAndClassify(contacts);
        labelingContacts(contactsFromASource);
        transferringContactsIntoCustomers(contactsFromASource);
    }

    public FileReader fileReader(String filename) {
        FileReader one = new FileReader();
        one.read(filename);
        return one;
    }

    public FileReader fileReaderInputStream(InputStream is) {
        FileReader one = new FileReader();
        one.readInputStream(is);
        return one;
    }

    public void labelingContacts(ContactList one) {
        for (Integer key : one.keySet()) {
            customers.searchAndLabel(key, one);
        }
    }

    public void transferringContactsIntoCustomers(ContactList one) {
        for (Integer key : one.keySet()) {
            customers.createAndUpdate(key, one);
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
    }

}
