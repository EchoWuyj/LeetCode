package alg_02_train_wyj._17_day_二叉树二;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author Wuyj
 * @DateTime 2023-08-11 14:56
 * @Version 1.0
 */
public class _07_437_path_sum_iii4 {

    public int pathSum(TreeNode root, int sum) {
        Map<Long, Integer> map = new HashMap<>();
        int curSum = 0;
        map.put(0L, 1);
        return dfs(root, curSum, sum, map);
    }

    public int dfs(TreeNode node, long curSum, int target, Map<Long, Integer> map) {
        if (node == null) return 0;
        curSum += node.val;
        int res = map.getOrDefault(curSum - target, 0);
        map.put(curSum, map.getOrDefault(curSum, 0) + 1);

        int left = dfs(node.left, curSum, target, map);
        int right = dfs(node.right, curSum, target, map);

        map.put(curSum, map.get(curSum) - 1);
        return res + left + right;
    }
}
