package fi.marketing.list.logic;

import java.util.Objects;

/**
 * Contacts are always single string rows of information. Thus, each of the row
 * are cleaned and labeled with Type and State in the process.
 */
public class Contact {

    private String row;
    private int insertid;
    private Type type;
    private State label;
    private int cusno;

    /**
     * Each contact is given its string (row) where the email or phone number is
     * added.
     *
     * @param row is the contact row
     * @param insertid is the id given by the system where the contact data were
     * received
     */
    public Contact(String row, int insertid) {
        this.row = row;
        this.insertid = insertid;
        this.type = null;
        this.label = null;
        this.cusno = 0;
    }

    /**
     * If the row contains a @ sign then we assume the row entails email.
     *
     * @return is true if the contact row entails a @ sign
     */
    public boolean isEmail() {
        if (this.row.contains("@")) {
            return true;
        }
        return false;
    }

    /**
     * If the contact row can be assumed as a valid email address then we assume
     * it is at least written correctly.
     *
     * @return is true if the contact row can be assumed as a valid email
     * address without knowing yet does the given email really work
     */
    public boolean isValidEmailAddress() {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(this.row);
        return m.matches();
    }

    /**
     * If the phone number is given in the right format then we assume it is
     * written correctly.
     *
     * @return is true if the contact row can be assumed as a valid phone number
     * without knowing yet does the number really work
     */
    public boolean inNumberFormat() {
        if (this.row.matches("0[0-9]{5,9}")) {
            return true;
        }
        return false;
    }

    /**
     * A number can be cleaned if it does not comply the standard format.
     */
    public void numberClean() {
        this.row = this.row.replaceAll("[^a-zA-Z0-9+]", "");

        if (this.row.contains("+3580")) {
            this.row = this.row.replace("+3580", "0");
        }
        if (this.row.contains("+358")) {
            this.row = this.row.replace("+358", "0");
        }
        if (this.row.startsWith("3580") == true) {
            this.row = this.row.replace("358", "");
        }
        if (this.row.startsWith("358") == true) {
            this.row = this.row.replace("358", "0");
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

    /**
     * The state can be set by knowing the right customer.
     *
     * @param label is the state given to the customer
     * @param one is the given customer
     */
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

    /**
     * A email hashcode is provided to simplify the search of contact compared
     * to customer.
     *
     * @return is the hash of contact
     */
    public int hashCodeEmail() {
        int hash = 5;
        hash = 43 * Objects.hashCode(this.row);
        return hash;
    }

    /**
     * A phone number hashcode is provided to simplify the search of contact
     * compared to customer.
     *
     * @return is the hash of contact
     */
    public int hashCodeNumber() {
        int hash = 3;
        hash = 71 * Objects.hashCode(this.row);
        return hash;
    }

}
