package alg_02_train_dm._17_day_二叉树二;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-04-01 11:51
 * @Version 1.0
 */
public class _03_114_flatten_binary_tree_to_linked_list1 {
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
}
