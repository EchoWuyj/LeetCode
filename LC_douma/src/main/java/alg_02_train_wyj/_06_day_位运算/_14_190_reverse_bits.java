package alg_02_train_wyj._06_day_位运算;

/**
 * @Author Wuyj
 * @DateTime 2023-04-23 17:08
 * @Version 1.0
 */
public class _14_190_reverse_bits {

    public int reverseBits1(int n) {
        int res = 0;
        for (int i = 0; i < 32; i++) {
            res = (res << 1) | (n & 1);
            n >>>= 1;
        }
        return res;
    }

    public int reverseBits2(int n) {

        int M1 = 0x55555555;
        int M2 = 0x33333333;
        int M3 = 0x0f0f0f0f;
        int M4 = 0x00ff00ff;

        n = ((n >>> 1) & M1) | ((n & M1) << 1);
        n = ((n >>> 2) & M2) | ((n & M2) << 2);
        n = ((n >>> 4) & M3) | ((n & M3) << 4);
        n = ((n >>> 8) & M4) | ((n & M4) << 8);
        n = ((n >>> 16)) | (n << 16);

        return n;
    }
}
