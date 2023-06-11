package algorithm._09_binary_tree;

/**
 * @Author Wuyj
 * @DateTime 2022-03-25 14:07
 * @Version 1.0
 */
public class LeetCode_226_InvertBinaryTree {
    // 1.先序遍历
    // 反转之后返回的是当前这个树的根节点
    public TreeNode invertTress(TreeNode root) {
        // 处理基准场景
        if (root == null) return null;

        // 1.先处理根节点,交换左右子节点
        // 这里不是简单的节点交换,而是以左右子节点为根的左右子树在交换
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;

        // 2.递归处理左右子树
        invertTress(root.left);
        invertTress(root.right);

        return root;
    }

    // 2.后序遍历
    public TreeNode invertTress01(TreeNode root) {
        // 处理基准场景
        if (root == null) return null;

        // 1.递归处理左右子树
        TreeNode left = invertTress01(root.left);
        TreeNode right = invertTress01(root.right);

        // 2.处理根节点,交换左右子节点
        root.left = right;
        root.right = left;

        return root;
    }

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(4);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(7);
        TreeNode node4 = new TreeNode(1);
        TreeNode node5 = new TreeNode(3);
        TreeNode node6 = new TreeNode(6);
        TreeNode node7 = new TreeNode(9);

        node1.left = node2;
        node1.right = node3;

        node2.left = node4;
        node2.right = node5;

        node3.left = node6;
        node3.right = node7;

        TreePrinter.printTreeLevelOrder(node1);
        System.out.println();

        LeetCode_226_InvertBinaryTree invertBinaryTree = new LeetCode_226_InvertBinaryTree();
        TreeNode treeNode = invertBinaryTree.invertTress01(node1);
        TreePrinter.printTreeLevelOrder(treeNode);
    }
}
