package alg_01_新手班_zcy.class05;

import java.util.HashSet;

/**
 * @Author Wuyj
 * @DateTime 2022-09-02 9:36
 * @Version 1.0
 */
public class Code01_BitMap1 {

    // 位图
    // 功能:对于确定范围的数字,将其做成一个集合
    //      通过该集合收集的数字,能确定数字是否存在
    // 好处:极大压缩空间
    // 实现:如下代码

    public static class BitMap {

        // 使用long数据类型来存储,一个long是64位,使用每一位0-63表示0-63数字是否出现
        // 将出现的数字对应位标记为1即可,一共可以表示64个数字是否出现

        // 第5位数值1表示出现过数值5
        // 第10位数值1表示出现过数值10
        private long[] bits;

        // 位图表示是否存在的数值,数值个数最大为max
        public BitMap(int max) {
            // >>6相当与/64(2^6)
            // 确定需要几个long数值来表示,从而开辟相应长度的数组
            // 0,1个数字,(0+64)/64=1个long来表示
            // 0~64个数字,(64+64)/64=2个long来表示
            bits = new long[(max + 64) >> 6];
        }

        // KeyPoint 将num加入位图bits中,即该num在位图bits存在,不是传统意义上的加操作
        // 思路:
        // 先确定long[]数组上那个整数上位置会有num,通过num>>6确定下标
        //      170/64=2点多,则下标为2
        // 再去确定是该整数的第第几位,num%64
        //      170%64=42位,该位表示170是否存在
        // 最后将其置为1表示其存在
        public void add(int num) {

            // num>>6等价于num/64
            // num&63等价于num%64
            //      num%64剩下数字只能是0-63,在二进制上表示为7位
            //      故等价于num&63(111 1111)将后7位保留,高位不要
            // 注意只是适用2^n的情况

            //  &和|常见运算
            //  任何数 &0 都是0(有0则为0)
            //  任何数 &1 都是自身
            //  助记:&可以理解乘
            //  任何数 |1 都是1(有1则为1)

            // |=(或等)等价于代表将该位置1

            // KeyPoint 数据类型一定需要对上!
            //  Long数据类型需要加上L,不然默认1是int数据类型,此时1只有32位
            //  位运算的代码一旦出错很难调试

            bits[num >> 6] |= (1L << (num & 63));
        }

        // 将num对应位上置为0即可
        public void delete(int num) {

            // &0将对应位变成0
            // &1其余的位数保持不变
            bits[num >> 6] &= ~(1L << (num & 63));
        }

        public boolean contains(int num) {
            // 对应位上和1进行&运算,完结果为1,不为0,则表示该位为1
            // KeyPoint 整体表达式需要加个括号
            return (bits[num >> 6] & (1L << (num & 63))) != 0;
        }
    }

    public static void main(String[] args) {
        System.out.println("测试开始！");
        int max = 10000;
        BitMap bitMap = new BitMap(max);
        HashSet<Integer> set = new HashSet<>();
        int testTime = 10000000;
        for (int i = 0; i < testTime; i++) {
            int num = (int) (Math.random() * (max + 1));
            double decide = Math.random();
            if (decide < 0.333) {
                bitMap.add(num);
                set.add(num);
            } else if (decide < 0.666) {
                bitMap.delete(num);
                set.remove(num);
            } else {
                if (bitMap.contains(num) != set.contains(num)) {
                    System.out.println("Oops!");
                    break;
                }
            }
        }
        for (int num = 0; num <= max; num++) {
            if (bitMap.contains(num) != set.contains(num)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("测试结束！");
    }
}
