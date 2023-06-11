package alg_01_ds_dm._05_application._04_bitmap;

/**
 * @Author Wuyj
 * @DateTime 2023-03-26 15:34
 * @Version 1.0
 */

public class BitMap1 {
    private byte[] bytes;
    // 存储的位数，通过构造方法将其传入
    private int nBits;

    public BitMap1(int nBits) {
        this.nBits = nBits;
        // nBits 不一定整除 8，可能存在多余的几个位，故需要多加 1 字节
        this.bytes = new byte[nBits / 8 + 1];
    }

    // KeyPoint 1 将指定整数存储到位图
    public void set(int num) {
        if (num > nBits) return;

        // KeyPoint 可以通过最简单的例子进行验证
        //  num = 3，byteIndex = 0，bitIndex = 3

        // 找到第几个字节，8 bit 一组，找到具体那一组
        int byteIndex = num / 8;
        // 找到第几位
        int bitIndex = num % 8;
        bytes[byteIndex] |= (1 << bitIndex);
    }

    // KeyPoint 2 判断指定整数是否在位图中
    public boolean contains(int target) {
        if (target > nBits) return false;
        // 找到第几个字节
        int byteIndex = target / 8;
        // 找到第几位
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
