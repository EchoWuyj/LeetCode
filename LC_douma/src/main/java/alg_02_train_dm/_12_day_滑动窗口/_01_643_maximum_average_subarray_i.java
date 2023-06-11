package alg_02_train_dm._12_day_滑动窗口;

/**
 * @Author Wuyj
 * @DateTime 2023-05-04 10:23
 * @Version 1.0
 */
public class _01_643_maximum_average_subarray_i {
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

    // KeyPoint 方法一 暴力解法
    // 时间复杂度：O(nk) 超时
    public double findMaxAverage1(int[] nums, int k) {
        int maxSum = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length - k + 1; i++) {
            int sum = 0;
            // i 到 i+k，一共 k 个数，进行累和 => 存在重复计算
            for (int j = i; j < i + k; j++) {
                sum += nums[j];
            }
            maxSum = Math.max(maxSum, sum);
        }
        // double 类型的默认精度是 16 位，也就是小数点后 16 位数
        return (double) maxSum / k;

        // 若想使得 double 保留 2 位小数
//        double num = 3.14159;
//        double precision = 2; // 指定精度为 2 位小数
//        double result = Math.round(num * Math.pow(10, precision)) / Math.pow(10, precision);
//        System.out.println(result); // 3.14
    }

    // KeyPoint 方法二 前缀和优化(空间换时间)
    // 时间复杂度：O(n)
    // 空间复杂度：O(n)
    public double findMaxAverage2(int[] nums, int k) {

        // 前缀和 => 消除重复计算，降低时间复杂度
        // num          1 12 -5 -6 50 3 -6
        // prefixSum  0 1 13  8  2 52
        //            ↑          ↑
        //            i         i+k

        // 前缀和另外一种形式
        int[] prefixSum = new int[nums.length + 1];
        prefixSum[0] = 0;
        for (int i = 1; i <= nums.length; i++) {
            // prefixSum[i] 表达的是原数组中前 i 个元素之和
            prefixSum[i] = prefixSum[i - 1] + nums[i - 1];
        }

        int maxSum = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length - k + 1; i++) {
            // KeyPoint 优化思路：
            // 1.前缀和只是使用一次，通过在滑动过程中维护 windowSum 变量
            // 2.每次滑动后，用新计算的值将原来 windowSum 覆盖，从而实现使用一个变量代替一个数组，降低空间复杂度
            int sum = prefixSum[i + k] - prefixSum[i];
            maxSum = Math.max(maxSum, sum);
        }
        // double 计算比 int 要慢，所以中间记录的值要设成 int 型，最后返回的时候再转换成 double
        return (double) maxSum / k;
    }

    // 前缀和另外一种方式
    public double findMaxAverage3(int[] nums, int k) {
        int n = nums.length;
        int[] prefixSum = new int[n];
        // KeyPoint 从 nums[0]，开始而不是从 0 开始
        prefixSum[0] = nums[0];
        for (int i = 1; i < n; i++) {
            prefixSum[i] = prefixSum[i - 1] + nums[i];
        }

        // index      0  1  2  3   4
        // nums      [1, 2, 3, 4]
        // prefixSum [1, 3, 6, 10]
        // 关键：第一组两个数，没法使用 prefixSum[i + k] - prefixSum[i] 计算，
        //       第一组两个数 prefixSum[k-1]

        int maxSum = prefixSum[k - 1];

        // 通过 flag 来控制，也是一种方式
//        boolean firstPrefix = true;

        // 因为第一组两个数，不使用 prefixSum[i + k] - prefixSum[i] 计算
        // 故循环次数减 1，即为 nums.length - k
        for (int i = 0; i < n - k; i++) {
            int sum = prefixSum[i + k] - prefixSum[i];
            maxSum = Math.max(maxSum, sum);
        }

        return (double) maxSum / k;
    }

    // KeyPoint 方法三 滑动窗口 => 标准代码
    // 时间复杂度：O(n) => 精确分析：每个元素最多会被遍历到 2 次，left 和 right 分别遍历一次 O(2n)
    // 空间复杂度：O(1)
    public double findMaxAverage4(int[] nums, int k) {
        int maxSum = Integer.MIN_VALUE;
        // left 是窗口的左边界，right 是窗口的右边界
        int left = 0, right = 0;
        // 窗口累加和
        int windowSum = 0;
        while (right < nums.length) {
            // 累加 [right]
            windowSum += nums[right];
            // 判断是否满足窗口的条件：达到了固定的窗口大小
            if (right - left + 1 == k) {
                // 窗口内比较
                maxSum = Math.max(maxSum, windowSum);
                // windowSum -[left]，先移动 left 指针
                windowSum -= nums[left];
                // right 和 left 都是 ++，都是往右移动
                left++;
            }
            // 再去移动 right 指针
            right++;
        }
        // (double) maxSum 是一个整体，再去 / k
        // 先将 maxSum 转 double，再去进行相除，避免省略小数
        return (double) maxSum / k;

        // KeyPoint 总结：和滑动窗口相关的题目，都是使用这种思路
        // 1.将窗口左右边界固定 left 和 right，不断处理右边元素
        // 2.处理完右边元素，再去判断窗口是否达到条件
        // 3.若到条件，则处理窗口左侧元素，同时窗口 left 边界右移
        // 4.窗口 right 边界不断右移
    }
}
