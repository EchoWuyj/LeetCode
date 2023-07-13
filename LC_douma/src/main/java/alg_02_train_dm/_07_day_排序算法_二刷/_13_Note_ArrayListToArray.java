package alg_02_train_dm._07_day_排序算法_二刷;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @Author Wuyj
 * @DateTime 2023-04-20 14:40
 * @Version 1.0
 */
public class _13_Note_ArrayListToArray {

    public static void test() {
        ArrayList<String> list = new ArrayList<String>();
        list.add("apple");
        list.add("banana");
        list.add("orange");
        String[] array = list.toArray(new String[list.size()]);

        // KeyPoint 集合 => 数组
        // 创建数组只能引用数据类型，不能是基本数据类型
        // => Integer[] √ int[] ×
        // list.toArray()
        // => list<String>集合 转换为 String[] 类型的数组，数组大小为 list.size()
        System.out.println(Arrays.toString(array)); // [apple, banana, orange]
    }

    public static void test1() {
        int[] arr = {1, 2, 3, 4, 5};
        // Arrays.toString() 方法将数组转换为字符串，并在每个元素之间添加 逗号 和 空格
        System.out.println(Arrays.toString(arr)); // [1, 2, 3, 4, 5]
    }

    public static void main(String[] args) {
        test();
        test1();
    }
}
