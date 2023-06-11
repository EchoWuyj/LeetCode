package alg_02_train_dm._07_day_排序算法;

/**
 * @Author Wuyj
 * @DateTime 2023-05-13 19:52
 * @Version 1.0
 */
public class _03_628_maximum_product_of_three_numbers {

    /*
        628. 三个数的最大乘积
        给你一个整型数组 nums ，在数组中找出由三个数组成的最大乘积，并输出这个乘积。

        提示：
        3 <= nums.length <= 10^4
        -1000 <= nums[i] <= 1000

        分析：
        数组元素中，存在负数，所以不是简单排序，取最大三个值乘积就行了

        分情况讨论：

        1.全正数 1 2 3 4 5 => 最大三个值乘积  3 4 5
        2.一个负数 -5 2 3 4 5 => 最大三个值乘积 3 4 5
        3.两个负数 -5 -4 3 4 5 => 最大三个值乘积 Math.max(3 4 5，-5 -4 5)
          KeyPoint 举例时，最好将前后比较部分取相等，提醒自己，真实的情况，前后比较的大小，不确定
        4.三个负数 -5 -4 -3 4 5 6 => 最大三个值乘积 Math.max(4 5 6，-5 -4 6)

     */
    public int maximumProduct(int[] nums) {
        // 最小值、第二小值
        int min1 = Integer.MAX_VALUE, min2 = Integer.MAX_VALUE;
        // 第一大、第二大、第三大值
        int max1 = Integer.MIN_VALUE, max2 = Integer.MIN_VALUE;
        int max3 = Integer.MIN_VALUE;

        for (int num : nums) {
            // 对每个 num 依次判断 min 和 max
            // 不存在 min 和 max 二选一的分支关系
            if (num < min1) {
                min2 = min1;
                min1 = num;
                // 需要对 num < min2 限制，故需要使用 else if，而不是单纯的 else
            } else if (num < min2) {
                min2 = num;
            }

            if (num > max1) {
                // KeyPoint 从最小值 max3 开始覆盖，不能先将 max2 覆盖，否则 max3 = max2，max3 的值不正确
                max3 = max2;
                max2 = max1;
                max1 = num;
            } else if (num > max2) {
                max3 = max2;
                max2 = num;
            } else if (num > max3) {
                max3 = num;
            }
        }
        // 使用 Math.max 代替了 if else 分支判断
        return Math.max(max1 * max2 * max3, max1 * min1 * min2);
    }
}
