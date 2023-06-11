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
        int[] num1 = {8, 4, 2, 1};
        int[] num2 = {32, 16, 8, 4, 2, 1};

        List<String> res = new ArrayList<>();
        for (int i = 0; i <= turnedOn; i++) {
            List<Integer> hours = findCombinations(num1, i);
            List<Integer> minutes = findCombinations(num2, turnedOn - i);
            for (int hour : hours) {
                if (hour > 11) continue;
                for (int minute : minutes) {
                    if (minute > 59) continue;
                    String minuteStr = (minute < 10) ? "0" + minute : minute + "";
                    res.add(hour + ":" + minuteStr);
                }
            }
        }
        return res;
    }

    private List<Integer> findCombinations(int[] nums, int count) {
        List<Integer> res = new ArrayList<>();
        dfs(nums, count, 0, 0, res);
        return res;
    }

    private void dfs(int[] nums, int count, int sum, int start, List<Integer> res) {
        if (count == 0) {
            res.add(sum);
            return;
        }
        for (int i = start; i < nums.length; i++) {
            dfs(nums, count - 1, sum + nums[i], i + 1, res);
        }
    }
}
