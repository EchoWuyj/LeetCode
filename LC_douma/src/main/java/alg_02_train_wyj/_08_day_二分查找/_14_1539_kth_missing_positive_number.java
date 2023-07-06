package alg_02_train_wyj._08_day_二分查找;

/**
 * @Author Wuyj
 * @DateTime 2023-04-05 19:52
 * @Version 1.0
 */
public class _14_1539_kth_missing_positive_number {
    public int findKthPositive1(int[] arr, int k) {
        int curNum = 1;
        int missCnt = 0;
        int LastNum = -1;

        int n = arr.length;
        int index = 0;
        while (missCnt < k) {
            if (index < n && arr[index] == curNum) {
                index++;
            } else {
                missCnt++;
                LastNum = curNum;
            }
            curNum++;
        }
        return LastNum;
    }

    public int findKthPositive(int[] arr, int k) {
        if (arr[0] > k) return k;
        int left = 0, right = arr.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (k > arr[mid] - mid - 1) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        int missCnt = arr[left - 1] - (left - 1) - 1;
        int res = arr[left - 1] + (k - missCnt);
        return res;
    }
}
