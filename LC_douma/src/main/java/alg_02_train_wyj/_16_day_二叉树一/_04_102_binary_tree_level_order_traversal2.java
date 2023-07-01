package alg_02_train_wyj._16_day_二叉树一;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @Author Wuyj
 * @DateTime 2023-06-29 15:55
 * @Version 1.0
 */
public class _04_102_binary_tree_level_order_traversal2 {
    class Node {
        TreeNode treeNode;
        int level;

        Node(TreeNode treeNode, int level) {
            this.treeNode = treeNode;
            this.level = level;
        }
    }

    public List<List<Integer>> levelOrder2(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        Stack<Node> stack = new Stack<>();
        stack.push(new Node(root, 0));
        while (!stack.isEmpty()) {
            Node cur = stack.pop();

            if (res.size() == cur.level) {
                List<Integer> levelList = new ArrayList<>();
                levelList.add(cur.treeNode.val);
                res.add(levelList);
            } else {
                res.get(cur.level).add(cur.treeNode.val);
            }

            if (cur.treeNode.right != null) {
                stack.push(new Node(cur.treeNode.right, cur.level + 1));
            }

            if (cur.treeNode.left != null) {
                stack.push(new Node(cur.treeNode.left, cur.level + 1));
            }
        }
        return res;
    }
}
