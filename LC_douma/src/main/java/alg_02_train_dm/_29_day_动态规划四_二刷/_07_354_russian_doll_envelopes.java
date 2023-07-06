package alg_02_train_dm._29_day_动态规划四_二刷;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-06-12 16:36
 * @Version 1.0
 */
public class _07_354_russian_doll_envelopes {
       /*
        354. 俄罗斯套娃信封问题
        给你一个二维整数数组 envelopes
        其中 envelopes[i] = [wi, hi] ，表示第 i 个信封的宽度和高度。

        当另一个信封的宽度和高度都比这个信封大的时候，
        这个信封就可以放进另一个信封里，如同俄罗斯套娃一样。

        请计算 最多能有多少个 信封能组成一组"俄罗斯套娃"信封（即可以把一个信封放到另一个信封里面）。
        注意：不允许旋转信封。

        示例 1：
        输入：envelopes = [[5,4],[6,4],[6,7],[2,3]]
        输出：3
        解释：最多信封的个数为 3, 组合为: [2,3] => [5,4] => [6,7]。

        示例 2：
        输入：envelopes = [[1,1],[1,1],[1,1]]
        输出：1

        提示：
        1 <= envelopes.length <= 5000
        envelopes[i].length == 2
        1 <= wi, hi <= 10^4

     */

    // 本题找出二维数组的一个排列，使得其中有最长的单调递增子序列(需要保证两个维度都递增)
    // 本质：就是 300 号算法题的变形体
    public int maxEnvelopes(int[][] envelopes) {
        int n = envelopes.length;
        // 只有一个信封，返回 1
        if (n == 1) return 1;

        // 排序
        Arrays.sort(envelopes, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] != o2[0]) {
                    // 先按照宽度升序排列
                    return o1[0] - o2[0];
                } else {
                    // 宽度相同的话，按照高度降序排列
                    return o2[1] - o1[1];

                    // if else 简化形式 => 推荐使用
                    // return o1[0] == o2[0] ? o2[1] - o1[1] : o1[0] - o2[0];

                    // KeyPoint 补充说明：为什么按照高度降序排列?
                    // 1.若高度按照升序排列，则会出现嵌套，而宽度相同是不能嵌套的
                    // [2,3],[4,5],[4,6],[6,4],[6,7]
                    // 在 3,5,6,4,7 中找最长递增子序列：4
                    // 其中 [4,5],[4,6] 都存在，则违背了宽度相同是不能嵌套的，故两者是不可能同时存在的

                    // 2.若高度按照降序排列，相同宽度的信封会淘汰掉其中一个
                    // [2,3],[4,6],[4,5],[6,7],[6,4]
                    // 在 3,6,5,7,4 中找最长递增子序列：3
                    // 因为找的是递增子序列，故 [2,3],[4,6],[4,5] 中，[4,6]，[4,5]，两者只能选择一个，
                    // 从而避免了：宽度相同是不能嵌套问题

                }
            }
        });

        int maxLen = 1;

        // 状态定义
        // dp[i] 表示以 i 对应元素结尾时，最长递增子序列的长度
        int[] dp = new int[n];

        // 状态初始化：单个元素最少有一个递增子序列元素
        Arrays.fill(dp, 1);

        // 排序后，问题转成：一维的最长的单调递增子序列
        // i 遍历每个信封
        for (int i = 1; i < n; i++) {
            // 从 0 到 i-1 位置，依次比较两个信封
            for (int j = 0; j < i; j++) {
                // 比较信封的高度
                if (envelopes[i][1] > envelopes[j][1]) {
                    dp[i] = Math.max(dp[j] + 1, dp[i]);
                    maxLen = Math.max(maxLen, dp[i]);
                }
            }
        }
        return maxLen;

        // KeyPoint 补充说明
        // 本题测试用例，修改了数据规模，改为 10^5，导致 O(n^2) 算法超时
        // 之前的输入数据规模是 5000，动态规划 O(n^2) 方案是可以的

        // 1 <= envelopes.length <= 10 ^ 5
        // envelopes[i].length == 2
        // 1 <= wi, hi <= 10 ^ 5

    }

    // 二分解法
    // 时间复杂度 O(nlogn)
    // 空间复杂度 O(n)
    public int maxEnvelopes2(int[][] envelopes) {
        int n = envelopes.length;
        if (n <= 1) return n;

        // 排序
        Arrays.sort(envelopes, new Comparator<int[]>() {
            public int compare(int[] o1, int[] o2) {
                return o1[0] == o2[0] ? o2[1] - o1[1] : o1[0] - o2[0];
            }
        });

        // res 用于存储所有可套娃信封的高度值，且保证 res 中的元素肯定是升序排列的
        List<Integer> res = new ArrayList<>();

        // 将宽度最小的信封的高度值放在 res 中
        res.add(envelopes[0][1]);

        // 从第二个信封开始，遍历并处理每一个信封
        for (int i = 1; i < n; i++) {
            // 当前信封高度
            int currHeight = envelopes[i][1];

            // KeyPoint 小技巧：通过具体数据，使用手动模拟方式，来理解陌生的代码

            // 1.若当前信封高度大于 res 中最后一个信封高度，则其放入到 res 中
            //   注意：当前信封宽度肯定 >= res 中最后一个信封的宽度，因为是按照宽度升序排列的
            if (currHeight > res.get(res.size() - 1)) {
                res.add(currHeight);
            } else {
                // 2.若当前信封高度小于等于 res 中最后一个信封高度
                // 通过二分法在 res 中查找 currHeight 合适的位置 => 找到第一个 '大于等于' currHeight 的位置
                int index = binarySearch(res, currHeight);
                res.set(index, currHeight);

                // 为什么可以将当前信封替换掉第 index 处信封?
                // 1.当前信封宽度，大于等于第 index - 1 处的信封的宽度
                // 2.当前信封高度，大于第 index - 1 处的信封的高度

                // [2,3],[4,6],[4,5],[6,7],[6,4]
                // res：3,6
                // 处理 [4,5] 时，5 <= 6，直接使用 5 替换 6，只保留一个信封
            }
        }
        return res.size();
    }

    // 二分查找：找第一个'大于等于' target 的元素的索引下标
    // 第一个'大于等于' target => 从右往左逼近
    public int binarySearch(List<Integer> list, int target) {
        int left = 0, right = list.size() - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (target > list.get(mid)) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        // 返回值，位置索引
        // while 循环结束，left == right，返回 left 或者 right 都可以
        return left;
    }
}
