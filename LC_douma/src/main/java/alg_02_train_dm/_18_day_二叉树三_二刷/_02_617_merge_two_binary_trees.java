package alg_02_train_dm._18_day_二叉树三_二刷;

/**
 * @Author Wuyj
 * @DateTime 2023-04-05 10:27
 * @Version 1.0
 */
public class _02_617_merge_two_binary_trees {
    /*
       617.合并二叉树
       给定两个二叉树，想象当你将它们中的一个覆盖到另一个上时，两个二叉树的一些节点便会重叠。
       你需要将他们合并为一个新的二叉树。
       合并的规则是如果两个节点重叠，那么将他们的值相加作为节点合并后的新值，
       否则不为 NULL 的节点将直接作为新二叉树的节点。

       示例1:
       输入:
           Tree 1                     Tree 2
                 1                         2
                / \                       / \
               3   2                     1   3
              /                           \   \
             5                             4   7
       输出:
       合并后的树:
                3
               / \
              4   5
             / \   \
            5   4   7
       注意:合并必须从两个树的根节点开始。

        提示：
        两棵树中的节点数目在范围 [0, 2000] 内
        -104 <= Node.val <= 104
    */

    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        if (root1 == null) return root2;
        if (root2 == null) return root1;
        return dfs(root1, root2);
    }

    // 递归含义：合并以 root1、root2 为根节点的两棵树，返回合并之后的新的二叉树的根节点
    private TreeNode dfs(TreeNode root1, TreeNode root2) {

        // KeyPoint 写递归
        // 分别从：递的过程和归的过程，从不同的过程考虑代码怎么写

        // 递的过程 -> 考虑边界
        if (root1 == null && root2 == null) return null;
        if (root1 == null && root2 != null) return root2;
        if (root1 != null && root2 == null) return root1;

        // 递的过程 -> 一路递进过程执行的操作
        TreeNode mergeNode = new TreeNode(root1.val + root2.val);

        // 归的过程 -> 考虑中间情况
        TreeNode left = dfs(root1.left, root2.left);
        TreeNode right = dfs(root1.right, root2.right);

        // 在左右子树都合并好之后，再去维护左右子树关系，后序遍历
        mergeNode.left = left;
        mergeNode.right = right;

        // KeyPoint 简化 => 在回溯时直接维护好树的结构
        // 合并后根节点左子树 = 已经合并好的左子节点
        // mergeNode.left = dfs(root1.left, root2.left);
        // 合并后根节点右子树 = 已经合并好的右子节点
        // mergeNode.right = dfs(root1.right, root2.right);

        return mergeNode;
    }
}
