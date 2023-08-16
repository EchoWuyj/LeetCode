package alg_01_ds_wyj._05_application._02_string_matching;

/**
 * @Author Wuyj
 * @DateTime 2023-03-29 19:09
 * @Version 1.0
 */
public class RK1 {
    public int indexOf(String mainStr, String pattern) {
        if (mainStr == null || pattern == null) return -1;
        int m = mainStr.length();
        int n = pattern.length();
        if (m < n) return -1;

        int[] hashCodes = new int[m - n + 1];
        for (int i = 0; i < m - n + 1; i++) {
            hashCodes[i] = calHashCode(mainStr.substring(i, i + n));
        }

        int hashCode = calHashCode(pattern);

        for (int i = 0; i < m - n + 1; i++) {
            if (hashCode == hashCodes[i]) {
                return i;
            }
        }
        return -1;
    }

    private int calHashCode(String str) {
        return str.hashCode();
    }

    public static void main(String[] args) {
        RK1 b = new RK1();
        String mainStr = "    your code";
        String patternStr = "your";

        System.out.println(b.indexOf(mainStr, patternStr)); // 4
    }
}
