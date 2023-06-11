package alg_02_train_dm._17_day_二叉树二;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-04-01 11:51
 * @Version 1.0
 */
public class _03_114_flatten_binary_tree_to_linked_list {
      /*
        114. 二叉树展开为链表
        给你二叉树的根结点 root ，请你将它展开为一个单链表：

        1. 展开后的单链表应该同样使用 TreeNode ，
        其中 right 子指针指向链表中下一个结点，而左子指针始终为 null 。
        2. 展开后的单链表应该与二叉树 先序遍历 顺序相同。

        提示：
        树中结点数在范围 [0, 2000] 内
        -100 <= Node.val <= 100

        进阶：你可以使用原地算法（O(1) 额外空间）展开这棵树吗？
     */

    // KeyPoint 方法一 DFS 先前序遍历，得到结果，在串联成链表
    // 时间复杂度 O(n)
    // 空间复杂度 O(n)
    public void flatten1(TreeNode root) {
        List<TreeNode> list = new ArrayList<TreeNode>();
        // 遍历得到结果集
        preorder(root, list);
        // 调整指针
        for (int i = 1; i < list.size(); i++) {
            TreeNode prev = list.get(i - 1);
            TreeNode curr = list.get(i);
            prev.left = null;
            prev.right = curr;
        }
    }

    public void preorder(TreeNode root, List<TreeNode> list) {
        if (root == null) return;
        list.add(root);
        preorder(root.left, list);
        preorder(root.right, list);
    }

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
