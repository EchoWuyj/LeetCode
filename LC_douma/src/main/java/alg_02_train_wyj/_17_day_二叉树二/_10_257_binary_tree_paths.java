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

    public void dfs(TreeNode root, String parentPath, List<String> res) {
        if (root == null) return;
        if (root.left == null && root.right == null) {
            res.add(parentPath + root.val);
            return;
        }
        dfs(root.left, parentPath + root.val + "->", res);
        dfs(root.right, parentPath + root.val + "->", res);
    }

    public List<String> binaryTreePaths(TreeNode root) {
        List<String> paths = new ArrayList<>();
        if (root == null) return paths;
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        Queue<String> pathQueue = new LinkedList<>();
        nodeQueue.offer(root);
        pathQueue.offer(Integer.toString(root.val));
        while (!nodeQueue.isEmpty()) {
            TreeNode node = nodeQueue.poll();
            String path = pathQueue.poll();
            if (node.right == null && node.left == null) {
                paths.add(path);
                continue;
            }
            if (node.left != null) {
                nodeQueue.offer(node.left);
                pathQueue.offer(new StringBuilder(path).append("->").append(node.left.val).toString());
            }

            if (node.right != null) {
                nodeQueue.offer(node.right);
                pathQueue.offer(new StringBuilder(path).append("->").append(node.right.val).toString());
            }
        }
        return paths;
    }
}
