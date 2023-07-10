package alg_02_train_dm._09_day_哈希查找_二刷;

/**
 * @Author Wuyj
 * @DateTime 2023-04-16 12:07
 * @Version 1.0
 */
public class _10_560_subarray_sum_equals_k1 {
     /*
       560 和为 K 的子数组
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

       提示：
       1 <= nums.length <= 2 * 104
       -1000 <= nums[i] <= 1000
       -107 <= k <= 107，注意：1e7 = 10^7

     */

    // KeyPoint 方法一 暴力解法 => 超出时间限制
    // O(n^3)
    public int subarraySum1(int[] nums, int k) {
        int res = 0;
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            // 注意：j 是能取到 i 的，不要将 i 遗漏了
            for (int j = i; j < n; j++) {
                int sum = 0;
                // index 指针，从 i 遍历 j，累和
                // i 起点，j 终点
                for (int index = i; index <= j; index++) {
                    sum += nums[index];
                }
                if (sum == k) res++;
            }
        }
        return res;
    }

    // 优化：暴力
    // 时间复杂度 O(n^2)
    // 空间复杂度 O(1)
    public int subarraySum2(int[] nums, int k) {
        int res = 0;
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            int sum = 0;
            // KeyPoint 注意 for 循环顺序
            // 若通过边遍历边计算累加和，来消除内层 for 循环，则 for 循环顺序只能从后往前遍历
            // 即不能每次从 0 索引开始，可能因为累加数组前面多余元素，而导致 sum != k
            for (int j = i; j >= 0; j--) {
                // j 从 i 往前遍历，直到 j = 0，边遍历边计算累加和
                // => 从而消除原来最内层 for 循环，将少一层时间复杂度
                // i 为终点，j 为起点
                sum += nums[j];
                if (sum == k) res++;
            }
        }
        return res;
    }

    // KeyPoint 存在 bug
    public static int subarraySum3(int[] nums, int k) {
        int n = nums.length;
        int count = 0;
        for (int i = 0; i < n; i++) {
            int sum = 0;
            // for 循环顺序从前往后
            //  -1 -1 1 k = 0
            // sum = -1
            // sum = -2
            // sum = -1
            // => 因为内层 for 循环，每次都是从 0 索引开始，导致额外累加了 num[0]，从而 sum != 0
            // => 其实从右往左累加，1+(-1)，sum == 0，count++
            for (int j = 0; j <= i; j++) {
                sum += nums[j];
//                System.out.println("sum = " + sum);
                if (sum == k) count++;
            }
            System.out.println();
        }
        return count;
    }
}
