package alg_01_ds_dm._02_tree._01_bt;

/**
 * @Author Wuyj
 * @DateTime 2023-03-12 14:51
 * @Version 1.0
 */
class TreeNode<E> {
    E data;
    TreeNode<E> left;
    TreeNode<E> right;

    TreeNode(E data) {
        // 只是定义节点值，左右指针并不设置
        this.data = data;
        left = right = null;
    }
}

