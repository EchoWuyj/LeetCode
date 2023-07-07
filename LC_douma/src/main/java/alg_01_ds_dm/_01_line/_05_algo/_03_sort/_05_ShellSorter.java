package alg_01_ds_dm._01_line._05_algo._03_sort;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @Author Wuyj
 * @DateTime 2023-05-09 13:44
 * @Version 1.0
 */

public class _05_ShellSorter extends Sorter {

    // 希尔排序
    // 思想：先对数组进行若干次间隔(h)排序[]，然后对全局进行排序，最终得到一个有序数组
    // => 先对间隔大排序，再对间隔小排序，最后全局排序
    // 希尔排序 => 本是就是插入排序，是对插入排序的优化
    // 时间复杂度 O(n^(3/2)) => 优于 O(n^2)
    // 空间复杂度 使用额外空间 list
    public void sort(int[] data) {
        if (data == null || data.length <= 1) return;

        // KeyPoint 1. 计算递增序列 (h)
        int n = data.length;
        // 存储满足条件的递增序列
        ArrayList<Integer> list = new ArrayList<>();

        // k 是 1,2,3,4,5,6 递增自然数
        int k = 1;

        // 递增序列 h 是有严格数学证明，保证 h 取值使得希尔排序性能更好
        // 经常使用的公式：h=(3^k-1)/2 要求：h <= ⌈N/3⌉，其中 N 为数组的长度
        int h;
        // 若 N=16，N/3=5，h=(3^k-1)/2
        // k=1 => h=1
        // k=2 => h=4
        // k=3 => h=13 => h=[1,4]
        do {
            // 通过公式来计算 h，h=(3^k-1)/2，其中 k 是 1,2,3,4 递增自然数
            // (int)Math.pow => 提前将其转成 int 类型
            h = ((int) Math.pow(3, k) - 1) / 2;

            // KeyPoint bug 修复：需要考虑 n < 3 的场景
            // 若 n < 3，如 n = 2，h = 1 > 2/3，直接 break
            // 但是实际上，还是应该将 h = 1 添加到 list 中，而不是 break
            // 对 h>n/3，附加限制条件，n>=3;
            if (n >= 3 && h > n / 3) break;
            // 正常情况下 h 取值：1, 4, 13, 40, 121 ...
            // 具体能 add 那些值，需要根据数组的长度 n 来判断
            list.add(h);
            k++;
        } while (h <= n / 3);

        // KeyPoint 2. 希尔排序
        // 倒序遍历 => 先是间隔大 h 排序，再是间隔小 h 排序，最后是全局排序
        for (int element = list.size() - 1; element >= 0; element--) {
            // 本题中，先是 h = 4，再 h = 1
            h = list.get(element);

            // 将数组变为 h 有序
            // => 以下代码和插入排序代码一样，将原来间隔 1，替换成 h
            //    类比，最原始间隔 h = 1，则  i 从 1 开始
            // => i 从 h 位置开始，每往后移动一步，都需要照 h 间隔，进行插入排序判断
            for (int i = h; i < n; i++) {
                // for 循环，j 从 i 位置开始，按照 h 间隔，j 依次往前，
                // [j] 和 [j-h] 比较大小，调整排序 => 多次比较，而不是依次比较
                // j >= h 为了保证 j -h 不越界
                for (int j = i; j >= h; j -= h) {
                    if (data[j] < data[j - h]) {
                        // 交换
                        swap(data, j, j - h);
                    } else {
                        break;
                    }
                }
            }
        }
    }

    // KeyPoint 优化：赋值代替交换
    public void sort1(int[] data) {
        if (data == null || data.length <= 1) return;

        // 1.计算递增序列 (h)
        int n = data.length;
        ArrayList<Integer> list = new ArrayList<>();
        int k = 1;
        int h;
        do {
            h = ((int) Math.pow(3, k) - 1) / 2;
            if (n >= 3 && h > n / 3) break;
            list.add(h);
            k++;
        } while (h <= n / 3);

        // 2.希尔排序
        for (int element = list.size() - 1; element >= 0; element--) {
            h = list.get(element);
            for (int i = h; i < n; i++) {
                int tmp = data[i];
                int j;
                for (j = i; j >= h; j = j - h) {
                    if (tmp < data[j - h]) {
                        // 赋值代替交换
                        data[j] = data[j - h];
                    } else {
                        break;
                    }
                }
                data[j] = tmp;
            }
        }
    }

    // 优化：不使用额外数组，空间复杂度 O(1) => 了解
    public void sort2(int[] data) {
        if (data == null || data.length <= 1) return;

        // 1. 计算递增序列 => 另外一种实现
        // 更换了 h 计算方式，避免使用 ArrayList 进行存储
        int n = data.length;
        int h = 1;
        while (h <= n / 3) h = 3 * h + 1; // 1, 4, 13, 40, 121......

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

        // KeyPoint 总结
        // 1.空间复杂度是 O(1)，原地排序算法
        // 2.希尔排序是不稳定的排序算法
    }

    public static void main(String[] args) {
        int[] data = new int[]{2, 5, 1, 23, 22, 33, 56, 12, 5, 3, 5, 6, 8, 2, 3, 4};
        new _05_ShellSorter().sort2(data);
        System.out.println(Arrays.toString(data)); // [1, 2, 2, 3, 3, 4, 5, 5, 5, 6, 8, 12, 22, 23, 33, 56]
    }
}
