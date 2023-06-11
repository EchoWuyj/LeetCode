package alg_02_train_wyj._16_day_二叉树一;

import java.util.*;

/**
 * @Author Wuyj
 * @DateTime 2023-04-02 12:32
 * @Version 1.0
 */
public class _04_102_binary_tree_level_order_traversal {
    public List<List<Integer>> levelOrder1(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            ArrayList<Integer> levelRes = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                levelRes.add(cur.val);
                if (cur.left != null) queue.offer(cur.left);
                if (cur.right != null) queue.offer(cur.right);
            }
            res.add(levelRes);
        }
        return res;
    }

    class Node {
        TreeNode treeNode;
        int level;

        public Node(TreeNode treeNode, int level) {
            this.treeNode = treeNode;
            this.level = level;
        }
    }

    public List<List<Integer>> levelOrder2(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        Deque<Node> stack = new ArrayDeque<>();
        stack.push(new Node(root, 0));
        while (!stack.isEmpty()) {
            Node node = stack.poll();
            TreeNode treeNode = node.treeNode;
            int level = node.level;

            if (res.size() == level) {
                ArrayList<Integer> levelRes = new ArrayList<>();
                levelRes.add(treeNode.val);
                res.add(levelRes);
            } else {
                res.get(level).add(treeNode.val);
            }
            if (treeNode.right != null) stack.push(new Node(treeNode.right, level + 1));
            if (treeNode.left != null) stack.push(new Node(treeNode.left, level + 1));
        }
        return res;
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        preOrder(root, 0, res);
        return res;
    }

    public void preOrder(TreeNode root, int level, List<List<Integer>> res) {
        if (root == null) return;
        if (res.size() == level) {
            ArrayList<Integer> levelRes = new ArrayList<>();
            levelRes.add(root.val);
            res.add(levelRes);
        } else {
            res.get(level).add(root.val);
        }

        preOrder(root.left, level + 1, res);
        preOrder(root.right, level + 1, res);
    }
}
