package alg_01_ds_dm._01_line._05_algo._03_sort;

import java.util.Arrays;

/**
 * @Author Wuyj
 * @DateTime 2023-05-09 10:47
 * @Version 1.0
 */
public class _03_InsertionSorter extends Sorter {

    // 思路：将未排序数组每个元素，插入到已经有序数组中合适位置
    // 时间复杂度：O(n^2)
    // 空间复杂度：O(1)
    public void sort1(int[] data) {
        int n = data.length;
        if (data == null || n <= 1) return;
        // 插入排序的轮数
        // 几个元素 => 几轮插入
        // i = 1，第一个元素，默认其是有序的，不需要插入排序
        for (int i = 1; i < n; i++) {
            // j 是比较交换指针，且 j 从 i 开始
            // j > 0，严格大于零，保证 i-1 不越界
            for (int j = i; j > 0; j--) {
                // [j] 更小，往前放，[j-1] 和 [j] 交换
                // [j] 严格小于，才进行交换，不改变原来相对次序，稳定排序算法
                if (data[j] < data[j - 1]) {
                    swap(data, j, j - 1);
                } else {
                    // 已经将 [j] 放到合适位置，[j-1] < [j] < [j+1]，跳出内层插入循环
                    break;
                }
            }
        }
    }

    // 优化：不使用元素交换实心插入排序 => 通过赋值代替交换，赋值操作每个元素只是访问一次
    // 交换方式 => 每个元素被访问两次，消耗时间
    // 时间复杂度：O(n^2)
    // 空间复杂度：O(1)
    public void sort(int[] data) {
        int n = data.length;
        if (data == null || n <= 1) return;
        for (int i = 1; i < n; i++) {
            // 临时变量记录 [i]
            int tmp = data[i];
            // j 定义在 for 循环外，为了让后面 data[j] 可以使用
            int j;
            for (j = i; j > 0; j--) {
                // 始终使用 tmp 进行比较，不是 [j-1]，为了找到 tmp 正确位置
                if (tmp < data[j - 1]) {
                    // 赋值代替交换
                    // [j-1] 更大，使用 [j-1] 覆盖 [j]，[j-1] 右移
                    data[j] = data[j - 1];
                } else {
                    break;
                }
            }
            // 找到 i 对应的元素需要插入的位置
            data[j] = tmp;
        }
    }

    public static void main(String[] args) {
        int[] data = new int[]{12, 23, 36, 9, 24, 42};
        new _03_InsertionSorter().sort(data);
        new _03_InsertionSorter().sort1(data);
        System.out.println(Arrays.toString(data)); // [9, 12, 23, 24, 36, 42]
    }
}
