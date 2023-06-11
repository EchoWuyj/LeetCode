package alg_01_ds_dm._02_tree._03_avl;

/**
 * @Author Wuyj
 * @DateTime 2023-03-15 12:29
 * @Version 1.0
 */
public class AVLTreeTest {
    public static void main(String[] args) {
        AVLTree<Integer> avl = new AVLTree<>();

        avl.add(9);
        avl.add(10);
        avl.add(11);
        avl.add(54);
        avl.add(25);
        avl.add(90);
        avl.add(13);
        avl.add(15);
        avl.remove(15);
        avl.add(16);
        System.out.println(avl.removeMax()); // 90
        System.out.println(avl.removeMin()); // 9
        avl.add(22);
        avl.add(39);
        System.out.println(avl.maxValue()); // 54
        System.out.println(avl.minValue()); // 10

        System.out.println("是否是二叉查找树：" + avl.isBST()); // 是否是二叉查找树：true
        System.out.println("是否是平衡二叉树：" + avl.isBalanced()); // 是否是平衡二叉树：true
    }
}
