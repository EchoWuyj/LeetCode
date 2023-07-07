package alg_01_ds_wyj._01_line._05_algo._03_sort;

/**
 * @Author Wuyj
 * @DateTime 2023-05-09 10:49
 * @Version 1.0
 */
public class Sorter {

    // 支持泛型 T，泛型方法需要方法签名中申明 <T>
    public static <T> void swap(T[] nums, int i, int j) {
        T tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    public static void swap(Integer[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    public static void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
