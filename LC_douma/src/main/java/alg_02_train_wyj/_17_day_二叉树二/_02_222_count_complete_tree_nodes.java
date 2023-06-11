package alg_02_train_wyj._17_day_二叉树二;

/**
 * @Author Wuyj
 * @DateTime 2023-04-03 10:14
 * @Version 1.0
 */
public class _02_222_count_complete_tree_nodes {
    public int countNodes1(TreeNode root) {
        if (root == null) return 0;
        int left = countNodes1(root.left);
        int right = countNodes1(root.right);
        return left + right + 1;
    }

    public int countNodes(TreeNode root) {
        if (root == null) return 0;
        int level = 0;
        TreeNode cur = root;
        while (cur.left != null) {
            level++;
            cur = cur.left;
        }

        int low = 1 << level, high = (1 << (level + 1)) - 1;
        while (low < high) {
            int mid = low + (high - low + 1) / 2;
            if (exists(root, level, mid)) {
                low = mid;
            } else {
                high = mid - 1;
            }

//            if (!exists(root, level, mid)) {
//                high = mid - 1;
//            } else {
//                low = mid;
//            }
        }
        return low;
    }

    public boolean exists(TreeNode root, int level, int mid) {
        int mask = 1 << (level - 1);
        TreeNode node = root;
        while (node != null && mask > 0) {
            if ((mask & mid) == 0) {
                node = node.left;
            } else {
                node = node.right;
            }
            mask >>= 1;
        }
        return node != null;
    }
}
