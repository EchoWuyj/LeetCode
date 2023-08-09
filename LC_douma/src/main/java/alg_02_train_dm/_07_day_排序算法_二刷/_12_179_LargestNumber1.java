package alg_02_train_dm._07_day_排序算法_二刷;

/**
 * @Author Wuyj
 * @DateTime 2023-05-15 19:16
 * @Version 1.0
 */
public class _12_179_LargestNumber1 {

     /*
        179. 最大数
        给定一组非负整数 nums，重新排列每个数的顺序(每个数不可拆分)，使之组成一个最大的整数。
        注意：输出结果可能非常大，所以你需要返回一个字符串而不是整数。

        示例 1：
        输入：nums = [10,2]
        输出："210"

        示例 2：
        输入：nums = [3,30,34,5,9]
        输出："9534330"

        提示：
        1 <= nums.length <= 100
        0 <= nums[i] <= 10^9

        KeyPoint 思路分析

        1.降序排列 ×
        2.按照每个数字的第一位降序，但是第一位相同，后续怎么处理，比较复杂 ×
        3.降序排列
          x，y
          x > y => x 在前，y 在 后
          x < y => y 在前，x 在 后

        nums 3 30 34

        3 30
        330 > 303
        30 应该在 3 的后面 => 3 > 30

        34 30
        3034 < 3430
        30 应该在 34 的后面 => 34 > 30

        34 3
        334 < 343
        3 应该在 34 的后面 => 34 > 3

        结论：34 > 3 > 30 => 34 3 30

        => 将两个数抽象成 x y
           xy > yx  => x 在前，y 在 后
           xy < yx  => y 在前，x 在 后

     */

    // KeyPoint 方法一 自定义排序 => 手动实现排序
    public String largestNumber1(int[] nums) {

        if (nums == null || nums.length == 0) return "";
        int n = nums.length;
        sort(nums, 0, n - 1);

        // KeyPoint 判断 nums 元素是否全为 0
        // 两个数组进行比较，需要遍历一遍数组，影响效率
//        if (Arrays.equals(nums, new int[nums.length])) {
//            return "0";
//        }

        // 拼接
        StringBuilder sb = new StringBuilder();
        for (int num : nums) {
            sb.append(num);
        }

        // 测试用例要求：输入 [0,0]，实际输出 "00"，要求输出 "0"
        // 只需要判断第一个元素即可，因为数组是降序排列的，第一个位置为 0，则后面必然也是 0
        if (sb.charAt(0) == '0') return "0";
        return sb.toString();
    }

    // 使用'三路快排'实现排序
    private void sort(int[] data, int low, int high) {
        if (low >= high) return;
        // 分区
        int pivot = data[high];

        int less = low;
        int great = high;

        int i = low;
        while (i <= great) {

            // 本质：自定义比较逻辑
            // data[i] 和 pivot 比较，比较逻辑不是比数值，而是比较两者拼接后结果
            // xy > yx  => x 在前，y 在 后
            // xy < yx  => y 在前，x 在 后

            // data[i] 和 pivot 进行拼接
            // => 类似：x 和 y 进行拼接
            // => 通过比较拼接后 xy 和 yx 大小，从而决定 data[i] 排序
            // data[i] -> x
            // pivot -> y
            // KeyPoint 定义 xy 和 yx 是在 while 循环里面，不是在 while 外面
            //          否则 data[i]，不会因为 i 的变化，而发生变化，即无线递归调用，栈溢出
            String xy = data[i] + "" + pivot;
            String yx = pivot + "" + data[i];

            // KeyPoint 注意事项
            // 字符串 String 默认实现 Comparable 接口，已经重写 compareTo 方法，故可以直接使用
            // 注意：StringBuilder 没有 compareTo 方法，故只能使用字符串拼接的方式
            // 降序排列
            if (xy.compareTo(yx) > 0) {
                // xy.compareTo(yx) > 0 => xy - yx > 0 => xy > yx
                // x 在前，y 在 后，即 x 往前放，故 i 和 less 交换
                // KeyPoint 不要想着降序排列，就调整 > 和 <，要从本质上理解，为什么这样做
                swap(data, i, less);
                less++;
                i++;
            } else if (xy.compareTo(yx) < 0) {
                swap(data, i, great);
                great--;
            } else {
                i++;
            }
        }
        sort(data, low, less - 1);
        sort(data, great + 1, high);
    }

    // 交换
    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
