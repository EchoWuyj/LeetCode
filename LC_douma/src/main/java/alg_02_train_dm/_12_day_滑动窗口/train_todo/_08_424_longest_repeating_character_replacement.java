package alg_02_train_dm._12_day_滑动窗口.train_todo;

/**
 * @Author Wuyj
 * @DateTime 2023-05-05 14:18
 * @Version 1.0
 */
public class _08_424_longest_repeating_character_replacement {

    /*
       leetcode 424. 替换后的最长重复字符
       给你一个仅由大写英文字母组成的字符串，
       你可以将任意位置上的字符替换成另外的字符，总共可最多替换 k 次。
       在执行上述操作后，找到包含重复字母的最长子串的长度。

       注意：字符串长度 和 k 不会超过 10^4。

       示例 1：
       输入：s = "ABAB", k = 2
       输出：4
       解释：用两个'A'替换为两个'B',反之亦然。

       示例 2：
       输入：s = "AABABBA", k = 1
       输出：4
       解释：
       将中间的一个'A'替换为'B',字符串变为 "AABBBBA"。
       子串 "BBBB" 有最长重复字母, 答案为 4。

    */
    public int characterReplacement(String s, int k) {
        int n = s.length();
        if (n < 2) {
            return n;
        }

        int res = 0;

        char[] charArray = s.toCharArray();
        int left = 0, right = 0;
        // 统计当前窗口中每个字符出现的次数
        int[] freq = new int[26];
        // 存储当前窗口中出现最多字符的出现次数
        int maxCount = 0;
        while (right < n) {
            freq[charArray[right] - 'A']++;
            maxCount = Math.max(maxCount, freq[charArray[right] - 'A']);
            // 出现最多的次数，再加上 k 都没有当前窗口长度大，说明可以缩减窗口了
            if ((right - left + 1) - maxCount > k) {
                freq[charArray[left] - 'A']--;
                left++;
            }
            res = Math.max(res, right - left + 1);

            right++;
        }

        return res;
    }
}
