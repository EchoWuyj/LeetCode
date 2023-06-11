package alg_01_ds_dm._01_line._01_array;

/**
 * @Author Wuyj
 * @DateTime 2023-03-08 19:42
 * @Version 1.0
 */
public class ArrayListTest_01 {
    public static void main(String[] args) {

        // 使用dm_01_ds._01_array包下的ArrayList
        ArrayList<Integer> data = new ArrayList<>(7);
        data.addLast(56);
        data.addLast(12);
        data.addLast(33);
        data.addLast(24);
        data.addLast(45);
        data.addLast(90);
        data.addLast(77);
        System.out.println(data); // Array: size = 7, capacity = 7, [56,12,33,24,45,90,77]

        data.addLast(23);
        System.out.println(data); // Array: size = 8, capacity = 14, [56,12,33,24,45,90,77,23]

        data.removeLast();
        data.removeLast();
        System.out.println(data); // Array: size = 6, capacity = 14, [56,12,33,24,45,90]

        ArrayList<Integer> d = new ArrayList<Integer>();
        int[] dd = new int[2];
    }
}
