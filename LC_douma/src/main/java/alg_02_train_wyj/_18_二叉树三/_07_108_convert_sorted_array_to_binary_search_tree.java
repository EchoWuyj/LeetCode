package alg_02_train_wyj._18_二叉树三;

/**
 * @Author Wuyj
 * @DateTime 2023-08-12 11:26
 * @Version 1.0
 */
public class _07_108_convert_sorted_array_to_binary_search_tree {
    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null) return null;
        int n = nums.length - 1;
        return buildBST(nums, 0, n);
    }

    private TreeNode buildBST(int[] nums, int left, int right) {
        if (left > right) return null;
        int mid = left + (right - left) / 2;
        TreeNode root = new TreeNode(nums[mid]);

        TreeNode leftNode = buildBST(nums, left, mid - 1);
        TreeNode rightNode = buildBST(nums, mid + 1, right);

        root.left = leftNode;
        root.right = rightNode;
        return root;
    }
}
