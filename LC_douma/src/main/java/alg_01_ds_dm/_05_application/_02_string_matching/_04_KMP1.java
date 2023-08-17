package alg_01_ds_dm._05_application._02_string_matching;

import java.util.Arrays;

/**
 * @Author Wuyj
 * @DateTime 2023-03-29 16:02
 * @Version 1.0
 */

public class _04_KMP1 {

    // -----------------------------------------------

    // KeyPoint 回顾
    // 一般都是通过 BM 算法，实现文本文档查找功能
    // KMP 没有 BM 算法高效，KMP 字符串匹配中最著名的算法

    // KeyPoint KMP 本质
    // 对暴力解法进行优化，根据字符特点，遇到不匹配的字符串，尽可能多的往后滑动模式串
    // BM 算法核心思想也是这样

    // KeyPoint KMP 做法
    // 根据好前缀字符串的特点，来计算出模式串往后移的位数

    // KeyPoint 手动模拟

    //  好前缀    i 坏字符
    // --------- ↓
    // a b a b a e a b a b a c
    //     -----
    //    后缀子串
    // a b a b a c
    // -----     ↑
    // 前缀子串   j
    // ---------
    //  好前缀

    // 前缀子串和后缀子串重叠部分 => 最长匹配前缀子
    // KMP 关键: 找到模式串中好前缀的最长匹配前缀子串

    // 模式串向后移动

    //           i
    //           ↓
    // a b a b a e a b a b a c
    //     -----
    // a b a b a c
    // ----- ↑
    //       j
    // j 不必从 0 索引开始，j 从最长匹配前缀子串后一位开始

    //     好前缀 i
    //     ----- ↓
    // a b a b a e a b a b a c
    //         -
    //     a b a b a c
    //     -
    //     ----- ↑
    //     好前缀 j

    // KeyPoint 补充说明
    // 1.mainStr 和 pattern 两者好前缀是一样的，
    //   故基于模式串计算最长匹配前缀子串，计算出模式串往后移的位数
    // 2.模式串中的前缀都有可能成为好前缀

    // KeyPoint 求解：最长匹配前缀子串
    // pattern： a b a b a c

    //                           好前缀的最长匹配前缀子串
    // 前缀    前缀结尾字符下标       的结尾字符下标           next 数组
    // a            0                  -1 => 表示不存在      next[0]=-1
    // ab           1                  -1                   next[1]=-1
    // aba          2                   0  aba              next[2]=0
    //                                     - -
    // abab         3                   1  ab ab            next[3]=1
    //                                     -- --
    // ababa        4                   2  ababa            next[4]=2
    //                                     ---
    //                                       ---

    // KeyPoint next 数组
    // 通过 next 数组，根据好前缀规则，计算模式串往后移的位数

    // next
    // index  好前缀结尾字符下标
    // value  好前缀的最长匹配前缀子串的结尾字符下标

    // KeyPoint 模式串往后移的位数

    //  好前缀    i 坏字符
    // --------- ↓
    // a b a b a e a b a b a c
    //     -----
    //    后缀子串
    // a b a b a c
    // -----     ↑
    // --------- j，j=next[4]=2+1=3
    //              j 移动到最长匹配前缀子串后面一个位置，故需要加 1
    //              j 不必从 0 索引开始，j 从最长匹配前缀子串后一位开始

    //           i
    //           ↓
    // a b a b a e a b a b a c
    //     -----
    // a b a b a c
    // ----- ↑
    //       j
    //

    //     好前缀 i
    //     ----- ↓
    // a b a b a e a b a b a c
    //         -
    //     a b a b a c
    //     -
    //     ----- ↑
    //     好前缀 j，j=next[2]=0+1=1

    //     好前缀 i
    //         - ↓
    // a b a b a e a b a b a c
    //         -
    //         a b a b a c
    //         -
    //         - ↑
    //     好前缀 j，j=next[0]=-1+1=0

    //           i
    //           ↓
    // a b a b a e a b a b a c
    //           a b a b a c
    //           ↑
    //           j，j=next[0]=-1+1=0

    // 注意
    // i 指针一直都没有变化，省略了 0~i 之间字符比较操作，从而提高了性能

    // 当 j 指针移动到 0 索引位置后，i 指针后移一位
    // i 指针 和 j 指针继续比较

    //             i
    //             ↓
    // a b a b a e a b a b a c
    //           a b a b a c
    //           ↑
    //           j

    //                         i
    //                         ↓
    // a b a b a e a b a b a c
    //           a b a b a c
    //                       ↑
    //                       j

    // -----------------------------------------------

    // KeyPoint KMP
    // 最好情况下时间复杂度：O(m)
    // 最坏情况下时间复杂度：O(mn)
    // 空间复杂度：O(n)
    public static int indexOf(String mainStr, String pattern) {

        if (mainStr == null || pattern == null) return -1;
        int m = mainStr.length();
        int n = pattern.length();
        if (m < n) return -1;

        // 根据模式串所有的前缀，计算得到 next 数组
        int[] next = getNext(pattern.toCharArray());

        // j 指针：表示模式串中起匹配位置
        int j = 0;
        // i 指针，遍历主串中每个字符
        for (int i = 0; i < m; i++) {

            // 往前移动 j 指针后，[i] 和 [j] 有可能依旧不相等，故需要使用 while 循环，反复判断
            // 同时，需要 j > 0 ，若 j = 0，此时 j 指针前面没有前缀，故不使用这种方式来计算，跳出 while 循环
            while (j > 0 && mainStr.charAt(i) != pattern.charAt(j)) {
                // j 不从头开始匹配了，直接跳到好前缀的最长匹配前缀字符串的结尾字符的下一个字符位置
                // j-1 表示好前缀结尾字符下标
                j = next[j - 1] + 1;
            }

            if (mainStr.charAt(i) == pattern.charAt(j)) {
                j++;
            }
            // j 指针移动到 n 位置，返回主串匹配起始位置 start：i-n+1
            if (j == n) {
                return i - n + 1;
            }

            // KeyPoint 注意事项
            // 图示上看：start 为 i-n，但是代码上执行 j++，就 return start 值
            // 此时 for 循环中 i++ 还没有执行，故 start 需要补上一个 1，最终返回 i-n+1

            //         start           i
            //           ↓             ↓
            // a b a b a e a b a b a c
            //           a b a b a c
            //                       ↑
            //                       j
        }

        return -1;
    }

    // 函数功能：实现获取 next 数组
    // 暴力解法 => 枚举所有情况
    // O(n^3)
    private static int[] getNext(char[] pattern) {

        // 暴力解法 => 枚举所有情况

        //   前缀子串
        //  ---------
        //  a b a b a | c
        //  -       - => next[4]=0
        //  ---   --- => 并不匹配 next[4]=0
        //  -----
        //      ----- => next[4]=2
        //  -------
        //    ------- => 并不匹配 next[4]=2

        int n = pattern.length;
        // bug 修复： 如果只有一个字符的话，就不计算 next 数组
        if (n == 1) return new int[0];

        // 模式串中的前缀子串不包括最后一个字符
        // a b a b a | c
        int[] next = new int[n - 1];
        Arrays.fill(next, -1);
        for (int i = 1; i < n - 1; i++) {
            // 1.拿到模式串中 [0...i] 这个好前缀
            String goodPrefix = new String(pattern, 0, i + 1);
            // 2.计算当前好前缀的[最长匹配前缀子串]结尾字符的下标
            for (int j = i; j > 0; j--) {
                // 2.1 拿到好前缀的 [j, i] 这个后缀字符串
                String suffix = goodPrefix.substring(j);
                // 2.2 将后缀的每个字符和好前缀的前缀的每个字符比较
                // 得到第一个不相等的字符所在的位置 k
                int k;
                for (k = 0; k < suffix.length(); k++) {
                    if (suffix.charAt(k) != goodPrefix.charAt(k)) {
                        break;
                    }
                }
                // 2.3 如果前缀匹配了后缀字符串，那么更新[最长匹配前缀字符串]结尾字符的下标
                if (k == suffix.length()) {
                    // 注意：next[i] 之前可能已经计算过，所以我们需要取最大值
                    next[i] = Math.max(k - 1, next[i]);
                }
            }
        }
        // 最值问题
        return next;
    }

    public static void main(String[] args) {
        String mainStr = "aaabaaa";
        String patternStr = "baaa";
        System.out.println(indexOf(mainStr, patternStr));
    }
}
