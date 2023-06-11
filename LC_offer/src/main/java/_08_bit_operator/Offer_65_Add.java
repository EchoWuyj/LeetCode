package _08_bit_operator;

/**
 * @Author Wuyj
 * @DateTime 2022-09-20 11:55
 * @Version 1.0
 */
public class Offer_65_Add {
    public int add(int a, int b) {
        int sum = a;
        while (b != 0) {
            sum = a ^ b;
            b = (a & b) << 1;
            a = sum;
        }

        return sum;
    }
}
