package my.marketing.list;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileReader {

    private List<Contact> list;
    private int countRows;

    public FileReader() {
        this.list = new ArrayList<>();
        this.countRows = 0;
    }

    public void read(String file) {
        try (Scanner reader = new Scanner(new File(file), "UTF-8")) {
            while (reader.hasNextLine()) {
                String row = reader.nextLine();
                String[] items = row.split("\\|");
                int id = Integer.valueOf(items[1]);
                list.add(new Contact(items[0], id));
                countRows++;
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List getList() {
        return this.list;
    }

    public int getNumberOfRows() {
        return this.countRows;
    }

}
