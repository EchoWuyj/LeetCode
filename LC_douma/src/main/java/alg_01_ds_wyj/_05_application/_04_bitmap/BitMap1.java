package alg_01_ds_wyj._05_application._04_bitmap;

/**
 * @Author Wuyj
 * @DateTime 2023-03-27 16:44
 * @Version 1.0
 */
public class BitMap1 {
    private byte[] bytes;
    private int nBits;

    public BitMap1(int nBits) {
        this.nBits = nBits;
        this.bytes = new byte[nBits / 8 + 1];
    }

    public void set(int num) {
        if (num > nBits) return;
        int byteIndex = num / 8;
        int bitIndex = num % 8;
        bytes[byteIndex] |= (1 << bitIndex);
    }

    public boolean contains(int target) {
        if (target > nBits) return false;
        int byteIndex = target / 8;
        int bitIndex = target % 8;
        return ((1 << bitIndex) & bytes[byteIndex]) != 0;
    }

    public static void main(String[] args) {
        int[] data = new int[]{2, 7, 2, 5, 3};
        int target = 2;

        BitMap1 bitMap1 = new BitMap1(8);
        for (int i = 0; i < data.length; i++) {
            bitMap1.set(data[i]);
        }

        if (bitMap1.contains(target)) {
            System.out.println("存在目标值：" + target);
        }
    }
}
