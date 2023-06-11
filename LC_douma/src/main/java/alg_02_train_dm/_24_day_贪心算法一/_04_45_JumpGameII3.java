package alg_02_train_dm._24_day_贪心算法一;

import alg_02_train_wyj._24_day_贪心算法一._04_45_JumpGame3;

/**
 * @Author Wuyj
 * @DateTime 2023-04-11 18:12
 * @Version 1.0
 */
public class _04_45_JumpGameII3 {

    // KeyPoint 贪心策略：每步都选择能跳到的最远距离
    // 时间复杂度 O(n)
    public int jump(int[] nums) {
        if (nums.length == 1) return 0;
        int steps = 0;
        // 分别赋值， start,end = 0，只是给 end 赋值了，但是 start 并没有赋值
        int start = 0, end = 0;
        // end >= nums.length - 1，跳出 while 循环
        // 即 end 到最后一个位置的时候就不用走了
        while (end < nums.length - 1) {
            // 记录最远位置
            int maxPos = 0;
            // 每次从 [start, end] 中都选择能跳到的最远距离
            // 本质上通过 start 和 end 指针，遍历一遍数组
            for (int i = start; i <= end; i++) {
                // i 当前位置索引
                // nums[i] 当前位置能跳多远
                maxPos = Math.max(maxPos, i + nums[i]);
            }
            start = end + 1;
            end = maxPos;
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
