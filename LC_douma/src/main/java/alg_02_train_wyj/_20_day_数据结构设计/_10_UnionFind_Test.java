package alg_02_train_wyj._20_day_数据结构设计;

import java.util.Random;

/**
 * @Author Wuyj
 * @DateTime 2023-05-29 14:37
 * @Version 1.0
 */
public class _10_UnionFind_Test {

    private static double testUF(_09_UnionFind uf, int times) {
        int size = uf.size();
        Random random = new Random();

        long startTime = System.nanoTime();
        for (int i = 0; i < times; i++) {
            int a = random.nextInt(size);
            int b = random.nextInt(size);
            uf.unionElement(a, b);
        }

        for (int i = 0; i < times; i++) {
            int a = random.nextInt(size);
            int b = random.nextInt(size);
            uf.isConnected(a, b);
        }

        long endTime = System.nanoTime();
        return (endTime - startTime) / 1000_000_000.0;
    }

    private static void test1() {
        int size = 10000000;
        int m = 10000000;
        _09_01_UnionFind_Array uf1 = new _09_01_UnionFind_Array(size);
        System.out.println("UnionFind_Array : " + testUF(uf1, m) + " s"); // UnionFind_Array : 7.1779066 s

        _09_02_UnionFind_Tree uf2 = new _09_02_UnionFind_Tree(size);
        System.out.println("UnionFind_Tree : " + testUF(uf2, m) + " s"); // UnionFind_Tree : 13.6387356 s
    }

    private static void test2() {
        int size = 10000000;
        int m = 10000000;
        _09_03_UnionFind_Tree_Size uf3 = new _09_03_UnionFind_Tree_Size(size);
        System.out.println("UnionFind_Tree_Size : " + testUF(uf3, m) + " s"); // UnionFind_Tree_Size : 4.9611325 s

        _09_04_UnionFind_Tree_Rank uf4 = new _09_04_UnionFind_Tree_Rank(size);
        System.out.println("UnionFind_Tree_Rank : " + testUF(uf4, m) + " s"); // UnionFind_Tree_Rank : 5.4220966 s
    }

    private static void test3() {
        int size = 10000000;
        int m = 10000000;

        _09_05_UnionFind_Tree_Rank_Path uf5 = new _09_05_UnionFind_Tree_Rank_Path(size);
        System.out.println("UnionFind_Tree_Rank_Path : " + testUF(uf5, m) + " s");
        // UnionFind_Tree_Rank_Path : 4.7202086 s

        _09_06_UnionFind_Tree_Rank_Path_Recursion uf6 = new _09_06_UnionFind_Tree_Rank_Path_Recursion(size);
        System.out.println("UnionFind_Tree_Rank_Path_Recursion : " + testUF(uf6, m) + " s");
        // UnionFind_Tree_Rank_Path_Recursion : 4.7153203 s


    }

    public static void main(String[] args) {
//        test1();
//        test2();

        System.out.println("start");
        test3();
        System.out.println("end");
    }
}
