package alg_02_train_dm._10_day_栈和队列;

import java.util.ArrayDeque;
import java.util.Arrays;

/**
 * @Author Wuyj
 * @DateTime 2023-04-25 21:51
 * @Version 1.0
 */
public class _07_01_RightFirstSmaller {

    /*
           题目：找出数组中右边第一个比我小的元素
           一个整数数组 nums，找到每个元素：右边第一个比我小的下标位置，没有则用 -1 表示。
           输入：[5, 2]
           输出：[1, -1]

           解释：
               因为元素 5 的右边离我最近且比我小的位置应该是 nums[1]，
               最后一个元素 2 右边没有比 2 小的元素，所以应该输出 -1。


           暴力求解

           索引             0 1 2 3 4  5 6
           元素             1 2 3 9 5  0 6
           数组中右边第一个  5 5 5 4 5 -1 -1
           比我小的元素

           单调递增栈

           索引  0 1 2 3 4 5 6
           元素  1 2 3 9 5 0 6

           对于暂时无法确定结果索引 index，先将 index 存储起来，后面再去处理 => '先进后出' => 栈

           暂存  0 1 2 3 => 3 位置，已经找到第一个比我小的元素 5，将其踢出暂存
           暂存  0 1 2 4 => 4 位置，已经找到第一个比我小的元素 0，将其踢出暂存
           暂存  0 1 2 => 2 位置，已经找到第一个比我小的元素 0，将其踢出暂存
           暂存  0 1 => 1 位置，已经找到第一个比我小的元素 0，将其踢出暂存
           暂存  0 => 0 位置，已经找到第一个比我小的元素 0，将其踢出暂存

           索引  0 1 2 3 4 5  6
           ans   5 5 5 4 5 -1 -1

           暂存 => 栈

    */
    public static int[] findRightSmall(int[] nums) {
        int[] ans = new int[nums.length];
        // 空间复杂度 O(n) => 最坏情况，stack 存储数组中所有元素
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        // for 循环 => 遍历一遍数组中元素
        // 最多遍历两遍数组，O(2n) => 时间复杂度 O(n)
        // 单调栈解决问题，时间复杂度 O(n)
        // KeyPoint 右边 => 从左往右遍历
        for (int i = 0; i < nums.length; i++) {
            int x = nums[i];
            // 单调递增栈
            // 关于递增递减判断
            // 栈中存储索引， 但是实际需要比较索引对应值的大小变化关系，从而确定递增，还是递减
            // 栈 0 1 2 3
            // 索引 0 1 2 3
            // 数组值 1 2 3 8 => 单调递增栈

            // 不断将'当前元素值'和'栈顶索引对应元素值'进行比较 => 使用 while，而不是 if
            // while 循环 => 最多往回遍历数组中元素
            // KeyPoint 根据需求来写循环条件
            // 右边第一个比其小的元素 =>  x < nums[stack.peek()]
            while (!stack.isEmpty() && x < nums[stack.peek()]) {
                ans[stack.peek()] = i;
                stack.pop();
            }
            // 栈存储的是索引
            stack.push(i);
        }
        // 栈中剩余元素没有比其小的元素，将其索引设置为 -1
        while (!stack.isEmpty()) {
            // 注意是 ans 赋值，不是 nums
            ans[stack.peek()] = -1;
            stack.pop();
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 9, 5, 0, 6};
        // Arrays.toString 将数组进行打印
        System.out.println(Arrays.toString(findRightSmall(arr))); // [5, 5, 5, 4, 5, -1, -1]
    }
}
