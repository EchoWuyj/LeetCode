package alg_01_新手班_wyj.class01;

/**
 * @Author Wuyj
 * @DateTime 2022-09-06 16:42
 * @Version 1.0
 */
public class Code01_PrintBinary {
    public static void print(int num) {
        for (int i = 31; i >= 0; i--) {
            System.out.print(((num & (1 << i)) == 0) ? "0" : "1");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int num = 0;
        print(num);
        print(Integer.MAX_VALUE); // -2147483648

        System.out.println(Integer.MIN_VALUE); // -2147483648
    }

}
