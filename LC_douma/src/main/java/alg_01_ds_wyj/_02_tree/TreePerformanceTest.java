package alg_01_ds_wyj._02_tree;

import alg_01_ds_wyj._02_tree._02_bst.BST;
import alg_01_ds_wyj._02_tree._03_avl.AVLTree;
import alg_01_ds_wyj._02_tree._04_rb.RBTree;

import java.util.ArrayList;
import java.util.Random;

/**
 * @Author Wuyj
 * @DateTime 2023-03-18 17:22
 * @Version 1.0
 */

// KeyPoint  add 和 contains 性能测试
public class TreePerformanceTest {
    private static Random random = new Random();

    public static void main(String[] args) {
        int num = 20000000;
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            list.add(random.nextInt());
        }

        long startTime = System.nanoTime();
        BST<Integer> bst = new BST<>();
        for (Integer i : list) bst.add(i);
        for (Integer i : list) bst.contains(i);
        long endTime = System.nanoTime();
        double time = (endTime - startTime) / 1000000000.0;
        System.out.println("BST：" + time + " s");

        startTime = System.nanoTime();
        AVLTree<Integer> avl = new AVLTree<>();
        for (Integer i : list) avl.add(i);
        for (Integer i : list) avl.contains(i);
        endTime = System.nanoTime();
        time = (endTime - startTime) / 1000000000.0;
        System.out.println("AVL：" + time + " s");

        startTime = System.nanoTime();
        RBTree<Integer> rbTree = new RBTree<>();
        for (Integer i : list) rbTree.add(i);
        for (Integer i : list) rbTree.contains(i);
        endTime = System.nanoTime();
        time = (endTime - startTime) / 1000000000.0;
        System.out.println("RB：" + time + " s");
    }


    /*
    BST：74.8638621 s
       1 对于完全随机的数据来说，普通的二叉查找树的性能很好
         不会退化成链表，能保持一定的平衡，且不需要维护平衡的操作
       2 普通的二又查找树的缺点：在极端的情况下会退化成链表（或者高度不平衡）
    AVL：194.5587693 s
    RB：97.3793216 s
    平衡实现比 AVL 树要简单，操作相对较少，插入时性能比 AVL 树要好
     */
}
