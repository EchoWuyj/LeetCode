package alg_02_train_dm._18_day_二叉树三;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @Author Wuyj
 * @DateTime 2023-04-05 10:45
 * @Version 1.0
 */
public class _03_105_construct_binary_tree_from_preorder_and_inorder_traversal1 {
     /*
        105. 从前序与中序遍历序列构造二叉树
        给定两个整数数组 preorder 和 inorder
        其中 preorder 是二叉树的先序遍历，inorder 是同一棵树的中序遍历，
        请构造二叉树并返回其根节点

        注意:
        你可以假设树中没有重复的元素。
    
        前序遍历 preorder = [3,9,20,15,7]
        中序遍历 inorder = [9,3,15,20,7]
        输出: [3,9,20,null,null,15,7]

        返回如下的二叉树：

            3
           / \
          9  20
            /  \
           15   7

        提示:
        1 <= preorder.length <= 3000
        inorder.length == preorder.length
        -3000 <= preorder[i], inorder[i] <= 3000
        preorder 和 inorder 均 无重复 元素
        inorder 均出现在 preorder
        preorder 保证 为二叉树的前序遍历序列
        inorder 保证 为二叉树的中序遍历序列

     */

    // KeyPoint 迭代法

    // 核心思路
    // 1.遍历前序遍历中每个节点，用于构建二叉树中节点
    //   前序数组中，相邻两个节点 => 前一个节点 i-1，当前节点 i
    // 2.至于遍历到节点是已经创建二叉树的左子树还是右子树，通过中序遍历确定
    //   2.1 中序遍历中，若节点 i 在节点 i-1 左边，则 i 为 i-1 左子节点
    //   2.2 中序遍历中，若节点 i 在节点 i-1 右边，找到 i 之前且距离 i 最近节点 j，i 为 j 右子节点

    // 前序遍历：3 9 5 4 7 2 1 => 根左右
    //            ↑
    //            i
    // 中序遍历：4 5 9 7 3 1 2 => 左根右
    //                  ↑
    //            左子树 j

    //         3
    //        /
    //      9        栈底 3 9

    // 前序遍历：3 9 5 4 7 2 1 => 根左右
    //              ↑
    //              i
    // 中序遍历：4 5 9 7 3 1 2 => 左根右
    //              ↑
    //        左子树 j

    //         3
    //       /
    //      9
    //     /
    //    5           栈底 3 9 5

    // 前序遍历：3 9 5 4 7 2 1 => 根左右
    //                ↑
    //                i
    // 中序遍历：4 5 9 7 3 1 2 => 左根右
    //            ↑
    //      左子树 j

    //         3
    //       /
    //      9
    //     /
    //    5
    //   /
    //  4              栈底 3 9 5 4

    // 前序遍历：3 9 5 4 7 2 1 => 根左右
    //                  ↑
    //                  i
    // 中序遍历：4 5 9 7 3 1 2 => 左根右
    //          ↑
    //          j 右子树 => 结论：7 是中序遍历中，距离 7 最近节点 [9] 的右子树

    //         3
    //       /
    //      9
    //     /\
    //    5  7  => 先进后出 => 节点 9 是最开始构建，到最后处理，节点 7 为 9 的右子树
    //   /      => 使用'栈'实现
    //  4
    //                  栈底 3 7

    // ...

    //         3
    //       /  \
    //      9    2
    //     /\   /
    //    5  7  1
    //   /
    //  4

    // KeyPoint 方法一 迭代 => 实现一
    public TreeNode buildTree1(int[] preorder, int[] inorder) {

        // 前序遍历第一个元素 => 二叉树根节点
        TreeNode root = new TreeNode(preorder[0]);
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);
        int inorderIndex = 0;
        int n = preorder.length;
        for (int i = 1; i < n; i++) {
            int childVal = preorder[i];
            TreeNode parentNode = stack.peek();
            // TODO 需要解释说明下为什么这样判断可以？
            if (parentNode.val != inorder[inorderIndex]) {
                parentNode.left = new TreeNode(childVal);
                stack.push(parentNode.left);
            } else {
                // 找到离右子节点最近的父亲节点
                while (!stack.isEmpty() &&
                        inorder[inorderIndex] == stack.peek().val) {
                    parentNode = stack.pop();
                    inorderIndex++;
                }
                parentNode.right = new TreeNode(childVal);
                stack.push(parentNode.right);
            }
        }
        return root;
    }

    // KeyPoint 方法一 迭代 => 实现一
    public TreeNode buildTree2(int[] preorder, int[] inorder) {

        // 前序遍历第一个元素 => 二叉树根节点
        TreeNode root = new TreeNode(preorder[0]);
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);
        int inorderIndex = 0;
        int n = preorder.length;
        for (int i = 1; i < n; i++) {
            int childVal = preorder[i];
            TreeNode parentNode = stack.peek();
            // TODO 需要解释说明下为什么这样判断可以？
            if (parentNode.val != inorder[inorderIndex]) {
                parentNode.left = new TreeNode(childVal);
                stack.push(parentNode.left);
            } else {
                // 找到离右子节点最近的父亲节点
                while (!stack.isEmpty() &&
                        inorder[inorderIndex] == stack.peek().val) {
                    parentNode = stack.pop();
                    inorderIndex++;
                }
                parentNode.right = new TreeNode(childVal);
                stack.push(parentNode.right);
            }
        }
        return root;
    }
}
