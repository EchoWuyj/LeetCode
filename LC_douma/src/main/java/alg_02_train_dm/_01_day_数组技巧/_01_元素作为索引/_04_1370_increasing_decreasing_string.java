package alg_02_train_dm._01_day_数组技巧._01_元素作为索引;

/**
 * @Author Wuyj
 * @DateTime 2023-04-16 15:30
 * @Version 1.0
 */
public class _04_1370_increasing_decreasing_string {

    /*
        1370. 上升下降字符串
        给你一个字符串 s ，请你根据下面的算法重新构造字符串：

        1 从 s 中选出 最小 的字符，将它 接在 结果字符串的后面。
        2 从 s 剩余字符中选出 最小 的字符，且该字符比上一个添加的字符大，将它 接在 结果字符串后面。
        3 重复步骤 2 ，直到你没法从 s 中选择字符。
        4 从 s 中选出 最大 的字符，将它 接在 结果字符串的后面。
        5 从 s 剩余字符中选出 最大 的字符，且该字符比上一个添加的字符小，将它 接在 结果字符串后面。
        6 重复步骤 5 ，直到你没法从 s 中选择字符。
        7 重复步骤 1 到 6 ，直到 s 中所有字符都已经被选过。

        在任何一步中，如果最小或者最大字符不止一个 ，你可以选择其中任意一个，并将其添加到结果字符串。
        请你返回将 s 中字符重新排序后的 结果字符串 。

        输入：s = "aaaabbbbcccc"
        输出："abccbaabccba" => abc|cba|abc|cba
        解释：第一轮的步骤 1，2，3 后，结果字符串为 result = "abc"
        第一轮的步骤 4，5，6 后，结果字符串为 result = "abccba"
        第一轮结束，现在 s = "aabbcc" ，我们再次回到步骤 1
        第二轮的步骤 1，2，3 后，结果字符串为 result = "abccbaabc"
        第二轮的步骤 4，5，6 后，结果字符串为 result = "abccbaabccba"

        输入：s = "rat"
        输出："art"
        解释：单词 "rat" 在上述算法重排序以后变成 "art"

        输入：s = "leetcode"
        输出："cdelotee"

        注意
        1 <= s.length <= 500
        s 只包含小写英文字母。
     */

    public String sortString(String s) {
        int[] counts = new int[26];
        for (char c : s.toCharArray()) {
            counts[c - 'a']++;
        }

        StringBuilder sb = new StringBuilder();
        // sb.length() = s.length() 结束循环，否则就一直执行 while 循环，不断地往 sb 中追加字符
        // 注意：sb 追加的最后一个元素是在循环里执行的，追加完之后，触发跳出 while 循环
        while (sb.length() < s.length()) {
            // 上升 => 正序遍历
            for (int i = 0; i < 26; i++) {
                if (counts[i] > 0) {
                    sb.append((char) (i + 'a'));
                    counts[i]--;
                }
            }

            // 下降 => 逆序遍历
            for (int i = 25; i >= 0; i--) {
                if (counts[i] > 0) {
                    sb.append((char) (i + 'a'));
                    counts[i]--;
                }
            }
        }

        return sb.toString();
    }
}
