package alg_02_train_dm._22_day_回溯算法一_2刷.review;

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
        // 左右子树
        TreeNode left;
        TreeNode right;

        TreeNode(int data) {
            this.val = data;
        }
    }

    // KeyPoint 注意事项
    // 1.该段代码在学习回溯中非常重要，后续基本上所有算法都是由该段代码演化
    //   => 一般所有回溯算法题，都可以抽象成树 dfs
    // 2.每次调用 dfs，都是处理树中一个节点
    // 3.dfs 逻辑 => 就是树中一个节点计算逻辑
    public List<Integer> preOrder(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        dfs(root, res);
        return res;
    }

    // KeyPoint 二叉树
    private void dfs(TreeNode node, List<Integer> res) {
        if (node == null) return;
        // 递的过程，执行操作，处理当前节点
        res.add(node.val);

        // 递的入口
        dfs(node.left, res);
        // 回溯，返回上一层，遍历右子树

        // 递的入口
        dfs(node.right, res);
        // 回溯，返回上一层
    }

    // KeyPoint 多叉树
    class Node {
        int val;
        // 多叉树分支
        List<Node> children;

        public Node(int val, List<Node> children) {
            this.val = val;
            this.children = children;
        }
    }

    private void dfs(Node node, List<Integer> res) {
        if (node == null) return;
        res.add(node.val);
        // children 是 child 的复数形式
        // 或者使用变量名 subNode
        for (Node child : node.children) {
            dfs(child, res);
        }
    }
}
