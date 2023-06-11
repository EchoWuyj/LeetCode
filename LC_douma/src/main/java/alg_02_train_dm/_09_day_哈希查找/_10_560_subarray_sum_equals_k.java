package alg_02_train_dm._09_day_哈希查找;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author Wuyj
 * @DateTime 2023-04-16 12:07
 * @Version 1.0
 */
public class _10_560_subarray_sum_equals_k {
     /*
       560 号算法题：和为K的子数组
       给定一个整数数组和一个整数 k，
       你需要找到该数组中和为 k 的'连续的子数组'的个数

       输入:nums = [1,1,1], k = 2
       输出: 2
       解释：[1,1,1] 和 [1,1,1]
             ↑ ↑          ↑ ↑  => 必须确保是连续的

       输入:nums = [0,1,-1,1,1,2], k = 0
       输出: 4
        0
        0,1,-1
        1,-1,
        -1,1

       提示
        - 数组的长度为 [1, 20,000]。
        - 数组中元素的范围是 [-1000, 1000] ，且整数 k 的范围是 [-1e7, 1e7]，1e7 = 10^7
     */

    // KeyPoint 方法一 暴力解法 => 超出时间限制
    // O(n^3)
    public int subarraySum1(int[] nums, int k) {
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i; j < nums.length; j++) {
                int sum = 0;
                // m 指针，从 i 遍历 j，累和
                // i 起点，j 终点
                for (int m = i; m <= j; m++) {
                    sum += nums[m];
                }
                if (sum == k) res++;
            }
        }
        return res;
    }

    // 优化：暴力
    // 时间复杂度 O(n^2)
    // 空间复杂度 O(1)
    public int subarraySum5(int[] nums, int k) {
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            int sum = 0;
            for (int j = i; j >= 0; j--) {
                // j 从 i 往前遍历，直到 j = 0，边遍历边计算累加和
                // i 为终点，j 为起点
                sum += nums[j];
                if (sum == k) res++;
            }
        }
        return res;
    }

    // KeyPoint 方法二 优化：前缀和 + 线性查找
    // 使用前缀和，避免在计算区间和的过程中，进行重复计算
    // 时间复杂度 O(n^2)
    // 空间复杂度 O(n)
    public int subarraySum2(int[] nums, int k) {

        // nums         2 1 4 3  9  6
        // prefixSum  0 2 3 7 10 19 25
        //                j      i
        // 4 + 3 + 9 = prefixSum[i] - prefixSum[j]

        // prefixSum[i] - prefixSum[j] == k
        // => prefixSum[i] - k == prefixSum[j]
        // 遍历 prefixSum 数组，查找是否有这样的 prefixSum[j] 即可

        int[] prefixSum = new int[nums.length + 1];
        prefixSum[0] = 0;

        // 构建前缀和
        // 存在 i-1，故 i 从 1 开始
        // i <= nums.length，其中 i 是可以取等的
        for (int i = 1; i <= nums.length; i++) {
            prefixSum[i] = prefixSum[i - 1] + nums[i - 1];
        }

        // KeyPoint 加深对 prefixSum 长度的理解
        // nums        0 1 2 3 4
        // prefixSum 0 1 2 3 4 5
        // prefixSum 长度比 nums 多 1

        // 遍历 prefixSum，for 循环条件，最好使用 i < prefixSum.length
        // 而不使用 i <= nums.length => 虽然可以使用，但是不建议
        // 因为 prefixSum 和 nums 长度不一样，在 for 的条件判断中，有取等和不取等的区别。

        int res = 0;
        // prefixSum 下标索引，还是是从 0 开始的
        // prefixSum[0]，表示 num[0] 的前缀和，不能从 1 开始，否则就漏掉 num[0] 的前缀和
        for (int i = 0; i <= nums.length; i++) {
            int diff = prefixSum[i] - k;
            // 线性查找
            for (int j = 0; j < i; j++) {
                if (prefixSum[j] == diff) res++;
            }
        }
        return res;
    }

    // 优化：前缀和 + 哈希查找
    // 时间复杂度 O(n)
    // 空间复杂度 O(n)
    public int subarraySum3(int[] nums, int k) {
        int[] prefixSum = new int[nums.length + 1];
        prefixSum[0] = 0;
        for (int i = 1; i <= nums.length; i++) {
            prefixSum[i] = prefixSum[i - 1] + nums[i - 1];
        }

        // map 存储 i 之前所有 prefixSum 出现的次数
        Map<Integer, Integer> map = new HashMap<>();
        int res = 0;
        // i < prefixSum.length，则 i 是不取等的
        for (int i = 0; i < prefixSum.length; i++) {
            // KeyPoint 优化：prefixSum[i]
            // prefixSum[i] 只是使用一次，并没有重复使用，可以使用一个变量进行代替，不需要数组 prefixSum
            int diff = prefixSum[i] - k;
            // 哈希查找
            if (map.containsKey(diff)) {
                res += map.get(diff);
            }
            // put 之前先去判断是否 containsKey，没有 key，再将 prefixSum[i] 进行 put
            map.put(prefixSum[i], map.getOrDefault(prefixSum[i], 0) + 1);
        }

        return res;
    }

    // 优化：prefixSum，降低空间复杂度
    // 时间复杂度 O(n)
    // 空间复杂度 O(n)
    public int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        // 若 prefixSum = k，即 diff = 0，也是成立的，故需要 prefixSum[0] = 0 单独加入一次
        map.put(0, 1);

        int prefixSum = 0;
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            prefixSum += nums[i];
            int diff = prefixSum - k;
            // 哈希查找
            if (map.containsKey(diff)) {
                res += map.get(diff);
            }
            map.put(prefixSum, map.getOrDefault(prefixSum, 0) + 1);
        }

        return res;
    }
}
