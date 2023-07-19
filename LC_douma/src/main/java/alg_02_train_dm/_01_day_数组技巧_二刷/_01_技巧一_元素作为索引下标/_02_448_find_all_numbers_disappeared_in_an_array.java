package alg_02_train_dm._01_day_数组技巧_二刷._01_技巧一_元素作为索引下标;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-04-16 14:22
 * @Version 1.0
 */
public class _02_448_find_all_numbers_disappeared_in_an_array {

    /*
        448. 找到所有数组中消失的数字
        给你一个含 n 个整数的数组 nums ，其中 nums[i] 在区间 [1, n] 内。
        请你找出所有在 [1, n] 范围内但没有出现在 nums 中的数字，并以数组的形式返回结果。

        输入：nums = [4,3,2,7,8,2,3,1]
        输出：[5,6]

        输入：nums = [1,1]
        输出：[2]

        要求：时间复杂度为 O(n)，空间复杂度 O(1)
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
            if (count[i] == 0) res.add(i);
        }
        return res;
    }

    // KeyPoint 方法二 + n，n 为数组长度
    // 标记数组元素出现的两种方式
    // 1.标记负数
    // 2.+ n
    // 注意:不能使用标记'负数'，一个元素出现两次，则负负得正，无法区分是否出现过
    public List<Integer> findDisappearedNumbers2(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            // 元素值 => 索引值
            // nums[i] => nums[i] - 1
            int index = (nums[i] - 1) % n;
            nums[index] += n;
        }

        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            // nums[i] <= n，说明没有 +n，则说明该索引 i 对应元素 i+1，一次没有出现
            if (nums[i] <= n) res.add(i + 1);
        }
        return res;
    }
}
