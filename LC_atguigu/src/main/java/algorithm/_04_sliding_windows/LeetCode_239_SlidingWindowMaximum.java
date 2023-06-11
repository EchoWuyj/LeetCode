package algorithm._04_sliding_windows;

import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @Author Wuyj
 * @DateTime 2022-03-04 13:32
 * @Version 1.0
 */
public class LeetCode_239_SlidingWindowMaximum {
    // 方法一:暴力法,遍历每一个窗口,对每个窗口遍历每个元素求最大值
    public int[] maxSlidingWindow01(int[] nums, int k) {
        // 定义一个结果数组,总共有n-k+1个窗口
        // 其中每个窗口都有个最大值,所以需要你使用这么长的数组来存储
        int[] result = new int[nums.length - k + 1];

        // 遍历数组,从0到n-k,作为滑动窗口的起始位置
        // 数组的长度-滑动窗口的长度=滑动窗口的起始位置的索引
        // 注意i是取到nums.length - k
        for (int i = 0; i <= nums.length - k; i++) {
            // 找窗口内的最大值,定义一个变量来保存
            int max = nums[i];
            // 遍历窗口中的每一个元素,比较大小
            for (int j = i + 1; j < i + k; j++) {
                if (nums[j] > max) {
                    max = nums[j];
                }
            }
            result[i] = max;
        }
        return result;
    }

    // 方法二:使用大顶堆
    public int[] maxSlidingWindow02(int[] nums, int k) {
        // 定义一个结果数组
        int[] result = new int[nums.length - k + 1];

        // 用优先队列实现一个大顶堆
        // k为队列的大小,默认是小顶堆,想要实现大顶堆需要传入比较器
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(k, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                // 倒序则是后减前,正序则是前减后
                return o2 - o1;
            }
        });

        // 准备工作:构建大顶堆,将第一个窗口元素（前k个）放入堆中
        for (int i = 0; i < k; i++) {
            // 通过大顶堆的数据结构会自动实现大的元素上浮,小的元素下沉
            maxHeap.add(nums[i]);
        }

        // 当前大顶堆的堆顶元素就是第一个窗口的最大值
        result[0] = maxHeap.peek();

        // 遍历所有窗口
        // 从第二窗口开始即可
        for (int i = 1; i <= nums.length - k; i++) {
            // 删除堆中上一个窗口的第一个元素
            maxHeap.remove(nums[i - 1]);
            // 添加当前窗口的最后一个元素进堆
            // KeyPoint 数学原理:数组始末索引0和n-1,项数=(n-1-0)+1=n
            // 当前元素索引为i,窗口长度为k,则窗口最后一个元素索引为i+k-1
            maxHeap.add(nums[i + k - 1]);
            result[i] = maxHeap.peek();
        }
        return result;
    }

    // 方法三:使用双向队列
    public int[] maxSlidingWindow03(int[] nums, int k) {
        // 定义一个结果数组
        int[] result = new int[nums.length - k + 1];

        // 定义双向队列,保存元素的索引
        // 在队前进行删除操作,在对尾进行添加和删除操作,所以使用双向队列
        ArrayDeque<Integer> deque = new ArrayDeque<>();

        // 初始化双向队列,处理第一个窗口的数据
        for (int i = 0; i < k; i++) {
            // 如果队尾元素小于当前元素,直接删除
            // 且不停地往前判断,只要有比当前元素小的元素即全部删除
            while (!deque.isEmpty() && nums[i] > nums[deque.getLast()]) {
                deque.removeLast();
            }
            // 往队尾添加元素
            deque.addLast(i);
        }

        // 第一个窗口的最大值
        result[0] = nums[deque.getFirst()];

        // 此时已经不需要遍历窗口了,因为从已经将索引0到k-1元素都已经判断了,当前的最大值已经有了
        // 现在入队和出队不需要从第2个元素开始判断起了,从没有判断的新元素k开始判断,k元素是窗口的最后一个位置
        for (int i = k; i < nums.length; i++) {
            // 判断如果上一个窗口删掉的就是窗口最大值,那么需要将队列中的最大值删掉
            // 当前新增的元素为i,该元素为双端队列的最后一个元素,则上个窗口的第一元素索引就是i-k
            if (!deque.isEmpty() && deque.getFirst() == i - k) {
                deque.removeFirst();
            }
            // 判断新增元素是否可以删除队尾元素
            while (!deque.isEmpty() && nums[i] > nums[deque.getLast()]) {
                deque.removeLast();
            }
            deque.addLast(i);
            // 保存结果,将双向队列的第一元素(最大的元素)保存到窗口第一个元素的位置
            result[i - k + 1] = nums[deque.getFirst()];
        }
        return result;
    }

    // 方法四:左右扫描
    public int[] maxSlidingWindow04(int[] nums, int k) {
        int n = nums.length;
        // 定义一个结果数组
        int[] result = new int[n - k + 1];

        // 定义存放块内最大值的left和right数组,长度和原数组保持一致
        // 一个从左往右扫描,另外一个是从右往左扫描
        int[] left = new int[n];
        int[] right = new int[n];

        // 遍历数组,在一次循环中进行左右扫描
        for (int i = 0; i < n; i++) {
            // 1.从左到右
            // 如果能整除k,就是块的起始位置,此时left[i]存入就是该元素,因为该元素是块内的第一个元素
            if (i % k == 0) {
                left[i] = nums[i];
            } else {
                // 如果不是起始位置,即后面的元素,就直接跟前一个元素left[i-1]比较取最大值即可
                left[i] = Math.max(left[i - 1], nums[i]);
            }
            // 2.从右到左
            // 定义j和i对称,即i+j=n-1,j就是倒数的i
            int j = n - 1 - i;
            // 从右到左方向,块内的第一元素为:j % k == k - 1
            // 同时,从右到左的第一快可能不完整,即使索引位置对k取余不是k-1,也是有可能为第一元素,加上这种特殊情况,即j==n-1
            if (j % k == k - 1 || j == n - 1) {
                right[j] = nums[j];
            } else {
                // 因为是从右往左扫描的,所以是当前元素和右边的元素进行比较,所以是right[j+1]
                right[j] = Math.max(right[j + 1], nums[j]);
            }
        }

        // 在有了left和right数组之后,再去对每个窗口计算最大值
        for (int i = 0; i < n - k + 1; i++) {
            // i是当前的起始位置,结束的位置为i + k - 1
            result[i] = Math.max(right[i], left[i + k - 1]);
        }
        return result;
    }

    public static void main(String[] args) {
        int[] input = {1, 3, -1, -3, 5, 3, 6, 7};
        int k = 3;
        LeetCode_239_SlidingWindowMaximum slidingWindowMaximum = new LeetCode_239_SlidingWindowMaximum();
        int[] output = slidingWindowMaximum.maxSlidingWindow04(input, k);

        for (int i : output) {
            System.out.print(i + "\t");
        }
    }
}


