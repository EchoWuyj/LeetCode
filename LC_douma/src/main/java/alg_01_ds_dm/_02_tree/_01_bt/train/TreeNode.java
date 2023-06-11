package alg_01_ds_dm._02_tree._01_bt.train;

/**
 * @Author Wuyj
 * @DateTime 2023-03-12 20:00
 * @Version 1.0
 */

// 没有定义泛型，直接使用 int
public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int val) {
        this.val = val;
        left = right = null;
    }
}
