package alg_02_train_dm._17_day_二叉树二;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @Author Wuyj
 * @DateTime 2023-04-01 11:52
 * @Version 1.0
 */
// KeyPoint 详细注释 _112_PathSum
public class _05_112_PathSum1 {

    /*
        112. 路径总和
        给你二叉树的根节点 root 和一个表示目标和的整数 targetSum 。
        判断该树中是否存在 根节点到叶子节点 的路径，这条路径上所有节点值相加等于目标和 targetSum 。
        如果存在，返回 true ；否则，返回  false 。

        叶子节点 是指没有子节点的节点。

                 5
               /   \
              4    8
             /    / \
            11  13  4
           / \       \
          7  2        1

         targetSum = 22
         输出: true => 5,4,11,2

        树中节点的数目在范围 [0, 5000] 内
        -1000 <= Node.val <= 1000
        -1000 <= targetSum <= 1000
     */

    /*

        回溯思想:本质 => 穷举(枚举)搜索，将所有情况都穷举，逐一进行判断
                     => 通过递归实现，递的过程 => 访问某个节点(深入处理某种情况)
                                     归的过程 <=> 回溯，即回到递归的上一层，基于上一层
                                                  再去访问其他节点(深入处理其他情况)
     */

    // 路径总和 => 前置题目 => 打印输出二叉数所有路径

    // KeyPoint 方法一 DFS
    // 1.二叉树，每个节点只访问一次，时间复杂度 O(n)
    // 2.一般意义上的回溯，是在解空间中进行搜索，并尝试各种可能的组合。
    //   在每一步的选择中，回溯算法会产生多个分支，递归地探索每个分支，
    //   直到找到问题的解 或者 遍历完所有可能的组合，故：时间复杂度是指数级别
    public static List<List<Integer>> allPath(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        dfs(root, path, res);
        return res;
    }

    // dfs 前序遍历
    public static void dfs(TreeNode node, List<Integer> path, List<List<Integer>> res) {
        // 返回上一层
        if (node == null) return;
        // 递过程:处理当前节点
        // 加入节点逻辑得在判断叶子节点之前，否则 path 没有加上叶子节点
        path.add(node.val);

        // 叶子节点
        if (node.left == null && node.right == null) {
            // 添加路径的时候需要 new 一个新的 ArrayList
            // 使得 res 中的对象和 path 不是同一个对象
            res.add(new ArrayList<>(path));
        }

        // 递过程:dfs 左右子子树
        dfs(node.left, path, res);
        dfs(node.right, path, res);

        // 归过程(回溯):从路径中删除当前节点 => 后续遍历
        // 1.注意回溯代码位置，在左右子树 dfs 之后，即左右子树都已经访问之后，再去回溯
        // 2.回溯的过程中，将当前的节点从 path 中删除
        path.remove(path.size() - 1);

        // 归过程(回溯):返回上一层
    }

    // KeyPoint 方法二 BFS -> 自己补充，性能好，高效
    public static List<List<Integer>> allPath1(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;

        List<Integer> path = new ArrayList<>();
        path.add(root.val);

        // 节点 + path 双队列
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        Queue<List<Integer>> pathQueue = new LinkedList<>();
        nodeQueue.offer(root);
        pathQueue.offer(path);

        while (!nodeQueue.isEmpty()) {
            TreeNode curNode = nodeQueue.poll();
            // 外部变量名，已经存在 path，故内部变量不能再叫 path
            // 外部变量名，内部可以调用；
            // 内部变量名，外部不可调用
            List<Integer> curPath = pathQueue.poll();

            if (curNode.left == null && curNode.right == null) {
                // 使用 curPath，不是最开始的 path，经常犯错
                res.add(new ArrayList<>(curPath));
                continue;
            }

            if (curNode.left != null) {
                nodeQueue.offer(curNode.left);
                // pathQueue.add() 形参需要 List<Integer>，而 ArrayList 的 add 返回值为 boolean
                // 不能直接在 pathQueue.add() 中 new ArrayList<>(path).add(curNode.right.val)
                ArrayList<Integer> temp = new ArrayList<>(curPath);
                temp.add(curNode.left.val);
                // 除了 debug，打印输出也是找 bug 好的处理手段，灵活使用 => 通过输出打印看 temp 存储的内容
//                System.out.println(temp);
                pathQueue.add(temp);
            }

            if (curNode.right != null) {
                nodeQueue.offer(curNode.right);
                ArrayList<Integer> temp = new ArrayList<>(curPath);
                temp.add(curNode.right.val);
                pathQueue.add(temp);
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

        // BFS 层次遍历
        System.out.println(allPath1(root));
        // 按照层次遍历，最先遍历完的路径为 [5, 8, 13]
        // [[5, 8, 13], [5, 4, 11, 7], [5, 4, 11, 2], [5, 8, 4, 5], [5, 8, 4, 1]]
    }
}
