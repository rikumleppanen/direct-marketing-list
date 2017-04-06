package fi.marketing.list.logic;

import java.util.UUID;

/**
 * StateKeeper Class keeps record of the individual rows of Contact, because one
 * insertation might entail several rows of information. At this point, there
 * are max of two rows in one insertation (email and phone number) labeled in
 * the process.
 */
public class StateKeeper {

    private Customer customer;
    private Contact[] rows;
    private State[] labels;

    public StateKeeper() {
    }

    public void setN(int n) {
        this.rows = new Contact[n];
        this.labels = new State[n];
    }

    public void set(Customer one, Contact row, int i, State identifier) {
        this.customer = one;
        this.rows[i] = row;
        this.labels[i] = identifier;
    }

    public void set(Contact row, int i, State identifier) {
        this.rows[i] = row;
        this.labels[i] = identifier;
    }

    public UUID getCustomer() {
        if (this.customer != null) {
            return this.customer.getCusnoId();
        }
        return null;
    }

    public Contact getContactRow(int i) {
        return this.rows[i];
    }

    public int size() {
        return this.rows.length;
    }

    public void setState(int i, State identifier) {
        this.labels[i] = identifier;
    }

    public State getState(int i) {
        return this.labels[i];
    }

    public boolean isNotAtAllFound() {
        int k = 0;
        for (int i = 0; i < this.labels.length; i++) {
            if (this.labels[i] == State.notFound) {
                k++;
            }
        }
        if (k == this.labels.length) {
            return true;
        }
        return false;
    }

    public boolean isNotFound() {
        for (int i = 0; i < this.labels.length; i++) {
            if (this.labels[i] == State.notFound) {
                return true;
            }
        }
        return false;
    }

    public boolean isIdenticalRow() {
        for (int i = 0; i < this.labels.length; i++) {
            if (this.labels[i] == State.sameEmail || this.labels[i] == State.sameNumber) {
                return true;
            }
        }
        return false;
    }
}
