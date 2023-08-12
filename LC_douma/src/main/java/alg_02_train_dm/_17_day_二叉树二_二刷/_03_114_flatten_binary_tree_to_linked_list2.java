package alg_02_train_dm._17_day_二叉树二_二刷;

import java.util.ArrayDeque;

/**
 * @Author Wuyj
 * @DateTime 2023-06-29 21:37
 * @Version 1.0
 */
public class _03_114_flatten_binary_tree_to_linked_list2 {

    // KeyPoint 方法二 先序遍历(迭代法) => 只要遍历一遍
    // 边遍历边串联，串联节点过程需要保证是先序遍历
    // 时间复杂度 O(n)
    // 空间复杂度 O(n)
    public void flatten2(TreeNode root) {
        if (root == null) return;
        ArrayDeque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);
        // cur 前一个节点
        TreeNode pre = null;
        while (!stack.isEmpty()) {
            TreeNode cur = stack.pop();

            // 判断 cur 节点是否有 pre 节点
            // => 最开始 pre == null，cur 没有 pre 节点
            //    说明 cur 刚开始为 root 节点，不执行 if 判断
            if (pre != null) {
                pre.left = null;
                // root 右子节点已经放入到栈中，所以可以调整 root 的 right 指针
                pre.right = cur;
            }
            // 获取左右子节点
            TreeNode left = cur.left, right = cur.right;
            if (right != null) {
                stack.push(right);
            }
            if (left != null) {
                stack.push(left);
            }

            // 对于下轮循环 cur = stack.pop()，此时 pre 指向上轮 cur 节点
            // => pre 为 cur 的前一个节点
            // => pre 随着 cur 更新而更新
            pre = cur;
        }
    }
}
