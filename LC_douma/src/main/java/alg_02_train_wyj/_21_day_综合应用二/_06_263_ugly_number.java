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
        int[] nums = {2, 3, 5};
        for (int num : nums) {
            if (n % num == 0) {
                if (dfs(n / num)) return true;
            }
        }
        return false;
    }

    public boolean isUgly(int n) {
        if (n == 0) return false;
        int[] nums = {2, 3, 5};
        for (int num : nums) {
            while (n % num == 0) {
                n /= num;
            }
        }
        return n == 1;
    }
}
