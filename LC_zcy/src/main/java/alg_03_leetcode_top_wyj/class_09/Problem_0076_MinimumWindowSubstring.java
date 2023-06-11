package alg_03_leetcode_top_wyj.class_09;

/**
 * @Author Wuyj
 * @DateTime 2023-02-27 13:33
 * @Version 1.0
 */
public class Problem_0076_MinimumWindowSubstring {
    public static String minWindow(String s, String t) {
        if (s.length() < t.length()) {
            return "";
        }

        char[] str = s.toCharArray();
        char[] target = t.toCharArray();

        int[] map = new int[256];
        for (char c : target) {
            map[c]++;
        }

        int all = target.length;
        int l = 0;
        int r = 0;

        int minLen = -1;
        int ansl = -1;
        int ansr = -1;

        while (r != str.length) {
            map[str[r]]--;
            if (map[str[r]] >= 0) {
                all--;
            }
            if (all == 0) {
                while (map[str[l]] < 0) {
                    map[str[l]]++;
                    l++;
                }

                if (minLen == -1 || r - l + 1 < minLen) {
                    minLen = r - l + 1;
                    ansr = r;
                    ansl = l;
                }
                all++;
                map[str[l]]++;
                l++;
            }
            r++;
        }

        return minLen == -1 ? "" : s.substring(ansl, ansr + 1);
    }
}
