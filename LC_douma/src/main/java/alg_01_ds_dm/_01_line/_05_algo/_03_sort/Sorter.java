package alg_01_ds_dm._01_line._05_algo._03_sort;

/**
 * @Author Wuyj
 * @DateTime 2023-05-08 21:51
 * @Version 1.0
 */

// 排序父类 => 自定义
public class Sorter {

    // 支持泛型 T，泛型方法需要方法签名中申明 <T>
    public <T> void swap(T[] nums, int i, int j) {
        T tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    public void swap(Integer[] nums, int i, int j) {
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
