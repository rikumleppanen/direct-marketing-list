package fi.marketing.list;

import fi.marketing.list.logic.FileWriter;
import fi.marketing.list.logic.FileReader;
import fi.marketing.list.logic.Customer;
import fi.marketing.list.logic.Type;
import fi.marketing.list.logic.lists.ContactList;
import fi.marketing.list.logic.lists.CustomerList;
import fi.marketing.list.logic.lists.MarketingList;
import java.io.InputStream;
import java.util.List;

/**
 * Operator takes control of the operations and information needed by the UI
 * users.
 */
public class Operator {

    private CustomerList customers;
    private int contactCount;
    private int writtenCount;

    /**
     * Operator requires a CustomerList in order to take uploads of new contacts
     * and provide downloads for marketing purposes.
     *
     * @param cust is the existing customerlist of the service provider.
     */
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

    /**
     * Matching a new contact list to the existing customers entails reading a
     * list, collecting items to contact list, parsing, labeling and
     * transferring contacts into customers.
     *
     * @param just is the FileReader which can read files and take inputstream
     * if needed
     * @param listname is the name the user gives as a name of contactlist, that
     * is the source or channel of contacts
     */
    public void matchAContactListToCustomers(FileReader just, String listname) {
        List contacts = just.getList();
        setContactCountNumber(just.getNumberOfRows());
        ContactList contactsFromASource = new ContactList(listname);
        contactsFromASource.addContactToList(contacts);
        contactsFromASource.cleanAndClassify(contacts);
        labelingContacts(contactsFromASource);
        transferringContactsIntoCustomers(contactsFromASource);
    }

    /**
     * FileReader reads an uploaded file.
     *
     * @param filename is tne name of file uploaded to the system
     * @return gives a FileReader object
     */
    public FileReader fileReader(String filename) {
        FileReader one = new FileReader();
        one.read(filename);
        return one;
    }

    /**
     * FileReaderInputStream reads an uploaded file from an input stream.
     *
     * @param is is tne input stream
     * @return gives a FileReader object
     */
    public FileReader fileReaderInputStream(InputStream is) {
        FileReader one = new FileReader();
        one.readInputStream(is);
        return one;
    }

    /**
     * Labeling Contacts is a part of the process to matching contacts to the
     * existing customers.
     *
     * @param one is the uploaded contact list
     */
    public void labelingContacts(ContactList one) {
        for (Integer key : one.keySet()) {
            customers.searchAndLabel(key, one);
        }
    }

    /**
     * Parsed and labeled Contacts are then transferred to new customers or
     * updated by new customer info or consents.
     *
     * @param one is the uploaded contact list
     */
    public void transferringContactsIntoCustomers(ContactList one) {
        for (Integer key : one.keySet()) {
            customers.createAndUpdate(key, one);
        }
    }

    /**
     * The duration of processes can be observed. If the duration of a certain
     * process is under one second, the output is given in milliseconds.
     *
     * @param startTime is the time when a certain process started
     * @param endTime is the time when a certain process ended
     * @return provides a text as a result
     */
    public String printTime(long startTime, long endTime) {
        String text = "";
        if (endTime - startTime <= 1000) {
            text = "The parsing and matching process took " + (endTime - startTime) + " milliseconds.";
        } else {
            text = "The parsing and matching process took " + (endTime - startTime) / 1000 + " seconds.";
        }
        return text;
    }

    /**
     * Marketing list can be generated by the type of channel, that is, by the
     * emails or phone numbers.
     *
     * @param listname is the name given for a specified campaign
     * @param type is the type of emails or phone numbers
     * @return a marketing list is provided after is has been created
     */
    public MarketingList createAMarketingList(String listname, Type type) {
        MarketingList spring = new MarketingList(listname);
        List<Customer> custolist = customers.getCustomers();

        if (type == Type.phone) {
            spring.addToCampaign(Type.phone, custolist);
        } else if (type == Type.email) {
            spring.addToCampaign(Type.email, custolist);
        }
        fileWriter(spring);
        return spring;
    }

    /**
     * A file can be written after a marketing list ist generated.
     *
     * @param list is the marketing list which need to be written into a file.
     */
    public void fileWriter(MarketingList list) {
        FileWriter writer = new FileWriter();
        writer.write(list.getName() + ".txt", list);
        setWrittenCount(writer.getRowCount());
    }
}
