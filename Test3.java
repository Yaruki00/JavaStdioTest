import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Test3 {
    private static final String GUIDE = "キーボードから正の整数を半角で入力してください。";
    private static final String INPUT = "入力された数値";
    private static final String IS = "は";
    private static final String EVEN = "偶数";
    private static final String ODD = "奇数";
    private static final String DESU = "です。";
    private static final String ERROR_NOT_INTEGER = "不正な入力：整数ではない";
    private static final String ERROR_ZERO_OR_NEGATIVE = "不正な入力：正ではない";
    private static final String ERROR_IO_EXCEPTION = "入力例外：もう一度入力してください";
    private static final String ERROR_INVALID_RETURN = "不正な返り値：もう一度入力してください";

    public static void main(String[] args) {
        int num;
        BufferedReader stdReader = new BufferedReader(new InputStreamReader(
                System.in));
        while (true) {
            System.out.println(GUIDE);
            num = readSingleNaturalFromStdin(stdReader);
            if (num > 0) {
                break;
            } else if (num == 0) {
                System.out.println(ERROR_NOT_INTEGER);
            } else if (num == -1) {
                System.out.println(ERROR_ZERO_OR_NEGATIVE);
            } else if (num == -2) {
                System.out.println(ERROR_IO_EXCEPTION);
            } else {
                System.out.println(ERROR_INVALID_RETURN);
            }
        }
        String judge;
        if (isEven(num)) {
            judge = EVEN;
        } else {
            judge = ODD;
        }
        System.out.println(INPUT + num + IS + judge + DESU);
    }

    protected static int readSingleNaturalFromStdin(BufferedReader stdReader) {
        try {
            int num = Integer.parseInt(stdReader.readLine());
            if (num > 0) {
                return num;
            } else {
                return -1;
            }
        } catch (NumberFormatException e) {
            return 0;
        } catch (IOException e) {
            return -2;
        }
    }

    private static boolean isEven(int num) {
        if (num % 2 == 0) {
            return true;
        } else {
            return false;
        }
    }
}
