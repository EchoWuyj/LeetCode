package alg_01_新手班_zcy.class01;

/**
 * @Author Wuyj
 * @DateTime 2022-08-30 13:40
 * @Version 1.0
 */
public class Code06_InsertionSort {
    public static void main(String[] args) {
        int[] arr = {7, 1, 3, 5, 1, 6, 8, 1, 3, 5, 7, 5, 6};
        printArray(arr);
        insertionSort02(arr);
        printArray(arr);
    }

    // 交换arr的i和j位置上的值
    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // 插入排序(实现一)(推荐)
    public static void insertionSort01(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }

        int N = arr.length;
        // 外层for循环,待比较的索引依次从1一直到N-1,而索引为0就一个元素默认其是有序的
        // 索引后移而产生的新增数组元素去判断应该插入的位置
        for (int end = 1; end < N; end++) {
            // 记录新加入的元素索引位置
            int newNumIndex = end;
            // newNumIndex -1 >= 0 保证自减不越界
            // 前一个元素大于后面一个元素
            while (newNumIndex - 1 >= 0 && arr[newNumIndex - 1] > arr[newNumIndex]) {
                // 交换之后,新加入的元素已经不在原来位置了,再和前面的数字进行比较,所以是整体索引迁移
                swap(arr, newNumIndex - 1, newNumIndex);
                // 向前移动一位
                newNumIndex--;
            }
        }
    }

    // 简化一下
    // 插入排序(实现一)
    public static void insertionSort02(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int N = arr.length;
        // KeyPoint 使用end见名知意
        for (int end = 1; end < N; end++) {
            // 将原来while循环替换成for循环
            // KeyPoint pre=end-1,就已经表示表示新数的前一个位置,不再需要pre-1来表示了
            for (int pre = end - 1; pre >= 0 && arr[pre] > arr[pre + 1]; pre--) {
                swap(arr, pre, pre + 1);
            }
        }
    }
}
