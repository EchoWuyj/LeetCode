package alg_02_train_dm._12_day_滑动窗口_二刷;

/**
 * @Author Wuyj
 * @DateTime 2023-07-24 11:33
 * @Version 1.0
 */
public class _01_643_maximum_average_subarray_i3 {

    // KeyPoint 优化思路
    // 1.前缀和只是使用一次，通过在滑动过程中，维护 windowSum 变量
    // 2.每次滑动后，用新计算的值将原来 windowSum 覆盖，从而实现使用一个变量代替一个数组，降低空间复杂度

    //               right
    //                 ↓
    // nums 1 12 -5 -6 50 3 -6 50 3
    //        ↑
    //       left
    //
    // windowSum 51
    // maxSum 51

    // KeyPoint 方法三 滑动窗口 => 标准代码
    // 时间复杂度：O(n)
    // => 更为精确分析：每个元素最多会被遍历到 2 次，left 和 right 分别遍历一次 O(2n) -> O(1)
    // 空间复杂度：O(1)
    public double findMaxAverage4(int[] nums, int k) {
        int maxSum = Integer.MIN_VALUE;
        // left 和 right 为左右两指针
        // left 是窗口的左边界，right 是窗口的右边界
        int left = 0, right = 0;
        // 窗口累加和
        int windowSum = 0;
        int n = nums.length;
        while (right < n) {
            // 累加 [right]
            windowSum += nums[right];
            // 判断是否满足窗口的条件：达到了固定的窗口大小
            // KeyPoint 可以将 if 替换成 while，以后要是吃不准就使用 while，肯定不会错
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

        // KeyPoint 总结 => 和滑动窗口相关的题目，都是使用这种思路
        // 1.将窗口左右边界固定 left 和 right，不断处理右边元素
        // 2.不断处理完右边元素 => 计算逻辑，再去判断窗口是否达到条件 => 固定长度，不固定长度
        // 3.若到条件，则处理窗口左侧元素，同时窗口 left 边界右移
        // 4.窗口 right 边界不断右移
    }
}
