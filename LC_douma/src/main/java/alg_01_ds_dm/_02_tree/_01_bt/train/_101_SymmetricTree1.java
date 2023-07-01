package alg_01_ds_dm._02_tree._01_bt.train;

/**
 * @Author Wuyj
 * @DateTime 2023-03-13 20:04
 * @Version 1.0
 */


public class _101_SymmetricTree1 {

    /*
        101. 对称二叉树
        给你一个二叉树的根节点 root ， 检查它是否轴对称。

        示例 1：
        输入：root = [1,2,2,3,4,4,3]
        输出：true

        提示：
        树中节点数目在范围 [1, 1000] 内
        -100 <= Node.val <= 100

     */

    // KeyPoint 方法一 DFS 递归实现
    // 判断一颗树是否是对称的 => 左右两颗子树是否是镜像对称的
    public boolean isSymmetric(TreeNode root) {
        // 空树认为是对称的
        if (root == null) return true;
        return isMirror(root.left, root.right);
    }

    // 判断两颗树是否是镜像的
    // 返回值为 boolean 值的递归逻辑
    private boolean isMirror(TreeNode t1, TreeNode t2) {

        // 思路：
        // 1.最先判断条件，返回值为 true 的情况
        // 2.之后再返回 false 的多种情况
        // 3.最后递归调用 isMirror 进行后续判断

        // 1.先对 t1，t2 根节点进行相等判断，判断是否相等
        if (t1 == null && t2 == null) return true;
        if (t1 == null || t2 == null) return false;
        if (t1.val != t2.val) return false;

        // 2.再对 t1，t2 左右字节点进行镜像判断
        // 类似于 SameTree 的判断 isSameTree1(p.left, q.left) && isSameTree1(p.right, q.right)
        // 将原来的左对左(相同的树) => 左对右(对称二叉树，左右两颗子树是否是镜像对称的)
        return isMirror(t1.left, t2.right) && isMirror(t1.right, t2.left);
    }


}
