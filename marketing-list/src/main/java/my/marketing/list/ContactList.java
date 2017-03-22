package my.marketing.list;

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

    public void cleanAndClassify(List<Contact> list) {
        for (Contact item : list) {
            if (item.isEmail() == true) {
                if (item.isValidEmailAddress() == true) {
                    item.setType(Type.email);
                } else {
                    item.setType(Type.unknown);
                }
            } else {
                if (item.inNumberFormat() == true) {
                    item.setType(Type.phone);
                } else {
                    item.numberClean();
                    if (item.inNumberFormat() == true) {
                        item.setType(Type.phone);
                    } else {
                        item.setType(Type.foreign);
                    }
                }
            }
        }
    }

    public boolean isContactOnTheList(Contact contact) {
        for (Integer key : mappi.keySet()) {
            for (Contact one : mappi.get(key)) {
                if (one.getRow().equals(contact.getRow())) {
                    return true;
                }
            }
        }
        return false;
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

    @Override
    public String toString() {
        return this.name;
    }

    public void print() {
        for (Integer key : mappi.keySet()) {
            for (Contact row : mappi.get(key)) {
                System.out.println(row.hashCode());
            }
        }
    }
}
