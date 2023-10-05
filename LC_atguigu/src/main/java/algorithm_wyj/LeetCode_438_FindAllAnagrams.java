package algorithm_wyj;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-09-25 16:21
 * @Version 1.0
 */
public class LeetCode_438_FindAllAnagrams {

    // 滑动窗口
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new ArrayList<>();
        int n = s.length();
        int[] cntStrP = new int[128];
        int uniqueCnt = 0;

        for (char c : p.toCharArray()) {
            if (cntStrP[c] == 0) uniqueCnt++;
            cntStrP[c]++;
        }

        int matchCnt = 0;
        int[] windowCnt = new int[128];
        int left = 0, right = 0;

        while (right < n) {
            char rightChar = s.charAt(right);
            windowCnt[rightChar]++;
            if (windowCnt[rightChar] == cntStrP[rightChar]) {
                matchCnt++;
            }
            while (left <= right && matchCnt == uniqueCnt) {
                // 保证长度
                if (right - left + 1 == p.length()) res.add(left);
                char leftChar = s.charAt(left);
                windowCnt[leftChar]--;
                if (windowCnt[leftChar] < cntStrP[leftChar]) {
                    matchCnt--;
                }
                left++;
            }

            right++;
        }
        return res;
    }
}
