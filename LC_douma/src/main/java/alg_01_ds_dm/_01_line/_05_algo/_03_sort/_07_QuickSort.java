package alg_01_ds_dm._01_line._05_algo._03_sort;

import java.util.Arrays;

/**
 * @Author Wuyj
 * @DateTime 2023-05-10 9:45
 * @Version 1.0
 */
public class _07_QuickSort extends Sorter {

    // 快排思路：将一个需要排序数组一分为二，分成两个子数组
    //          然后分别对这两个子数组独立排序，之后整个数组就有序了
    // 时间复杂度 O(nlogn) 调用过程和归并排序差不多
    public static void sort(int[] data) {
        if (data == null || data.length < 2) return;
        // 大问题 => 拆分子问题
        sort(data, 0, data.length - 1);
    }

    // 递归实现 => 递归实现快排，系统调用方法栈，空间复杂度 O(logn)
    private static void sort(int[] data, int low, int high) {
        // 递归边界 => 最小子问题
        // 只有一个元素，或者没有元素，则不需要排序
        if (low >= high) return;

        // 递的过程 => 不断划分子问题 => 先分区，再对左右数组排序
        // 1.分区 => 返回分区点的位置索引，每次分区之后，都能确定分区元素的位置
        // 再去对剩下 low 和 index-1 和 index+1，high 进行排序
        // KeyPoint debug 只有执行完该行后，才能 evaluate 变量 j 的值
        int j = partition(data, low, high);

        // 2.左右数组排序 => 都是不包括分区点 j
        // 2.1对左边数组排序
        sort(data, low, j - 1); // log2n
        // 2.2对右边数组排序
        sort(data, j + 1, high); // log2n

        // 归的过程 => 没有明显操作，故这里没有代码
    }

    // 分区逻辑(二路快排)
    private static int partition(int[] data, int low, int high) {
        // 选择数组最后一个元素作为分区点
        int pivot = data[high];

        // KeyPoint 注意点
        // 1.less 和 great 一开始都是从 low 位置开始
        // 2.和三路快排(三向切分) great 在 high 位置进行区别

        // less 表示：小于 pivot 值的后一个位置
        // => 即 less 之前都是小于 pivot 的元素
        int less = low;
        // great 遍历指针，从 low 遍历到 high-1
        int great = low;

        // great 指针，只需要遍历到分区点前一个位置，进行比较即可
        // 因为 data[分区点] == pivot，必然成立，不用交换，只是 great 右移
        // 故 great 没有必要执行到 high 位置
        for (; great <= high - 1; great++) {

            // 非严格取等也是可以的
//            if (data[great] <= pivot) {

            // 严格小于的关系，才进行交换
            if (data[great] <= pivot) {
                swap(data, less, great);
                less++;
            }
            // data[great] >= pivot，不用交换
            // 执行 for 循环 great++，great 右移
        }
        // 结束 while 循环，分区点位置和 less 交换
        swap(data, less, high);
        // 此时 less 为分区点索引，将其返回，该位置元素已经排好序
        return less;
    }

    public static void test1() {
        int[] array = {51, 34, 88, 78, 11, 11, 11, 11};
        sort(array);
        System.out.println(Arrays.toString(array));
    }

    // KeyPoint 补充说明
    // 1.快排是原地排序算法
    //   => 在分区过程中，基于原来的输入数组 data 上，通过交换元素的方式完成的排序，没有申请任何额外空间
    // 2.空间复杂度 O(logn)
    //   递归实现快排，系统调用方法栈
    //   递归过程，将方法压入到栈中 => 最大层数，即为最多方法压入到栈中
    //    => 不必将递归树所有节点，当做最大栈空间，因为递归过程中存在复用和释放
    //    => 假设最后一层，层数为 k，n/2^k = 1(子数组个数) => k = log2(n)，最多调度 log2(n) 层
    //    => 递归树的高度 => 系统调用栈空间大小 => 空间复杂度 O(logn)

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
