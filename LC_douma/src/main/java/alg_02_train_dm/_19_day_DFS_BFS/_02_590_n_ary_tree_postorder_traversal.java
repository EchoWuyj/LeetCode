package alg_02_train_dm._19_day_DFS_BFS;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * @Author Wuyj
 * @DateTime 2023-05-31 12:02
 * @Version 1.0
 */
public class _02_590_n_ary_tree_postorder_traversal {
      /*
        590. N 叉树的后序遍历
        给定一个 N 叉树，返回其节点值的 后序遍历 。
        N 叉树 在输入中按层序遍历进行序列化表示，
        每组子节点由空值 null 分隔（请参见示例）。

        输入：root = [1,null,3,2,4,null,5,6]
                 1
              /  |  \
             3   2   4
           /  \
          5    6

        输出： [5, 6, 3, 2, 4, 1]

        进阶：
        递归法很简单，你可以使用迭代法完成此题吗?

        KeyPoint 补充说明：N 叉树有多个子节点，无法确定根节点放在那里才叫中间，故 N 叉树没有中序遍历
     */

    // KeyPoint 方法一 迭代
    public List<Integer> postorder1(Node root) {
        LinkedList<Integer> res = new LinkedList<>();
        if (root == null) return res;

        Stack<Node> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            Node curr = stack.pop();
            // 每次都从 First 进行添加，从而实现逆序 reverse
            res.addFirst(curr.val);
            List<Node> children = curr.children;
            for (Node child : children) {
                stack.push(child);
            }
        }
        return res;
    }

    // KeyPoint 方法二 递归
    public List<Integer> postorder(Node root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        dfs(root, res);
        return res;
    }

    private void dfs(Node node, List<Integer> res) {
        if (node == null) return;
        for (Node child : node.children) {
            dfs(child, res);
        }
        res.add(node.val);
    }
}
