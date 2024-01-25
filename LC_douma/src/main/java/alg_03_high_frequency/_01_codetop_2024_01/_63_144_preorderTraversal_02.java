package alg_03_high_frequency._01_codetop_2024_01;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @Author Wuyj
 * @DateTime 2024-01-16 16:38
 * @Version 1.0
 */
public class _63_144_preorderTraversal_02 {

    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode cur = stack.pop();
            res.add(cur.val);
            // 先判断是否为空，再加入右子树，最后加入左子树
            // 否则调用 cur.val 有可能为空指针异常
            if (cur.right != null) stack.push(cur.right);
            if (cur.left != null) stack.push(cur.left);
        }
        return res;
    }
}
