package alg_02_体系班_wyj.class02;

/**
 * @Author Wuyj
 * @DateTime 2022-09-14 21:37
 * @Version 1.0
 */
public class Code03_KM_01 {

    // one k other m
    public static int onlyKTimes(int[] arr, int k, int m) {

        int[] temp = new int[32];

        for (int num : arr) {
            for (int i = 0; i <= 31; i++) {
                temp[i] += (num >> i) & 1;
            }
        }

        int ans = 0;
        for (int i = 0; i < 32; i++) {
            if (temp[i] % m != 0) {
                ans |= (1 << i);
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        int[] arr = {4, 4, 3, 3, 3, 2, 2, 2};
        int k = 2;
        int m = 3;
        System.out.println(onlyKTimes(arr, 2, 3));
    }
}
