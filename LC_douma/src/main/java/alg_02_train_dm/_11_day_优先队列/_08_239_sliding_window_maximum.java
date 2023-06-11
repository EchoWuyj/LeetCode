package alg_02_train_dm._11_day_优先队列;

import java.util.Deque;
import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 * @Author Wuyj
 * @DateTime 2023-05-23 15:32
 * @Version 1.0
 */
public class _08_239_sliding_window_maximum {

     /*
        239 号算法题：滑动窗口最大值
        给你一个整数数组 nums，有一个大小为 k 的滑动窗口，从数组的最左侧移动到数组的最右侧
        你只可以看到在滑动窗口内的 k 个数字，滑动窗口每次只向右移动一位，返回滑动窗口中的最大值

        输入：nums = [1,3,-1,-3,5,3,6,7], k = 3
        输出：[3,3,5,5,6,7]

        滑动窗口的位置                  最大值
        ----------------------------------
        [1  3  -1] -3  5  3  6  7       3
         1 [3  -1  -3] 5  3  6  7       3
         1  3 [-1  -3  5] 3  6  7       5
         1  3  -1 [-3  5  3] 6  7       5
         1  3  -1  -3 [5  3  6] 7       6
         1  3  -1  -3  5 [3  6  7]      7

        提示：
        1 <= nums.length <= 10^5
        -10^4 <= nums[i] <= 10^4
        1 <= k <= nums.length

     */

    // KeyPoint 方法一 暴力解法
    // 时间复杂度 O((n-k)*k) => 相当于是 O(n^2) => 超时
    // => 1 <= k <= nums.length
    // => 1 <= nums.length <= 10^5
    public int[] maxSlidingWindow1(int[] nums, int k) {
        int n = nums.length;
        // ans 存储每个窗口最大值，窗口的个数 n - k + 1
        // 假设最后一个窗口，start 索引为 x，n-1-x+1 = k，x = n-k
        // 从 0 ~ n-k，故一共有 n-k+1 个窗口
        int[] ans = new int[n - k + 1];
        // 遍历每个窗口
        for (int i = 0; i < n - k + 1; i++) { // O(n-k)
            int maxNum = Integer.MIN_VALUE;
            // 遍历每个窗口中 k 个元素
            for (int j = i; j < i + k; j++) { // O(k)
                maxNum = Math.max(maxNum, nums[j]);
            }
            // 分析：
            // 1.每个窗口内，存在重复计算，性能瓶颈
            // 2.获取最大值，最快的方法，就是堆(大顶堆)，堆顶就是最大值
            ans[i] = maxNum;
        }
        return ans;
    }

    // KeyPoint 方法二 优先队列(大顶堆)
    // 时间复杂度：O(nlogn)
    // 空间复杂度：O(n)
    public int[] maxSlidingWindow2(int[] nums, int k) {

        // 堆中不仅仅存储数组元素，还需要存储数组元素对应的索引，从而方便判断该元素是否还在窗口中
        // 大顶堆
        //  1.若两个元素值不想等，那么元素大的放在前面 => 大顶堆
        //  2.若两个元素值相等的话，坐标大的放在前面，这样坐标小于等于 i - k 的机会就会少点，这样删除的动作就会少发生了
        //    其实元素相等的时候哪个放在前面，哪个放在后面，都无所谓的
        PriorityQueue<int[]> pq
                = new PriorityQueue<>((a, b) -> a[0] != b[0] ? b[0] - a[0] : b[1] - a[1]);

        // pq 中 添加 k 个元素
        for (int i = 0; i < k; i++) { // O(k)
            pq.add(new int[]{nums[i], i}); // O(logn)
        }

        int[] ans = new int[nums.length - k + 1];
        // pq.peek() 返回值为 int[]，需要再确定是 [0]
        ans[0] = pq.peek()[0];

        // i 从 k 开始 => pq 中开始添加 k+1 个元素
        for (int i = k; i < nums.length; i++) { // O(n-k)
            pq.add(new int[]{nums[i], i});
            // pq 中超出窗口范围元素，将其从窗口弹出
            // 如：若 i = 4，k = 3 => 窗口范围：[2,3,4]，pq.peek()[1] <= 4-3=1，将其从窗口弹出
            while (pq.peek()[1] <= i - k) {
                pq.remove(); // O(logn) 堆里面的元素个数有可能是 n 个，因为只是 remove 堆顶，而堆顶以下没有操作
            }
            // i 从 k 开始，ans[1] = 当前窗口最大值
            // ans 最极限状态，ans[n-k+1]
            ans[i - k + 1] = pq.peek()[0];
        }

        return ans;
    }

    // KeyPoint 方法三 双端队列 => 对队首和队尾操作，追加，删除，查询，时间复杂度都是 O(1) 级别
    // 基本性质：
    //  1.i < j，窗口向右移动后，如果 i 在窗口内，那么 j 肯定在窗口
    //  2.若 nums[i] < nums[j]，则 nums[i] 永远不可能是当前窗口最大值
    // 时间复杂度：O(n)
    // 空间复杂度：O(n)
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        int[] ans = new int[n - k + 1];
        // Deque 中存储的是索引，通过索引判断该元素是否在窗口中
        Deque<Integer> deque = new LinkedList<>();
        // for 循环 -> O(n)
        for (int i = 0; i < nums.length; i++) {
            // while 循环中，每个元素最多可能被访问两次，从元素的角度分析，而不是从操作角度分析，O(2n) -> O(n)
            // 保证队列里面最多只有 k 个元素
            while (!deque.isEmpty() && deque.peek() <= i - k) {
                // deque.peek() 看队首元素，若 deque 为空，则 deque.peek() 返回 null
                // deque.poll() 队首出队 => Queue 接口下的方法
                deque.poll();
                // 补充说明：这里的 while 可以使用 if 来代替，要维护一个大小为 k 的窗口的话，每次最多只需要处理一个元素即可
            }

            // 示意图
            // 注意：在底层数据结构中，队首在左，队尾在右
            //  ← 队首 first (出队)    队尾 last (入队) ←
            //     5 3 -1
            //  双端队列里面元素：从队首到队尾，单调递减 => 单调队列
            //  每个窗口中，最大值在队首中
            while (!deque.isEmpty() && nums[i] > nums[deque.peekLast()]) {
                // 如果当前的滑动窗口中有两个下标 i 和 j，其中 i 在 j 的左侧（i < j）
                // 并且 i 对应的元素不大于 j 对应的元素，即 nums[i] ≤ nums[j]
                // 当滑动窗口向右移动时，只要 i 还在窗口中，那么 j 一定也还在窗口中，
                // 由于 nums[j] 的存在，nums[i] 一定不会是滑动窗口中的最大值了，我们可以将 nums[i] 永久地移除
                deque.pollLast();
            }
            deque.offer(i);
            // i 从 0 开始，i >= k - 1，则说明窗口中已经有 k 个元素，计算窗口的最大值，即为 deque.peek()
            if (i >= k - 1) ans[i - k + 1] = nums[deque.peek()];
        }
        return ans;
    }
}
