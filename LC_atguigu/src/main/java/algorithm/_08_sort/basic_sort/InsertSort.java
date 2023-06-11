package algorithm._08_sort.basic_sort;

/**
 * @Author Wuyj
 * @DateTime 2022-03-22 20:59
 * @Version 1.0
 */
public class InsertSort {
    public void insertSort(int[] arr) {
        //特殊情况的处理
        if (arr == null) {
            return;
        }

        int len = arr.length;
        int preIndex, current;

        for (int i = 1; i < len; i++) {
            //第一个元素
            preIndex = i - 1;
            //当前元素
            current = arr[i];

            //如果该元素(已排序)大于当前元素
            while (preIndex >= 0 && arr[preIndex] > current) {
                //在while成立的条件下,将已排序元素后移一位
                arr[preIndex + 1] = arr[preIndex];
                preIndex--;
            }
            //当arr[preIndex](已排序元素)<=current时,将当前元素插入到arr[preIndex]的后面一个位置
            arr[preIndex + 1] = current;
        }
    }

    public static void main(String[] args) {
        int[] arr = {55, 44, 66, 50, 22, 11};
        for (int i : arr) {
            System.out.print(i + "\t");
        }
        System.out.println();
        System.out.println("排序之后");

        InsertSort insertSort = new InsertSort();
        insertSort.insertSort(arr);

        for (int i : arr) {
            System.out.print(i + "\t");
        }
    }
}
