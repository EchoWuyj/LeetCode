package alg_02_train_wyj._22_day_回溯算法一;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-04-09 12:06
 * @Version 1.0
 */
public class _04_47_Permutations {

    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        boolean[] visited = new boolean[nums.length];
        Arrays.sort(nums);
        dfs(nums, visited, path, res);
        return res;
    }

    private void dfs(int[] nums, boolean[] visited, List<Integer> path, List<List<Integer>> res) {
        if (path.size() == nums.length) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (visited[i]) continue;
            if (i >= 1 && nums[i] == nums[i - 1] && !visited[i - 1]) continue;
            path.add(nums[i]);
            visited[i] = true;
            dfs(nums, visited, path, res);
            path.remove(path.size() - 1);
            visited[i] = false;
        }
    }
}
