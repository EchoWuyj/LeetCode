package alg_02_体系班_zcy.class02;

/**
 * @Author Wuyj
 * @DateTime 2022-09-14 11:46
 * @Version 1.0
 */
public class Code03_KM_01 {

    // 一个数组中有一种数出现K次,其他数都出现了M次,
    // 已知M>1,K<M,找到出现了K次的数(返回的是该数,不是次数)
    // 要求额外空间复杂度O(1),时间复杂度O(N)

    // KeyPoint 实现方式一(位运算)
    // 将int数转成32位二进制形式进行存储,通过判读32上1或0来实现
    public static int onlyKTimes(int[] arr, int k, int m) {

        // 准备一个辅助固定长度32位数组,所以额外的空间复杂度为O(1)
        int[] t = new int[32];

        // t[0] 0位置的1出现了几个
        // ...
        // t[i] i位置的1出现了几个
        for (int num : arr) {
            // 只是从31位开始,没有包括最高位
            // 因为不涉及输出,所以是正向for遍历
            for (int i = 0; i <= 31; i++) {
                // 注意这里不是直接赋值,而是+=,是一个迭代过程
                // 在t[i]迭代的过程累加
                t[i] += ((num >> i) & 1);
            }
        }

        int ans = 0;

        for (int i = 0; i < 32; i++) {

            // 取余的结果非0,则在第i位上是有1,不是==0
            if (t[i] % m != 0) {
                // 将对应i位设置为1,只能使用|(有1则1)
                // 置为1证明该位有即可,不用考虑个数的问题
                // 因为是循环遍历每一位,所以不会有遗漏的1,没有置为1
                // 所以由二进制构成数值本身不会错的
                ans |= (1 << i);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] arr = {4, 4, 3, 3, 3, 2, 2, 2};
        int k = 2;
        int m = 3;
        System.out.println(onlyKTimes(arr, k, m)); // 4
    }
}
