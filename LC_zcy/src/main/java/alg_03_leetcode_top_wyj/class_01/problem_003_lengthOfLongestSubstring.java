package alg_03_leetcode_top_wyj.class_01;

/**
 * @Author Wuyj
 * @DateTime 2023-02-17 19:37
 * @Version 1.0
 */
public class problem_003_lengthOfLongestSubstring {
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        char[] chars = s.toCharArray();

        int[] map = new int[256];
        for (int i = 0; i < map.length; i++) {
            map[i] = -1;
        }

        int len = 0;
        int pre = -1;
        int cur = 0;

        for (int i = 0; i < chars.length; i++) {
            pre = Math.max(pre, map[chars[i]]);
            cur = i - pre;
            len = Math.max(cur, len);
            map[chars[i]] = i;
        }
        return len;
    }
}
