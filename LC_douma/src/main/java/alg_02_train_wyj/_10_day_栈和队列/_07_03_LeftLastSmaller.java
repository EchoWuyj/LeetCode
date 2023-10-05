package alg_02_train_wyj._10_day_栈和队列;

import java.util.Arrays;
import java.util.Stack;

/**
 * @Author Wuyj
 * @DateTime 2023-04-26 11:59
 * @Version 1.0
 */
public class _07_03_LeftLastSmaller {

    // 找出数组中左边离我最近比我小的元素
    public static int[] findLeftLastSmall(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        Stack<Integer> stack = new Stack<>();
        for (int i = n - 1; i >= 0; i--) {
            int num = nums[i];
            while (!stack.isEmpty() && num < nums[stack.peek()]) {
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
        int[] arr = {6, 0, 2, 9, 5, 3, 1};
        System.out.println(Arrays.toString(findLeftLastSmall(arr)));
        // [-1, -1, 1, 2, 2, 2, 1]
    }
}
