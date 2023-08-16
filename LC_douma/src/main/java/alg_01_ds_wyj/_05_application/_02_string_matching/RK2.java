package alg_01_ds_wyj._05_application._02_string_matching;

/**
 * @Author Wuyj
 * @DateTime 2023-03-29 19:20
 * @Version 1.0
 */
public class RK2 {
    public int indexOf(String mainStr, String pattern) {
        if (mainStr == null || pattern == null) return -1;

        int m = mainStr.length();
        int n = pattern.length();
        if (m < n) return -1;

        int[] hashCodes = new int[m - n + 1];
        hashCodes[0] = calFirstSubHashCode(mainStr.substring(0, n));

        for (int i = 1; i < m - n + 1; i++) {
            hashCodes[i] = calHashCode(mainStr, i, hashCodes, n);
        }

        int hashCode = calFirstSubHashCode(pattern);

        for (int i = 0; i < m - n + 1; i++) {
            if (hashCode == hashCodes[i]) {
                int k = i;
                for (int j = 0; j < n && k < m; j++, k++) {
                    if (mainStr.charAt(k) != pattern.charAt(j)) {
                        break;
                    }
                    if (j == n - 1) return i;
                }
            }
        }
        return -1;
    }

    private int calHashCode(String mainStr, int i, int[] hashCodes, int n) {
        return hashCodes[i - 1] - (mainStr.charAt(i - 1) - 'a')
                + (mainStr.charAt(i - 1 + n) - 'a');
    }

    private int calFirstSubHashCode(String str) {
        int n = str.length();
        int hashCode = 0;
        for (int i = 0; i < n; i++) {
            hashCode += (str.charAt(i) - 'a');
        }
        return hashCode;
    }

    public static void main(String[] args) {
        test1(); // -1
        test2(); // 4
    }

    private static void test2() {
        RK2 b = new RK2();
        String mainStr = "    your code";
        String patternStr = "your";

        System.out.println(b.indexOf(mainStr, patternStr)); // 4
    }

    private static void test1() {
        RK2 b = new RK2();
        String mainStr = "    oury code";
        String patternStr = "your";

        System.out.println(b.indexOf(mainStr, patternStr)); // -1
    }
}
