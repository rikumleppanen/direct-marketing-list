package fi.marketing.list.logic;

import fi.marketing.list.logic.Type;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * Customers consists of an individual row or a set of rows of contact
 * information. Customers have a list of consents with timestamps so we can use
 * their contact data in marketing campaigns. There might be situations when an
 * individual customer cannot be selected to marketing campaigns. For example,
 * he or she is currently a paying customer so that it is not a reasonable to
 * market the same product for existing customer.
 */
public class Customer {

    private String email;
    private String number;
    private List<Consent> consent;
    private UUID cusno;
    private int insertId;

    /**
     * Customer can be generated without any specific information.
     */
    public Customer() {
        this.email = "";
        this.number = "";
        this.cusno = UUID.randomUUID();
        this.insertId = 0;
        this.consent = new ArrayList<>();
    }

    /**
     * Customer can be generated with email and number information.
     *
     * @param email Email is provided
     * @param number Phone number is provided
     */
    public Customer(String email, String number) {
        this.email = email;
        this.number = number;
        this.cusno = UUID.randomUUID();
        this.insertId = 0;
        this.consent = new ArrayList<>();
    }

    public Customer setID(int id) {
        this.insertId = id;
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

    public UUID getCusnoId() {
        return cusno;
    }

    public int getInsertId() {
        return insertId;
    }

    public List<Consent> getConsentList() {
        return this.consent;
    }

    public List<Consent> getConsentList(Type type) {
        List<Consent> list = new ArrayList<>();
        for (Consent con : consent) {
            if (con.getType() == type) {
                list.add(con);
            }
        }
        return list;
    }

    /**
     * As every customer has a list of consents with timestamps, we want to find
     * the latest of consents by Type.
     *
     * @param type Type selects whether we are interested in emails or phone
     * numbers
     * @return chosen Gives a Consent that is the latest of the same type
     */
    public Consent getMaxConsent(Type type) {
        List<Consent> list = getConsentList(type);
        if (!list.isEmpty()) {
            Consent chosen = list.get(0);
            Timestamp max = chosen.getTimestamp();
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getTimestamp().after(max)) {
                    max = list.get(i).getTimestamp();
                    chosen = list.get(i);
                }
            }
            return chosen;
        }
        return null;
    }

    public void updateConsentList(String row, Type type) {
        consent.add(new Consent(row, type));
    }

    /**
     * HashCode consists of hashCode of email and hashCode of phone number each
     * separately.
     */
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
