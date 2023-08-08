package alg_02_train_dm._10_day_栈和队列;

/**
 * @Author Wuyj
 * @DateTime 2023-04-27 16:36
 * @Version 1.0
 */
public class _13_456_132_pattern1 {

    /*
        456 号算法题：132 模式
        给你一个整数数组 nums ，数组中共有 n 个整数。
        132 模式的子序列 由三个整数 nums[i]、nums[j] 和 nums[k] 组成，
        并同时满足：i < j < k 和 nums[i] < nums[k] < nums[j] 。
        如果 nums 中存在 132 模式的子序列 ，返回 true ；否则，返回 false 。

        解释：132 模式
              i < j < k  => 索引
              ↑   ↑   ↑
              1   3   2  => 数值

        输入：nums = [1,2,3,4]
        输出：false

        输入：nums = [3,1,4,2]
        输出：true

        1,4,2 => 132 模式

        输入：nums = [-1,3,2,0]
        输出：true

        -1,3,2 => 132 模式

        子序列 => 不要求是连续的
        -1,2,0 => 132 模式

        提示：
        n == nums.length
        1 <= n <= 2*10^5
        -10^9 <= nums[i] <= 10^9
     */

    // KeyPoint 方法一 暴力解法 => 超时
    // => 没有什么思路，就先暴力解
    // O(n^3)
    public boolean find132pattern1(int[] nums) {
        int n = nums.length;
        if (n < 3) return false;
        for (int i = 0; i < n; i++) {
            // 严格从后一个位置开始，保证索引严格不等
            for (int j = i + 1; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    // 索引 i < j < k
                    // 数值 nums[i] < nums[k] < nums[j]
                    if (nums[i] < nums[k] && nums[k] < nums[j]) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    // KeyPoint 方法二 维护最小值 min => 超时
    // O(n^2)
    // 提示给的数据范围：2*10^5，而不是 2*10^4，2*10^4 有可能勉强通过
    public boolean find132pattern2(int[] nums) {

        // nums 3 1 4 4 2 1
        //      ↑   ↑     ↑
        //      i   j     k

        // 固定 0 ~ j 上找最小值
        // 在 j 的右边找到 nums[k]
        // => 从而使得 min[0,j) < nums[k] < nums[j]
        // => 注意：[0,j) 等价于 [0,j-1]

        int n = nums.length;
        if (n < 3) return false;
        // numsi 表示：[0,j) 中最小值
        int numsi = nums[0];
        // j 表示 "132" 中的 3，j 只能从索引 1 开始
        for (int j = 1; j < n; j++) {

            // KeyPoint 优化
            // 将原来方法一中，里面两层 for 嵌套循环，拆解成两个相互独立 for 循环 O(n)
            // 从而将时间复杂度 O(n^3) =>  O(n^2)

            // 在 [j+1,n-1] 区间，通过线性查找，是否存在这样的 nums[k]
            // => 即 numsi < nums[k] && nums[k] < nums[j]
            for (int k = j + 1; k < n; k++) {
                if (numsi < nums[k] && nums[k] < nums[j]) {
                    return true;
                }
            }

            // 在线性时间内 [for 循环过程]，维护 [0,j) 等价于 [0,j-1] 内最小值 nums[i]
            // 注意：[0,j) 是不包括 j 的
            // 尽量让 "132" 中 1 小，这样找到 k 的概率更大
            numsi = Math.min(numsi, nums[j]);
        }
        return false;
    }
}
