package alg_02_train_wyj._12_day_滑动窗口;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-07-25 14:48
 * @Version 1.0
 */
public class _04_76_minimum_window_substring2 {

    public String minWindow1(String s, String t) {
        int n = s.length();
        int UniqueCharsInT = 0;
        int[] cntT = new int[60];
        for (char c : t.toCharArray()) {
            if (cntT[c - 'A'] == 0) UniqueCharsInT++;
            cntT[c - 'A']++;
        }

        List<Pair<Integer, Character>> filteredS = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (cntT[c - 'A'] > 0) {
                filteredS.add(new Pair<>(i, c));
            }
        }

        int[] windowCnt = new int[60];
        int matchCnt = 0;

        int[] res = {-1, 0, 0};

        int left = 0, right = 0;
        while (right < filteredS.size()) {
            Character rightChar = filteredS.get(right).value;
            windowCnt[rightChar - 'A']++;
            if (windowCnt[rightChar - 'A'] == cntT[rightChar - 'A']) {
                matchCnt++;
            }
            while (left <= right && matchCnt == UniqueCharsInT) {
                int end = filteredS.get(right).getKey();
                int start = filteredS.get(left).getKey();

                if (res[0] == -1 || end - start + 1 < res[0]) {
                    res[0] = end - start + 1;
                    res[1] = start;
                    res[2] = end;
                }
                char leftChar = filteredS.get(left).getValue();
                windowCnt[leftChar - 'A']--;
                if (windowCnt[leftChar - 'A'] < cntT[leftChar - 'A']) {
                    matchCnt--;
                }
                left++;
            }
            right++;
        }
        return res[0] == -1 ? "" : s.substring(res[1], res[2] + 1);
    }

    public class Pair<K, V> {
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
