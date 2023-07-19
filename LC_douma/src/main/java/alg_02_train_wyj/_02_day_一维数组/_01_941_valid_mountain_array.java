package alg_02_train_wyj._02_day_一维数组;

/**
 * @Author Wuyj
 * @DateTime 2023-04-17 12:30
 * @Version 1.0
 */
public class _01_941_valid_mountain_array {
    public boolean validMountainArray(int[] arr) {
        int n = arr.length;
        int i = 0;
        while (i < n - 1 && arr[i] < arr[i + 1]) i++;
        if (i == 0 || i == n - 1) return false;
        while (i < n - 1 && arr[i] > arr[i + 1]) i++;
        return i == n - 1;
    }
}
