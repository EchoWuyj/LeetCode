package alg_01_新手班_zcy.class07;

/**
 * @Author Wuyj
 * @DateTime 2022-09-04 23:48
 * @Version 1.0
 */

// 测试链接：https://leetcode.cn/problems/path-sum

public class Code04_LeetCode_112_PathSum {
    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    // 定义全局公共变量
    public static boolean isSum = false;

    // 路径总和
    // 给你二叉树的根节点root和一个表示目标和的整数targetSum
    // 判断该树中是否存在根节点到叶子节点的路径(根节点到叶子节点,中途不能停止)
    // 这条路径上所有节点值相加等于目标和targetSum
    // 如果存在返回true,否则返回false
    // 叶子节点 是指没有子节点的节点

    // 实现方式一
    public static boolean hasPathSum1(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }

        // 全局变量,多用户可以共享使用,所以跑一颗新树需要手动修改成false;
        // 避免之前已经被修改成true了,再次调用则一开始则为true,则出现错误
        isSum = false;

        // 最开始的根节点的累加和为0
        // 同时经过process1函数之后,已经将isSum的值进行了修改,
        process1(root, 0, sum);

        return isSum;
    }

    // 思路:
    // 1)process调用所有的节点,一旦有能凑出x.val+preSum==sum
    //   全局变量由false设置为true,只要发生一次,hasPathSum1直接返回true
    // 2)void不在于返回什么,关键在于是否有x.val+preSum=sum相等的时刻
    public static void process1(TreeNode x, int preSum, int sum) {

        // 注意这里必须限定在叶子节点,中间的节点即使满足也不行
        // 同时,x==null,不能代表其上游是叶子节点
        // 故将边界情况定义成叶节点(左右都为null),而不是自己为null,找上游节点
        if (x.left == null && x.right == null) {
            if (x.val + preSum == sum) {
                // 修改全局变量isSum,返回
                isSum = true;
                return;
            }
        }

        // x是非叶节点
        preSum += x.val;

        // 左树为null不要递归调用,避免递归边界的空指针异常
        // 同时,由于左树为null,则左子树也没有叶节点,所以直接跳过,判断右子树
        // (右子树同理)
        //  3
        //   ↘
        // 	   4

        // KeyPoint
        //  有点像树的深度优先遍历
        //  从root一直往左下方递归,直到最左,递归边界不满足,返回上一层,再去递归右子树
        //  依次反复,直到遍历完整颗树
        if (x.left != null) {
            process1(x.left, preSum, sum);
        }

        if (x.right != null) {
            process1(x.right, preSum, sum);
        }
    }

    //--------------------------------------------------------------------

    // 实现方式二 递归实现(常规递归)
    public static boolean hasPathSum2(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        return process2(root, sum);
    }

    public static boolean process2(TreeNode root, int rest) {
        if (root.left == null && root.right == null) {
            // 表达式本身就是boolean值,直接返回即可
            return root.val == rest;
        }

        boolean ans = (root.left != null) ? process2(root.left, rest - root.val) : false;
        ans |= (root.right != null) ? process2(root.right, rest - root.val) : false;

        // 返回最终结果
        return ans;
    }
}
