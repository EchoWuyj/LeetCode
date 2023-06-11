package alg_02_train_dm._22_day_回溯算法一;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-04-07 15:10
 * @Version 1.0
 */
public class _03_46_Permutations4 {
    // O(n! * n)
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        // 通过数组来记录 nums 中对应的下标的元素是否被使用过
        boolean[] used = new boolean[nums.length];
        dfs(nums, path, res, used);
        return res;
    }

    // 递归过程，额外信息通过形参方式传入，尽量不要通过成员变量方式传入
    // 将多个方法都使用到的变量定义成全局变量是可以，但不是很规范
    private void dfs(int[] nums, List<Integer> path,
                     List<List<Integer>> res, boolean[] used) { // O(n)
        if (path.size() == nums.length) {
            res.add(new ArrayList<>(path));
            return;
        }
        // KeyPoint
        //  一开始的 root(-1) 没有进行任何处理操作
        //  通过 for 循环直接处理 [0,1,2] 索引对应节点
        for (int i = 0; i < nums.length; i++) { // O(n)
            // 剪枝，判断之前是否已经使用过该数字，避免重复使用
            // if (used[i] == true) continue; used[i] 本身就是 boolean，故可以简化
            if (used[i]) continue; // O(1)
            path.add(nums[i]);
            // 设置 i 已经使用
            used[i] = true;
            // KeyPoint
            //  递归调用后，再次执行 for 循环，i 又是从 0 开始的
            //  即每次递归调用时，遍历的都是最左边的子节点
            dfs(nums, path, res, used);
            // KeyPoint 回溯 "恢复现场" 操作是与递归操作，对应相反
            // 回溯的过程中，将当前的节点从 path 中删除
            path.remove(path.size() - 1);
            // 设置 i 未被使用
            used[i] = false;
        }
    }

    public static void main(String[] args) {
        System.out.println(new _03_46_Permutations4().permute(new int[]{1, 1, 2}));
        // [[1, 1, 2], [1, 2, 1], [1, 1, 2], [1, 2, 1], [2, 1, 1], [2, 1, 1]]
    }
}
