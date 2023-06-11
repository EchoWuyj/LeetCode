package alg_02_train_dm._19_day_DFS_BFS;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @Author Wuyj
 * @DateTime 2023-05-31 12:02
 * @Version 1.0
 */
public class _01_589_n_ary_tree_preorder_traversal {
     /*
        589. N 叉树的前序遍历
        给定一个 N 叉树，返回其节点值的 前序遍历
        N 叉树 在输入中按层序遍历进行序列化表示
        每组子节点由空值 null 分隔（请参见示例）

        输入：root = [1,null,3,2,4,null,5,6]
                 1
              /  |  \
             3   2   4
           /  \
          5    6

         输出： [1, 3, 5, 6, 2, 4]

        进阶：
        递归法很简单，你可以使用迭代法完成此题吗?
     */

    //  KeyPoint 方法一 递归
    public List<Integer> preorder(Node root) {
        ArrayList<Integer> res = new ArrayList<>();
        if (root == null) return res;
        dfs(root, res);
        return res;
    }

    private void dfs(Node node, List<Integer> res) {
        if (node == null) return;
        // 处理当前遍历的节点
        res.add(node.val);
        // n 叉树，有多个子节点，循环遍历
        for (Node child : node.children) {
            dfs(child, res);
        }
    }

    // KeyPoint 方法二 迭代
    public List<Integer> preorder1(Node root) {
        ArrayList<Integer> res = new ArrayList<>();
        if (root == null) return res;

        Stack<Node> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            Node curr = stack.pop();
            res.add(curr.val);
            List<Node> children = curr.children;
            int size = children.size();
            // 从后往前遍历，将 N 个子节点放入到栈中
            for (int i = size - 1; i >= 0; i--) {
                stack.push(children.get(i));
            }
        }
        return res;
    }
}
