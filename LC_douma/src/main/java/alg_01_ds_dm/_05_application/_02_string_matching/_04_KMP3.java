package alg_01_ds_dm._05_application._02_string_matching;

/**
 * @Author Wuyj
 * @DateTime 2023-03-29 16:03
 * @Version 1.0
 */
public class _04_KMP3 {
    // 最好情况下时间复杂度：O(m)
    // 最坏情况下时间复杂度：O(mn)
    // 空间复杂度：O(n)
    public static int indexOf(String mainStr, String pattern) {
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

    // 函数功能：实现获取 next 数组
    // 暴力解法
    private static int[] getNext(char[] pattern) {
        int n = pattern.length;
        // bug 修复： 如果只有一个字符的话，就不计算 next 数组
        if (n == 1) return new int[0];
        int[] next = new int[n - 1];

        next[0] = -1;

        for (int j = 1; j < n - 1; j++) {
            int pre = next[j - 1];
            while (pre != -1 && pattern[pre + 1] != pattern[j]) {
                // 因为前一个最长串的下一个字符不与最后一个相等，所以需要找前一个的次长串
                // 问题就变成了求 0 到 next(pre) 的最长串
                pre = next[pre];
            }
            if (pattern[pre + 1] == pattern[j]) {
                pre++;
            }
            next[j] = pre;
        }
        // 最值问题
        return next;
    }

    public static void main(String[] args) {
        String mainStr = "aaabaaa";
        String patternStr = "baaa";
        System.out.println(indexOf(mainStr, patternStr));
    }
}
