package alg_02_train_dm._17_day_二叉树二_二刷;

/**
 * @Author Wuyj
 * @DateTime 2023-06-29 21:37
 * @Version 1.0
 */
public class _03_114_flatten_binary_tree_to_linked_list3_推荐 {

    // KeyPoint 方法三 原地改变指针 => 不需要额外空间
    // 时间复杂度 O(n)
    // 空间复杂度 O(1)
    public void flatten(TreeNode root) {

        //        1 ← cur
        //      /   \
        //     2     6
        //    / \     \
        //   3   4     7
        //        \
        //         5

        //        1 ← cur
        //      /
        //     2
        //    / \
        //   3   4
        //        \
        //         5 ← pre
        //          \
        //          6
        //           \
        //            7

        //        1
        //         \
        //          2 ← cur
        //         / \
        //        3   4
        //             \
        //              5 ← pre
        //               \
        //               6
        //                \
        //                 7

        //        1
        //         \
        //          2 ← cur
        //         /
        //        3 ← pre
        //         \
        //          4
        //           \
        //            5
        //             \
        //             6
        //              \
        //               7

        //        1
        //         \
        //          2
        //           \
        //            3 ← cur => cur 没有左子树，符合展开链表规则，一直往下遍历 cur.right
        //             \
        //              4
        //               \
        //                5
        //                 \
        //                 6
        //                  \
        //                   7

        // 操作步骤
        // 1.root 右子树是最后访问，先将其调整到 root 左子树的最右位置(前驱节点)
        //   前序遍历过程：先遍历 root 左子树最右位置，再去遍历 root 右子树
        // 2.其次调整 root 左子树，将 root 左子树移动到 right 位置
        // 3.依次遍历后续的节点，即 cur.right 节点，重复以上的操作

        if (root == null) return;
        // cur 是遍历指针，从 root 开始
        TreeNode cur = root;
        // cur 不为 null，则一直处理
        // KeyPoint cur 不仅仅 root 节点，而是每个子树的 root 节点
        while (cur != null) {
            // 找 root 右子树的前驱节点
            if (cur.left != null) {
                // 左子树
                TreeNode left = cur.left;
                TreeNode pre = left;
                while (pre.right != null) {
                    pre = pre.right;
                }
                // 将 root 右子树挂在 root 左子树最右位置
                // 1.注意：当前根节点 cur 是不断变化的
                //   使用当前根节点的右子树 cur.right，而不是固定根节点的右子树 root.right，
                // 2.左子树最右位置为叶子节点，left 和 right 必然为 null，故 pre.left 不用设置
                pre.right = cur.right;

                // KeyPoint 重新设置 cur 左右子树
                // 一开始 cur 是 root 位置，root 的 left 设置为 null
                cur.left = null;
                // root 的 right 位置，使用左子树 left 接上
                cur.right = left;
            }
            // 当前根节点 cur 的左子树已经处理好
            // cur 移动到右子树上，更新成新的根节点
            cur = cur.right;
        }
    }
}
