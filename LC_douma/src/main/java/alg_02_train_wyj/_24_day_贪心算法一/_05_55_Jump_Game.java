package alg_02_train_wyj._24_day_贪心算法一;

/**
 * @Author Wuyj
 * @DateTime 2023-04-12 15:45
 * @Version 1.0
 */
public class _05_55_Jump_Game {
    public boolean canJump(int[] nums) {
        int n = nums.length;
        int maxDis = 0;
        for (int i = 0; i < n; i++) {
            if (maxDis < i) return false;
            maxDis = Math.max(i + nums[i], maxDis);
        }
        return true;
    }
}
