package alg_02_train_dm._02_day_一维数组;

/**
 * @Author Wuyj
 * @DateTime 2023-04-17 16:38
 * @Version 1.0
 */
public class _03_665_non_decreasing_array {

    /*
        665. 非递减数列
        给你一个长度为 n 的整数数组 nums，请你判断在 最多 改变 1 个元素的情况下，该数组能否变成一个非递减数列。
        我们是这样定义一个非递减数列的： 对于数组中任意的 i (0 <= i <= n-2)，总满足 nums[i] <= nums[i + 1]。

        提示
        n == nums.length
        1 <= n <= 10^4
        -10^5 <= nums[i] <= 10^5

        输入: nums = [4,2,3]
        输出: true
        解释: 你可以通过把第一个 4 变成 1 来使得它成为一个非递减数列。

        输入: nums = [4,2,1]
        输出: false
        解释: 你不能在只改变一个元素的情况下将其变为非递减数列。

     */

    // 直接模拟 => 从数组最开始一两个元素，逐步增加新的元素，从简单到复杂的过程中，使用代码实现基本功能
    //         => 代码框架基本完成之后，需要考虑特殊情况，增强代码的健壮性
    public boolean checkPossibility(int[] nums) {
        int cnt = 0;
        for (int i = 1; i < nums.length; i++) {
            // 不满足'非递减数'，需要进行调整
            if (nums[i] < nums[i - 1]) {
                cnt++;
                if (cnt > 1) return false;
                // 修改 nums[i] 大小，需要考虑 nums[i] 和 nums[i - 2] 的关系
                // 1 若仅有两个元素，if 条件不成立，i=2，不满足 i < nums.length，跳出 for 循环，return true
                // 2 若三个元素及其以上的情况，判断相邻元素之间大小关系
                // 已知 nums[i] < nums[i-1]
                //      nums[i-2] <= nums[i-1]
                // 让 nums[i] = nums[i-1] 必然 '大于等于' nums[i-2]
                if (i - 2 >= 0 && nums[i] < nums[i - 2]) nums[i] = nums[i - 1];
                // 若后续 for 循环，存在 nums[i] < nums[i - 1]，count > 1 直接 return false
            }
        }
        return true;
    }
}
