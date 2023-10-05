package alg_02_train_wyj._02_day_一维数组;

/**
 * @Author Wuyj
 * @DateTime 2023-04-17 12:55
 * @Version 1.0
 */
public class _02_189_rotate_array1 {

    public void rotate1(int[] nums, int k) {
        int n = nums.length;
        k = k % n;
        reverse(nums, 0, n - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, n - 1);
    }

    private void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int tmp = nums[start];
            nums[start] = nums[end];
            nums[end] = tmp;
            start++;
            end--;
        }
    }

    public void rotate2(int[] nums, int k) {
        int n = nums.length;
        k %= n;

        int[] tmpArr = new int[n];
        for (int i = 0; i < n; i++) {
            int index = (i + k) % n;
            tmpArr[index] = nums[i];
        }

        System.arraycopy(tmpArr, 0, nums, 0, n);
    }
}




