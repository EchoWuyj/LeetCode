package alg_02_体系班_wyj.class01;

/**
 * @Author Wuyj
 * @DateTime 2022-09-14 17:05
 * @Version 1.0
 */
public class Code06_BSAwesome {
    public static int getLessIndex(int[] arr) {
        if (arr == null || arr.length == 0) {
            return -1;
        }

        if (arr.length == 1 || arr[0] < arr[1]) {
            return 0;
        }

        if (arr[arr.length - 1] < arr[arr.length - 2]) {
            return arr.length - 1;
        }

        int left = 1;
        int right = arr.length - 2;

        while (left < right) {
            int mid = left + ((right - left) >> 1);
            if (arr[mid] > arr[mid - 1]) {
                right = mid - 1;
            } else if (arr[mid] > arr[mid + 1]) {
                left = mid + 1;
            } else {
                return mid;
            }
        }

        return left;
    }

    // 生成随机数组，且相邻数不相等
    public static int[] randomArray(int maxLen, int maxValue) {
        int len = (int) (maxLen * Math.random());
        int[] arr = new int[len];

        if (len > 0) {

            // 先随机确定一个数,以其为基准保证后面的数不与其相同
            arr[0] = (int) (Math.random() * maxValue);
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

    //  验证传入的minIndex是否为局部最小位置
    public static boolean check(int[] arr, int minIndex) {
        if (arr.length == 0) {
            // 返回值为boolean值,判断minIndex是否为-1
            return minIndex == -1;
        }

        // 局部最小的左右位置
        int left = minIndex - 1;
        int right = minIndex + 1;

        // left>=0 说明 minIndex不是边界
        boolean leftBigger = (left >= 0) ? arr[left] > arr[minIndex] : true;
        boolean rightBigger = (right < arr.length) ? arr[right] > arr[minIndex] : true;

        // 注意:这里需要判断两个条件同时成立,而不是一个条件
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
            int ans = getLessIndex(arr);
            if (!check(arr, ans)) {
                printArray(arr);
                System.out.println(ans);
                break;
            }
        }
        System.out.println("测试结束");
    }
}
