package alg_02_train_dm._10_day_栈和队列;

import java.util.ArrayDeque;

/**
 * @Author Wuyj
 * @DateTime 2023-04-26 12:08
 * @Version 1.0
 */
public class _08_739_daily_temperatures {
      /*
        739 号算法题：每日温度
        请根据每日气温列表，重新生成一个列表。
        对应位置的输出为：要想观测到更高的气温，至少需要等待的天数。
        如果气温在这之后都不会升高，请在该位置用 0 来代替。

        输入：temperatures = [73, 74, 75, 71, 69, 72, 76, 73]
        输出：[1, 1, 4, 2, 1, 1, 0, 0]

        index   0  1  2  3  4  5  6  7
        value   73 74 75 71 69 72 76 73
                      ↑            ↑

        提示：
        1 <= temperatures.length <= 10^5
        30 <= temperatures[i] <= 100

        KeyPoint 数据规模与时间复杂度
        10^5 => O(n^2) 超时

     */

    // KeyPoint 方法一 暴力解法 => 超时
    // 时间复杂度 O(n^2)
    public int[] dailyTemperatures1(int[] T) {
        if (T.length == 1) return new int[]{0};
        int n = T.length;
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                // 剩余元素中，找到第一个，大于 T[i]，更新 res
                if (T[j] > T[i]) {
                    // 计算间隔天数，不包括一端，故直接索引相减 j - i
                    res[i] = j - i;
                    // 为保证是第一个大于 T[i]，故需要 break
                    // 否则若继续遍历， res[i] 值可能会覆盖
                    break;
                }
            }
        }
        return res;
    }

    // KeyPoint 方法二 单调栈
    // 抽象 => 找出数组中右边第一个比我大的元素
    // 时间复杂度 O(n)
    // 空间复杂度 O(n)
    public int[] dailyTemperatures(int[] T) {
        int n = T.length;
        if (n == 1) return new int[]{0};
        int[] res = new int[n];
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            int t = T[i];
            // 单调递减栈
            // => 看栈中索引值对应元素值递增或递减关系，而不是索引值
            while (!stack.isEmpty() && t > T[stack.peek()]) {
                int top = stack.pop();
                // 间隔天数
                res[top] = i - top;
            }
            stack.push(i);
        }
        // 剩余在 stack 中索引，不用额外处理，java 中默认赋值就是 0
        return res;
    }
}
