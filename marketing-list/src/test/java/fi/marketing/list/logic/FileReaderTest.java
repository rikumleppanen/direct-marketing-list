package fi.marketing.list.logic;

import fi.marketing.list.logic.FileReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class FileReaderTest {

    FileReader one;
    FileReader two;

    public FileReaderTest() {
    }

    @Before
    public void setUp() {
        one = new FileReader();
        two = new FileReader();
    }

    @Test
    public void existsFileReader() {
        assertTrue(one != null);
        assertEquals(0, one.getNumberOfRows());
    }

    @Test
    public void readingFile() throws FileNotFoundException {
        one.read("koe.txt");
        assertEquals(17, one.getNumberOfRows());
        assertEquals(0, two.getNumberOfRows());
        InputStream inp = new FileInputStream("koe1.txt");
        two.readInputStream(inp);
        assertEquals(18, two.getNumberOfRows());
    }

    @Test
    public void testList() {
        List ab = one.getList();
        assertTrue(ab != null);
        assertEquals(ab.size(), one.getNumberOfRows());
    }

}
