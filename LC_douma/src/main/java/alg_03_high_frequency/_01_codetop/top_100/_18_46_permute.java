package alg_03_high_frequency._01_codetop.top_100;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2024-01-13 13:04
 * @Version 1.0
 */
public class _18_46_permute {

    // 全排列
    // 深度优先遍历
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        int n = nums.length;
        // 标记 i 位置是否已经访问过
        boolean[] visited = new boolean[n];
        dfs(nums, res, path, visited);
        return res;
    }

    // KeyPoint 注意：dfs 中没有传入 index
    public void dfs(int[] nums, List<List<Integer>> res,
                    List<Integer> path, boolean[] visited) {
        // path 已经存满
        if (path.size() == nums.length) {
            // 对 path 进行外层包装
            res.add(new ArrayList<>(path));
            // 返回
            return;
        }

        // 每次 dfs 中，for 循环中的 i 都是从 0 开始
        for (int i = 0; i < nums.length; i++) {
            // 选过数字之后，不能重复再选，跳过本轮循环
            if (visited[i]) continue;
            path.add(nums[i]);
            visited[i] = true;
            // 调用 dfs 函数，没有传入 index
            dfs(nums, res, path, visited);
            path.remove(path.size() - 1);
            visited[i] = false;
        }
    }
}
