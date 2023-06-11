package alg_02_train_wyj._21_day_综合应用二;

/**
 * @Author Wuyj
 * @DateTime 2023-05-26 16:41
 * @Version 1.0
 */
public class _06_263_ugly_number {

    public boolean isUgly1(int n) {
        if (n == 0) return false;
        return dfs(n);
    }

    public boolean dfs(int n) {
        if (n == 1) return true;
        int[] factors = {2, 3, 5};
        for (int factor : factors) {
            if (n % factor == 0) {
                if (dfs(n / factor)) return true;
            }
        }
        return false;
    }

    public boolean isUgly(int n) {
        if (n == 0) return false;
        int[] factors = {2, 3, 5};
        for (int factor : factors) {
            while (n % factor == 0) n /= factor;
        }
        return n == 1;
    }
}
