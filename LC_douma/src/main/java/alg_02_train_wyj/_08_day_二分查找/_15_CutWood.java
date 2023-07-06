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
            maxValue = Math.max(maxValue, num);
        }

        int maxM = 0;
        for (int i = 1; i <= maxValue; i++) {
            int cnt = 0;
            for (int num : nums) {
                cnt += num / i;
            }
            if (cnt >= k) maxM = Math.max(maxM, i);
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
            int mid = left + (right - left + 1) / 2;
            if (k > calWoodCnt(mid, nums)) {
                right = mid - 1;
            } else {
                left = mid;
            }
        }
        return left;
    }

    private static int calWoodCnt(int mid, int[] nums) {
        int cnt = 0;
        for (int num : nums) {
            cnt += num / mid;
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
