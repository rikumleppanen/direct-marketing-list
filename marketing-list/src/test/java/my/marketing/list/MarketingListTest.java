package my.marketing.list;

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
    public void addToMarketingList() {
        list.addToCampaign(Type.email, custolist);
        assertEquals("a4@a4.fi", list.getListOfConsents().get(0).getRow());
        //assertEquals("uc6@uc6.fi", list.getListOfConsents().get(1).getRow());
    }

}
