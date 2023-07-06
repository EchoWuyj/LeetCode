package alg_02_train_dm._29_day_动态规划四_二刷;

/**
 * @Author Wuyj
 * @DateTime 2023-06-12 16:37
 * @Version 1.0
 */
public class _09_376_wiggle_subsequence {
     /* 
        376. 摆动序列
        如果连续数字之间的差 严格地在 正数 和 负数 之间交替，则数字序列称为 摆动序列 。
        第一个差(如果存在的话)可能是正数或负数。
        仅有一个元素 或者含 两个不等元素的序列也视作摆动序列。

        例如，[1, 7, 4, 9, 2, 5] 是一个 摆动序列 ，
        因为差值 (6, -3, 5, -7, 3) 是正负交替出现的
        KeyPoint 注意：后一个元素减去前一个元素

        相反，[1, 4, 7, 2, 5] 和 [1, 7, 4, 5, 5] 不是摆动序列，
        第一个序列是因为它的前两个差值都是正数，第二个序列是因为它的最后一个差值为零。

        子序列：可以通过从原始序列中删除一些(也可以不删除)元素来获得，剩下的元素保持其原始顺序。
        KeyPoint 子序列 => 可以不连续

        给你一个整数数组 nums ，返回 nums 中作为 摆动序列 的 最长子序列的长度 。

        示例 1：
        输入：nums = [1,7,4,9,2,5]
        输出：6
        解释：整个序列均为摆动序列，各元素之间的差值为 (6, -3, 5, -7, 3) 。

        示例 2：
        输入：nums = [1,17,5,10,13,15,10,5,16,8]
        输出：7
        解释：这个序列包含几个长度为 7 摆动序列。
        其中一个是 [1, 17, 10, 13, 10, 16, 8] ，各元素之间的差值为 (16, -7, 3, -3, 6, -8) 。

        示例 3：
        输入：nums = [1,2,3,4,5,6,7,8,9]
        输出：2

        提示：
        1 <= nums.length <= 1000
        0 <= nums[i] <= 1000

     */

    // 定义双状态的动态规划
    public int wiggleMaxLength1(int[] nums) {

        // nums： 1 3 2 4
        //        ↗ ↘ ↗
        //        将最后一个差值为正数，定义为上升摆动序列

        // nums：4 2 3 1
        //       ↘ ↗ ↘
        //        将最后一个差值为负数，定义为下降摆动序列

        // 1.在摆动序列中，要么是上升摆动序列，要么是下降摆动序列
        // 2.定义双状态，up[i] 和 down[i]
        //   2.1 每次 up[i] 状态计算依赖于 down[i-1]
        //   2.2 每次 down[i] 状态计算依赖于 up[i-1]
        // 3.最终：求出摆动序列中，最大上升摆动序列 和 最大下降摆动序列
        //         并且在两者中取最大值，即为最长的摆动序列的长度

        // up[i] 表示以索引为 i 的元素结尾的 最长上升摆动序列
        // down[i] 表示以索引为 i 的元素结尾的 最长下降摆动序列
        // KeyPoint 总结：数组相关 dp，以索引 i 结尾的，定义状态比较常见

        // nums     1   3   2   4   2   4   5  1   0  0
        //           ↗   ↘  ↗  ↘  ↗   ↗  ↘  ↘  →
        // up[i]    1   2   2   4   4   6   6  6   6  6
        // down[j]  1   1   3   3   5   5   5  7   7  7

        int n = nums.length;
        // 本题对长度有特殊说明：仅有一个元素 或者含 两个不等元素的序列也视作摆动序列
        // 所以需要进行特判，否则测试用例通过不了
        if (n < 2) return n;

        // 定义两个状态
        // up[i] 表示以索引为 i 的元素结尾的最长上升摆动序列
        // down[i] 表示以索引为 i 的元素结尾的最长下降摆动序列
        int[] up = new int[n];
        int[] down = new int[n];

        // 摆动序列的长度为 1，而不是 nums[0]
        up[0] = down[0] = 1;

        for (int i = 1; i < n; i++) {
            // 上升，up 变化
            if (nums[i] > nums[i - 1]) {
                // 即使 nums 数组中，连续上升两次，但是 down[i-1] 没有发生变化，则 up[i] 始终为 down[i-1] + 1
                // 不会因为连续上升两次，导致加两次 1
                up[i] = down[i - 1] + 1;
                down[i] = down[i - 1];
                // 下降，down 变化
            } else if (nums[i] < nums[i - 1]) {
                up[i] = up[i - 1];
                down[i] = up[i - 1] + 1;
                // 不变化，则 up 和 down 都不变
            } else {
                up[i] = up[i - 1];
                down[i] = down[i - 1];
            }
        }

        return Math.max(up[n - 1], down[n - 1]);
    }

    // 动态规划 + 状态压缩 => 严格按照规则来
    public int wiggleMaxLength2(int[] nums) {
        int n = nums.length;
        if (n <= 1) return 1;

        // up[i] => curUp => 全局定义(全局变量)
        // down[i] => curDown => 全局定义全局变量)
        // up[i - 1] => preUp => 临时变量
        // down[i - 1] => preDown => 临时变量

        // 新的一轮 for 循环开始，提前赋值
        // int preUp = curUp, preDown = curDown;

        int curUp = 1;
        int curDown = 1;

        for (int i = 1; i < n; i++) {
            // 新的一轮 for 循环开始，提前赋值
            int preUp = curUp, preDown = curDown;
            if (nums[i] > nums[i - 1]) {
                curUp = preDown + 1;
                curDown = preDown;
            } else if (nums[i] < nums[i - 1]) {
                curDown = preUp + 1;
                curUp = preUp;
            } else {
                curUp = preUp;
                curDown = preDown;
            }
        }
        return Math.max(curUp, curDown);
    }

    // 动态规划 + 状态压缩 => 进一步优化，for 循环中存在重复赋值
    public int wiggleMaxLength3(int[] nums) {
        int n = nums.length;
        if (n <= 1) return 1;

        int curUp = 1;
        int curDown = 1;

        for (int i = 1; i < n; i++) {
            int preUp = curUp, preDown = curDown;
            if (nums[i] > nums[i - 1]) {
                curUp = preDown + 1;
            } else if (nums[i] < nums[i - 1]) {
                curDown = preUp + 1;
            }
        }
        return Math.max(curUp, curDown);
    }

    // 动态规划 + 状态压缩
    public int wiggleMaxLength(int[] nums) {
        int n = nums.length;
        if (n < 2) return n;

        // 使用两个变量代替两个数组
        // 使用 up 替换 up[i-1]
        // 使用 down 替换 down[i-1]
        int up = 1, down = 1;

        for (int i = 1; i < n; i++) {
            if (nums[i] > nums[i - 1]) {
                up = down + 1;
            } else if (nums[i] < nums[i - 1]) {
                down = up + 1;
            }
        }
        return Math.max(up, down);
    }
}
