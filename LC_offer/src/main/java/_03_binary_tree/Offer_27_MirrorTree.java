package _03_binary_tree;

/**
 * @Author Wuyj
 * @DateTime 2022-08-24 13:45
 * @Version 1.0
 */
public class Offer_27_MirrorTree {
    public static void main(String[] args) {

    }

    public static TreeNode mirrorTree(TreeNode root) {
        // 当节点为空时，直接返回
        if (root == null) {
            return null;
        }

        // 设置一个临时的节点 tmp 用来存储当前节点的左子树
        TreeNode tmp = root.left;

        // 当前节点的左子树为节点的右子树
        // 同时递归下去，不停的交互子树中的节点
        root.left = mirrorTree(root.right);

        // 当前节点的右子树为节点的左子树
        // 同时递归下去，不停的交互子树中的节点
        root.right = mirrorTree(tmp);

        // 最后返回根节点
        // KeyPoint 因为 mirrorTree 方法 最终放回值都是 root 节点的值
        //      所以能实现左右节点的反转
        return root;
    }
}
