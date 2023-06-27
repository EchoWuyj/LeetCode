package alg_02_train_wyj._22_day_回溯算法一.review;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-04-08 19:50
 * @Version 1.0
 */
public class TreeDFS {

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int data) {
            this.val = data;
        }
    }

    public List<TreeNode> preOrder(TreeNode root) {
        List<TreeNode> res = new ArrayList<>();
        dfs(root, res);
        return res;
    }

    private void dfs(TreeNode node, List<TreeNode> list) {
        if (node == null) return;
        list.add(node);

        dfs(node.left, list);
        dfs(node.right, list);
    }

    class Node {
        int val;
        List<Node> children;

        Node(int val, List<Node> children) {
            this.val = val;
            this.children = children;
        }
    }

    public void dfs(Node node, List<Integer> res) {
        if (node == null) return;
        res.add(node.val);

        for (Node subNode : node.children) {
            dfs(subNode, res);
        }
    }
}
