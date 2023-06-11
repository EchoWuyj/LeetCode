package alg_01_新手班_wyj.class03;

/**
 * @Author Wuyj
 * @DateTime 2022-09-08 11:14
 * @Version 1.0
 */
public class Code04_BSAwesome {
    public static int oneMinIndex(int[] arr) {
        if (arr == null || arr.length == 0) {
            return -1;
        }

        int N = arr.length;

        if (N == 1) {
            return 0;
        }

        if (arr[0] < arr[1]) {
            return 0;
        }

        if (arr[N - 2] > arr[N - 1]) {
            return N - 1;
        }

        int L = 0;
        int R = N - 1;

        while (L < R - 1) {
            int mid = L + ((R - L) >> 1);
            if (arr[mid - 1] > arr[mid] && arr[mid] < arr[mid + 1]) {
                return mid;
            } else if (arr[mid - 1] < arr[mid]) {
                R = mid - 1;
            } else {
                L = mid + 1;
            }
        }

        return arr[L] < arr[R] ? L : R;
    }

    // 生成随机数组，且相邻数不相等
    public static int[] randomArray(int maxLen, int maxValue) {
        int len = (int) (maxLen * Math.random());
        int[] arr = new int[len];

        if (len > 0) {
            arr[0] = (int) (Math.random() * maxValue);
            //
            for (int i = 1; i < len; i++) {
                do {
                    arr[i] = (int) (Math.random() * maxValue);
                    // 保证相邻不相等
                    // 因为涉及i-1,所以先随机arr[0],保证for循环从i开始,避免索引越界
                } while (arr[i] == arr[i - 1]);
            }
        }
        return arr;
    }

    public static boolean check(int[] arr, int minIndex) {
        if (arr.length == 0) {
            return -1 == minIndex;
        }

        int left = minIndex - 1;
        int right = minIndex + 1;

        boolean leftBigger = (left >= 0) ? arr[left] > arr[minIndex] : true;
        boolean rightBigger = (right < arr.length) ? arr[right] > arr[minIndex] : true;
        return leftBigger && rightBigger;
    }

    public static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {

        int maxLen = 10000;
        int maxValue = 200;
        int testTime = 100000;

        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr = randomArray(maxLen, maxValue);
            int ans = oneMinIndex(arr);
            if (!check(arr, ans)) {
                printArray(arr);
                System.out.println(ans);
                break;
            }
        }
        System.out.println("测试结束");
    }
}
