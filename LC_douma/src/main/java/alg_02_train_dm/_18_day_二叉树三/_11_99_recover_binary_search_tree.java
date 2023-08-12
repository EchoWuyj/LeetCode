package alg_02_train_dm._18_day_二叉树三;

/**
 * @Author Wuyj
 * @DateTime 2023-08-11 17:10
 * @Version 1.0
 */
public class _11_99_recover_binary_search_tree {
     /*
        99. 恢复二叉搜索树
        给你二叉搜索树的根节点 root ，该树中的两个节点被错误地交换。
        请在不改变其结构的情况下，恢复这棵树。

           3 ←          2
         /   \        /   \
        1     4  =>   1    4
             /            /
            2 ←          3

        输入：root = [3,1,4,null,null,2]
        输出：[2,1,4,null,null,3]
        解释：2 不能在 3 的右子树中，因为 2 < 3 。交换 2 和 3 使二叉搜索树有效。

        提示：
        树上节点的数目在范围 [2, 1000] 内
        -231 <= Node.val <= 231 - 1

        KeyPoint 补充说明
        若题目没有明说二叉搜索树中没有重复元素，则默认没有

     */

    private TreeNode prev = null;
    // 使用 x 和 y 记录错误交换的两个节点
    private TreeNode x = null;
    private TreeNode y = null;

    public void recoverTree(TreeNode root) {
        dfs(root);
        // 交换 x 和 y 值
        int tmp = x.val;
        x.val = y.val;
        y.val = tmp;
    }

    private void dfs(TreeNode node) {

        //         6
        //      /     \
        //     2       4 ← y
        //   / \       / \
        //  0   8 ← x 7   9
        //     / \
        //    3  5

        if (node == null) return;
        dfs(node.left);
        // KeyPoint 中序遍历中执行具体操作 => 在两个 dfs 代码之间
        // 处理当前的节点
        // => 利用 BST 性质：中序遍历，输出结果是升序的
        // => 在中序遍历中，记住不符合升序的节点 => 不符合 BST 性质的节点
        if (prev != null && node.val < prev.val) {

            // 1.遇到第一不符合 BST 性质的节点
            //   => 先用 y 记录 node
            //   => prev 节点，将其记录到 x 中

            // 2.遇到第二不符合 BST 性质的节点
            //   => 新的 node 更新 y
            //   => 直接 return，结束循环

            // => 这种代码形式，x 和 y 能处理两节点 相邻 或者 分开 的情况

            y = node;
            if (x == null) {
                // 第一个不符合 BST 性质的节点
                x = prev;
            } else {
                return;
            }
        }
        // 更新 prev
        prev = node;
        dfs(node.right);
    }

    // KeyPoint 核心：加强对二叉搜索树理解

    //        4
    //      /  \
    //     2     6
    //    / \   / \
    //   1  3  5   7
    //
    //  写二叉搜索树
    //  => 按照'左根右'顺序，尖三角'^'形式，同时保证数字严格递增

}
