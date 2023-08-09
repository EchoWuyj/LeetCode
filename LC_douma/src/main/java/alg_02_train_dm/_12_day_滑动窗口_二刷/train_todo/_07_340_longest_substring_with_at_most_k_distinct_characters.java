package alg_02_train_dm._12_day_滑动窗口_二刷.train_todo;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author Wuyj
 * @DateTime 2023-05-05 14:17
 * @Version 1.0
 */
public class _07_340_longest_substring_with_at_most_k_distinct_characters {

     /*
         leetcode 340. 至多包含 K 个不同字符的最长子串
         备注： 159 号算法题的升级版
         给定一个字符串 s ，找出 至多 包含 k 个不同字符的最长子串 T。

         示例 1:
         输入: s = "eceba", k = 2
         输出: 3
         解释: 则 T 为 "ece"，所以长度为 3。

         示例 2:
         输入: s = "aa", k = 1
         输出: 2
         解释: 则 T 为 "aa"，所以长度为 2。

     */

    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        int ans = 0;

        int left = 0, right = 0;
        // 维护当前窗口中每个字符出现的次数
        Map<Character, Integer> windowCharCnt = new HashMap<>();
        while (right < s.length()) {
            char currChar = s.charAt(right);
            windowCharCnt.put(currChar, windowCharCnt.getOrDefault(currChar, 0) + 1);

            while (left <= right && windowCharCnt.size() > k) { // 这里改成 k 即可
                char leftChar = s.charAt(left);
                windowCharCnt.put(leftChar, windowCharCnt.getOrDefault(leftChar, 0) - 1);
                if (windowCharCnt.get(leftChar) <= 0) {
                    windowCharCnt.remove(leftChar);
                }
                left++;
            }

            ans = Math.max(ans, right - left + 1);
            right++;
        }
        return ans;
    }
}
