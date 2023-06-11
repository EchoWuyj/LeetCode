package alg_02_train_dm._02_day_一维数组;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-04-17 19:27
 * @Version 1.0
 */
public class _05_163_missing_ranges {

    /*
        163. 汇总区间-变形题 => 缺失的区间
        给定一个排好序的整数数组 nums，其中元素的范围在闭区间 [lower, upper] 内，
        请你在该数组中找出符合下列条件的缺失的区间，并返回其列表：
        缺失区间的起始位置必须在数组中给定区间的范围内。
        缺失区间的终止位置必须在数组中给定区间的范围内。
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

    public static List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> res = new ArrayList<>();
        // 为避免数据溢出，我们可以在进行数组元素相加时，使用更大的数据类型，例如使用 long 类型代替 int 类型
        // pre 表示数组中前一个元素值
        // 为了统一'数组元素'和'给定区间 lower'的处理逻辑
        long pre = lower - 1;
        for (int i = 0; i < nums.length; i++) {
            // KeyPoint 当前值 nums[i] 和前一个值 pre 相差的 3 种情况
            // 情况一：1 3 相差 = 2，缺失区间为 2，即为 pre + 1
            // 防止数据溢出，不是使用 nums[i] - pre == 2，而是使用 nums[i] == pre + 2
            if (nums[i] == pre + 2) res.add(String.valueOf(pre + 1));
                // 情况二：3 50 相差 > 2 => 缺失区间 [3->49]
            else if (nums[i] > pre + 2) {
                // 字符串拼接，注意运算优先级，需要加 ()
                res.add((pre + 1) + "->" + (nums[i] - 1));
            }

            // 情况三：1 相差 < 2，没有缺失区间
            // pre 更新 num[i+1] 的前一个元素值 nums[i] 位置
            // KeyPoint 注意：该代码语句，不在 if 判断里面，不管 if 判断是否成立，都是需要执行的
            pre = nums[i];

            // KeyPoint 区别于下面这种形式
//            if (nums[i] == pre + 2) res.add(String.valueOf(pre + 1));
//            else if (nums[i] > pre + 2) {
//                res.add((pre + 1) + "->" + (nums[i] - 1));
//            } else {
            // KeyPoint if else，多分支结构，只是执行其中一种情况，所以并不是每次都执行，导致 pre 没有及时更新
//                pre = nums[i];
//            }

        }

        // upper 处理逻辑
        // for 循环结束之后，pre 指向 nums[i] 位置，pre 需要和 upper 进行比较
        if (upper == pre + 1) res.add(String.valueOf(pre + 1));
        else if (upper > pre + 1) {
            res.add((pre + 1) + "->" + upper);
        }

        return res;
    }

    public static void main(String[] args) {
        int[] arr = {0, 1, 3, 50, 75};
        System.out.println(findMissingRanges(arr, 0, 99)); // [2, 4->49, 51->74, 76->99]
        System.out.println(findMissingRanges(arr, -1, 76)); // [-1, 2, 4->49, 51->74, 76]
        System.out.println(findMissingRanges(arr, -5, 99));  // [-5->-1, 2, 4->49, 51->74, 76->99]
    }
}
