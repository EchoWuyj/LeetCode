package alg_02_train_wyj._24_day_贪心算法一;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-04-12 14:31
 * @Version 1.0
 */
public class _04_45_JumpGame1 {

    private int minSteps = Integer.MAX_VALUE;

    public int jump(int[] nums) {
        dfs(nums, 0, new ArrayList<>());
        return minSteps == Integer.MAX_VALUE ? 0 : minSteps;
    }

    private void dfs(int[] nums, int jumpedIndex, List<Integer> path) {
        if (jumpedIndex == nums.length - 1) {
            minSteps = Math.min(minSteps, path.size());
            return;
        }

        for (int i = 1; i <= nums[jumpedIndex]; i++) {
            if (jumpedIndex + i >= nums.length) continue;
            path.add(i);
            dfs(nums, jumpedIndex + i, path);
            path.remove(path.size() - 1);
        }
    }

    public static void main(String[] args) {
        int[] nums = {2, 3, 1, 1, 4};
        System.out.println(new _04_45_JumpGame1().jump(nums));
    }
}
