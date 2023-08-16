package alg_01_ds_dm._05_application._02_string_matching;

import alg_01_ds_wyj._05_application._02_string_matching.RK3;

/**
 * @Author Wuyj
 * @DateTime 2023-03-29 16:01
 * @Version 1.0
 */

public class _02_RK3 {

    // KeyPoint 引入哈希算法，手动实现 hashCode => 实现二

    // 假设字符串中，只包含 a~z 26 个字符
    // 因为字母有 26 个，故将其看成 26 进制 => 用一个二十六进制表示一个字符串，不存在哈希冲突

    // s： d c c k c e e m e
    //     |---|
    //   哈希值 = 3*26 + 2*26 + 2*26 = 2368

    // KeyPoint 存在问题
    // RK 算法不能应用文本文档搜索功能
    // 1.RK 算法基于假设：只包含 a~z 26 个字符
    // 2.若 pattern 特别长，对应的 26进制数特别大，可能超出整型的表示范围

    // 时间复杂度：O(m-n)
    // 空间复杂度：O(m-n)
    public int indexOf(String mainStr, String pattern) {
        if (mainStr == null || pattern == null) return -1;

        int m = mainStr.length();
        int n = pattern.length();
        if (m < n) return -1;

        // 1. 计算主串中 m - n + 1 个子串的哈希值
        int[] hashCodes = new int[m - n + 1];
        // 计算第一个子串的 hash 值
        hashCodes[0] = calFirstSubStrHashCode(mainStr.substring(0, n));

        for (int i = 1; i < m - n + 1; i++) {
            // 根据前一个子串的 hash 值计算下一个子串的 hash 值
            hashCodes[i] = calHashCode(mainStr, i, hashCodes, n);
        }

        // 2. 计算模式串的哈希值
        int hashCode = calFirstSubStrHashCode(pattern);

        // 3. 在所有子串哈希值中，寻找是否有模式串的哈希值
        for (int i = 0; i < m - n + 1; i++) {
            if (hashCode == hashCodes[i]) {
                return i;
            }
        }
        return -1;
    }

    private int calHashCode(String mainStr, int i, int[] hashCodes, int n) { // O(1)

        // 使用用一个二十六进制表示一个字符串，这种哈希算法是没有冲突
        // 推导公式，通过具体例子找规律，不用抽象的式子，比较麻烦
        // a b c d
        // |---| => 0*26^2 + 1*26 + 2*26^0
        //   |---| => 1*26^2 + 2*26 + 3*26^0
        // [0*26^2 + 1*26 + 2*26^0]*26 => 0*26^3 + 1*26^2 + 2*26^1
        // 故有：
        // [a b c]*26：0*26^3 + 1*26^2 + 2*26^1
        // [b c d]              1*26^2 + 2*26 + 3*26^0
        return hashCodes[i - 1] * 26
                - (mainStr.charAt(i - 1) - 'a') * (int) Math.pow(26, n)
                + (mainStr.charAt(i + n - 1) - 'a');
    }

    private int calFirstSubStrHashCode(String str) { // O(n)
        int n = str.length();
        int hashCode = 0;

        for (int i = 0; i < n; i++) {
            // Math.pow 返回是 double 数据类型，主要转成 int 类型
            // 26 进制，高位在左，低位在右
            // abc => 0*26^2 + 1*26 + 2*26^0 = 28
            // i 是从左往右遍历的，i 从 0 开始
            hashCode += (int) Math.pow(26, n - i - 1) * (str.charAt(i) - 'a');
        }

        return hashCode;
    }

    public static void main(String[] args) {
        test1(); // -1
        test2(); // 4
    }

    private static void test2() {
        RK3 b = new RK3();
        String mainStr = "    your code";
        String patternStr = "your";

        System.out.println(b.indexOf(mainStr, patternStr)); // 4
    }

    private static void test1() {
        RK3 b = new RK3();
        String mainStr = "    oury code";
        String patternStr = "your";

        System.out.println(b.indexOf(mainStr, patternStr)); // -1
    }
}
