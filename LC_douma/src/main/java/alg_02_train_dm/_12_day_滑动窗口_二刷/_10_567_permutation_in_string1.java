package alg_02_train_dm._12_day_滑动窗口_二刷;

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
        输出: True
        解释:
        "ab" 排列 "ab" 和 "ba"
        "eid|ba|ooo"
        s2 包含 s1 的排列之一 ("ba").

        示例 2：
        输入: s1= "ab" s2 = "eidboaoo"
        输出: False

        提示：
        输入的字符串只包含小写字母
        两个字符串的长度都在 [1, 10,000] 之间
     */

    // KeyPoint 方法一 字符计数[固定长度]
    // 通过对'字符'对应索引进行计数，从而判断 s2 的一个排列是否和 s1 相等
    public boolean checkInclusion1(String s1, String s2) {




        /*
            输入: s1 = "ab" s2 = "ei|dbaooo"
            ab 和 ei|dbaooo
            原则：以 s1 为比较标准，动态移动 s2

            s1:
                a->1
                b->1

            s2：
                e->1 ×
                i->1 ×
                b->1 √
                a->1 √ => 已经找到，提前返回

         */

        // s2 是否包含 s1
        int n = s1.length(), m = s2.length();
        if (n > m) {
            return false;
        }

        // 计数器
        int[] cnt1 = new int[26];
        int[] cnt2 = new int[26];

        // 先累和 n 个字符值 => s1 字符个数
        for (int i = 0; i < n; i++) {
            cnt1[s1.charAt(i) - 'a']++;
            cnt2[s2.charAt(i) - 'a']++;
        }

        // 特判：数组个数，元素次序都相等，提前返回 true
        if (Arrays.equals(cnt1, cnt2)) {
            return true;
        }

        // s1 对应 cnt1 固定不变
        // s2 对应 cnt2，调整字符，数组尾后移一位，数组头后移一位，不断和 s1 判断
        for (int i = n; i < m; i++) {
            cnt2[s2.charAt(i) - 'a']++;
            // i 从 n 开始，i-n = 0，将 cnt2 数组头后移一位
            cnt2[s2.charAt(i - n) - 'a']--;
            if (Arrays.equals(cnt1, cnt2)) {
                return true;
            }
        }
        return false;
    }
}
