package alg_02_train_wyj._21_day_综合应用二;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author Wuyj
 * @DateTime 2023-05-26 16:25
 * @Version 1.0
 */
public class _05_202_happy_number {
    public boolean isHappy1(int n) {
        Set<Integer> set = new HashSet<>();
        while (true) {
            if (n == 1) return true;
            if (set.contains(n)) return false;
            set.add(n);
            n = squareSum(n);
        }
    }

    private int squareSum(int n) {
        int sum = 0;
        while (n != 0) {
            int num = n % 10;
            sum += num * num;
            n /= 10;
        }
        return sum;
    }

    public boolean isHappy2(int n) {
        if (n == 1) return true;
        int slow = n, fast = n;
        while (true) {
            slow = squareSum(slow);
            fast = squareSum(squareSum(fast));
            if (slow == 1 || fast == 1) return true;
            if (slow == fast) return false;
        }
    }
}
