package alg_02_train_wyj._17_day_二叉树二;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @Author Wuyj
 * @DateTime 2023-04-07 9:52
 * @Version 1.0
 */
public class _10_257_binary_tree_paths {
    public List<String> binaryTreePaths1(TreeNode root) {
        List<String> res = new ArrayList<>();
        dfs(root, "", res);
        return res;
    }

    private void dfs(TreeNode node, String parentPath, List<String> res) {
        if (node == null) return;
        if (node.left == null && node.right == null) {
            res.add(parentPath + node.val);
        }

        dfs(node.left, parentPath + node.val + "->", res);
        dfs(node.right, parentPath + node.val + "->", res);
    }

    public List<String> binaryTreePaths(TreeNode root) {
        List<String> res = new ArrayList<>();
        if (root == null) return res;
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        Queue<String> pathQueue = new LinkedList<>();

        nodeQueue.offer(root);
        pathQueue.offer(Integer.toString(root.val));

        while (!nodeQueue.isEmpty()) {
            TreeNode node = nodeQueue.poll();
            String path = pathQueue.poll();

            if (node.left == null && node.right == null) {
                res.add(path);
                continue;
            }

            if (node.left != null) {
                nodeQueue.offer(node.left);
                pathQueue.offer(new StringBuilder(path)
                        .append("->").append(node.left.val).toString());
            }

            if (node.right != null) {
                nodeQueue.offer(node.right);
                pathQueue.offer(new StringBuilder(path)
                        .append("->").append(node.right.val).toString());
            }
        }
        return res;
    }
}
