package alg_02_train_dm._07_day_排序算法;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @Author Wuyj
 * @DateTime 2023-05-15 19:16
 * @Version 1.0
 */
public class _12_179_LargestNumber {

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
        0 <= nums[i] <= 109

     */

    // KeyPoint 方法一 自定义排序(手动实现排序)
    public String largestNumber1(int[] nums) {
        if (nums == null || nums.length == 0) return "";

        sort(nums, 0, nums.length - 1);

        // KeyPoint 两个数组进行比较，需要遍历一遍数组，影响效率
//        if (Arrays.equals(nums, new int[nums.length])) {
//            return "0";
//        }

        // 拼接
        StringBuilder sb = new StringBuilder();
        for (int str : nums) {
            sb.append(str);
        }

        // 测试用例要求
        // 输入 [0,0]，实际输出 "00"，要求输出 "0"
        // 只需要判断第一个元素即可，因为数组是降序排列的，第一个位置为 0，则后面必然也是 0
        if (sb.charAt(0) == '0') return "0";
        return sb.toString();
    }

    // 使用三路快排，实现排序
    private void sort(int[] data, int low, int high) {
        if (low >= high) return;
        // 分区
        int pivot = data[high];

        int less = low;
        int great = high;

        int i = low;
        while (i <= great) {

            // x 和 y 进行拼接
            // => 通过比较拼接后 xy 和 yx 大小，从而决定 data[i] 排序
            String xy = data[i] + "" + pivot;
            String yx = pivot + "" + data[i];

            // KeyPoint 字符串 默认实现 Comparable 接口，已经重写 compareTo 方法，故可以直接使用
            // 降序排列
            if (xy.compareTo(yx) > 0) {
                // data[i] -> x
                // pivot -> pivot
                // xy > yx => y 在 x 后面，即 x 往前放，调整为 nums[i]
                // 不要想着降序排列，就调整 > 和 <，要真的理解
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
    private void swap(int[] strs, int i, int j) {
        int tmp = strs[i];
        strs[i] = strs[j];
        strs[j] = tmp;
    }

    // KeyPoint 方法一 Java 内置排序 (推荐使用，执行速度更快)
    public String largestNumber2(int[] nums) {
        if (nums == null || nums.length == 0) return "";

        // KeyPoint 错误写法，nums 是 int 类型，比较器无法传入 int，而使用 Integer 也不匹配
//        Arrays.sort(nums, new Comparator<Integer>() {
//            @Override
//            public int compare(Integer o1, Integer o2) {
//                return 0;
//            }
//        })

        // 将 int 转成 String 类型，因为比较器泛型限制，基本数据类型，所以需要进行转换
        String[] strs = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            // 将 int 转成 String 类型
            strs[i] = String.valueOf(nums[i]);
        }

        // 使用 Java 内置排序，但是需要提供自定义比较器
        // sort 方法中，传入 strs 和 比较器的数据类型(String)需要保持一致，不能使用 nums
        Arrays.sort(strs, new LargestNumberComparator());

        // 字符串使用 equals 比较方法，不使用 ==
        // 并且 strs 是字符串数组，使用 "0"，而不是 '0'
        if (strs[0].equals("0")) {
            return "0";
        }

        // 不推荐使用'字符串拼接'处理，使用 StringBuilder 提高性能
        StringBuilder sb = new StringBuilder();
        for (String str : strs) {
            // KeyPoint 注意：str 和 sb 别混淆了，一般智能提示只是输入 s，自动提示 sb，
            //  需要多输入几个字符，保证前缀匹配，避免无意识写错代码
            sb.append(str);
        }

        return sb.toString();
    }

    // 自定义比较器
    private class LargestNumberComparator implements Comparator<String> {
        @Override
        public int compare(String x, String y) {
            // 降序排列
            String xy = x + y;
            String yx = y + x;
            return yx.compareTo(xy);

            // 注意：最后 return 结果，决定的是 x 和 y，前后顺序
            // xy - yx > 0，等价于：表示 x - y > 0，返回正数，Arrays.sort 根据比较器返回结果(正数)
            // 从而决定 x 和 y 相对次序，即 x > y，排序结果 y,x，升序排列

            // 结合本题，若 yx - xy > 0，则 yx > xy => y 在前，x 在后
            // x - y > 0，x > y，对应 y 在前，x 在后，与上面一致
        }
    }
}
