package alg_03_high_frequency._01_codetop.top_100;

/**
 * @Author Wuyj
 * @DateTime 2024-01-16 16:39
 * @Version 1.0
 */
public class _64_101_isSymmetric {

    // 对称二叉树
    // 深度优先遍历
    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        // 同一颗树，自我比较，判断是否对称
        return isMirror(root, root);
    }

    public boolean isMirror(TreeNode t1, TreeNode t2) {
        // 若 t1 he t2 都为 null，直接返回 true，结束判断
        if (t1 == null && t2 == null) return true;
        if (t1 == null && t2 != null) return false;
        if (t1 != null && t2 == null) return false;
        if (t1.val != t2.val) return false;
        // 对称：t1 左 和 t2 右 && t1 右 和 t2 左
        return isMirror(t1.left, t2.right) && isMirror(t1.right, t2.left);
    }
}
