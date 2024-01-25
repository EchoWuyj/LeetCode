package alg_03_high_frequency._01_codetop_2024_01;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @Author Wuyj
 * @DateTime 2024-01-13 13:39
 * @Version 1.0
 */
public class _20_236_lowestCommonAncestor {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

        if (root == null) return null;
        // map 维护整个树结构
        // 左右子树值，对应根节点
        Map<Integer, TreeNode> map = new HashMap<>();

        // 构建关系
        dfs(root, map);

        // 判断当前值是否已经重复
        Set<Integer> visited = new HashSet<>();

        // p 是节点
        while (p != null) {
            // 加入 visited 中
            visited.add(p.val);
            // 通过 p.val 获取父节点，并且将其赋值给p
            p = map.get(p.val);
        }

        while (q != null) {
            // 直接返回 q 节点，不是 q.val，注意通过 map 维护整个树结构
            if (visited.contains(q.val)) return q;
            q = map.get(q.val);
        }
        // 没有返回 null
        return null;
    }

    private void dfs(TreeNode node, Map<Integer, TreeNode> map) {
        if (node == null) return;

        // 在 dfs 之前维护 map 关系
        if (node.left != null) {
            // 左子树值，对应根节点
            map.put(node.left.val, node);
        }
        dfs(node.left, map);

        if (node.right != null) {
            map.put(node.right.val, node);
        }
        dfs(node.right, map);
    }
}
