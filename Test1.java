public class Test1 {
    private static final String GREETING = "こんにちは。";
    private static final String TITLE = "日本の";
    private static final String WHO = "Yuta Kawabe";
    private static final String DESU = "です。";
    private static final String WHAT = "Javaテストの問１が終わりました。";

    public static void main(String args[]) {
        printLines(makeLines());
    }

    private static String[] makeLines() {
        String[] lines = new String[3];
        lines[0] = GREETING;
        lines[1] = TITLE + WHO + DESU;
        lines[2] = WHAT;
        return lines;
    }

    private static void printLines(String[] lines) {
        for (String line : lines) {
            System.out.println(line);
        }
    }

    // テスト用
    public static String getWho() {
        return WHO;
    }
}
