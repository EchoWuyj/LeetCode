package alg_02_train_wyj._29_day_动态规划四;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-06-15 16:18
 * @Version 1.0
 */
public class _07_354_russian_doll_envelopes {
    public int maxEnvelopes(int[][] envelopes) {
        int n = envelopes.length;
        if (n <= 1) return n;

        Arrays.sort(envelopes, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] != o2[0]) {
                    return o1[0] - o2[0];
                } else {
                    return o2[1] - o1[1];
                }
            }
        });

        int maxLen = 1;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (envelopes[i][1] > envelopes[j][1]) {
                    dp[i] = Math.max(dp[j] + 1, dp[i]);
                    maxLen = Math.max(maxLen, dp[i]);
                }
            }
        }
        return maxLen;
    }

    public int maxEnvelopes1(int[][] envelopes) {
        int n = envelopes.length;
        if (n <= 1) return n;
        Arrays.sort(envelopes, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] != o2[0]) {
                    return o1[0] - o2[0];
                } else {
                    return o2[1] - o1[1];
                }
            }
        });

        List<Integer> res = new ArrayList<>();
        res.add(envelopes[0][1]);
        for (int i = 1; i < n; i++) {
            int curHeight = envelopes[i][1];
            if (curHeight > res.get(res.size() - 1)) {
                res.add(curHeight);
            } else {
                int index = BS(res, curHeight);
                res.set(index, curHeight);
            }
        }
        return res.size();
    }

    // 找第一个'大于等于' target 的元素的下标
    public int BS(List<Integer> res, int target) {
        int left = 0, right = res.size() - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (target > res.get(mid)) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }


}
