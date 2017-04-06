package fi.marketing.list.logic.lists;

import fi.marketing.list.logic.Contact;
import fi.marketing.list.logic.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ContactList {

    private String name;
    private Map<Integer, List<Contact>> mappi;

    public ContactList(String name) {
        this.name = name;
        this.mappi = new HashMap<>();
    }

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

    public void labelEmail(Contact one) {
        if (one.isValidEmailAddress() == true) {
            one.setType(Type.email);
        } else {
            one.setType(Type.unknown);
        }
    }

    public void labelNumber(Contact one) {
        if (one.inNumberFormat() == true) {
            one.setType(Type.phone);
        } else {
            one.setType(Type.foreign);
        }
    }

    public void cleanAndLabelNumber(Contact one) {
        if (one.inNumberFormat() == true) {
            one.setType(Type.phone);
        } else {
            one.numberClean();
            labelNumber(one);
        }
    }

    public void cleanAndClassify(List<Contact> list) {
        for (Contact item : list) {
            if (item.isEmail() == true) {
                labelEmail(item);
            } else {
                cleanAndLabelNumber(item);
            }
        }
    }

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

    public Set<Integer> keySet() {
        return mappi.keySet();
    }

    public List<Contact> get(Integer key) {
        return mappi.get(key);
    }

    public Contact getContact(Integer key, Integer i) {
        return mappi.get(key).get(i);
    }

    public String getNameOfContactList() {
        return this.name;
    }

    @Override
    public String toString() {
        return this.name;
    }

    public void print() {
        for (Integer key : mappi.keySet()) {
            for (Contact row : mappi.get(key)) {
                System.out.println(key + " " + row.getRow() + " " + row.getType() + " " + row.getState() + " " + row.hashCodeEmail() + " " + row.hashCodeNumber());
            }
        }
    }
}
