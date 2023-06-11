package algorithm._09_binary_tree;

import java.util.LinkedList;

/**
 * @Author Wuyj
 * @DateTime 2022-03-24 22:46
 * @Version 1.0
 */
public class TreePrinter {
    //1.先序遍历
    public static void printTreePreOrder(TreeNode root) {
        //处理基准情况:处理叶子节点
        if (root == null) return;

        //先打印根
        System.out.print(root.val + " ");
        //打印左子树
        printTreePreOrder(root.left);
        //打印右子树
        printTreePreOrder(root.right);
    }

    //2.中序遍历
    public static void printTreeInOrder(TreeNode root) {
        //处理基准情况:处理叶子节点
        if (root == null) return;

        //先打印左子树
        printTreeInOrder(root.left);
        //打印根
        System.out.print(root.val + " ");
        //打印右子树
        printTreeInOrder(root.right);
    }

    //3.后序遍历
    public static void printTreePostOrder(TreeNode root) {
        //处理基准情况:处理叶子节点
        if (root == null) return;

        //先打印左子树
        printTreePostOrder(root.left);
        //打印右子树
        printTreePostOrder(root.right);
        //打印根
        System.out.print(root.val + " ");
    }

    //4.分层遍历
    public static void printTreeLevelOrder(TreeNode root) {
        //定义一个队列
        LinkedList<TreeNode> queue = new LinkedList<>();

        //先把根节点放入队列
        queue.offer(root);

        //只要队列不为空,就一直出队
        while (!queue.isEmpty()) {
            TreeNode curNode = queue.poll();
            System.out.print(curNode.val + " ");

            //将子节点加入队列
            if (curNode.left != null) {
                queue.offer(curNode.left);
            }
            if (curNode.right != null) {
                queue.offer(curNode.right);
            }
        }
    }

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);

        node1.left = node2;
        node1.right = node3;

        node3.left = node4;
        node3.right = node5;

        node4.right = node6;

        /*
        树的形状
           1
         2  3
           4  5
            6
        */
        printTreePreOrder(node1); //1 2 3 4 6 5
        System.out.println();
        printTreeInOrder(node1);//2 1 4 6 3 5
        System.out.println();
        printTreePostOrder(node1);//2 6 4 5 3 1
        System.out.println();
        printTreeLevelOrder(node1);//1 2 3 4 5 6
    }
}
