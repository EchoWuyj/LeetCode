package alg_01_ds_wyj._02_tree._03_avl;

/**
 * @Author Wuyj
 * @DateTime 2023-03-16 16:54
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
        System.out.println(avl.removeMax());
        System.out.println(avl.removeMin());
        avl.add(22);
        avl.add(39);
        System.out.println(avl.maxValue());
        System.out.println(avl.minValue());

        System.out.println("是否是二叉查找树：" + avl.isBST());
        System.out.println("是否是平衡二叉树：" + avl.isBalanced());
    }
}
