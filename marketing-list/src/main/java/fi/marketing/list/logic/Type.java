package fi.marketing.list.logic;

/**
 * Each contact row is in a string format. Thus, rows are validated and labeled
 * first by type so as to know if the row is assumed to be a email, phone number
 * or unknown. If phone number begins with a foreign prefix such as +44, then it
 * is labeled as a foreign.
 */
public enum Type {
    email, phone, foreign, unknown
}
