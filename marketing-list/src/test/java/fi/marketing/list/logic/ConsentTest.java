package fi.marketing.list.logic;

import fi.marketing.list.logic.lists.MarketingList;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ConsentTest {

    Consent alpha;
    Consent beta;
    MarketingList newey;

    public ConsentTest() {
    }

    @Before
    public void setUp() {
        alpha = new Consent("091234", Type.phone);
        try {
            Thread.sleep(1000);                 //1000 milliseconds is one second.
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        beta = new Consent("arto@arto.fi", Type.email);
        newey = new MarketingList("EarlyBirdCampaign");
    }

    @Test
    public void existsConsent() {
        assertTrue(alpha != null);
        assertEquals("091234", alpha.getRow());
        assertEquals("arto@arto.fi", beta.getRow());
        assertEquals(Type.phone, alpha.getType());
        assertEquals(Type.email, beta.getType());
    }

    @Test
    public void setTest() {
        alpha.setRow("0123456");
        assertEquals("0123456", alpha.getRow());
        alpha.setType(Type.foreign);
        assertEquals(Type.foreign, alpha.getType());
    }

    @Test
    public void timestampTest() {
        assertTrue(alpha.getTimestamp() != null);
        assertEquals(true, alpha.getTimestamp().before(beta.getTimestamp()));
        alpha.setNewTimestamp();
        assertEquals(false, alpha.getTimestamp().before(beta.getTimestamp()));
    }

    @Test
    public void marketingListUpdateTest() {
        assertTrue(newey != null);
        assertEquals("EarlyBirdCampaign", newey.getName());
        alpha.rememberMarketingList(newey.getName(), newey.getCreated());
        assertEquals("EarlyBirdCampaign", alpha.getNameOfMarketingList("EarlyBirdCampaign"));
    }

}
