package alg_01_ds_dm._05_application._02_string_matching;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author Wuyj
 * @DateTime 2023-03-29 16:01
 * @Version 1.0
 */

// KeyPoint 使用坏字符规则实现 BM
public class BM1 {
    // 时间复杂度：O((m - n)*n)，因为 pattern 移动时不是一个一个移动，故时间复杂度 <=  O((m - n)*n)
    // 最好情况下时间复杂度：O(m/n)
    // 空间复杂度：O(n)
    public int indexOf(String mainStr, String pattern) {
        if (mainStr == null || pattern == null) return -1;

        int m = mainStr.length();
        int n = pattern.length();
        if (m < n) return -1;

        // 数据(模式串)预处理, 构建坏字符哈希表
        // key -> 字符
        // value -> 索引位置
        Map<Character, Integer> bc = genBadCharIndexMap(pattern);

        // i 表示每次匹配的时候，主串的起始位置，初始化为 0
        int i = 0;
        // i = m-n 此时，模式串已经不能向后移动了
        while (i <= m - n) {
            // 1. 找到第一个坏字符，使用 y 指针来表示
            int y;
            // pattern 从后往前匹配，来找到第一个坏字符位置
            for (y = n - 1; y >= 0; y--) {
                // 索引 0 + n-1，n-1 表示 n-1 个元素个数，0 ~ n-1 一共有 n 个元素
                // 因为 模式串和主串都从 0 开始的，故 i + y 可以直接相加
                if (mainStr.charAt(i + y) != pattern.charAt(y)) break;
            }

            // 2. 表示匹配成功，没有坏字符
            if (y < 0) {
                // 返回主串和模式串第一个匹配字符串的位置
                return i;
            }

            // 3. 匹配不成功，存在坏字符，模式串往后移动
            //    x 表示坏字符在模式串中的位置，首先需要计算 x

            // mainStr 中的坏字符
            char badChar = mainStr.charAt(i + y);

            // 模式串中没有坏字符，则 x = -1;
            int x = bc.getOrDefault(badChar, -1);

            // 4. KeyPoint 往后移动 y - x 位 => 修改 mainStr 中 i 指针的位置，从 mainStr 后面的位置进行匹配
            // KeyPoint bug 修复：坏字符在模式串的中位置可能会大于 y，即 x 有可能大于 y
            // 比如：主串为  aaabaaabaaabaaaa
            //      模式串为 aba
            // 匹配过程     a a a
            //             a b a
            //               y x
            // 索引位置     0 1 2
            // i 移动 1 步    a b a
            // 解决方案就是：如果 y < x 的话，那么去选择往前走 1 步
            i = i + Math.max(1, (y - x));
        }
        return -1;
    }

    // 数据(模式串)预处理, 构建坏字符哈希表
    // 线性查找 -> 哈希查找，提高效率
    private Map<Character, Integer> genBadCharIndexMap(String pattern) {
        char[] patternChars = pattern.toCharArray();
        Map<Character, Integer> bc = new HashMap<>();
        for (int i = 0; i < patternChars.length; i++) {
            // 若有相同的字符，则是取最后一个字符的位置索引，put 操作会进行覆盖
            bc.put(patternChars[i], i);
        }
        return bc;
    }

    public static void main(String[] args) {
        test1(); // 2
        test2(); // -1
    }

    private static void test2() {
        BM1 b = new BM1();
        String mainStr = "aaaaaaa";
        String patternStr = "baaa";
        System.out.println(b.indexOf(mainStr, patternStr));  // -1
    }

    private static void test1() {
        BM1 b = new BM1();
        String mainStr = "aaabaaabaaabaaaa";
        String patternStr = "aba";

        System.out.println(b.indexOf(mainStr, patternStr));  // 2
    }
}
