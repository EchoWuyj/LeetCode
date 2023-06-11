package alg_02_train_dm._22_day_回溯算法一;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-04-07 22:31
 * @Version 1.0
 */
public class _04_47_Permutations {

    /*
     给定一个可包含重复数字的序列 nums，按任意顺序 返回所有不重复的全排列。
     */

    // O(n! * n)
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        boolean[] used = new boolean[nums.length];
        // 通过排序将相同的值靠在一起，才能判断'当前值'='前面值'，这是去重的基础
        Arrays.sort(nums);
        dfs(nums, path, res, used);
        return res;
    }

    private void dfs(int[] nums,
                     List<Integer> path,
                     List<List<Integer>> res,
                     boolean[] used) { // O(n)
        if (path.size() == nums.length) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            // 剪枝，判断重复使用的数字
            if (used[i]) continue;
            // 剪枝，去重的条件，上一个值已经访问，且当前一个值等于上一个值，进行剪枝
            // 保证 i -1 不越界，故 i > 0，
            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) continue;

            // 关于 !used[i - 1] 解释
            // 遍历索引为 i 的元素是从遍历 i - 1 元素回溯过来的，used[i - 1] = false
            // 表示的是这个 i - 1 是已经被访问过的，而不是正在访问
            // 如果是正在访问的话，那么是 true，如果是回溯后，就会设置为 false

            // 对于 !used[i - 1] 的解释请见 issue：
            // https://gitee.com/douma_edu/douma_algo_training_camp/issues/I48M6Q
            path.add(nums[i]);
            used[i] = true;
            dfs(nums, path, res, used);
            // 回溯的过程中，将当前的节点从 path 中删除
            path.remove(path.size() - 1);
            used[i] = false;
        }
    }

    public static void main(String[] args) {
        System.out.println(new _04_47_Permutations().permuteUnique(new int[]{1, 2, 1}));
    }
}
