package alg_01_ds_dm._01_line._05_algo._03_sort;

import java.util.Arrays;

/**
 * @Author Wuyj
 * @DateTime 2023-05-10 9:45
 * @Version 1.0
 */
public class _07_QuickSort extends Sorter {

    // 快排思路：
    // 1.将一个需要排序数组一分为二，分成两个子数组
    // 2.然后分别对这两个子数组独立排序，之后整个数组就有序了
    // 3.注意：快排没有 merge 过程，区别于：归并排序

    // 1 3 2 4  6 5 8 7
    //   ↙        ↘
    // 1 2 3 4  5 5 7 8

    // 时间复杂度 O(nlogn) 调用过程和归并排序差不多
    public static void sort(int[] data) {
        if (data == null || data.length <= 1) return;
        // 大问题 => 拆分子问题
        sort(data, 0, data.length - 1);
    }

    // 快排 => 满足递归 3 个特点
    // 1.每个大问题拆分成两个子问题，子问题解决了，大问题就解决了
    // 2.子问题求解方式和大问题求解方式一样
    // 3.存在最小子问题
    // 递归实现 => 递归实现快排，系统调用方法栈，空间复杂度 O(logn)
    private static void sort(int[] data, int low, int high) {
        // 递归边界 => 最小子问题
        // 只有一个元素，或者没有元素，则不需要排序
        if (low >= high) return;

        // 递的过程
        // 不断划分子问题 => 先分区，再对左右数组排序

        // 分区
        // 1.返回分区点的位置索引，每次分区之后，都能确定分区元素的位置
        // 2.再去对剩下 low 和 index-1 和 index+1，high 进行排序
        int j = partition(data, low, high);

        // 补充说明：
        // debug 只有执行完该行 partition(data, low, high) 后，才能 evaluate 变量 j 的值

        // 左右数组排序 => 都是不包括分区点 j
        // 1.对左边数组排序
        sort(data, low, j - 1); // log2n
        // 2.对右边数组排序
        sort(data, j + 1, high); // log2n

        // 归的过程 => 没有明显操作，故这里没有代码
    }

    // 分区逻辑(二路快排)
    private static int partition(int[] data, int low, int high) {

        // 分区逻辑(二路快排) => 划分子数组逻辑
        // 1.找到一个分区点 pivot (本题中将数组最后一个元素作为分区点)
        // 2.将大于 pivot 元素放到 pivot 后面
        // 3.将小于 pivot 元素放到 pivot 前面

        // 51 34 88 78 32 9 11 37
        //                      ↑
        //                    pivot(分区点)

        //            ↓ 分区
        // 34 32 9 11 37 51 88 78

        // 将左子数组排序 |9 11 32 34| 37 51 88 78
        // 将右子数组排序  9 11 32 34 37 |51 78 88|
        // 结果(整体有序)  9 11 32 34 37 51 78 88

        // KeyPoint 详细过程

        // 一、变量定义
        //   1.pivot：选择数组最后一个元素作为分区点
        //   2.less：小于 pivot 值的后一个位置，即 less 之前都是小于 pivot 的元素
        //           但是 less 位置元素是大于 pivot 的
        //   3.great：大于 pivot 值的后一个位置，great 为遍历指针，从 low 遍历到 high-1

        // 二、比较逻辑
        //   1.若 data[great] < pivot，则交换 great 和 less 对应元素值，即 swap(data, less, great)
        //     同时，less 指针前移，great 指针前移
        //   2.若 data[great] > pivot，不进行任何操作，只是 great 指针前移
        //   3.循环上面操作，直到 great 指针遍历到分区点前一个位置即可，因为 data[high] == pivot，
        //     必然成立，不用交换，只是 great 右移，故 great 没有必要执行到 high 位置
        //   4. 结束 for 循环，分区点位置和 less 交换，此时 less 为分区点索引，将其返回，该位置元素已经排好序

        // KeyPoint 易错点
        // less 和 great 一开始都是从 low 位置开始
        // 区别：三路快排(三向切分) great 在 high 位置进行

        int pivot = data[high];
        int less = low;
        int great = low;
        for (; great <= high - 1; great++) {

            // 注意：非严格取等也是可以的
//            if (data[great] <= pivot) {

            // 严格小于，才进行交换
            if (data[great] < pivot) {
                swap(data, less, great);
                // less 右移 => 交换后区间发生变化了，不要遗留了
                less++;
            }
            // data[great] >= pivot，不用交换
            // 执行 for 循环 great++，great 右移
        }

        // 结束 for 循环，分区点位置和 less 交换
        swap(data, less, high);
        // 此时 less 为分区点索引，将其返回，该位置元素已经排好序
        return less;
    }

    // KeyPoint 快排总结
    // 1.时间复杂度 O(nlogn)
    //   => 快排调用过程和归并差不多，归并时间复杂度 O(nlogn) => 快排 O(nlogn)
    // 2.空间复杂度 O(logn)，但是快排是原地排序算法
    //   => 在分区过程中，基于原来的输入数组 data 上，通过交换元素的方式完成的排序，没有申请任何额外空间
    //   => 递归实现快排， 递归中涉及：系统调用方法栈
    //      1.递归过程，将方法压入到栈中，其中递归树最大层数，即为最多方法压入到栈中
    //        不必将递归树所有节点，当做最大栈空间，因为递归过程中存在复用和释放
    //      2.假设最后一层，层数为 k，子数组个数公式：n/(2^k)，当其到递归边界，即仅有 1 个元素
    //        n/(2^k)=1 => k = log2(n)，递归树最大层数 log2(n) => 多方法压入到栈中
    //      3.递归树的高度 => 系统调用栈空间大小 => 空间复杂度 O(logn)
    // 3.并不是原地排序算法的空间复杂度就是 O(1)
    //   空间复杂度是 O(1) 的排序算法，肯定是原地排序算法
    // 4.快排不稳定排序算法 => 记忆：快则不稳定

    public static void test1() {
        int[] array = {51, 34, 88, 78, 11, 11, 11, 11};
        sort(array);
        System.out.println(Arrays.toString(array));
    }

    public static void main(String[] args) {
        test(); // [1, 12, 21, 33, 34, 78, 98, 100]
        test1(); // [11, 11, 11, 11, 34, 51, 78, 88]
    }

    private static void test() {
        int[] data = new int[]{34, 33, 12, 78, 21, 1, 98, 100};
        sort(data);
        System.out.println(Arrays.toString(data)); // [1, 12, 21, 33, 34, 78, 98, 100]
    }
}
