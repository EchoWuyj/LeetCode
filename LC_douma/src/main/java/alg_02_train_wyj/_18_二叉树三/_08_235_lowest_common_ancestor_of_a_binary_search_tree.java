package alg_02_train_wyj._18_二叉树三;

/**
 * @Author Wuyj
 * @DateTime 2023-08-12 11:03
 * @Version 1.0
 */
public class _08_235_lowest_common_ancestor_of_a_binary_search_tree {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode ancestor = root;
        while (ancestor != null) {
            if (p.val < ancestor.val & q.val < ancestor.val) {
                ancestor = ancestor.left;
            } else if (p.val > ancestor.val && q.val > ancestor.val) {
                ancestor = ancestor.right;
            } else {
                break;
            }
        }
        return ancestor;
    }
}
