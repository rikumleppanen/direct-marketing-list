package my.marketing.list;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        //Shall read the data from a txt file     
        FileReader just = new FileReader();
        just.read("koe.txt");
        //Lets take contacts out of the FileReader
        List contacts = just.getList();

        //Lets create a special ContactList where individual contact rows are bundled together by insertations
        ContactList contactsFromChannelOne = new ContactList("ChannelONE");
        System.out.println("There is " + just.getNumberOfRows() + " rows of contact data to be put on the list " + contactsFromChannelOne.getNameOfContactList());
        //lets add all the given data to our ContactLit
        contactsFromChannelOne.addContactToList(contacts);
        //next we will clean and classify the contacts within ContactList
        contactsFromChannelOne.cleanAndClassify(contacts);

        //Lets create some Customers that are already in the customer list to be matched with fresh contact data
        CustomerList customers = new CustomerList();
        customers.addNewCustomer("0407378716", Type.phone, 45);
        customers.addNewCustomer("0504561234", Type.phone, 101);
        customers.updateExistingCustomer(customers.getCustomer(101), "ahakutti@hotmail.com", Type.email);

        //Lets sort and update/create customers by comparing customers with contact list
        System.out.println("xxxxx");
        for (Integer key : contactsFromChannelOne.keySet()) {
            customers.sortAndCreate2(key, contactsFromChannelOne);
        }
        customers.print();
        System.out.println("xxxxx");

        //Lets create a special Marketing List that will be the basis for a campaign
        MarketingList spring = new MarketingList("SpringCampaingEmail");
        List<Consent> consents = customers.getAllConsentsList();
        //Lets be specific and say we want to use just emails in our spring campaign
        spring.addToCampaign(Type.email, consents);
        spring.print();

        System.out.println("xxxxx");
        //contactsFromChannelOne.print();
        FileWriter writer = new FileWriter();
        writer.write(spring.getName() + ".txt", spring);
        //Montako riviä tallennettiin Result-tiedostoon
        System.out.println("How many rows were saved to the " + spring.getName() + " : " + writer.getRowCount());
    }
}
