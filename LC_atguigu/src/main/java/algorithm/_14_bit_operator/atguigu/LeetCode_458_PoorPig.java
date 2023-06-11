package algorithm._14_bit_operator.atguigu;

/**
 * @Author Wuyj
 * @DateTime 2022-04-01 14:35
 * @Version 1.0
 */
public class LeetCode_458_PoorPig {
    //多进制的编码问题
    public int poorPigs(int buckets, int minutesToDie, int minutesToTest) {
        int k = minutesToTest / minutesToDie;
        return (int) Math.ceil(Math.log(buckets) / Math.log(k + 1)); //整体向上取整后再强制类型转换变成int类型
    }

    public static void main(String[] args) {
        LeetCode_458_PoorPig poorPig = new LeetCode_458_PoorPig();

        System.out.println(poorPig.poorPigs(4, 15, 15));
        System.out.println(poorPig.poorPigs(4, 15, 30));
        System.out.println(poorPig.poorPigs(1000, 15, 60));
    }
}
