package alg_03_high_frequency._01_codetop_2024_01;

/**
 * @Author Wuyj
 * @DateTime 2024-01-17 18:25
 * @Version 1.0
 */
public class _99_283_moveZeroes {
    public void moveZeroes(int[] nums) {
        int n = nums.length;
        int slow = 0;
        int fast = 0;
        while (fast < n) {
            if (nums[fast] != 0) {
                if (fast != slow) {
                    nums[slow] = nums[fast];
                }
                fast++;
                slow++;
            } else {
                fast++;
            }
        }

        while (slow < n) {
            // slow 之前位置，不包括 slow，已经正确
            // slow 及其之后需要修正
            nums[slow++] = 0;
        }
    }
}
