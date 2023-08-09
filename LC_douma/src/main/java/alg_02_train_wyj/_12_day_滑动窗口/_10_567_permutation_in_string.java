package alg_02_train_wyj._12_day_滑动窗口;

import java.util.Arrays;

/**
 * @Author Wuyj
 * @DateTime 2023-05-07 15:01
 * @Version 1.0
 */
public class _10_567_permutation_in_string {

    public boolean checkInclusion1(String s1, String s2) {
        int n = s1.length(), m = s2.length();
        if (n > m) {
            return false;
        }

        int[] cnt1 = new int[26];
        int[] cnt2 = new int[26];

        for (int i = 0; i < n; i++) {
            cnt1[s1.charAt(i) - 'a']++;
            cnt2[s2.charAt(i) - 'a']++;
        }
        if (Arrays.equals(cnt1, cnt2)) return true;

        for (int i = n; i < m; i++) {
            cnt2[s2.charAt(i) - 'a']++;
            cnt2[s2.charAt(i - n) - 'a']--;
            if (Arrays.equals(cnt1, cnt2)) return true;
        }
        return false;
    }

    public boolean checkInclusion2(String s1, String s2) {
        if (s1 == null || s2 == null) return false;
        int n = s1.length(), m = s2.length();
        if (n > m) {
            return false;
        }
        int[] cnt = new int[26];
        for (int i = 0; i < n; i++) {
            cnt[s1.charAt(i) - 'a']++;
        }
        int left = 0, right = 0;
        while (right < m) {
            int index = s2.charAt(right) - 'a';
            cnt[index]--;
            while (cnt[index] < 0) {
                cnt[s2.charAt(left) - 'a']++;
                left++;
            }
            if (right - left + 1 == n) return true;
            right++;
        }
        return false;
    }

    public static void main(String[] args) {
        int num = 'a';
        System.out.println(num);
    }
}
