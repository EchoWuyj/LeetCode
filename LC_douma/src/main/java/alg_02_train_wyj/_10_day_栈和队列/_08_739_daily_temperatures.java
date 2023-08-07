package alg_02_train_wyj._10_day_栈和队列;

import java.util.ArrayDeque;
import java.util.Deque;

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

    public int[] dailyTemperatures2(int[] T) {
        int n = T.length;
        if (n == 1) return new int[0];
        int[] res = new int[n];
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            int t = T[i];
            while (!stack.isEmpty() && t > T[stack.peek()]) {
                res[stack.peek()] = i - stack.peek();
                stack.pop();
            }
            stack.push(i);
        }
        return res;
    }
}
