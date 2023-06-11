package alg_02_体系班_zcy.class04;

public class Code01_MergeSort {

    // 递归方法实现
    public static void mergeSort1(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process(arr, 0, arr.length - 1);
    }

    // 请把arr[L..R]排有序
    public static void process(int[] arr, int L, int R) {
        // base case
        // 问题小到什么规模就不需要划分了
        if (L == R) {
            return;
        }

        int mid = L + ((R - L) >> 1);
        process(arr, L, mid); // 规模N/2
        process(arr, mid + 1, R);// 规模N/2
        merge(arr, L, mid, R); // O(N)
    }

    // 归并
    public static void merge(int[] arr, int L, int M, int R) {
        int[] help = new int[R - L + 1];
        int i = 0;

        // 左右数组的指针
        // KeyPoint 对于给定的范围的数组L,R,其索引位置不再是从0开始的,避免思维误区
        int p1 = L;
        int p2 = M + 1;

        while (p1 <= M && p2 <= R) {
            // 取不取等都一样
            help[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
        }
        // 要么p1越界了,要么p2越界了
        while (p1 <= M) {
            help[i++] = arr[p1++];
        }
        while (p2 <= R) {
            help[i++] = arr[p2++];
        }

        // KeyPoint 多次犯错
        //  for循环中的i是从0开始的,但是数组arr[L,R]不是从0开始的,需要在0的基础上加上L
        for (i = 0; i < help.length; i++) {
            arr[L + i] = help[i];
        }
    }

    // mergeSort分析
    // l...R
    // T(N) = 2 * T(N / 2) + O(N) 套用Master公式
    // O(N*logN)

    // 归并排序 O(N*logN)优于O(N^2)
    // 本质:归并排序没有浪费比较行为,归并排序之后有序的形式留下来了,所以比较行为没有浪费
    // 而O(N^2)的排序每次比较都是独立行为,前一次的比较不影响后一次比较,这样就在浪费比较行为

    // -----------------------------------------------------------

    // 非递归方法实现
    public static void mergeSort2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }

        // 数组的大小
        int N = arr.length;

        // 整个归并的左组和右组是2倍的步长(mergeSize)
        // mergeSize表示左右两侧分别大小
        int mergeSize = 1;

        while (mergeSize < N) { // logN

            // 当前左组的第一个位置
            int L = 0;
            // 每个左组的第一个位置<N,表示不越界
            while (L < N) {

                // L...M 左组(mergeSize)
                // L~N-1一共有(N-1)-L+1=N-L个数

                // 凑不齐左右两组,则直接break掉
                if (mergeSize >= N - L) {
                    break;
                }

                // 能凑齐两组,再去计算M位置
                int M = L + mergeSize - 1;

                // 计算右组R的位置,分成凑够和凑不够的两种情况,直接Math.min简化
                // 够的情况直接为mergeSize大小,不够N-M-1

                // L...M M+1...R
                // M+1..N-1一共(N-1)-(M+1)+1=N-M-1个数
                int R = M + Math.min(mergeSize, N - M - 1);
                // 确定定好,左右组的索引范围,则使用merge函数
                merge(arr, L, M, R); // 每次merge操作都是O(N)
                L = R + 1;
            }
            // 防止溢出
            if (mergeSize > N / 2) {
                break;
            }
            mergeSize <<= 1;
        }
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        return arr;
    }

    // for test
    public static int[] copyArray(int[] arr) {
        if (arr == null) {
            return null;
        }
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i];
        }
        return res;
    }

    // for test
    public static boolean isEqual(int[] arr1, int[] arr2) {
        if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
            return false;
        }
        if (arr1 == null && arr2 == null) {
            return true;
        }
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    // for test
    public static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // for test
    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            mergeSort1(arr1);
            mergeSort2(arr2);
            if (!isEqual(arr1, arr2)) {
                System.out.println("出错了！");
                printArray(arr1);
                printArray(arr2);
                break;
            }
        }
        System.out.println("测试结束");
    }
}
