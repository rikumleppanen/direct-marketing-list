package my.marketing.list;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class CustomerTest {

    Customer one;
    Customer two;
    String email = "test.user@helsinki.fi";
    String number = "0401234567";
    Customer three;
    Consent alpha;
    Consent beta;

    @Before
    public void setUp() {
        one = new Customer();
        two = new Customer();
        three = new Customer("opera@mundi.com", "0401234567");
        alpha = new Consent("test.user@helsinki.fi", Type.email);
        beta = new Consent("0401234567", Type.phone);
    }

    @Test
    public void customerExists() {
        assertTrue(one != null);
        assertTrue(three != null);
    }

    @Test
    public void canSetARow() {
        one.setEmail(email);
        one.setNumber(number);
        assertEquals(email, one.getEmail());
        assertEquals(number, one.getNumber());
        assertEquals("opera@mundi.com", three.getEmail());
        three.setEmail(email);
        assertEquals(email, three.getEmail());
        assertEquals("ape@ape.com", two.setEmailC("ape@ape.com").getEmail());
        assertEquals("", two.getNumber());
        assertEquals("0505556", two.setNumberC("0505556").getNumber());
        assertNotEquals("", two.getNumber());
    }

    @Test
    public void getterTest() {
        assertEquals(0, one.getInsertId());
        one.setID(5);
        assertEquals(5, one.getInsertId());
        assertEquals("", one.getEmail());
        assertEquals("", one.getNumber());
        assertEquals(one.hashCodeEmail() + one.hashCodeNumber(), one.hashCode());
        one.setEmail(email);
        one.setNumber(number);
        assertEquals(email, one.getEmail());
        assertEquals(number, one.getNumber());
        assertEquals(one.hashCodeEmail() + one.hashCodeNumber(), one.hashCode());
    }

    @Test
    public void consentTest() {
        assertTrue(one.getConsentList() != null);
        two.updateConsentList(email, Type.email);
        assertEquals(email, two.getConsentList().get(0).getRow());
        assertEquals(Type.email, two.getConsentList().get(0).getType());
        assertEquals("test.user@helsinki.fi", alpha.getRow());
        assertEquals("0401234567", beta.getRow());
        assertEquals(email, two.getConsentList(Type.email).get(0).getRow());
    }

    @Test
    public void maxCOnsentTest() {
        two.updateConsentList(email, Type.email);
        try {
            Thread.sleep(10);                 //1000 milliseconds is one second.
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        two.updateConsentList("retu@reetu.com", Type.email);
        assertEquals("retu@reetu.com", two.getMaxConsent(Type.email).getRow());
    }

    @Test
    public void equalsTest() {
        assertTrue(one.equals(one));

    }
}
