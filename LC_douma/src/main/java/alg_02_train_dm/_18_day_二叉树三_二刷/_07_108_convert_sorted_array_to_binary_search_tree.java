package alg_02_train_dm._18_day_二叉树三_二刷;

/**
 * @Author Wuyj
 * @DateTime 2023-08-11 17:08
 * @Version 1.0
 */
public class _07_108_convert_sorted_array_to_binary_search_tree {
       /*
            108. 将有序数组转换为二叉搜索树
            给你一个整数数组 nums ，其中元素已经按 升序 排列，
            请你将其转换为一棵 高度平衡 二叉搜索树。

            高度平衡：二叉树是一棵满足「每个节点的左右两个子树的高度差的绝对值不超过 1」的二叉树。

            输入：nums = [-10,-3,0,5,9]
            输出：
                     0
                   /   \
                  -3    9
                 /     /
               -10    5

             或者：
                     0
                   /   \
                 -10    5
                   \     \
                  -3      9

            提示：
            1 <= nums.length <= 10^4
            -10^4 <= nums[i] <= 10^4
            nums 按 严格递增 顺序排列
     */

    // 分治 + 中序遍历
    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null) return null;
        return buildBST(nums, 0, nums.length - 1);
    }

    private TreeNode buildBST(int[] nums, int left, int right) {

        // nums： -4 -2  0  3  5  9 10
        //                  ↑
        //                 mid

        // mid 取在中点
        // 1.左子树 < mid < 右子树 => 数值从小到大关系满足
        // 2.只有 mid 取在中间 => |左子树个数 - 右子树个数| <= 1 => 高度平衡

        // 分治处理(递归实现)： -4 -2  0 和 5  9 10

        // 若只有一个元素，此时 left = right，则 mid = left = right
        // 则创建一个 root 节点，且 leftNode 和 rightNode 都为 null
        // 在连接 leftNode，rightNode 之后，返回 root

        if (left > right) return null;

        int mid = left + (right - left) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        TreeNode leftNode = buildBST(nums, left, mid - 1);
        TreeNode rightNode = buildBST(nums, mid + 1, right);

        root.left = leftNode;
        root.right = rightNode;
        return root;
    }

    // 思考：如果把数组变成链表的话，你会怎么实现呢？ 见 leetcode
    // https://leetcode-cn.com/problems/convert-sorted-list-to-binary-search-tree/
}
