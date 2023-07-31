package alg_02_train_wyj._12_day_滑动窗口;

/**
 * @Author Wuyj
 * @DateTime 2023-05-08 11:13
 * @Version 1.0
 */
public class _08_1151_minimum_swaps_to_group_all_1s_together {

    public static int minSwaps(int[] data) {
        if (data == null) return 0;
        int k = 0;
        for (int num : data) {
            if (num == 1) k++;
        }
        int left = 0, right = 0;
        int cnt = 0;
        int minCnt = Integer.MAX_VALUE;

        int n = data.length;
        while (right < n) {
            if (data[right] == 0) cnt++;
            while (right - left + 1 == k) {
                minCnt = Math.min(minCnt, cnt);
                if (data[left] == 0) cnt--;
                left++;
            }
            right++;
        }
        return minCnt == Integer.MAX_VALUE ? 0 : minCnt;
    }

    public static void main(String[] args) {
        int[] arr1 = {1, 0, 1, 0, 1, 0, 0, 1, 1, 0, 1};
        int[] arr2 = {1, 0, 1, 0, 1};

        System.out.println(minSwaps(arr1)); // 3
        System.out.println(minSwaps(arr2)); // 1
    }
}
