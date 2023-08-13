package alg_02_train_wyj._24_day_贪心算法一;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @Author Wuyj
 * @DateTime 2023-04-12 14:51
 * @Version 1.0
 */
public class _04_45_JumpGame2 {
    public int jump(int[] nums) {
        if (nums.length == 1) return 0;
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(0);
        int level = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int index = queue.poll();
                if (index == nums.length - 1) return level;
                for (int j = 1; j <= nums[index]; j++) {
                    queue.offer(index + j);
                }
            }
            level++;
        }
        return 0;
    }

    public static void main(String[] args) {
        int[] nums = {2, 3, 1, 1, 4};
        System.out.println(new _04_45_JumpGame2().jump(nums));
        // 2
    }
}
