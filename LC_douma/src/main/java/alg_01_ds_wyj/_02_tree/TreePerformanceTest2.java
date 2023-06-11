package alg_01_ds_wyj._02_tree;

import alg_01_ds_wyj._02_tree._03_avl.AVLTree;
import alg_01_ds_wyj._02_tree._04_rb.RBTree;

import java.util.ArrayList;

/**
 * @Author Wuyj
 * @DateTime 2023-03-18 17:22
 * @Version 1.0
 */

// KeyPoint 有序数据 add 和 contains 性能测试
public class TreePerformanceTest2 {
    public static void main(String[] args) {
        int num = 20000000;
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            list.add(i);
        }

        long startTime = System.nanoTime();
        AVLTree<Integer> avl = new AVLTree<>();
        for (Integer i : list) avl.add(i);
        for (Integer i : list) avl.contains(i);
        long endTime = System.nanoTime();
        double time = (endTime - startTime) / 1000000000.0;
        System.out.println("AVL：" + time + " s");

        startTime = System.nanoTime();
        RBTree<Integer> rbTree = new RBTree<>();
        for (Integer i : list) rbTree.add(i);
        for (Integer i : list) avl.contains(i);
        endTime = System.nanoTime();
        time = (endTime - startTime) / 1000000000.0;
        System.out.println("RB：" + time + " s");
    }


    /*
    AVL：10.5937279 s
    RB：9.960108 s
     */
}
