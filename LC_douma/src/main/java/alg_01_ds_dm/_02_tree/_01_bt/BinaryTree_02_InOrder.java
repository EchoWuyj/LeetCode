package alg_01_ds_dm._02_tree._01_bt;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @Author Wuyj
 * @DateTime 2023-03-30 23:57
 * @Version 1.0
 */
public class BinaryTree_02_InOrder {

    // KeyPoint 2 中序遍历
    // 中序遍历
    // 时间复杂度：O(n), n 表示二叉树节点个数
    // 空间复杂度：O(n)
    public static List<Integer> inOrder(TreeNode<Integer> root) {

        // KeyPoint 通过具体的小样本案例来梳理大致的代码逻辑框架
        //          之后再进行边界条件的完善，不要想着一口气直接搞出来

        ArrayList<Integer> res = new ArrayList<>();
        if (root == null) return res;
        Stack<TreeNode<Integer>> stack = new Stack<>();
        // 定义 curr 用于遍历每个子节点的指针，并且是从 root 开始的
        TreeNode<Integer> curr = root;

        // 循环处的结束条件：curr == null && stack.isEmpty()， 即两者是 && 关系，不是 || 的关系
        // 结束反面 => 循环处执行 => curr != null || !stack.isEmpty()
        // KeyPoint  while 循环在处理 && 还是 || 得仔细，不是想当然是认为是 &&
        while (curr != null || !stack.isEmpty()) {

            // KeyPoint 1 左子树处理逻辑
            // 循环找到树的最左子节点，直到 cur = null
            // 同时将沿途遇到节点先加入栈中，后面处理 => 中序遍历还是借助栈实现
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }

            // cur 走到最左，cur = null，此时处理栈顶元素
            TreeNode<Integer> node = stack.pop();
            // KeyPoint 2 根处理逻辑 => 不同的题目中，处理逻辑不同，这里只是将其加入 res
            res.add(node.data);

            // KeyPoint 3 右子树处理逻辑
            // cur 指向出栈后节点的右节点
            curr = node.right;
        }
        return res;
    }

    // KeyPoint 中序遍历(递归)
    public static List<Integer> inOrderR(TreeNode<Integer> root) {
        List<Integer> res = new ArrayList<>();
        inOrder(root, res);
        return res;
    }

    private static void inOrder(TreeNode<Integer> node, List<Integer> res) {
        if (node == null) return;
        inOrder(node.left, res);
        res.add(node.data);
        inOrder(node.right, res);
    }
}
