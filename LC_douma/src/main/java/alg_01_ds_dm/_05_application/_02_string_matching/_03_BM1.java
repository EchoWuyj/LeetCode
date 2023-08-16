package alg_01_ds_dm._05_application._02_string_matching;

import alg_01_ds_wyj._05_application._02_string_matching.BM1;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author Wuyj
 * @DateTime 2023-03-29 16:01
 * @Version 1.0
 */

public class _03_BM1 {

    // KeyPoint  BM => 坏字符规则
    // 时间复杂度：O((m - n)*n)
    // => 因为 pattern 移动时不是一个字符一个字符移动，故时间复杂度 <= O((m - n)*n)
    // => 最好情况：时间复杂度：O(m/n)
    // mainStr：aaab aaab aaab aaaa
    // pattern：aaaa
    // 空间复杂度：O(n)
    public int indexOf(String mainStr, String pattern) {

        // KeyPoint BM 坏字符规则

        // pattern 从后往前匹配字符，遇到主串第一个不匹配字符，该字符称之为坏字符 (c)
        // 遇到坏字符之后，在 pattern 中查找是否含有坏字符 (c)
        // 若 pattern 中没有坏字符 (c)，则将 pattern 右移到坏字符 (c) 后面一位即可

        // i：主串匹配起始位置
        // y：pattern 坏字符位置
        // x：坏字符(c) 在 pattern 中位置，若不存在，则 x=-1

        //       i   坏字符
        //       ↓     ↓
        //       a  b  c  a  c  a  b  d  c
        //       a  b  d
        //     ↑       ↑
        //   x=-1      y=2

        //   i = i + (y-x)
        //           i   坏字符
        //           ↓     ↓
        //  a  b  c  a  c  a  b  d  c
        //           a  b  d
        //           ↑     ↑
        //         x=0   y=2

        //          i = i + (y-x)
        //                 i
        //                 ↓
        //  a  b  c  a  c  a  b  d  c
        //                 a  b  d

        // 特殊情况：若坏字符在模式串中出现多次，那么 x 等于最靠后的那个位置

        //         坏字符
        //           i
        // a b c a a a d d c
        //       a a d
        //       x=1 y=2
        // 坏字符 a 在模式串中出现两次，则 x 取靠后的位置，x=1

        if (mainStr == null || pattern == null) return -1;
        int m = mainStr.length();
        int n = pattern.length();
        if (m < n) return -1;

        // 数据(模式串)预处理, 构建坏字符哈希表
        // key -> 字符
        // value -> 索引位置
        Map<Character, Integer> map = getIndexMap(pattern);

        // i 表示每次匹配的时候，主串的起始位置，初始化为 0
        int i = 0;
        // i = m-n 此时，模式串已经不能向后移动了
        while (i <= m - n) {
            // 1.找到第一个坏字符，使用 y 指针来表示
            // y 定义在 for 循环外面，后续会使用到 y 变量，否认只能在 for 循环内部使用
            int y;
            // pattern 从后往前匹配，来找到第一个坏字符位置
            for (y = n - 1; y >= 0; y--) {
                // KeyPoint 抽象公式，通过具体例子推导
                if (mainStr.charAt(i + y) != pattern.charAt(y)) break;

                // index    0 1 2 3 4
                //         i=0  坏字符：y+i
                // mainStr  a b c d e
                // pattern  c d e
                //             y=2
            }

            // 2.y < 0，匹配成功，没有坏字符
            if (y < 0) {
                // 返回主串和模式串第一个匹配字符串的位置
                return i;
            }
            // 3. y > 0，匹配不成功，存在坏字符，模式串往后移动
            // mainStr 中的坏字符
            char badChar = mainStr.charAt(i + y);

            // x 表示坏字符在模式串中的位置，需要计算 x 位置
            // 模式串中没有坏字符，则 x = -1;
            // KeyPoint 使用哈希查找，优化原来的线性查找
            int x = map.getOrDefault(badChar, -1);

            // 4.往后移动 y - x 位，通过移动 i 指针实现
            // KeyPoint bug 修复
            // 坏字符在模式串的中位置可能会大于 y，即 x 有可能大于 y
            // 解决方案就是：如果 y < x 的话，那么去选择往前走 1 步
            i = i + Math.max(1, (y - x));

            // KeyPoint
            //  aaa
            //  aba
            // mainStr  a a a a
            // pattern  b a a a
            //          ↑     ↑
            //        y=0    x=3
            // i = i + (y-x) = -3，此时模式串左移，存在 bug
            // 解决方案就是：如果 y < x 的话，那么去选择往前走 1 步
        }

        // while 循环结束，都没有 return，则匹配不上
        return -1;
    }

    // 函数功能：模式串预处理，构建坏字符哈希表
    // 线性查找 -> 哈希查找，提高效率
    private Map<Character, Integer> getIndexMap(String pattern) {
        char[] chars = pattern.toCharArray();
        int n = chars.length;
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            // 若有相同的字符，则是取最后一个字符的位置索引，put 操作会进行覆盖
            map.put(chars[i], i);
        }
        return map;
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
