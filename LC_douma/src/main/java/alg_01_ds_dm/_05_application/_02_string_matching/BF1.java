package alg_01_ds_dm._05_application._02_string_matching;

/**
 * @Author Wuyj
 * @DateTime 2023-03-25 21:04
 * @Version 1.0
 */

// BF 暴力匹配
public class BF1 {
    // 时间复杂度：O(mn)
    // 空间复杂度：O(1)
    public int indexOf(String mainStr, String pattern) {
        // 主串或者模式串，若其中一个为 null，则返回 -1
        if (mainStr == null || pattern == null) return -1;

        int m = mainStr.length();
        int n = pattern.length();
        if (m < n) return -1;

        // i 遍历主串的每个字符
        for (int i = 0; i < m; i++) {
            // 1. 比对后面的字符，如果相等的话，一直比对下去
            int k = i;
            // j 遍历模式串的每个字符
            for (int j = 0; j < n; j++) {
                // KeyPoint m 为数组长度，索引 < 长度，严格小于的关系
                // k < m 避免越界
                if (k < m && pattern.charAt(j) == mainStr.charAt(k)) {
                    k++;
                    // 2. 如果 j 是模式串的最后一个字符，说明匹配到了模式串
                    if (j == n - 1) return i;
                } else {
                    // k 和 j 对应的字符不相等，退出循环，从 k
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
