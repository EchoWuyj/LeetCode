package alg_01_ds_wyj._01_line._01_array;

/**
 * @Author Wuyj
 * @DateTime 2023-03-09 15:31
 * @Version 1.0
 */
public class ArrayListTest {
    public static void main(String[] args) {
        ArrayList<Integer> arrayList = new ArrayList<>(10);
        System.out.println(arrayList.isEmpty());

        arrayList.addFirst(34);
        arrayList.addLast(23);
        arrayList.add(2, 50);
        System.out.println(arrayList.isEmpty());
        System.out.println(arrayList);

        arrayList.remove(1);
        System.out.println(arrayList);

        arrayList.removeElement(1);
        System.out.println(arrayList);

        ArrayList<String> arrayList1 = new ArrayList<>(10);
        arrayList1.add(0, "hello");
        arrayList1.addLast("world");
        System.out.println(arrayList1);
    }
}
