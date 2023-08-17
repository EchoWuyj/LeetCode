package alg_01_ds_dm._05_application._02_string_matching;

import alg_01_ds_wyj._05_application._02_string_matching.BM1;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author Wuyj
 * @DateTime 2023-08-17 16:24
 * @Version 1.0
 */
public class _03_BM3 {

    // 无注释版本
    public int indexOf(String mainStr, String pattern) {
        if (mainStr == null || pattern == null) return -1;
        int m = mainStr.length();
        int n = pattern.length();
        if (m < n) return -1;
        Map<Character, Integer> map = getIndexMap(pattern);

        int[] suffix = new int[n];
        boolean[] prefix = new boolean[n];
        genGoodSuffixArr(pattern.toCharArray(), suffix, prefix);

        int i = 0;
        while (i <= m - n) {
            int y;
            for (y = n - 1; y >= 0; y--) {
                if (mainStr.charAt(i + y) != pattern.charAt(y)) break;
            }
            if (y < 0) {
                return i;
            }
            char badChar = mainStr.charAt(i + y);
            int x = map.getOrDefault(badChar, -1);
            int badCharMoveSteps = y - x;
            int goodSuffixMoveSteps = 0;
            if (y < n - 1) {
                goodSuffixMoveSteps = calMoveSteps(y, n, suffix, prefix);
            }
            i = i + Math.max(goodSuffixMoveSteps, badCharMoveSteps);
        }
        return -1;
    }

    private int calMoveSteps(int y, int n, int[] suffix, boolean[] prefix) {
        int k = n - 1 - y;
        if (suffix[k] != -1) return y - suffix[k] + 1;
        for (int i = y + 1; i < n; i++) {
            if (prefix[n - i]) {
                return i;
            }
        }
        return n;
    }

    private void genGoodSuffixArr(char[] pattern, int[] suffix, boolean[] prefix) {
        Arrays.fill(suffix, -1);
        int n = pattern.length;
        for (int i = 0; i < n - 1; i++) {
            int j = i;
            int k = 0;
            while (j >= 0 && pattern[j] == pattern[n - 1 - k]) {
                k++;
                suffix[k] = j;
                j--;
            }
            if (j == -1) prefix[k] = true;
        }
    }

    private Map<Character, Integer> getIndexMap(String pattern) {
        char[] chars = pattern.toCharArray();
        Map<Character, Integer> map = new HashMap<>();
        int n = chars.length;
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
