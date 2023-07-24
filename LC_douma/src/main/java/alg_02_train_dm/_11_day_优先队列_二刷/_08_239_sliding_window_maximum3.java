package alg_02_train_dm._11_day_优先队列_二刷;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @Author Wuyj
 * @DateTime 2023-07-22 16:54
 * @Version 1.0
 */
public class _08_239_sliding_window_maximum3 {

    // KeyPoint 方法三 双端队列
    // 时间复杂度：O(n)
    // => 遍历 nums 数组中，每个元素最多可能被访问两次：入队访问一次，出队访问一次 => O(2n) -> O(n)
    // => 注意：从元素的角度分析，而不是从操作角度分析
    // 空间复杂度：O(n)
    public int[] maxSlidingWindow(int[] nums, int k) {

        // nums：5 3 -1 -3 -4 3 2 7
        //                 ↑  ↑
        //                 i  j
        //                    ↑ ↑
        //                    i j

        // KeyPoint 基本性质
        // 1.i < j，窗口向右移动后，如果 i 在窗口内，那么 j 肯定在窗口
        // 2.若 nums[i] < nums[j]，则 nums[i] 永远不可能是当前窗口最大值 => 将 nums[i] 删除
        //   => 只要 nums[i] >= nums[j]，则 nums[j] 可能为当前窗口最大值，需要将其保留下来

        // nums：5 3 -1 -3 -4 3 2 7，k = 3
        //                    ↑
        //                    i
        //   window [-3 -4 3] => [3]
        //              -4 < 3，满足 i < j 且 nums[i] < nums[j]，则 -4 不可能为最大值，将 -4 删除掉
        //              -3 < 3，同理，将其删除

        // res：5 3 -1 3

        // 要想实现对 window 窗口两端任意操作，能对应的数据结构 => 双端队列
        // 双端队列：在队首和队尾操作：如：追加，删除，查询，时间复杂度都是 O(1) 级别

        // Deque 在底层数据结构中：队首在左，队尾在右
        //  ← 队首 first (出队)    队尾 last (入队) ←
        //     5 3 -1
        // 双端队列里面元素：从队首到队尾，单调递减 => 单调队列
        // 每个窗口中，最大值在队首中

        int n = nums.length;
        int[] res = new int[n - k + 1];
        // Deque 中存储的是索引，通过索引判断该元素是否在窗口中，即判断 index 与 i-k 关系
        Deque<Integer> deque = new LinkedList<>();
        // for 循环 -> O(n)
        // 虽然 for 循环中存在 while 循环，但每个元素最多被访问两次，时间复杂度 O(n)
        for (int i = 0; i < n; i++) {

            // 保证队列里面最多只有 k 个元素
            while (!deque.isEmpty() && deque.peek() <= i - k) {
                deque.poll();

                // KeyPoint while 和 if
                // 1.if 可以替换成 while，但是 while 不能替换成 if
                // 2.本题这里 while 可用 if 来代替，要维护一个大小为 k 的窗口，每次最多只需要处理一个元素即可

                // KeyPoint 补充说明：peek() 和 poll() 方法
                // 1.deque.peek()
                //   peek() 等价于 peekFirst()，看队首元素，若 deque 为空，则 deque.peek() 返回 null
                // 2.deque.poll()
                //   poll() 等价于 pollFirst()，弹出队首元素，若 deque 为空，则 deque.poll() 返回 null
                //   该方法是 Queue 接口下的方法
            }

            // 若当前滑动窗口中有两个下标 i 和 j，其中 i < j 且 nums[i] < nums[j]
            // => 当滑动窗口向右移动时，只要 i 还在窗口中，那么 j 一定也还在窗口中
            // => 由于 nums[j] 的存在，nums[i] 一定不会是滑动窗口中的最大值了，我们可以将 nums[i] 永久地移除
            // KeyPoint 映射关系
            // nums[i] => nums[j]
            // deque.peekLast() => nums[i]
            while (!deque.isEmpty() && nums[i] > nums[deque.peekLast()]) {
                deque.pollLast();
            }

            // 将索引加入 deque 中
            deque.offer(i);

            // i 从 0 开始，i >= k - 1，[0,k-1]，说明窗口中已经有 k 个元素
            // 计算窗口的最大值，为 deque 的队首位置，即为 deque.peek()
            if (i >= k - 1) res[i - k + 1] = nums[deque.peek()];
        }
        return res;
    }
}
