package algorithm._08_sort.basic_sort;

/**
 * @Author Wuyj
 * @DateTime 2022-03-22 19:35
 * @Version 1.0
 */
public class BubbleSort {
    public void bubbleSort(int[] arr) {
        //特殊情况的处理
        if (arr == null) {
            return;
        }

        //外层循环:控制的是求几次最大值(比较的轮数)
        //4个元素则比较3次,则for循环中count从1开始
        for (int count = 1; count < arr.length; count++) {
            //内层循环:求一次最大比较的次数
            //每次排序结束之后,剩余的数组需要比较的元素减1,故arr.length-count,数组的元素随着轮数的增多而减少
            for (int i = 0; i < arr.length - count; i++) {
                //前一个数比后面一个数要大,需要调换位置
                //这里没有arr[i+1]数组越界,i的最大值为3,i+1为4;
                if (arr[i] > arr[i + 1]) {
                    int temp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = temp;
                }
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

        BubbleSort bubbleSort = new BubbleSort();
        bubbleSort.bubbleSort(arr);

        for (int i : arr) {
            System.out.print(i + "\t");
        }
    }
}
