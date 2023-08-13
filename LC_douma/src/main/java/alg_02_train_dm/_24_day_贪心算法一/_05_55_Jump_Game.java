package alg_02_train_dm._24_day_贪心算法一;

/**
 * @Author Wuyj
 * @DateTime 2023-04-11 19:26
 * @Version 1.0
 */
public class _05_55_Jump_Game {
    /*
        55. 跳跃游戏
        给定一个非负整数数组 nums，你最初位于数组的 第一个下标
        数组中的每个元素代表你在该位置可以跳跃的最大长度
        判断你是否能够到达最后一个下标。
    
        示例 1：
        输入：nums = [2,3,1,1,4]
        输出：true
        解释：可以先跳 1 步，从下标 0 到达下标 1, 然后再从下标 1 跳 3 步到达最后一个下标。
    
        示例 2：
        输入：nums = [3,2,1,0,4]
        输出：false
        解释：无论怎样，总会到达下标为 3 的位置。
        但该下标的最大跳跃长度是 0 ， 所以永远不可能到达最后一个下标。
    
        提示：
        1 <= nums.length <= 3 * 10^4
        0 <= nums[i] <= 10^5

     */

    // KeyPoint 贪心策略：每步都选择能跳到的最远距离
    public boolean canJump(int[] nums) {
        int n = nums.length;
        int maxPos = 0;
        // 不同点：判断你是否能够到达最后一个下标，而不是最少跳跃的次数
        // i 需要在 n-1 位置进行判断，故 i < n，而不是 i < n-1
        for (int i = 0; i < n; i++) {
            // 每次跳跃之前，都要判断下是否 maxPos >= i => 即最远位置 maxPos 是否能跳跃到 i 位置
            // 若最远位置 maxPos < i 位置，则说明到不了 i 位置，返回 false
            if (maxPos < i) return false;
            // i 每次移动一步都去计算 maxPos
            maxPos = Math.max(maxPos, i + nums[i]);
        }
        return true;
    }

    // KeyPoint 自己解法 => 在上一题基础上修改
    public boolean canJump1(int[] nums) {
        int n = nums.length;
        if (n == 1) return true;
        int maxPos = 0, end = 0;
        for (int i = 0; i < n; i++) {
            maxPos = Math.max(maxPos, i + nums[i]);
            if (i == end) {
                if (maxPos <= i && i != n - 1) return false;
                end = maxPos;
            }
        }
        return true;

        // KeyPoint 代码经验
        // 写代码的过程中，结合手动模拟具体的测试用例，明确操作流程
        // 再去使用代码实现，而不是纯写代码
    }
}
