package alg_01_ds_dm._05_application._02_string_matching;

import java.util.Arrays;

/**
 * @Author Wuyj
 * @DateTime 2023-03-29 16:02
 * @Version 1.0
 */
// KeyPoint
//  KMP 没有 BM 算法高效
//  KMP 本质：对暴力解法进行优化，根据字符特点，遇到不匹配的字符串，尽可能多的往后滑动模式串
public class KMP1 {
    // 最好情况下时间复杂度：O(m)
    // 最坏情况下时间复杂度：O(mn)
    // 空间复杂度：O(n)
    public int indexOf(String mainStr, String pattern) {
        if (mainStr == null || pattern == null) return -1;

        int m = mainStr.length();
        int n = pattern.length();
        if (m < n) return -1;

        // 根据模式串所有的前缀，计算得到 next 数组
        int[] next = getNext(pattern.toCharArray());

        int j = 0;
        for (int i = 0; i < m; i++) {
            while (j > 0 && mainStr.charAt(i) != pattern.charAt(j)) {
                // 就不从头开始匹配了，直接跳到下一个最长匹配前缀字符串的结尾字符的下一个字符位置
                j = next[j - 1] + 1;
            }

            if (mainStr.charAt(i) == pattern.charAt(j)) {
                j++;
            }

            if (j == n) {
                return i - n + 1;
            }
        }

        return -1;
    }

    private int[] getNext(char[] pattern) { // O(n^3)
        int n = pattern.length;
        // bug 修复： 如果只有一个字符的话，就不计算 next 数组
        if (n == 1) return new int[0];

        int[] next = new int[n - 1];
        Arrays.fill(next, -1);

        for (int i = 1; i < n - 1; i++) {
            // 1. 拿到模式串中 [0...i] 这个好前缀
            String goodPrefix = new String(pattern, 0, i + 1);
            // 2. 计算当前好前缀的【最长匹配前缀子串】结尾字符的下标
            for (int j = i; j > 0; j--) {
                // 2.1 拿到好前缀的 [j, i] 这个后缀字符串
                String suffix = goodPrefix.substring(j);
                // 2.2 将后缀的每个字符和好前缀的前缀的每个字符比较
                // 得到第一个不相等的字符所在的位置 k
                int k;
                for (k = 0; k < suffix.length(); k++) {
                    if (suffix.charAt(k) != goodPrefix.charAt(k)) {
                        break;
                    }
                }
                // 2.3 如果前缀匹配了后缀字符串，那么更新【最长匹配前缀字符串】结尾字符的下标
                if (k == suffix.length()) {
                    // 注意：next[i] 之前可能已经计算过，所以我们需要取最大值
                    next[i] = Math.max(k - 1, next[i]);
                }
            }
        }
        // 最值问题
        return next;
    }

    public static void main(String[] args) {
        KMP1 b = new KMP1();
        String mainStr = "aaabaaa";
        String patternStr = "baaa";

        System.out.println(b.indexOf(mainStr, patternStr));
    }
}
