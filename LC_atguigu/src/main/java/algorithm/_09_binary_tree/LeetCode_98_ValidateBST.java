package algorithm._09_binary_tree;

import java.util.ArrayList;

/**
 * @Author Wuyj
 * @DateTime 2022-03-25 16:42
 * @Version 1.0
 */
public class LeetCode_98_ValidateBST {
    // 方法一:先序遍历(先去进行上下界的处理操作,再去递归左右子树进行判断)
    public boolean isValidBST01(TreeNode root) {
        if (root == null) return true;
        // root本身是没有上下界的,所以传入为null值
        return validator(root, null, null);
    }

    // 定义一个辅助校验器,用来传入上下界递归调用
    // 上下界定义成Integer方便后续传入参数为null值
    private boolean validator(TreeNode root, Integer lowerBound, Integer upperBound) {
        if (root == null) return true;

        // 1.判断当前节点的值是否在上下界范围内,如果超出直接返回false
        // BST中是不能包括等于的情况,故需要将相等的情况排除在外
        if (lowerBound != null && root.val <= lowerBound) {
            return false;
        }
        if (upperBound != null && root.val >= upperBound) {
            return false;
        }

        // 2.递归判断左右子树
        return validator(root.left, lowerBound, root.val)
                && validator(root.right, root.val, upperBound);

    }

    // 定义一个列表
    private ArrayList<Integer> inOrderArray = new ArrayList<>();

    // 方法二:中序遍历(推荐)
    // 中序遍历将二叉搜索树直接展开就是一个按照顺序排列的数组
    public boolean isValidBST02(TreeNode root) {
        // 1.中序遍历,得到升序数组
        inOrder(root);

        // 2.遍历数组,判断是否升序
        for (int i = 0; i < inOrderArray.size(); i++) {
            // i>0即第一个数就不用判断了
            if (i > 0 && inOrderArray.get(i) <= inOrderArray.get(i - 1)) {
                return false;
            }
        }
        return true;
    }

    // 实现一个中序遍历的方法
    public void inOrder(TreeNode root) {
        if (root == null) return;
        inOrder(root.left);
        // 处理当前元素,将当前元素添加到数组中
        inOrderArray.add(root.val);
        inOrder(root.right);
    }

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(5);
        TreeNode node2 = new TreeNode(1);
        TreeNode node3 = new TreeNode(4);
        TreeNode node4 = new TreeNode(3);
        TreeNode node5 = new TreeNode(6);

        node1.left = node2;
        node1.right = node3;
        node3.left = node4;
        node3.right = node5;

        LeetCode_98_ValidateBST validateBST = new LeetCode_98_ValidateBST();
        System.out.println(validateBST.isValidBST02(node1));
    }
}
