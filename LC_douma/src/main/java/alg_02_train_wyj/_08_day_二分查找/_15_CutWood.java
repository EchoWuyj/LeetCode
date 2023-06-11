package alg_02_train_wyj._08_day_二分查找;

/**
 * @Author Wuyj
 * @DateTime 2023-04-06 15:32
 * @Version 1.0
 */
public class _15_CutWood {
    public static int cutWood1(int k, int[] nums) {
        int maxValue = Integer.MIN_VALUE;
        for (int num : nums) {
            maxValue = Math.max(num, maxValue);
        }

        int maxM = 0;
        for (int m = 1; m <= maxValue; m++) {
            int cnt = 0;
            for (int i = 0; i < nums.length; i++) {
                cnt += nums[i] / m;
            }
            if (cnt >= k) {
                maxM = Math.max(maxM, m);
            }
        }
        return maxM;
    }

    public static int cutWood(int k, int[] nums) {
        int maxValue = Integer.MIN_VALUE;
        for (int num : nums) {
            maxValue = Math.max(maxValue, num);
        }
        int left = 1, right = maxValue;
        while (left < right) {
            int mid = left + (right - left - 1) / 2;
            if (calWoodCnt(mid, nums) < k) {
                right = mid - 1;
            } else { // calWoodCnt(mid, nums) >= k
                left = mid;
            }
        }
        return left;
    }

    private static int calWoodCnt(int mid, int[] nums) {
        int cnt = 0;
        for (int i = 0; i < nums.length; i++) {
            cnt += nums[i] / mid;
        }
        return cnt;
    }

    public static void main(String[] args) {
        int k = 5;
        int[] nums = {4, 7, 2, 10, 5};
        System.out.println(cutWood1(5, nums));
        System.out.println("============");
        System.out.println(cutWood(5, nums));
    }
}
