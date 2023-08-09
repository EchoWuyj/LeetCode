package alg_02_train_wyj._12_day_滑动窗口;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-05-07 16:50
 * @Version 1.0
 */
public class _11_763_partition_labels {

    public List<Integer> partitionLabels(String s) {
        int[] map = new int[26];
        int n = s.length();
        for (int i = 0; i < n; i++) {
            map[s.charAt(i) - 'a'] = i;
        }

        List<Integer> res = new ArrayList<>();
        int left = 0, right = 0;
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            right = Math.max(right, map[c - 'a']);
            if (i == right) {
                res.add(right - left + 1);
                left = right + 1;
            }
        }
        return res;
    }
}
