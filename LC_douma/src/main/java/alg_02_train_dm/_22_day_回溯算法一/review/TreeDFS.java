package alg_02_train_dm._22_day_回溯算法一.review;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-04-01 19:12
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

    // KeyPoint 该代码在学习回溯中非常重要，后续基本上所有算法都是由该段代码演化
    //          => 一般所有回溯算法题，都可以抽象成树 dfs
    public List<TreeNode> preOrder(TreeNode root) {
        List<TreeNode> res = new ArrayList<>();
        dfs1(root, res);
        return res;
    }

    // KeyPoint 每次调用 dfs，都是处理树中一个节点
    //          dfs 逻辑 => 就是树中一个节点计算逻辑
    // 二叉树
    private void dfs1(TreeNode node, List<TreeNode> res) {

        if (node == null) return;

        // 递的过程，执行操作，处理当前节点
        res.add(node);

        // 递的入口
        dfs1(node.left, res);
        // 回溯，遍历右子树

        // 递的入口
        dfs1(node.right, res);
        // 回溯
    }

    class Node {
        int val;
        List<Node> children;

        public Node(int val, List<Node> children) {
            this.val = val;
            this.children = children;
        }
    }

    // 多叉树
    private void dfs2(Node node, List<Integer> res) {
        if (node == null) return;
        res.add(node.val);
        for (Node child : node.children) {
            dfs2(child, res);
        }
    }
}
