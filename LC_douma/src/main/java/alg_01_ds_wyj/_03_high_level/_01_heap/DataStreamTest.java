package alg_01_ds_wyj._03_high_level._01_heap;

import alg_01_ds_dm._03_high_level._01_heap.DataStream1;

/**
 * @Author Wuyj
 * @DateTime 2023-03-16 18:48
 * @Version 1.0
 */
public class DataStreamTest {
    public static void main(String[] args) {
        test1();
        System.out.println("=============");
        test2();
        System.out.println("=============");
        test3();
    }

    private static void test3() {
        DataStream2 dataStream = new DataStream2();
        dataStream.add(3);
        System.out.println(dataStream.removeMax()); // 打印 3
        dataStream.add(6);
        dataStream.add(5);
        System.out.println(dataStream.removeMax()); // 打印 6
        dataStream.add(2);
        dataStream.add(1);
        System.out.println(dataStream.removeMax()); // 打印 5
    }

    private static void test2() {
        DataStream1 dataStream = new DataStream1();
        dataStream.add(3);
        System.out.println(dataStream.removeMax()); // 打印 3
        dataStream.add(6);
        dataStream.add(5);
        System.out.println(dataStream.removeMax()); // 打印 6
        dataStream.add(2);
        dataStream.add(1);
        System.out.println(dataStream.removeMax()); // 打印 5
    }

    private static void test1() {
        DataStream dataStream = new DataStream();
        dataStream.add(3);
        System.out.println(dataStream.removeMax()); // 打印 3
        dataStream.add(6);
        dataStream.add(5);
        System.out.println(dataStream.removeMax()); // 打印 6
        dataStream.add(2);
        dataStream.add(1);
        System.out.println(dataStream.removeMax()); // 打印 5
    }
}
