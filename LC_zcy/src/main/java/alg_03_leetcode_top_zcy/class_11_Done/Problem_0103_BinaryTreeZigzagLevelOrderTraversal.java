package alg_03_leetcode_top_zcy.class_11_Done;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-03-01 13:36
 * @Version 1.0
 */

// 二叉树的锯齿形层序遍历
public class Problem_0103_BinaryTreeZigzagLevelOrderTraversal {

    /*
         给你二叉树的根节点root,返回其节点值的 锯齿形层序遍历
         即先从左往右,再从右往左进行下一层遍历,以此类推,层与层之间交替进行

         输入:root = [3,9,20,null,null,15,7] => 对应就是层次遍历顺序
         输出:[[3],[20,9],[15,7]]

                3         从左往右
             9   20       从右往左
           N  N 15 7

         头过程:双端队列中从head出元素,从tail进元素,先加左孩子,再加右孩子
         尾过程:双端队列中从tail出元素,从head进元素,先加右孩子,再加左孩子
         先头过程,再尾过程,依次交替

              a
           b    c
         d  e  f  g

        第1层
        head(tail)
               a  -> 头过程

        第2层
        head   tail
              b c -> 尾过程

        第3层
        head   tail
           d e f g  -> 头过程

     */
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
    }

    public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        LinkedList<TreeNode> deque = new LinkedList<>();
        deque.add(root);
        int size = 0;
        // 标记该层是头过程 => 双端队列中从head出元素,从tail进元素,先加左孩子,再加右孩子
        boolean isHead = true;
        while (!deque.isEmpty()) {
            size = deque.size();
            List<Integer> curLevel = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                // 头过程从first位置移除元素,否则从last位置移除元素
                TreeNode cur = isHead ? deque.pollFirst() : deque.pollLast();
                curLevel.add(cur.val);
                if (isHead) {
                    // 从last加入,先加左孩子,再加右孩子
                    if (cur.left != null) {
                        deque.addLast(cur.left);
                    }
                    if (cur.right != null) {
                        deque.addLast(cur.right);
                    }
                } else {
                    // 从first加入,先加右孩子,再加左孩子
                    if (cur.right != null) {
                        deque.addFirst(cur.right);
                    }
                    if (cur.left != null) {
                        deque.addFirst(cur.left);
                    }
                }
            }
            ans.add(curLevel);
            // 将头过程设置为尾过程
            isHead = !isHead;
        }
        return ans;
    }
}
