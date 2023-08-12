package alg_02_train_wyj._17_day_二叉树二;

/**
 * @Author Wuyj
 * @DateTime 2023-04-03 10:14
 * @Version 1.0
 */
public class _02_222_count_complete_tree_nodes {

    public int countNodes1(TreeNode root) {
        if (root == null) return 0;
        return dfs(root);
    }

    public int dfs(TreeNode node) {
        if (node == null) return 0;
        int left = dfs(node.left);
        int right = dfs(node.right);
        return left + right + 1;
    }

    public int countNodes(TreeNode root) {
        if (root == null) return 0;
        int level = 0;
        TreeNode node = root;
        while (node.left != null) {
            level++;
            node = node.left;
        }
        int left = 1 << level;
        int right = (1 << (level + 1)) - 1;

        while (left < right) {
            int mid = left + (right - left + 1) / 2;
            if (!exist(root, level, mid)) {
                right = mid - 1;
            } else {
                left = mid;
            }
        }
        return left;
    }


    private boolean exist(TreeNode root, int level, int mid) {
        int mask = 1 << (level - 1);
        TreeNode node = root;
        while (node != null && mask > 0) {
            if ((mid & mask) == 0) {
                node = node.left;
            } else {
                node = node.right;
            }
            mask >>= 1;
        }
        return node != null;
    }
}
