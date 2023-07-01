package alg_02_train_dm._17_day_二叉树二;

/**
 * @Author Wuyj
 * @DateTime 2023-06-29 21:37
 * @Version 1.0
 */
public class _03_114_flatten_binary_tree_to_linked_list3 {


    // KeyPoint 方法三 原地改变指针
    // 时间复杂度 O(n)
    // 空间复杂度 O(1)
    public void flatten(TreeNode root) {
        // 1 root 右子树是最后访问，先将其调整到 root 左子节点最右位置(前驱节点)
        // 2 其次调整 root 左子树，将 root 左子树移动到 right 位置
        // 3 依次遍历后续的节点，即 cur.right 节点，重复以上的操作
        if (root == null) return;
        // cur 是遍历指针，从 root 开始
        TreeNode curr = root;
        while (curr != null) {
            // 找 root 右子树的前驱节点
            if (curr.left != null) {
                TreeNode left = curr.left;
                TreeNode pre = left;
                while (pre.right != null) {
                    pre = pre.right;
                }
                // 将 root 右子树挂在 root 左子节点最右位置
                // 这里需要注意，不能使用 root.right，而是应该使用 cur.right
                // pre.left 本来就是 null，不需要重新设置
                pre.right = curr.right;

                // 一开始 curr 就是 root 位置
                // root 的 left 设置为 null
                curr.left = null;
                // root 的 right 位置，使用左子树 left 接上
                curr.right = left;
            }
            // 更新 cur
            curr = curr.right;
        }
    }
}
