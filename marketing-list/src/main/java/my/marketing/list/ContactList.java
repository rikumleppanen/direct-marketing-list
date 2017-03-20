package my.marketing.list;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
                    item.emailClean();
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
}
