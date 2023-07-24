package alg_02_train_wyj._11_day_优先队列;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @Author Wuyj
 * @DateTime 2023-05-22 15:34
 * @Version 1.0
 */
public class _04_973_k_closest_points_to_origin1{

    public int[][] kClosest1(int[][] points, int k) {
        if (points == null) return points;
        Arrays.sort(points,
                (o1, o2) -> (o1[0] * o1[0] + o1[1] * o1[1]) - (o2[0] * o2[0] + o2[1] * o2[1]));
        return Arrays.copyOfRange(points, 0, k);
    }

    public int[][] kClosest2(int[][] points, int k) {
        PriorityQueue<int[]> maxHeap
                = new PriorityQueue<>(k + 1,
                (a, b) -> ((b[0] * b[0] + b[1] * b[1]) - (a[0] * a[0] + a[1] * a[1])));

        for (int[] point : points) {
            maxHeap.add(point);
            if (maxHeap.size() > k) maxHeap.remove();
        }

        int[][] res = new int[k][2];
        int index = 0;
        while (!maxHeap.isEmpty()) {
            res[index++] = maxHeap.remove();
        }
        return res;
    }
}
