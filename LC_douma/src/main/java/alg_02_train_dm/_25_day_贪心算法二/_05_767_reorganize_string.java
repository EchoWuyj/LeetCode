package alg_02_train_dm._25_day_贪心算法二;

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

        注意:
        S 只包含小写字母并且长度在[1, 500]区间内。

     */

    // 贪心:只是贪心地考虑，字符串中出现次数最多字符，将其排列不相邻
    //      因为将其排列不相邻的同时，也能保证其他字符是不相邻的
    public String reorganizeString(String s) {
        // 1. 统计每个字符出现的次数
        char[] chars = s.toCharArray();
        int n = chars.length;
        int[] count = new int[26];
        for (char c : chars) {
            // index 字母对应索引位置
            // count[index] 字母出现次数
            int index = c - 'a';
            count[index]++;
            // 某个字符出现的个数 > 数组一半，则不可行，返回 ""
            // 奇数和偶数，统一使用 (n+1)/2 计算
            if (count[index] > (n + 1) / 2) return "";
        }

        // 2. 拿到出现次数最多的字符
        int maxCountIndex = 0;
        for (int i = 0; i < 26; i++) {
            if (count[i] > count[maxCountIndex]) {
                maxCountIndex = i;
            }
        }

        // 3. 先将出现次数最多的字符放在偶数索引上
        char[] res = new char[n];
        int index = 0;
        // KeyPoint  while 条件 => 满足反复执行某操作的条件
        while (count[maxCountIndex] > 0) {
            // 转成 char 类型
            res[index] = (char) (maxCountIndex + 'a');
            index += 2;
            count[maxCountIndex]--;
        }

        // 4. 将其他的字符放到其他的位置
        for (int i = 0; i < 26; i++) {
            while (count[i] > 0) {
                // index 超过数组范围，则从索引 1 位置开始
                if (index >= n) index = 1;
                res[index] = (char) (i + 'a');
                index += 2;
                count[i]--;
            }
        }

        return new String(res);
    }
}
