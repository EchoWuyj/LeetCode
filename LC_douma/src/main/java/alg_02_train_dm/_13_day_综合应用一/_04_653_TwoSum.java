package alg_02_train_dm._13_day_综合应用一;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Author Wuyj
 * @DateTime 2023-03-17 20:05
 * @Version 1.0
 */
public class _04_653_TwoSum {

    /*
        653. 两数之和 IV - 输入二叉搜索树
        给定一个二叉搜索树 root 和一个目标结果 k，
        如果二叉搜索树中存在两个元素且它们的和等于给定的目标结果，则返回 true。

        示例 1：
        输入: root = [5,3,6,2,4,null,7], k = 9
        输出: true

        示例 2：
        输入: root = [5,3,6,2,4,null,7], k = 28
        输出: false

        提示:
        二叉树的节点个数的范围是  [1, 104].
        -10^4 <= Node.val <= 10^4
        题目数据保证，输入的 root 是一棵 有效 的二叉搜索树
        -10^5 <= k <= 10^5

     */

    // KeyPoint 方法一 BST 中序遍历 + 对撞指针
    public boolean findTarget1(TreeNode root, int target) {
        if (root == null) return false;

        List<Integer> nums = new ArrayList<>();
        // BST 中序遍历 => 有序序列
        inOrder(root, nums);

        // 对撞指针
        int len = nums.size();
        int left = 0, right = len - 1;
        while (left < right) {
            int sum = nums.get(left) + nums.get(right);
            if (sum == target)
                return true;
            else if (sum < target)
                left++;
            else
                right--;
        }

        return false;
    }

    // BST 中序遍历 => O(n)
    private void inOrder(TreeNode node, List<Integer> nums) {
        if (node == null) return;
        inOrder(node.left, nums);
        nums.add(node.val);
        inOrder(node.right, nums);
    }

    // KeyPoint 方法二 哈希查找
    public boolean findTarget2(TreeNode root, int target) {
        if (root == null) return false;
        return find(root, target, new HashSet<>());
    }

    // 递归 => DFS => 将树所有节点都遍历一遍，找到返回 true，否则返回 false
    private boolean find(TreeNode node, int target, Set<Integer> set) {
        if (node == null) return false;
        // 经过 if 判断之后，node 不为 null
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



