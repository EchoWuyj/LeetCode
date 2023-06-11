package alg_02_train_dm._19_day_DFS_BFS;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @Author Wuyj
 * @DateTime 2023-05-31 12:02
 * @Version 1.0
 */
public class _03_429_n_ary_tree_level_order_traversal {
     /*
        429. N 叉树的层序遍历
        给定一个 N 叉树，返回其节点值的 层序遍历 。
        N 叉树 在输入中按层序遍历进行序列化表示，
        每组子节点由空值 null 分隔（请参见示例）。

        输入：root = [1,null,3,2,4,null,5,6]
                 1
              /  |  \
             3   2   4
           /  \
          5    6

          输出： [[1],[3,2,4],[5,6]]

     */

    // KeyPoint 方法一 BFS
    public List<List<Integer>> levelOrder1(Node root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;

        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> levelNodes = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                Node curr = queue.poll();
                levelNodes.add(curr.val);
                for (Node child : curr.children) {
                    if (child != null) queue.offer(child);
                }
            }
            res.add(levelNodes);
        }
        return res;
    }

    // KeyPoint 方法二 DFS (前序遍历) => 需要掌握，以后常使用
    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(root, 0, res);
        return res;
    }

    private void dfs(Node node, int currLevel, List<List<Integer>> res) {
        if (node == null) return;

        // 处理当前遍历的节点
        if (res.size() == currLevel) {
            List<Integer> levelNodes = new ArrayList<>();
            levelNodes.add(node.val);
            res.add(levelNodes);
        } else {
            res.get(currLevel).add(node.val);
        }

        for (Node child : node.children) {
            dfs(child, currLevel + 1, res);
        }
    }
}
