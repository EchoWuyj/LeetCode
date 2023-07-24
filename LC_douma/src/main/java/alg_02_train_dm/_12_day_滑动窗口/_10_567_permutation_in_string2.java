package alg_02_train_dm._12_day_滑动窗口;

/**
 * @Author Wuyj
 * @DateTime 2023-07-24 21:01
 * @Version 1.0
 */
public class _10_567_permutation_in_string2 {

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
