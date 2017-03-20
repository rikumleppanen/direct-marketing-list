package my.marketing.list;

import java.util.Set;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class ContactTest {

    Contact one1;
    Contact one2;
    Contact two;
    Contact three;
    Contact four;
    Contact five;

    String email = "test.user@helsinki.fi";
    String number = "0401234567";
    String testnumber = "+358 40 1234";

    String testemail = "test..@helsinki";

    @Before
    public void setUp() {
        one1 = new Contact(email, 1);
        one2 = new Contact(number, 1);
        two = new Contact(email, 2);
        three = new Contact(number, 3);
        four = new Contact(testemail, 4);
        five = new Contact(testnumber, 5);
    }

    @Test
    public void contactExists() {
        assertTrue(one1 != null);
        assertTrue(one2 != null);
        assertTrue(one1.getType() == null);
    }

    @Test
    public void isEmailTest() {
        assertTrue(email, one1.isEmail());
        assertTrue(testemail, four.isEmail());
        assertFalse(number, one2.isEmail());
    }

    @Test
    public void isEmailValidAddressTest() {
        assertTrue(email, one1.isValidEmailAddress());
        assertTrue(email, two.isValidEmailAddress());
        assertFalse(number, one2.isValidEmailAddress());
        assertFalse(testemail, four.isValidEmailAddress());
    }

    @Test
    public void isNumberFormatTest() {
        assertTrue(number, one2.inNumberFormat());
        assertFalse(testnumber, five.inNumberFormat());
        assertFalse(email, one1.inNumberFormat());
    }

    @Test
    public void numberCleanTest() {
        Contact hupu = new Contact("+358-50-456 1234", 4);
        Contact lupu = new Contact("(358)-0400 123 4567", 5);
        Contact tupu = new Contact("+44(343)-123 4567", 6);
        hupu.numberClean();
        lupu.numberClean();
        tupu.numberClean();
        assertEquals("0504561234", hupu.getRow());
        assertEquals("04001234567", lupu.getRow());
        assertEquals("+443431234567", tupu.getRow());
    }

    @Test
    public void canSetAType() {
        one1.setType(Type.email);
        one2.setType(Type.phone);
        two.setType(Type.unknown);
        three.setType(Type.foreign);
        assertEquals(Type.email, one1.getType());
        assertEquals(Type.phone, one2.getType());
        assertEquals(Type.unknown, two.getType());
        assertEquals(Type.foreign, three.getType());
        assertTrue(one1.getType() != null);

    }

}
