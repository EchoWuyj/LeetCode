package alg_02_体系班_wyj.class02;

/**
 * @Author Wuyj
 * @DateTime 2022-09-14 19:21
 * @Version 1.0
 */
public class Code01_Swap {
    //
    public static void main(String[] args) {
        int i = 5;
        int j = 10;

        i = i ^ j;
        j = i ^ j;
        i = i ^ j;

        System.out.println(i);// 10
        System.out.println(j);// 5

        int[] array = {1, 2, 3};

        int a = 0;
        int b = 0;

        array[a] = array[a] ^ array[b];
        array[b] = array[a] ^ array[b];
        array[a] = array[a] ^ array[b];

        System.out.println(array[a] + "---" + array[b]);
    }
}
