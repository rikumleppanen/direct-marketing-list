package fi.marketing.list.logic.lists;

import fi.marketing.list.logic.Contact;
import fi.marketing.list.logic.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * ContactList keeps record of the individual rows of contact data so that each
 * key represent one insertation which might entail max of two rows of
 * information (that is email and phone number).
 */
public class ContactList {

    private String name;
    private Map<Integer, List<Contact>> mappi;

    /**
     * As a new contact list is generated it is named and given a new hashmap.
     *
     * @param name is the name of contact list which can be the name of channel
     * where tthe contacts were received.
     */
    public ContactList(String name) {
        this.name = name;
        this.mappi = new HashMap<>();
    }

    /**
     * A new contact row can be added in the contact list. Contact list serves
     * as a place where individual contact rows can be collected under a single
     * key representing a one insertation from the system where the contacts
     * were obtained.
     *
     * @param list is a list of individual contact rows
     */
    public void addContactToList(List<Contact> list) {
        for (Contact item : list) {
            if (!mappi.containsKey(item.getInsertid())) {
                mappi.put(item.getInsertid(), new ArrayList<>());
                mappi.get(item.getInsertid()).add(item);
            } else {
                mappi.get(item.getInsertid()).add(item);
            }
        }
    }

    /**
     * A contact can be labeled as an email or unknown.
     *
     * @param one is the contact row
     */
    public void labelEmail(Contact one) {
        if (one.isValidEmailAddress() == true) {
            one.setType(Type.email);
        } else {
            one.setType(Type.unknown);
        }
    }

    /**
     * A contact can be labeled as a phone number or foreign phone number.
     *
     * @param one is the contact row
     */
    public void labelNumber(Contact one) {
        if (one.inNumberFormat() == true) {
            one.setType(Type.phone);
        } else {
            one.setType(Type.foreign);
        }
    }

    /**
     * A contact can be cleaned and labeled at once.
     *
     * @param one is the contact row
     */
    public void cleanAndLabelNumber(Contact one) {
        if (one.inNumberFormat() == true) {
            one.setType(Type.phone);
        } else {
            one.numberClean();
            labelNumber(one);
        }
    }

    /**
     * A list of contacts can be cleaned and labeled at once.
     *
     * @param list is a list of contacts
     */
    public void cleanAndClassify(List<Contact> list) {
        for (Contact item : list) {
            if (item.isEmail() == true) {
                labelEmail(item);
            } else {
                cleanAndLabelNumber(item);
            }
        }
    }

    /**
     * A contact can be found by the row.
     *
     * @param row is the contact row
     * @return is the individual contact object if the contact is found
     */
    public Contact getContact(String row) {
        for (Integer key : mappi.keySet()) {
            for (Contact one : mappi.get(key)) {
                if (one.getRow().equals(row)) {
                    return one;
                }
            }
        }
        return null;
    }

    /**
     * A keySet of ContactList can be obtained.
     *
     * @return is a set of keys of ContactList
     */
    public Set<Integer> keySet() {
        return mappi.keySet();
    }

    /**
     * A list of contacts from the keySet of ContactList can be obtained.
     *
     * @param key is the key of keySet in ContactList
     * @return is the list of contacts
     */
    public List<Contact> get(Integer key) {
        return mappi.get(key);
    }

    /**
     * A contact from the ContactList can be obtained.
     *
     * @param key is the key of keySet in ContactList
     * @param i is the individual row in the hashmap
     * @return is the given contact
     */
    public Contact getContact(Integer key, Integer i) {
        return mappi.get(key).get(i);
    }

    /**
     * The name of ContactList can be obtained.
     *
     * @return is the name of ContactList
     */
    public String getNameOfContactList() {
        return this.name;
    }

    @Override
    public String toString() {
        return this.name;
    }

    /**
     * The contents of ContactList can be printed.
     */
    public void print() {
        for (Integer key : mappi.keySet()) {
            for (Contact row : mappi.get(key)) {
                System.out.println(key + " " + row.getRow() + " " + row.getType() + " " + row.getState() + " " + row.hashCodeEmail() + " " + row.hashCodeNumber());
            }
        }
    }
}
