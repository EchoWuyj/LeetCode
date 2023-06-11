package algorithm._14_bit_operator.wyj;

import java.util.Arrays;
import java.util.HashSet;

/**
 * @Author Wuyj
 * @DateTime 2022-08-29 22:11
 * @Version 1.0
 */
public class LeetCode_268_MissingNumber {
    public static void main(String[] args) {
        // 给定一个包含 [0, n] 中 n 个数的数组 nums
        // 找出 [0, n] 这个范围内没有出现在数组中的那个数。

        // KeyPoint 明确数组长度和数字个数
        // 数组长度为n,其中元素取值范围[0,n],nums中的所有数字都独一无二
        // 但是数字从0-n,一共是n+1个数
    }

    // 排序
    public int missingNumber01(int[] nums) {
        if (nums == null) return -1;

        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i) return i;
        }
        return nums.length;

        // KeyPoint 复杂度分析
        //  核心代码： 主要看 Arrays.sort(nums)
        //  时间复杂度：O(nlogn) 排序的时间复杂度是O(nlogn)
        //  空间复杂度：O(logn) 其中n是数组nums的长度,空间复杂度主要取决于排序的递归调用栈空间
    }

    // 使用Hashset
    public int missingNumber02(int[] nums) {
        if (nums == null) return -1;

        HashSet<Integer> result = new HashSet<>();

        // 第一遍历将数据存到Hashset中
        for (int i = 0; i < nums.length; i++) {
            result.add(nums[i]);
        }

        // 两种写法
        // 方式一
        for (int i = 0; i <= nums.length; i++) {
            if (!result.contains(i)) return i;
        }

        return -1;

        // 方式二
//        for (int i = 0; i < nums.length; i++) {
//            if (!result.contains(i)) return i;
//        }
//
//        return nums.length;

        // KeyPoint 复杂度分析
        //  核心代码： 两个for循环
        //  时间复杂度：O(n) 两个for循环遍历,两次遍历时间复杂度分别是O(n)
        //  空间复杂度：O(n) 其中n是数组nums的长度,哈希集合中需要存储n个整数

    }

    // 位运算
    public int missingNumber03(int[] nums) {
        // 直接使用异或运算^进行抵消,剩下的数字就是缺失的了

        // 将数字n赋值给res
        int res = nums.length;

        // for循环只是遍历到索引 nums.length-1
        for (int i = 0; i < nums.length; i++) {
            // 两个相同的数字异或为0
            // 任何一个数字异或0,还是本身
            //  0 ^ 4 = 4
            //  4 ^ 4 = 0

            // 数组中元素
            res ^= nums[i];
            // 数字中元素
            res ^= i;
            // 通过数组中元素^数字中元素的结果是否为0,从而判断那个数字为缺失的
        }
        return res;
    }
}
