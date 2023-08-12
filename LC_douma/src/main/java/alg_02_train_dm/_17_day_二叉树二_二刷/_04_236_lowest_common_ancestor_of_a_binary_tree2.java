package alg_02_train_dm._17_day_二叉树二_二刷;

/**
 * @Author Wuyj
 * @DateTime 2023-08-10 15:46
 * @Version 1.0
 */
public class _04_236_lowest_common_ancestor_of_a_binary_tree2 {

    // KeyPoint 方法二 DFS 后序遍历 => 重点掌握(递归模板)
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

        // 对于父与子关系，在遍历过程中就能够获取，不需要通过 HashMap 进行显示维护
        // 以 root 为根节点的子树，包含 p 或者 q 的最近公共祖先

        // KeyPoint 情况一

        //         1
        //        / \
        //       2   6
        //      /\   /\
        //     3  4    7
        //       / \
        //      9   5
        //    p = 9，q = 7

        //     4 ← 返回 9
        //    / \
        //   9   5
        //  以 4 为根节点的子树，包含 9 的祖先，此时还不是最近公共祖先，返回上一层

        //            1
        //           / \
        // 返回 9 → 2   6  ← 返回 7
        // 此时 left 和 right 都是不为 null 的 => root 为最近公共祖先

        // KeyPoint 情况二

        //         1
        //        / \
        //       2   6
        //      /\   /\
        //     3  4    7
        //       / \
        //      9   5
        //    p = 9，q = 4

        //     4 ← 返回 4，若 root = q，直接返回，不用后续再找了
        //    / \
        //   9   5
        //  以 4 为根节点的子树，包含 4 的祖先，返回上一层

        // KeyPoint 情况三

        //         1
        //        / \
        //       2   6
        //      /\   /\
        //     3  4    7
        //       / \
        //      9   5
        //   p = 3，q = 9

        //            2  ← 返回 2，此时 left 和 right 都是不为 null 的 => root 为最近公共祖先
        //           / \
        // 返回 3 → 3   4 ← 返回 9
        //             /
        //            9

        // KeyPoint 加深对'边界条件'理解
        // 边界条件属于递的过程，可能遇到的各种情况，进行相应处理

        // 边界条件 => 空树没有公共祖先，直接返回 null
        if (root == null) return null;
        // 边界条件 => 若本身 root 为 p 或者 q，即为祖先，直接返回
        if (root == p || root == q) return root;

        // KeyPoint 递的过程 => 找祖先
        // root 左侧找 p 或者 q 的祖先
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        // root 右侧找 p 或者 q 的祖先
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        // 归的过程 => 后续遍历之后，对左右子树返回的节点处理
        if (left == null) return right;
        if (right == null) return left;

        // if 潜在逻辑，left != null && right != null
        // 此时 root 为 p q 的最近公共祖先，返回 root
        return root;
    }
}
