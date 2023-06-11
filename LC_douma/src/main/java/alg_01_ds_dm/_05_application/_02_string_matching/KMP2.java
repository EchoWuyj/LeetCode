package alg_01_ds_dm._05_application._02_string_matching;

/**
 * @Author Wuyj
 * @DateTime 2023-03-29 16:03
 * @Version 1.0
 */
public class KMP2 {
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

    private int[] getNext(char[] pattern) {
        int n = pattern.length;
        // bug 修复： 如果只有一个字符的话，就不计算 next 数组
        if (n == 1) return new int[0];
        int[] next = new int[n - 1];

        next[0] = -1;

        for (int j = 1; j < n - 1; j++) {
            if (pattern[next[j - 1] + 1] == pattern[j]) {
                next[j] = next[j - 1] + 1;
            } else {
                int pre = next[j - 1];

                while (pre >= 0 && pattern[pre + 1] != pattern[j]) {
                    // 看前一个【最长匹配前缀字符串】
                    pre = next[pre];
                }

                if (pattern[pre + 1] == pattern[j]) {
                    next[j] = pre + 1;
                } else {
                    next[j] = pre;
                }
            }
        }
        // 最值问题
        return next;
    }

    public static void main(String[] args) {
        KMP2 b = new KMP2();
        String mainStr = "aaabaaa";
        String patternStr = "baaa";

        System.out.println(b.indexOf(mainStr, patternStr));
    }
}
