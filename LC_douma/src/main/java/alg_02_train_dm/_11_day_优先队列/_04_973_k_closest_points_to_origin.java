package alg_02_train_dm._11_day_优先队列;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Random;

/**
 * @Author Wuyj
 * @DateTime 2023-05-22 15:38
 * @Version 1.0
 */
public class _04_973_k_closest_points_to_origin {
       /*
        973 号算法题：最接近原点的 K 个点
        我们有一个由平面上的点组成的列表 points，需要从中找出 K 个距离原点 (0, 0) 最近的点
        （这里，平面上两点之间的距离是欧几里德距离。）
        你可以按任何顺序返回答案。除了点坐标的顺序之外，答案确保是唯一的。

        KeyPoint 欧几里德距离 d = sqrt((x2 - x1)^2 + (y2 - y1)^2)

        输入：points = [[3,3],[5,-1],[-2,4]], K = 2
            3*3 + 3*3 = 18 => 这里统一省略 sqrt
            5*5 + -1*(-1) = 26
            -2*(-2) + 4 * 4 = 20
        输出：[[-2,4], [3,3]]

        1 <= K <= points.length <= 10000
        -10000 < points[i][0] < 10000
        -10000 < points[i][1] < 10000
     */

    // KeyPoint 方法一 数学：按照欧几里得距离排序
    public int[][] kClosest1(int[][] points, int K) {
        if (points == null) return points;
        // 升序排列
        // KeyPoint 注意：两部分相减，需要整体加上括号，否则就不是升序的逻辑了
        Arrays.sort(points, (o1, o2) -> (o1[0] * o1[0] + o1[1] * o1[1]) - (o2[0] * o2[0] + o2[1] * o2[1]));

        // 数组中前 k 个点 => 最接近原点的 K 个点
        // from 位置 -> 包括
        // to 位置 -> 不包括
        return Arrays.copyOfRange(points, 0, K);
    }

    // KeyPoint 方法二 大顶堆
    // 1.前 K 个最大元素 -> 升序排列 -> 小顶堆
    // 2.前 K 个最小元素 -> 降序排列 -> 大顶堆
    public int[][] kClosest2(int[][] points, int K) {
        PriorityQueue<int[]> pq
                // KeyPoint new PriorityQueue 需要加上 <>
                = new PriorityQueue<>(K + 1,
                // 按照欧几里德距离，降序排序，大顶堆
                (a, b) -> (b[0] * b[0] + b[1] * b[1]) - (a[0] * a[0] + a[1] * a[1]));

        for (int[] point : points) {
            pq.add(point);
            // 堆顶是 k+1 大元素，则堆顶以下为前 K 个最小元素
            if (pq.size() > K) pq.remove();
        }

        // 小顶堆中存储的就是出现了前 k 个高频的元素
        int[][] res = new int[K][2];
        int index = 0;
        while (!pq.isEmpty()) {
            // remove 返回 int[]，将其存储到 res 中
            res[index++] = pq.remove();
        }
        return res;
    }

    private Random random = new Random(System.currentTimeMillis());

    // KeyPoint 方法三 快速排序分区优化
    // 1.前 K 个最大元素 -> 升序排列(快排) -> 拿到后面 K 个元素即可
    // 2.前 K 个最小元素 -> 降序排列(快排) -> 拿到后面 K 个元素即可
    // => 通过调整快排的'升序'和'降序'，从而保持主代码逻辑不变化，
    public int[][] kClosest3(int[][] points, int k) {
        int n = points.length;
        // 只是改变快排逻辑，而 target 不发生变化
        int target = n - k;
        int left = 0, right = n - 1;
        while (true) {
            int index = partition(points, left, right);
            if (index == target) {
                break;
            } else if (index < target) {
                left = index + 1;
            } else {
                right = index - 1;
            }
        }

        return Arrays.copyOfRange(points, n - k, n);
    }

    private int partition(int[][] nums, int low, int high) {
        if (high > low) {
            int pivotIndex = low + 1 + random.nextInt(high - low);
            swap(nums, pivotIndex, high);
        }
        int pivot = nums[high][0] * nums[high][0]
                + nums[high][1] * nums[high][1];
        int less = low, great = low;
        for (; great < high; great++) {
            int num = nums[great][0] * nums[great][0]
                    + nums[great][1] * nums[great][1];
            // 降序排列
            if (num > pivot) {
                swap(nums, less, great);
                less++;
            }
        }
        swap(nums, less, high);
        return less;
    }

    private void swap(int[][] nums, int i, int j) {
        int[] tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
