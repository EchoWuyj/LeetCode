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
        KeyPoint 面积不为零，说明得能构成三角形
                 构成三角形前提条件:两条较小值边之和大于最大边

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
        1 <= A[i] <= 10^6  => 为了防止数值相加溢出
     */

    // 贪心：为了得到三角形的最大周长，每次选择最大的三个边，判断这三条边能否构成三角形
    public int largestPerimeter(int[] nums) {
        // 升序排序
        Arrays.sort(nums);
        // 数组下标索引涉及 i-2，故需要保证 i>=2
        for (int i = nums.length - 1; i >= 2; i--) {
            if (nums[i - 2] + nums[i - 1] > nums[i]) {
                return nums[i - 2] + nums[i - 1] + nums[i];
            }
            // if 不成立，三条边 num[i-2]，nums[i-1]，nums[i] 的 i 都要统一减 1
            // num[i-2]，nums[i-1] 已经是除 nums[i] 之外的最大值了，故只能 nums[i] 减小
        }
        return 0;
    }
}
