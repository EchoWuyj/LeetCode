package alg_02_train_dm._17_day_二叉树二_二刷;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @Author Wuyj
 * @DateTime 2023-08-10 19:10
 * @Version 1.0
 */
public class _10_257_binary_tree_paths2 {

    // KeyPoint 方法二 BFS => 高性能，打败 100 % => 需要掌握
    public List<String> binaryTreePaths(TreeNode root) {

        // 输入:
        //        1
        //      /   \
        //     2     3
        //      \
        //       5
        //
        // 输出: ["1->2->5", "1->3"]

        List<String> res = new ArrayList<String>();
        if (root == null) return res;

        Queue<TreeNode> nodeQueue = new LinkedList<TreeNode>();
        // 在 BFS 过程中，每遍历一个节点，都会有一个 path 与之对应
        // 直到遇到叶子节点，将完整 path 加入到 res 中
        Queue<String> pathQueue = new LinkedList<String>();

        nodeQueue.offer(root);
        // KeyPoint 区别：两者 API
        // 1.Integer.toString()
        // 2.Integer.parseInt()
        pathQueue.offer(Integer.toString(root.val));

        while (!nodeQueue.isEmpty()) {
            TreeNode node = nodeQueue.poll();
            String path = pathQueue.poll();

            // 叶子节点
            if (node.left == null && node.right == null) {
                res.add(path);
                // 结束后面循环
                continue;
            }

            if (node.left != null) {
                nodeQueue.offer(node.left);
                // KeyPoint 注意事项
                // append(node.left.val)，不是 node.left，node.left 表示节点
                // 同时，因为对 node.left 已经做了判空，不存在空指针异常
                pathQueue.offer(new StringBuilder(path).append("->").append(node.left.val).toString());
            }

            if (node.right != null) {
                nodeQueue.offer(node.right);
                pathQueue.offer(new StringBuilder(path).append("->").append(node.right.val).toString());
            }
        }
        return res;
    }
}
