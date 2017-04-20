package fi.marketing.list.logic.lists;

import fi.marketing.list.FileReader;
import fi.marketing.list.logic.Contact;
import fi.marketing.list.logic.Customer;
import fi.marketing.list.logic.Type;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class CustomerListTest {

    CustomerList customers;
    ContactList contactsList;
    FileReader reader;
    List<Contact> row;
    Customer one;
    Customer two;
    String email = "rabe@rabe.com";
    String number = "010665000";

    @Before
    public void setUp() {
        reader = new FileReader();
        reader.read("koe1.txt");
        List contacts = reader.getList();
        customers = new CustomerList();
        one = new Customer();
        contactsList = new ContactList("Test");
        contactsList.addContactToList(contacts);
        contactsList.cleanAndClassify(contacts);
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
        //Lets give state-labels to contacts to make it easer to create and update the customers
        for (Integer key : contactsList.keySet()) {
            customers.searchAndLabel(key, contactsList);
        }
        //Lets create and update customers according to the contents of contact list
        for (Integer key : contactsList.keySet()) {
            customers.createAndUpdate(key, contactsList);
        }
    }

    @Test
    public void existsCustomerList() {
        assertTrue(customers != null);
    }

    @Test
    public void addAndUpdateCustomerToListTest() {
        customers.addNewCustomer(email, Type.email, 899);
        customers.addNewCustomer(number, Type.phone, 900);
        assertEquals(email, customers.getCustomerInsertId(899).getEmail());
        assertEquals(number, customers.getCustomerInsertId(900).getNumber());
        customers.updateExistingCustomer(customers.getCustomer("a4@a4.fi"), "0451234567", Type.phone);
        customers.updateExistingCustomer(customers.getCustomerInsertId(60), "030456789", Type.phone);
        assertEquals("a4@a4.fi", customers.getCustomer("a4@a4.fi").getEmail());
        assertEquals("030456789", customers.getCustomerInsertId(60).getNumber());
        assertEquals(email, customers.getCustomerCusnoId(customers.getCustomer(email).getCusnoId()).getEmail());

    }

    @Test
    public void createNewCustomersTest() {

        //Test case 1 (add an email address)
        assertEquals("n1@n1.fi", customers.getCustomer("n1@n1.fi").getEmail());
        assertEquals("", customers.getCustomer("n1@n1.fi").getNumber());
        //Test case 2 (add a phone number)
        assertEquals("021234567", customers.getCustomer("021234567").getNumber());
        assertEquals("", customers.getCustomer("021234567").getEmail());
        //Test case 3 (add both email address and phone number)
        assertEquals("n3@n3.fi", customers.getCustomer("n3@n3.fi").getEmail());
        assertEquals("035467865", customers.getCustomer("n3@n3.fi").getNumber());
    }

    @Test
    public void addARowToExistingCustomerTest() {
        //Test case 4 (add a phone number to the customer who has the same email address)
        assertEquals("a4@a4.fi", customers.getCustomer("a4@a4.fi").getEmail());
        assertEquals("043456773", customers.getCustomer("a4@a4.fi").getNumber());
        //Test case 5 (add a email address to the customer who has the same phone number)
        assertEquals("0507378716", customers.getCustomer("0507378716").getNumber());
        assertEquals("a5@a5.fi", customers.getCustomer("0507378716").getEmail());
    }

    @Test
    public void updateAConsentToExistingCustomerTest() {
        //Test case 6 (add a new email consent)
        assertEquals(2, customers.getCustomer("uc6@uc6.fi").getConsentList(Type.email).size());
        //Test case 7 (add a new phone number consent)
        assertEquals(2, customers.getCustomer("070345678").getConsentList(Type.phone).size());
        //Test case 8 (add both email and phone number consent)
        assertEquals(2, customers.getCustomer("0812345673").getConsentList(Type.phone).size());
        assertEquals(2, customers.getCustomer("0812345673").getConsentList(Type.email).size());
        //Test case 9 (add an email address consent to customer who has existing email address and phone number)
        assertEquals(1, customers.getCustomer("0912334342").getConsentList(Type.phone).size());
        assertEquals(2, customers.getCustomer("0912334342").getConsentList(Type.email).size());
        //Test case 10 (add a phone number consent to customer who has existing email address and phone number)
        assertEquals(2, customers.getCustomer("102343434").getConsentList(Type.phone).size());
        assertEquals(1, customers.getCustomer("102343434").getConsentList(Type.email).size());
    }
    
    @Test
    public void updateARowToExistingCustomerTest() {
        //Test case 11 (update phone number to the customer who has been matched by email address)
        assertEquals("cuc11@cuc11.com", customers.getCustomer("cuc11@cuc11.com").getEmail());
        assertEquals("111111111", customers.getCustomer("cuc11@cuc11.com").getNumber());
        //Test case 12 (update email address to the customer who has been matched by phone number)
        assertEquals("0403456789", customers.getCustomer("0403456789").getNumber());
        assertEquals("cuc12@cuc12.fi", customers.getCustomer("0403456789").getEmail());
    }
    @Test
    public void countingTest() {
        assertEquals(10,customers.getNumberOfEmails());
        assertEquals(10,customers.getNumberOfPhoneNumbers());
        assertEquals(12,customers.numberOfCustomers());
    }
    @Test
    public void existsNumberTest() {
        assertTrue(customers.existsNumber("0507378716"));
        assertFalse(customers.existsNumber("05073787164"));

    }

//    @Test
//    public void getterTest() {
//        assertEquals("ahakutti@hotmail.com",customers.getCustomerInsertId(101).getEmail());
//        assertEquals("0504561234", customers.getCustomerInsertId(101).getNumber());
//        assertEquals("0407378716",customers.getCustomer("0407378716").getNumber());
//        assertEquals("",customers.getCustomer("0407378716").getEmail());
//        assertEquals(null,customers.getConsentList(one));
//    }
//    
//    @Test
//    public void addCustomerTest() {
//        
//    }
}
