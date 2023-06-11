package alg_02_train_wyj._02_day_一维数组;

import java.util.Arrays;

/**
 * @Author Wuyj
 * @DateTime 2023-04-29 15:17
 * @Version 1.0
 */
public class _07_135_candy {
    public int candy1(int[] ratings) {
        int n = ratings.length;
        int[] candies = new int[n];
        Arrays.fill(candies, 1);
        boolean hasChanged = true;
        while (hasChanged) {
            hasChanged = false;
            for (int i = 0; i < n; i++) {
                if (i != n - 1
                        && ratings[i] > ratings[i + 1]
                        && candies[i] <= candies[i + 1]) {
                    candies[i] = candies[i + 1] + 1;
                    hasChanged = true;
                }
                if (i != 0
                        && ratings[i] > ratings[i - 1]
                        && candies[i] <= candies[i - 1]) {
                    candies[i] = candies[i - 1] + 1;
                    hasChanged = true;
                }
            }
        }

        int res = 0;
        for (int candy : candies) res += candy;
        return res;
    }

    public int candy2(int[] ratings) {
        int n = ratings.length;
        int[] left2right = new int[n];
        int[] right2left = new int[n];
        Arrays.fill(left2right, 1);
        Arrays.fill(right2left, 1);

        for (int i = 0; i < n; i++) {
            if (i != 0 && ratings[i] > ratings[i - 1]
                    && left2right[i] <= left2right[i - 1]) {
                left2right[i] = left2right[i - 1] + 1;
            }
        }

        int sum = 0;
        for (int i = n - 1; i >= 0; i--) {
            if (i != n - 1 && ratings[i] > ratings[i + 1]
                    && right2left[i] <= right2left[i + 1]) {
                right2left[i] = right2left[i + 1] + 1;
            }
            sum += Math.max(left2right[i], right2left[i]);
        }
        return sum;
    }

    public int candy3(int[] ratings) {
        int n = ratings.length;
        int[] left2right = new int[n];
        Arrays.fill(left2right, 1);

        for (int i = 0; i < n; i++) {
            if (i != 0 && ratings[i] > ratings[i - 1]
                    && left2right[i] <= left2right[i - 1]) {
                left2right[i] = left2right[i - 1] + 1;
            }
        }

        int res = 0;
        int right = 0;
        for (int i = n - 1; i >= 0; i--) {
            if (i != n - 1 && ratings[i] > ratings[i + 1]) {
                right++;
            } else {
                right = 1;
            }

            res += Math.max(left2right[i], right);
        }
        return res;
    }
}
