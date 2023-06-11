package algorithm._09_binary_tree;

/**
 * @Author Wuyj
 * @DateTime 2022-03-25 15:16
 * @Version 1.0
 */
public class LeetCode_110_BalancedBinaryTree {
    // 方法一:先序遍历
    public boolean isBalanced01(TreeNode root) {
        if (root == null) return true;

        // 处理根节点,再去处理左子树和右子树
        return Math.abs(height(root.left) - height(root.right)) <= 1
                && isBalanced01(root.left)
                && isBalanced01(root.right);
    }

    // 定义一个计算树高度的方法,还是通过递归调用来实现
    public int height(TreeNode root) {
        if (root == null) return 0;
        // 返回左右子树中高度更大的那个
        return Math.max(height(root.left), height(root.right)) + 1;
    }

    // 方法二:后序遍历(先处理左右子树,再去处理当前节点)
    public boolean isBalanced02(TreeNode root) {
        return balancedHeight(root) > -1;
    }

    // 定义一个直接判断当前数是否平衡的方法
    // 平衡则返回高度,不平衡则返回-1
    public int balancedHeight(TreeNode root) {
        if (root == null) return 0;

        // 递归计算左右子树高度
        int leftHeight = balancedHeight(root.left);
        int rightHeight = balancedHeight(root.right);

        // 如果子树不平衡,直接返回-1
        // 如果左子树或者右子树中有一个不平衡
        if (leftHeight == -1 || rightHeight == -1 || Math.abs(leftHeight - rightHeight) > 1) {
            return -1;
        }

        // 如果平衡,返回当前高度
        return Math.max(leftHeight, rightHeight) + 1;
    }

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(3);
        TreeNode node2 = new TreeNode(9);
        TreeNode node3 = new TreeNode(20);
        TreeNode node4 = new TreeNode(15);
        TreeNode node5 = new TreeNode(7);

        node1.left = node2;
        node1.right = node3;
        node3.left = node4;
        node3.right = node5;

        LeetCode_110_BalancedBinaryTree balancedBinaryTree = new LeetCode_110_BalancedBinaryTree();
        System.out.println(balancedBinaryTree.isBalanced02(node1));
    }
}
