package alg_02_train_wyj._10_day_栈和队列;

import java.util.ArrayDeque;
import java.util.Arrays;

/**
 * @Author Wuyj
 * @DateTime 2023-04-26 11:59
 * @Version 1.0
 */
public class _07_04_LeftLastLarger {
    public static int[] findLeftLastLarge(int[] nums) {
        int[] ans = new int[nums.length];
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        for (int i = nums.length - 1; i >= 0; i--) {
            int x = nums[i];
            while (!stack.isEmpty() && x > nums[stack.peek()]) {
                ans[stack.peek()] = i;
                stack.pop();
            }
            stack.push(i);
        }

        while (!stack.isEmpty()) {
            int top = stack.pop();
            ans[top] = -1;
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] arr = {6, 0, 2, 9, 1, 3, 5};
        System.out.println(Arrays.toString(findLeftLastLarge(arr))); // [-1, 0, 0, -1, 3, 3, 3]
    }
}
