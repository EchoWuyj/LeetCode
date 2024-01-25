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
        boolean[] visited = new boolean[nums.length];
        List<Integer> path = new ArrayList<>();
        Arrays.sort(nums);
        dfs(nums, visited, path, res);
        return res;
    }

    public void dfs(int[] nums, boolean[] visited,
                    List<Integer> path, List<List<Integer>> res) {
        if (path.size() == nums.length) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (visited[i]) continue;
            if (i > 0 && nums[i] == nums[i - 1] && !visited[i - 1]) continue;
            path.add(nums[i]);
            visited[i] = true;
            dfs(nums, visited, path, res);
            visited[i] = false;
            path.remove(path.size() - 1);
        }
    }
}
