package alg_02_train_wyj._22_day_回溯算法一;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @Author Wuyj
 * @DateTime 2023-04-08 20:18
 * @Version 1.0
 */
public class _01_112_PathSum {
    public static List<List<Integer>> allPath(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        dfs(root, path, res);
        return res;
    }

    public static void dfs(TreeNode node, List<Integer> path, List<List<Integer>> res) {
        if (node == null) return;
        path.add(node.val);
        if (node.left == null && node.right == null) {
            res.add(new ArrayList<>(path));
        }
        dfs(node.left, path, res);
        dfs(node.right, path, res);
        path.remove(path.size() - 1);
    }

    public static List<List<Integer>> allPath1(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        List<Integer> path = new ArrayList<>();
        path.add(root.val);
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        Queue<List<Integer>> pathQueue = new LinkedList<>();
        nodeQueue.add(root);
        pathQueue.add(path);
        while (!nodeQueue.isEmpty()) {
            TreeNode curNode = nodeQueue.poll();
            List<Integer> curPath = pathQueue.poll();

            if (curNode.left == null && curNode.right == null) {
                res.add(curPath);
                continue;
            }

            if (curNode.left != null) {
                nodeQueue.add(curNode.left);
                List<Integer> tmp = new ArrayList<>(curPath);
                tmp.add(curNode.left.val);
                pathQueue.add(tmp);
            }

            if (curNode.right != null) {
                nodeQueue.add(curNode.right);
                List<Integer> tmp = new ArrayList<>(curPath);
                tmp.add(curNode.right.val);
                pathQueue.add(tmp);
            }
        }
        return res;
    }

    public static void main(String[] args) {

        TreeNode root = new TreeNode(5);
        TreeNode node1 = new TreeNode(4);
        TreeNode node2 = new TreeNode(8);
        root.left = node1;
        root.right = node2;

        TreeNode node3 = new TreeNode(11);
        TreeNode node4 = new TreeNode(7);
        TreeNode node5 = new TreeNode(2);
        node1.left = node3;
        node3.left = node4;
        node3.right = node5;

        TreeNode node6 = new TreeNode(13);
        TreeNode node7 = new TreeNode(4);
        TreeNode node8 = new TreeNode(5);
        TreeNode node9 = new TreeNode(1);
        node2.left = node6;
        node2.right = node7;
        node7.left = node8;
        node7.right = node9;

        //                 5
        //               /   \
        //              4    8
        //             /    / \
        //            11  13  4
        //           / \     / \
        //          7  2    5   1

        // dfs 先序遍历
        System.out.println(allPath(root));
        // [[5, 4, 11, 7], [5, 4, 11, 2], [5, 8, 13], [5, 8, 4, 5], [5, 8, 4, 1]]

        // BFS 层次
        System.out.println(allPath1(root));
        // [[5, 8, 13], [5, 4, 11, 7], [5, 4, 11, 2], [5, 8, 4, 5], [5, 8, 4, 1]]
    }
}
