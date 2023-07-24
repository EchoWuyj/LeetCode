package alg_02_train_dm._12_day_滑动窗口;

import java.util.Arrays;

/**
 * @Author Wuyj
 * @DateTime 2023-05-05 20:13
 * @Version 1.0
 */
public class _10_567_permutation_in_string1 {
      /* 
        567. 字符串的排列
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


}
