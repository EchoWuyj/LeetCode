package alg_02_train_dm._16_day_二叉树一_二刷;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @Author Wuyj
 * @DateTime 2023-06-29 15:31
 * @Version 1.0
 */
public class _04_102_binary_tree_level_order_traversal2 {

    // 树的节点 + 层数
    // => 封装成 Node 节点
    // => 处理节点一种方式，别的题目也可以这样做，学会举一反三
    class Node {
        TreeNode node;
        int level;

        Node(TreeNode node, int level) {
            this.node = node;
            this.level = level;
        }
    }

    // KeyPoint 方法二 层序遍历 => 前序遍历-迭代 实现
    // 前序遍历无法像层次遍历那样知道节点层数，故需要在前序遍历中
    // 需要标记节点层次，并根据标记层次将其放到对应的结果集中
    public List<List<Integer>> levelOrder2(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;

        Stack<Node> stack = new Stack<>();
        stack.push(new Node(root, 0));
        while (!stack.isEmpty()) {
            // 前序遍历，stack 每次 pop 都是该层的第一个节点
            Node node = stack.pop();
            TreeNode curr = node.node;
            int currLevel = node.level;

            // 对栈中弹出的节点进行判断
            // 若为 currLevel 的第一个节点，创建该层的结果集
            // 否则将节点放到对应所属层的结果集中
            if (res.size() == currLevel) {
                List<Integer> levelNodes = new ArrayList<>();
                levelNodes.add(curr.val);
                res.add(levelNodes);
            } else {
                // 通过 currLevel 索引，获取 currLevel 对应的结果集
                res.get(currLevel).add(curr.val);
            }

            if (curr.right != null) {
                stack.push(new Node(curr.right, currLevel + 1));
            }
            if (curr.left != null) {
                stack.push(new Node(curr.left, currLevel + 1));
            }
        }
        return res;
    }
}
