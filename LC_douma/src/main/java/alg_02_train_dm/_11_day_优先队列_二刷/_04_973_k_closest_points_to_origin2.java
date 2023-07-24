package alg_02_train_dm._11_day_优先队列_二刷;

import java.util.PriorityQueue;

/**
 * @Author Wuyj
 * @DateTime 2023-07-22 16:00
 * @Version 1.0
 */
public class _04_973_k_closest_points_to_origin2 {

    // KeyPoint 方法二 大顶堆
    // 1.前 k 个最大元素 -> 升序排列 -> 小顶堆
    // 2.前 k 个最小元素 -> 降序排列 -> 大顶堆
    // KeyPoint 关键
    // 看二叉堆的下面元素，保证其和'最大'或'最小'一致
    // 1.最大 => 堆的下面元素要大 => 堆顶小 => 小顶堆
    // 2.最小 => 堆的下面元素要小 => 堆顶大 => 大顶底
    public int[][] kClosest2(int[][] points, int k) {
        PriorityQueue<int[]> maxHeap
                // 注意：new PriorityQueue 需要加上 <>
                = new PriorityQueue<>(k + 1,
                // 按照欧几里德距离，降序排序，大顶堆
                // 整体加上括号，之后再去相减 (b[0] * b[0] + b[1] * b[1])
                (a, b) -> (b[0] * b[0] + b[1] * b[1]) - (a[0] * a[0] + a[1] * a[1]));

        for (int[] point : points) {
            maxHeap.add(point);
            // 堆顶是 k+1 大元素，则堆顶以下为前 k 个最小元素
            if (maxHeap.size() > k) maxHeap.remove();
        }

        // 前 k 个近元素在 maxHeap 中
        // 没法像前面一样，使用 Arrays.copyOfRange，拷贝是针对数组的
        int[][] res = new int[k][2];
        int index = 0;
        while (!maxHeap.isEmpty()) {
            // remove 返回 int[]，将其存储到 res 中
            res[index++] = maxHeap.remove();
        }
        return res;
    }
}
