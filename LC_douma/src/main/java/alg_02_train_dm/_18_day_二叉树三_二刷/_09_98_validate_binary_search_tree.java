package alg_02_train_dm._18_day_二叉树三_二刷;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-08-11 17:09
 * @Version 1.0
 */
public class _09_98_validate_binary_search_tree {
      /*

        98. 验证二叉搜索树
        给定一个二叉树，判断其是否是一个有效的二叉搜索树。

        假设一个二叉搜索树具有如下特征：
        1.节点的左子树只包含小于当前节点的数。
        2.节点的右子树只包含大于当前节点的数。
        3. 所有左子树和右子树自身必须也是二叉搜索树。

             输入:
                2
               / \
              1   3
            输出: true

            输入:
                5
               / \
              1   4
                 / \
                3   6
            输出: false

        提示：
        树中节点数目范围在[1, 104] 内
        -2^31 <= Node.val <= 2^31 - 1
     */

    // KeyPoint 方法一   1. 中序遍历 => 先拿到遍历结果，再验证顺序性
    public boolean isValidBST1(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        dfs(root, res);
        int size = res.size();
        for (int i = 1; i < size; i++) {
            // 包含等于关系 => 二叉搜索树不能有相等的数值
            if (res.get(i) <= res.get(i - 1)) return false;
        }

        return true;
    }

    private void dfs(TreeNode node, List<Integer> res) {
        if (node == null) return;
        dfs(node.left, res);
        res.add(node.val);
        dfs(node.right, res);
    }

    // KeyPoint 方法二 中序遍历 => 边遍历边验证顺序性
    // BST 很多问题，都是可以通过中序遍历解决的
    private TreeNode prev = null;
    private boolean isBST = true;

    public boolean isValidBST(TreeNode root) {
        // 空树通常认为是二叉搜索树
        if (root == null) return true;
        dfs1(root);
        return isBST;
    }

    private void dfs1(TreeNode node) {
        if (node == null) return;

        dfs1(node.left);
        // 测试用例：[2,2,2] 输出 false
        // => 二叉搜索树，元素默认是不相等的
        // => 只要 node.val = prev.val 同样认为 false
        if (prev != null && node.val <= prev.val) {
            // 不符合 BST 性质
            isBST = false;
            return;
        }
        prev = node;
        dfs1(node.right);
    }

    // KeyPoint 方法三 后序遍历
    public boolean isValidBST3(TreeNode root) {
        // MIN_VALUE < root.val < MAX_VALUE
        // 测试用例：[2147483647] 输出 true
        // => 2147483647 为 Integer.MAX_VALUE，满足 node.val >= max，return false
        //    但是实际上应该是 return true
        // => 更换 long 数据类型，不能使用 Integer
        return dfs(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    // 1.定义使用 long 类型，实际传入形参为 int 类型 => 隐式转换
    // 2.定义使用 Long 类型，实际传入形参为 int 类型 => 无法自动隐式转换，需要手动强制转换
    private boolean dfs(TreeNode node, long min, long max) {
        // 空树 => BST
        if (node == null) return true;
        // 对于 BST 每个节点范围：左边节点最大值 < node.val < 右边节点最小值
        if (node.val <= min || node.val >= max) {
            return false;
        }
        // MIN_VALUE < node.left < node.val
        boolean left = dfs(node.left, min, node.val);
        // node.val < node.right < MAX_VALUE
        boolean right = dfs(node.right, node.val, max);

        // 左右子树都是 BST
        // 同时，当前根节点 node，满足：左边节点最大值 < node.val < 右边节点最小值
        return left && right;
    }
}
