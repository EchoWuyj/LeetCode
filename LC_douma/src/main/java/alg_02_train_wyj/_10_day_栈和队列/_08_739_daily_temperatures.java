package alg_02_train_wyj._10_day_栈和队列;

import java.util.Stack;

/**
 * @Author Wuyj
 * @DateTime 2023-04-26 12:23
 * @Version 1.0
 */
public class _08_739_daily_temperatures {

    public int[] dailyTemperatures1(int[] T) {
        if (T.length == 1) return new int[]{0};
        int n = T.length;
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (T[j] > T[i]) {
                    res[i] = j - i;
                    break;
                }
            }
        }
        return res;
    }

    public int[] dailyTemperatures2(int[] temperatures) {
        int n = temperatures.length;
        if (n == 1) return new int[]{0};
        int[] res = new int[n];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            int t = temperatures[i];
            while (!stack.isEmpty() && t > temperatures[stack.peek()]) {
                int top = stack.pop();
                res[top] = i - top;
            }
            stack.push(i);
        }
        return res;
    }
}
