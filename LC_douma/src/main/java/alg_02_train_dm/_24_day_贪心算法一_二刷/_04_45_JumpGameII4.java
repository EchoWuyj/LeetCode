package alg_02_train_dm._24_day_贪心算法一_二刷;

import alg_02_train_wyj._24_day_贪心算法一._04_45_JumpGame4;

/**
 * @Author Wuyj
 * @DateTime 2023-04-11 18:12
 * @Version 1.0
 */
public class _04_45_JumpGameII4 {

    // KeyPoint 贪心 => 优化循环
    public int jump(int[] nums) {
        int n = nums.length;
        if (n == 1) return 0;
        int steps = 0, end = 0;
        int maxPos = 0;
        // 优化循环 => 只需要循环遍历一次数组，即可求得最少跳跃步数
        // i < nums.length-1，i 到最后一个位置，没有必要再去遍历了
        for (int i = 0; i < n - 1; i++) {
            // 遍历从 0 位置到 i 位置，每一个位置都去计算 maxPos
            maxPos = Math.max(maxPos, i + nums[i]);
            // 当 i 指针到 end 位置，说明该下次跳跃了
            // 将 end 移动到能跳最远位置 maxPos，同时记录跳跃了一次
            if (i == end) {
                steps++;
                end = maxPos;
            }
        }
        return steps;
    }

    public static void main(String[] args) {
        int[] nums = {2, 3, 1, 1, 4};
        System.out.println(new _04_45_JumpGame4().jump(nums));
    }
}
