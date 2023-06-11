package alg_01_ds_wyj._02_tree._01_btree;

/**
 * @Author Wuyj
 * @DateTime 2023-03-12 23:14
 * @Version 1.0
 */
public class TreeNode<E> {
    E val;
    TreeNode<E> left;
    TreeNode<E> right;

    public TreeNode(E val) {
        this.val = val;
        left = right = null;
    }
}
