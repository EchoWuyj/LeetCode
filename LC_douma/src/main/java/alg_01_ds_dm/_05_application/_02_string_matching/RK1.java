package alg_01_ds_dm._05_application._02_string_matching;

/**
 * @Author Wuyj
 * @DateTime 2023-03-29 16:01
 * @Version 1.0
 */

// KeyPoint 引入哈希算法，系统实现 hashCode
public class RK1 {
    // 时间复杂度：O((m - n)*n)
    // 空间复杂度：O(m - n) -> 申请了额外长度 m - n + 1 的数组
    public int indexOf(String mainStr, String pattern) {
        if (mainStr == null || pattern == null) return -1;

        int m = mainStr.length();
        int n = pattern.length();
        if (m < n) return -1;

        // 1. 计算主串中 m - n + 1 个子串的哈希值，并且将哈希值存储在 hashCodes 数组
        // m - n + 1 通过举例计算而得到
        // 0 1 2 3 4 -> 5
        //     0 1 2 -> 3
        // 有 0 1 2 个字串，即为 5-3+1 = 3
        int[] hashCodes = new int[m - n + 1];
        for (int i = 0; i < m - n + 1; i++) {
            hashCodes[i] = calHashCode(mainStr.substring(i, i + n));
        }

        // 2. 计算模式串的哈希值
        int hashCode = calHashCode(pattern);

        // 3. 在所有子串哈希值中，寻找是否有模式串的哈希值
        for (int i = 0; i < m - n + 1; i++) {
            if (hashCode == hashCodes[i]) {
                return i;
            }
        }

        return -1;
    }

    // KeyPoint 优化 -> 降低 hashCode 的时间复杂度
    private int calHashCode(String str) {
        // 需要遍历子串的长度 n，故时间复杂度为 O(n)
        return str.hashCode(); // O(n)
    }

    public static void main(String[] args) {
        RK1 b = new RK1();
        String mainStr = "    your code";
        String patternStr = "your";

        System.out.println(b.indexOf(mainStr, patternStr)); // 4
    }
}
