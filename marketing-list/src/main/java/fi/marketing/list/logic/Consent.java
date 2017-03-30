package fi.marketing.list.logic;

import fi.marketing.list.logic.Type;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

public class Consent {

    private String row;
    private Type type;
    private Timestamp timestamp;
    private Map<String, Timestamp> used;

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

    public void rememberMarketingList(String name, Timestamp date) {
        used.put(name, date);
    }
    
    public void setType(Type type) {
        this.type = type;
    }

    public void setNewTimestamp() {
        this.timestamp = new Timestamp(System.currentTimeMillis());
    }

}
