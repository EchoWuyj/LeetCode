package alg_01_ds_wyj._05_application._02_string_matching;

/**
 * @Author Wuyj
 * @DateTime 2023-03-29 18:28
 * @Version 1.0
 */
public class BF1 {
    public int indexOf(String mainStr, String pattern) {
        if (mainStr == null || pattern == null) return -1;
        int m = mainStr.length();
        int p = pattern.length();
        if (m < p) return -1;

        for (int i = 0; i < m; i++) {
            int k = i;
            for (int j = 0; j < p; j++) {
                if (k < m && pattern.charAt(j) == mainStr.charAt(k)) {
                    k++;
                    if (j == p - 1) return i;
                } else {
                    break;
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        BF1 b = new BF1();
        String mainStr = "    your code";
        String patternStr = "your";

        System.out.println(b.indexOf(mainStr, patternStr)); // 4
    }
}
