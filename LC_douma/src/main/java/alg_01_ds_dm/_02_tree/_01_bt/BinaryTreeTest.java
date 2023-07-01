package alg_01_ds_dm._02_tree._01_bt;

/**
 * @Author Wuyj
 * @DateTime 2023-03-13 15:04
 * @Version 1.0
 */
public class BinaryTreeTest {
    public static void main(String[] args) {

        TreeNode<Integer> root_23 = new TreeNode<>(23);
        TreeNode<Integer> node_34 = new TreeNode<>(34);
        TreeNode<Integer> node_21 = new TreeNode<>(21);
        TreeNode<Integer> node_99 = new TreeNode<>(99);
        TreeNode<Integer> node_77 = new TreeNode<>(77);
        TreeNode<Integer> node_90 = new TreeNode<>(90);
        TreeNode<Integer> node_45 = new TreeNode<>(45);
        TreeNode<Integer> node_60 = new TreeNode<>(60);

//        root.left = node1;
//        root.right = node2;
//        node1.left = node3;
//        node3.left = node4;
//        node3.right = node5;
//        node2.left = node6;
//        node2.right = node7;

        // 推荐直观写法
        root_23.left = node_34;
        root_23.right = node_21;
        node_34.left = node_99;
        node_21.left = node_45;
        node_21.right = node_60;
        node_99.left = node_77;
        node_99.right = node_90;

        /*
                 23
               34   21
             99   45  60
          77   90

         */

        System.out.println(BinaryTree_01_PreOrder.preOrder(root_23));
        // [23, 34, 99, 77, 90, 21, 45, 60]

        System.out.println("===============");

        System.out.println(BinaryTree_02_InOrder.inOrder(root_23));
        // [77, 99, 90, 34, 23, 45, 21, 60]

        System.out.println("===============");

        System.out.println(BinaryTree_03_PostOrder.postOrder(root_23));
        // [77, 90, 99, 34, 45, 60, 21, 23]

        System.out.println("===============");

        System.out.println(BinaryTree_04_LevelOrder.levelOrder1(root_23));
        // [23, 34, 21, 99, 45, 60, 77, 90]

        System.out.println("===============");

        System.out.println(BinaryTree_04_LevelOrder.levelOrder2(root_23));
        // [[23], [34, 21], [99, 45, 60], [77, 90]]
    }
}
