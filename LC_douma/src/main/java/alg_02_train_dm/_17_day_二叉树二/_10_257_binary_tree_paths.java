package alg_02_train_dm._17_day_二叉树二;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @Author Wuyj
 * @DateTime 2023-04-01 21:29
 * @Version 1.0
 */
public class _10_257_binary_tree_paths {
     /*
        257. 二叉树的所有路径
        给定一个二叉树，返回所有从根节点到叶子节点的路径。

        说明: 叶子节点是指没有子节点的节点。

        输入:
           1
         /   \
        2     3
         \
          5
        输出: ["1->2->5", "1->3"]

        输出: [
                [1,2,5],
                [1,3]
              ]
     */

    // KeyPoint 方法一 DFS
    public List<String> binaryTreePaths1(TreeNode root) {
        List<String> res = new ArrayList<>();
        // root 节点传入""
        dfs(root, "", res);
        return res;
    }

    // 递归函数形参需要传入父路径，在其基础上拼接左右子节点以及 "->"
    // 一般情况
    // 1.前序遍历，递归函数不需要返回值，使用 void 即可
    // 2.后续遍历，递归函数需要有返回值，左右子树计算好的统计信息，返回给当层 root 节点进一步整合
    private void dfs(TreeNode node, String parentPath, List<String> res) {
        if (node == null) return;
        if (node.left == null && node.right == null) {
            res.add(parentPath + node.val);
            return;
        }
        // 在递归访问节点中拼接 "->"，故回溯上层时不用移除下层节点 "->"
        // 递归函数形参，不能传入 node.left.val，或者 node.right.val，空指针异常
        dfs(node.left, parentPath + node.val + "->", res);
        dfs(node.right, parentPath + node.val + "->", res);
    }

    // KeyPoint 有问题的方法，测试用例通过一半
    public static List<String> binaryTreePaths2(TreeNode root) {
        List<String> res = new ArrayList<>();
        StringBuilder builder = new StringBuilder();
        if (root == null) return res;
        dfs(root, builder, res);
        return res;

//        List<String> res = new ArrayList<>();
//        res.add("1" + "->");
//        res.add("2");
//        System.out.println(res.toString());
        // [1->, 2] 中间存在 ','，不符合题目要求

    }

    private static void dfs(TreeNode root, StringBuilder builder, List<String> res) {
        if (root == null) return;
        if (root.left == null && root.right == null) {
            builder.append(root.val + "");
            res.add(new String(builder));
        } else {
            builder.append(root.val + "->");
        }
        dfs(root.left, builder, res);
        dfs(root.right, builder, res);
        // 使用 builder 的 deleteCharAt 方法，将 "1->2->5" 的 5 移除之后
        // 还剩下 "1->2->"，同时剩下的 "->"，也是字符这样回溯过程就出现了问题
        // builder 和 ArrayList 的 path 不一样，关键还添加了 "->" 字符
        builder.deleteCharAt(builder.length() - 1);
    }

    // KeyPoint 方法二 BFS => 高性能，打败 100 %
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> paths = new ArrayList<String>();
        if (root == null) {
            return paths;
        }
        Queue<TreeNode> nodeQueue = new LinkedList<TreeNode>();
        Queue<String> pathQueue = new LinkedList<String>();

        nodeQueue.offer(root);
        pathQueue.offer(Integer.toString(root.val));

        while (!nodeQueue.isEmpty()) {
            TreeNode node = nodeQueue.poll();
            String path = pathQueue.poll();

            if (node.left == null && node.right == null) {
                paths.add(path);
                continue;
            }

            if (node.left != null) {
                nodeQueue.offer(node.left);
                // 注意，append(node.left.val)，不是 node.left，因为对 node.left 已经做了判空，不存在空指针异常
                pathQueue.offer(new StringBuilder(path).append("->").append(node.left.val).toString());
            }

            if (node.right != null) {
                nodeQueue.offer(node.right);
                pathQueue.offer(new StringBuilder(path).append("->").append(node.right.val).toString());
            }
        }
        return paths;
    }
}
