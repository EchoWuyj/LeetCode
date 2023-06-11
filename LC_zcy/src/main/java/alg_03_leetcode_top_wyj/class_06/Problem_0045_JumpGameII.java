package alg_03_leetcode_top_wyj.class_06;

/**
 * @Author Wuyj
 * @DateTime 2023-02-20 11:12
 * @Version 1.0
 */
public class Problem_0045_JumpGameII {
    public int jump(int[] nums) {
        if (nums == null || nums.length == 0 || nums.length == 1) {
            return 0;
        }

        int step = 0;
        int cur = 0;
        int next = nums[0];

        for (int i = 1; i < nums.length; i++) {
            if (next >= nums.length - 1) {
                return step + 1;
            }

            if (i > cur) {
                step++;
                cur = next;
            }

            next = Math.max(next, i + nums[i]);
        }
        return step;
    }
}
