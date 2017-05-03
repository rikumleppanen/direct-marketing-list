package fi.marketing.list.logic;

import fi.marketing.list.Main;
import fi.marketing.list.logic.Consent;
import fi.marketing.list.logic.lists.MarketingList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * FileWriter produces a file where all the individual contact rows are added if
 * the consents are valid. It keeps record of the total number of rows added as
 * well.
 */
public class FileWriter {

    private int countRows;

    /**
     * As FileWriter starts it has not written/uploaded a single row yet.
     */
    public FileWriter() {
        this.countRows = 0;
    }

    /**
     * Method writes each consent from marketing list to a given file.
     *
     * @param row is the name of file where the data will be added
     * @param list is the marketing list where data is generated in the system
     */
    public void write(String row, MarketingList list) {
        if (list.getRows() == 0) {
            System.out.println("Marketing List is empty");
        } else {
            try (PrintWriter write = new PrintWriter(new File(row))) {
                for (Consent one : list.getListOfConsents()) {
                    write.println(one.getRow());
                    countRows++;
                }
                write.flush();
                write.close();

            } catch (FileNotFoundException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * The number of written/uploaded rows can be obtained.
     *
     * @return is the number of rows which were uploaded.
     */
    public int getRowCount() {
        return this.countRows;
    }
}
