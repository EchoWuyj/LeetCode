package alg_02_train_dm._24_day_贪心算法一;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-04-11 17:44
 * @Version 1.0
 */
public class _04_45_JumpGameII1 {
    /*
     跳跃游戏II
     给定一个整数数组，你最初位于数组的第一个位置
     数组中的每个元素代表在该位置可以跳跃的最大长度
     你的目标是使用 最少 的跳跃次数达到数组的最后一个位置

     输入: nums = [2,3,1,1,4]
     输出: 2
     解释: 跳到最后一个位置的最小跳跃数是 2。
          从下标为 0 跳到下标为 1 的位置，跳 1 步，然后跳 3 步到达数组的最后一个位置。

  */

    private int minSteps = Integer.MAX_VALUE;

    // KeyPoint DFS 超出时间限制
    // 跳跃游戏 => 抽象成树形结构 => 求抽象树形结构的最短路径
    public int jump(int[] nums) {
        dfs(nums, 0, new ArrayList<>());
        return minSteps == Integer.MAX_VALUE ? 0 : minSteps;
    }

    // jumpedIndex 已经跳到的下标
    private void dfs(int[] nums, int jumpedIndex, List<Integer> path) {
        // jumpedIndex 需要在 nums.length - 1 位置来计算 minSteps，所以不能是递归的边界不是 nums.length
        if (jumpedIndex == nums.length - 1) {
            minSteps = Math.min(minSteps, path.size());
            return;
        }

        // nums[jumpedIndex] 可以跳的步数，步数从 1 开始跳，且 <= nums[jumpedIndex]，是可以取等，本质:多叉树
        for (int i = 1; i <= nums[jumpedIndex]; i++) {
            // 剪枝 => 当前 jumpedIndex 位置 + 跳跃步数 i >= nums.length 直接跳过
            if (jumpedIndex + i >= nums.length) continue;
            path.add(i);
            // jumpedIndex + i，表示跳到下一步所在的位置
            dfs(nums, jumpedIndex + i, path);
            path.remove(path.size() - 1);
        }
    }

    public static void main(String[] args) {
        int[] nums = {2, 3, 1, 1, 4};
        System.out.println(new alg_02_train_wyj._24_day_贪心算法一._04_45_JumpGame1().jump(nums));
        // 2
    }
}
