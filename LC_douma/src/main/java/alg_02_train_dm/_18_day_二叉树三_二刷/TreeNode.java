package alg_02_train_dm._18_day_二叉树三_二刷;

/**
 * @Author Wuyj
 * @DateTime 2023-04-04 10:18
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
