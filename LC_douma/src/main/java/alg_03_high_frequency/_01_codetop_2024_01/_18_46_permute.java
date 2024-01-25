package alg_03_high_frequency._01_codetop_2024_01;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2024-01-13 13:04
 * @Version 1.0
 */
public class _18_46_permute {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        int n = nums.length;
        // 标记 i 位置
        boolean[] visited = new boolean[n];
        dfs(nums, res, path, visited);
        return res;
    }

    // dfs 中没有传入 index
    public void dfs(int[] nums, List<List<Integer>> res,
                    List<Integer> path, boolean[] visited) {
        // path 已经存满了
        if (path.size() == nums.length) {
            res.add(new ArrayList<>(path));
            // 返回
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            // 选过数字之后，不能重复再选，跳过本轮循环
            if (visited[i]) continue;
            path.add(nums[i]);
            visited[i] = true;
            // 没有 index
            dfs(nums, res, path, visited);
            path.remove(path.size() - 1);
            visited[i] = false;
        }
    }
}
