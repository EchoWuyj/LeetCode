package alg_03_leetcode_top_zcy.class_11_Done;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-03-01 11:55
 * @Version 1.0
 */

// 二叉树的层序遍历
public class Problem_0102_BinaryTreeLevelOrderTraversal {

        /*
            给你二叉树的根节点root,返回其节点值的层序遍历(即逐层地从左到右访问所有节点)
         */

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
        // 定义双端队列(数据类型TreeNode)
        LinkedList<TreeNode> deque = new LinkedList<>();
        // 从队尾加入
        deque.add(root);
        int size = 0;
        while (!deque.isEmpty()) {
            // KeyPoint 技巧:宽度优先遍历一次一批,不一定得一个一个来
            // 记录树该层需要弹出几个元素
            size = deque.size();
            // 收集当前层元素
            List<Integer> curLevel = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                // 从尾弹出
                TreeNode cur = deque.pollLast();
                curLevel.add(cur.val);
                if (cur.left != null) {
                    // 定义双端队列结构:first始终在左侧,last始终在右组
                    deque.addFirst(cur.left);
                }
                if (cur.right != null) {
                    deque.addFirst(cur.right);
                }
            }
            // 在树一层遍历结束之后,将curLevel加入到ans,此时到代码结尾,会到重新到while循环条件判断
            // 再次进入while循环new一个新的ArrayList,不再是原来的ArrayList
            ans.add(curLevel);
        }
        return ans;
    }
}
