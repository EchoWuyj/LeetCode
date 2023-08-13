package alg_02_train_wyj._24_day_贪心算法一;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-04-12 14:31
 * @Version 1.0
 */
public class _04_45_JumpGame1 {

    private int minStep = Integer.MAX_VALUE;

    public int jump(int[] nums) {
        if (nums.length == 1) return 0;
        dfs(nums, 0, new ArrayList<>());
        return minStep == Integer.MAX_VALUE ? 0 : minStep;
    }

    private void dfs(int[] nums, int index, List<Integer> list) {
        if (index == nums.length - 1) {
            minStep = Math.min(minStep, list.size());
            return;
        }

        for (int i = 1; i <= nums[index]; i++) {
            if (index + i >= nums.length) continue;
            list.add(1);
            dfs(nums, index + i, list);
            list.remove(list.size() - 1);
        }
    }

    public static void main(String[] args) {
        int[] nums = {2, 3, 1, 1, 4};
        System.out.println(new _04_45_JumpGame1().jump(nums));
    }
}
