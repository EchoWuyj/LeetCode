package alg_03_high_frequency._01_codetop_2024_01;

/**
 * @Author Wuyj
 * @DateTime 2024-01-14 1:28
 * @Version 1.0
 */
public class _43_31_nextPermutation {
    public void nextPermutation(int[] nums) {

        // 下一个排列
        // 核心：数组从后往前遍历
        int n = nums.length;

        // i 作为遍历 nums 指针
        // while 循环中涉及 i+1，故 i 从 n - 2 位置开始
        int i = n - 2;
        // 想找的是降序第一个元素的位置
        // 123456
        //     ↑
        // while 循环条件却是升序判断 nums[i] >= nums[i+1]，直到降序才去跳出 while 循环
        while (i >= 0 && nums[i] >= nums[i + 1]) i--;

        // i >= 0，则说明存在 i
        if (i >= 0) {
            // j 也是从 n-1 开始从右往左遍历
            int j = n - 1;
            // 从右往左第一个比[较小数]大
            // 较小数 123465 => 降序第一个元素
            //           ↑
            // 较大数 465 => 从右往左第一个比[较小数]大
            //          ↑
            while (j >= 0 && nums[i] >= nums[j]) j--;
            swap(nums, i, j);
            // 剩余元素反转，即范围 [i+1,n-1]
            reverse(nums, i + 1, n - 1);
        }

        // i == -1，则说明不存在 i
        if (i == -1) {
            // 直接反转，比如 321
            reverse(nums, 0, n - 1);
        }
    }

    // 反转
    public void reverse(int[] nums, int i, int j) {
        while (i < j) {
            swap(nums, i, j);
            i++;
            j--;
        }
    }

    // 交换
    public void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
