package alg_03_high_frequency._01_codetop_2024_01;

/**
 * @Author Wuyj
 * @DateTime 2024-01-12 18:14
 * @Version 1.0
 */
public class _01_03_lengthOfLongestSubstring {
    public int lengthOfLongestSubstring(String s) {

        int n = s.length();
        if (n <= 1) return n;

        int[] window = new int[128];
        int maxLen = 1;
        // left 和 right 需要同时初始化
        int left = 0, right = 0;

        while (right < n) {
            char rightChar = s.charAt(right);
            int index = window[rightChar];
            if (index == 0 || index < left) {
                left = left;
            } else {
                // right 遇到重复字符，left 移动到 index 位置
                left = index;
            }
            // 更新 maxLen
            maxLen = Math.max(maxLen, right - left + 1);
            // window 通过 rightChar 作为作为索引，所以window[] 中传入的是 rightChar
            window[rightChar] = right + 1;
            right++;
        }
        return maxLen;
    }
}
