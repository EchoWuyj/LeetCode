package alg_01_ds_dm._01_line._05_algo._03_sort_二刷;

import java.util.Arrays;

/**
 * @Author Wuyj
 * @DateTime 2023-05-09 15:24
 * @Version 1.0
 */
public class _06_MergeSorter1_推荐 {

    // 归并排序
    // 基本思路：
    // 1.将无序数组从中间分成前后两个部分
    // 2.分别对前后两个部分进行排序
    // 3.将排好序的两部分有序数组进行合并

    // 总结：
    // 1.划分
    // 2.排序
    // 3.合并

    // 时间复杂度 O(nlogn) => 详细证明见 PPT
    public static void sort(int[] data) {
        if (data == null || data.length <= 1) return;
        int n = data.length;
        // 空间复杂度 O(n)，不是原地算法
        int[] tmp = new int[n];
        // 大问题：原数组进行排序
        // 子问题：前后两部分子数组，分别进行排序
        // => 调用 sort 方法，递归实现
        sort(data, 0, data.length - 1, tmp);
    }

    // KeyPoint 方法一 自顶而下 => 递归实现
    // 子问题：子数组进行排序
    private static void sort(int[] data, int left, int right, int[] tmp) {

        // 1.终止递归条件 => 最小子问题
        // 一个元素本身就是有序的
        if (left == right) return;

        // 2.递的过程 => 分别对两个子问题求解
        int mid = (left + right) / 2;
        // 类似于：二叉树，左右子树，分别依次处理
        sort(data, left, mid, tmp);
        sort(data, mid + 1, right, tmp);

        // 3.归的过程 => 合并两个有序的数组
        // 即合并 [left...mid] 和 [mid + 1, right]，这两个数组在上面已经排过序了
        merge2(data, left, mid, right, tmp);

        // KeyPoint 递归思考方向
        // 1.从递和归两个方向，梳理操作逻辑
        //   递的过程做什么，归的过程做什么?
        // 2.合并递和归，从而实现完整的递归逻辑
    }

    // KeyPoint 合并两个有序的数组 => 实现一
    // 递归函数，形参传入临时数组 tmp，避免每次 merge 都去创建数组
    // 在 sort 方法中，最开始创建 tmp，后面每次调用即可，局部使用
    private static void merge(int[] data, int left, int mid, int right, int[] tmp) {

        // KeyPoint 思路：
        // 核心：data 有序数组
        // 1.data 归并排序到 tmp    => 归并操作
        // 2.tmp 再往 data 上拷贝   => 无脑拷贝

        // 临时数组指针，遍历 tmp 数组
        // index 不一定都是从 0 开始，有可能右半部分区间合并
        int index = left;

        // data 数组指针
        int i = left;
        int j = mid + 1;

        // 在 i 和 j 都没有越界，即左右部分都有数据
        // 根据 [i]，[j] 大小，按照从小到大的顺序，依次拷贝到临时的数组中
        while (i <= mid && j <= right) {
            // [i] 和 [j] 谁小，使用谁赋值
            if (data[i] <= data[j]) {
                tmp[index++] = data[i++];
            } else { // data[i] > data[j]
                tmp[index++] = data[j++];
            }
        }

        // 如果左边还有元素，则直接将左边的元素拷贝到临时数组
        while (i <= mid) {
            tmp[index++] = data[i++];
        }

        // 如果右边还有元素，则直接将右边的元素拷贝到临时数组
        while (j <= right) {
            tmp[index++] = data[j++];
        }

        // 从 tmp 往 data 拷贝
        // 注意：index 索引范围 [left,right]
        for (index = left; index <= right; index++) {
            // data 又重新从 left 开始
            // tmp[index] 中不用 index 不用++，已经是在 for 循环中，存在 index++
            data[left++] = tmp[index];
        }
    }

    // KeyPoint 合并两个有序的数组
    //  => 实现二 => 经常使用，需要掌握
    //  => merge 数组范围都是 [left,right]
    private static void merge2(int[] data, int left, int mid, int right, int[] tmp) {

        // KeyPoint 思路：
        // 核心：data 有序数组
        // 1.先将 data 数组中元素，拷贝到 tmp 中 => 无脑拷贝
        // 2.再针对 tmp，将其归并到 data 中      => 归并操作
        //                                     => 归并算法题目，常在归并操作中有骚操作

        // 1.将 data 数组中元素，拷贝到 tmp 中
        // 范围 [left,right]，并不是全部数组长度
        for (int i = left; i <= right; i++) {
            tmp[i] = data[i];
        }

        // tmp 左半部分，起始指针
        int i = left;
        // tmp 右半部分，起始指针
        int j = mid + 1;

        // index 是 data 数组中遍历指针
        // 范围 [left,right]，并不是全部数组长度
        for (int index = left; index <= right; index++) {

            // KeyPoint 编程技巧：
            // if else 分支中，先将极端情况放在前面，将极端情况排除之后就是一般情况

            // KeyPoint 整个过程 tmp 赋值给 data，逻辑关系不要反了，很容易出错
            // 1.左边没有元素，右边有元素
            //   i 最右为 mid，i == mid+1 越界
            // 2.for 循环中，对 index 进行限制，不存在 i == mid+1 && j == right+1 情况
            //   故 if 条件，可以只写一个 i == mid+1 条件，否则就是遗漏一种情况
            if (i == mid + 1) {
                data[index] = tmp[j++];
            } else if (j == right + 1) {
                // 2.左边有元素，右边没有元素
                //   j 最右为 right，j == right+1 越界
                data[index] = tmp[i++];
                // KeyPoint 易错点
                // 1.tmp 数组间比较
                // 2.同时是 tmp 赋值给 data，不是 data 赋值 data
            } else if (tmp[i] <= tmp[j]) {
                // 3.左边有元素，右边有元素，比较大小进行归并，谁小就先归并谁
                // 注意：比较的是 tmp，被赋值的是 data
                data[index] = tmp[i++];
            } else {
                data[index] = tmp[j++];
            }

            // KeyPoint 写代码，很容易，shift 没有按上(使劲按)，导致 + 和 = 相混淆，自己小心

            // KeyPoint 总结
            // 1.空间复杂度是 O(n)，不是原地排序算法
            // 2.归并排序是稳定的排序算法
        }
    }

    public static void main(String[] args) {
        int[] data = new int[]{34, 33, 12, 78, 21, 1, 98, 100};
        sort(data);
        System.out.println(Arrays.toString(data)); // [1, 12, 21, 33, 34, 78, 98, 100]
    }
}
