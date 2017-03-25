package my.marketing.list;

import java.io.File;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class FileReaderTest {

    FileReader one;

    public FileReaderTest() {
    }

    @Before
    public void setUp() {
        one = new FileReader();
    }

    @Test
    public void existsFileReader() {
        assertTrue(one != null);
    }

    @Test
    public void readingFile() {
        one.read("koe.txt");
        assertEquals(15, one.getNumberOfRows());
    }

}
