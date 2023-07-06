package alg_02_train_dm._22_day_回溯算法一_二刷;

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
        47. 全排列 II
        给定一个可 包含重复数字 的序列 nums，按任意顺序 返回所有 不重复 的全排列。

        示例 1：
        输入：nums = [1,1,2]
        输出：
        [[1,1,2],
         [1,2,1],
         [2,1,1]]

        说明：[1,1,2] 和 [1,1,2]  => 这两种情况算作一种情况，只需要留下一个即可
               ↑ ↑        ↑ ↑
              红 黑      黑 红

        示例 2：
        输入：nums = [1,2,3]
        输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]

        提示：
        1 <= nums.length <= 8
        -10 <= nums[i] <= 10

     */

    // O(n! * n)
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        boolean[] used = new boolean[nums.length];
        // 一定得排序，不要遗忘了！
        // 通过排序将相同的值靠在一起，才能判断 '当前值' = '前面值'，这是去重的基础
        Arrays.sort(nums);
        dfs(nums, path, res, used);
        return res;
    }

    // O(n)
    private void dfs(int[] nums,
                     List<Integer> path,
                     List<List<Integer>> res,
                     boolean[] used) {
        if (path.size() == nums.length) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            // 1.剪枝 => 判断元素值是否重复
            //   根据索引 i 判重，不是根据元素值判重
            //   本题中 红 1 和 黑 1 都是 1，但是看做不同元素
            if (used[i]) continue;
            // 2.剪枝 => 判断全排列是否重复
            //   在排序后，同一层相邻重复元素，看成一个元素，只保留一个元素下分支路径即可
            //   => 上一个值已经访问，且当前一个值等于上一个值，进行剪枝
            //   注意：涉及数组索引变换，保证 i -1 不越界，故 i > 0
            //   used[i-1] = false 表示 i-1 已经访问过了，已经回溯过
            if (i >= 1 && nums[i] == nums[i - 1] && !used[i - 1]) continue;

            // 关于 !used[i - 1] = false 解释
            // 遍历索引为 i 的元素，是从遍历 i-1 元素回溯过来的
            // 回溯中将 used[i - 1] = false，表示的是这个 i-1 是已经被访问过的
            // 若是正在访问的话，那么是 true

            // 对于 !used[i - 1] 的解释请见 issue：
            // https://gitee.com/douma_edu/douma_algo_training_camp/issues/I48M6Q

            path.add(nums[i]);
            used[i] = true;
            dfs(nums, path, res, used);
            // 回溯，将当前的节点从 path 中删除
            path.remove(path.size() - 1);
            used[i] = false;
        }
    }
}
