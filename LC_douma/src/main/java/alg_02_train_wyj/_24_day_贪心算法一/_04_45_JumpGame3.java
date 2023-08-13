package alg_02_train_wyj._24_day_贪心算法一;

/**
 * @Author Wuyj
 * @DateTime 2023-04-12 15:21
 * @Version 1.0
 */
public class _04_45_JumpGame3 {
    public int jump(int[] nums) {
        int n = nums.length;
        if (n == 1) return 0;
        int steps = 0;
        int start = 0, end = 0;
        while (end < n - 1) {
            int maxDis = 0;
            for (int i = start; i <= end; i++) {
                maxDis = Math.max(maxDis, i + nums[i]);
            }
            start = end + 1;
            end = maxDis;
            steps++;
        }
        return steps;
    }

    public static void main(String[] args) {
        int[] nums = {2, 3, 1, 1, 4};
        System.out.println(new _04_45_JumpGame3().jump(nums));
        // 2
    }
}
