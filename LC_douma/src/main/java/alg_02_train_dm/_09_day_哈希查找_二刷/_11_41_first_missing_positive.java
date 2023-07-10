package alg_02_train_dm._09_day_哈希查找_二刷;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author Wuyj
 * @DateTime 2023-04-16 12:08
 * @Version 1.0
 */
public class _11_41_first_missing_positive {
    /*
        41 ：缺失的第一个正数
        给你一个 未排序 的整数数组 nums ，请你找出其中没有出现的最小的正整数。
        输入：nums = [1,2,3]
        输出： 4

        输入：nums = [3,4,-1,1]
        输出： 2

        提示：
        1 <= nums.length <= 5 * 10^5
        -2^31 <= nums[i] <= 2^31 - 1

        进阶：你可以实现时间复杂度为 O(n) 并且只使用常数级别额外空间的解决方案吗？

        KeyPoint 注意事项
        1.区别：1539 第 k 个缺失的正整数
        2.思路：遇到新的问题，从暴力方法进行逐步优化，不要想着一步就搞出最优解
     */

    // KeyPoint 方法一 暴力解法 => 超时
    // 时间复杂度 O(n^2)
    // 空间辅助度 O(1)
    public int firstMissingPositive1(int[] nums) {
        int n = nums.length;
        // 逐一进行 check 每个正数，从 1 到 n
        for (int i = 1; i <= n; i++) {
            boolean isExits = false;
            // 线性查找
            for (int j = 0; j < n; j++) {
                if (nums[j] == i) {
                    isExits = true;
                    break;
                }
            }
            // 若数组中不存在 i ，则是缺失的
            if (!isExits) return i;
        }
        return n + 1;
    }

    // KeyPoint 方法二 哈希查找
    // 时间复杂度 O(n)
    // 空间辅助度 O(n)
    public int firstMissingPositive2(int[] nums) {
        // 申请额外空间 set，为了记录已经出现的数字，便于后面的查找
        Set<Integer> set = new HashSet<>();
        for (int num : nums) set.add(num);

        int n = nums.length;
        for (int i = 1; i <= n; i++) {
            // 哈希查找
            if (!set.contains(i)) return i;
        }
        return n + 1;
    }

    // KeyPoint 方法三 数组元素作为索引
    // 时间复杂度 O(n)
    // 空间辅助度 O(1)
    public static int firstMissingPositive(int[] nums) {

        // 申请额外空间 set，为了记录已经出现的数字，便于后面的查找
        // 优化：为了节省 set，需要将已经出现的数字记录在 num 中

        // num  1 2 3 4 5 6
        // 索引 0 1 2 3 4 5

        // 根据元素和索引相差 1 的关系，nums[index] - index = 1，将元素对应成索引
        // 1.遍历到数组元素为数字 k，将索引 k-1 上的值设置为负数，标记数组元素数字 k 已经出现过
        // 2.通过以上操作，将已经出现过的元素，记录在 num 中

        // 具体案例

        // num  1 2 6 4 5 6
        // 索引 0 1 2 3 4 5
        //      i

        // num  -1 -2 6 4 5 -6
        // 索引  0  1 2 3 4  5
        //            i

        // num  -1 -2 6 -4 -5 -6
        // 索引  0  1 2  3  4  5
        //                  i

        // num  -1 -2 6 -4 -5 -6
        // 索引  0  1 2  3  4  5
        //                     i

        // 1.已经出现的正整数 i+1，将其转化成索引 i，将索引位置上元素设置为负数 -num[i]
        //   标识该索引 i 对应数字 i+1 已经出现过了，这样将已经出现数字信息放入原始 num 中
        // 2.再去从左往右，遍历一遍 num，找到第一个不为负数的元素，对应索引 i，则第一缺失的
        //   正数为 i+1

        int n = nums.length;

        // 1. 将小于 0 的元素变成正数
        // 因为本题找的是缺失的第一个正数，故遇到负数不考虑，直接跳过，将负数变成：数组长度 + 1
        for (int i = 0; i < n; i++) {
            // nums[i] <= 0 必须取等，否则报错，因为后续代码涉及 num - 1
            // 若存在 num = 0，则 nums[num-1] 就数据索引越界了
            if (nums[i] <= 0) nums[i] = n + 1;
        }

        // 2. 将索引为 nums[i] 的元素设置为负数
        for (int i = 0; i < n; i++) {
            // 靠后 index -1 位置 对应元素值 num(nums[index -1])
            // 可能被前面 [num] = index，将其设置为负，故需要取绝对值，获取：正值 num
            int num = Math.abs(nums[i]);

            // 判断 num 是否在合法范围内
            // => 数组最大索引 n-1，对应数字 n，故可以取到 n
            if (num <= n) {
                // 数字 num => 索引 num - 1
                // 将 nums[num-1] 设置为负数，不管 nums[num-1] 是否为负，将其统一设置为负数
                nums[num - 1] = -Math.abs(nums[num - 1]);
            }
        }

        // 3. 找到第一个大于 0 的元素，返回这个元素对应的下标 i 再加 1
        for (int i = 0; i < n; i++) {
            if (nums[i] > 0) return i + 1;
        }
        // 数组中没有 num[i] 为正，表示每个 index 位置上都为负值
        // 则第一个缺失正数，数组长度 n+1
        // num     1 2 3
        // index   0 1 2
        return n + 1;
    }
}
