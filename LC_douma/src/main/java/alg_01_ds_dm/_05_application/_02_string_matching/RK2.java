package alg_01_ds_dm._05_application._02_string_matching;

/**
 * @Author Wuyj
 * @DateTime 2023-03-29 16:01
 * @Version 1.0
 */
// KeyPoint 引入哈希算法，手动实现 hashCode
//          假设字符串中，只包含 a~z 26 个字符
//          子串的哈希值等于子串所有字符之和，存在哈希冲突
public class RK2 {
    // 时间复杂度：O(m - n)
    // 空间复杂度：O(m - n)
    public int indexOf(String mainStr, String pattern) {
        if (mainStr == null || pattern == null) return -1;

        int m = mainStr.length();
        int n = pattern.length();
        if (m < n) return -1;

        // 1. 计算主串中 m - n + 1 个子串的哈希值
        int[] hashCodes = new int[m - n + 1];
        // 计算 mainStr 第一个子串的 hash 值
        hashCodes[0] = calFirstSubStrHashCode(mainStr.substring(0, n));
        for (int i = 1; i < m - n + 1; i++) {
            // 根据前一个子串的 hash 值计算下一个子串的 hash 值，故需要传入 hashCodes 数组
            // i 为是 mainStr 是第几个字符的位置，for 循环遍历每个位置
            // n 为 pattern 的长度
            hashCodes[i] = calHashCode(mainStr, i, hashCodes, n);
        }

        // 2. 计算模式串的哈希值
        int hashCode = calFirstSubStrHashCode(pattern);

        // 3. 在所有子串哈希值中，寻找是否有模式串的哈希值
        for (int i = 0; i < m - n + 1; i++) {
            // 存在哈希冲突的情况，需要进行进一步判断
            // abc = 0 + 1 + 2 = 3
            // cba = 2 + 1 + 0 = 3
            if (hashCode == hashCodes[i]) {
                // 解决哈希冲突问题：将子串和模式串重新对比一遍
                // KeyPoint 若存在很多哈希冲突，则该算法时间复杂度退化成 O((m - n)*n)
                int k = i; // 使用 k 代替 i 往后遍历，不能直接使用 i，因为 i 还要返回
                for (int j = 0; j < n && k < m; j++, k++) {
                    if (mainStr.charAt(k) != pattern.charAt(j)) {
                        break;
                    }
                    if (j == n - 1) return i;
                }
            }
        }

        return -1;
    }

    // h[i] 使用 h[i-1] 表示，需要在 h[i-1] 加 i + n - 1 项 ，减 i - 1 项
    private int calHashCode(String mainStr, int i, int[] hashCodes, int n) {
        return hashCodes[i - 1] - (mainStr.charAt(i - 1) - 'a')
                + (mainStr.charAt(i + n - 1) - 'a');
    }

    // 计算 mainStr 第一个子串的 hash 值
    // abc => 0 + 1 + 2 = 3
    private int calFirstSubStrHashCode(String str) { // O(n)
        int n = str.length();

        int hashCode = 0;
        for (int i = 0; i < n; i++) {
            hashCode += (str.charAt(i) - 'a'); // 正序
            // hashCode += (str.charAt(n - i - 1) - 'a'); 逆序

        }

        return hashCode;
    }

    public static void main(String[] args) {
        test1(); // -1
        test2(); // 4
    }

    private static void test2() {
        RK2 b = new RK2();
        String mainStr = "    your code";
        String patternStr = "your";

        System.out.println(b.indexOf(mainStr, patternStr)); // 4
    }

    private static void test1() {
        RK2 b = new RK2();
        String mainStr = "    oury code";
        String patternStr = "your";

        System.out.println(b.indexOf(mainStr, patternStr)); // -1
    }
}
