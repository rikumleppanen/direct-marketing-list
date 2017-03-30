package my.marketing.list;

import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class ContactListTest {

    ContactList channel;
    CustomerList customers;
    List contacts;
    String name = "testingCampaign";
    Contact alpha;
    Contact beta;

    @Before
    public void setUp() {
        channel = new ContactList(name);
        contacts = new ArrayList<>();
        contacts.add(new Contact("0402202", 3));
        contacts.add(new Contact("091234", 2));
        contacts.add(new Contact("riku@riku.fi", 2));
        contacts.add(new Contact("arto@arto.fi", 3));
        contacts.add(new Contact("+358-50-456 1234", 4));
        contacts.add(new Contact("(358)-0400 123 4567", 5));
        contacts.add(new Contact("358401234567", 6));
        contacts.add(new Contact("0407378716", 7));
        contacts.add(new Contact("riku@rikunauski.fi", 7));
        contacts.add(new Contact("testemail@helsinki", 8));
        contacts.add(new Contact("+44(343)-123 4567", 9));
        contacts.add(new Contact("+44(343)-123oho4567000", 9));
        contacts.add(new Contact("+358045678910", 10));
        alpha = new Contact("testing@testing.com", 188);
        beta = new Contact("testing@testing.com", 200);
        contacts.add(alpha);
        contacts.add(beta);
        channel.addContactToList(contacts);
        channel.getContact("riku@riku.fi").setState(State.sameEmail);
        channel.getContact("091234").setState(State.sameNumber);
        channel.getContact("testemail@helsinki").setState(State.notFound);

        customers = new CustomerList();
        customers.addNewCustomer("091234", Type.phone, 50);
        customers.updateExistingCustomer(customers.getCustomerInsertId(50), "riku@riku.fi", Type.email);
        customers.addNewCustomer("arto@arto.fi", Type.email, 60);

    }

    @Test
    public void listExists() {
        assertTrue(channel != null);
        assertEquals(name, channel.toString());
    }

    @Test
    public void cleanAndClassifyTest() {
        channel.cleanAndClassify(contacts);

        assertEquals(Type.email, channel.getContact("riku@riku.fi").getType());
        assertEquals(Type.phone, channel.getContact("0402202").getType());
        assertEquals(Type.phone, channel.getContact("0504561234").getType());
        assertEquals(Type.foreign, channel.getContact("04001234567").getType());
        assertEquals(Type.unknown, channel.getContact("testemail@helsinki").getType());
        assertEquals(Type.foreign, channel.getContact("+443431234567").getType());
        assertEquals(Type.foreign, channel.getContact("+44343123oho4567000").getType());
        assertEquals(Type.phone, channel.getContact("045678910").getType());

    }

    @Test
    public void getContactTest() {
        assertEquals("testemail@helsinki", channel.getContact("testemail@helsinki").getRow());
        assertEquals("091234", channel.getContact("091234").getRow());
    }

    @Test
    public void getterTests() {
        assertTrue(channel.keySet() != null);
        assertEquals(contacts.get(4).hashCode(), channel.get(4).get(0).hashCode());
        assertEquals(contacts.get(4), channel.getContact(4, 0));
        assertEquals(State.sameNumber, channel.getContact("091234").getState().sameNumber);
    }

    @Test
    public void hashcodeTests() {
        assertEquals(customers.getCustomer("091234").hashCodeNumber(), channel.getContact("091234").hashCodeNumber());
        assertNotEquals(customers.getCustomer("091234").hashCode(), channel.getContact("091234").hashCode());
        assertEquals(customers.getCustomer("arto@arto.fi").hashCodeEmail(), channel.getContact("arto@arto.fi").hashCodeEmail());
        assertTrue(alpha.equals(beta));
        assertEquals(alpha.hashCode(), beta.hashCode());
        assertEquals(alpha.hashCodeNumber(), alpha.hashCodeNumber());
    }
}
