package alg_03_high_frequency._01_codetop_2024_01_Top100;

import java.util.*;

/**
 * @Author Wuyj
 * @DateTime 2024-01-14 19:16
 * @Version 1.0
 */
public class _54_78_subsets {

    // 子集
    // 深度优先遍历
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> subset = new ArrayList<>();
        dfs(nums, 0, subset, res);
        return res;
    }

    public void dfs(int[] nums, int index, List<Integer> subset, List<List<Integer>> res) {

        // 结果集 res 需要保留每种情况，故每种情况都得添加
        // => 没有显示的递归边界
        res.add(new ArrayList<>(subset));

        // 通过最后 for 循环终止，从而结束递归
        for (int i = index; i < nums.length; i++) {
            subset.add(nums[i]);
            // 下轮 dfs 都是基于以上一轮索引位置，故 index = i+1
            // 传入形参 index，只是决定 i 的起始位置，并不是针对 index 进行加 1 操作
            // 注意：相同的错误，重复犯过好几次了
            dfs(nums, i + 1, subset, res);
            // 回溯过程需要清理现场，不影响后续回溯
            subset.remove(subset.size() - 1);
        }
    }
}
