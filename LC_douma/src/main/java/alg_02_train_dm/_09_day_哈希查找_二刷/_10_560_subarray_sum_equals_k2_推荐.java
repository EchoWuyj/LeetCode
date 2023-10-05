package alg_02_train_dm._09_day_哈希查找_二刷;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author Wuyj
 * @DateTime 2023-07-06 21:17
 * @Version 1.0
 */
public class _10_560_subarray_sum_equals_k2_推荐 {

    // KeyPoint 方法二 前缀和 + 线性查找
    // 使用前缀和，避免在计算区间和的过程中，进行重复计算
    // 时间复杂度 O(n^2)
    // 空间复杂度 O(n)
    public int subarraySum2(int[] nums, int k) {

        // KeyPoint 补充说明：前缀和

        // 1.构建前缀和，定义 prefixSum[0] = 0

        //   nums         2 1 4 3   9  6
        //   prefixSum    0 2 3 7  10 19 25
        //                     i-1  i
        //   prefixSum[i] = prefixSum[i-1] + nums[i-1];

        //   nums         2 1 4 3  9  6
        //   prefixSum    0 2 3 7 10 19 25
        //                  j      i
        //   4 + 3 + 9 = prefixSum[i] - prefixSum[j]

        // 2.加深对 prefixSum 长度的理解
        //   nums        index    0 1 2 3 4
        //   prefixSum   index    0 1 2 3 4 5
        //   prefixSum 长度比 nums 多 1

        // 总结：
        // 1.遍历 prefixSum，for 循环条件，最好使用 i < prefixSum.length
        //   不建议使用 i <= nums.length
        // 2.因为 prefixSum 和 nums 长度不一样，在 for 的条件判断中，有取等和不取等的区别

        // 3.结合本题
        //  prefixSum[i] - prefixSum[j] == k => prefixSum[i] - k == prefixSum[j]
        //  遍历 prefixSum 数组，查找是否有这样的 prefixSum[j] 即可

        int n = nums.length;
        int[] prefixSum = new int[n + 1];
        prefixSum[0] = 0;

        // 构建前缀和
        // 1.存在 i-1，故 i 从 1 开始
        // 2.prefixSum 长度为 n+1，故 i < n+1
        //   或者 i <= n，其中 i 是可以取等的
        for (int i = 1; i < n + 1; i++) {
            prefixSum[i] = prefixSum[i - 1] + nums[i - 1];
        }

        int res = 0;
        // prefixSum 下标索引 i 还是是从 0 开始的
        // prefixSum[0]，表示 num[0] 的前缀和
        // 不能从 1 开始，否则就漏掉 num[0] 的前缀和
        for (int i = 0; i < n + 1; i++) {
            // diff 即为 prefixSum[j] 的值
            int diff = prefixSum[i] - k;
            // 线性查找
            for (int j = 0; j < i; j++) {
                if (prefixSum[j] == diff) res++;
            }
        }
        return res;
    }

    // KeyPoint 优化：前缀和 + 哈希查找 => 推荐
    // 时间复杂度 O(n)
    // 空间复杂度 O(n)
    public int subarraySum3(int[] nums, int k) {
        int n = nums.length;
        int[] prefixSum = new int[n + 1];
        prefixSum[0] = 0;
        for (int i = 1; i < n + 1; i++) {
            prefixSum[i] = prefixSum[i - 1] + nums[i - 1];
        }

        // map 存储 i 索引之前，所有 prefixSum[k] 出现的次数
        // => prefixSum[0] ~ prefixSum[i-1] 出现次数
        // key => prefixSum[k]，其中 k 范围 [0,i-1]
        // value => 次数
        Map<Integer, Integer> map = new HashMap<>();

        int res = 0;
        // i 遍历前缀和数组 prefixSum，范围从 0 到 n
        for (int i = 0; i < n + 1; i++) {
            int diff = prefixSum[i] - k;
            // 哈希查找
            // => 当索引为 i，则当前索引 i 对应的 prefixSum[i] 没有存储
            //    只能从 prefixSum[0] ~ prefixSum[i-1] 范围查找
            if (map.containsKey(diff)) {
                res += map.get(diff);
            }
            // 存储当前索引 i 之前的 prefixSum，即 prefixSum[0] ~ prefixSum[i-1]
            // 同时在本轮遍历结束前，也将 prefixSum[i] 存入 map 中
            map.put(prefixSum[i], map.getOrDefault(prefixSum[i], 0) + 1);
        }
        return res;
    }

    // KeyPoint 优化：prefixSum 数组 => 降低空间复杂度
    // 在 for 循环中 prefixSum[i] 只是使用一次，并没有重复使用，
    // 可以使用一个变量进行代替，不需要 prefixSum 数组
    // 时间复杂度 O(n)
    // 空间复杂度 O(n)
    public int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        // 若 k = prefixSum，即 diff = 0，也是成立的
        // 故需要 prefixSum[0] = 0 单独加入一次，即 map.put(0,1)
        map.put(0, 1);
        int prefixSum = 0;
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            // 前缀和 => 只是使用一次，没有重复使用
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
