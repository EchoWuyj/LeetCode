package alg_01_ds_wyj._05_application._02_string_matching;

/**
 * @Author Wuyj
 * @DateTime 2023-03-29 18:38
 * @Version 1.0
 */
public class BF2 {
    public int indexOf(String mainStr, String pattern) {
        if (mainStr == null || pattern == null) return -1;
        int m = mainStr.length();
        int n = pattern.length();
        if (m < n) return -1;

        char first = pattern.charAt(0);
        for (int i = 0; i < m; i++) {
            if (mainStr.charAt(i) != first) {
                while (++i < m && mainStr.charAt(i) != first) ;
            }

            if (i < m) {
                int k = i + 1;
                int j = 1;
                if (j == n) return i;
                for (; j < n && k < m; j++, k++) {
                    if (mainStr.charAt(k) == pattern.charAt(j)) {
                        if (j == n - 1) return i;
                    } else {
                        break;
                    }
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        BF2 b = new BF2();
        String mainStr = "a";
        String patternStr = "a";

        System.out.println(b.indexOf(mainStr, patternStr)); // 0

//        String mainStr = "    your code";
//        String patternStr = "your";
//        System.out.println(b.indexOf(mainStr, patternStr)); // 4
    }
}
