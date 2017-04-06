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
 * MarketingList Class is important part of the process, since we want to know
 * where and when each customer contact were used. customer lists and we want to
 * use only the contacts that are collected within two years from the date
 * consent was given.
 */
public class MarketingList {

    private String name;
    private List<Consent> list;
    private Timestamp created;
    private Timestamp bestafter;

    public MarketingList(String name) {
        this.name = name;
        this.list = new ArrayList<>();
        this.created = new Timestamp(new Date().getTime());
        this.bestafter = getEarliestAllowedDate();
    }

    public Timestamp getEarliestAllowedDate() {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(created.getTime());
        cal.add(Calendar.YEAR, -2);
        Timestamp earliest = new Timestamp(cal.getTime().getTime());
        return earliest;
    }

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

    public boolean selectActiveConsents(Consent one) {
        if (one != null && one.getTimestamp().before(created) && one.getTimestamp().after(bestafter)) {
            return true;
        }
        return false;
    }

    public List<Consent> getListOfConsents() {
        return this.list;
    }

    public int getRows() {
        return this.list.size();
    }

    public String getName() {
        return this.name;
    }

    public Timestamp getCreated() {
        return this.created;
    }

    public void print() {
        for (Consent one : list) {
            System.out.println(one.getRow());
        }
    }

}
