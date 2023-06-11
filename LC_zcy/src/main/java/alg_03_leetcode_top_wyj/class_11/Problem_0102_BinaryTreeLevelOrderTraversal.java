package alg_03_leetcode_top_wyj.class_11;

import java.util.*;

/**
 * @Author Wuyj
 * @DateTime 2023-03-01 13:24
 * @Version 1.0
 */
public class Problem_0102_BinaryTreeLevelOrderTraversal {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
    }

    public static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        LinkedList<TreeNode> deque = new LinkedList<>();
        deque.add(root);
        int size = 0;
        while (!deque.isEmpty()) {
            size = deque.size();
            ArrayList<Integer> curLevel = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode cur = deque.pollLast();
                curLevel.add(cur.val);
                if (cur.left != null) {
                    deque.addFirst(cur.left);
                }
                if (cur.right != null) {
                    deque.addFirst(cur.right);
                }
            }
            ans.add(curLevel);
        }
        return ans;
    }
}
