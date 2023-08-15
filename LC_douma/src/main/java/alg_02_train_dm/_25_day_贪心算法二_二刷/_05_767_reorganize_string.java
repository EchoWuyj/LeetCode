package alg_02_train_dm._25_day_贪心算法二_二刷;

/**
 * @Author Wuyj
 * @DateTime 2023-04-13 13:26
 * @Version 1.0
 */
public class _05_767_reorganize_string {
     /*
        767. 重构字符串
        给定一个字符串S，检查是否能重新排布其中的字母，使得两相邻的字符不同。
        若可行，输出任意可行的结果。若不可行，返回空字符串。

        示例 1:
        输入: S = "aab"
        输出: "aba"

        示例 2:
        输入: S = "aaab"
        输出: ""

        提示:
        1 <= s.length <= 500
        s 只包含小写字母

     */

    // 贪心:只是贪心地考虑，字符串中出现次数最多字符，将其排列不相邻
    //      因为将其排列不相邻的同时，也能保证其他字符是不相邻的
    // 类似：字符串间隙插入
    public String reorganizeString(String s) {

        // 统计每个字符出现的次数

        // s：e c b b c c c e c c

        // a -> 0
        // b -> 2
        // c -> 6
        // d -> 0
        // e -> 2
        // ...
        // z -> 0

        // index 0 1 2 3 4 5 6 7 8 9
        //       c   c   c   c   c  => 剩余一个 c 不管放在那里，都会和已有的 c 相邻

        // 1. 统计每个字符出现的次数
        char[] chars = s.toCharArray();
        int n = chars.length;
        int[] counts = new int[26];
        for (char c : chars) {
            // index 字母对应索引位置
            // counts[index] 字母出现次数
            int index = c - 'a';
            counts[index]++;
            // 特判
            // 若某个字符出现的个数 > 数组一半，则无法重构，直接返回 ""
            // 数组奇数个和偶数个，统一使用 (n+1)/2 计算
            if (counts[index] > (n + 1) / 2) return "";
        }

        // 2.拿到出现次数最多的字符
        int maxCountIndex = 0;
        for (int i = 0; i < 26; i++) {
            if (counts[i] > counts[maxCountIndex]) {
                maxCountIndex = i;
            }
        }

        // 3.先将出现次数最多的字符放在偶数索引上
        // 结果集字符数组，大小 => n
        char[] res = new char[n];
        int index = 0;
        // while 条件 => 满足反复执行某操作的条件
        while (counts[maxCountIndex] > 0) {
            // KeyPoint 转成 char 类型 => 记住这种写法
            res[index] = (char) (maxCountIndex + 'a');
            // 每次走两步，保证是偶数索引
            index += 2;
            counts[maxCountIndex]--;
        }

        // 4. 将其他的字符放到其他的位置
        // 一共有 26 个字符，逐个遍历
        for (int i = 0; i < 26; i++) {
            while (counts[i] > 0) {
                // index 有可能超过数组范围，则从索引 1 位置开始
                // 说明偶数索引位置已经被使用完了，只能从奇数位置开始
                if (index >= n) index = 1;
                res[index] = (char) (i + 'a');
                index += 2;
                counts[i]--;
            }
        }

        return new String(res);

        // 输出：
        // "a\u0000a\u0000\u0000\u0000\u0000\u0000\u0000\u0000
        //   \u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000"

        // \u0000 表示 Unicode码的字符
        // 每一个'\u0000'都代表了一个空格

        // => 说明 String 中存在空格，即 res 有空格没有被赋值
        // => res 赋值操作可能存在问题，去找 bug
    }
}
