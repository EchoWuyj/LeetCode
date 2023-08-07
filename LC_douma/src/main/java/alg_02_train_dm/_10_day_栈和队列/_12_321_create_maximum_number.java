package alg_02_train_dm._10_day_栈和队列;

import java.util.ArrayDeque;
import java.util.Arrays;

/**
 * @Author Wuyj
 * @DateTime 2023-04-27 16:34
 * @Version 1.0
 */
public class _12_321_create_maximum_number {
     /* 
        321 号算法题：拼接最大数
        给定长度分别为 m 和 n 的两个数组，其元素由 0 - 9 构成，表示两个自然数各位上的数字。
        现在从这两个数组中选出 k (k <= m + n) 个数字拼接成一个新的数，
        要求从同一个数组中取出的数字保持其在原数组中的相对顺序。
        求满足该条件的最大数。结果返回一个表示该最大数的长度为 k 的数组。
        说明: 请尽可能地优化你算法的时间和空间复杂度。

        输入:
            nums1 = [3, 4, 6, 5]    --> 6, 5
            nums2 = [9, 1, 2, 5, 8, 3]   --> 9, 8, 3
            k = 5
       输出：[9,8,6,5,3] => 保证 nums1 和 nums2 相对顺序不能改变

     */

    public int[] maxNumber(int[] nums1, int[] nums2, int k) {

        int[] maxSubSeq = new int[k];
        int m = nums1.length;
        int n = nums2.length;

        // KeyPoint 分析 m 最小个数，依赖于 n 和 k 的关系
        // 1.num2 n，n >= k
        //   num1 m，最小取 0 个数
        // 2.num2 n，n < k
        //   num1 m，最小取 n-k 个数
        // => Math.max(0, k - n)
        // => 通过一个表达式，将多种情况都包括在内了

        // KeyPoint 分析 m 最大个数，依赖于 m 和 k 的关系
        // num1 m < k，最大取 m 个数
        // num1 m >= k，最大取 k 个数
        // => Math.min(m, k)

        // KeyPoint 一旦当确定 num1 选出数字个数 x，则 num2 选出数字个数 k-x

        // 一共可能组合数
        int start = Math.max(0, k - n);
        int end = Math.min(m, k);

        // 每种情况进行
        for (int i = start; i <= end; i++) {
            // KeyPoint 子序列，只需要保持相对顺序一致，不要求连续
            // 最大子序列(可以不连续)
            // num1 选出数字个数 i
            int[] subSeq1 = maxSubSequence(nums1, i);
            // num2 选出数字个数 k-i
            int[] subSeq2 = maxSubSequence(nums2, k - i);

            // 合并
            int[] currMaxSubSeq = merge(subSeq1, subSeq2);
            // 比较，谁大取谁
            if (compare(currMaxSubSeq, 0, maxSubSeq, 0) > 0) {
                // System 数组 copy 方法
                // 将当前 currMaxSubSeq 赋值给最终的 maxSubSeq，还需确定长度
                System.arraycopy(currMaxSubSeq, 0, maxSubSeq, 0, k);
            }
        }
        return maxSubSeq;
    }

    // 在 num 数组中，获取 k 个元素，该 k 个元素组成该数组的最大子序列
    private int[] maxSubSequence1(int[] nums, int k) {
        // KeyPoint 解决思路：手动一步步模拟，再用代码实现
        // [9,2,1,5,8,3] k = 3 => 单调栈 => 最大子序列
        // 流程
        // 9,2,1 遇到 5，将栈顶元素 pop，将 5 放到 9 的后面，即 9,5
        // 9,5 遇到 8，将栈顶元素 pop，将 8 放到 9 的后面，即 9，8
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        // 剩余数字个数
        int remain = nums.length - k;
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            // 本题单调栈存储就是数字本身，不是索引，故不需要 nums[stack.peek()]，而是直接使用 stack.peek()
            while (!stack.isEmpty() && num > stack.peek() && remain > 0) {
                stack.pop();
                // stack.pop() 之后，必然后续需要再 push 一个元素，所以 remain--
                remain--;
            }
            // 保证最大子序列个数 <= k
            // 严格小于 k，否则，最大子序列个数 > k
            if (stack.size() < k) {
                stack.push(num);
            } else {
                // 浪费剩余的一个数字
                remain--;
            }
        }

        int[] res = new int[k];
        int index = k - 1;
        while (!stack.isEmpty()) {
            // 栈顶元素放到数组最后，数组：栈底 -> 栈顶
            // 从右往左，index--
            res[index--] = stack.pop();
        }
        // 因为最后返回是数组，故使用数组模拟栈
        return res;
    }

    // for test
    public static void testStack() {
        int[] nums = {1, 2, 4, 5, 8, 1};
        int k = 2;
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        // remain = 4
        int remain = nums.length - k;
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            while (!stack.isEmpty() && num > stack.peek() && remain > 0) {
                stack.pop();
                remain--;
            }
            if (stack.size() < k) {
                stack.push(num);
            } else {
                remain--;
            }
        }

        int[] res = new int[k];
        int index = k - 1;
        while (!stack.isEmpty()) {
            res[index--] = stack.pop();
        }
        System.out.println(Arrays.toString(res)); // [8, 1]
    }

    // 优化：因为考虑最后返回是数组，故使用数组模拟栈，最后直接返回即可，省略了栈转数组
    private int[] maxSubSequence(int[] nums, int k) {
        // 数组模拟栈 => 栈底 [ ... ] 栈顶
        int[] stack = new int[k];
        // 栈顶，对应
        int top = -1;
        int remain = nums.length - k;
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            // top >= 0 表示栈不为空
            while (top >= 0 && num > stack[top] && remain > 0) {
                top--;
                remain--;
            }
            // k 从 0 开始，故 k - 1
            if (top < k - 1) {
                // top 指向栈顶位置，故得先 top +1，再去进行赋值
                stack[++top] = num;
            } else {
                remain--;
            }
        }
        return stack;
    }

    // 合并两个最大子序列
    // 基本原则：谁大谁放前面，但是还是有点细节需要注意
    private int[] merge(int[] subSeq1, int[] subSeq2) {

        // KeyPoint 情况一
        // subSeq1 [9,5,8]
        // subSeq2 [7,4]
        // subSeq1 和 subSeq2，挑一个最大数轮流比较，相对顺序不能改变
        // 9 > 7 => 9
        // 7 > 5 => 7
        // 5 > 4 => 5
        // 8 > 4 => 8
        // 剩余 4
        // => merged [9,7,5,8,4]

        // KeyPoint 情况二
        // subSeq1 [9,6,8]
        // subSeq2 [9,5,4]
        // => 9 相等，则比较后一位，6 > 5
        //   将 subSeq1 对应的 9，加入结果 merged
        //=> merged [9,9,6,8,5,4]

        // KeyPoint 情况三
        // subSeq1 [9,5,4,3,4]
        // subSeq2 [9,5,4]
        // => subSeq1 和 subSeq2 一直比较，直到 subSeq2，subSeq1 长度更长，将 subSeq1 返回

        int x = subSeq1.length;
        int y = subSeq2.length;
        if (x == 0) return subSeq2;
        if (y == 0) return subSeq1;
        int mergeLen = x + y;
        // 合并后数组
        int[] merged = new int[mergeLen];
        int index1 = 0, index2 = 0;
        for (int i = 0; i < mergeLen; i++) {
            // 不断从 subSeq1 和 subSeq2 中取较大值，拼接到 merged 中
            if (compare(subSeq1, index1, subSeq2, index2) > 0) {
                // diff 正数 => 取 subSeq1
                merged[i] = subSeq1[index1++];
            } else {
                // diff 负数 => 取 subSeq2
                merged[i] = subSeq2[index2++];
            }
        }
        return merged;
    }

    // 两个子序列，逐个位置比较，逐个返回 diff
    // subSeq1 从 index1 到 结尾
    // subSeq2 从 index2 到 结尾
    private int compare(int[] subSeq1, int index1,
                        int[] subSeq2, int index2) {

        int x = subSeq1.length;
        int y = subSeq2.length;
        // index1 和 index2 都没有越界
        while (index1 < x && index2 < y) {
            int diff = subSeq1[index1] - subSeq2[index2];
            // diff 返回 正数 表示前面 subSeq1 大
            // diff 返回 负数 表示后面 subSeq2 大
            if (diff != 0) return diff;
            // diff 返回 0 表示相等，比较下一位，直到不相等为止
            index1++;
            index2++;
        }
        // 比较剩余的长度，选择剩余长度大的 subSeq
        // subSeq1 [9,5,4,3,4]
        //                ↑ index1
        // subSeq2 [9,5,4]
        //                ↑ index2
        return (x - index1) - (y - index2);
    }

    public static void main(String[] args) {
        testStack();
    }
}
