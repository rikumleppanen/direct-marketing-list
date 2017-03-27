package my.marketing.list;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

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

    public void addToCampaign(Type type, List<Consent> active) {
        for (Consent one : active) {
            if (one.getType() == type) {
                if (selectActiveConsents(one) == true) {
                    list.add(one);
                    one.rememberMarketingList(this.name, this.created);
                }
            }
        }
    }

    public boolean selectActiveConsents(Consent one) {
        if (one.getTimestamp().before(created) && one.getTimestamp().after(bestafter)) {
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

    public void print() {
        for (Consent one : list) {
            System.out.println(one.getRow());
        }
    }

}
