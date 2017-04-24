package fi.marketing.list;

import fi.marketing.list.logic.Consent;
import fi.marketing.list.logic.lists.CustomerList;
import fi.marketing.list.logic.lists.ContactList;
import fi.marketing.list.logic.Customer;
import fi.marketing.list.logic.lists.MarketingList;
import fi.marketing.list.logic.Type;
import fi.marketing.list.ui.*;
import java.util.List;
import java.util.Scanner;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class Main {

    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);

        //Lets create Customers that are already in the customer list to be matched with fresh contact data
        CustomerList customers = new CustomerList();
        loadingExistingCustomers(customers);
//        System.out.println("ZZZZ");
//        customers.addNewCustomer("0505551234", Type.phone, 130);
//
//        System.out.println("wwwww");
//        customers.print();
//        System.out.println("dddd");
//        //contactsFromChannelOne.print();
//        System.out.println("dddd");

        while (true) {
            System.out.println("Do you want to upload new contacts, create a new marketing list or quit?");
            System.out.print("> ");
            String decision = reader.nextLine();
            if (decision.equalsIgnoreCase("quit") || decision.equalsIgnoreCase("q")) {
                break;
            } else if ((decision.equalsIgnoreCase("upload") || decision.equalsIgnoreCase("contacts"))) {
                while (true) {
                    System.out.println("What is the name of file you want to upload? \n There are 'koe1.txt', 'test1.txt' and 'test2.txt' available.");
                    System.out.print("> ");
                    String name = reader.nextLine();
                    if (name.equalsIgnoreCase("break")) {
                        break;
                    } else if (name.equalsIgnoreCase("koe1.txt") || name.equalsIgnoreCase("test2.txt") || name.equalsIgnoreCase("test1.txt")) {
                        System.out.println("File is found!");

                        FileReader just = new FileReader();

                        just.read(name);
                        List contacts = just.getList();
                        System.out.println("What will be the source or channel (via they were received) of these contacts?");
                        System.out.print("> ");
                        String listname = reader.nextLine();
                        ContactList contactsFromASource = new ContactList(listname);
                        System.out.println("There is " + just.getNumberOfRows() + " rows of contact data to be put on the list " + contactsFromASource.getNameOfContactList());
                        //lets add all the given data to our ContactLit
                        long startTime = System.currentTimeMillis();
                        contactsFromASource.addContactToList(contacts);
                        //next we will clean and classify the contacts within ContactList
                        contactsFromASource.cleanAndClassify(contacts);
                        //Lets give state-labels to contacts to make it easer to create and update the customers
                        for (Integer key : contactsFromASource.keySet()) {
                            customers.searchAndLabel(key, contactsFromASource);
                        }
                        //Lets create and update customers according to the contents of contact list
                        System.out.println("rrrr");
                        for (Integer key : contactsFromASource.keySet()) {
                            customers.createAndUpdate(key, contactsFromASource);
                        }
                        long endTime = System.currentTimeMillis();
                        if (endTime - startTime <= 1000) {
                            System.out.println("The cleaning and matching process took " + (endTime - startTime) + " milliseconds");
                        } else {
                            System.out.println("The cleaning and matching process took " + (endTime - startTime) / 1000 + " seconds");
                        }
                        break;
                    } else {
                        System.out.println("The file is not available. Write the name again or break.");
                    }
                }
            } else if (decision.equalsIgnoreCase("create") || decision.equalsIgnoreCase("marketing list") || decision.equalsIgnoreCase("list")) {
                while (true) {
                    System.out.println("What will be the name of this marketing list?");
                    System.out.print("> ");
                    String campaignName = reader.nextLine();
                    if (!campaignName.isEmpty()) {

                        System.out.println("Do you want to collect phone numbers or emails?");
                        //Lets create a special Marketing List that will be the basis for a campaign
                        MarketingList spring = new MarketingList(campaignName);
                        //Lets be specific and say we want to use just emails in our spring campaign
                        List<Customer> custolist = customers.getCustomers();
                        while (true) {
                            System.out.print("> ");
                            String option = reader.nextLine();
                            if (option.equalsIgnoreCase("phone numbers") || option.equalsIgnoreCase("numbers")) {
                                spring.addToCampaign(Type.phone, custolist);
                                break;
                            } else if (option.equalsIgnoreCase("emails")) {
                                spring.addToCampaign(Type.email, custolist);
                                break;
                            } else {
                                System.out.println("Please state 'emails' or 'numbers' or 'phone numbers'");
                            }

                        }
                        FileWriter writer = new FileWriter();
                        writer.write(spring.getName() + ".txt", spring);
                        System.out.println("How many rows were saved to the " + spring.getName() + " : " + writer.getRowCount());
                        //UI
                        //SwingUtilities.invokeLater(new UserInterface(customers));
                        SwingUtilities.invokeLater(new Runnable() {
                            public void run() {
                                //Turn off metal's use of bold fonts
                                UIManager.put("swing.boldMetal", Boolean.FALSE);
                                new ResultArea(spring).setVisible(true);
                            }
                        });
                        break;
                    } else {
                        System.out.println("Please state a name for the marketing list.");
                    }
                }

            } else {
                System.out.println("Please state your command clearly");
            }

        }
        System.exit(0);

//        //Testing timestamps
//        Consent alpha = new Consent("112", Type.phone);
//        try {
//            Thread.sleep(1000);                 //1000 milliseconds is one second.
//        } catch (InterruptedException ex) {
//            Thread.currentThread().interrupt();
//        }
//        Consent beta = new Consent("3456", Type.phone);
//        System.out.println(alpha.getTimestamp().before(beta.getTimestamp()));
//        System.out.println(alpha.getTimestamp());
//        alpha.setNewTimestamp();
//        System.out.println(alpha.getTimestamp());
//        System.out.println(alpha.getTimestamp().before(beta.getTimestamp()));
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
