package alg_02_train_wyj._10_day_栈和队列;

import java.util.ArrayDeque;

/**
 * @Author Wuyj
 * @DateTime 2023-04-27 20:34
 * @Version 1.0
 */
public class _12_321_create_maximum_number {
    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
        int[] maxSubSeq = new int[k];
        int m = nums1.length;
        int n = nums2.length;

        int minM = Math.max(0, k - n);
        int maxM = Math.min(m, k);

        for (int count = minM; count <= maxM; count++) {
            int[] subSeq1 = maxSubSeq1(nums1, count);
            int[] subSeq2 = maxSubSeq1(nums2, k - count);
            int[] curMaxSubSeq = merge(subSeq1, subSeq2);
            if (compare(curMaxSubSeq, 0, maxSubSeq, 0) > 0) {
                System.arraycopy(curMaxSubSeq, 0, maxSubSeq, 0, k);
            }
        }
        return maxSubSeq;
    }

    private int[] maxSubSeq1(int[] nums, int k) {
        int n = nums.length;
        int remain = n - k;
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
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
        return res;
    }

    private int[] maxSubSeq2(int[] nums, int k) {
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

    private int[] merge(int[] arr1, int[] arr2) {
        int len1 = arr1.length;
        int len2 = arr2.length;
        if (len1 == 0) return arr2;
        if (len2 == 0) return arr1;
        int mergeLen = len1 + len2;
        int[] merged = new int[mergeLen];
        int i = 0, j = 0;
        for (int index = 0; index < mergeLen; index++) {
            if (compare(arr1, i, arr2, j) > 0) {
                merged[index] = arr1[i++];
            } else {
                merged[index] = arr2[j++];
            }
        }
        return merged;
    }

    private int compare(int[] arr1, int i, int[] arr2, int j) {
        int len1 = arr1.length;
        int len2 = arr2.length;
        while (i < len1 && j < len2) {
            int diff = arr1[i] - arr2[j];
            if (diff != 0) return diff;
            i++;
            j++;
        }
        return (len1 - i) - (len2 - j);
    }
}
