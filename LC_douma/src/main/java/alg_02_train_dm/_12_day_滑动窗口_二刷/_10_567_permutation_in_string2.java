package alg_02_train_dm._12_day_滑动窗口_二刷;

/**
 * @Author Wuyj
 * @DateTime 2023-07-24 21:01
 * @Version 1.0
 */
public class _10_567_permutation_in_string2 {

    // KeyPoint 方法二 滑动窗口
    public boolean checkInclusion(String s1, String s2) {

        // s1：a b   a => 1
        //           b => 1

        //   right
        //     ↓
        // s2：a i d b a o o o
        //     ↑
        //    left

        // s2 包含 s1 的排列条件：
        // 1.维护 window，直到 a 和 b 对应值为 0
        // 2.且 window 长度为 s1 长度

        if (s1 == null || s2 == null) return false;
        int n = s1.length(), m = s2.length();
        if (n > m) {
            return false;
        }

        // 先统计字符串 s1 中每个字符出现的次数
        int[] cnt = new int[26];
        for (int i = 0; i < n; ++i) {
            cnt[s1.charAt(i) - 'a']++;
        }

        // 维护窗口
        // 1.使得 s1 字符计数为 0
        // 2.且 s2 窗口大小和 s1 窗口大小相同
        // => 则说明找到了，返回 true
        int left = 0, right = 0;
        while (right < m) {

            // KeyPoint 区别：字母 ASCII 码 和 字母索引
            // int index = s2.charAt(right) => 字母 ASCII
            // 如：'a' => 97
            // => 不能直接使用字母对应 ASCII 码，否则数组越界
            // => 必须 s2.charAt(right)-'a'，才能保证是索引 index，其范围在 int[26] 中

            int index = s2.charAt(right) - 'a';
            // right 指向字符，在窗口中，计数减 1
            cnt[index]--;
            // 1.若 cnt[index] < 0，则说明 index 字符不是 s1 中，则需要右移 left
            //   通过缩减窗口使得 cnt[index] 不为负数，变成 0，结束循环
            // 2.同时，右移 left，使得该字符不在窗口中，该字符计数需要加 1
            while (cnt[index] < 0) {
                // 先计数 +1，再去右移 left
                cnt[s2.charAt(left) - 'a']++;
                left++;
            }
            // 执行计算
            // 1.s1 字符计数 cnt 为 0
            // 2.s2 窗口大小和 s1 窗口大小相同
            if (right - left + 1 == n) return true;
            // 右移 right
            right++;
        }
        return false;
    }
}
