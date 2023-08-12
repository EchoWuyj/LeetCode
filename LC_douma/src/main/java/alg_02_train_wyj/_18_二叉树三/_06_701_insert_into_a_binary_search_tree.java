package alg_02_train_wyj._18_二叉树三;

/**
 * @Author Wuyj
 * @DateTime 2023-08-12 16:00
 * @Version 1.0
 */
public class _06_701_insert_into_a_binary_search_tree {
    public TreeNode insertIntoBST1(TreeNode root, int val) {
        if (root == null) return new TreeNode(val);
        TreeNode cur = root;
        while (cur != null) {
            if (val < cur.val && cur.left == null) {
                cur.left = new TreeNode(val);
                return root;
            } else if (val > cur.val && cur.right == null) {
                cur.right = new TreeNode(val);
                return root;
            }

            if (val < cur.val) {
                cur = cur.left;
            } else {
                cur = cur.right;
            }
        }
        return root;
    }

    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) return new TreeNode(val);
        if (val < root.val) {
            root.left = insertIntoBST(root.left, val);
        } else {
            root.right = insertIntoBST(root.right, val);
        }
        return root;
    }
}
