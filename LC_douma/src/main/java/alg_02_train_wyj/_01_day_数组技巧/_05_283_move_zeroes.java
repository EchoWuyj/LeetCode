package alg_02_train_wyj._01_day_数组技巧;

/**
 * @Author Wuyj
 * @DateTime 2023-04-16 16:37
 * @Version 1.0
 */
public class _05_283_move_zeroes {
    public void moveZeroes(int[] nums) {
        int n = nums.length;
        int fast = 0;
        int slow = 0;
        while (fast < n) {
            if (nums[fast] != 0) {
                if (fast != slow) {
                    int tmp = nums[slow];
                    nums[slow] = nums[fast];
                    nums[fast] = tmp;
                }
                slow++;
            }
            fast++;
        }
    }

    public void moveZeroes1(int[] nums) {
        int n = nums.length;
        int fast = 0;
        int slow = 0;
        while (fast < n) {
            if (nums[fast] != 0) {
                nums[slow] = nums[fast];
                slow++;
            }
            fast++;
        }

        while (slow < n) nums[slow++] = 0;
    }
}
