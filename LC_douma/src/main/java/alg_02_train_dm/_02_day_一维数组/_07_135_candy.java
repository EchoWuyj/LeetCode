package alg_02_train_dm._02_day_一维数组;

import java.util.Arrays;

/**
 * @Author Wuyj
 * @DateTime 2023-04-29 14:36
 * @Version 1.0
 */
public class _07_135_candy {


    /*
        135. 分发糖果
        n 个孩子站成一排。给你一个整数数组 ratings 表示每个孩子的评分。
        你需要按照以下要求，给这些孩子分发糖果：
            1.每个孩子至少分配到 1 个糖果。
            2.相邻两个孩子评分更高的孩子会获得更多的糖果。
        请你给每个孩子分发糖果，计算并返回需要准备的 最少糖果数目 。

        示例 1：
        输入：ratings = [1,0,2]
        输出：5
        解释：你可以分别给第一个、第二个、第三个孩子分发 2、1、2 颗糖果。

        示例 2：
                         1 2 1
        输入：ratings = [1,2,2]
        输出：4
        解释：你可以分别给第一个、第二个、第三个孩子分发 1、2、1 颗糖果。
             第三个孩子只得到 1 颗糖果，这满足题面中的两个条件。

                        1 2 3
             ratings = [1,2,3]
         输出：6


        提示：
        n == ratings.length
        1 <= n <= 2 * 10^4
        0 <= ratings[i] <= 2 * 10^4

     */

    // KeyPoint 方法一 直接模拟，暴力解法
    // 时间复杂度 O(n^2) 勉强通过 => 在所有 Java 提交中击败了 5.74% 的用户
    public int candy1(int[] ratings) {

        // 直接模拟

        //  ratings     1 3 5 2 3 3
        // 初始 candies 1 1 1 1 1 1
        // 最终 candies 1 2 3 1 2 1 => 有的情况遍历不止一遍

        //  ratings       1 2 87 87 87 2 1
        // 初始   candies 1 1 1  1  1  1 1
        // 第一遍 candies 1 2 3  1  2  2 1 => 87 > 2，但是 candies 却是 2 和 2 => 存在问题
        // 第二遍 candies 1 2 3  1  3  2 1 => 一直遍历到 candies 不发生变化，停止遍历

        int n = ratings.length;
        int[] candies = new int[n];
        Arrays.fill(candies, 1);
        boolean hasChanged = true;
        while (hasChanged) {
            // 一轮 for 循环结束之后，将  hasChanged 设置为 false
            // 若下轮 for 循环，没有将其设置为 true，结束 while 循环
            hasChanged = false;
            // 并不是一次循环就能得到结果，一直遍历到 candies 不发生变化，停止遍历
            //   ratings     14 11 10 7 6 2 1 (降序排列)
            // 一次 for 循环  2  2  2  2 2 2 1
            // 二次 for 循环  3  3  3  3 3 2 1
            // ...
            // n 个数，n 次循环，每次循环 O(n) => O(n^2)
            for (int i = 0; i < n; i++) {
                // 当前和右边相比 => i 和 i+1 比较
                // 最后一元素没有右边元素
                if (i != n - 1
                        && ratings[i] > ratings[i + 1]
                        && candies[i] <= candies[i + 1]) {
                    candies[i] = candies[i + 1] + 1;
                    hasChanged = true;
                }
                // 当前和左边相比 => i 和 i-1 比较
                // 第一个一元素没有右边元素
                if (i != 0
                        && ratings[i] > ratings[i - 1]
                        && candies[i] <= candies[i - 1]) {
                    candies[i] = candies[i - 1] + 1;
                    hasChanged = true;
                }
            }
        }

        int sum = 0;
        for (int candy : candies) sum += candy;
        return sum;
    }

    // KeyPoint 方法二 两个数组 + 两次遍历
    // 时间复杂度 O(n)
    // 空间复杂度 O(n)
    public int candy2(int[] ratings) {

        //  ratings                  1 2 87 87 87 6 3 1
        // 从左往右(只比左边) candies 1 2 3  1  1  1 1 1 => ratings 升序，从左往右遍历，一次搞定
        // 从右往左(只比右边) candies 1 1 1  1  4  3 2 1 => ratings 降序，从右往左遍历，一次搞定
        // 取两个数组的最大值 candies 1 2 3  1  4  3 2 1 => 最终结果

        int n = ratings.length;
        int[] left2right = new int[n];
        Arrays.fill(left2right, 1);

        int[] right2left = new int[n];
        Arrays.fill(right2left, 1);

        for (int i = 0; i < n; i++) {
            if (i != 0 && ratings[i] > ratings[i - 1]) {
                left2right[i] = left2right[i - 1] + 1;
            }
        }

        int sum = 0;
        for (int i = n - 1; i >= 0; i--) {
            if (i != n - 1 && ratings[i] > ratings[i + 1]) {
                right2left[i] = right2left[i + 1] + 1;
            }
            // 在第 right2left 遍历中，顺便将 sum 求解，避免再遍历一遍
            sum += Math.max(left2right[i], right2left[i]);
        }
        return sum;
    }

    // KeyPoint 方法三 一个数组 + 两次遍历
    // 时间复杂度 O(n)
    // 空间复杂度 O(n)
    public int candy(int[] ratings) {
        int n = ratings.length;
        // 第一次遍历结果 left2right 需要暂存，第二次遍历基于 left2right 的值
        // 计算 right，比较最大值，累加到 res 中，故 right2left 可以省略
        int[] left2right = new int[n];
        Arrays.fill(left2right, 1);

        for (int i = 0; i < n; i++) {
            if (i != 0 && ratings[i] > ratings[i - 1]) {
                left2right[i] = left2right[i - 1] + 1;
            }
        }

        int sum = 0;
        // 使用 right 变量代替 right2left 数组，在后面一次 for 循环
        // 每次计算出的 right 直接使用，并不需要将其存到数组中
        int right = 0;
        for (int i = n - 1; i >= 0; i--) {
            if (i != n - 1 && ratings[i] > ratings[i + 1]) {
                // 满足 ratings[i] > ratings[i + 1]，则 right 不断累加
                right++;
            } else {
                right = 1;
            }
            sum += Math.max(left2right[i], right);
        }

        return sum;
    }
}
