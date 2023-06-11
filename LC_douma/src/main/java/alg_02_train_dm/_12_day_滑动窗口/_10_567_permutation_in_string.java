package alg_02_train_dm._12_day_滑动窗口;

import java.util.Arrays;

/**
 * @Author Wuyj
 * @DateTime 2023-05-05 20:13
 * @Version 1.0
 */
public class _10_567_permutation_in_string {
      /* 
        leetcode 567. 字符串的排列
        给定两个字符串 s1 和 s2，写一个函数来判断 s2 是否包含 s1 的排列。
        换句话说，'第一个字符串的排列之一'是第二个字符串的 子串 。
    
        示例 1：
        输入: s1 = "ab" s2 = "eidbaooo"
        KeyPoint "ab" 排列 "ab" 和 "ba"
        输出: True
        解释: s2 包含 s1 的排列之一 ("ba").
    
        示例 2：
        输入: s1= "ab" s2 = "eidboaoo"
        输出: False

        提示：
        输入的字符串只包含小写字母
        两个字符串的长度都在 [1, 10,000] 之间
     */

    // KeyPoint 方法一 字符计数
    public boolean checkInclusion1(String s1, String s2) {
        /*
            输入: s1 = "ab" s2 = "ei|dbaooo"
            ab 和 ei|dbaooo
            s1:
                a->1
                b->1
            s2：
                b->1
                a->1
         */

        int n = s1.length(), m = s2.length();
        if (n > m) {
            return false;
        }

        // 计数器
        int[] cnt1 = new int[26];
        int[] cnt2 = new int[26];
        // 先累和 n 个字符值
        for (int i = 0; i < n; i++) {
            ++cnt1[s1.charAt(i) - 'a'];
            ++cnt2[s2.charAt(i) - 'a'];
        }

        // 数组个数，元素都相等，返回 true
        if (Arrays.equals(cnt1, cnt2)) {
            return true;
        }

        // s1 固定不变
        // s2 调整字符，后移一位，前移一位，不断和 s1 判断
        for (int i = n; i < m; i++) {
            ++cnt2[s2.charAt(i) - 'a'];
            --cnt2[s2.charAt(i - n) - 'a'];
            // 通过累加'字符'对应索引值，从而判断 s2 的一个排列是否和 s1 相等
            // KeyPoint 通过字符计数方式从而避开了字符位置不同造成不同排列的影响
            if (Arrays.equals(cnt1, cnt2)) {
                return true;
            }
        }
        return false;
    }

    // KeyPoint 方法二 滑动窗口
    public boolean checkInclusion(String s1, String s2) {
        if (s1 == null || s2 == null) return false;
        int n = s1.length(), m = s2.length();
        if (n > m) {
            return false;
        }

        // 先统计字符串 s1 中每个字符出现的次数
        int[] cnt = new int[26];
        for (int i = 0; i < n; ++i) {
            ++cnt[s1.charAt(i) - 'a'];
        }

        // 维护窗口，使得 s1 字符计数为 0，并且 s2 窗口大小和 s1 窗口大小相同，则说明找到了
        int left = 0, right = 0;
        while (right < m) {
            // KeyPoint 字符 -> 索引
            // int x = s2.charAt(right) ×
            // 不能直接使用字符对应 ascii 码，数组越界，必须 -'a'，才能保证在 int[26] 中
            int x = s2.charAt(right) - 'a';
            // 右移 right，将字符包括在窗口中，计数减 1
            cnt[x]--;
            // cnt[x] < 0，则说明 x 字符不是 s1 中，则需要右移 left
            // 同时，右移 left，使得不在窗口中的字符，计数需要加 1
            while (cnt[x] < 0) {
                // 通过缩减窗口使得 cnt[x] 不为负数
                cnt[s2.charAt(left) - 'a']++;
                left++;
            }
            // 到现在为止，当前窗口中字符的 cnt 值都为 0(不包含 s1 里面的字符)
            // 如果窗口的长度等于 n 的话，那么当前窗口中的 cnt 的值都是 0
            if (right - left + 1 == n) return true;
            right++;
        }
        return false;
    }
}
