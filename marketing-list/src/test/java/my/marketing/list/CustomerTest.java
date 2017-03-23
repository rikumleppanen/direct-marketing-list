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

    @Before
    public void setUp() {
        one = new Customer();
    }

    @Test
    public void customerExists() {
        assertTrue(one != null);
    }

    @Test
    public void canSetARow() {
        one.setEmail(email);
        one.setNumber(number);
        assertEquals(email, one.getEmail());
        assertEquals(number, one.getNumber());
    }
    

    @Test
    public void getterTest() {
        assertEquals(0,one.getID());
        one.setID(5);
        assertEquals(5,one.getID());
        assertEquals("",one.getEmail());
        assertEquals("",one.getNumber());
        assertEquals(one.hashCodeEmail()+one.hashCodeNumber(),one.hashCode());
        one.setEmail(email);
        one.setNumber(number);
        assertEquals(email,one.getEmail());
        assertEquals(number,one.getNumber());
        assertEquals(one.hashCodeEmail()+one.hashCodeNumber(),one.hashCode());
    }
    
    @Test
    public void equalsTest() {
        assertTrue(one.equals(one));
    }

}
