package alg_02_train_dm._07_day_排序算法;

/**
 * @Author Wuyj
 * @DateTime 2023-05-14 10:46
 * @Version 1.0
 */

// KeyPoint 归并排序应用
public class _05_Offer_51_ReversePairs1 {

      /*
        剑指 Offer 51. 数组中的逆序对
        在数组中的两个数字，如果 前面一个数字 大于 后面的数字，则这两个数字组成一个逆序对
        输入一个数组，求出这个数组中的逆序对的总数。

        示例 1:
        输入: [7,5,6,4]
        输出: 5

        解释： 5 个逆序对
            7,5
            7,6
            7,4
            5,4
            6,4

        限制：
        0 <= 数组长度 <= 50000 => 时间复杂度 O(n^2) 算法必然超时

     */

    // KeyPoint 方法一 暴力解 => 超出时间限制
    // 时间复杂度 O(n^2)
    public int reversePairs1(int[] nums) {
        if (nums == null || nums.length < 2) {
            return 0;
        }

        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            // j 直接从 i+1 开始，避免从 i 开始，提高一点效率
            for (int j = i + 1; j < nums.length; j++) {
                // 逆序，则 count++
                if (nums[i] > nums[j]) count++;
            }
        }
        return count;
    }
}
