package fi.marketing.list.logic.lists;

import fi.marketing.list.FileReader;
import fi.marketing.list.logic.Customer;
import fi.marketing.list.logic.Contact;
import fi.marketing.list.logic.Contact;
import fi.marketing.list.logic.Customer;
import fi.marketing.list.logic.Type;
import fi.marketing.list.logic.Type;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
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

        //Test case 1
        assertEquals("n1@n1.fi", customers.getCustomer("n1@n1.fi").getEmail());
        assertEquals("", customers.getCustomer("n1@n1.fi").getNumber());
        //Test case 2
        assertEquals("021234567", customers.getCustomer("021234567").getNumber());
        assertEquals("", customers.getCustomer("021234567").getEmail());
        //Test case 3 + add a row
        assertEquals("n3@n3.fi", customers.getCustomer("n3@n3.fi").getEmail());
        assertEquals("035467865", customers.getCustomer("n3@n3.fi").getNumber());
    }

    @Test
    public void addARowToExistingCustomerTest() {
        //Test case 4
        assertEquals("a4@a4.fi", customers.getCustomer("a4@a4.fi").getEmail());
        assertEquals("043456773", customers.getCustomer("a4@a4.fi").getNumber());
        //Test case 5
        assertEquals("0507378716", customers.getCustomer("0507378716").getNumber());
        assertEquals("a5@a5.fi", customers.getCustomer("0507378716").getEmail());
    }

//    @Test
//    public void updateAConsentToExistingCustomerTest() {
//        //Test case 6   
//        //Test case 7      
//        //Test case 8      
//        //Test case 9
//        //Test case 10
//    }
    @Test
    public void updateARowToExistingCustomerTest() {
        //Test case 11
        assertEquals("cuc11@cuc11.com", customers.getCustomer("cuc11@cuc11.com").getEmail());
        assertEquals("111111111", customers.getCustomer("cuc11@cuc11.com").getNumber());
        //Test case 12
        assertEquals("0403456789", customers.getCustomer("0403456789").getNumber());
        assertEquals("cuc12@cuc12.fi", customers.getCustomer("0403456789").getEmail());
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
