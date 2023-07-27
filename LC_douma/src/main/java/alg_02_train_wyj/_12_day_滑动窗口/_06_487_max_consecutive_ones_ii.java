package alg_02_train_wyj._12_day_滑动窗口;

/**
 * @Author Wuyj
 * @DateTime 2023-05-05 20:44
 * @Version 1.0
 */
public class _06_487_max_consecutive_ones_ii {

    public static int findMaxConsecutiveOnes1(int[] nums) {
        int res = 0;
        int left = 0, right = 0;
        int n = nums.length;
        int count = 0;
        while (right < n) {
            if (nums[right] == 0) {
                count++;
                if (count == 2) {
                    res = Math.max(res, right - left);
                }
            }
            if (count == 2) {
                if (nums[left] == 0) count--;
                left++;
            }
            right++;
        }
        return Math.max(res, right - left);
    }

    public static int findMaxConsecutiveOnes2(int[] nums) {
        int res = 0;
        int left = 0, right = 0;
        int n = nums.length;

        int zeroIndex = -1;
        while (right < n) {
            if (nums[right] == 0) {
                if (zeroIndex >= 0) {
                    res = Math.max(res, right - left);
                    left = zeroIndex + 1;
                }
                zeroIndex = right;
            }
            right++;
        }
        return Math.max(res, right - left);
    }

    public static void main(String[] args) {
        int[] array = new int[]{1, 0, 1, 1, 0};
        System.out.println(findMaxConsecutiveOnes1(array)); // 4
        System.out.println(findMaxConsecutiveOnes2(array)); // 4

        int[] array1 = new int[]{1, 0, 1, 0, 1, 0, 1, 0};
        System.out.println(findMaxConsecutiveOnes1(array1)); // 3
        System.out.println(findMaxConsecutiveOnes2(array1)); // 3
    }
}
