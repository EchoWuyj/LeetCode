package alg_02_train_dm._17_day_二叉树二_二刷;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @Author Wuyj
 * @DateTime 2023-08-10 18:36
 * @Version 1.0
 */
public class _05_112_PathSum1 {

    // KeyPoint 路径总和前置题目 => 打印输出二叉数所有路径
    // KeyPoint 方法二 BFS => 自己解法，性能好，高效
    public static List<List<Integer>> allPath1(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;

        List<Integer> path = new ArrayList<>();
        path.add(root.val);

        // 双队列 => 节点队列 + path 队列
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        Queue<List<Integer>> pathQueue = new LinkedList<>();
        nodeQueue.offer(root);
        pathQueue.offer(path);

        while (!nodeQueue.isEmpty()) {
            TreeNode curNode = nodeQueue.poll();
            // 外部变量名，已经存在 path，故内部变量不能再叫 path
            // KeyPoint 内部和外部变量名
            // 1.外部变量名，内部可以调用
            // 2.内部变量名，外部不可调用
            List<Integer> curPath = pathQueue.poll();

            if (curNode.left == null && curNode.right == null) {
                // 使用 curPath，不是最开始的 path，经常犯错
                res.add(new ArrayList<>(curPath));
                continue;
            }

            if (curNode.left != null) {
                nodeQueue.offer(curNode.left);
                // pathQueue.add() 形参为 List<Integer>，而 ArrayList 的 add 返回值为 boolean
                // => 不能直接在 pathQueue.add() 中 执行 ArrayList 的 add() 方法，需要分开操作
                //    错误写法 pathQueue.add(new ArrayList<>(path).add(curNode.right.val))
                // => 每个节点都有对应的独立的 newPath
                ArrayList<Integer> newPath = new ArrayList<>(curPath);
                newPath.add(curNode.left.val);
                pathQueue.add(newPath);

                // KeyPoint 输出打印找 bug
                // 除了 debug，打印输出也是找 bug 好的处理手段，灵活方便，简单好用
                // => 通过输出打印看 newPath 存储的内容
//                System.out.println(newPath);

            }

            if (curNode.right != null) {
                nodeQueue.offer(curNode.right);
                ArrayList<Integer> newPath = new ArrayList<>(curPath);
                newPath.add(curNode.right.val);
                pathQueue.add(newPath);
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

        // BFS 层次遍历
        System.out.println(allPath1(root));
        // 按照层次遍历，最先遍历完的路径为 [5, 8, 13]
        // [[5, 8, 13], [5, 4, 11, 7], [5, 4, 11, 2], [5, 8, 4, 5], [5, 8, 4, 1]]
    }
}
