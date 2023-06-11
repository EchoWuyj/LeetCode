package alg_02_train_dm._22_day_回溯算法一;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-04-07 15:09
 * @Version 1.0
 */
public class _03_46_Permutations2 {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        dfs(nums, path, res);
        return res;
    }

    // KeyPoint 回溯代码框架 => 重点掌握  => 本质:对'树'的深度优先遍历
    private void dfs(int[] nums, List<Integer> path, List<List<Integer>> res) {
        // KeyPoint 优化，递归边界，将两个 if 进行合并
        if (path.size() == nums.length) {
            res.add(new ArrayList<>(path));
            return;
        }
        // 遍历相邻节点
        for (int i = 0; i < nums.length; i++) {
            // path 添加一个节点
            path.add(nums[i]);
            dfs(nums, path, res);
            // 修改回溯位置，在 for 循环位置中回溯，且在回溯过程中，将当前的节点从 path 中删除
            path.remove(path.size() - 1);
        }
    }

    public static void main(String[] args) {
        System.out.println(new _03_46_Permutations2().permute(new int[]{1, 2, 3}));
        // [[1, 1, 1], [1, 1, 2], [1, 1, 3], [1, 2, 1], [1, 2, 2], [1, 2, 3], [1, 3, 1],
        // [1, 3, 2], [1, 3, 3], [2, 1,1], [2, 1, 2], [2, 1, 3], [2, 2, 1], [2, 2, 2],
        // [2, 2, 3], [2, 3, 1], [2, 3, 2], [2, 3, 3], [3, 1, 1], [3,1, 2], [3, 1, 3],
        // [3, 2, 1], [3, 2, 2], [3, 2, 3], [3, 3, 1], [3, 3, 2], [3, 3, 3]]
    }
}
