package alg_02_体系班_zcy.class04;

public class Code03_ReversePair {

    // KeyPoint 总结
    //  使用mergeSort场景:计算每一个数右边(左边)有多少数(全要)比你大或者小

    // 在数组中的两个数字,如果前面一个数字大于后面的数字,
    // 则这两个数字组成一个逆序对。输入一个数组,求出这个数组中的逆序对的总数

    // [3,1,7,0,2] 求逆序对
    // [3,1] [3,0] [3,2]
    // [1,0]
    // [7,0] [7,2]
    // 等价于求一个数,右边多少个数比它小,此时该数是左组姿态

    // 操作步骤
    // 从右往左merge,所以左右两指针都是在最右侧位置
    //  1)左组数<右组数,先copy右组数到help数组,右指针左移
    //  2)左组数=右组数,先copy右组数到help数组,右指针左移
    //  3)左组数>右组数,先copy左组数到help数组,产生逆序对(根据右组元素个数计算逆序对个数),左指针左移

    public static int reversePairNumber(int[] arr) {
        if (arr == null || arr.length < 2) {
            // 返回0表示没有逆序对
            return 0;
        }
        return process(arr, 0, arr.length - 1);
    }

    // arr[L..R]既要排好序,也要求逆序对数量返回
    // 所有merge时,产生的逆序对数量,累加返回
    // 左排序,merge并产生逆序对数量
    // 右排序,merge并产生逆序对数量
    public static int process(int[] arr, int l, int r) {
        if (l == r) {
            // 递归的边界表示什么意思需要明确
            // 返回0表示没有逆序对
            return 0;
        }
        // l < r
        int mid = l + ((r - l) >> 1);
        return process(arr, l, mid) + process(arr, mid + 1, r) + merge(arr, l, mid, r);
    }

    public static int merge(int[] arr, int L, int m, int r) {
        int[] help = new int[r - L + 1];

        // 辅助数组的指针index,左右组的指针都是最右侧索引开始,向左移动
        // 注意不是再从左侧开始了,即p1,p2不是l和m+1
        int i = help.length - 1;
        int p1 = m;
        int p2 = r;

        int res = 0;
        while (p1 >= L && p2 > m) {
            res += arr[p1] > arr[p2] ? (p2 - m) : 0;
            help[i--] = arr[p1] > arr[p2] ? arr[p1--] : arr[p2--];
        }
        while (p1 >= L) {
            help[i--] = arr[p1--];
        }
        while (p2 > m) {
            help[i--] = arr[p2--];
        }
        for (i = 0; i < help.length; i++) {
            arr[L + i] = help[i];
        }
        return res;
    }

    // for test
    public static int comparator(int[] arr) {
        int ans = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    ans++;
                }
            }
        }
        return ans;
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
            if (reversePairNumber(arr1) != comparator(arr2)) {
                System.out.println("Oops!");
                printArray(arr1);
                printArray(arr2);
                break;
            }
        }
        System.out.println("测试结束");
    }
}
