import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestTest1 {
    private ByteArrayOutputStream testOut;
    private PrintStream stdout;

    @Before
    public void setUp() throws Exception {
        testOut = new ByteArrayOutputStream();
        stdout = System.out;
        System.setOut(new PrintStream(testOut));
    }

    @After
    public void tearDown() {
        System.setOut(stdout);
    }

    @Test
    public void testMain() {
        Test1.main(null);
        System.out.flush();
        String lines[] = { "こんにちは。",
                           "日本の" + Test1.getWho() + "です。",
                           "Javaテストの問１が終わりました。" };
        String expected = joinStrings(lines);
        String actual = testOut.toString();
        assertEquals(expected, actual);
    }

    private String joinStrings(String[] strs) {
        String newLine = System.getProperty("line.separator");
        String result = "";
        for (String s : strs) {
            result += s + newLine;
        }
        return result;
    }

}
