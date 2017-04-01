package fi.marketing.list.logic;

import fi.marketing.list.logic.Type;
import fi.marketing.list.logic.State;
import java.util.Objects;

public class Contact {

    private String row;
    private int insertid;
    private Type type;
    private State label;
    private int cusno;

    public Contact(String row, int insertid) {
        this.row = row;
        this.insertid = insertid;
        this.type = null;
        this.label = null;
        this.cusno = 0;
    }

    public boolean isEmail() {
        if (this.row.contains("@")) {
            return true;
        }
        return false;
    }

    public boolean isValidEmailAddress() {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(this.row);
        return m.matches();
    }

    public boolean inNumberFormat() {
        if (this.row.matches("0[0-9]{5,9}")) {
            return true;
        }
        return false;
    }

    public void numberClean() {
        if (this.row.contains(" ")) {
            setRow(this.row.replace(" ", ""));
        }
        if (this.row.contains("-")) {
            setRow(this.row.replace("-", ""));
        }
        if (this.row.contains("(")) {
            setRow(this.row.replace("(", ""));
        }
        if (this.row.contains(")")) {
            setRow(this.row.replace(")", ""));
        }
        if (this.row.contains("+3580")) {
            setRow(this.row.replace("+3580", "0"));
        }
        if (this.row.contains("+358")) {
            setRow(this.row.replace("+358", "0"));
        }
        if (this.row.startsWith("3580") == true) {
            setRow(this.row.replace("358", ""));
        }
        if (this.row.startsWith("358") == true) {
            setRow(this.row.replace("358", "0"));
        }

    }

    public String getRow() {
        return row;
    }

    public void setRow(String one) {
        this.row = one;
    }

    public int getInsertid() {
        return insertid;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public void setState(State label) {
        this.label = label;
    }

    public void setStateAndTagCustomer(State label, Customer one) {
        this.label = label;
        this.cusno = one.getInsertId();
    }

    public State getState() {
        return this.label;
    }

    @Override
    public int hashCode() {
        if (this.type == Type.email) {
            return hashCodeEmail();
        }
        if (this.type == Type.phone || this.type == Type.foreign) {
            return hashCodeNumber();
        }
        return 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Contact other = (Contact) obj;
        if (!Objects.equals(this.row, other.row)) {
            return false;
        }

        return true;
    }

    public int hashCodeEmail() {
        int hash = 5;
        hash = 43 * Objects.hashCode(this.row);
        return hash;
    }

    public int hashCodeNumber() {
        int hash = 3;
        hash = 71 * Objects.hashCode(this.row);
        return hash;
    }

}