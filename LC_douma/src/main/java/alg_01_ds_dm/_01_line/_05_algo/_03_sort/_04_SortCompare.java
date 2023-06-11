package alg_01_ds_dm._01_line._05_algo._03_sort;

import java.util.Random;

/**
 * @Author Wuyj
 * @DateTime 2023-05-09 12:07
 * @Version 1.0
 */
public class _04_SortCompare {

    private static Random random = new Random();

    // 随机构建一个数组
    private static int[] genData(int n) {
        int[] data = new int[n];
        for (int i = 0; i < n; i++) {
            data[i] = random.nextInt();
        }
        return data;
    }

    // 不同排序算法，排序随机数组，花费时间
    private static long time(String sortType, int[] data) {
        long start = System.currentTimeMillis();

        if (sortType.equals("bubble")) new _01_BubbleSorter().sort(data);
        else if (sortType.equals("selection")) new _02_SelectionSorter().sort(data);
        else if (sortType.equals("insertion")) new _03_InsertionSorter().sort(data);
        else if (sortType.equals("insertion1")) new _03_InsertionSorter().sort1(data);
        else if (sortType.equals("shell")) new _05_ShellSorter().sort(data);

        return System.currentTimeMillis() - start;
    }

    // k 个，长度为 n 数组，测试
    private static long manyTimesSort(String sortType, int n, int k) {
        long totalTime = 0;
        for (int i = 0; i < k; i++) {
            totalTime += time(sortType, genData(n));
        }
        return totalTime;
    }

    public static void main(String[] args) {
        // 将其转成 double 类型，ti / tj 有小数点，显示结果比较清晰
        double t1 = manyTimesSort("bubble", 1000, 100);
        double t2 = manyTimesSort("selection", 1000, 100);
        double t3 = manyTimesSort("insertion1", 1000, 100);
        double t4 = manyTimesSort("insertion", 1000, 100);
        double t5 = manyTimesSort("shell", 1000, 100);

        System.out.println(t1 / t2); // t1 > t2 => 用时，bubble > selection
        System.out.println(t2 / t3); // t2 > t3 => 用时，selection > insertion1
        System.out.println(t3 / t4); // t3 > t4 => 用时，交换 > 赋值
        System.out.println(t3 / t5); // t3 > t5  => 用时，insertion1 > shell

        // 结论：插入排序性能最好，其次是选择排序，最后是冒泡排序
        // 补充说明：
        // 1.一般都不会使用冒泡排序，性能实在太差
        // 2.选择排序没有插入排序性能好，且是不稳定排序算法，很少使用
        // 3.以后插入排序使用最多，且使用直接赋值实现

    }
}
