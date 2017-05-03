package fi.marketing.list.logic;


import fi.marketing.list.logic.lists.MarketingList;
import fi.marketing.list.logic.lists.CustomerList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class FileWriterTest {

    FileWriter one;
    MarketingList list;
    CustomerList customers;
    List<Customer> custolist;

    public FileWriterTest() {
    }

    @Before
    public void setUp() {
        one = new FileWriter();
        customers = new CustomerList();
        customers.addNewCustomer("a4@a4.fi", Type.email, 40);
        customers.addNewCustomer("0507378716", Type.phone, 50);

        try {
            Thread.sleep(10);                 //1000 milliseconds is one second.
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        list = new MarketingList("Testing");
        custolist = customers.getCustomers();
        list.addToCampaign(Type.phone, custolist);
    }

    @Test
    public void existsFileWriter() {
        assertTrue(one != null);
    }

    @Test
    public void writingTest() {
        one.write(list.getName() + ".txt", list);
        assertEquals("Testing", list.getName());
        assertEquals(1, list.getRows());
    }

}
