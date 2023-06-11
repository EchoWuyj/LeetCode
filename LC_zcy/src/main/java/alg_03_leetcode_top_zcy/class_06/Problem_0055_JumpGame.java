package alg_03_leetcode_top_zcy.class_06;

/**
 * @Author Wuyj
 * @DateTime 2023-02-20 9:03
 * @Version 1.0
 */

// 跳跃游戏
public class Problem_0055_JumpGame {
    /*
        给定一个非负整数数组nums,你最初位于数组的第一个下标,数组中的每个元素代表你在该位置可以跳跃的最大长度
        判断你是否能够到达最后一个下标

        思路:
        定义变量max表示目前为止能够跳跃到最右的位置,最开始max=0;
        来到i位置,需要判断i位置是否能到达,即比较i和max,若i>max则位置到达不了,返回false
        同时遍历数组中每个位置时,需要判断:当前位置的数字+能够跳跃距离num[i]是否大于max,大于则更新max

     */
    public boolean canJump(int[] nums) {
        if (nums.length < 2) {
            return true;
        }
        int max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            // 过滤条件
            if (max >= nums.length - 1) {
                return true;
            }
            if (i > max) {
                return false;
            }
            // 注意:i+nums[i],不是max+nums[i]
            max = Math.max(max, i + nums[i]);
        }

        // for循环结束都没有违规情况,则是能到达n-1位置
        return true;
    }
}

