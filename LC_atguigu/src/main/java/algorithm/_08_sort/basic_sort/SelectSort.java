package algorithm._08_sort.basic_sort;

/**
 * @Author Wuyj
 * @DateTime 2022-03-22 19:52
 * @Version 1.0
 */
public class SelectSort {
    public void selectSort(int[] arr) {
        //特殊条件的判断
        if (arr == null) return;
        int n = arr.length;

        //依次从后面序列中选择当前最小的元素作为第i个元素,最后一个元素不需要排序
        for (int i = 0; i < n - 1; i++) {
            //min存的是当前最小元素所在下标,初值设为第i个
            int min = i;
            //从第i个元素往后找,一直要找到最后一个元素
            for (int j = i + 1; j < n; j++) {
                //如果这个值更小,则更新min值为这个更小的元素所在下标
                if (arr[j] < arr[min]) {
                    min = j;
                }
            }
            //如果第i个元素不是剩下元素最小的,则和最小的进行交换
            if (min != i) {
                int temp = arr[i];
                arr[i] = arr[min];
                arr[min] = temp;
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {55, 44, 33, 66, 22, 11};
        for (int i : arr) {
            System.out.print(i + "\t");
        }
        System.out.println();
        System.out.println("排序之后");

        SelectSort selectSort = new SelectSort();
        selectSort.selectSort(arr);

        for (int i : arr) {
            System.out.print(i + "\t");
        }
    }
}
