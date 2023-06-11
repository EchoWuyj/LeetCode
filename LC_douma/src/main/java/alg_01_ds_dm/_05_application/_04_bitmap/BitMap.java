package alg_01_ds_dm._05_application._04_bitmap;

/**
 * @Author Wuyj
 * @DateTime 2023-03-26 15:34
 * @Version 1.0
 */

// KeyPoint 应用场景
//  给你 1 千万个整数，整数的范围在 1 到 1 亿之间，
//  如何快读的判断某个整数是否在这个 1 千万个整数中呢?

// 位图：整数范围有多大，使用相应位数来标识对应的整数是否存在
//      存在标记 1，不存在标记为 0，判断数字是否在整数数组中，则检测数字对应的位即可
public class BitMap {
    // 一个字节，8 bit，用来存储 2，7，2，5，3 这几个数字
    private byte b;

    public BitMap() {
        b = 0;
    }

    // KeyPoint 1 将指定整数存储到位图
    public void set(int num) {
        /*
            set 2
            00000001 << 2
            00000100
          | 00000000   num 位为 0 的情况
          -----------
          = 00000100

            set 2
            00000001 << 2
            00000100
          | 00000100   num 位为 1 的情况
          -----------
          = 00000100
         */
        b |= (1 << num);
    }

    // KeyPoint 2 判断指定整数是否在位图中
    public boolean contains(int target) {
        /*
            存完数字 2，7，2，5，3 后，b 10101100
            调用 contains 4
            00000001 << 4
            00010000
          & 10101100
          ----------
          = 00000000
         */

        // 1 << target 除了对应的位为 1，其余都是为 0 的，因而对应的位数不管 0 还是 1和 0 相与，结果都为 0
        // 关键看 target 位相与操作之后的结果，只要结果不为 0，则说明 target 在位图中是 1，表示对应的整数存在
        return ((1 << target) & b) != 0;
    }

    @Override
    public String toString() {
        // 获取 b 的低 8 位
        return "BitMap { " +
                "b = " + Integer.toBinaryString(b).substring(24) +
                " }";
        // public String substring(int beginIndex) beginIndex 到字符串最后
        // public String substring(int beginIndex, int endIndex) endIndex 不包括
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
