package fi.marketing.list.logic;

import java.util.UUID;

/**
 * StateKeeper Class keeps record of the individual rows of contact data,
 * because one insertation might entail several rows of information. At this
 * point, there are max of two rows in one insertation (email and phone number)
 * labeled in the process. Some have only one row, i.e. email or phone number.
 */
public class StateKeeper {

    private Customer customer;
    private Contact[] rows;
    private State[] labels;

    /**
     * The length of table is needed which correlates with number of rows in
     * contact insertation. That is, if both email and phone number is given,
     * then the length is 2. If more information than this is given, for
     * instance home address, this table is extended respectively
     *
     * @param n is the length of tables (Contact[] and State[])
     */
    public void setN(int n) {
        this.rows = new Contact[n];
        this.labels = new State[n];
    }

    /**
     * If the customer is known the can set the information at once.
     *
     * @param one is the customer object
     * @param row is the contact row i.e. email or phone number
     * @param i is the table place number
     * @param identifier is the State of a contact row i.e. does a contact have
     * the same email and/or phone number or not
     */
    public void set(Customer one, Contact row, int i, State identifier) {
        this.customer = one;
        this.rows[i] = row;
        this.labels[i] = identifier;
    }

    /**
     * If the customer is unknown we can set other pieces of information at
     * once.
     *
     * @param row is the contact row i.e. email or phone number
     * @param i is the place number of the table
     * @param identifier is the State of a contact row i.e. does a contact have
     * the same email and/or phone number or not
     */
    public void set(Contact row, int i, State identifier) {
        this.rows[i] = row;
        this.labels[i] = identifier;
    }

    /**
     * We can utilize the system's generated customer number to pinpoint the
     * customer whose information is updated.
     *
     * @return the system's generated customer number
     */
    public UUID getCustomer() {
        if (this.customer != null) {
            return this.customer.getCusnoId();
        }
        return null;
    }

    /**
     * We can get the contact row by stating the place in table.
     *
     * @param i is the place number in the table
     * @return the given Contact object
     */
    public Contact getContactRow(int i) {
        return this.rows[i];
    }

    /**
     * The length of table can be obtained.
     *
     * @return the the length of table
     */
    public int size() {
        return this.rows.length;
    }

    /**
     * The state of each row can be set.
     *
     * @param i is the place number in the table
     * @param identifier is the state given to the place in table
     */
    public void setState(int i, State identifier) {
        this.labels[i] = identifier;
    }

    /**
     * We can get the given state according to the given place in table.
     * @param i is the place number in the table
     * @return the given label in the given place in table
     */
    public State getState(int i) {
        return this.labels[i];
    }

    /**
     * If the state is not found within any of the items in table then this will
     * be true.
     *
     * @return if not a sigle item is found
     */
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

    /**
     * If the state is not found within some of the items in table then this
     * will be true.
     *
     * @return if not found at least once
     */
    public boolean isNotFound() {
        for (int i = 0; i < this.labels.length; i++) {
            if (this.labels[i] == State.notFound) {
                return true;
            }
        }
        return false;
    }

    /**
     * If the contact info in several rows is identical to the customer info
     * then this will be true.
     *
     * @return is true if the rows are identical to the customers' info
     */
    public boolean isIdenticalRow() {
        for (int i = 0; i < this.labels.length; i++) {
            if (this.labels[i] == State.sameEmail || this.labels[i] == State.sameNumber) {
                return true;
            }
        }
        return false;
    }
}
