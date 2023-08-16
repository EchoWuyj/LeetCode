package alg_01_ds_wyj._05_application._02_string_matching;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author Wuyj
 * @DateTime 2023-03-30 15:28
 * @Version 1.0
 */
public class BM1 {
    public int indexOf(String mainStr, String pattern) {
        if (mainStr == null || pattern == null) return -1;
        int m = mainStr.length();
        int n = pattern.length();
        if (m < n) return -1;
        Map<Character, Integer> map = getIndexMap(pattern);
        int i = 0;
        while (i <= m - n) {
            int y;
            for (y = n - 1; y >= 0; y--) {
                if (mainStr.charAt(i + y) != pattern.charAt(y)) break;
            }
            if (y < 0) return i;
            char badChar = mainStr.charAt(i + y);
            int x = map.getOrDefault(badChar, -1);
            i = i + Math.max(1, (y - x));
        }
        return -1;
    }

    private Map<Character, Integer> getIndexMap(String pattern) {
        char[] chars = pattern.toCharArray();
        int n = chars.length;
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.put(chars[i], i);
        }
        return map;
    }

    public static void main(String[] args) {
        test1(); // 2
        test2(); // -1
    }

    private static void test2() {
        BM1 b = new BM1();
        String mainStr = "aaaaaaa";
        String patternStr = "baaa";
        System.out.println(b.indexOf(mainStr, patternStr));  // -1
    }

    private static void test1() {
        BM1 b = new BM1();
        String mainStr = "aaabaaabaaabaaaa";
        String patternStr = "aba";

        System.out.println(b.indexOf(mainStr, patternStr));  // 2
    }
}
