package alg_02_train_dm._17_day_二叉树二_二刷;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-04-01 21:29
 * @Version 1.0
 */
public class _10_257_binary_tree_paths1 {
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

        提示：
        树中节点的数目在范围 [1, 100] 内
        -100 <= Node.val <= 100

     */

    // KeyPoint 方法一 DFS
    public List<String> binaryTreePaths1(TreeNode root) {
        List<String> res = new ArrayList<>();
        // root 节点传入""
        dfs(root, "", res);
        return res;
    }

    // KeyPoint 一般情况
    // 1.前序遍历，递归函数不需要返回值，使用 void 即可
    // 2.后续遍历，递归函数需要有返回值，左右子树计算好的统计信息，返回给当层 root 节点进一步整合
    // 递归函数形参需要传入父路径，在其基础上拼接左右子节点以及 "->"
    private void dfs(TreeNode node, String parentPath, List<String> res) {
        if (node == null) return;

        // 叶子节点
        if (node.left == null && node.right == null) {
            res.add(parentPath + node.val);
            return;
        }

        // KeyPoint 注意事项
        // 1.在递归访问节点中拼接 "->"，故回溯到上层时，不用移除下层节点 "->"
        // 2.递归函数形参，不能传入 node.left.val，或者 node.right.val，
        //   若 node.left 或者 node.right 为 null，则空指针异常
        dfs(node.left, parentPath + node.val + "->", res);
        dfs(node.right, parentPath + node.val + "->", res);

        // KeyPoint 补充说明
        // 回溯过程中，拼接 "->" 字符串，不推荐使用 StringBuilder，因为 deleteCharAt
        // 将 "1->2->5" 的 5 移除之后，"1->2->" 中的 "->" 没有删除，回溯中存在 bug
        // 区别于：path.remove(path.size() - 1);

        // List<String> res = new ArrayList<>();
        // res.add("1" + "->");
        // res.add("2");
        // System.out.println(res.toString());
        // 其中 [1->, 2] 中间存在 ','，不符合题目要求，也不行
    }

    // KeyPoint 方法一 DFS => 另外一种实现
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> res = new ArrayList<>();
        List<String> path = new ArrayList<>();
        dfs(root, path, res);
        return res;
    }

    public static void dfs(TreeNode node, List<String> path, List<String> res) {
        if (node == null) return;
        path.add(node.val + "");

        if (node.left == null && node.right == null) {
            // KeyPoint 注意事项
            // 1.字符串拼接 API => String.join => 需要掌握
            // 2.path 中泛型得是 String，否则无法使用 String.join
            String strPath = String.join("->", path);
            res.add(strPath);
        }
        dfs(node.left, path, res);
        dfs(node.right, path, res);
        path.remove(path.size() - 1);
    }
}
