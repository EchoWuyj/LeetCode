package alg_02_train_dm._13_day_综合应用一_二刷;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author Wuyj
 * @DateTime 2023-07-31 15:25
 * @Version 1.0
 */
public class _04_653_TwoSum2 {

    // KeyPoint 方法二 哈希查找
    public boolean findTarget1(TreeNode root, int target) {
        if (root == null) return false;
        return find(root, target, new HashSet<>());
    }

    // 递归 => DFS => 将树所有节点都遍历一遍，找到返回 true，否则返回 false
    private boolean find(TreeNode node, int target, Set<Integer> set) {
        // 叶子节点为 null，返回 false
        if (node == null) return false;
        // 经过 if 判断之后，node 不为 null
        if (set.contains(target - node.val)) return true;
        set.add(node.val);
        // KeyPoint 递归从左右子树去找，只要有一个有，返回 true，否则返回 false
        //          => 递归遍历，判断了所有节点左右子树，不存在遗漏的情况
        return find(node.left, target, set) || find(node.right, target, set);
    }

    // KeyPoint 方法二 哈希查找 另外一种实现 => 比较好理解
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
}
