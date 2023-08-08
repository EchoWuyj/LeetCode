package alg_02_train_dm._10_day_栈和队列_二刷;

import java.util.ArrayDeque;
import java.util.Arrays;

/**
 * @Author Wuyj
 * @DateTime 2023-04-25 23:51
 * @Version 1.0
 */
public class _07_04_LeftLastLarger {

    /*
       题目：找出数组中左边离我最近比我大的元素
       一个整数数组 nums，找到每个元素：左边第一个比我小的下标位置，没有则用 -1 表示。
       输入：[1, 2]
       输出：[-1, 0]

       解释：
       因为元素 2 的左边离我最近且比我小的位置应该是 nums[0]，
       第一个元素 1 左边没有比 1 小的元素，所以应该输出 -1。
    */
    public static int[] findLeftLastLarge(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        // 左边离我最近比我大的元素
        // => 从右往左遍历
        for (int i = n - 1; i >= 0; i--) {
            int num = nums[i];
            // 单调递减栈
            // KeyPoint 根据题目需求 => 来写循环条件
            // 左边离我最近比我大的元素 => num > nums[stack.peek()]
            while (!stack.isEmpty() && num > nums[stack.peek()]) {
                res[stack.peek()] = i;
                stack.pop();
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            res[stack.peek()] = -1;
            stack.pop();
        }
        return res;
    }

    public static void main(String[] args) {
        int[] arr = {6, 0, 2, 9, 1, 3, 5};
        System.out.println(Arrays.toString(findLeftLastLarge(arr)));
        // [-1, 0, 0, -1, 3, 3, 3]
    }
}
