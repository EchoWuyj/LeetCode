package alg_02_train_dm._20_day_数据结构设计;

import java.util.Random;

/**
 * @Author Wuyj
 * @DateTime 2023-05-28 20:25
 * @Version 1.0
 */
public class _10_UnionFind_Test {

    private static double testUF(_09_UnionFind uf, int times) {
        int size = uf.size();
        Random random = new Random();
        // 纳秒 = 10^-9 秒
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
        // 转秒
        return (endTime - startTime) / 1000_000_000.0;
    }

    // for test
    private static void test1() {
        int size = 100000;
        int m = 100000;
        _09_01_UnionFind_Array uf1 = new _09_01_UnionFind_Array(size);
        System.out.println("UnionFind_Array : " + testUF(uf1, m) + " s");
        // UnionFind_Array : 7.1779066 s

        _09_02_UnionFind_Tree uf2 = new _09_02_UnionFind_Tree(size);
        System.out.println("UnionFind_Tree : " + testUF(uf2, m) + " s");
        // UnionFind_Tree : 13.6387356 s

        // KeyPoint 解释说明
        // 1.UnionFind_Tree 中 find 方法，时间复杂度变高，O(1) => O(h)
        // 2.对于数组顺序访问，JVM 会优化，在 CPU 层面有优化
        //   具体来说：数组是整块的，相邻元素先会放到 CPU 缓存中
        //             此时数组顺序访问，只要从缓存获取即可，速度比较快
    }

    // for test
    private static void test2() {
        int size = 10000000;
        int m = 10000000;
        _09_03_UnionFind_Tree_Size uf3 = new _09_03_UnionFind_Tree_Size(size);
        System.out.println("UnionFind_Tree_Size : " + testUF(uf3, m) + " s");
        // UnionFind_Tree_Rank : 5.4220966 s

        _09_04_UnionFind_Tree_Rank uf4 = new _09_04_UnionFind_Tree_Rank(size);
        System.out.println("UnionFind_Tree_Rank : " + testUF(uf4, m) + " s");
        // UnionFind_Tree_Size : 4.9611325 s

        // KeyPoint 解释说明
        // 基于 rank 优化性能更好，因为树的高度更矮

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

        // KeyPoint 解释说明
        // 性能差不多，使用迭代和递归写法都差不多
    }

    public static void main(String[] args) {

        // test1();
        // test2();
        test3();
    }
}
