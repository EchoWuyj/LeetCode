package alg_02_train_dm._07_day_排序算法_二刷;

import java.util.Arrays;

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

        KeyPoint 分析
        数组元素中，存在负数，所以不是简单排序，取最大三个值乘积就行了

        分情况讨论：
        1.全正数 1 2 3 4 5 => 最大三个值乘积  3 4 5
        2.一个负数 -5 2 3 4 5 => 最大三个值乘积 3 4 5
        3.两个负数 -5 -4 3 4 5 => 最大三个值乘积 Math.max(3 4 5，-5 -4 5)
        4.三个负数 -5 -4 -3 4 5 6 => 最大三个值乘积 Math.max(4 5 6，-5 -4 6)

        规律：Math.max {最大 3 个数乘积，两个最小数 * 最大数}

        注意：自己举例分析时，不要被眼前的特殊例子所迷惑，只是特定
             多考虑相反，或者其他情况，保证后续代码更加健壮
     */

    // KeyPoint 方法一 排序
    public static int maximumProduct1(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        // 通过 Math.max 将所有情况放在一起进行比较
        // 索引从 0 开始，故前 3 个数，分别是 num[0],num[1],num[2]，而不是 num[1],num[2],num[3]
        return Math.max(nums[n - 1] * nums[n - 2] * nums[n - 3]
                , nums[0] * nums[1] * nums[n - 1]);
    }

    // KeyPoint 方法二 线性扫描 => 类似于找第二大元素
    // 不使用排序，直接找出数组中，3 个最大值，2 个最小值
    // 再去使用 Math.max 进行比较，再进行返回
    public int maximumProduct2(int[] nums) {

        // 最小值、第二小值 => 初始化 MAX_VALUE
        int min1 = Integer.MAX_VALUE, min2 = Integer.MAX_VALUE;

        // 第一大、第二大、第三大值 => 初始化 MIN_VALUE
        int max1 = Integer.MIN_VALUE, max2 = Integer.MIN_VALUE;
        int max3 = Integer.MIN_VALUE;

        for (int num : nums) {
            // KeyPoint 注意事项
            // 对每个 num 依次判断 min 和 max
            // 不存在 min 和 max 二选一的分支关系

            // 1.最小值判断
            if (num < min1) {
                min2 = min1;
                min1 = num;
            } else if (num < min2) {
                // min2 > num >= min1
                // 注意：需要对 num < min2 限制，故需要使用 else if，而不是单纯的 else
                min2 = num;
            }

            // 2.最大值判断
            if (num > max1) {
                // 记忆：高位向低位覆盖
                // 从最小值 max3 开始覆盖，不能先将 max2 覆盖，否则 max3 = max2，max3 的值不正确
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

    public static void main(String[] args) {
        int[] arr = {1, 2, 3};
        maximumProduct1(arr);
    }
}
