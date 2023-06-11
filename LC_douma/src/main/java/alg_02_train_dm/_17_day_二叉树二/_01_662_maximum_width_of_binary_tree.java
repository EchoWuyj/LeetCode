package alg_02_train_dm._17_day_二叉树二;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @Author Wuyj
 * @DateTime 2023-04-01 11:50
 * @Version 1.0
 */
public class _01_662_maximum_width_of_binary_tree {
     /*
        662. 二叉树最大宽度

         给定一个二叉树，编写一个函数来获取这个树的最大宽度。
         树的最大宽度是所有层中的最大宽度。
         这个二叉树与满二叉树（full binary tree）结构相同，但一些节点为空。
         每一层的宽度被定义为两个端点（该层最左和最右的非空节点，两端点间的null节点也计入长度）之间的长度。

            输入:
               1
             /   \
            3     2
           / \     \
          5   3     9
            输出: 4

            输入:
              1
             /
            3
           / \
          5   3
            输出: 2

            输入:
              1
             / \
            3   2
           /
          5

            输出: 2

            输入:
              1
             / \
            3   2
           /     \
          5       9
         /         \
        6           7
            输出: 8

       KeyPoint 先求出每层的宽度，比较再求出最大宽度即可
                每层宽度，关键求出该层最左节点到最右节点的长度
                给每个节点标记序号，从 1 开始，每层都是从左往右开始标记序号

     */

    // 标记序号，定义成 node
    class Node {
        TreeNode node;
        int seqNo;

        Node(TreeNode node, int seqNo) {
            this.node = node;
            this.seqNo = seqNo;
        }
    }

    // BFS
    public int widthOfBinaryTree1(TreeNode root) {
        if (root == null) return 0;
        Queue<Node> queue = new LinkedList<Node>();
        // 编号从 1 开始
        queue.offer(new Node(root, 1));
        int maxWidth = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            // for 循环遍历一层之后，startSeqNo 和 endSeqNo
            // 都已经清零，所以将其定义在 for 循环外面是比较好的
            int startSeqNo = 0, endSeqNo = 0;
            // 一层一层遍历节点
            for (int i = 0; i < size; i++) {
                Node curr = queue.poll();
                TreeNode node = curr.node;
                int seqNo = curr.seqNo;
                if (i == 0) startSeqNo = seqNo;
                if (i == size - 1) endSeqNo = seqNo;
                if (node.left != null) {
                    queue.offer(new Node(node.left, 2 * seqNo));
                }
                if (node.right != null) {
                    queue.offer(new Node(node.right, 2 * seqNo + 1));
                }
            }
            // 每层宽度，比较得最大宽度，需要加 1
            maxWidth = Math.max(maxWidth, endSeqNo - startSeqNo + 1);
        }
        return maxWidth;
    }

    // DFS
    public int widthOfBinaryTree(TreeNode root) {
        return dfs(root, 0, 1, new ArrayList<>(), new ArrayList<>());
    }

    // KeyPoint 递归函数：返回以 node 为根节点的子树的最大宽度，返回值为 int
    // 递归调用中，通过增加形参方式来传递更多信息
    // 1 level 层数，dfs 不是一层结束再去遍历下一层的，因此遍历每个节点时需要传入 level 信息
    // 2 seqNo 序号
    // 3 dfs 遍历到每个节点，并知道该节点是 start 起始节点或者是 end 结束节点，所以需要传入两个集合
    // KeyPoint 这里的 dfs 不是简单的先序或者后序，而是两者结合，分别在递和归的过程中进行不同的操作
    private int dfs(TreeNode node, int level, int seqNo,
                    List<Integer> start, List<Integer> end) {
        // 遇到 null，则最大宽度为 0
        if (node == null) return 0;

        // KeyPoint 前序(深入) -> 递过程，直到递归边界(最小子问题)
        // level 和 seqNo 的值已经在递归函数中维护好了，关键维护 start 和 end 的值
        // 通过两个 start 和 end 集合，在前序遍历的过程中进行维护

        // level 层的第一个节点
        if (start.size() == level) {
            // start 和 end 集合 0 索引位置表示 0 层的 start 和 end 节点
            start.add(seqNo);
            end.add(seqNo);
        } else {
            // 不是 level 层的第一个节点，则只是更新 end 序号即可
            end.set(level, seqNo);
        }

        // 左子树最大宽度
        int leftMaxWidth = dfs(node.left, level + 1, 2 * seqNo, start, end);
        // 右子树最大宽度
        int rightMaxWidth = dfs(node.right, level + 1, 2 * seqNo + 1, start, end);

        // KeyPoint 后序(回溯) -> 归过程，返回递归上层操作逻辑
        // 只有将左右子树都已经访问完成，维护好 start 和 end 集合之后
        // 才能计算以 root 为当根节点的子树最大宽度
        int currWidth = end.get(level) - start.get(level) + 1;

        // 比较:当前节点最大宽度，左子树最大宽度，右子树最大宽度，取最大值
        return Math.max(currWidth, Math.max(leftMaxWidth, rightMaxWidth));
    }
}
