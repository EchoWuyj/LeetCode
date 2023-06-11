package alg_02_train_dm._12_day_滑动窗口;

/**
 * @Author Wuyj
 * @DateTime 2023-05-05 20:12
 * @Version 1.0
 */
public class _08_1151_minimum_swaps_to_group_all_1s_together {
    /*
        leetcode 1151. 最少交换次数来组合所有的 1
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
        由于数组中只有一个 1，所以不需要交换。

        示例 3：
        输入：[1,0,1,0,1,0,0,1,1,0,1]
        输出：3
        解释：
        交换 3 次，一种可行的只用 3 次交换的解决方案是 [0,0,0,0,0,1,1,1,1,1,1]。

        提示：
        1 <= data.length <= 10^5
        0 <= data[i] <= 1

     */

    // 转换思路：没有必要真的将 1 和 0 进行交换，即不必发生交换动作
    // 统计数组中元素值等于 1 的个数，将 1 的个数当做窗口，再去统计窗口中 0 的个数，即为需要交换的次数
    public static int minSwaps(int[] data) {
        if (data == null) return 0;
        // 统计数组中元素值等于 1 的个数
        int k = 0;
        for (int x : data) {
            if (x == 1) k++;
        }

        // 维护窗口大小为 k 的滑动窗口
        int left = 0, right = 0;

        // 存储每个窗口中 0 的数量
        int windowZeroCnt = 0;

        // minZeroCnt 所有窗口中最少的 0 的数量
        // minZeroCnt 一开始涉及比较，必须得给赋初值，否则没法比较
        int minZeroCnt = Integer.MAX_VALUE;

        while (right < data.length) {
            if (data[right] == 0) windowZeroCnt++;
            // 是否符合窗口条件 => 窗口大小 = k
            if (right - left + 1 == k) {
                minZeroCnt = Math.min(minZeroCnt, windowZeroCnt);
                // left 将 0 移动出窗口，windowZeroCnt 减 1
                if (data[left] == 0) windowZeroCnt--;
                left++;
            }
            right++;
        }
        // 所有窗口可能没有 0，即 minZeroCnt 没有更新，还是 Integer.MAX_VALUE，不需要交换，返回 0 即可
        return minZeroCnt == Integer.MAX_VALUE ? 0 : minZeroCnt;
    }

    public static void main(String[] args) {
        int[] arr1 = {1, 0, 1, 0, 1, 0, 0, 1, 1, 0, 1};
        int[] arr2 = {1, 0, 1, 0, 1};

        System.out.println(minSwaps(arr1)); // 3
        System.out.println(minSwaps(arr2)); // 1
    }
}
