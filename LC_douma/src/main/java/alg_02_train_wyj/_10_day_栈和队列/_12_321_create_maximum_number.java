package alg_02_train_wyj._10_day_栈和队列;

import java.util.ArrayDeque;
import java.util.Arrays;

/**
 * @Author Wuyj
 * @DateTime 2023-04-27 20:34
 * @Version 1.0
 */
public class _12_321_create_maximum_number {
    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
        int m = nums1.length;
        int n = nums2.length;
        int[] maxSubSeq = new int[k];

        int start = Math.max(0, k - n);
        int end = Math.min(m, k);

        for (int i = start; i <= end; i++) {
            int[] subSeq1 = maxSubSeq1(nums1, i);
            int[] subSeq2 = maxSubSeq1(nums2, k - i);

            int[] curMaxSubSeq = merge(subSeq1, subSeq2);

            if (compare(curMaxSubSeq, 0, maxSubSeq, 0) > 0) {
                System.arraycopy(curMaxSubSeq, 0, maxSubSeq, 0, k);
            }
        }
        return maxSubSeq;
    }

    private static int[] maxSubSeq(int[] nums, int k) {
        int n = nums.length;
        int remain = n - k;
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            int x = nums[i];
            while (!stack.isEmpty() && x > stack.peek() && remain > 0) {
                stack.pop();
                remain--;
            }
            if (stack.size() < k) {
                stack.push(x);
            } else {
                remain--;
            }
        }

        int[] res = new int[k];
        int index = k - 1;
        while (!stack.isEmpty()) {
            res[index--] = stack.pop();
        }
        return res;
    }

    private static int[] maxSubSeq1(int[] nums, int k) {
        int[] stack = new int[k];
        int top = -1;
        int n = nums.length;
        int remain = n - k;
        for (int i = 0; i < n; i++) {
            int num = nums[i];
            while (top >= 0 && num > stack[top] && remain > 0) {
                top--;
                remain--;
            }

            if (top < k - 1) {
                stack[++top] = num;
            } else {
                remain--;
            }
        }
        return stack;
    }

    public static void main(String[] args) {
        int[] nums = {9, 2, 1, 5, 8, 3};
        System.out.println(Arrays.toString(maxSubSeq1(nums, 3)));
    }

    private int[] merge(int[] subSeq1, int[] subSeq2) {
        int x = subSeq1.length;
        int y = subSeq2.length;
        if (x == 0) return subSeq2;
        if (y == 0) return subSeq1;
        int mergeLen = x + y;
        int[] merge = new int[mergeLen];
        int index1 = 0, index2 = 0;
        for (int i = 0; i < mergeLen; i++) {
            if (compare(subSeq1, index1, subSeq2, index2) > 0) {
                merge[i] = subSeq1[index1++];
            } else {
                merge[i] = subSeq2[index2++];
            }
        }
        return merge;
    }

    private int compare(int[] subSeq1, int index1, int[] subSeq2, int index2) {
        int x = subSeq1.length;
        int y = subSeq2.length;
        while (index1 < x && index2 < y) {
            int diff = subSeq1[index1] - subSeq2[index2];
            if (diff != 0) return diff;
            index1++;
            index2++;
        }
        return (x - index1) - (y - index2);
    }
}
