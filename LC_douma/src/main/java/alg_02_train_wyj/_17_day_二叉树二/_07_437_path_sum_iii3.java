package alg_02_train_wyj._17_day_二叉树二;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author Wuyj
 * @DateTime 2023-08-11 14:46
 * @Version 1.0
 */
public class _07_437_path_sum_iii3 {

    private int res = 0;

    public int pathSum(TreeNode root, int sum) {
        Map<Long, Integer> map = new HashMap<>();
        map.put(0L, 1);
        dfs(root, 0, sum, map);
        return res;
    }

    private void dfs(TreeNode root, long curSum, long target, Map<Long, Integer> map) {
        if (root == null) return;
        curSum += root.val;
        res += map.getOrDefault(curSum - target, 0);
        map.put(curSum, map.getOrDefault(curSum, 0) + 1);

        dfs(root.left, curSum, target, map);
        dfs(root.right, curSum, target, map);

        map.put(curSum, map.get(curSum) - 1);
    }
}
