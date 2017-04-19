package fi.marketing.list.logic.lists;

import fi.marketing.list.logic.Consent;
import fi.marketing.list.logic.Customer;
import fi.marketing.list.logic.Type;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * MarketingList is important part of the process, since we want to know where
 * and when each customer contact were used. A consent can be used if it is no
 * older than two years.
 */
public class MarketingList {

    private String name;
    private List<Consent> list;
    private Timestamp created;
    private Timestamp bestafter;

    /**
     * A new MarketingList is always named, since we want to track who and when
     * was contacted and whether he or she accepted the offer. The timestamp is
     * generated at the same time.
     *
     * @param name is the name of MarketingList generated.
     */
    public MarketingList(String name) {
        this.name = name;
        this.list = new ArrayList<>();
        this.created = new Timestamp(new Date().getTime());
        this.bestafter = getEarliestAllowedDate();
    }

    /**
     * Every MarketingList has a best after date, since we cannot use the oldest
     * consents.
     *
     * @return gives a timestamp that is the earliest possible to be used in the
     * marketing list.
     */
    public Timestamp getEarliestAllowedDate() {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(created.getTime());
        cal.add(Calendar.YEAR, -2);
        Timestamp earliest = new Timestamp(cal.getTime().getTime());
        return earliest;
    }

    /**
     * Each customer has a list of consents which we want to compare a) which is
     * the most up-to-date consent within all consents and b) is that consent
     * still valid.
     *
     * @param type is the type of consent we are collecting, for instance emails
     * @param customers is the list of customers
     */
    public void addToCampaign(Type type, List<Customer> customers) {
        for (Customer one : customers) {
            Consent chosen;
            chosen = one.getMaxConsent(type);
            if (selectActiveConsents(chosen) == true && chosen != null) {
                this.list.add(chosen);
                chosen.rememberMarketingList(this.name, this.created);
            }
        }
    }

    /**
     * The timestamp of each consent is compared so as to obtain only the valid
     * consents.
     *
     * @param one is the consent we want to investigate
     * @return is true if the consent is no older than best after and no younger
     * than the time of marketing list was created
     */
    public boolean selectActiveConsents(Consent one) {
        if (one != null && one.getTimestamp().before(created) && one.getTimestamp().after(bestafter)) {
            return true;
        }
        return false;
    }

    /**
     * The list of Consents can be obtained from the marketing list.
     *
     * @return is the list of consents collected to the marketing list
     */
    public List<Consent> getListOfConsents() {
        return this.list;
    }

    /**
     * The number of rows in the marketing list can be obtained.
     *
     * @return is the number of rows in the marketing list
     */
    public int getRows() {
        return this.list.size();
    }

    /**
     * The name of the marketing list can be obtained.
     *
     * @return is the name of marketing list
     */
    public String getName() {
        return this.name;
    }

    /**
     * The time when the marketing list was generated can be obtained.
     *
     * @return is the timestamp when the marketing list was created.
     */
    public Timestamp getCreated() {
        return this.created;
    }

    /**
     * The contents of marketing list can be printed.
     */
    public void print() {
        for (Consent one : list) {
            System.out.println(one.getRow());
        }
    }

}
