package alg_02_体系班_zcy.class11;

import java.util.ArrayList;
import java.util.Stack;

/**
 * @Author Wuyj
 * @DateTime 2022-10-05 19:45
 * @Version 1.0
 */
public class Code06_SuccessorNode02 {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    // 时间复杂度:O(N),N是节点数量
    // 思路:通过头节点生成中序遍历的序列,在序列中找到p节点在那里,从而得到其后继节点
    public TreeNode inorderSuccessor(TreeNode head, TreeNode p) {
        if (head == null) {
            return head;
        }
        // 记录中序序列
        ArrayList<TreeNode> list = new ArrayList<>();
        if (head != null) {
            Stack<TreeNode> stack = new Stack<>();
            while (!stack.isEmpty() || head != null) {
                if (head != null) {
                    stack.push(head);
                    head = head.left;
                } else {
                    head = stack.pop();
                    list.add(head);
                    head = head.right;
                }
            }
        }

        // 再遍历中序序列,找目标节点的后续节点
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).val == p.val) {
                // 边界情况单独判断,后继节点是中序序列最后一个节点的情况,,防止索引越界
                if (i == list.size() - 1) {
                    return null;
                } else {
                    // KeyPoint 涉及到i+1运算,则需要考虑是否越界的情况
                    return list.get(i + 1);
                }
            }
        }

        // 虽然这样写,但是一定能找到,即永远不会执行!
        return null;
    }
}

