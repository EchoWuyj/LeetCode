package alg_02_train_wyj._07_day_排序算法;

import java.util.Arrays;

/**
 * @Author Wuyj
 * @DateTime 2023-05-17 11:14
 * @Version 1.0
 */
public class _15_905_sort_array_by_parity {
    public static int[] sortArrayByParity1(int[] nums) {

        int n = nums.length;
        int[] res = new int[n];
        int index = 0;
        int i = 0;
        while (i < n) {
            if (nums[i] % 2 == 0) {
                res[index++] = nums[i];
            }
            i++;
        }

        // 新的一轮，i 指针从 0 开始
        i = 0;
        while (i < n) {
            if (nums[i] % 2 == 1) {
                res[index++] = nums[i];
            }
            i++;
        }
        return res;
    }

    public static void main(String[] args) {
        int[] arr = {3, 1, 2, 4};
        System.out.println(Arrays.toString(sortArrayByParity1(arr)));
    }

    public int[] sortArrayByParity2(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        int left = 0, right = n - 1;
        for (int i = 0; i < n; i++) {
            if (nums[i] % 2 == 0) {
                res[left++] = nums[i];
            } else {
                res[right--] = nums[i];
            }
        }
        return res;
    }

    public int[] sortArrayByParity3(int[] nums) {
        int n = nums.length;
        Integer[] tmp = new Integer[n];
        for (int i = 0; i < n; i++) {
            tmp[i] = nums[i];
        }

        Arrays.sort(tmp, (o1, o2) -> o1 % 2 - o2 % 2);
        for (int i = 0; i < n; i++) {
            nums[i] = tmp[i];
        }
        return nums;
    }

    public int[] sortArrayByParity4(int[] nums) {
        int n = nums.length;
        int less = 0, great = 0;
        for (; great <= n - 1; great++) {
            if (nums[less] % 2 > nums[great] % 2) {
                swap(nums, less, great);
            }
            if (nums[less] % 2 == 0) less++;
        }

        return nums;
    }

    public void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    public int[] sortArrayByParity5(int[] nums) {
        int less = 0;
        int great = nums.length - 1;
        while (less < great) {
            if (nums[less] % 2 > nums[great] % 2) {
                swap(nums, less, great);
            }

            if (nums[less] % 2 == 0) less++;
            if (nums[great] % 2 == 1) great--;
        }
        return nums;
    }
}
