package alg_02_train_wyj._12_day_滑动窗口;

/**
 * @Author Wuyj
 * @DateTime 2023-05-07 18:34
 * @Version 1.0
 */
public class _12_845_longest_mountain_in_array {

    public int longestMountain(int[] arr) {
        if (arr == null) return 0;
        int n = arr.length;
        int left = 0;
        int right;
        int ans = 0;
        while (left + 2 < n) {
            right = left + 1;
            if (arr[left] < arr[right]) {
                while (right + 1 < n && arr[right] < arr[right + 1]) {
                    right++;
                }
                if (right + 1 < n && arr[right] > arr[right + 1]) {
                    while (right + 1 < n && arr[right] > arr[right + 1]) {
                        right++;
                    }
                    ans = Math.max(right - left + 1, ans);
                } else {
                    right++;
                }
            }
            left = right;
        }
        return ans;
    }
}
