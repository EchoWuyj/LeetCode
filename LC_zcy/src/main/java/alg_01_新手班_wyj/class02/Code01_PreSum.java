package alg_01_新手班_wyj.class02;

/**
 * @Author Wuyj
 * @DateTime 2022-09-07 13:38
 * @Version 1.0
 */
public class Code01_PreSum {
    public static void main(String[] args) {

        int[] arr = {1, 2, 3, 4, 5};
        System.out.println(preSum1(arr, 2, 4));

        PreSum preSum = new PreSum(new int[]{1, 2, 3, 4, 5});
        System.out.println(preSum.preSum(2, 4));
    }

    public static int preSum1(int[] arr, int L, int R) {
        int sum = 0;
        for (int i = L; i <= R; i++) {
            sum += arr[i];
        }
        return sum;
    }

    public static class PreSum {
        int[] help;

        public PreSum(int[] arr) {
            help = new int[arr.length];
            help[0] = arr[0];
            for (int i = 1; i < arr.length; i++) {
                help[i] = help[i - 1] + arr[i];
            }
        }

        public int preSum(int L, int R) {
            return (L == 0) ? help[R] : (help[R] - help[L - 1]);
        }
    }
}
