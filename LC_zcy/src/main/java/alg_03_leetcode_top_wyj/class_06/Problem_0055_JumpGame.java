package alg_03_leetcode_top_wyj.class_06;

/**
 * @Author Wuyj
 * @DateTime 2023-02-20 9:19
 * @Version 1.0
 */
public class Problem_0055_JumpGame {
    public boolean canJump(int[] nums) {
        if (nums.length < 2) {
            return true;
        }
        int max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (max >= nums.length - 1) {
                return true;
            }
            if (i > max) {
                return false;
            } else {
                max = Math.max(max, i + nums[i]);
            }
        }
        return true;
    }
}
