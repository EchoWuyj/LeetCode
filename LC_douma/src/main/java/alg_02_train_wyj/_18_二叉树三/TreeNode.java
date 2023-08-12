package alg_02_train_wyj._18_二叉树三;

/**
 * @Author Wuyj
 * @DateTime 2023-08-11 16:18
 * @Version 1.0
 */
public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode leftNode, TreeNode rightNode) {
        this.val = val;
        left = leftNode;
        right = rightNode;
    }
}
