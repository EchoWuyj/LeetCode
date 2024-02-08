package alg_03_high_frequency._01_codetop_2024_01;

import java.util.*;

/**
 * @Author Wuyj
 * @DateTime 2024-01-13 21:09
 * @Version 1.0
 */
public class _35_94_inorderTraversal {

    // 二叉树的中序遍历
    // 迭代实现
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        // 定义栈
        Stack<TreeNode> stack = new Stack<>();
        // cur 从 root 节点开始遍历
        TreeNode cur = root;
        // 循环条件：或的关系 ||
        while (cur != null || !stack.isEmpty()) {
            // 一直往左遍历，直到为空
            while (cur != null) {
                // 不断压栈
                stack.push(cur);
                cur = cur.left;
            }
            // 从栈中弹出一个元素，作为中序遍历到的元素
            TreeNode node = stack.pop();
            res.add(node.val);
            // 将 cur 移动到 node 节点的右子树，重新给 cur 赋值
            cur = node.right;
        }
        return res;
    }
}
