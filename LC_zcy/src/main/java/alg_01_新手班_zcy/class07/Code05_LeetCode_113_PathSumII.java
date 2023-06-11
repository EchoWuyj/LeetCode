package alg_01_新手班_zcy.class07;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2022-09-04 23:48
 * @Version 1.0
 */

// 测试链接：https://leetcode.cn/problems/path-sum-ii

public class Code05_LeetCode_113_PathSumII {

    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    // 路径总和 II
    // 给你二叉树的根节点root和一个整数目标和targetSum
    // 找出所有从根节点到叶子节点路径总和等于给定目标和的路径
    // 叶子节点是指没有子节点的节点
    public static List<List<Integer>> pathSum(TreeNode root, int sum) {

        ArrayList<List<Integer>> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }

        ArrayList<Integer> path = new ArrayList<Integer>();
        process(root, path, 0, sum, ans);
        return ans;
    }

    public static void process(TreeNode x, List<Integer> path, int preSum,
                               int sum, List<List<Integer>> ans) {

        if (x.left == null && x.right == null) {
            if (x.val + preSum == sum) {
                path.add(x.val);
                // 先单独copy一份path,将其加入到ans里
                // 后续的path还需要继续加入元素判断,避免影响原始的path
                ans.add(copy(path));

                // 经典的清理现场的过程,在最后递归返回上层前,
                // 需要将加入的节点再去删除,从而不影响后main路径的选择

                //     3
                //   2    3
                // 1   1
                // 假设sum=6,在递归到最左侧1,{3,2,1},返回上层前,将最左侧1移除,
                // 之后到上层,再去向右递归右边的1
                // path.size()-1 表示集合位置中最后一个元素
                path.remove(path.size() - 1);
            }

            // void型函数中return语句的作用
            // 表示该方法结束,程序不继续往下执行
            // KeyPoint return是在外层if,只要是叶子节点最终都要返回
            return;
        }

        // x非叶节点
        path.add(x.val);
        preSum += x.val;

        // 判断左右子树
        if (x.left != null) {
            process(x.left, path, preSum, sum, ans);
        }
        if (x.right != null) {
            process(x.right, path, preSum, sum, ans);
        }

        // 在判断某节点的左,右子树后,递归返回某节点上层前,需要将该某节点从path中移除
        //     1
        //   2   3
        // 4  5
        // 在2节点判断左,右子树4,5之后,返回到1前,将path中的2移除
        path.remove(path.size() - 1);
    }

    public static List<Integer> copy(List<Integer> path) {
        ArrayList<Integer> ans = new ArrayList<>();
        for (Integer num : path) {
            // 可以使用替换 ans.addAll(path);
            ans.add(num);
        }
        return ans;
    }
}
