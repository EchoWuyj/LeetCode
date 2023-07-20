package alg_02_train_dm._02_day_一维数组_二刷;

import java.util.Arrays;

/**
 * @Author Wuyj
 * @DateTime 2023-07-20 14:40
 * @Version 1.0
 */
public class _07_135_candy3 {
    // KeyPoint 方法三 一个数组 + 两次遍历
    // 时间复杂度 O(n)
    // 空间复杂度 O(n)
    public int candy(int[] ratings) {
        int n = ratings.length;
        // 第一次遍历结果 left2right 需要暂存
        // 第二次遍历基于 left2right 的值，计算 right，比较最大值，累加到 res 中，故 right2left 可以省略
        int[] left2right = new int[n];
        Arrays.fill(left2right, 1);

        for (int i = 0; i < n; i++) {
            if (i != 0 && ratings[i] > ratings[i - 1]) {
                left2right[i] = left2right[i - 1] + 1;
            }
        }

        int res = 0;
        // 使用 right 变量代替 right2left 数组
        // 在后面一次 for 循环，每次计算出的 right 直接使用，并不需要将其存到数组中
        int right = 0;
        for (int i = n - 1; i >= 0; i--) {
            if (i != n - 1 && ratings[i] > ratings[i + 1]) {
                // 满足 ratings[i] > ratings[i + 1]，则 right 不断累加
                right++;
            } else {
                // 不符合，right 重新为 1
                right = 1;
            }
            res += Math.max(left2right[i], right);
        }

        return res;
    }
}
