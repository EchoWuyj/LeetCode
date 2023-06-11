package alg_01_ds_dm._01_line._01_array;

/**
 * @Author Wuyj
 * @DateTime 2023-03-08 18:53
 * @Version 1.0
 */
public class ArrayListTest_02 {
    public static void main(String[] args) {

        // 使用dm_01_ds._01_array包下的ArrayList
        ArrayList<Integer> arrayList = new ArrayList<>(10);
        System.out.println(arrayList.isEmpty()); // true

        arrayList.addFirst(34);
        arrayList.addLast(23);
        arrayList.add(2, 50);
        System.out.println(arrayList.isEmpty()); // false
        System.out.println(arrayList); // Array: size = 3, capacity = 10, [34,23,50]

        arrayList.remove(1);
        System.out.println(arrayList); // Array: size = 2, capacity = 5, [34,50]

        arrayList.removeElement(1);  // 删除元素值1，因为 arr 中没有该元素，所以没有删除
        System.out.println(arrayList); // Array: size = 2, capacity = 5, [34,50]

        ArrayList<String> arrayList1 = new ArrayList<>(10);
        arrayList1.add(0, "hello");
        arrayList1.addLast("world");
        System.out.println(arrayList1); // Array: size = 2, capacity = 10, [hello,world]
    }
}
