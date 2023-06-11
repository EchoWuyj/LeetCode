package alg_02_train_dm._24_day_贪心算法一;

import alg_02_train_wyj._24_day_贪心算法一._04_45_JumpGame4;

/**
 * @Author Wuyj
 * @DateTime 2023-04-11 18:12
 * @Version 1.0
 */
public class _04_45_JumpGameII4 {
    // KeyPoint 贪心 => 优化循环
    public int jump(int[] nums) {
        if (nums.length == 1) return 0;
        int steps = 0;
        int maxPos = 0, end = 0;
        // 优化循环 => 只需要循环遍历一次数组，即可求得最少跳跃步数
        // i < nums.length - 1，i 到最后一个位置，没有必要再去遍历了
        for (int i = 0; i < nums.length - 1; i++) {
            // 遍历从 0 位置到 i 位置，每一个位置都去计算 maxPos
            maxPos = Math.max(maxPos, i + nums[i]);
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
