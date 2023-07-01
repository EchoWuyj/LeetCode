package alg_01_ds_dm._02_tree._01_bt;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @Author Wuyj
 * @DateTime 2023-03-30 23:56
 * @Version 1.0
 */
public class BinaryTree_01_PreOrder {

    // KeyPoint 方法一 前序遍历(迭代)
    // 前序遍历(非递归) => 栈
    // 时间复杂度：O(n), n 表示二叉树节点个数 => 遍历树的每个节点 O(1) => n * O(1) => O(n)
    // 空间复杂度：O(n)
    public static List<Integer> preOrder(TreeNode<Integer> root) {
        ArrayList<Integer> res = new ArrayList<>();
        if (root == null) return res;

        Stack<TreeNode<Integer>> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode<Integer> curr = stack.pop();
            res.add(curr.data);

            // 在访问左子树前，先将右子树存下，以便在左子树访问完后，能找到右子树进行访问
            //  => 右子树先存后用  => Stack

            // 1.先加入右子节点，再加入左子节点
            // 2.curr.right 为 null，则是没有必要放入 stack，同时也是不能放入 stack
            if (curr.right != null) stack.push(curr.right);
            if (curr.left != null) stack.push(curr.left);

            // 经过上面两步操作，cur 节点的左右子节点已经判断过了，此时 while 循环结束，
            // 再去从 Stack 的栈顶弹出元素，继续判断左右节点，依次循环
        }
        return res;
    }

    // KeyPoint 方法二 前序遍历(递归)
    // 递归条件
    // 1 大问题 => 拆解子问题 ; 子问题解决 => 大问题解决
    // 2 子问题和大问题解决方法逻辑是一样的 => 递归公式
    // 3 一定存在递归终止条件(最小子问题的解已知) => 递归边界
    // 时间复杂度：O(n)
    // 空间复杂度：O(n) => 申请了 ArrayList<Integer> res 存储遍历结果
    public static List<Integer> preOrderR(TreeNode<Integer> root) {
        ArrayList<Integer> res = new ArrayList<>();
        preOrder(root, res);
        return res;
    }

    // 递归调用 时间复杂度：O(n)
    // 递归调用 空间复杂度：最坏是 O(n)，最好的情况 O(logn)
    // 1.递归过程，虽然没有直接申请额外的空间，但程序因为是递归的，需要申请系统栈，
    //   而系统栈是需要消耗空间的，递归调用几次，则系统栈中压入几个栈帧，因此，系统栈使用的大小取决于树的高度
    // 2.树的高度需要分情况：最好(完全二叉树) => O(logn)，最差(斜树) => O(n)
    private static void preOrder(TreeNode<Integer> node, List<Integer> res) {

        // ArrayList<Integer> res = new ArrayList<>();
        // 用来收集结果 全局变量 res 不能定义在递归方法中。
        // 否则，次递归调用时都会创建一个新的 res，则不是唯一全局变量
        // => 将 res 当做形参，传入到递归方法中，或者将 res 设置成员变量

        // 退出边界条件
        if (node == null) return;

        // 先访问当前节点
        res.add(node.data);

        // 再前序遍历左子树
        preOrder(node.left, res);
        // 最后前序遍历右子树
        preOrder(node.right, res);
    }
}
