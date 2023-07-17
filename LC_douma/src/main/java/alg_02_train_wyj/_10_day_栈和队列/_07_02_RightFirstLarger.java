package alg_02_train_wyj._10_day_栈和队列;

import java.util.ArrayDeque;
import java.util.Arrays;

/**
 * @Author Wuyj
 * @DateTime 2023-04-26 11:43
 * @Version 1.0
 */
public class _07_02_RightFirstLarger {

    // 找出数组中右边第一个比我大的元素
    public static int[] findRightLarge(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            int num = nums[i];
            while (!stack.isEmpty() && num > nums[stack.peek()]) {
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
        int[] arr = {6, 4, 3, 9, 5, 0, 6};
        System.out.println(Arrays.toString(findRightLarge(arr)));
        // [3, 3, 3, -1, 6, 6, -1]
    }
}
