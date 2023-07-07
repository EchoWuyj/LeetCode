package alg_02_train_wyj._09_day_哈希查找;

/**
 * @Author Wuyj
 * @DateTime 2023-04-16 12:13
 * @Version 1.0
 */
public class _10_560_subarray_sum_equals_k1 {
    public int subarraySum1(int[] nums, int k) {
        int n = nums.length;
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                int sum = 0;
                for (int index = j; index <= i; index++) {
                    sum += nums[index];
                }
                if (sum == k) count++;
            }
        }
        return count;
    }

    public static int subarraySum2(int[] nums, int k) {
        int n = nums.length;
        int count = 0;
        for (int i = 0; i < n; i++) {
            int sum = 0;
            for (int j = i; j >= 0; j--) {
                sum += nums[j];
                if (sum == k) count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int[] arr = {-1, -1, 1};
        System.out.println(subarraySum2(arr, 0));
    }
}
