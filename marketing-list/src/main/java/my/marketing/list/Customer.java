package my.marketing.list;

public class Customer {

    private String email;
    private String number;

    public Customer() {
        this.email = null;
        this.number = null;
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

}
