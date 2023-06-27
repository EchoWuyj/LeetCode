package alg_02_train_dm._22_day_回溯算法一_2刷;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-04-07 15:09
 * @Version 1.0
 */
public class _03_46_Permutations2 {

    // 非全排列代码
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        dfs(nums, path, res);
        return res;
    }

    // KeyPoint 回溯代码框架 => 重点掌握 => 本质:对'树'的深度优先遍历
    private void dfs(int[] nums,
                     List<Integer> path, List<List<Integer>> res) {

        // 优化：递归边界，将两个 if 进行合并
        // => 退出机制
        if (path.size() == nums.length) {
            res.add(new ArrayList<>(path));
            return;
        }

        // 遍历相邻节点 =>
        // 将 index 省略，遍历固定分支
        // for 循环处理一层的节点(分支)
        for (int i = 0; i < nums.length; i++) {
            // path 添加一个节点
            path.add(nums[i]);
            dfs(nums, path, res);
            // 修改回溯位置，在 for 循环位置中进行回溯
            // 且在回溯过程中，将当前的节点从 path 中删除
            // 一般在回溯中，进行相反操作(清除现场)
            path.remove(path.size() - 1);
        }

        /*
            KeyPoint 注意事项
            for 循环等价于：多个分支的 dfs 代码，因为这里是 3 叉树，故有 3 次 dfs
            区别：二叉树 dfs，一般不会在 left 之后回溯，而是在 right之后，整体回溯

            path.add(nums[0]);
            dfs(nums, 0, path, res);
            path.remove(path.size() - 1);

            path.add(nums[1]);
            dfs(nums, 0, path, res);
            path.remove(path.size() - 1);

            path.add(nums[2]);
            dfs(nums, 0, path, res);
            path.remove(path.size() - 1);

         */
    }

    public static void main(String[] args) {
        System.out.println(new _03_46_Permutations2().permute(new int[]{1, 2, 3}));

        // [[1, 1, 1], [1, 1, 2], [1, 1, 3],
        //  [1, 2, 1], [1, 2, 2], [1, 2, 3],
        //  [1, 3, 1], [1, 3, 2], [1, 3, 3],
        //  [2, 1, 1], [2, 1, 2], [2, 1, 3],
        //  [2, 2, 1], [2, 2, 2], [2, 2, 3],
        //  [2, 3, 1], [2, 3, 2], [2, 3, 3],
        //  [3, 1, 1], [3, 1, 2], [3, 1, 3],
        //  [3, 2, 1], [3, 2, 2], [3, 2, 3],
        //  [3, 3, 1], [3, 3, 2], [3, 3, 3]]
    }
}
