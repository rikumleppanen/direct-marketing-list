package my.marketing.list;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class FileWriter {

    private int countRows;

    public FileWriter() {
        this.countRows = 0;
    }

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
                System.out.println("File cannot be written");
            }
        }
    }

    public int getRowCount() {
        return this.countRows;
    }
}
