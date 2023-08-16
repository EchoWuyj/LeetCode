package alg_01_ds_dm._05_application._02_string_matching;

/**
 * @Author Wuyj
 * @DateTime 2023-03-25 21:05
 * @Version 1.0
 */

public class _01_BF2 {

    // KeyPoint BF (Brute Force) 暴力匹配算法 / 朴素匹配算法 => 实现二

    // 时间复杂度：O(mn)
    // 空间复杂度：O(1)
    public static int indexOf(String mainStr, String pattern) {

        //       k
        //       ↓
        // s： d c c k c e e m e
        //       ↑
        //       i
        // p： c e e
        //     ↑
        //     j

        // 1.找到等于模式串中第一个字符的位置
        //    mainStr 和 pattern 首个字符相等之后，分别从后面一个字符开始比较
        // 2.比对后面的字符，如果相等的话，一直比对下去

        if (mainStr == null || pattern == null) return -1;
        int m = mainStr.length();
        int n = pattern.length();
        if (m < n) return -1;

        // 记录模式串的第一字符
        char firstChar = pattern.charAt(0);
        for (int i = 0; i < m; i++) {
            // 1.找到等于模式串中第一个字符的位置
            if (mainStr.charAt(i) != firstChar) {
                // while 循环中 ++i，i++ 都是可以的，但是需要牢记 i++ 和 ++i 的区别
                // i++ 先操作，再自增
                // ++i 先自增，再操作
                while (i < m && mainStr.charAt(i) != firstChar) {
                    i++;
                }
            }

            // 经过上面 while 循环之后
            // mainStr 和 pattern 首个字符相等，之后分别从后面一个字符开始比较
            // i < m 保证不越界
            if (i < m) {
                // 2. 比对后面的字符，如果相等的话，一直比对下去
                int k = i + 1;
                int j = 1;
                // KeyPoint bug 修复
                // 如果 j 已经超出了 pattern 范围，说明已经找到了，则直接返回
                // 边界条件：在 pattern 长度为 1 时的判断 => test2()
                if (j == n) return i;
                // 使用 while 循环替换 => while (k < m && j < n)
                for (; j < n && k < m; j++, k++) {
                    if (mainStr.charAt(k) == pattern.charAt(j)) {
                        // 3.如果 j 是模式串的最后一个字符，说明匹配到了模式串
                        if (j == n - 1) return i;
                    } else {
                        // 如果字符不相等，则退出循环
                        break;
                    }
                }
            }
        }
        return -1;

        // KeyPoint 补充说明
        // java.lang.String.indexOf 实现就是这种方法
        // 1.平时开发中，mainStr 和 pattern 都不会太长，O(mn) 满足性能需要，可以接受
        // 2.BF 代码实现简单，不容易出现 bug
        // 3.java.lang.String.indexOf 不适用于：文本文档搜索功能，因为主串可能非常大
    }

    // for test
    public static void test1() {
        String mainStr = "    your code";
        String patternStr = "your";
        System.out.println(indexOf(mainStr, patternStr)); // 4
    }

    // for test
    public static void test2() {
        String mainStr = "a";
        String patternStr = "a";
        System.out.println(indexOf(mainStr, patternStr)); // 0
    }

    public static void main(String[] args) {
        test1();
        test2();
    }
}
