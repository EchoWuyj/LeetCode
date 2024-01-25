package alg_03_high_frequency._01_codetop_2024_01;

/**
 * @Author Wuyj
 * @DateTime 2024-01-14 16:00
 * @Version 1.0
 */
public class _52_41_firstMissingPositive {
    public int firstMissingPositive(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            // 求正数，则将 <= 0 都设置成 n+1
            if (nums[i] <= 0) nums[i] = n + 1;
        }

        for (int i = 0; i < n; i++) {
            // 绝对值，避免为负值
            int num = Math.abs(nums[i]);
            // 对 num 限制
            if (num <= n) {
                // 数字和索引差 1 的关系
                // 注意：使用 num - 1，而不是使用 n-1
                nums[num - 1] = -Math.abs(nums[num - 1]);
            }
        }

        for (int i = 0; i < n; i++) {
            // 只要没有设置为负值，则是该位置索引，对应值为：索引+1，即为i+1，为对应数值 num
            if (nums[i] > 0) return i + 1;
        }

        // 全都没有负值，则在数组最后的后一位，返回 n+1
        return n + 1;
    }
}
