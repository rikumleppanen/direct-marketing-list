package fi.marketing.list;

import fi.marketing.list.logic.Contact;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * FileReader produces a list where all the individual contact rows are added.
 * It keeps record of the total number of rows as well.
 */
public class FileReader {

    private List<Contact> list;
    private int countRows;

    /**
     * The FileReader as not yet read any rows, since the count is zero at the
     * beginning.
     */
    public FileReader() {
        this.list = new ArrayList<>();
        this.countRows = 0;
    }

    /**
     * Method reads each row of a file and adds contacts.
     *
     * @param file is the file name that will be read
     */
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

    /**
     * Method reads each row of a file and adds contacts from the InputStream.
     *
     * @param is is the InputStream
     */
    public void readInputStream(InputStream is) {
        try (Scanner reader = new Scanner(is, "UTF-8")) {
            while (reader.hasNextLine()) {
                String row = reader.nextLine();
                String[] items = row.split("\\|");
                int id = Integer.valueOf(items[1]);
                list.add(new Contact(items[0], id));
                countRows++;
            }
        } catch (Exception e) {
        }
    }

    /**
     * A list can be extracted from the FileReader.
     *
     * @return is a list of contacts
     */
    public List getList() {
        return this.list;
    }

    /**
     * The number of downloaded rows can be obtained.
     *
     * @return is the number of rows which were downloaded to the system
     */
    public int getNumberOfRows() {
        return this.countRows;
    }

}
