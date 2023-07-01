package alg_02_train_dm._17_day_二叉树二;

import java.util.ArrayDeque;

/**
 * @Author Wuyj
 * @DateTime 2023-06-29 21:37
 * @Version 1.0
 */
public class _03_114_flatten_binary_tree_to_linked_list2 {

    // KeyPoint 方法二 先序遍历(迭代) 边遍历，边串联，串联节点过程需要保证是先序遍历
    // 时间复杂度 O(n)
    // 空间复杂度 O(n)
    public void flatten2(TreeNode root) {
        if (root == null) return;

        ArrayDeque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);
        TreeNode prev = null;
        while (!stack.isEmpty()) {
            TreeNode curr = stack.pop();
            // 判断 curr 节点是否有 prev 节点
            if (prev != null) {
                prev.left = null;
                // root 右子节点已经放入到栈中，所以可以调整 root 的 right 指针
                prev.right = curr;
            }
            // 获取左右子节点
            TreeNode left = curr.left, right = curr.right;
            if (right != null) {
                stack.push(right);
            }
            if (left != null) {
                stack.push(left);
            }
            // prev 指针指向 curr 节点，对于 root 来说，prev 是记录 root 的
            // 对于下轮 curr 而言，prev 指向的是下轮 curr 而言的前一个节点
            prev = curr;
        }
    }
}
