import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.StringReader;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestTest3 {
    private InputStream stdin;
    private PrintStream stdout;
    private StandardInputSnatcher testIn;
    private StandardOutputSnatcher testOut;

    @Before
    public void setUp() throws Exception {
        stdin = System.in;
        stdout = System.out;
        testIn = new StandardInputSnatcher();
        testOut = new StandardOutputSnatcher();
        System.setIn(testIn);
        System.setOut(testOut);
    }

    @After
    public void tearDown() {
        System.setIn(stdin);
        System.setOut(stdout);
    }

    @Test
    public void testReadSingleNaturalFromStdin() {
        BufferedReader stdReader = new BufferedReader(new InputStreamReader(
                System.in));
        // valid input: 1 -> 1
        testIn.inputln("1");
        assertEquals(1, Test3.readSingleNaturalFromStdin(stdReader));
        // zero_or_negative input: 0 -> -1
        testIn.inputln("0");
        assertEquals(-1, Test3.readSingleNaturalFromStdin(stdReader));
        // zero_or_negative input: -1 -> -1
        testIn.inputln("-1");
        assertEquals(-1, Test3.readSingleNaturalFromStdin(stdReader));
        // not_integer input: aaa -> 0
        testIn.inputln("aaa");
        assertEquals(0, Test3.readSingleNaturalFromStdin(stdReader));
        // not_integer input: 5.5 -> 0
        testIn.inputln("5.5");
        assertEquals(0, Test3.readSingleNaturalFromStdin(stdReader));
    }

    @Test
    public void testMain() {
        // 1.非整数, 2.非正, 3.奇数
        testIn.inputln("5.0f");
        testIn.inputln("-999");
        testIn.inputln("777");
        Test3.main(null);
        assertEquals(testOut.readLine(), "キーボードから正の整数を半角で入力してください。");
        assertEquals(testOut.readLine(), "不正な入力：整数ではない");
        assertEquals(testOut.readLine(), "キーボードから正の整数を半角で入力してください。");
        assertEquals(testOut.readLine(), "不正な入力：正ではない");
        assertEquals(testOut.readLine(), "キーボードから正の整数を半角で入力してください。");
        assertEquals(testOut.readLine(), "入力された数値777は奇数です。");
        // 1.非正, 2.非整数, 3.偶数
        testIn.inputln("0");
        testIn.inputln("hogefuga");
        testIn.inputln("888");
        Test3.main(null);
        assertEquals(testOut.readLine(), "キーボードから正の整数を半角で入力してください。");
        assertEquals(testOut.readLine(), "不正な入力：正ではない");
        assertEquals(testOut.readLine(), "キーボードから正の整数を半角で入力してください。");
        assertEquals(testOut.readLine(), "不正な入力：整数ではない");
        assertEquals(testOut.readLine(), "キーボードから正の整数を半角で入力してください。");
        assertEquals(testOut.readLine(), "入力された数値888は偶数です。");
    }

    public class StandardInputSnatcher extends InputStream {

        private StringBuilder buffer = new StringBuilder();
        private String crlf = System.getProperty("line.separator");

        public void inputln(String str) {
            buffer.append(str).append(crlf);
        }

        @Override
        public int read() throws IOException {
            if (buffer.length() == 0) {
                return -1;
            }
            int result = buffer.charAt(0);
            buffer.deleteCharAt(0);
            return result;
        }
    }

    public class StandardOutputSnatcher extends PrintStream {
        private BufferedReader buffer = new BufferedReader(new StringReader(""));

        public StandardOutputSnatcher() {
            super(new ByteArrayOutputStream());
        }

        public String readLine() {
            try {
                String line = "";
                if ((line = buffer.readLine()) != null) {
                    return line;
                } else {
                    buffer = new BufferedReader(
                            new StringReader(out.toString()));
                    ((ByteArrayOutputStream) out).reset();
                    return buffer.readLine();
                }
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

}
