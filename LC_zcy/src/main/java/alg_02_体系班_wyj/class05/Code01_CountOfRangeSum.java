package alg_02_体系班_wyj.class05;

/**
 * @Author Wuyj
 * @DateTime 2022-09-17 13:31
 * @Version 1.0
 */
public class Code01_CountOfRangeSum {
    public static int countRangeSum(int[] nums, int lower, int upper) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        long[] preSum = new long[nums.length];

        preSum[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            preSum[i] = preSum[i - 1] + nums[i];
        }

        return process(preSum, 0, preSum.length - 1, lower, upper);
    }

    public static int process(long[] preSum, int L, int R, int lower, int upper) {
        if (L == R) {
            return (preSum[L] >= lower && preSum[L] <= upper) ? 1 : 0;
        }

        int mid = L + ((R - L) >> 1);
        return process(preSum, L, mid, lower, upper)
                + process(preSum, mid + 1, R, lower, upper)
                + merge(preSum, L, mid, R, lower, upper);
    }

    public static int merge(long[] preSum, int L, int M, int R, int lower, int upper) {
        // 处理逻辑
        int ans = 0;
        int windowL = L;
        int windowR = L;
        for (int i = M + 1; i <= R; i++) {
            long min = preSum[i] - upper;
            long max = preSum[i] - lower;
            while (windowL <= M && preSum[windowL] < min) {
                windowL++;
            }
            while (windowR <= M && preSum[windowR] <= max) {
                windowR++;
            }
            ans += (windowR - windowL);
        }

        // merge
        long[] help = new long[R - L + 1];
        int index = 0;
        int p1 = L;
        int p2 = M + 1;

        while (p1 <= M && p2 <= R) {
            help[index++] = (preSum[p1] < preSum[p2]) ? preSum[p1++] : preSum[p2++];
        }

        while (p1 <= M) {
            help[index++] = preSum[p1++];
        }

        while (p2 <= R) {
            help[index++] = preSum[p2++];
        }

        for (int i = 0; i < help.length; i++) {
            preSum[L + i] = help[i];
        }

        return ans;
    }
}
