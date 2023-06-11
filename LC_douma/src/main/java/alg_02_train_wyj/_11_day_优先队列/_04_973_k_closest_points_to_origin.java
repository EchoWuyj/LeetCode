package alg_02_train_wyj._11_day_优先队列;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Random;

/**
 * @Author Wuyj
 * @DateTime 2023-05-22 15:34
 * @Version 1.0
 */
public class _04_973_k_closest_points_to_origin {

    public int[][] kClosest1(int[][] points, int k) {
        if (points == null) return points;
        // 升序
        Arrays.sort(points, (o1, o2) -> (o1[0] * o1[0] + o1[1] * o1[1]) - (o2[0] * o2[0] + o2[1] * o2[1]));
        return Arrays.copyOfRange(points, 0, k);
    }

    public int[][] kClosest2(int[][] points, int k) {
        if (points == null) return points;
        PriorityQueue<int[]> pq = new PriorityQueue<>(k + 1, (o1, o2) ->
                (o2[0] * o2[0] + o2[1] * o2[1]) - (o1[0] * o1[0] + o1[1] * o1[1]));

        for (int[] arr : points) {
            pq.add(arr);
            if (pq.size() > k) pq.remove();
        }

        int[][] res = new int[k][];
        int index = 0;
        while (!pq.isEmpty()) {
            res[index++] = pq.remove();
        }
        return res;
    }

    private Random random = new Random(System.currentTimeMillis());

    public int[][] kClosest3(int[][] points, int k) {
        if (points == null) return points;
        int left = 0, right = points.length - 1;
        int target = points.length - k;
        while (true) {
            int pivotIndex = partition(points, left, right);
            if (pivotIndex == target) {
                break;
            } else if (pivotIndex < target) {
                left = pivotIndex + 1;
            } else {
                right = pivotIndex - 1;
            }
        }
        return Arrays.copyOfRange(points, target, points.length);
    }

    public int partition(int[][] nums, int low, int high) {
        if (high > low) {
            int pivotIndex = random.nextInt(high - low + 1) + low;
            swap(nums, pivotIndex, high);
        }
        int pivot = nums[high][0] * nums[high][0] + nums[high][1] * nums[high][1];
        int less = low, great = low;
        for (; great < high; great++) {
            int num = nums[great][0] * nums[great][0] + nums[great][1] * nums[great][1];
            if (num > pivot) {
                swap(nums, less, great);
                less++;
            }
        }
        swap(nums, less, high);
        return less;
    }

    public void swap(int[][] nums, int i, int j) {
        int[] tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
