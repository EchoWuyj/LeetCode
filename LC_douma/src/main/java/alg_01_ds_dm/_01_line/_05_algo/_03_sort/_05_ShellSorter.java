package alg_01_ds_dm._01_line._05_algo._03_sort;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @Author Wuyj
 * @DateTime 2023-05-09 13:44
 * @Version 1.0
 */

public class _05_ShellSorter extends Sorter {

    // 希尔排序思想：先使数组中任间隔为 h 的元素有序，然后对全局进行排序
    // 希尔排序 => 本是就是插入排序，是对插入排序的优化
    public void sort(int[] data) {
        if (data == null || data.length <= 1) return;

        // 1. 计算递增序列
        int n = data.length;
        // 存储满足条件的递增序列
        ArrayList<Integer> list = new ArrayList<>();
        // k 是 1,2,3,4 递增自然数
        int k = 1;
        int h;
        do {
            // 通过公式来计算 h，h = (3^k-1)/2，其中 k 是 1,2,3,4 递增自然数
            // (int)Math.pow => 提前将其转成 int 类型
            h = ((int) Math.pow(3, k) - 1) / 2;
            // KeyPoint bug 修复：需要考虑 n < 3 的场景
            // 若 n < 3，如 n = 2，h = 1 >  2 / 3，还是应该将 h = 1 添加到 list 中，而不是 break
            if (h > n / 3 && n >= 3) break;
            // 正常情况下 h 取值：1, 4, 13, 40, 121 ...
            // 具体能 add 那些值，需要根据数组的长度 n 来判断
            list.add(h);
            k++;
        } while (h <= n / 3);

        // 2. 希尔排序
        // 倒序遍历 => 先是间隔大 h 排序，再是间隔小 h 排序，最后是全局排序
        for (int ele = list.size() - 1; ele >= 0; ele--) {
            // 本题中 h = 4，再 h = 1
            h = list.get(ele);
            // 将数组变为 h 有序，代码和插入排序代码一样，将原来间隔 1，替换成 h
            // i 从 h 位置开始，每往后移动一步，都需要照 h 间隔，进行插入排序判断
            for (int i = h; i < n; i++) {
                // j 从 i 位置开始，按照 h 间隔，比较大小，调整顺序
                // j >= h 为了保证 j -h 不越界
                for (int j = i; j >= h; j = j - h) {
                    if (data[j] < data[j - h]) {
                        // KeyPoint 优化：赋值代替交换
                        swap(data, j, j - h);
                    } else {
                        break;
                    }
                }
            }
        }
    }

    // 优化：不使用额外数组，空间复杂度 O(1)
    public void sort1(int[] data) {
        if (data == null || data.length <= 1) return;

        // 1. 计算递增序列 => 另外一种实现
        // 更换了 h 计算方式，避免使用 ArrayList 进行存储
        int n = data.length;
        int h = 1;
        while (h < n / 3) h = 3 * h + 1; // 1, 4, 13, 40, 121......

        // 2. 希尔排序
        while (h >= 1) {
            // 将数组变为 h 有序
            for (int i = h; i < n; i++) {
                for (int j = i; j >= h; j = j - h) {
                    if (data[j] < data[j - h]) {
                        swap(data, j, j - h);
                    } else {
                        break;
                    }
                }
            }
            h = h / 3;
        }
    }

    public static void main(String[] args) {
        int[] data = new int[]{2, 5, 1, 23, 22, 33, 56, 12, 5, 3, 5, 6, 8, 2, 3, 4};
        new _05_ShellSorter().sort1(data);
        System.out.println(Arrays.toString(data)); // [1, 2, 2, 3, 3, 4, 5, 5, 5, 6, 8, 12, 22, 23, 33, 56]
    }
}
