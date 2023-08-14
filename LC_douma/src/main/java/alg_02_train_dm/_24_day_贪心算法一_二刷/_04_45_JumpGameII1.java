package alg_02_train_dm._24_day_贪心算法一_二刷;

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

        提示:
        1 <= nums.length <= 10^4
        0 <= nums[i] <= 1000
        题目保证可以到达 nums[n-1]

  */

    private int minSteps = Integer.MAX_VALUE;

    // KeyPoint 方法一 DFS => 超时
    // 跳跃游戏 => 抽象成树形结构
    // 每次选择可以看成一个分支 => 求抽象树形结构的最短路径
    public int jump(int[] nums) {
        dfs(nums, 0, new ArrayList<>());
        return minSteps == Integer.MAX_VALUE ? 0 : minSteps;
    }

    // index 已经跳到的下标
    private void dfs(int[] nums, int index, List<Integer> path) {
        // index 在数组最后一个位置 nums.length-1 触发计算 minSteps
        // 注意：这里递归边界不是 nums.length
        if (index == nums.length - 1) {
            // 更新 minSteps
            minSteps = Math.min(minSteps, path.size());
            // KeyPoint 记得加上 return;
            return;
        }

        // KeyPoint 本质：多叉树
        // i 表示跳跃步数范围：[1,nums[index]]
        for (int i = 1; i <= nums[index]; i++) {
            // 优化：剪枝
            // 当前 index 位置 + 跳跃步数 i >= nums.length 直接跳过
            if (index + i >= nums.length) continue;
            path.add(i);
            // index + i，表示跳到下一步所在的位置
            dfs(nums, index + i, path);
            path.remove(path.size() - 1);
        }
    }

    public static void main(String[] args) {
        int[] nums = {2, 3, 1, 1, 4};
        System.out.println(new _04_45_JumpGameII1().jump(nums));
        // 2
    }
}
