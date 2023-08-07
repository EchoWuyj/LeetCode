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

           KeyPoint 方法一 暴力求解
           线性遍历，时间复杂度 O(n^2)
           索引             0 1 2 3 4  5 6
           元素             1 2 3 9 5  0 6
           数组中右边第一个  5 5 5 4 5 -1 -1
           比其小的元素

           KeyPoint 方法二 单调递增栈
           索引  0 1 2 3 4 5 6
           元素  1 2 3 9 5 0 6
           遍历  →

           索引  0 1 2 3 4 5  6
           res   5 5 5 4 5 -1 -1

           从左往右依次遍历每个索引 index，对于暂时无法确定结果 => 右边第一个比其小的元素
           先将 index 存储起来，后面再去处理 => '先进后出' => 栈

           详细过程
           i 从 0 遍历到 3，右边没有第一个比其小的元素，index 存入栈中 stack：0 1 2 3
           i = 4，nums[3](9) > nums[4](5)，找到第一个比栈顶小的元素，更新 res[3] = 4，stack 弹出索引 3
           ...

    */

    // 单调栈解决问题，时间复杂度 O(n)
    // => for 循环 => 正向遍历一遍数组中元素
    //    while 循环 => 最多往回遍历数组中元素
    // => 最多遍历两遍数组，时间复杂度 O(2n)，去掉系数 O(n)
    // 空间复杂度 O(n) => 最坏情况，stack 存储数组中所有元素
    public static int[] findRightSmall(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        // 右边第一个比我小的元素
        // => 从左往右遍历
        for (int i = 0; i < n; i++) { // O(n)
            int num = nums[i];

            // KeyPoint 使用 while，不用 if
            // 不断将'当前元素值'和'栈顶索引对应元素值'进行比较
            // => 使用 while 多次比较，而不是 if 单词比较

            // KeyPoint 根据题目需求 => 来写循环条件
            // 右边第一个比其小的元素 =>  num < nums[stack.peek()]

            // KeyPoint 解释：单调递增栈
            // => 只有 num < nums[栈顶]，执行 while 循环
            // => 说明：已经存入栈中，元素一个比一个大，故栈是递增的
            while (!stack.isEmpty() && num < nums[stack.peek()]) {
                res[stack.peek()] = i;
                // 弹栈
                stack.pop();
            }
            // KeyPoint 注意：栈存储的是索引 index，而不是 nums[i]，不要混淆
            // => 数组所有 index 都是需要压入一遍栈
            stack.push(i);
        }

        // 栈中剩余元素没有比其小的元素，将其索引设置为 -1
        // KeyPoint 注意：使用 while 循环判断，不是 if 单次判断
        while (!stack.isEmpty()) {
            // 注意是 res 赋值，不是 nums
            res[stack.pop()] = -1;
        }
        return res;

        // KeyPoint 单调递增栈
        // 关于递增，递减判断
        // => 栈中存储索引，但是实际比较的是：索引对应值的 nums[index] 大小变化关系
        //    从而确定递增，还是递减

        // stack 存储索引 0 1 2 3
        // nums
        // index   0 1 2 3
        // value   1 2 3 8 => 单调递增栈
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 9, 5, 0, 6};
        // Arrays.toString 将数组进行打印
        System.out.println(Arrays.toString(findRightSmall(arr)));
        // [5, 5, 5, 4, 5, -1, -1]
    }
}
