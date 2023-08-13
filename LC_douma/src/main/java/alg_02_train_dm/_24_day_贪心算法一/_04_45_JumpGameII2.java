package alg_02_train_dm._24_day_贪心算法一;

import alg_02_train_wyj._24_day_贪心算法一._04_45_JumpGame2;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @Author Wuyj
 * @DateTime 2023-04-11 17:44
 * @Version 1.0
 */
public class _04_45_JumpGameII2 {

    // KeyPoint 方法二 BFS 超出内存限制
    public int jump(int[] nums) {
        // 特判，只有一个元素，直接不用跳跃
        if (nums.length == 1) return 0;
        // Queue 存放 index，index 表示已经跳到的索引
        // 只要 index 达到 nums.length-1，即可返回
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(0);
        // 记录层数，用来计算最短路径
        // 层数越短，则对应的路径越短
        int level = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int index = queue.poll();
                //  若跳跃到最后位置，返回 level
                if (index == nums.length - 1) return level;
                // KeyPoint 区别：二叉树和多叉树进队方式
                // 二叉树 left 和 right 加入队列，多叉树使用 for 循环
                for (int j = 1; j <= nums[index]; j++) {
                    // 使用循环变量 j 而不是 nums[index]
                    // j 相当于每次按照 1 累加的跳跃步数，每种情况都将其加入队列
                    queue.offer(index + j);
                }
            }
            level++;
        }
        // BFS 结束都没有找到，则返回 0
        return 0;
    }

    public static void main(String[] args) {
        int[] nums = {2, 3, 1, 1, 4};
        System.out.println(new _04_45_JumpGame2().jump(nums));
        // 2
    }
}
