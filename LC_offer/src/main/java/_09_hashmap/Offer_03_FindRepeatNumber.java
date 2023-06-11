package _09_hashmap;

import java.util.HashSet;

/**
 * @Author Wuyj
 * @DateTime 2022-08-19 19:42
 * @Version 1.0
 */
public class Offer_03_FindRepeatNumber {
    public static void main(String[] args) {
        int[] input = {2, 3, 1, 0, 2, 5, 3};
        int result = findRepeatNumber(input);
        System.out.println(result);
    }

    public static int findRepeatNumber(int[] nums) {

        // HashSet 的特点是不会存储重复元素
        // 所以可以利用 HashSet 来查找出重复的元素
        HashSet<Integer> dic = new HashSet<>();

        // 遍历数组，设置此时遍历的元素为 num
        for (int num : nums) {
            // KeyPoint 这里 if 只有两种选择，所以使用 if else 结构
            // 如果发现 dic 中已经存储了 num，那么说明找到了重复的那个元素
            if (dic.contains(num)) {
                // 把 num 这个结果进行返回
                return num;
            } else {
                // 把 num 添加到 dic 中
                dic.add(num);
            }
        }

        // 由于 nums 中所有的数字都在 0 ～ n-1 的范围内
        // 所以负数，比如 -1 必然不在 nums 这个范围内
        // 如果没有找到重复的数字，那么返回 -1
        return -1;
    }
}
