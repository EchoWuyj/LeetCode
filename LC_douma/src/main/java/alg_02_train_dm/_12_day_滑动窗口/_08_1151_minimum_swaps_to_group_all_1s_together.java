package alg_02_train_dm._12_day_滑动窗口;

/**
 * @Author Wuyj
 * @DateTime 2023-05-05 20:12
 * @Version 1.0
 */
public class _08_1151_minimum_swaps_to_group_all_1s_together {
    /*
        1151. 最少交换次数 来组合所有的 1
        给出一个二进制数组 data，你需要通过交换位置，
        将数组中 任何位置 上的 1 组合到一起，并返回所有可能中所需 最少的交换次数。

        示例 1：
        输入：[1,0,1,0,1]
        输出：1
        解释：
        有三种可能的方法可以把所有的 1 组合在一起：
        [1,1,1,0,0]，交换 1 次；
        [0,1,1,1,0]，交换 2 次；
        [0,0,1,1,1]，交换 1 次。
        所以最少的交换次数为 1。

        示例 2：
        输入：[0,0,0,1,0]
        输出：0
        解释：
        由于数组中只有一个 1，所以不需要交换

        示例 3：
        输入：[1,0,1,0,1,0,0,1,1,0,1]
        输出：3
        解释：
        交换 3 次，一种可行的只用 3 次交换的解决方案是 [0,0,0,0,0,1,1,1,1,1,1]。

        提示：
        1 <= data.length <= 10^5
        0 <= data[i] <= 1

     */

    // KeyPoint 问题转化思想 => 将陌生未知题目转换成熟悉已知题目
    // 转换思路：
    // 1.没有必要真的将 1 和 0 进行交换，即不必发生交换动作
    // 2.统计数组中元素值等于 1 的个数，将 1 的个数当做窗口
    // 3.再去统计窗口中 0 的个数，即为需要交换的次数
    public static int minSwaps(int[] data) {
        if (data == null) return 0;
        // 统计数组中元素值等于 1 的个数，记作 k，同时将 k 当做窗口大小
        int k = 0;
        for (int num : data) {
            if (num == 1) k++;
        }

        // 维护窗口大小为 k 的滑动窗口
        int left = 0, right = 0;
        // 存储每个窗口中 0 的数量
        // => 即为需要将 0 交换 1 的次数，但不一定是最小的交换次数
        int cnt = 0;

        // KeyPoint 变量 minCnt 涉及比较，一定需要赋初值，否则没法比较
        // minCnt 所有窗口中最少的 0 的数量
        int minCnt = Integer.MAX_VALUE;

        int n = data.length;
        while (right < n) {
            if (data[right] == 0) cnt++;
            // 是否符合窗口条件 => 窗口大小 = k
            while (right - left + 1 == k) {
                minCnt = Math.min(minCnt, cnt);
                // 缩短串口，left 右移
                // 但 left 右移前需要判断，若 data[left] 为 0，则 cnt 需要减 1
                if (data[left] == 0) cnt--;
                left++;
            }
            right++;
        }
        // 极端情况，所有窗口可能没有 0 都是 1，即 minCnt 没有更新
        // minCnt 还是 Integer.MAX_VALUE，不需要交换，返回 0 即可
        return minCnt == Integer.MAX_VALUE ? 0 : minCnt;

        // KeyPoint
        // 定义初值 minCnt，返回前判断下，minCnt 是否已经被更新了
        // 若 minCnt 还是 Integer.MAX_VALUE，则需要特殊处理下
    }

    public static void main(String[] args) {
        int[] arr1 = {1, 0, 1, 0, 1, 0, 0, 1, 1, 0, 1};
        int[] arr2 = {1, 0, 1, 0, 1};

        System.out.println(minSwaps(arr1)); // 3
        System.out.println(minSwaps(arr2)); // 1
    }
}
