package my.marketing.list;

import java.sql.Timestamp;

public class Consent {

    private String row;
    private Type type;
    private Timestamp timestamp;

    public Consent(String row, Type type) {
        this.row = row;
        this.type = type;
        this.timestamp = new Timestamp(System.currentTimeMillis());
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

    public void setType(Type type) {
        this.type = type;
    }

    public void setNewTimestamp() {
        this.timestamp = new Timestamp(System.currentTimeMillis());
    }

}
