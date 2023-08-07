package alg_02_train_wyj._10_day_栈和队列;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * @Author Wuyj
 * @DateTime 2023-04-26 11:28
 * @Version 1.0
 */
public class _07_01_RightFirstSmaller {
    // 右边第一个比其小的
    public static int[] findRightSmall(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            int num = nums[i];
            while (!stack.isEmpty() && num < nums[stack.peek()]) {
                res[stack.peek()] = i;
                stack.pop();
            }
            stack.push(i);
        }

        while (!stack.isEmpty()) {
            res[stack.pop()] = -1;
        }
        return res;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 9, 5, 0, 6};
        // Arrays.toString 将数组进行打印
        System.out.println(Arrays.toString(findRightSmall(arr))); // [5, 5, 5, 4, 5, -1, -1]
    }
}
