package alg_02_train_dm._17_day_二叉树二_二刷;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author Wuyj
 * @DateTime 2023-08-11 10:52
 * @Version 1.0
 */
public class _07_437_path_sum_iii3 {

    // KeyPoint 方法二 DFS(前序遍历) + 前缀和
    // O(n)
    private int res = 0;

    public int pathSum(TreeNode root, int sum) {
        // 使用 Long，避免数据越界
        Map<Long, Integer> map = new HashMap<>();
        map.put(0L, 1);
        dfs(root, 0, sum, map);
        return res;
    }

    private void dfs(TreeNode node, long curSum, int target, Map<Long, Integer> map) {

        if (node == null) return;
        curSum += node.val;
        res += map.getOrDefault(curSum - target, 0);
        map.put(curSum, map.getOrDefault(curSum, 0) + 1);

        dfs(node.left, curSum, target, map);
        dfs(node.right, curSum, target, map);

        // 返回上一层 node 前，清除现场
        map.put(curSum, map.get(curSum) - 1);
    }
}
