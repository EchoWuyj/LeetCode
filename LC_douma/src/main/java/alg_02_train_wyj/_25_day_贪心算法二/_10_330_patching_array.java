package alg_02_train_wyj._25_day_贪心算法二;

/**
 * @Author Wuyj
 * @DateTime 2023-04-14 20:24
 * @Version 1.0
 */
public class _10_330_patching_array {
    public int minPatches(int[] nums, int n) {
        int res = 0;
        long x = 1;
        int index = 0;
        while (x <= n) {
            if (index < nums.length && nums[index] <= x) {
                x += nums[index];
                index++;
            } else {
                res++;
                x *= 2;
            }
        }
        return res;
    }
}
