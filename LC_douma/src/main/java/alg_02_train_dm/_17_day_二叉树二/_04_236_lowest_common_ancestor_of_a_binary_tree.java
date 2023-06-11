package alg_02_train_dm._17_day_二叉树二;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @Author Wuyj
 * @DateTime 2023-04-01 11:52
 * @Version 1.0
 */
public class _04_236_lowest_common_ancestor_of_a_binary_tree {
      /*
        236. 二叉树的最近公共祖先
        给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。

        百度百科中最近公共祖先的定义为：
            “对于有根树 T 的两个节点 p、q，最近公共祖先表示为一个节点 x，
            满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”

        树中节点数目在范围 [2, 10^5] 内。
        -10^9 <= Node.val <= 10^9
        所有 Node.val 互不相同
        p != q
        p 和 q 均存在于给定的二叉树中。
     */

    // KeyPoint 方法一 根据节点与父亲节点关系来实现
    public TreeNode lowestCommonAncestor1(TreeNode root, TreeNode p, TreeNode q) {
        // 1. 维护子节点与其对应父节点的关系
        Map<Integer, TreeNode> parent = new HashMap<>();

        // 前序遍历，记录子节点与其对应父节点的关系
        dfs(root, parent);

        // 用于记录 p 依次访问它的祖先的容器
        Set<Integer> visited = new HashSet<>();

        // 2. 从节点 p 开始，依次访问它的祖先
        while (p != null) {
            // 一个节点也可以是它自己的祖先，故也是需要将其放入到 HashSet 中的
            visited.add(p.val);
            p = parent.get(p.val);
        }

        // 3. 从节点 q 开始，依次访问它的祖先
        // 如果第一次遇到了 p 的祖先的话，那么这个祖先就是最近的公共祖先
        while (q != null) {
            if (visited.contains(q.val)) return q;
            q = parent.get(q.val);
        }

        // while 循环结束，没有找到公共祖先，直接返回 null
        return null;
    }

    // 前序遍历，记录子节点与其对应父节点的关系
    private void dfs(TreeNode node, Map<Integer, TreeNode> parent) {
        if (node == null) return;

        // 记录子节点与其对应父节点的关系
        if (node.left != null) parent.put(node.left.val, node);
        if (node.right != null) parent.put(node.right.val, node);

        dfs(node.left, parent);
        dfs(node.right, parent);
    }

    // KeyPoint 方法二 DFS 后序遍历 => 重点掌握(递归模板)
    // 对于父与子关系，在遍历过程中就能够获取，不需要通过 HashMap 进行显示维护
    // 以 root 为根节点的子树，包含 p 或者 q 的祖先
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // 边界条件 => 空树没有公共祖先，直接返回 null
        if (root == null) return null;

        // 边界条件 => 若本身 root 为 p 或者 q，即为祖先，直接返回
        if (root == p || root == q) return root;

        // 递的过程 => 找祖先
        // root 左侧找 p 或者 q 的祖先
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        // root 右侧找 p 或者 q 的祖先
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        // 归的过程 => 后续遍历之后，对左右子树返回的节点处理
        if (left == null) return right;
        if (right == null) return left;

        // if 潜在逻辑， left != null && right != null
        // 此时 root 为 p q 的最近公共祖先，返回 root
        return root;
    }
}
