package alg_02_train_dm._22_day_回溯算法一;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-04-07 15:10
 * @Version 1.0
 */
public class _03_46_Permutations3 {

    // 时间复杂度：O(n!*n^2) => 一共有 n! 个节点，每个节点 O(n^2)
    // 本质：计算 dfs 调用的次数
    // 回溯算法解决问题，时间复杂度都是挺高的，因为整体是穷举的过程
    // 后面有些问题，可以通过贪心，动态规划来优化，降低其复杂度
    // A(n,m) = n! / (n-m)!
    // A(4,2) = 4! / 2! = 4 * 3 = 12
    // A(n,m) 需要按照一定顺序
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        dfs(nums, path, res);
        return res;
    }

    // 回溯套路
    // 1.将问题抽取成多叉树
    // 2.定义触发条件将结果存在结果集，穷举所有结果
    // 3.剪枝，排除不要结果
    private void dfs(int[] nums,
                     List<Integer> path,
                     List<List<Integer>> res) { // O(n^2)
        if (path.size() == nums.length) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < nums.length; i++) { // O(n)
            // 剪枝 => 每次遍历节点，判断是否为重复使用的数字
            // 如果出现相同的数字，跳过当前的 dfs，进行下一轮 dfs
            if (path.contains(nums[i])) continue; // O(n)
            path.add(nums[i]);
            dfs(nums, path, res);
            // 回溯的过程中，将当前的节点从 path 中删除
            path.remove(path.size() - 1);

//            if (!path.contains(nums[i])) {
//                path.add(nums[i]);
//            }

            // KeyPoint 错误写法，导致 StackOverflowError
            // 因为即使 if 条件判断不成立，后续代码的 dfs 递归函数都是进行的
            // 导致 int i 每次都从 0 开始，形成了递归死循环，最终导致 StackOverflowError
        }
    }

    /*
        def backtrack(路径, 选择列表):
        if 满足结束条件:
            result.add(路径)
            return

        for 选择 in 选择列表:
            做选择
            backtrack(路径, 选择列表)
            撤销选择
     */

    public static void main(String[] args) {
        System.out.println(new _03_46_Permutations3().permute(new int[]{1, 2, 3}));
        // [[1, 2, 3], [1, 3, 2], [2, 1, 3], [2, 3, 1], [3, 1, 2], [3, 2, 1]]
    }
}
