package alg_01_ds_dm._05_application._02_string_matching;

import alg_01_ds_wyj._05_application._02_string_matching.RK1;

/**
 * @Author Wuyj
 * @DateTime 2023-03-29 16:01
 * @Version 1.0
 */

public class _02_RK1 {

    // KeyPoint RK 字符串匹配算法

    // 分析 BF 存在性能瓶颈
    // => 在找到主串和模式串第一个相等字符，后面还需要一个字符，一个字符逐一匹配对比 => 算法慢的原因

    // 引入哈希算法 => 优化：一个字符，一个字符逐一匹配对比
    // 哈希算法 => 系统实现 hashCode

    // 思路：
    // 1.计算主串中 m-n+1 个子串的哈希值，并且将哈希值存储在 hashCodes 数组
    // 2.计算模式串的哈希值
    // 3.在所有子串哈希值中，寻找是否有模式串的哈希值

    // s： d c c k c e e m e
    //     |---| => 计算哈希值 h1
    //       |---| => 计算哈希值 h2
    //           ...

    // p： c e e

    // RK 字符串匹配算法
    // 时间复杂度：O((m-n)*n) => 时间复杂度还是有点高，需要进一步优化
    //                       => 通过降低 calHashCode 时间复杂度
    // 空间复杂度：O(m-n) -> 申请了额外长度 m-n+1 的数组
    public int indexOf(String mainStr, String pattern) {
        if (mainStr == null || pattern == null) return -1;

        int m = mainStr.length();
        int n = pattern.length();
        if (m < n) return -1;

        // 1.计算主串中 m-n+1 个子串的哈希值，并且将哈希值存储在 hashCodes 数组
        //    m-n+1 通过举例计算而得到
        //    0 1 2 3 4 -> m = 5
        //        0 1 2 -> n = 3
        //    有 0 1 2 个字串，即为 5-3+1 = 3
        int[] hashCodes = new int[m - n + 1];
        for (int i = 0; i < m - n + 1; i++) {
            // 在主串重截取长度为 n 的字串，计算其哈希值
            // 保证：end - start = n
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

    // 系统实现 hashCode
    private int calHashCode(String str) {
        // 需要遍历子串的长度 n，故时间复杂度为 O(n)
        // 可以优化：降低 hashCode 的时间复杂度
        return str.hashCode(); // O(n)
    }

    public static void main(String[] args) {
        RK1 b = new RK1();
        String mainStr = "    your code";
        String patternStr = "your";

        System.out.println(b.indexOf(mainStr, patternStr)); // 4
    }
}
