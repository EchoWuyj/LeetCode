package alg_02_train_wyj._12_day_滑动窗口;

/**
 * @Author Wuyj
 * @DateTime 2023-05-07 20:29
 * @Version 1.0
 */
public class _04_76_minimum_window_substring1 {

    public String minWindow1(String s, String t) {
        int n = s.length();
        int UniqueCharsInT = 0;
        int[] cntT = new int[60];
        for (char c : t.toCharArray()) {
            if (cntT[c - 'A'] == 0) UniqueCharsInT++;
            cntT[c - 'A']++;
        }

        int[] windowCnt = new int[60];
        int matchCnt = 0;

        int[] res = {-1, 0, 0};

        int left = 0, right = 0;
        while (right < n) {
            char rightChar = s.charAt(right);
            windowCnt[rightChar - 'A']++;
            if (windowCnt[rightChar - 'A'] == cntT[rightChar - 'A']) {
                matchCnt++;
            }
            while (left <= right && matchCnt == UniqueCharsInT) {
                if (res[0] == -1 || right - left + 1 < res[0]) {
                    res[0] = right - left + 1;
                    res[1] = left;
                    res[2] = right;
                }
                char leftChar = s.charAt(left);
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

    // KeyPoint 直接定义 数组 128 => 推荐使用
    public String minWindow2(String s, String t) {
        int n = s.length();
        int[] cntT = new int[128];
        int uniqueCnt = 0;

        for (char c : t.toCharArray()) {
            if (cntT[c] == 0) uniqueCnt++;
            cntT[c]++;
        }

        int[] windowCnt = new int[128];
        int matchedCnt = 0;

        int[] res = {-1, 0, 0};

        int left = 0, right = 0;
        while (right < n) {
            char rightChar = s.charAt(right);
            windowCnt[rightChar]++;
            if (cntT[rightChar] == windowCnt[rightChar]) matchedCnt++;
            while (left <= right && uniqueCnt == matchedCnt) {
                if (res[0] == -1 || right - left + 1 < res[0]) {
                    res[0] = right - left + 1;
                    res[1] = left;
                    res[2] = right;
                }
                char leftChar = s.charAt(left);
                windowCnt[leftChar]--;
                if (windowCnt[leftChar] < cntT[leftChar] ) matchedCnt--;
                left++;
            }
            right++;
        }
        return res[0] == -1 ? "" : s.substring(res[1], res[2] + 1);
    }
}
