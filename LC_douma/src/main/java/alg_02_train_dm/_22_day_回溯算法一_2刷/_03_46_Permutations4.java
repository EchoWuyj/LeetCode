package alg_02_train_dm._22_day_回溯算法一_2刷;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-04-07 15:10
 * @Version 1.0
 */
public class _03_46_Permutations4 {

    // 优化：通过 boolean 标记元素是否被使用过，降低时间复杂度
    // O(n! * n)
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        // 通过 boolean 数组来记录 nums 中对应的下标的元素是否被使用过
        boolean[] used = new boolean[nums.length];
        dfs(nums, path, res, used);
        return res;
    }

    // 递归过程，额外信息通过形参方式传入，也可以通过成员变量方式传入，那种方式简单使用那种
    // 将多个方法都使用到的变量，定义成全局变量，方便多个方法调用 => oj 测试表现：有时性能更好
    // O(n)
    private void dfs(int[] nums, List<Integer> path,
                     List<List<Integer>> res, boolean[] used) {
        if (path.size() == nums.length) {
            res.add(new ArrayList<>(path));
            return;
        }
        // 一开始的 root(-1) 没有进行任何处理操作
        // 通过 for 循环直接处理 [0,1,2] 索引对应节点
        for (int i = 0; i < nums.length; i++) { // O(n)
            // 优化：剪枝
            // path.contains(nums[i]) => if (used[i] == true) continue;
            // 且 used[i] 本身就是 boolean，故可以简化成 if (used[i])
            // i 对应每个元素位置，used 通过 i 来标记是否访问过
            if (used[i]) continue; // O(1)
            path.add(nums[i]);
            // 设置 i 已经使用，不要遗漏
            used[i] = true;
            // 递归调用 dfs 后，再次执行 for 循环，i 又是从 0 开始的
            // 即：每次递归调用时，遍历的都是最左边的子节点
            dfs(nums, path, res, used);

            // 回溯 "恢复现场" 操作是与递归操作，对应相反逻辑
            // 1.将当前的节点从 path 中删除
            path.remove(path.size() - 1);
            // 2.设置 i 未被使用
            used[i] = false;
        }
    }

    public static void main(String[] args) {
        System.out.println(new _03_46_Permutations4().permute(new int[]{1, 1, 2}));
        // [[1, 1, 2], [1, 2, 1], [1, 1, 2], [1, 2, 1], [2, 1, 1], [2, 1, 1]]
    }
}
