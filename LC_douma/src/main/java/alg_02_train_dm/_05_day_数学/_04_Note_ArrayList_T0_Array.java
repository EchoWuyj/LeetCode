package alg_02_train_dm._05_day_数学;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @Author Wuyj
 * @DateTime 2023-04-20 14:40
 * @Version 1.0
 */
public class _04_Note_ArrayList_T0_Array {

    public static void test() {
        ArrayList<String> list = new ArrayList<String>();
        list.add("apple");
        list.add("banana");
        list.add("orange");
        String[] array = list.toArray(new String[list.size()]);

        // KeyPoint 创建数组只能引用数据类型，不能是基本数据类型，Integer √ int ×

        // 在上面的代码中，list.toArray() 方法将 ArrayList 转换为 Object[] 类型的数组，
        // 然后使用 new String[list.size()] 创建了一个与 ArrayList 元素数量相等的 String 类型的新数组，
        // 最后将 Object[] 类型的数组转换为 String[] 类型的数组并将其赋值给 array 变量。

        System.out.println(Arrays.toString(array)); // [apple, banana, orange]
    }

    public static void test1() {
        int[] arr = {1, 2, 3, 4, 5};
        // Arrays.toString() 方法将数组转换为字符串，并在每个元素之间添加逗号和空格
        System.out.println(Arrays.toString(arr)); // [1, 2, 3, 4, 5]
    }

    public static void main(String[] args) {
        test();
        test1();
    }
}
