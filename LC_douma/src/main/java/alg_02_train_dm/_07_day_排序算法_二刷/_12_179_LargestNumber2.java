package alg_02_train_dm._07_day_排序算法_二刷;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @Author Wuyj
 * @DateTime 2023-07-10 20:34
 * @Version 1.0
 */
public class _12_179_LargestNumber2 {

    // KeyPoint 方法二 Java 内置排序 => 推荐使用，执行速度更快
    public String largestNumber2(int[] nums) {
        if (nums == null || nums.length == 0) return "";

        // KeyPoint 补充说明：比较器 Comparator 泛型限制
        //  详见：12_179_Note_Comparator_Generic

        // 将 int 转成 String 类型，因为比较器泛型限制，基本数据类型，所以需要进行转换
        int n = nums.length;
        String[] strs = new String[n];
        for (int i = 0; i < nums.length; i++) {
            // 将 int 转成 String 类型
            strs[i] = String.valueOf(nums[i]);
        }

        // 使用 Java 内置排序，但是需要提供自定义比较器
        // sort 方法中，传入 strs 和 比较器的数据类型 Comparator<String> 需要保持一致
        // 不能使用 int[] nums，故需要前面的转换
        Arrays.sort(strs, new LargestNumberComparator());

        // KeyPoint 另外一种简单方式，在 sort 中传入比较器
//        Arrays.sort(strs, new Comparator<String>() {
//            @Override
//            public int compare(String x, String y) {
//                String xy = x + y;
//                String yx = y + x;
//                return yx.compareTo(xy);
//            }
//        });

        // Lambda表达式形式
//        Arrays.sort(strs, (x, y) -> {
//            String xy = x + y;
//            String yx = y + x;
//            return yx.compareTo(xy);
//        });

        // 字符串使用 equals 比较方法，不使用 ==，等于比较符号
        // 并且 strs 是字符串数组，使用 "0"，而不是 '0'
        if (strs[0].equals("0")) {
            return "0";
        }

        // 不推荐使用'字符串拼接'处理，使用 StringBuilder 提高性能
        StringBuilder sb = new StringBuilder();
        for (String str : strs) {
            // KeyPoint 写代码避免自动提示带来混淆 => 养成习惯
            // 注意 str 和 sb 别混淆了，一般智能提示只是输入 s，自动提示 sb，
            // 需要多输入几个字符，保证前缀匹配，避免无意识写错代码
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
        }
    }

    // KeyPoint 比较器 compare 方法中 return 说明
    // 核心：由最后 return yx.compareTo(xy) 结果，决定的是 x 和 y 前后顺序
    // 1.若 yx.compareTo(xy) 结果为负数，表示 yx 小于 xy => 则 x 在前，y 在后
    //   => 从数组排序位置上看(数值上看) x < y，正好和 x - y < 0 对应上
    //   注意：本题不是单纯 x 和 y 值的大小决定返回值的正负，而是通过 xy 和 yx 比较
    // 2.若 yx.compareTo(xy) 结果为正数，表示 yx 大于 xy =>  则 y 在前，x 在后
    //   => y < x，正好和 x - y > 0 对应上
    // 3.若结果为 0，表示 yx 和 xy 相等，它们的顺序可以是任意的
}
