package alg_02_train_dm._09_day_哈希查找;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author Wuyj
 * @DateTime 2023-04-16 12:08
 * @Version 1.0
 */
public class _11_41_first_missing_positive {
    /*
        41 号算法题：缺失的第一个正数
        给你一个未排序的整数数组 nums ，请你找出其中没有出现的最小的正整数。

        输入：nums = [1,2,3]
        输出： 4

        输入：nums = [3,4,-1,1]
        输出： 2

        1 <= nums.length <= 5 * 10^5
        -2^31 <= nums[i] <= 2^31 - 1

        进阶：你可以实现时间复杂度为 O(n) 并且只使用常数级别额外空间的解决方案吗？
     */

    // KeyPoint 遇到新的问题，从暴力方法进行逐步优化，不要想着一步就搞出最优解
    // KeyPoint 方法一 暴力解法 => 超时
    // 时间复杂度 O(n^2)
    // 空间辅助度 O(1)
    public int firstMissingPositive1(int[] nums) {
        // 从 1 到 nums.length 逐一进行 check
        for (int i = 1; i <= nums.length; i++) {
            boolean isExits = false;
            // 线性查找
            for (int j = 0; j < nums.length; j++) {
                if (nums[j] == i) isExits = true;
            }
            // 若数组中不存在 i ，则是缺失的
            if (!isExits) return i;
        }
        return nums.length + 1;
    }

    // KeyPoint 方法二 哈希查找
    // 时间复杂度 O(n)
    // 空间辅助度 O(n)
    public int firstMissingPositive2(int[] nums) {
        // 申请额外空间 set，为了记录已经出现的数字，便于后面的查找
        Set<Integer> set = new HashSet<>();
        for (int num : nums) set.add(num);

        for (int i = 1; i <= nums.length; i++) {
            // 哈希查找
            if (!set.contains(i)) return i;
        }
        return nums.length + 1;
    }

    // KeyPoint 方法三 数组元素作为索引
    // 优化：为了节省 set，需要将已经出现的数字记录在 num 中
    // 时间复杂度 O(n)
    // 空间辅助度 O(1)
    public static int firstMissingPositive(int[] nums) {

        // num  1 2 3 4 5 6
        // 索引 0 1 2 3 4 5
        // 根据数字和索引的关系，将数字对应成索引
        // 遍历到数字 k，将索引 k-1 上的值设置为负数，标记 k 已经出现过
        // 通过以上操作，将已经出现过的元素，记录在 num 中

        int n = nums.length;

        // 1. 将小于 0 的元素变成正数
        // 因为本题找的是缺失的第一个正数，故遇到负数不考虑，直接跳过，将负数变成：数组长度 + 1
        for (int i = 0; i < n; i++) {
            // KeyPoint nums[i] <= 0 必须取等，否则报错，
            //          因为后续代码涉及 num - 1，若 num = 0，则 nums[num - 1] 就越界了
            if (nums[i] <= 0) nums[i] = n + 1;
        }

        // 2. 将索引为 nums[i] 的元素设置为负数
        for (int i = 0; i < n; i++) {
            // 靠后 index -1 位置 num 可能被前面 [num] = index，将其设置为负
            int num = Math.abs(nums[i]);
            // 索引 n-1，对应数字 n，故可以取到 n
            if (num <= n) {
                // 数字 num => 索引 num - 1
                // 将 nums[num - 1] 设置为负数
                // 不管 nums[num - 1] 是否为负，将其统一设置为负数
                nums[num - 1] = -Math.abs(nums[num - 1]);
            }
        }

        // 3. 找到第一个大于 0 的元素，返回这个元素对应的下标 i 再加 1
        for (int i = 0; i < n; i++) {
            if (nums[i] > 0) return i + 1;
        }

        return n + 1;
    }
}
