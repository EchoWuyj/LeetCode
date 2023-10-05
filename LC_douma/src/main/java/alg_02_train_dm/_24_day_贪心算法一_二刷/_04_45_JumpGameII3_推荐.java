package alg_02_train_dm._24_day_贪心算法一_二刷;

import alg_02_train_wyj._24_day_贪心算法一._04_45_JumpGame3;

/**
 * @Author Wuyj
 * @DateTime 2023-04-11 18:12
 * @Version 1.0
 */
public class _04_45_JumpGameII3_推荐 {

    // KeyPoint 贪心策略：每步都选择能跳到的最远距离
    // 时间复杂度 O(n) => 遍历一遍数组
    public int jump(int[] nums) {
        int n = nums.length;
        if (n == 1) return 0;
        int steps = 0;
        // start 和 end 本质：能够跳跃最远范围的左右两端
        // 注意：对 start 和 end 分别赋值的写法
        int start = 0, end = 0;
        // KeyPoint 错误代码格式
        // start,end = 0 => 只是给 end 赋值了，但是 start 并没有赋值

        // 经过上一步跳跃之后，若 end >= nums.length-1
        // 则已经在最后位置 或者 超出最后位置，跳出 while 循环，不用走了
        while (end < n - 1) {
            // 记录最远位置
            int maxPos = 0;
            // 每次从 [start,end] 中都选择能跳到的最远距离
            // 本质：通过 start 和 end 指针，遍历一遍数组
            // KeyPoint 优化 => 将 while 循环 和 for 循环合并成一个循环
            for (int i = start; i <= end; i++) {
                // i 当前位置索引
                // nums[i] 当前位置能跳多远
                // i + nums[i] 在原来 i 的基础上能跳的位置
                maxPos = Math.max(maxPos, i + nums[i]);
            }
            // 上一轮 end 的后一个位置
            start = end + 1;
            end = maxPos;
            // 跳跃一步
            steps++;
        }
        return steps;
    }

    public static void main(String[] args) {
        int[] nums = {2, 3, 1, 1, 4};
        System.out.println(new _04_45_JumpGame3().jump(nums));
        // 2
    }
}
