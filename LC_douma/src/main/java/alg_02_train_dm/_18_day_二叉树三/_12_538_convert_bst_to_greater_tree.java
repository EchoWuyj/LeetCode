package alg_02_train_dm._18_day_二叉树三;

/**
 * @Author Wuyj
 * @DateTime 2023-08-11 17:10
 * @Version 1.0
 */
public class _12_538_convert_bst_to_greater_tree {
      /*
        538. 把二叉搜索树转换为累加树
        给出二叉 搜索 树的根节点，该树的节点值各不相同，请你将其转换为累加树（Greater Sum Tree），
        使每个节点 node 的新值等于原树中大于或等于 node.val的值之和。

        二叉搜索树满足下列约束条件
        节点的左子树仅包含键 小于 节点键的节点。
        节点的右子树仅包含键 大于 节点键的节点。
        左右子树也必须是二叉搜索树。

               4
             /   \
            1      6
           / \    / \
          0   2  5   7
               \      \
                3      8

        输入：[4,1,6,0,2,5,7,null,null,null,3,null,null,null,8]
        输出：[30,36,21,36,35,26,15,null,null,null,33,null,null,null,8]

                  4[30]
                 /     \
              1[36]    6[21]
               / \        /  \
          0[36] 2[35]  5[26] 7[15]
                   \           \
                  3[33]        8[8]

        提示：
        树中的节点数介于 0 和 10^4 之间。
        每个节点的值介于 -10^4 和 10^4 之间。
        树中的所有值 互不相同 。
        给定的树为二叉搜索树。
     */

    private int curSum = 0;

    // 归结二叉树遍历问题
    public TreeNode convertBST(TreeNode root) {
        dfs(root);
        // 返回修改新值的二叉树根节点
        return root;
    }

    // 暴力求解 => 一次中序遍历 + 多次树遍历
    // 对二叉树直接使用中序遍历，在计算每个节点新值时，再去遍历除该节点以外所有节点
    // => 时间复杂度非常高

    // 从二叉树最右边位置，依次递减向左遍历
    // => 反着的中序遍历：右根左
    private void dfs(TreeNode node) {
        if (node == null) return;

        dfs(node.right);
        // 处理当前的根节点
        curSum += node.val;
        // 更新节点新值
        node.val = curSum;
        dfs(node.left);
    }
}
