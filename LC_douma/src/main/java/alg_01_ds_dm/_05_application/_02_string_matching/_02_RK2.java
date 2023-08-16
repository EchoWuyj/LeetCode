package alg_01_ds_dm._05_application._02_string_matching;

import alg_01_ds_wyj._05_application._02_string_matching.RK2;

/**
 * @Author Wuyj
 * @DateTime 2023-03-29 16:01
 * @Version 1.0
 */

public class _02_RK2 {

    // KeyPoint 引入哈希算法 => 手动实现 hashCode => 实现一

    // 假设字符串中，只包含 a~z 26 个字符
    // 0 表示 a
    // 1 表示 b
    // 2 表示 c
    // 3 表示 d
    // ...
    // 25 表示 z

    // 子串的哈希值等于子串所有字符之和，存在哈希冲突
    // s： d c c k c e e m e
    //     |---|
    //     哈希值：3+2+2

    // 引入哈希算法 => 手动实现 hashCode
    // 时间复杂度：O(m-n)，前提条件： m-n > n
    // 空间复杂度：O(m-n)
    public int indexOf(String mainStr, String pattern) {
        if (mainStr == null || pattern == null) return -1;

        int m = mainStr.length();
        int n = pattern.length();
        if (m < n) return -1;

        // 1. 计算主串中 m-n+1 个子串的哈希值
        int[] hashCodes = new int[m - n + 1];

        // mainStr 第一个子串的 hash 值，单独计算，其没有前面哈希值
        hashCodes[0] = calFirstSubStrHashCode(mainStr.substring(0, n));
        for (int i = 1; i < m - n + 1; i++) {
            // 1.根据前一个子串的 hash[i-1] 值计算当前子串的 hash[i] 值，故需要传入 hashCodes 数组
            // 2.i 为是 mainStr 是第几个字符的位置，for 循环遍历每个位置
            // 3.n 为 pattern 的长度，计算 mainStr.charAt(i+n-1)-'a' 使用
            hashCodes[i] = calHashCode(mainStr, i, hashCodes, n);
        }

        // 2.计算模式串的哈希值
        int hashCode = calFirstSubStrHashCode(pattern);

        // 3.在所有子串哈希值中，寻找是否有模式串的哈希值
        for (int i = 0; i < m - n + 1; i++) {
            // KeyPoint 存在 bug：存在哈希冲突的情况 => 需要进行进一步判断
            // abc = 0+1+2 = 3
            // cba = 2+1+0 = 3
            if (hashCode == hashCodes[i]) {
                // KeyPoint 解决哈希冲突
                // 1.将子串和模式串重新对比一遍
                // 2.若存在很多哈希冲突，则该算法时间复杂度退化成 O((m - n)*n)
                // 使用 k 代替 i 往后遍历，不能直接使用 i，因为 i 还要返回
                int k = i;
                for (int j = 0; j < n && k < m; j++, k++) {
                    if (mainStr.charAt(k) != pattern.charAt(j)) {
                        break;
                    }
                    // 最后判断 j 指针，若 j 指针能到达 n-1，则说明能匹配上，返回 i 指针
                    if (j == n - 1) return i;
                }
            }
        }

        return -1;
    }

    // 函数功能：自定义 calHashCode
    // 时间复杂度 O(1)
    private int calHashCode(String mainStr, int i, int[] hashCodes, int n) {
        // 计算 h[i] 使用 h[i-1] 推导，需要在 h[i-1] 加 i + n - 1 项 ，减 i - 1 项
        return hashCodes[i - 1] - (mainStr.charAt(i - 1) - 'a')
                + (mainStr.charAt(i + n - 1) - 'a');
    }

    // 函数功能：计算 mainStr 第一个子串的 hash 值
    // 时间复杂度 O(n)
    private int calFirstSubStrHashCode(String str) { // O(n)
        int n = str.length();
        int hashCode = 0;
        for (int i = 0; i < n; i++) {
            // abc => 0 + 1 + 2 = 3
            hashCode += (str.charAt(i) - 'a'); // 正序
            // hashCode += (str.charAt(n - i - 1) - 'a'); 逆序

        }
        return hashCode;
    }

    private static void test2() {
        RK2 b = new RK2();
        String mainStr = "    your code";
        String patternStr = "your";

        System.out.println(b.indexOf(mainStr, patternStr)); // 4
    }

    private static void test1() {
        RK2 b = new RK2();
        String mainStr = "    oury code"; // oury 和 your 产生哈希冲突
        String patternStr = "your";

        System.out.println(b.indexOf(mainStr, patternStr)); // -1
    }

    public static void main(String[] args) {
        test1(); // -1
        test2(); // 4
    }
}
