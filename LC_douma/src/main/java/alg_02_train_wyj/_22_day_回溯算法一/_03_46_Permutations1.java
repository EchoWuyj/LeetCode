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
        dfs(nums, -1, path, res);
        return res;
    }

    public void dfs(int[] nums, int index,
                    List<Integer> path, List<List<Integer>> res) {
        if (path.size() == nums.length) return;
        if (index != -1) path.add(nums[index]);
        if (path.size() == nums.length) {
            res.add(new ArrayList<>(path));
        }
        for (int i = 0; i < nums.length; i++) {
            dfs(nums, i, path, res);
        }

        if (index != -1) path.remove(path.size() - 1);
    }

    public static void main(String[] args) {
        System.out.println(new _03_46_Permutations1().permute(new int[]{1, 2, 3}));
    }
}
