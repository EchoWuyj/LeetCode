package alg_01_ds_wyj._01_line._01_array;

/**
 * @Author Wuyj
 * @DateTime 2023-03-09 15:38
 * @Version 1.0
 */
public class ArrayTest {
    public static void main(String[] args) {
        ArrayList<Integer> data = new ArrayList<>(7);
        data.addLast(56);
        data.addLast(12);
        data.addLast(33);
        data.addLast(24);
        data.addLast(45);
        data.addLast(90);
        data.addLast(77);
        System.out.println(data);

        data.addLast(23);
        System.out.println(data);

        data.removeLast();
        data.removeLast();
        System.out.println(data);

        ArrayList<Integer> d = new ArrayList<Integer>();
        int[] dd = new int[2];
    }
}
