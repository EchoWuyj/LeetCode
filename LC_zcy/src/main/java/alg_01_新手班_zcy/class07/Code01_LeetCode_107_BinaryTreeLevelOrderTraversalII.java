package alg_01_新手班_zcy.class07;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @Author Wuyj
 * @DateTime 2022-09-04 23:48
 * @Version 1.0
 */

// 测试链接：https://leetcode.cn/problems/binary-tree-level-order-traversal-ii

public class Code01_LeetCode_107_BinaryTreeLevelOrderTraversalII {
    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    // 二叉树的层序遍历 II
    // 给你二叉树的根节点root,返回其节点值自底向上的层序遍历
    // （即按从叶子节点所在层到根节点所在的层,逐层从左向右遍历）
    public List<List<Integer>> levelOrderBottom(TreeNode root) {

        // 父类引用指向子类对象
        // 不能LinkedList<LinkedList<Integer>> = new LinkedList<>()
        List<List<Integer>> ans = new LinkedList<>();
        if (root == null) {
            // root为null时,不能返回null
            // 而是根据题目要求返回相应的数据结构List<List<Integer>>
            return ans;
        }

        // 队列可以使用LinkedList作为实现
        // 泛型是Node,不是Integer
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            // 记录一层元素的个数,给后面for循环使用
            int size = queue.size();
            LinkedList<Integer> curAns = new LinkedList<>();
            for (int i = 0; i < size; i++) {
                TreeNode curNode = queue.poll();
                curAns.add(curNode.val);
                if (curNode.left != null) {
                    queue.add(curNode.left);
                }
                if (curNode.right != null) {
                    queue.add(curNode.right);
                }
            }
            // 保证是逆序,每次都是在0索引位置加入,后遍历到的层次在前面
            ans.add(0, curAns);
        }
        return ans;
    }

    public static void main(String[] args) {

        // 使用Java自带的Stack效率不高
        // 一般推荐使用LinkedList实现,或者通过数组实现
        // 一般笔试中,跑分需要对代码进行优化

        // 使用LinkedList实现
        LinkedList<Integer> stack = new LinkedList<>();
        // 从尾巴加入元素
        stack.addLast(1);
        stack.addLast(2);
        stack.addLast(3);

        while (!stack.isEmpty()) {
            // 从尾巴弹出元素
            System.out.println(stack.pollLast());
        }

        System.out.println("=========================");

        // 使用数组实现(实现方式一)
        int[] stack1 = new int[100];

        int index = 0;
        // 先赋值为1,index再去自增(指针后移)
        //  0  1  2  3
        //  1  2  3
        //           ↑
        //         index
        stack1[index++] = 1;
        stack1[index++] = 2;
        stack1[index++] = 3;

        // 弹栈
        System.out.println(stack1[--index]);
        System.out.println(stack1[--index]);
        System.out.println(stack1[--index]);

        System.out.println("=========================");

        // 使用数组实现(实现方式二)
        int[] stack2 = new int[100];

        int top = -1;

        // 元素入栈
        // 先让top自增1,再去对自增后的top赋值
        //  0  1  2  3
        //  1  2  3
        //        ↑
        //      index
        stack2[++top] = 1;
        stack2[++top] = 2;
        stack2[++top] = 3;
        System.out.println(top);

        // 先输入值,再去自减
        System.out.println(stack2[top--]);
        System.out.println(stack2[top--]);
        // 最后一个top可以不使用top--,而是直接使用top
        // 因为top自减之后,该变量没有再被使用了,所以可以不同top--
        System.out.println(stack2[top]);
    }
}
