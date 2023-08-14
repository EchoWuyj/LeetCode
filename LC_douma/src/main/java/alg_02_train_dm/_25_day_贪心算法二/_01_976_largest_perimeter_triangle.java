package alg_02_train_dm._25_day_贪心算法二;

import java.util.Arrays;

/**
 * @Author Wuyj
 * @DateTime 2023-04-13 10:46
 * @Version 1.0
 */
public class _01_976_largest_perimeter_triangle {
     /*
        976. 三角形的最大周长
        给定由一些正数(代表长度)组成的数组 A
        返回由其中三个长度组成的、面积不为零的三角形的最大周长。

        如果不能形成任何面积不为零的三角形，返回 0。

        KeyPoint 注意事项
        构成三角形前提条件：两条较小值边之和(严格)大于最大边

        示例 1：
        输入：[2,1,2]
        输出：5

        示例 2：
        输入：[1,2,1]
        输出：0

        示例 3：
        输入：[3,2,3,4]
        输出：10

        示例 4：
        输入：[3,6,2,3]
        输出：8

        提示：
        3 <= A.length <= 10000
        1 <= A[i] <= 10^6

        KeyPoint 注意事项
        1 <= A[i] <= 10^6 => 为了防止数值相加溢出
     */

    // 贪心：为了得到三角形的最大周长，贪心地每次选择最大的三个边，判断这三条边能否构成三角形
    public int largestPerimeter(int[] nums) {
        // 升序排序
        Arrays.sort(nums);
        int n = nums.length;
        // 数组下标索引涉及 i-2，故需要保证 i>=2
        for (int i = n - 1; i >= 2; i--) {
            // 严格大于
            if (nums[i - 2] + nums[i - 1] > nums[i]) {
                return nums[i - 2] + nums[i - 1] + nums[i];
            }
            // 若 if 不成立 => nums[i - 2] + nums[i - 1] <= nums[i]
            // nums[i] 作为最大边不成立，只能找 nums[i-1] 作为最大的边
            // 同时，另外两边都得小于 nums[i-1]，故另外两边只能：nums[i-2] 和 nums[i-3]
            // => 每条边索引 i 统一减 1

        }
        return 0;
    }
}
