package alg_02_train_wyj._24_day_贪心算法一;

/**
 * @Author Wuyj
 * @DateTime 2023-04-12 15:37
 * @Version 1.0
 */
public class _04_45_JumpGame4 {
    public int jump(int[] nums) {
        int n = nums.length;
        if (n == 1) return 0;
        int steps = 0, end = 0;
        int maxPos = 0;
        for (int i = 0; i < n - 1; i++) {
            maxPos = Math.max(maxPos, i + nums[i]);
            if (i == end) {
                steps++;
                end = maxPos;
            }
        }
        return steps;
    }

    public static void main(String[] args) {
        int[] nums = {2, 3, 1, 1, 4};
        System.out.println(new _04_45_JumpGame4().jump(nums));
    }
}
