package my.marketing.list;

public class Customer {

    private String email;
    private String number;
    private int id;

    public Customer() {
        this.email = null;
        this.number = null;
        this.id = 0;
    }
    public Customer setID(int id) {
        this.id = id;
        return this;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public Customer setEmailC(String email) {
        this.email = email;
        return this;
    }
    public void setNumber(String number) {
        this.number = number;
    }

    public Customer setNumberC(String number) {
        this.number = number;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public String getNumber() {
        return number;
    }
    public int getID() {
        return id;
    }

}
