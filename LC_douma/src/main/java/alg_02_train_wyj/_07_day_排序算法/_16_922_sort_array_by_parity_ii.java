package alg_02_train_wyj._07_day_排序算法;

/**
 * @Author Wuyj
 * @DateTime 2023-05-17 15:22
 * @Version 1.0
 */
public class _16_922_sort_array_by_parity_ii {

    public int[] sortArrayByParityII1(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        int i = 0;
        for (int num : nums) {
            if (num % 2 == 0) {
                res[i] = num;
                i += 2;
            }
        }
        i = 1;
        for (int num : nums) {
            if (num % 2 == 1) {
                res[i] = num;
                i += 2;
            }
        }
        return res;
    }

    public int[] sortArrayByParityII(int[] nums) {
        int n = nums.length;
        int i = 0, j = 1;
        while (i < n) {
            if (nums[i] % 2 == 1) {
                while (nums[j] % 2 == 1) j += 2;
                swap(nums, i, j);
            }
            i += 2;
        }
        return nums;
    }

    public void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
