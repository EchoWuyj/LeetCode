package alg_01_ds_dm._01_line._05_algo._03_sort;

import java.util.Arrays;

/**
 * @Author Wuyj
 * @DateTime 2023-07-07 20:04
 * @Version 1.0
 */
public class _06_MergeSorter2 {

    // KeyPoint 方法二 自低朝上 => 迭代实现(不是重点)
    public static void sortBottomUp(int[] data) {
        if (data == null || data.length <= 1) return;
        int n = data.length;
        int[] tmp = new int[n];

        // 1.两两合并(子数组长度为1)，四四合并，八八合并 ...
        //   xx 合并，xx 指整体数组长度
        // 2.size 表示子数组的长度，从 1,2,4,8... 依次递增
        for (int size = 1; size < n; size += size) {

            // 思路：
            // 1.通过 left，mid，right 三个索引变量定位两组数组
            //   [left,mid]，[mid+1,right]
            // 2.后续通过 merge 方法将其合并

            // 补充说明：关于 for 循环 left < n - size
            // left < n - size => 从数组结尾逆向推得坐标

            // 比如：
            // size = 1，两个元素一组
            // 索引 0 1  2 3  4 5 6
            // 元素 1 2  3 4  5 6 7
            //                 |--|
            // 两个元素一组，进行分组，left 最多为 5，
            // 因为 n = 7，left < n - 1 => left < 7 - 1 = 6 => 5
            // 最后一组索引 5 7

            // 注意：left < n - size 指的是上限，有可能取不到
            // 实际上，left 只能为 4 或者 6，不为 5

            // 索引 0 1  2 3  4 5 6
            // 元素 1 2  3 4  5 6 7
            //     |--| |--| |--|

            // KeyPoint 对于抽象坐标变换，通过简单具体例子，来梳理数学关系
            // 如：四四合并，size = 2
            //   0   1   2      3
            // left mid mid+1 right
            // mid = left + size - 1
            // right = left + 2 * size - 1;
            for (int left = 0; left < n - size; left += 2 * size) {
                // mid 为第一组数组的结尾
                int mid = left + size - 1;
                // KeyPoint 处理数组 mid 和 right 可能越界的一种方式
                // 更加保险，铁定不会错
                // int mid = Math.min(left + size - 1, n - 1);

                // 数组索引从 0 开始，故计算 right 还要 -1
                // 同时 right 还不能超过 n-1，故两者中取最小值
                int right = Math.min(left + 2 * size - 1, n - 1);
                // merge 合并需要 left，mid，right 前中后三个位置指针
                merge1(data, left, mid, right, tmp);
            }
        }
    }

    // KeyPoint 合并两个有序的数组 => 实现一
    // 递归函数，形参传入临时数组 tmp，避免每次 merge 都去创建数组
    // 在 sort 方法中，最开始创建 tmp，后面每次调用即可，局部使用
    private static void merge(int[] data, int left, int mid, int right, int[] tmp) {

        // 思路：
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

    // KeyPoint 合并两个有序的数组 => 实现二 => 经常使用，需要掌握
    public static void merge1(int[] data, int left, int mid, int right, int[] tmp) {
        for (int i = left; i <= right; i++) {
            tmp[i] = data[i];
        }

        int i = left;
        int j = mid + 1;

        for (int index = left; index <= right; index++) {
            if (i == mid + 1) {
                data[index] = tmp[j++];
            } else if (j == right + 1) {
                data[index] = tmp[i++];
            } else if (tmp[i] <= tmp[j]) {
                data[index] = tmp[i++];
            } else {
                data[index] = tmp[j++];
            }
        }
    }

    public static void main(String[] args) {
        int[] data = new int[]{34, 33, 12, 78, 21, 1, 98, 100};
        sortBottomUp(data);
        System.out.println(Arrays.toString(data)); // [1, 12, 21, 33, 34, 78, 98, 100]
    }
}
