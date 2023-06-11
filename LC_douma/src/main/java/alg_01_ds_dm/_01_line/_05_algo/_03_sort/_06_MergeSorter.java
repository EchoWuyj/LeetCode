package alg_01_ds_dm._01_line._05_algo._03_sort;

import java.util.Arrays;

/**
 * @Author Wuyj
 * @DateTime 2023-05-09 15:24
 * @Version 1.0
 */
public class _06_MergeSorter {

    // 归并排序基本思路：
    // 1.将无序数组从中间分成前后两个部分
    // 2.分别对前后两个部分进行排序
    // 3.将排好序的两部分有序数组进行合并

    public static void sort(int[] data) {
        if (data == null || data.length < 2) return;
        // 空间复杂度 O(n)，不是原地算法
        int[] tmp = new int[data.length];
        // 大问题：原数组进行排序
        // 子问题：前后两部分子数组，分别进行排序
        // => 调用 sort 方法，递归实现
        sort(data, 0, data.length - 1, tmp);
    }

    // KeyPoint 自顶而下 => 递归实现
    // 子问题：子数组进行排序
    private static void sort(int[] data, int left, int right, int[] tmp) {

        // 1.终止递归条件 => 最小子问题
        if (left == right) return;

        // 2.递的过程 => 分别对两个子问题求解
        int mid = (left + right) / 2;
        sort(data, left, mid, tmp);
        sort(data, mid + 1, right, tmp);

        // 3.归的过程 => 合并两个有序的数组
        // 即合并 [left...mid] 和 [mid + 1, right]，这两个数组在上面已经排过序了
        merge2(data, left, mid, right, tmp);

        // 实现递归，从递和归两个方向梳理操作逻辑，递的过程做什么，归的过程做什么?
        // => 从而实现完整的递归逻辑
    }

    // KeyPoint 合并两个有序的数组 => 实现一
    // 思路：data 归并到 tmp，tmp 再往 data 上拷贝
    // 递归函数，形参传入临时数组 tmp，避免每次 merge 都去创建数组
    // 在 sort 方法中，最开始创建 tmp，后面每次调用即可，局部使用
    private static void merge(int[] data, int left, int mid, int right, int[] tmp) {

        // 临时数组指针
        int tmpPos = left;

        // data 数组指针
        int i = left;
        int j = mid + 1;

        // 在 i 和 j 都没有越界，即左右部分都有数据
        // 根据 [i]，[j] 大小，按照从小到大的顺序，依次拷贝到临时的数组中
        while (i <= mid && j <= right) {
            // [i] 和 [j] 谁小，使用谁赋值
            if (data[i] <= data[j]) {
                tmp[tmpPos++] = data[i++];
            } else { // data[i] > data[j]
                tmp[tmpPos++] = data[j++];
            }
        }

        // 如果左边还有元素，则直接将左边的元素拷贝到临时数组
        while (i <= mid) {
            tmp[tmpPos++] = data[i++];
        }

        // 如果右边还有元素，则直接将右边的元素拷贝到临时数组
        while (j <= right) {
            tmp[tmpPos++] = data[j++];
        }

        // 从 tmp 往 data 拷贝
        for (tmpPos = left; tmpPos <= right; tmpPos++) {
            data[left++] = tmp[tmpPos];
        }
    }

    // KeyPoint 合并两个有序的数组 => 实现二
    // 思路：先将 data 数组中元素，拷贝到 tmp 中，再针对 tmp，将其归并到 data 中
    private static void merge2(int[] data, int left, int mid, int right, int[] tmp) {
        // 将 data 数组中元素，拷贝到 tmp 中
        for (int i = left; i <= right; i++) {
            tmp[i] = data[i];
        }

        // tmp 左半部分，起始指针
        int i = left;
        // tmp 右半部分，起始指针
        int j = mid + 1;

        // k 是 data 数组中遍历指针
        for (int k = left; k <= right; k++) {

            // 编程技巧：
            // if else 分支中，先将极端情况放在前面，将极端情况排除之后就是一般情况

            // 1.左边没有元素，右边有元素
            // i 最右为 mid
            if (i == mid + 1) {
                data[k] = tmp[j++];
                // 2.左边有元素，右边没有元素
                // j 最右为 right
            } else if (j == right + 1) {
                data[k] = tmp[i++];
                // 3.左边有元素，右边有元素，比较大小进行归并，谁小就先归并谁
            } else if (tmp[i] <= tmp[j]) {
                data[k] = tmp[i++];
            } else {
                data[k] = tmp[j++];
            }
        }
    }

    // KeyPoint 自低朝上 => 迭代实现(不是重点)
    public static void sortBU(int[] data) {
        if (data == null || data.length <= 1) return;
        int len = data.length;
        int[] tmp = new int[len];

        // size 表示子数组的长度，从 1,2,4,8... 依次递增
        // 即从两两合并，四四合并，八八合并
        for (int size = 1; size < len; size += size) {

            // left < len - size => 从数组结尾逆向推得坐标
            // size = 1
            // 索引 0 1 2 3 4 5 6
            // 元素 1 2  3 4  5 6  7
            //     |--| |--| |--|
            // 2 个元素一组，进行分组，left 最多为 5
            // 因为 len = 7，left < len - 1 => left < 7 - 1 = 6 => 5
            // 最后一组索引 5 6

            for (int left = 0; left < len - size; left += 2 * size) {
                int mid = left + size - 1;
                // 数组索引从 0 开始，故计算 right 还要 -1
                // 同时 right 还不能超过 len-1，故两者中取最小值
                int right = Math.min(left + 2 * size - 1, len - 1);
                // merge 合并需要 left，mid，right 前中后三个位置指针
                merge(data, left, mid, right, tmp);
            }
        }
    }

    public static void main(String[] args) {
        int[] data = new int[]{34, 33, 12, 78, 21, 1, 98, 100};
         sort(data);
//        sortBU(data);
        System.out.println(Arrays.toString(data)); // [1, 12, 21, 33, 34, 78, 98, 100]
    }
}
