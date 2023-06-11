package alg_02_体系班_zcy.class01;

/**
 * @Author Wuyj
 * @DateTime 2022-09-09 23:14
 * @Version 1.0
 */
public class Code06_BSAwesome {
    public static int getLessIndex(int[] arr) {

        // 二分不一定有序,在数据状况特殊的情况下
        // 只要能构建一种排他性的东西,左右两侧有一侧肯定有,砍一半

        // 几种边界情况
        if (arr == null || arr.length == 0) {
            return -1; // no exist
        }
        if (arr.length == 1 || arr[0] < arr[1]) {
            return 0;
        }
        if (arr[arr.length - 1] < arr[arr.length - 2]) {
            return arr.length - 1;
        }

        // 这里避免数组中mid-1或者mid+1的越界
        // 即左右的指针left和right不是从首尾两端直接开始,而是跳过一个元组元素
        int left = 1;
        int right = arr.length - 2;
        int mid = 0;

        // L..R 至少两个数的时候
        // 边界状态L,R相邻.mid且和left和right其中一个重合
        while (left < right) {
            mid = (left + right) / 2;
            // 与arr[mid]比较,保留比arr[mid]小的部分
            // arr[mid]比前一个元素大,则保留比arr[mid]小的左半部分
            if (arr[mid] > arr[mid - 1]) {
                right = mid - 1;
                // arr[mid]比后一个元素大,则保留比arr[mid]小的右半部分
            } else if (arr[mid] > arr[mid + 1]) {
                left = mid + 1;
            } else {
                // else语句是上面两个判断条件的相反方向,同时加上相邻不相等的原始定义
                // 则可以推倒出:arr[mid] < arr[mid - 1] && arr[mid] < arr[mid + 1]
                // 即满足局部最小的定义
                return mid;
            }
        }
        // left<right不满足说明,此时left=right,返回的就是局部最小
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
