package alg_02_train_wyj._13_day_综合应用一;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Author Wuyj
 * @DateTime 2023-05-24 21:25
 * @Version 1.0
 */
public class _04_653_TwoSum {

    public boolean findTarget1(TreeNode root, int target) {
        if (root == null) return false;
        List<Integer> list = new ArrayList<>();
        inOrder(root, list);

        int left = 0;
        int right = list.size() - 1;
        while (left < right) {
            int sum = list.get(left) + list.get(right);
            if (sum == target) {
                return true;
            } else if (sum < target) {
                left++;
            } else {
                right--;
            }
        }
        return false;
    }

    public void inOrder(TreeNode root, List<Integer> list) {
        if (root == null) return;
        inOrder(root.left, list);
        list.add(root.val);
        inOrder(root.right, list);
    }

    public boolean findTarget2(TreeNode root, int target) {
        if (root == null) return false;
        return find(root, target, new HashSet<>());
    }

    public boolean find(TreeNode node, int target, Set<Integer> set) {
        if (node == null) return false;
        if (set.contains(target - node.val)) return true;
        set.add(node.val);
        return find(node.left, target, set) || find(node.right, target, set);
    }

    // Tree 结构
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
            this.left = this.right = null;
        }
    }
}
