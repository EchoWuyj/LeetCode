package alg_01_新手班_zcy.class01;

/**
 * @Author Wuyj
 * @DateTime 2022-08-30 11:20
 * @Version 1.0
 */
public class Code04_SelectionSort {
    public static void main(String[] args) {
        int[] arr = {7, 1, 3, 5, 1, 6, 8, 1, 3, 5, 7, 5, 6};
        printArray(arr);
        selectionSort(arr);
        printArray(arr);
    }

    public static void swap(int[] arr, int i, int j) {
        // KeyPoint 注意事项
        //  只是使用临时变量 int tmp 进行存储
        //  不需要定义 int temp =0 arr[temp] 这样方式
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
    //

    // 选择(最小)排序
    // 每次从未排序部分找出最小的,与当前未排序部分的第一个位置上的元素互换
    // 重复以上步骤,直至排序完毕
    public static void selectionSort(int[] arr) {

        // 先处理边界条件
        if (arr == null || arr.length < 2) {
            // 函数的返回为void,此时使用return表示终止函数
            return;
        }
        // 先在纸上将伪代码描述清楚,再去写代码
        int N = arr.length;
        // 外层for循环遍历数组中每个位置,找到每个位置上的最小值
        // 从前往后遍历
        for (int i = 0; i < N; i++) {
            int minValueIndex = i;
            // 内层for循环,将当前位置元素和数组中后面所有元素进行比较,获取最小值
            // 只有依次比较,比较完整个数组,才能获取最小值对应的索引
            for (int j = i + 1; j < N; j++) {
                minValueIndex = (arr[j] < arr[minValueIndex]) ? j : minValueIndex;
            }
            // 交换
            // 传入数组两个比较元素的两个索引即可,swap函数会根据索引交换其数值
            // 获取的最小值索引minValueIndex和当前位置i进行交换
            swap(arr, i, minValueIndex);
        }
    }
}
