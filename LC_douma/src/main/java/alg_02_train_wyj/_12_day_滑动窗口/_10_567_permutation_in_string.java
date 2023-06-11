package alg_02_train_wyj._12_day_滑动窗口;

import java.util.Arrays;

/**
 * @Author Wuyj
 * @DateTime 2023-05-07 15:01
 * @Version 1.0
 */
public class _10_567_permutation_in_string {

    public boolean checkInclusion1(String s1, String s2) {
        if (s1 == null || s2 == null) return false;
        int n = s1.length(), m = s2.length();
        if (n > m) return false;

        int[] count1 = new int[26];
        int[] count2 = new int[26];

        for (int i = 0; i < n; i++) {
            count1[s1.charAt(i) - 'a']++;
            count2[s2.charAt(i) - 'a']++;
        }

        if (Arrays.equals(count1, count2)) {
            return true;
        }

        for (int i = n; i < m; i++) {
            count2[s2.charAt(i) - 'a']++;
            count2[s2.charAt(i - n) - 'a']--;
            if (Arrays.equals(count1, count2)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkInclusion2(String s1, String s2) {
        if (s1 == null || s2 == null) return false;
        int n = s1.length(), m = s2.length();
        if (n > m) return false;
        int[] count = new int[26];
        for (int i = 0; i < n; i++) {
            count[s1.charAt(i) - 'a']++;
        }
        int left = 0, right = 0;
        int len = s2.length();
        while (right < len) {
            int cur = s2.charAt(right) - 'a';
            count[cur]--;
            while (count[cur] < 0) {
                count[s2.charAt(left) - 'a']++;
                left++;
            }
            if (right - left + 1 == n) return true;
            right++;
        }
        return false;
    }
}
