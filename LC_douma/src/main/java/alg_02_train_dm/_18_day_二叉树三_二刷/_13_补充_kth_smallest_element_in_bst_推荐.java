package alg_02_train_dm._18_day_二叉树三_二刷;

import java.util.Stack;

/**
 * @Author Wuyj
 * @DateTime 2023-09-24 12:47
 * @Version 1.0
 */
public class _13_补充_kth_smallest_element_in_bst_推荐 {

    // 迭代中序遍历
    // 二叉搜索树 默认每个节点值都是不同的
    public int kthSmallest(TreeNode root, int k) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            TreeNode pop = stack.pop();
            k--;
            if (k == 0) {
                return pop.val;
            }
            cur = pop.right;
        }
        return -1;
    }
}
