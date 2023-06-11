package _08_bit_operator;

/**
 * @Author Wuyj
 * @DateTime 2022-09-14 19:51
 * @Version 1.0
 */
public class Offer_15_HammingWeight {

    // 二进制中1的个数
    // 编写一个函数,输入是一个无符号整数(以二进制串的形式)
    // 回其二进制表达式中数字位数为 '1' 的个数(也被称为汉明重量)

    public int hammingWeight(int n) {
        int res = 0;
        for (int i = 0; i <= 31; i++) {
            if (((n >> i) & 1) == 1) {
                res++;
            }
        }
        return res;
    }
}
