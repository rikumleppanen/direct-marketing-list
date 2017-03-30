package fi.marketing.list.logic.lists;

import fi.marketing.list.logic.Customer;
import fi.marketing.list.logic.Customer;
import fi.marketing.list.logic.Type;
import fi.marketing.list.logic.Type;
import java.sql.Timestamp;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class MarketingListTest {

    MarketingList list;
    CustomerList customers;
    List<Customer> custolist;
    Timestamp testingtime;
    Timestamp testingnext;

    public MarketingListTest() {
    }

    @Before
    public void setUp() {
        customers = new CustomerList();
        customers.addNewCustomer("a4@a4.fi", Type.email, 40);
        customers.addNewCustomer("0507378716", Type.phone, 50);
        customers.addNewCustomer("uc6@uc6.fi", Type.email, 60);
        customers.addNewCustomer("070345678", Type.phone, 70);
        custolist = customers.getCustomers();
        try {
            Thread.sleep(10);                 //1000 milliseconds is one second.
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        list = new MarketingList("TestCampaign");
        testingtime = list.getCreated();
        try {
            Thread.sleep(10);                 //1000 milliseconds is one second.
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        testingnext = new Timestamp(System.currentTimeMillis());
    }

    @Test
    public void existsMarketingList() {
        assertTrue(list != null);
        assertEquals("TestCampaign", list.getName());
        assertEquals(testingtime, list.getCreated());
    }

    @Test
    public void getEarliestAllowedDateTest() {
        assertTrue(list.getEarliestAllowedDate() != null);
        assertTrue(list.getEarliestAllowedDate().before(testingtime));
    }

    @Test
    public void addToCampaignListEmails() {
        assertEquals(4, custolist.size());
        list.addToCampaign(Type.email, custolist);
        assertEquals(2, list.getRows());
        assertEquals("a4@a4.fi", list.getListOfConsents().get(0).getRow());
        assertEquals("uc6@uc6.fi", list.getListOfConsents().get(1).getRow());

    }

    public void addToCampaignListNumbers() {
        list.addToCampaign(Type.phone, custolist);
        assertEquals(2, list.getRows());
        assertEquals("0507378716", list.getListOfConsents().get(0).getRow());
        assertEquals("070345678", list.getListOfConsents().get(1).getRow());
    }

}
