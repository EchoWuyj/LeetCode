package alg_02_train_dm._10_day_栈和队列_二刷;

import java.util.ArrayDeque;

/**
 * @Author Wuyj
 * @DateTime 2023-04-27 16:34
 * @Version 1.0
 */
public class _12_321_create_maximum_number {
     /* 
        321 号算法题：拼接最大数
        给定长度分别为 m 和 n 的两个数组，其元素由 0-9 构成，表示两个自然数各位上的数字。
        现在从这两个数组中选出 k (k <= m + n) 个数字拼接成一个新的数，
        要求从同一个数组中取出的数字保持其在原数组中的相对顺序，求满足该条件的最大数。
        结果返回一个表示该最大数的长度为 k 的数组。
        要求: 请尽可能地优化你算法的时间和空间复杂度

        输入:
            nums1 = [3, 4, 6, 5]
            nums2 = [9, 1, 2, 5, 8, 3]
            k = 5
        输出：[9,8,6,5,3]

        解释：
         k = 5 => 挑出 5 个数字
         nums1 = [3, 4, 6, 5]  => 6, 5
         nums2 = [9, 1, 2, 5, 8, 3]  => 9, 8, 3
         [9,8,6,5,3] => 输出结果：保证 nums1 和 nums2，元素相对顺序不能改变
                     => 如：nums2 中 9 在 8 的前面，8 在 3 的前面

        没有提示

     */

    public int[] maxNumber(int[] nums1, int[] nums2, int k) {

        // KeyPoint 分析：确定所有组合可能情况

        // m 个数字，nums1：3 4 6 5，取 0
        // n 个数字，nums2：9 1 2 5 8 3，n > k，取 5
        // k = 5

        // m 个数字，nums1：3 4 6 5，取 k-n = 5-3 = 2
        // n 个数字，nums2：9 1 2，n < k，取 n = 3
        // k = 5

        // KeyPoint 分析 num1 的 m 最小个数，依赖于 n 和 k 的关系

        // 1.num1，m 个，最小取 0 个数
        //   num2，n 个，n >= k
        // 2.num1，m 个，最小取 n-k 个数
        //   num2，n 个，n < k
        // => num1 最小取的个数：Math.max(0, k-n)
        // => 通过一个表达式，将 2 种情况都包括在内了

        // KeyPoint 分析 num1 的 m 最大个数，依赖于 m 和 k 的关系

        // 1.num1，m 个，m < k，最大取 m 个数
        //              √
        // 2.num1，m 个，m >= k，最大取 k 个数
        //                   √
        // => 两种情况都是取小值，故使用 Math.min 来表达，Math.min(m, k)
        //    若是使用 if else 表示也是可以的，但不够简洁

        // KeyPoint 最后确定 m 和 n
        // 一旦当确定 num1 选出数字个数 m，则 num2 选出数字个数 k-m

        // KeyPoint 核心思路
        // 1.先确定所有组合可能情况
        // 2.对每种组合情况进行遍历，nums1 选出 i 个，nums2 选出 k-i 个，总的选取数字为 k 个
        // 3.合并每种组合，两个数组的最大子序列
        // 4.比较，取最大值，返回 maxSubSeq

        int[] maxSubSeq = new int[k];
        int m = nums1.length;
        int n = nums2.length;

        // 1.确定所有组合可能情况
        // 针对 m 取值，最小取值和最大取值
        int minM = Math.max(0, k - n);
        int maxM = Math.min(m, k);

        // 2.对每种组合情况进行遍历
        for (int count = minM; count <= maxM; count++) {

            // 获取 num1 和 num2 最大子序列
            // 注意：子序列，只需要保持相对顺序一致，不要求连续
            // num1 选出数字个数 count
            int[] subSeq1 = maxSubSeq2(nums1, count);
            // num2 选出数字个数 k-count
            int[] subSeq2 = maxSubSeq2(nums2, k - count);

            // 3.合并每种组合，两个数组的最大子序列
            int[] curMaxSubSeq = merge(subSeq1, subSeq2);

            // 4.比较，取最大值，返回 maxSubSeq
            if (compare(curMaxSubSeq, 0, maxSubSeq, 0) > 0) {
                // System.arraycopy
                // => 将当前 curMaxSubSeq 赋值给最终的 maxSubSeq，还需确定长度 k
                System.arraycopy(curMaxSubSeq, 0, maxSubSeq, 0, k);
            }
        }
        return maxSubSeq;
    }

    // 函数功能：在 num 数组中，获取 k 个元素，该 k 个元素组成该数组的最大子序列
    private int[] maxSubSeq1(int[] nums, int k) {

        // KeyPoint 解决思路
        // 先从简单情况入手，手动一步步模拟，去找到规律，再用代码实现

        // 在 num 数组中，获取 k 个元素，该 k 个元素组成该数组的最大子序列
        // => 单调递减栈

        // 9 2 1 5 8 3，k = 3
        //       ↑
        //       i

        // 栈底 9 2 1
        //         ↑
        //        栈顶

        // nums[i] = 5 > stack.peek() = 1
        // while 循环判断，栈顶依次弹栈，将 5 放到 9 的后面，即 9 5

        // 9 2 1 5 8 3，k = 3
        //         ↑
        //         i

        // 栈底 9 5
        //        ↑
        //      栈顶

        // nums[i] = 8 > stack.peek() = 5
        // while 循环判断，栈顶依次弹栈，将 8 放到 9 的后面，即 9 8

        // 9 2 1 5 8 3，k = 3
        //           ↑
        //           i

        // 栈底 9 8 3
        //         ↑
        //        栈顶

        // 最终输出：9 8 3

        int n = nums.length;
        ArrayDeque<Integer> stack = new ArrayDeque<>();

        // 剩余数字个数
        int remain = n - k;
        for (int i = 0; i < n; i++) {
            int num = nums[i];
            // 本题单调递减栈存储就是数字本身，存储的不是索引，接使用 stack.peek()
            while (!stack.isEmpty() && num > stack.peek() && remain > 0) {
                stack.pop();
                // 在 stack.pop() 之后，后续必然 stack.push(num)
                // 而 remain--，则表示 num 在 remain 中自身的那一个
                remain--;

                // KeyPoint 注意事项
                // remain -- 本质为了保证 stack 弹栈之后，remain 后续必然有一个元素能补上来，
                // 从而保证最大子序列个数 = k，否则无法保证为最大子序列
                // 如：k = 3：9 2 1 和  k = 2：9 5 _
                // =>  9 2 1 > 9 5 _
            }

            // 保证最大子序列个数 = k，故需要严格小于 k，否则，最大子序列个数 > k
            if (stack.size() < k) {
                // stack 没有满，num 使用的名额是 k 的，故 n-k 的名额不变
                stack.push(num);
            } else {
                // 因为 stack.size() 已经等于 k，
                // 此时 num 使用的名额是 n-k 的，且没有发挥作用，浪费一个数字，故减一
                remain--;
            }
        }

        // 因为最后需要返回是数组，需要将栈转数组，同时需要控制次序
        int[] res = new int[k];
        int index = k - 1;
        while (!stack.isEmpty()) {

            //  9  8  3
            //  ↑     ↑
            // 栈底  栈顶

            // 栈顶元素放到数组最后 => 数组：栈底 ... 栈顶
            // 从右往左，index--
            res[index--] = stack.pop();
        }
        return res;
    }

    // KeyPoint 优化
    // 因为考虑最后返回是数组，故使用数组模拟栈，最后直接返回即可，省略了栈转数组
    private int[] maxSubSeq2(int[] nums, int k) {

        // 数组模拟栈
        // num1 ... numn
        //  ↑        ↑
        // 栈底     栈顶
        //          top
        int[] stack = new int[k];
        // top => 栈顶 => 数组末尾
        int top = -1;
        int n = nums.length;
        int remain = n - k;
        for (int i = 0; i < n; i++) {
            int num = nums[i];
            // top >= 0 表示栈不为空
            while (top >= 0 && num > stack[top] && remain > 0) {
                top--;
                remain--;
            }
            // k 从 0 开始，故 k - 1
            if (top < k - 1) {
                // top 指向栈顶位置，故得先 top +1，空出一个位置，再去进行赋值
                stack[++top] = num;
            } else {
                remain--;
            }
        }
        // 本身就是使用数组模拟栈，故可以直接返回
        return stack;
    }

    // 函数功能：合并两个最大子序列
    private int[] merge(int[] arr1, int[] arr2) {

        // KeyPoint 基本原则
        // 谁大谁放前面，但是还是有点细节需要注意

        // KeyPoint 情况一
        // arr1：9 5 8
        // arr2：7 4
        // arr1 和 arr2，挑一个最大数轮流比较，相对顺序不能改变
        // 9 > 7 => 9
        // 7 > 5 => 7
        // 5 > 4 => 5
        // 8 > 4 => 8
        // 剩余 4
        // => merged [9,7,5,8,4]

        // KeyPoint 情况二
        // arr1  9 6 8
        // arr2  9 5 4
        // => 9 相等，则比较后一位，6 > 5，将 arr1 对应的 9，加入结果 merged
        // => merged [9,9,6,8,5,4]

        // KeyPoint 情况三
        // arr1 [9,5,4,3,4]
        // arr2 [9,5,4]
        // => arr1 和 arr2 一直比较，直到 arr2，arr1 长度更长
        //    将 arr1 [9,5,4,3,4] 返回

        int len1 = arr1.length;
        int len2 = arr2.length;
        // 特判
        if (len1 == 0) return arr2;
        if (len2 == 0) return arr1;
        int mergeLen = len1 + len2;
        // 合并后数组
        int[] merged = new int[mergeLen];
        int i = 0, j = 0;
        for (int index = 0; index < mergeLen; index++) {
            // 不断从 arr1 和 arr2 中取较大值，拼接到 merged 中
            if (compare(arr1, i, arr2, j) > 0) {
                // diff 正数 => 取 arr1
                merged[index] = arr1[i++];
            } else {
                // else 语句中，并不是 compare <= 0，因为 compare 返回值没有为 0
                // diff 负数 => 取 arr2
                merged[index] = arr2[j++];
            }
        }
        return merged;
    }

    // KeyPoint 类比：12_179_LargestNumber1 最大数排序
    // 函数功能：两个子序列，逐个位置比较，逐个返回 diff
    // => 自顶定义 compare 方法，不是排序中的 compare
    // arr1 从 i 到 结尾
    // arr2 从 j 到 结尾
    private int compare(int[] arr1, int i, int[] arr2, int j) {

        // 长度
        int len1 = arr1.length;
        int len2 = arr2.length;
        // i 和 j 都没有越界
        while (i < len1 && j < len2) {
            int diff = arr1[i] - arr2[j];
            // diff 不相等，直接返回 diff
            // 1.若 diff > 0，arr1[i] 大
            // 2.若 diff < 0，arr2[i] 大
            if (diff != 0) return diff;

            // diff 返回 0 表示相等，比较下一位，直到不相等为止
            i++;
            j++;
        }

        // 若直到跳出 for 循环，diff 都是 0，则比较剩余的长度，选择剩余长度大的 subSeq

        // arr1 [9,5,4,3,4]
        //             ↑
        //             i

        // arr2 [9,5,4]
        //             ↑
        //             j

        // 1.若 diff > 0，arr1[i] 大
        // 2.若 diff < 0，arr2[i] 大
        return (len1 - i) - (len2 - j);
    }
}
