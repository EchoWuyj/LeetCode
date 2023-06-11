package alg_01_新手班_wyj.class08;

/**
 * @Author Wuyj
 * @DateTime 2022-09-12 23:38
 * @Version 1.0
 */
public class Code03_PartitionAndQuickSort {

    public static void splitNum1(int[] arr) {
        int lessEqualR = -1;
        int index = 0;
        int mostRight = arr.length - 1;

        while (index < arr.length) {
            if (arr[index] <= arr[mostRight]) {
                swap(arr, lessEqualR + 1, index);
                index++;
                lessEqualR++;
            } else {
                index++;
            }
        }
    }

    public static void splitNum2(int[] arr) {
        int N = arr.length;
        int index = 0;
        int lessR = -1;
        int moreL = arr.length - 1;

        while (index < moreL) {
            if (arr[index] < arr[N - 1]) {
                swap(arr, ++lessR, index++);
            } else if (arr[index] > arr[N - 1]) {
                swap(arr, --moreL, index);
            } else {
                index++;
            }
        }
        swap(arr, moreL, N - 1);
    }

    public static void swap(int[] arr, int L, int R) {
        int temp = arr[L];
        arr[L] = arr[R];
        arr[R] = temp;
    }

    public static int[] partition(int[] arr, int L, int R) {
        int lessR = L - 1;
        int moreL = R;
        int index = L;
        while (index < moreL) {
            if (arr[index] < arr[R]) {
                swap(arr, ++lessR, index++);
            } else if (arr[index] > moreL) {
                swap(arr, --moreL, index);
            } else {
                index++;
            }
        }
        swap(arr, R, moreL);
        return new int[]{lessR + 1, moreL};
    }

    public static void process(int[] arr, int L, int R) {
        if (L >= R) {
            return;
        }

        int[] equalE = partition(arr, L, R);

        process(arr, L, equalE[0] - 1);
        process(arr, equalE[1] + 1, R);
    }

    public static void quickSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process(arr, 0, arr.length - 1);
    }

    // ------------------------------------------------

    public static class Job {
        public int L;
        public int R;

        public Job(int left, int right) {

        }
    }

    public static void main(String[] args) {
        int[] arr = {2, 5, 1, 3, 4};
        splitNum1(arr);
        for (int num : arr) {
            System.out.print(num + " ");
        }
    }
}
