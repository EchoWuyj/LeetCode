package alg_03_high_frequency._01_codetop_2024_01;

import java.util.*;

/**
 * @Author Wuyj
 * @DateTime 2024-01-14 19:16
 * @Version 1.0
 */
public class _54_78_subsets {
    public List<List<Integer>> subsets(int[] nums) {
        // 不熟悉，需要多多练习！
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> subset = new ArrayList<>();
        dfs(nums, 0, subset, res);
        return res;
    }

    public void dfs(int[] nums, int index,
                    List<Integer> subset,
                    List<List<Integer>> res) {
        // 需要保留每种情况，故每种情况都得 add
        res.add(new ArrayList<>(subset));
        // 通过最后 for 循环终止，从而结束递归
        // index 只是确定起始位置，后续元素的确定都是通过 i 来实现的
        for (int i = index; i < nums.length; i++) {
            subset.add(nums[i]);
            // 针对 i 进行加 1 操作，不是 index + 1，
            // 相同的错误，重复犯过好几次了
            dfs(nums, i + 1, subset, res);
            // 回溯过程需要清理现场，不影响后续回溯
            subset.remove(subset.size() - 1);
        }
    }
}
