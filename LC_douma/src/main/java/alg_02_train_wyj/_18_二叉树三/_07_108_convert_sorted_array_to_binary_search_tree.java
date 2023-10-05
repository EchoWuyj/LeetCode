package alg_02_train_wyj._18_二叉树三;

/**
 * @Author Wuyj
 * @DateTime 2023-08-12 11:26
 * @Version 1.0
 */
public class _07_108_convert_sorted_array_to_binary_search_tree {
    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null || nums.length == 0) return null;
        return build(nums, 0, nums.length - 1);
    }

    public TreeNode build(int[] nums, int left, int right) {
        if (left > right) return null;
        int mid = left + (right - left) / 2;

        TreeNode node = new TreeNode(nums[mid]);

        TreeNode leftNode = build(nums, left, mid - 1);
        TreeNode rightNode = build(nums, mid + 1, right);

        node.left = leftNode;
        node.right = rightNode;
        return node;
    }
}
