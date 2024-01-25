package alg_02_train_wyj._29_day_动态规划四;

/**
 * @Author Wuyj
 * @DateTime 2023-06-15 18:53
 * @Version 1.0
 */
public class _09_376_wiggle_subsequence {
    public int wiggleMaxLength1(int[] nums) {
        int n = nums.length;
        if (n < 2) return n;
        int[] up = new int[n];
        int[] down = new int[n];

        up[0] = down[0] = 1;

        for (int i = 1; i < n; i++) {
            if (nums[i] > nums[i - 1]) {
                up[i] = down[i - 1] + 1;
                down[i] = down[i - 1];
            } else if (nums[i] < nums[i - 1]) {
                down[i] = up[i - 1] + 1;
                up[i] = up[i - 1];
            } else {
                up[i] = up[i - 1];
                down[i] = down[i - 1];
            }
        }
        return Math.max(up[n - 1], down[n - 1]);
    }

    public int wiggleMaxLength2(int[] nums) {
        int n = nums.length;
        if (n <= 1) return 1;

        int curUp = 1;
        int curDown = 1;

        for (int i = 1; i < n; i++) {
            int preUp = curUp, preDown = curDown;
            if (nums[i] > nums[i - 1]) {
                curUp = preDown + 1;
            } else if (nums[i] < nums[i - 1]) {
                curDown = preUp + 1;
            }
        }
        return Math.max(curUp, curDown);
    }
}
