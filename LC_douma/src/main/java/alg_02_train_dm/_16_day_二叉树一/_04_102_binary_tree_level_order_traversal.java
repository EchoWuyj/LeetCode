package alg_02_train_dm._16_day_二叉树一;

import java.util.*;

/**
 * @Author Wuyj
 * @DateTime 2023-03-31 12:34
 * @Version 1.0
 */

// 详细注释 BinaryTree_04_LevelOrder
public class _04_102_binary_tree_level_order_traversal {
      /*
         102. 二叉树的层序遍历
         给你一个二叉树，请你返回其按 层序遍历 得到的节点值。
        （即逐层地，从左到右访问所有节点）
     */

    // KeyPoint 方法一 层序遍历(迭达)
    public List<List<Integer>> levelOrder1(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            // 每轮循环遍历处理一层的节点
            int size = queue.size();
            List<Integer> levelNodes = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode curr = queue.poll();
                levelNodes.add(curr.val);
                if (curr.left != null) queue.offer(curr.left);
                if (curr.right != null) queue.offer(curr.right);
            }
            res.add(levelNodes);
        }

        return res;
    }

    // 树的节点 + 层数 => 封装成 Node 节点
    class Node {
        TreeNode node;
        int level;

        Node(TreeNode node, int level) {
            this.node = node;
            this.level = level;
        }
    }

    // KeyPoint 方法二 层序遍历(前序遍历-迭代)
    // 思路：前序遍历无法像层次遍历那样知道节点层数，故需要在前序遍历中
    //       标记节点层次，并根据标记层次将其放到对应的结果集中
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

    // KeyPoint 方法三 层序遍历(前序遍历-递归) => 需要掌握，后续多次使用，打败 100 %
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        preorder(root, 0, res);
        return res;
    }

    // 每次调用 preorder 都是访问一个节点
    // 节点和所属层的关系，通过方法参数控制
    private void preorder(TreeNode node, int currLevel, List<List<Integer>> res) {
        if (node == null) return;
        // 在递的过程，添加额外的处理逻辑：处理当前遍历的节点，将节点放到所属的结果集中
        if (res.size() == currLevel) {
            List<Integer> levelNodes = new ArrayList<>();
            levelNodes.add(node.val);
            res.add(levelNodes);
        } else {
            res.get(currLevel).add(node.val);
        }

        preorder(node.left, currLevel + 1, res);
        preorder(node.right, currLevel + 1, res);
    }
}
