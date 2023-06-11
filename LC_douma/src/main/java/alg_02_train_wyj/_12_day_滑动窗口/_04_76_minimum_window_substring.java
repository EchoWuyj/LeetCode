package alg_02_train_wyj._12_day_滑动窗口;

import java.util.ArrayList;

/**
 * @Author Wuyj
 * @DateTime 2023-05-07 20:29
 * @Version 1.0
 */
public class _04_76_minimum_window_substring {

    public String minWindow1(String s, String t) {
        if (s == null || t == null) return "";
        int n = s.length();
        int[] cntT = new int[60];
        int UniqueCharInT = 0;

        for (int i = 0; i < t.length(); i++) {
            if (cntT[t.charAt(i) - 'A'] == 0) UniqueCharInT++;
            cntT[t.charAt(i) - 'A']++;
        }

        int[] res = {-1, 0, 0};
        int left = 0, right = 0;
        int[] windowCntS = new int[60];
        int matchedChars = 0;

        while (right < n) {
            char rightChar = s.charAt(right);
            int rightCharIndex = rightChar - 'A';
            windowCntS[rightCharIndex]++;

            if (windowCntS[rightCharIndex] == cntT[rightCharIndex]) {
                matchedChars++;
            }

            while (left <= right && matchedChars == UniqueCharInT) {
                if (res[0] == -1 || right - left + 1 < res[0]) {
                    res[0] = right - left + 1;
                    res[1] = left;
                    res[2] = right;
                }

                char leftChar = s.charAt(left);
                int leftCharIndex = leftChar - 'A';
                windowCntS[leftCharIndex]--;
                if (windowCntS[leftCharIndex] < cntT[leftCharIndex]) {
                    matchedChars--;
                }
                left++;
            }
            right++;
        }
        return res[0] == -1 ? "" : s.substring(res[1], res[2] + 1);
    }

    public String minWindow2(String s, String t) {
        if (s == null || t == null) return "";
        int[] cntT = new int[60];
        int UniqueCharInT = 0;
        for (char c : t.toCharArray()) {
            if (cntT[c - 'A'] == 0) UniqueCharInT++;
            cntT[c - 'A']++;
        }

        ArrayList<Pair<Integer, Character>> filteredS = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (cntT[c - 'A'] > 0) {
                filteredS.add(new Pair<>(i, c));
            }
        }

        int left = 0, right = 0;
        int[] windowCntS = new int[60];
        int matchedChars = 0;
        int size = filteredS.size();
        int[] ans = {-1, 0, 0};

        while (right < size) {
            char rightChar = filteredS.get(right).getValue();
            int rightCharIndex = rightChar - 'A';
            windowCntS[rightCharIndex]++;
            if (windowCntS[rightCharIndex] == cntT[rightCharIndex]) {
                matchedChars++;
            }

            while (left <= right && matchedChars == UniqueCharInT) {
                int end = filteredS.get(right).getKey();
                int start = filteredS.get(left).getKey();
                if (ans[0] == -1 || end - start + 1 < ans[0]) {
                    ans[0] = end - start + 1;
                    ans[1] = start;
                    ans[2] = end;
                }

                char leftChar = filteredS.get(left).getValue();
                int leftCharIndex = leftChar - 'A';
                windowCntS[leftCharIndex]--;
                if (windowCntS[leftCharIndex] < cntT[leftCharIndex]) {
                    matchedChars--;
                }
                left++;
            }
            right++;
        }
        return ans[0] == -1 ? "" : s.substring(ans[1], ans[2] + 1);
    }

    private class Pair<K, V> {
        K key;
        V value;

        public Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }
    }
}
