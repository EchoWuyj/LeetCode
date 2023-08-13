package alg_02_train_wyj._24_day_贪心算法一;

/**
 * @Author Wuyj
 * @DateTime 2023-04-12 15:45
 * @Version 1.0
 */
public class _05_55_Jump_Game {
    public boolean canJump(int[] nums) {
        int n = nums.length;
        if (n == 1) return true;
        int maxPos = 0, end = 0;
        for (int i = 0; i < n; i++) {
            maxPos = Math.max(maxPos, i + nums[i]);
            if (i == end) {
                if (maxPos <= i && i != n - 1) return false;
                end = maxPos;
            }
        }
        return true;
    }
}
