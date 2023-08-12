package alg_02_train_dm._18_day_二叉树三;

/**
 * @Author Wuyj
 * @DateTime 2023-08-11 17:04
 * @Version 1.0
 */
public class _06_701_insert_into_a_binary_search_tree {
    /*
        701. 二叉搜索树中的插入操作
        给定二叉搜索树(BST)的根节点和要插入树中的值，将值插入二叉搜索树。
        返回插入后二叉搜索树的根节点。
        输入数据 保证 ，新值和原始二叉搜索树中的任意节点值都不同。

        注意，可能存在多种有效的插入方式，只要树在插入后仍保持为二叉搜索树即可。
        你可以返回 任意有效的结果

        输入：root = [4,2,7,1,3], val = 5
        输出：[4,2,7,1,3,5]

              4
            /   \
           2     7
          / \
         1   3

         插入后元素 5

              4
            /   \
           2     7
          / \    /
         1   3  5

        提示：
        给定的树上的节点数介于 0 和 10^4 之间
        每个节点都有一个唯一整数值，取值范围从 0 到 10^8
        -10^8 <= val <= 10^8
        新值和原始二叉搜索树中的任意节点值都不同
     */

    // KeyPoint 方法一 迭代
    // O(logn)
    public TreeNode insertIntoBST1(TreeNode root, int val) {
        if (root == null) return new TreeNode(val);
        TreeNode cur = root;
        while (cur != null) {
            // val 比当前根节点 cur 要小，且 cur 左子树为 null
            if (val < cur.val && cur.left == null) {
                // val 放到 cur 左子树
                cur.left = new TreeNode(val);
                // 插入之后，结束 while 循环
                return root;
                // 比当前根节点 cur 要大，且 cur 右子树为 null
            } else if (val > cur.val && cur.right == null) {
                // val 放到 cur 右子树
                cur.right = new TreeNode(val);
                // 插入之后，结束 while 循环
                return root;
            }

            // cur 左右子树为 null，val 没能插入，则 val 不断往 BST 下面找合适的位置
            if (val < cur.val) {
                cur = cur.left;
            } else { // 没有相同值，else 即表示 val > cur.val
                cur = cur.right;
            }
        }
        return root;
    }

    // KeyPoint 方法二 递归
    // 递归含义：将 val 插入到以 root 为根节点的二叉搜索树中，并返回插入节点后的二叉搜索树的根节点
    // => 使用递归之前，必须明确递归含义，基于递归含义从而实现整体代码
    public TreeNode insertIntoBST(TreeNode root, int val) {
        // 直达 BST 叶子节点的最下层，即递归边界，创建一个新节点返回
        if (root == null) return new TreeNode(val);
        if (val < root.val) {
            // 将 val 插入到 root 左子树 (当成黑盒，默认是可以实现)，并将左子树根节点返回
            // 接在在 root left 指针上
            root.left = insertIntoBST(root.left, val);
        } else {
            root.right = insertIntoBST(root.right, val);
        }
        return root;
    }
}
