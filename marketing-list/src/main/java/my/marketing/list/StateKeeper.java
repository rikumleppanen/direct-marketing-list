package my.marketing.list;

public class StateKeeper {

    private Customer customer;
    private Contact[] rows;
    private boolean identical;
    private boolean sameEmail;
    private boolean sameNumber;
    private boolean notFound;

    public StateKeeper() {
        boolean identical = false;
        boolean sameEmail = false;
        boolean sameNumber = false;
        boolean notFound = false;
    }

    public void setN(int n) {
        this.rows = new Contact[n];
    }

    public void set(Customer one, Contact row, int i) {
        this.rows[i] = row;
        this.customer = one;
    }

    public Customer getCustomer() {
        return this.customer;
    }

    public Contact getContactRow(int i) {
        return this.rows[i];
    }
    
    public int size() {
        return this.rows.length;
    }

    public void setTrueIdentical() {
        identical = true;
    }

    public void setTrueSameEmail() {
        sameEmail = true;
    }

    public void setTrueSameNumber() {
        sameNumber = true;
    }

    public void setNotFound() {
        notFound = true;
    }

    public boolean isIdentical() {
        return identical;
    }

    public boolean isSameEmail() {
        return sameEmail;
    }

    public boolean isSameNumber() {
        return sameNumber;
    }

    public boolean isNotFound() {
        return notFound;
    }

}
