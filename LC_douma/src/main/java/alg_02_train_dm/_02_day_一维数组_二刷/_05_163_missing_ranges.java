package alg_02_train_dm._02_day_一维数组_二刷;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-04-17 19:27
 * @Version 1.0
 */
public class _05_163_missing_ranges {

    /*
         163 缺失的区间
         给定一个 排好序 的整数数组 nums，其中元素的范围在闭区间 [lower, upper] 内
         请你在该数组中找出符合下列条件的缺失的区间，并返回其列表。

         缺失区间的 起始位置 必须在数组中给定区间的范围内。
         缺失区间的 终止位置 必须在数组中给定区间的范围内。
         缺失区间内的数字必须属于数组中的元素，而不是介于其范围之间的其他数字。

         nums = [0, 1, 3, 50, 75]
         给定区间 lower = 0 和 upper = 99
         缺失的区间应该是 ["2", "4->49", "51->74", "76->99"]

         nums = [0, 1, 3, 50, 75]
         给定区间 lower = -1 和 upper = 76
         缺失的区间应该是 ["-1","2", "4->49", "51->74", "76"]

         nums = [0, 1, 3, 50, 75]
         给定区间 lower = -5 和 upper = 99
         缺失的区间应该是 ["-5->-1", "2", "4->49", "51->74", "76->99"]

     */

    // KeyPoint 汇总区间 -> 变形题
    public static List<String> findMissingRanges(int[] nums, int lower, int upper) {

        // lower = 0，upper = 99
        // nums：0 1 3 50 75

        // 将 lower 和 upper 作为数组 nums 的一部分
        // 0 0 1 3 50 75 99

        List<String> res = new ArrayList<>();
        int n = nums.length;
        // pre 表示数组中，前一个元素值
        // lower-1 => 为了统一'数组元素'和'给定区间 lower'的处理逻辑
        int pre = lower - 1;

        for (int i = 0; i < n; i++) {
            // KeyPoint 讨论：nums[i] 与 pre 差的 3 种情况
            // 情况一：nums[i] 与 pre 相差 2，缺失区间为：pre+1
            // 比如：1 和 3 差为 2，缺失区间为：pre+1 = 2

            // KeyPoint 防止数据溢出
            // 1.不是使用 nums[i]-pre == 2，而是使用 nums[i] == pre + 2
            // 2.为避免数据溢出，我们可以在进行数组元素相加时，使用更大的数据类型
            //   比如：使用 long 类型代替 int 类型

            if (nums[i] == pre + 2) {
                res.add(String.valueOf(pre + 1));
            } else if (nums[i] > pre + 2) {
                // 情况二：nums[i] 与 pre 差 > 2，缺失区间为：[pre+1，nums[i]-1]
                // 比如：3 和 50 差 > 2，缺失区间 [3->49]
                // 注意：字符串拼接，注意运算优先级，需要加括号 ()
                res.add((pre + 1) + "->" + (nums[i] - 1));
            }

            // 情况三：nums[i] 与 pre 差 < 2，没有缺失区间
            // 更新 pre 到 num[i+1] 的前一个元素值 nums[i] 位置
            // 注意：该代码语句，不在 if 判断里面，即不管 if 判断是否成立，都是需要执行的
            pre = nums[i];

            // KeyPoint 区别：if ... else ... 多分支结构
            // if else 多分支结构，每次只是执行其中一种情况
            // 所以并不是每次都执行 pre = nums[i]，导致 pre 没有及时更新

//            if (nums[i] == pre + 2) {
//                res.add(String.valueOf(pre + 1));
//            } else if (nums[i] > pre + 2) {
//                res.add((pre + 1) + "->" + (nums[i] - 1));
//            } else {
//                pre = nums[i];
//            }

        }

        // upper 处理逻辑
        // for 循环结束之后，pre 指向 nums[n-1] 位置，pre 需要和 upper 进行比较
        if (upper == pre + 1) res.add(String.valueOf(pre + 1));
        else if (upper > pre + 1) {
            res.add((pre + 1) + "->" + upper);
        }
        return res;
    }

    public static void main(String[] args) {
        int[] arr = {0, 1, 3, 50, 75};
        System.out.println(findMissingRanges(arr, 0, 99));
        // [2, 4->49, 51->74, 76->99]
        System.out.println(findMissingRanges(arr, -1, 76));
        // [-1, 2, 4->49, 51->74, 76]
        System.out.println(findMissingRanges(arr, -5, 99));
        // [-5->-1, 2, 4->49, 51->74, 76->99]
    }
}
