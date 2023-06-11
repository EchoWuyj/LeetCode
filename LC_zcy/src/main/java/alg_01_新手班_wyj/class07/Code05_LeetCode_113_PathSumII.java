package alg_01_新手班_wyj.class07;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2022-09-12 0:28
 * @Version 1.0
 */
public class Code05_LeetCode_113_PathSumII {
    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public static List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }

        ArrayList<Integer> path = new ArrayList<>();
        process(root, 0, sum, path, ans);
        return ans;
    }

    public static void process(TreeNode root, int preSum, int sum, ArrayList<Integer> path, List<List<Integer>> ans) {
        if (root.left == null && root.right == null) {
            if (root.val + preSum == sum) {
                path.add(root.val);
                ans.add(copy(path));
                path.remove(path.size() - 1);
            }
            return;
        }
        path.add(root.val);
        preSum += root.val;

        if (root.left != null) {
            process(root.left, preSum, sum, path, ans);
        }

        if (root.right != null) {
            process(root.right, preSum, sum, path, ans);
        }
        path.remove(path.size() - 1);
    }

    public static ArrayList<Integer> copy(ArrayList<Integer> path) {
        ArrayList<Integer> tempPath = new ArrayList<>();
        for (Integer element : path) {
            tempPath.add(element);
        }
        return tempPath;
    }
}
