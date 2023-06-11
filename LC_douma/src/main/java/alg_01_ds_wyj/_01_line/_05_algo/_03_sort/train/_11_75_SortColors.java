package alg_01_ds_wyj._01_line._05_algo._03_sort.train;

/**
 * @Author Wuyj
 * @DateTime 2023-05-12 19:03
 * @Version 1.0
 */
public class _11_75_SortColors {
    public void sortColors1(int[] nums) {
        if (nums == null) return;
        int[] count = new int[3];
        for (int num : nums) {
            count[num]++;
        }

        int index = 0;
        for (int i = 0; i <= 2; i++) {
            int num = count[i];
            for (int j = 1; j <= num; j++) {
                nums[index++] = i;
            }
        }
    }

    public void sortColors2(int[] nums) {
        if (nums == null) return;
        int zero = 0;
        int two = nums.length - 1;
        int i = 0;

        while (i <= two) {
            if (nums[i] == 0) {
                swap(nums, i, zero);
                i++;
                zero++;
            } else if (nums[i] == 2) {
                swap(nums, i, two);
                two--;
            } else {
                i++;
            }
        }
    }

    public void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
