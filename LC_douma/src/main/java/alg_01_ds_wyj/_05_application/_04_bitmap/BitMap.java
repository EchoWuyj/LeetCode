package alg_01_ds_wyj._05_application._04_bitmap;

/**
 * @Author Wuyj
 * @DateTime 2023-03-27 16:25
 * @Version 1.0
 */
public class BitMap {
    private byte b;

    public BitMap() {
        b = 0;
    }

    public void set(int num) {
        b |= (1 << num);
    }

    public boolean contains(int target) {
        return ((1 << target) & b) != 0;
    }

    @Override
    public String toString() {
        return "BitMap {" + " b = " + Integer.toBinaryString(b).substring(24) + " }";
    }

    public static void main(String[] args) {
        int[] data = new int[]{2, 7, 2, 5, 3};
        int target = 2;

        BitMap bitMap = new BitMap();
        for (int i = 0; i < data.length; i++) {
            bitMap.set(data[i]);
        }

        System.out.println(bitMap);

        if (bitMap.contains(target)) {
            System.out.println("存在目标值：" + target);
        }
    }
}
