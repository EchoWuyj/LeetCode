package alg_02_train_dm._01_day_数组技巧._01_元素作为索引;

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
     */

    // KeyPoint 方法一 第一次出现，标记负数，再次遇到负数，第二次出现
    // nums 范围 [1, n]，nums 都是正整数，联想通过负数标记第一次出现
    public List<Integer> findDuplicates1(int[] nums) {
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            // 因为 nums[index] = -nums[index] 缘故，获取索引需要保证为正数
            int index = Math.abs(nums[i]) - 1;
            // nums[index] < 0，则 nums[i] 已经出现一次，将其加入 res
            // Math.abs(nums[i]) -> 输出结果需要保证为正，nums[i] 可能被设置为负之后，再遍历到该元素
            if (nums[index] < 0) res.add(Math.abs(nums[i]));
                // nums[i] 第一次出现，将该 index 上 nums[index] 设置为负数，标记出现一次
            else nums[index] = -nums[index];
        }
        return res;
    }

    // KeyPoint 方法二 +n
    // nums 范围 [1, n]，nums[index] 出现一次加一个 n，若 nums[index] > 2n，nums[index] 出现两次
    public List<Integer> findDuplicates(int[] nums) {
        // 多次使用变量，单独定义
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            // 转换公式，通过  % n，将多加的 n 全部消除
            int index = (nums[i] - 1) % n;
            nums[index] += n;
        }

        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            // 元素值 > 2*n，该索引对应的元素出现 2 次
            // i 索引，i + 1 元素值，两者之间大小是差 1 的关系
            if (nums[i] > 2 * n) res.add(i + 1);
        }
        return res;
    }
}
