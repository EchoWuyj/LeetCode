package alg_02_train_dm._01_day_数组技巧._01_元素作为索引;

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

       时间复杂度为 O(n)，空间复杂度 O(1)
     */

    // KeyPoint +n
    // 注意:不能使用改'负数'，一个元素出现两次，则负负得正，无法区分是否出现过
    public List<Integer> findDisappearedNumbers(int[] nums) {
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
