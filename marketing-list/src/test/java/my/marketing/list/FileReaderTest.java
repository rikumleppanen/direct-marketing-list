package my.marketing.list;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
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
    public void readingFile() {
        one.read("koe.txt");
        assertEquals(15, one.getNumberOfRows());
        assertEquals(0, two.getNumberOfRows());
    }

    @Test
    public void testList() {
        List ab = one.getList();
        assertTrue(ab != null);
        assertEquals(ab.size(), one.getNumberOfRows());
    }

}
