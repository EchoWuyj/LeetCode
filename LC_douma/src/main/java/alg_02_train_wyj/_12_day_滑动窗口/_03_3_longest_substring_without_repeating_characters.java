package alg_02_train_wyj._12_day_滑动窗口;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @Author Wuyj
 * @DateTime 2023-05-04 18:58
 * @Version 1.0
 */
public class _03_3_longest_substring_without_repeating_characters {
    public int lengthOfLongestSubstring1(String s) {
        int n = s.length();
        if (n <= 1) return n;
        int maxLen = 1;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (allUnique(s, i, j)) {
                    maxLen = Math.max(maxLen, j - i + 1);
                }
            }
        }
        return maxLen;
    }

    public boolean allUnique(String s, int start, int end) {
        Set<Character> set = new HashSet<>();
        for (int i = start; i <= end; i++) {
            if (set.contains(s.charAt(i))) {
                return false;
            }
            set.add(s.charAt(i));
        }
        return true;
    }

    public int lengthOfLongestSubstring(String s) {
        int n = s.length();
        if (n <= 1) return n;
        int left = 0, right = 0;
        int maxLen = 1;
        Set<Character> set = new HashSet<>();
        while (right < n) {
            char rightChar = s.charAt(right);
            while (set.contains(rightChar)) {
                set.remove(s.charAt(left));
                left++;
            }
            maxLen = Math.max(maxLen, right - left + 1);
            set.add(rightChar);
            right++;
        }
        return maxLen;
    }

    public int lengthOfLongestSubstring2(String s) {
        if (s == null) return 0;
        int left = 0, right = 0;
        int n = s.length();
        int maxLen = 0;
        Map<Character, Integer> map = new HashMap<>();
        while (right < n) {
            char rightChar = s.charAt(right);
            int rightIndex = map.getOrDefault(rightChar, -1);
            if (left == -1 || rightIndex < left) {
            } else {
                left = rightIndex + 1;
            }
            maxLen = Math.max(maxLen, right - left + 1);
            map.put(rightChar, right);
            right++;
        }
        return maxLen;
    }

    public static int lengthOfLongestSubstring3(String s) {
        if (s == null) return 0;
        int left = 0, right = 0;
        int n = s.length();
        int maxLen = 0;
        int[] map = new int[128];
        while (right < n) {
            char rightChar = s.charAt(right);
            int rightIndex = map[rightChar];
            if (rightIndex == 0 || rightIndex < left) {
            } else {
                left = rightIndex;
            }
            maxLen = Math.max(maxLen, right - left + 1);
            map[rightChar] = right + 1;
            right++;
        }
        return maxLen;
    }

    public static void main(String[] args) {
        int res = lengthOfLongestSubstring3("abcabcbb");
    }
}
