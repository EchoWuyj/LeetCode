package alg_02_train_dm._11_day_优先队列_二刷;

import java.util.PriorityQueue;

/**
 * @Author Wuyj
 * @DateTime 2023-07-22 16:53
 * @Version 1.0
 */
public class _08_239_sliding_window_maximum2 {

    // KeyPoint 方法二 优先队列(大顶堆)
    // 时间复杂度：O(nlogn) => O(k*logn) + O((n-k)*logn) 整体合并 O(nlogn)
    // 空间复杂度：O(n)
    public int[] maxSlidingWindow2(int[] nums, int k) {

        // KeyPoint 优化
        // 窗口中获取最大值 => 大顶堆，堆顶维护最大值
        // 注意：
        // 堆中不仅仅存储数组元素，还需要存储数组元素对应的索引，从而方便判断该元素是否还在窗口中
        // => 将两个信息整体封装成数组形式存到堆中

        // 大顶堆比较逻辑
        // 1.若两个元素值不相等，那么元素大的放在前面 => 大顶堆
        // 2.若两个元素值相等，坐标大的放在前面
        //    => 这样坐标小于等于 i-k 的机会就会少点，从而删除动作就会少发生了
        //    => 非必须选项，其实元素相等时，哪个放在前面，哪个放在后面，都可以的
        PriorityQueue<int[]> maxHeap
                = new PriorityQueue<>((a, b) -> a[0] != b[0] ? b[0] - a[0] : b[1] - a[1]);

        // 向 maxHeap 中，添加 k 个元素
        for (int i = 0; i < k; i++) { // O(k)
            maxHeap.add(new int[]{nums[i], i}); // O(logn)
        }

        int n = nums.length;
        // 一共有 n-k+1 个窗口，故结果集大小为 n-k+1
        int[] res = new int[n - k + 1];
        // maxHeap.peek() 返回值为 int[]，需要再确定是 [0]
        res[0] = maxHeap.peek()[0];

        // KeyPoint 优先通过打印输出来找 bug，这样的方式解决 bug 的效率很高
        // System.out.println(maxHeap.peek()[0]);

        // i 从 k 开始
        // => maxHeap 中开始添加 k+1 个元素
        for (int i = k; i < n; i++) { // O(n-k)
            maxHeap.add(new int[]{nums[i], i});
            // maxHeap 堆顶元素，若超出窗口范围元素，将其从窗口弹出
            // 如：若 i = 4，k = 3 => 则窗口范围：[2,3,4] => maxHeap.peek()[1] <= i-k = 4-3 = 1，将其从窗口弹出
            // KeyPoint 使用 while 判断，而不是使用 if 判断
            // 注意 maxHeap 中容量没有限制，maxHeap 可能存在多个较大值，maxHeap 在 remove 之后，
            // 上浮到堆顶的元素可能同样满足 <= i-k，故需要使用 while 循环，而不是 if 判断
            // KeyPoint 区别：数字 1 和 字母 i，两者不要相混淆 => 经常容易犯错，务必要小心！
            while (maxHeap.peek()[1] <= i - k) {
                //  O(logn) 堆里面的元素个数有可能是 n 个，因为只是 remove 堆顶，而堆顶以下没有操作
                maxHeap.remove();
            }

            // KeyPoint 注意实现
            // 1.for 循环中，循环赋值，涉及循环变量 i，不是固定值
            // 2.i 从 k 开始，即 res 数组从 res[1] 开始赋值，最后一个值，当 i = n-1，即 res[n-1-k+1] = res[n-k]
            //   res 数组中一共有 n-k+1 个元素，但最大索引下标 res[n-k]
            res[i - k + 1] = maxHeap.peek()[0];
        }
        return res;
    }
}
