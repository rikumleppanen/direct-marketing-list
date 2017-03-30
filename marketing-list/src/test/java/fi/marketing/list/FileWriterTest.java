package fi.marketing.list;

import fi.marketing.list.logic.Consent;
import fi.marketing.list.logic.lists.MarketingList;
import fi.marketing.list.logic.Type;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class FileWriterTest {

    FileWriter one;
    MarketingList list;
    List<Consent> consents;

    public FileWriterTest() {
    }

    @Before
    public void setUp() {
        one = new FileWriter();
        consents = new ArrayList<>();
        consents.add(new Consent("0141234567", Type.phone));
        consents.add(new Consent("email@test.com", Type.email));
        try {
            Thread.sleep(10);                 //1000 milliseconds is one second.
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        list = new MarketingList("Testing");
        //list.addToCampaign2(Type.phone, consents);
    }

//    @Test
//    public void existsFileWriter() {
//        assertTrue(one != null);
//    }
//
//    @Test
//    public void writingTest() {
//        one.write(list.getName() + ".txt", list);
//        assertEquals(1, list.getRows());
//    }

}
