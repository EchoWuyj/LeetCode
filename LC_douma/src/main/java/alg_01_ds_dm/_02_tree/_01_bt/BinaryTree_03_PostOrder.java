package alg_01_ds_dm._02_tree._01_bt;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * @Author Wuyj
 * @DateTime 2023-03-30 23:57
 * @Version 1.0
 */

public class BinaryTree_03_PostOrder {

    // 变形的前序遍历：根 -> 右 -> 左
    // 反转 => 后续遍历：左 -> 右 -> 根

    // KeyPoint 方法一 后序遍历(迭代) => 后指的是根的位置
    // 时间复杂度：O(n), n 表示二叉树节点个数
    // 空间复杂度：O(n)
    public static List<Integer> postOrder(TreeNode<Integer> root) {

        // 使用 LinkedList 为了后面使用 addFirst，每次都从头 add 元素，自然实现反转
        // 父类引用，也必须是 LinkedList，不能是 List，否则无法调用 addFirst 方法
        LinkedList<Integer> res = new LinkedList<>();
        if (root == null) return res;
        Stack<TreeNode<Integer>> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode<Integer> curr = stack.pop();
            // 优化：避免执行 Collections.reverse(res)，加入节点时维护好逆序
            // 1.add 从尾加入元素顺序 1,2,3,4
            // 2.addFirst 每次都从头加入元素，4,3,2,1 从而直接实现了反转
            res.addFirst(curr.data);
            if (curr.left != null) stack.push(curr.left);
            if (curr.right != null) stack.push(curr.right);
        }

        // 对集合进行反转的 API，直接是在原 res 上进行修改的
        // Collections.reverse(res);
        return res;
    }

    // KeyPoint 方法二 后序遍历(递归)
    public static List<Integer> postOrderR(TreeNode<Integer> root) {
        List<Integer> res = new ArrayList<>();
        postOrder(root, res);
        return res;
    }

    private static void postOrder(TreeNode<Integer> node, List<Integer> res) {
        if (node == null) return;
        postOrder(node.left, res);
        postOrder(node.right, res);
        // 经过左右子树之后(收集左右子树的信息)，返回到上层 root 节点，然后再去进行操作
        res.add(node.data);
    }
}
