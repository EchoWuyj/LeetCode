package alg_02_train_dm._12_day_滑动窗口;

/**
 * @Author Wuyj
 * @DateTime 2023-05-04 10:23
 * @Version 1.0
 */
public class _01_643_maximum_average_subarray_i1 {
     /*
        643. 子数组最大平均数 I
        给定 n 个整数，找出平均数最大且长度为 k 的连续子数组，并输出该最大平均数。

        示例：
        输入：[1,12,-5,-6,50,3], k = 4
        输出：12.75
        解释：最大平均数 (12-5-6+50)/4 = 51/4 = 12.75
        2、51、42

        提示：
        1 <= k <= n <= 30,000。
        所给数据范围 [-10,000，10,000]。

     */

    // KeyPoint 涉及连续子数组的问题，通常有两种思路：一是滑动窗口、二是前缀和
    // KeyPoint 方法一 暴力解法
    // 时间复杂度：O(nk) 超时
    public double findMaxAverage1(int[] nums, int k) {
        int maxSum = Integer.MIN_VALUE;
        int n = nums.length;
        for (int i = 0; i < n - k + 1; i++) {  // O(n-k+1)
            int sum = 0;
            // i 到 i+k，一共 k 个数，进行累和
            // => 累和中存在重复计算
            // => 使用前缀和优化
            for (int j = i; j < i + k; j++) {
                sum += nums[j];
            }
            maxSum = Math.max(maxSum, sum);
        }

        // KeyPoint 注意：double 计算比 int 要慢
        // => 所以中间记录的值要设成 int 型，最后返回再转换成 double

        // KeyPoint 返回值前，先转 double
        // 最后返回值要求是 double 数据类型，需要强转 (double)
        // double 类型的默认精度是 16 位，也就是小数点后 16 位数

        // KeyPoint 易错点
        // 1.先将 maxSum 转 double，再去执行 maxSum / k，整体过程是 double / double
        // 2. 若 (double)(maxSum / k)，则 maxSum / k 还是 int / int， 只是将结果转成 double，中间相除过程存在精度损失
        return (double) maxSum / k;
    }

    public static void main(String[] args) {
        // KeyPoint 补充说明
        // 若想使得 double 保留 2 位小数
        double num = 3.14159;
        // 指定精度为 2 位小数
        double precision = 2;
        // round 表示 "四舍五入"，向右取整
        // Math.round(11.5) // 12
        // Math.round(-11.5) // -11
        double result = Math.round(num * Math.pow(10, precision)) / Math.pow(10, precision);
        System.out.println(result); // 3.14
    }
}
