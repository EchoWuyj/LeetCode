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
            n = squareNum(n);
        }
    }

    public int squareNum(int num) {
        int total = 0;
        while (num != 0) {
            total += (num % 10) * (num % 10);
            num /= 10;
        }
        return total;
    }

    public boolean isHappy2(int n) {
        if (n == 1) return true;
        int slow = n;
        int fast = n;
        while (true) {
            slow = squareNum(n);
            fast = squareNum(squareNum(n));
            if (slow == 1) return true;
            if (slow == fast) return false;
        }
    }
}
