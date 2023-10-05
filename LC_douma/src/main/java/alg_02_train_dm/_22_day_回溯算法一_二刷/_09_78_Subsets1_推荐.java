package alg_02_train_dm._22_day_回溯算法一_二刷;

import alg_02_train_wyj._22_day_回溯算法一._09_78_Subsets1;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-04-09 15:47
 * @Version 1.0
 */
public class _09_78_Subsets1_推荐 {
    /*
        78.子集I
        给你一个 整数 数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集(幂集)。
        解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。

        输入：nums = [1,2,3]
        输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]


        注意
        [2,3] 和 [3,2] 属于重复的子集 => 通过 start 控制遍历的顺序

        提示：
        1 <= nums.length <= 10
        -10 <= nums[i] <= 10
        nums 中的所有元素 互不相同

     */

    // 类比：10_516_LongestPalindromeSubseq1
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        // 1 <= nums.length <= 10 => 不需要特判
//        if (nums == null || nums.length == 0)
//            return res;
        dfs(nums, 0, new ArrayList<>(), res);
        return res;
    }

    private void dfs(int[] nums,
                     int start,
                     List<Integer> subset,
                     List<List<Integer>> res) {
        // 树中 任何一个组合 都是子集，而不是要一个完整的路径。
        // 即：每次遍历树中的节点，加入 subset 中，都能得到一个子集
        // 注意：这里没有显示的递归边界，将剪枝后的树的节点遍历完之后，递归就结束了
        res.add(new ArrayList<>(subset));

        // 将 start 值赋值给 i
        for (int i = start; i < nums.length; i++) {
            // 每次新加入一个节点都是一个新的子集
            subset.add(nums[i]);
            // 父索引 < 子索引 => 保证每个元素只能使用一次
            // KeyPoint 在 i 基础上加 1，不是 start
            dfs(nums, i + 1, subset, res);
            subset.remove(subset.size() - 1);
        }
    }

    public static void main(String[] args) {
        System.out.println(new _09_78_Subsets1().subsets(new int[]{1, 2, 3}));
        // [[], [1], [1, 2], [1, 2, 3], [1, 3], [2], [2, 3], [3]]
    }
}
