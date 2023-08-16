package alg_01_ds_dm._05_application._02_string_matching;

import alg_01_ds_wyj._05_application._02_string_matching.BF1;

/**
 * @Author Wuyj
 * @DateTime 2023-03-25 21:04
 * @Version 1.0
 */

public class _01_BF1 {

    // KeyPoint BF (Brute Force) 暴力匹配算法 / 朴素匹配算法 => 实现一
    // 暴力匹配：一个字符一个字符比较
    // 时间复杂度：O(mn)
    // 空间复杂度：O(1)
    public int indexOf(String mainStr, String pattern) {

        //       k
        //       ↓
        // s： d c c k c e e m e
        //       ↑
        //       i
        // p： c e e
        //     ↑
        //     j

        //                 k
        //                 ↓
        // s： d c c k c e e m e
        //             ↑
        //             i
        // p： c e e
        //         ↑
        //         j

        // 主串或者模式串，若其中一个为 null，则返回 -1
        if (mainStr == null || pattern == null) return -1;
        int m = mainStr.length();
        int n = pattern.length();
        // 主串长度 < 模式串长度，返回 -1
        if (m < n) return -1;

        // i 遍历主串的每个字符
        for (int i = 0; i < m; i++) {
            // 1.比对后面的字符，如果相等的话，一直比对下去
            int k = i;
            // j 遍历模式串的每个字符
            for (int j = 0; j < n; j++) {
                // KeyPoint 注意事项
                // 1.k < m ，m 为数组长度，索引 < 长度，严格小于，避免越界
                // 2.循环比较，移动的是 k 指针，而不是 i 指针
                // 3.出现 bug，通过打印输出 k，分许 bug 在那里，不要依赖文件对比
                if (k < m && pattern.charAt(j) == mainStr.charAt(k)) {
                    k++;
                    // 如果 j 是模式串的最后一个字符，说明匹配到了模式串，返回 i 位置
                    if (j == n - 1) return i;
                } else {
                    // k 和 j 对应的字符不相等，退出循环，执行 i 的下轮循环
                    break;
                }
            }
        }
        // 没有找到，返回 -1
        return -1;
    }

    public static void main(String[] args) {
        BF1 b = new BF1();
        String mainStr = "    your code";
        String patternStr = "your";

        System.out.println(b.indexOf(mainStr, patternStr)); // 4
    }
}
