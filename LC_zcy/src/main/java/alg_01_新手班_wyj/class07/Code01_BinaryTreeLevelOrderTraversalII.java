package alg_01_新手班_wyj.class07;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @Author Wuyj
 * @DateTime 2022-09-11 16:42
 * @Version 1.0
 */
public class Code01_BinaryTreeLevelOrderTraversalII {

    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    public List<List<Integer>> levelOrderBottom(TreeNode root) {

        List<List<Integer>> ans = new LinkedList<>();
        if (root == null) {
            return ans;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            LinkedList<Integer> curLevelList = new LinkedList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                curLevelList.add(node.val);
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            ans.add(0, curLevelList);
        }
        return ans;
    }

    public static void main(String[] args) {
        // 通过LinkedList实现Stack
        LinkedList<Integer> stack = new LinkedList<>();
        stack.addLast(3);
        stack.addLast(6);
        stack.addLast(8);

        while (!stack.isEmpty()) {
            System.out.println(stack.pollLast());
        }

        System.out.println("=====================");

        // 使用数组实现
        int[] arrayStack = new int[100];
        int top = -1;

        arrayStack[++top] = 4;
        arrayStack[++top] = 5;
        arrayStack[++top] = 8;
        System.out.println(arrayStack[top]);

        System.out.println(arrayStack[top--]);
        System.out.println(arrayStack[top--]);

        System.out.println(arrayStack[top]);
    }
}
