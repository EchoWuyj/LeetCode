package alg_02_体系班_wyj.class03;



/**
 * @Author Wuyj
 * @DateTime 2022-09-16 10:51
 * @Version 1.0
 */
public class Code08_GetMax {

    public static int getMax(int[] arr) {
        if (arr == null) {
            throw new RuntimeException("数组为空,没有最大值!");
        }

        if (arr.length == 1) {
            return arr[0];
        }

        int L = 0;
        int R = arr.length - 1;
        return process(arr, L, R);
    }

    public static int process(int[] arr, int L, int R) {
        if (L == R) {
            return arr[L];
        }

        int M = L + ((R - L) >> 1);

        int leftMax = process(arr, L, M);
        int rightMax = process(arr, M + 1, R);

        return Math.max(leftMax, rightMax);
    }

    public static void main(String[] args) {
        int[] arr = {3, 6, 2, 1, 4, 5, 6, 8};
        System.out.println(getMax(arr));
    }
}
