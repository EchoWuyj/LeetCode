package alg_01_ds_dm._05_application._02_string_matching;

/**
 * @Author Wuyj
 * @DateTime 2023-03-29 16:01
 * @Version 1.0
 */

// KeyPoint 引入哈希算法，手动实现 hashCode，假设字符串中，只包含 a~z 26 个字符
//          用一个二十六进制表示一个字符串，不存在哈希冲突
//          存在问题：若 pattern 特别长，对应的 26进制数特别大，可能超出整型的表示范围
//          RK 不能应用文本文档的搜索实现
public class RK3 {
    // 时间复杂度：O(m - n)
    // 空间复杂度：O(m - n)
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

    // KeyPoint 使用用一个二十六进制表示一个字符串，这种哈希算法是没有冲突
    private int calHashCode(String mainStr, int i, int[] hashCodes, int n) { // O(1)
        return hashCodes[i - 1] * 26
                - (mainStr.charAt(i - 1) - 'a') * (int) Math.pow(26, n)
                + (mainStr.charAt(i + n - 1) - 'a');
    }

    // abc => 0 * 26^2 + 1 * 26 + 2 = 28
    private int calFirstSubStrHashCode(String str) { // O(n)
        int n = str.length();
        int hashCode = 0;
        // 遍历字符串，是从左往右，进行遍历的
        for (int i = 0; i < n; i++) {
            // Math.pow 返回是 double 数据类型，主要转成 int 类型
            // 26 进制，高位在左，低位在右
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
