package my.marketing.list;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Customer {

    private String email;
    private String number;
    private List<Consent> consent;
    private int id;

    public Customer() {
        this.email = "";
        this.number = "";
        this.id = 0;
        this.consent = new ArrayList<>();
    }

    public Customer(String email, String number) {
        this.email = email;
        this.number = number;
        this.id = 0;
        this.consent = new ArrayList<>();
    }

    public Customer setID(int id) {
        this.id = id;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
        this.consent.add(new Consent(email, Type.email));
    }

    public Customer setEmailC(String email) {
        this.email = email;
        this.consent.add(new Consent(email, Type.email));
        return this;
    }

    public void setNumber(String number) {
        this.number = number;
        this.consent.add(new Consent(number, Type.phone));
    }

    public Customer setNumberC(String number) {
        this.number = number;
        this.consent.add(new Consent(number, Type.phone));
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
    
    public List<Consent> getConsentList() {
        return this.consent;
    }

    @Override
    public int hashCode() {
        return hashCodeEmail() + hashCodeNumber();
    }

    public int hashCodeEmail() {
        int hash = 5;
        hash = 43 * Objects.hashCode(this.email);
        return hash;
    }

    public int hashCodeNumber() {
        int hash = 3;
        hash = 71 * Objects.hashCode(this.number);
        return hash;
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
        final Customer other = (Customer) obj;
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.number, other.number)) {
            return false;
        }
        return true;
    }
}
