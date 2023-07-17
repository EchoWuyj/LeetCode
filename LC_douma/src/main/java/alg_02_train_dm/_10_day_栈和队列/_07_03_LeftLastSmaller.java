package alg_02_train_dm._10_day_栈和队列;

import java.util.ArrayDeque;
import java.util.Arrays;

/**
 * @Author Wuyj
 * @DateTime 2023-04-25 23:49
 * @Version 1.0
 */
public class _07_03_LeftLastSmaller {

    /*
      题目：找出数组中左边离我最近比我小的元素
      一个整数数组 nums，找到每个元素：左边第一个比我小的下标位置，没有则用 -1 表示。
      输入：[1, 2]
      输出：[-1, 0]

      解释：
      因为元素 2 的左边离我最近且比我小的位置应该是 nums[0]，
      第一个元素 1 左边没有比 1 小的元素，所以应该输出 -1。
   */
    public static int[] findLeftLastSmall(int[] nums) {
        int[] ans = new int[nums.length];
        ArrayDeque<Integer> stack = new ArrayDeque<>();

        // 左边离我最近比我小的元素
        // => 从右往左遍历
        for (int i = nums.length - 1; i >= 0; i--) {
            int num = nums[i];
            // KeyPoint 根据题目需求 => 来写循环条件
            // 左边离我最近比我小的元素 => num < nums[stack.peek()]
            // KeyPoint 解释：单调递增栈
            // => 只有 num < nums[栈顶]，执行 while 循环
            // => 说明：栈中元素一个比一个大，故栈是递增的
            while (!stack.isEmpty() && num < nums[stack.peek()]) {
                ans[stack.peek()] = i;
                stack.pop();
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            ans[stack.peek()] = -1;
            stack.pop();
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] arr = {6, 0, 2, 9, 5, 3, 1};
        // Arrays.toString 打印数组
        System.out.println(Arrays.toString(findLeftLastSmall(arr)));
        // [-1, -1, 1, 2, 2, 2, 1]
    }
}
