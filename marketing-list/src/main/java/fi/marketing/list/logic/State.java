package fi.marketing.list.logic;

/**
 * Each contact row is compared to each customer's data. If the equal email or
 * phone number is found, the state labels sameEmail and sameNumber
 * respectively. If the contact row is not matched with customers, the row is
 * labeled as state notFound.
 */
public enum State {
    sameEmail, sameNumber, notFound
}
