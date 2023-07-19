package alg_02_train_dm._01_day_数组技巧_二刷._01_技巧一_元素作为索引下标;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-04-16 12:29
 * @Version 1.0
 */
public class _01_442_find_all_duplicates_in_an_array {
    /*
        442. 数组中重复的数据
        给你一个长度为 n 的整数数组 nums ，其中 nums 的所有整数都在范围 [1, n] 内，
        且每个整数出现 一次 或 两次 。请你找出所有出现 两次 的整数，并以数组形式返回。
        你必须设计并实现一个时间复杂度为 O(n) 且仅使用常量额外空间的算法解决此问题。

        输入：nums = [4,3,2,7,8,2,3,1]
        输出：[2,3]

        输入：nums = [1,1,2]
        输出：[1]

        提示：
        n == nums.length
        1 <= n <= 105
        1 <= nums[i] <= n
        nums 中的每个元素出现 一次 或 两次
     */

    // KeyPoint 方法一 统计计数
    // 时间复杂度 O(n)
    // 空间复杂度 O(n) => 使用额外空间，不符合题目要求
    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> res = new ArrayList<>();
        int n = nums.length;
        int[] count = new int[n + 1];
        for (int num : nums) {
            count[num]++;
        }
        for (int i = 1; i <= n; i++) {
            if (count[i] == 2) res.add(i);
        }
        return res;
    }

    // KeyPoint 方法二 标记负数 => 纯正数数组常用
    // 1.不使用额外数组，标记数字第一次出现
    //    => 使用 num 减 1 得到对应的唯一索引 index，将该位置上数字标记为负数
    // 2.若按照这种方式标记，再次遇到负数，则表示第二次出现，加入结果集
    // 关键：nums 范围 [1, n]，nums 都是正整数，联想通过负数标记第一次出现
    // 类似：11_41_first_missing_positive => 将正数变成负数
    public List<Integer> findDuplicates1(int[] nums) {
        List<Integer> res = new ArrayList<>();
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            // nums[i] 有可能先被设置为了负值，因为 nums[index] = -nums[index] 缘故
            // 获取索引需要保证为正数，故需要 Math.abs 取正值
            int index = Math.abs(nums[i]) - 1;

            // nums[index] < 0，则 nums[i] 已经出现一次，将其加入 res
            // 注意：nums[i] 可能被设置为负之后，再遍历到该元素，使用 Math.abs 取正值
            if (nums[index] < 0) res.add(Math.abs(nums[i]));
                // nums[i] 第一次出现，将该 index 上 nums[index] 设置为负数，标记出现一次
            else nums[index] = -nums[index];
        }
        return res;
    }

    // KeyPoint 方法三 + n，n 为数组长度
    // 1.nums 范围 [1, n]，使用 num 减 1 得到对应的唯一索引 index
    //   将 nums[index] 累加 n，即出现一次加一个 n，其中 n 对应数组长度
    //   => 注意：因为涉及加 n，故每次取 index 都要 % n，消除多加的 n
    // 2.若 nums[index] > 2n，nums[index] 出现两次
    public List<Integer> findDuplicates2(int[] nums) {
        // 多次使用变量，单独定义
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            // 转换公式，通过  % n，将多加的 n 全部消除
            // KeyPoint 先减 1，再去取模
            // 若是 (nums[i]) % n-1，可能数组越界，如 nums[i] = n，n % n = 0，再去减 1，结果为 -1
            int index = (nums[i] - 1) % n;
            nums[index] += n;
        }

        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            // 元素值 > 2n，该索引对应的元素出现 2 次
            // => 自身数组值 + 2n > 2n
            // i 索引，i+1 元素值，两者之间大小是差 1 的关系
            if (nums[i] > 2 * n) res.add(i + 1);
        }
        return res;
    }
}
