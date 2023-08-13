package alg_02_train_dm._18_day_二叉树三_二刷;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author Wuyj
 * @DateTime 2023-08-11 17:03
 * @Version 1.0
 */
public class _04_106_construct_binary_tree_from_inorder_and_postorder_traversal {
    /*
           106. 从中序与后序遍历序列构造二叉树
           根据一棵树的中序遍历与后序遍历构造二叉树

           注意:
           你可以假设树中没有重复的元素。

           例如
           中序遍历 inorder = [9,3,15,20,7]
           后序遍历 postorder = [9,15,7,20,3]
           返回如下的二叉树：

               3
              / \
             9  20
               /  \
              15   7

            输入：inorder = [9,3,15,20,7], postorder = [9,15,7,20,3]
            输出：[3,9,20,null,null,15,7]


            提示:
            1 <= inorder.length <= 3000
            postorder.length == inorder.length
            -3000 <= inorder[i], postorder[i] <= 3000
            inorder 和 postorder 都由 不同 的值组成
            postorder 中每一个值都在 inorder 中
            inorder 保证是树的中序遍历
            postorder 保证是树的后序遍历
    */

    private int[] postorder;
    private int rootIndex;
    private Map<Integer, Integer> idxMap;

    public TreeNode buildTree(int[] inorder, int[] postorder) {

        // 本质：和上一题一样
        // => 只是整颗树的根节点，从后续遍历的最后一个节点开始

        // 后续遍历： 6 10 9 15 7 20 3
        //           |左子树||右子树| ↑  => 左右根

        // 中序遍历： 6 9 10 3 15 20 7
        //          |左子树| ↑ |右子树|  => 左根右

        // 后续遍历：6 10 9 15 7 20 3 => 从后往前遍历
        //                         ↑

        //            3[1]  → 后续遍历次序，需要和构建二叉树次序保持一致
        //            /   \
        //        9[5]     20[2]
        //        / \      /   \
        //   6 [7] 10[6] 15[4] 7[3]

        this.postorder = postorder;
        idxMap = new HashMap<>();
        int n = postorder.length;
        // root 索引从 n-1 开始
        rootIndex = n - 1;
        for (int i = 0; i < n; i++) {
            idxMap.put(inorder[i], i);
        }
        return dfs(0, n - 1);
    }

    private TreeNode dfs(int left, int right) {
        if (left > right) return null;
        int rootVal = postorder[rootIndex];
        // 索引自减
        rootIndex--;
        TreeNode root = new TreeNode(rootVal);
        int mid = idxMap.get(rootVal);

        // 先构建右子树，再去构建左子树
        root.right = dfs(mid + 1, right);
        root.left = dfs(left, mid - 1);

        return root;
    }
}
