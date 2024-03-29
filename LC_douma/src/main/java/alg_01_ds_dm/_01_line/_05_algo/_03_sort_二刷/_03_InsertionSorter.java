package alg_01_ds_dm._01_line._05_algo._03_sort_二刷;

import java.util.Arrays;

/**
 * @Author Wuyj
 * @DateTime 2023-05-09 10:47
 * @Version 1.0
 */
public class _03_InsertionSorter extends Sorter {

    // 插入排序
    // 思路：将未排序数组每个元素，插入到已经有序数组中合适位置
    // 1.将数组分成已排序区间和未排序区间
    // 2.循环遍历未排序区间中每个元素，在已排序区间中找到应该在位置，然后将其插入
    // 3.以此往复
    // 时间复杂度：O(n^2)
    // 空间复杂度：O(1)
    public void sort1(int[] data) {
        int n = data.length;
        if (data == null || n <= 1) return;
        // 插入排序的轮数：几个元素 => 几轮插入
        // 注意：第一个元素，默认其是有序的，不需要插入排序，故减少一轮排序，i 从 1 开始
        for (int i = 1; i < n; i++) {
            // j 是比较交换指针，且 j 从 i 开始
            // j > 0，严格大于零，保证 i-1 不越界
            for (int j = i; j >= 1; j--) {
                // 1.[j] 是当前元素，[j-1] 更小，[j] 往前放，[j-1] 和 [j] 交换
                // 2.[j] 严格小于，才进行交换，不改变原来相对次序，稳定排序算法
                // 3.若代码执行 if 分支，执行完 swap 后，则还需要执行 for 循环
                //   即一直往前比较，直到找到合适的位置，结束 for 循环
                if (data[j] < data[j - 1]) {
                    swap(data, j, j - 1);
                } else {
                    // 已经将 [j] 放到合适位置，[j-1] < [j] < [j+1]，跳出内层插入循环
                    break;
                }
            }
        }
    }

    // 优化：不使用元素交换实心插入排序
    // 1.通过赋值代替交换，赋值操作每个元素只是访问一次
    // 2.交换方式，每个元素被访问两次，消耗时间
    // 时间复杂度：O(n^2)
    // 空间复杂度：O(1)
    public void sort(int[] data) {
        int n = data.length;
        if (data == null || n <= 1) return;
        for (int i = 1; i < n; i++) {
            // 临时变量 tmp 记录 [i]
            int tmp = data[i];
            // j 定义在 for 循环外，为了让后面 data[j] 可以使用
            int j;
            for (j = i; j >= 1; j--) {
                // 始终使用 tmp 进行比较，不是 [j-1]，为了找到 tmp 正确位置
                if (tmp < data[j - 1]) {
                    // 不做交换，只是赋值，一次赋值，每个元素访问一次
                    // 因为 tmp < data[j-1] => [j-1] 更大，故使用 [j-1] 赋值 [j]，等价于 [j-1] 右移
                    // 被赋值 ← 赋值，逻辑不要搞反了，写成了 data[j-1] = data[j]
                    data[j] = data[j - 1];
                } else {
                    // tmp >= data[j-1]
                    break;
                }
            }
            // 找到 i 对应的元素需要插入的位置
            data[j] = tmp;
        }
    }

    public static void main(String[] args) {
        int[] data = new int[]{12, 23, 36, 9, 24, 42};
//        new _03_InsertionSorter().sort(data);
        new _03_InsertionSorter().sort1(data);
        System.out.println(Arrays.toString(data)); // [9, 12, 23, 24, 36, 42]
    }
}
