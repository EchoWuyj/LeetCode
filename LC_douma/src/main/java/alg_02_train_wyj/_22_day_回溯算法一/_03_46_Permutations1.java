package alg_02_train_wyj._22_day_回溯算法一;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-04-08 20:18
 * @Version 1.0
 */
public class _03_46_Permutations1 {
    public List<List<Integer>> permute(int[] nums) {

        List<List<Integer>> res = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        int n = nums.length;
        boolean[] visited = new boolean[n];
        dfs(nums, 0, visited, path, res);
        return res;
    }

    public void dfs(int[] nums, int index,
                    boolean[] visited, List<Integer> path, List<List<Integer>> res) {
        if (index == nums.length) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (visited[i]) continue;
            path.add(nums[i]);
            visited[i] = true;
            dfs(nums, index + 1, visited, path, res);
            visited[i] = false;
            path.remove(path.size() - 1);
        }
    }

    public static void main(String[] args) {
        System.out.println(new _03_46_Permutations1().permute(new int[]{1, 2, 3}));
    }
}
