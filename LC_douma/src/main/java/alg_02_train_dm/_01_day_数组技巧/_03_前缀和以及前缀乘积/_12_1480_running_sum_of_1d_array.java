package alg_02_train_dm._01_day_数组技巧._03_前缀和以及前缀乘积;

import java.util.Arrays;

/**
 * @Author Wuyj
 * @DateTime 2023-04-17 10:09
 * @Version 1.0
 */
public class _12_1480_running_sum_of_1d_array {

    /*
        1480. 一维数组的前缀和
        给你一个数组 nums 。数组「动态和」的计算公式为：runningSum[i] = sum(nums[0]，...，ums[i])
        请返回 nums 的动态和。

        提示
        1 <= nums.length <= 1000
        -10^6 <= nums[i] <= 10^6 => 最多 1000 个 10^6，到达 10^9 大小，不会越界


        输入：nums = [1,2,3,4]
        输出：[1,3,6,10]
        解释：动态和计算过程为 [1, 1+2, 1+2+3, 1+2+3+4] 。

        输入：nums = [1,1,1,1,1]
        输出：[1,2,3,4,5]
        解释：动态和计算过程为 [1, 1+1, 1+1+1, 1+1+1+1, 1+1+1+1+1] 。

     */

    // KeyPoint 方法一 暴力
    public int[] runningSum(int[] nums) {
        int n = nums.length;
        int[] prefixSum = new int[n];
        for (int i = 0; i < n; i++) {
            int sum = 0;
            // 每次 j 都从 0 累加到 i，存在重复计算，可以优化
            for (int j = 0; j <= i; j++) {
                sum += nums[j];
            }
            prefixSum[i] = sum;
        }
        return prefixSum;
    }

    // KeyPoint 方法二 动态规划思想 => 利用中间状态值，推导新的状态值，从而消除重复计算
    public static int[] runningSum1(int[] nums) {
        int n = nums.length;
        int[] prefixSum = new int[n];
        prefixSum[0] = nums[0];
        // i 从 1 开始，存在 [i-1]，避免索引越界
        for (int i = 1; i < n; i++) {
            // 通过中间值推导，消除重复计算
            // 充分利用 prefixSum[i - 1]
            prefixSum[i] = prefixSum[i - 1] + nums[i];
        }
        return prefixSum;
    }

    // 前缀和另外一种形式(不推荐)
    public static int[] runningSum2(int[] nums) {
        int n = nums.length;
        int[] prefixSum = new int[nums.length + 1];
        prefixSum[0] = 0;
        for (int i = 1; i <= n; i++) {
            prefixSum[i] = prefixSum[i - 1] + nums[i - 1];
        }

        return prefixSum;
    }

    public static void main(String[] args) {
//        test1();
//        test2();
        test3();
        System.out.println("=====");
//        test4();
    }

    private static void test1() {
        int[] arr = new int[]{1, 2, 3, 4};
        System.out.println(Arrays.toString(runningSum1(arr))); // [1, 3, 6, 10]

        // index        0 1 2 3
        // num          1 2 3 4
        // prefixSum    1 3 6 10
        //                  ↑
        //                  i
        //           6 = 1 + 2 + 3
        //           ps[i]，包括当前 [i]

    }

    private static void test2() {
        int[] nums = new int[]{1, 2, 3, 4};
        System.out.println(Arrays.toString(nums)); // [1, 2, 3, 4]
        int n = nums.length;
        int[] prefixSum = new int[n];
        // 前缀和，直接从 nums[0] 开始
        prefixSum[0] = nums[0];
        for (int i = 1; i < n; i++) {
            prefixSum[i] = prefixSum[i - 1] + nums[i];
        }
        System.out.println(Arrays.toString(prefixSum)); // [1, 3, 6, 10]

        int k = 2;

        // index      0  1  2  3   4
        // nums      [1, 2, 3, 4]
        // prefixSum [1, 3, 6, 10]
        // 第一组两个数，没法使用 prefixSum[i + k] - prefixSum[i] 计算，
        // 第一组两个数 prefixSum[k-1] => prefixSum[1] => [0]+[1]
        // 因为第一组两个数，不使用 prefixSum[i + k] - prefixSum[i]，循环次数减 1，即为 nums.length - k
        int sum = prefixSum[k - 1];
        // 通过 flag 来控制
        boolean firstPrefix = true;
        for (int i = 0; i < nums.length - k; i++) {
            if (firstPrefix) {
                System.out.println(sum);
                firstPrefix = false;
            }
            sum = prefixSum[i + k] - prefixSum[i];
            int tmp = i + k;
            System.out.println("prefixSum[" + tmp + "]" + " - " + "prefixSum[" + i + "]" + " => " + sum);

            // prefixSum[2] - prefixSum[0] => 5
            // 本质：[0]+[1]+[2]-[0] => [1]+[2]

            // prefixSum[3] - prefixSum[1] => 7
            // 本质：[0]+[1]+[2]+[3]-([0]+[1]) => [2]+[3]
        }
    }

    // 前缀和另外一种形式
    private static void test3() {
        int[] arr = new int[]{1, 2, 3, 4};
        System.out.println(Arrays.toString(runningSum2(arr))); // [0, 1, 3, 6, 10]

        // index        0 1 2 3 4
        // num          1 2 3 4
        // prefixSum    0 1 3 6 10
        //                    ↑
        //                    i
        //           ps[i] 表达的是原数组中前 i 个元素之和
        //           ps[i]，不包括当前 [i]
        //           ps[i]，计算的是 [0]+[1] ... [i-1] 前缀和
        //           如：ps[3] =>  6 = 1 + 2 + 3

    }

    private static void test4() {
        int[] nums = new int[]{1, 2, 3, 4};
        System.out.println(Arrays.toString(nums)); // [1, 2, 3, 4]
        int n = nums.length;
        int[] prefixSum = new int[nums.length + 1];
        prefixSum[0] = 0;
        for (int i = 1; i <= n; i++) {
            prefixSum[i] = prefixSum[i - 1] + nums[i - 1];
        }
        System.out.println(Arrays.toString(prefixSum)); // [0, 1, 3, 6, 10]

        int k = 2;
        // 循环次数
        for (int i = 0; i < nums.length - k + 1; i++) {
            int sum = prefixSum[i + k] - prefixSum[i];
            int tmp = i + k;
            System.out.println("prefixSum[" + tmp + "]" + " - " + "prefixSum[" + i + "]" + " => " + sum);

            // index      0  1  2  3   4
            // nums      [1, 2, 3, 4]
            // prefixSum [0, 1, 3, 6, 10]

            // prefixSum[2] - prefixSum[0] => 3
            // 本质：[0]+[1]-0 => [0]+[1]

            // prefixSum[3] - prefixSum[1] => 5
            // 本质：[0]+[1]+[2]-[0] => [1]+[2]

            // prefixSum[4] - prefixSum[2] => 7
            // 本质：[0]+[1]+[2]+[3]-([0]+[1]) => [2]+[3]

        }
    }
}
