package alg_02_train_dm._17_day_二叉树二_二刷;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author Wuyj
 * @DateTime 2023-08-11 14:38
 * @Version 1.0
 */
public class _07_437_path_sum_iii2 {

    // KeyPoint 前缀和 => Map 实现

    // 函数功能：在一个数组 nums 中，求连续子数组之和 [区间和 ⇄ 路径和] 等于 target 的连续子数组的个数
    // => 通过使用前缀和的思想，避免了重复计算路径的过程
    public static int prefixSum(int[] nums, int target) {

        // index       0 1 2 3 4  5
        // num         1 2 3 4 5
        // prefixSum   0 1 3 6 10 15

        // map
        // curSum  count
        //  0   =>   1
        //  1   =>   1
        //  3   =>   1
        //  6   =>   1
        //  10  =>   1
        //  15  =>   1

        // prefixSum[j] - prefixSum[i] = target
        // => prefixSum[j] - target = prefixSum[i]
        // 不断将 curSum ⇄ prefixSum[j] 加入 map 的同时，判断 map 中是否存在 prefixSum[i]
        // 因为 map 已经存储之前所有的 curSum 的状态

        // 如：target = 5
        // curSum = 6 => curSum - target = 1，map 存在 1，且 count = 1，res += count = 1
        // curSum = 15 => curSum - target = 10，map 存在 10，且 count = 1，res += count = 2

        // KeyPoint 区别：构建前缀和数组方式，本题将前缀和存在 Map 中
        // map 前缀和映射
        // key => prefixSum 前缀和[路径和]
        // value => 出现次数
        Map<Integer, Integer> map = new HashMap<>();
        // 单独添加 prefixSum = 0
        map.put(0, 1);
        int curSum = 0;
        int res = 0;
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            // 累加路径和
            curSum += nums[i];
            // 计算 res
            res += map.getOrDefault(curSum - target, 0);
            // map 更新 新累加路径和
            // => curSum 和 count
            map.put(curSum, map.getOrDefault(curSum, 0) + 1);
        }
        return res;

        // KeyPoint 总结
        // 1.对于一段新的代码，从操作逻辑上对其理解，对其有宏观的认识
        // 2.通过从详细的模拟过程，加强对代码边界条件的理解
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};
        System.out.println(prefixSum(arr, 5)); // 2
    }
}
