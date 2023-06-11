package alg_03_leetcode_top_zcy.class_11_Done;

/**
 * @Author Wuyj
 * @DateTime 2023-03-02 12:31
 * @Version 1.0
 */

// 将有序数组转换为二叉搜索树
public class Problem_0108_ConvertSortedArrayToBinarySearchTree {

    /*
        给你一个整数数组nums,其中元素已经按升序排列,请你将其转换为一棵高度平衡二叉搜索树。
        高度平衡二叉树是一棵满足「每个节点的左右两个子树的高度差的绝对值不超过1」的二叉树

        为什么要强调平衡,任何一个有序数组都可以构造链式的二叉树
        [-10,-3,0,5,9]

                    -10
                       -3
              =>          0     => 符合二叉搜索树的定义
                            5
                              9

                         0
              =>     -3    9
                   -10    5

     */
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    public TreeNode sortedArrayToBST(int[] nums) {
        return process(nums, 0, nums.length - 1);
    }

    public static TreeNode process(int[] nums, int L, int R) {
        if (L > R) {
            return null;
        }

        // 只有一个节点,使用L或者R都是可以
        if (L == R) {
            return new TreeNode(nums[L]);
        }
        // 使用二分后的点作为头节点,保证左右两边的序列个数一样多,从而保证平衡
        int M = (L + R) / 2;
        TreeNode head = new TreeNode(nums[M]);

        // 递归构造左树和右树
        head.left = process(nums, L, M - 1);
        head.right = process(nums, M + 1, R);

        // 最终返回根节点
        return head;
    }
}
