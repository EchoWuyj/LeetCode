package alg_02_train_wyj._23_day_回溯算法二;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-04-10 14:41
 * @Version 1.0
 */
public class _01_401_binary_watch {
    public List<String> readBinaryWatch(int turnedOn) {
        int[] nums1 = {8, 4, 2, 1};
        int[] nums2 = {32, 16, 8, 4, 2, 1};

        List<String> res = new ArrayList<>();
        for (int i = 0; i <= turnedOn; i++) {
            List<Integer> hours = findCombinations(nums1, i);
            List<Integer> minutes = findCombinations(nums2, turnedOn - i);
            for (int hour : hours) {
                if (hour > 11) continue;
                for (int minute : minutes) {
                    if (minute > 59) continue;
                    String minuteStr = (minute < 10) ? "0" + minute : "" + minute;
                    res.add(hour + ":" + minuteStr);
                }
            }
        }
        return res;
    }

    public List<Integer> findCombinations(int[] nums, int count) {
        List<Integer> res = new ArrayList<>();
        dfs(nums, 0, 0, count, res);
        return res;
    }

    public void dfs(int[] nums, int index, int sum, int count, List<Integer> res) {
        if (index > nums.length) return;

        if (count == 0) {
            res.add(sum);
            return;
        }
        for (int i = index; i < nums.length; i++) {
            dfs(nums, i + 1, sum + nums[i], count - 1, res);
        }
    }

}
