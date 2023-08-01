package alg_02_train_wyj._13_day_综合应用一;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * @Author Wuyj
 * @DateTime 2023-05-24 21:25
 * @Version 1.0
 */
public class _04_653_TwoSum {

    public boolean findTarget1(TreeNode root, int target) {
        if (root == null) return false;
        ArrayList<Integer> list = new ArrayList<>();
        process(root, list);
        int size = list.size();
        int left = 0, right = size - 1;
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

    public void process(TreeNode root, ArrayList<Integer> list) {
        if (root == null) return;
        process(root.left, list);
        list.add(root.val);
        process(root.right, list);
    }

    public boolean hasTarget = false;

    public boolean findTarget2(TreeNode root, int target) {
        if (root == null) return false;
        HashSet<Integer> set = new HashSet<>();
        process(root, target, set);
        return hasTarget;
    }

    public void process(TreeNode root, int target, HashSet<Integer> set) {
        if (root == null) return;
        int num1 = root.val;
        int num2 = target - num1;
        if (set.contains(num2)) {
            hasTarget = true;
        } else {
            set.add(num1);
        }
        process(root.left, target, set);
        process(root.right, target, set);
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
