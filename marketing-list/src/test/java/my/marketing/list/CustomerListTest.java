package my.marketing.list;

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
    ContactList contacts;
    List<Contact> row;
    Customer one;
    Customer two;
    String email = "0407378716";

    @Before
    public void setUp() {
        customers = new CustomerList();
        contacts = new ContactList("Test");
        one = new Customer();
        one.setID(10);
        customers.addNewCustomer("0407378716", Type.phone, 5); 
        customers.addNewCustomer("0504561234", Type.phone, 101);
        customers.updateExistingCustomer(customers.getCustomer(101), "ahakutti@hotmail.com", Type.email);
        row = new ArrayList<>();
        row.add(new Contact("0402202", 3));
        row.add(new Contact("091234", 2));
        row.add(new Contact("riku@riku.fi", 2));
        row.add(new Contact("arto@arto.fi", 3));
        row.add(new Contact("+358-50-456 1234", 4));
        contacts.addContactToList(row);
        contacts.cleanAndClassify(row);
    }

    @Test
    public void getterTest() {
        assertEquals("ahakutti@hotmail.com",customers.getCustomer(101).getEmail());
        assertEquals("0504561234", customers.getCustomer(101).getNumber());
        assertEquals("0407378716",customers.getCustomer("0407378716").getNumber());
        assertEquals("",customers.getCustomer("0407378716").getEmail());
        assertEquals(null,customers.getConsentList(one));
    }
    
    @Test
    public void addCustomerTest() {
        
    }

}
