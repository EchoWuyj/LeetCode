package alg_01_ds_wyj._01_line._05_algo._03_sort;

/**
 * @Author Wuyj
 * @DateTime 2023-05-09 10:49
 * @Version 1.0
 */
public class Sorter {
    public static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void swap(Integer[] nums, int i, int j) {
        Integer tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    public <E> void swap(E[] data, int i, int j) {
        E tmp = data[i];
        data[i] = data[j];
        data[j] = tmp;
    }
}
