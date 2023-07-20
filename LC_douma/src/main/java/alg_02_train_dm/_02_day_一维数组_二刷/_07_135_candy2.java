package alg_02_train_dm._02_day_一维数组_二刷;

import java.util.Arrays;

/**
 * @Author Wuyj
 * @DateTime 2023-07-20 14:40
 * @Version 1.0
 */
public class _07_135_candy2 {

    // KeyPoint 方法二 两个数组 + 两次遍历
    // 时间复杂度 O(n)
    // 空间复杂度 O(n)
    public int candy2(int[] ratings) {

        //  ratings                        1 2 87 87 87 6 3 1
        // 从左往右(只比左边元素)   candies 1 2 3  1  1  1 1 1 => ratings 升序，从左往右遍历，一次搞定
        // 从右往左(只比右边元素)   candies 1 1 1  1  4  3 2 1 => ratings 降序，从右往左遍历，一次搞定
        // 最终：取两个数组的最大值 candies 1 2 3  1  4  3 2 1 => 最终结果

        int n = ratings.length;
        int[] left2right = new int[n];
        Arrays.fill(left2right, 1);

        int[] right2left = new int[n];
        Arrays.fill(right2left, 1);

        // 从左往右遍历，只比左边元素
        for (int i = 0; i < n; i++) {
            if (i != 0 && ratings[i] > ratings[i - 1]) {
                left2right[i] = left2right[i - 1] + 1;
            }
        }

        int res = 0;
        // 从右往左遍历，只比右边元素
        for (int i = n - 1; i >= 0; i--) {
            if (i != n - 1 && ratings[i] > ratings[i + 1]) {
                right2left[i] = right2left[i + 1] + 1;
            }
            // 在第 right2left 遍历中，顺便将 res 求解，避免再遍历一遍
            res += Math.max(left2right[i], right2left[i]);
        }
        return res;
    }
}
