package alg_03_high_frequency._01_codetop_2024_01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2024-01-12 19:44
 * @Version 1.0
 */
public class _06_15_threeSum {
    public List<List<Integer>> threeSum(int[] nums) {

        // 使用 ArrayList 来接受
        List<List<Integer>> res = new ArrayList<>();
        // 特判
        if (nums == null || nums.length <= 2) return res;
        // 排序
        Arrays.sort(nums);
        int n = nums.length;

        // 最后一组，第一元素为 n-3，保留 n-2 和 n-1 作为第二和第三个元素
        for (int i = 0; i < n - 2; i++) {
            // 重复跳过
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            int target = -nums[i];
            // 从 i 后面位置开始判断
            int left = i + 1;
            int right = n - 1;
            // left 必然不能和 right 相等
            while (left < right) {
                // 计算 [left] 和 [right]
                int sum = nums[left] + nums[right];
                if (sum == target) {
                    // 保存数字，不是索引
                    res.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    // 去重
                    // 保证 left 和 right 是不同的，故 left < right
                    while (left < right && nums[left] == nums[++left]) ;
                    while (left < right && nums[right] == nums[--right]) ;
                } else if (sum < target) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return res;
    }
}
