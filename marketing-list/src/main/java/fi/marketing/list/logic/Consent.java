package fi.marketing.list.logic;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Consents have each their own lifespan of two years from the latest consent.
 * One customer might have several consents and each of these have different
 * timestamps. It is important to know where (=what campaign) and when (=when
 * campaign was generated) the consents were used.
 */
public class Consent {

    private String row;
    private Type type;
    private Timestamp timestamp;
    private Map<String, Timestamp> used;

    /**
     * A new consent consists of a string (row) and type. A timestamp is given
     * when consent is generated.
     *
     * @param row is the contact row, i.e. email or phone number
     * @param type is the type of contact row
     */
    public Consent(String row, Type type) {
        this.row = row;
        this.type = type;
        this.timestamp = new Timestamp(System.currentTimeMillis());
        this.used = new HashMap<>();
    }

    public String getRow() {
        return row;
    }

    public Type getType() {
        return type;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setRow(String row) {
        this.row = row;
    }

    /**
     * As the use of consents will be tracked the name of marketing list and its
     * timestamp is recorded to the consent data.
     *
     * @param name is the name of the given marketing list where the consent was
     * used
     * @param date is the date of the given marketing list where the consent was
     * used
     */
    public void rememberMarketingList(String name, Timestamp date) {
        used.put(name, date);
    }

    /**
     * Get the Name of Marketing List.
     *
     * @param name is the name of Campaign which we are searching
     * @return is the name of Marketing List.
     */
    public String getNameOfMarketingList(String name) {
        if (this.used.containsKey(name)) {
            return name;
        }
        return "";
    }

    public void setType(Type type) {
        this.type = type;
    }

    /**
     * A new Timestamp can be set for instance if timestamp is updated.
     */
    public void setNewTimestamp() {
        this.timestamp = new Timestamp(System.currentTimeMillis());
    }

}
