package alg_01_新手班_zcy.class03;

/**
 * @Author Wuyj
 * @DateTime 2022-09-01 0:01
 * @Version 1.0
 */
public class Code04_BSAwesome {

    // KeyPoint 题目3 局部最小值问题

    // 当前数组无序且任意两个相邻的数不相等,返回其中一个局部最小就行
    // 注意:一定是要保证相邻不等,如果不能保证相邻不等,比如3,1,1,1,2则中间是抹平的,则不存局部最小值

    // 定义局部最小值有三种情况,只要满足其中一种就是局部最小
    //     (1)0位置上面的数比1位置上面的数小，因为0左边没数
    //     (2)N-2位置>N-1位置，因为N-1位置右边没数
    //     (3)左边>i<右边 i位置既比左边小也比右边小

    // arr 整体无序
    // arr 相邻的数不相等！
    public static int oneMinIndex(int[] arr) {
        if (arr == null || arr.length == 0) {
            return -1;
        }

        int N = arr.length;

        // 一个数默认为局部小值
        if (N == 1) return 0;

        // 判断两头局部最小的情况,满足直接返回
        // N=2的情况直接包括在内
        if ((arr[0] < arr[1])) {
            return 0;
        }
        if (arr[N - 2] > arr[N - 1]) {
            return N - 1;
        }

        // 判断数组里面位置的局部最小
        // 数组变化趋势 ↘ ↗, L..R中肯定有局部最小
        int L = 0;
        int R = N - 1;

        // L < R-1 保证 L...R范围上三个数以上进行二分L R-1 R
        // 为什么需要3个数,因为需要保证mid-1和mid+1已经在L和R范围内了

        // 3 2 3 2 3
        // 0 1 2 3 4
        //    mid
        // mid=2 保留左边3,2

        // 3 2
        // 0 1
        // L R  边界情况:只有两个数LR
        // mid=(L+R)/2=0
        // 再去调用后面的mid-1,但是此时mid-1已经越界了,所以while循环得保证3个数以上

        while (L < R - 1) {
            int mid = (R + L) / 2;
            // 左> mid && mid < 右
            if (arr[mid] < arr[mid - 1] && arr[mid] < arr[mid + 1]) {
                return mid;
            } else {
                // 左 > mid && mid > 右
                // 左 < mid && mid > 右
                // 左 < mid && mid < 右
                // 剩余的3中情况中,只需要arr[mid]>arr[mid-1]
                if (arr[mid] > arr[mid - 1]) {
                    R = mid - 1;
                    //
                } else {
                    // mid>右
                    L = mid + 1;
                }
            }
        }

        // L>=R-1,此时就已经剩余两个数了(R-1,R),跳出for=循环
        // 又一定存在局部最小,正是因为存在局部最小才砍成这样的,所以最后谁小返回谁
        // 同时若arr[L]=arr[R],返回R
        return arr[L] < arr[R] ? L : R;
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
            int ans = oneMinIndex(arr);
            if (!check(arr, ans)) {
                printArray(arr);
                System.out.println(ans);
                break;
            }
        }
        System.out.println("测试结束");
    }

    // ------------------------------------------------------

    // 时间复杂度

    // 不关心系数,不关心低阶项,只关心最高阶
    // 和数据量(n)无关的语句就是 常数操作
    // 和数据量(n)有关的语句就不是 常数操作
    // 估计时间复杂度,使用程序最差的情况,不能使用最好的情况

    // 时间复杂度由小到大排序为
    // 1 < logN < N < NlogN < N^2 < N^3 < N^ K < 2^N < k^N < N!

    // ArrayList 动态数组
    // ArrayList的虽然有动态扩容,但是对其性能影响在时间复杂度上只是常数时间的慢,在工程上的影响几乎感觉不到的
}
