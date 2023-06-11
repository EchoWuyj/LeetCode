package alg_01_新手班_zcy.class08;

import java.util.Stack;

/**
 * @Author Wuyj
 * @DateTime 2022-09-06 23:16
 * @Version 1.0
 */
public class Code03_PartitionAndQuickSort {

    // splitNum1函数
    // p是原数组最后一个元素,让小于等于<=p放左边(从-1位置开始),
    // 大于p放右边,同时p是<=p区域里面最后一个数

    // 1 3 1 2 2 3 | 7 4 5 4 4 5
    // 将<=3都放在左边,>3都放在右边

    // 实现思路
    //  1)当前数<=p,当前数和<=区下一个数交换,<=区右扩,当前数跳下一个
    //  2)当前数>p,当前数直接跳下一个

    // 本质:以数组最后一个元素为标准,将小于标准的左移,大于标准的保持不动
    public static void splitNum1(int[] arr) {

        // <=区的右边界(R),表示扩的位置
        int lessEqualR = -1;
        // 当前位置的下标
        int index = 0;
        // 划分值的下标
        int mostRight = arr.length - 1;

        while (index < arr.length) {
            // KeyPoint 最后一轮index==mostRight,此时也是满足的,也是需要交换的
            if (arr[index] <= arr[mostRight]) {

                // 区别:两种++
                // ++lessEqualR 先是加1后的值,再使用lessEqualR
                // index++ 先使用完index之后,再++

                // KeyPoint 纠正错误的观念
                //  int lessEqualR=-1,swap(arr, lessEqualR + 1, index)
                //  只是传入到swap函数时,lessEqualR+1=0,但是其实lessEqualR本身还是-1
                //  只有执行lessEqualR++,此时lessEqualR才加1

                // 当前数和<=区下一个数交换
                swap(arr, index, lessEqualR + 1);
                lessEqualR++;
                index++;
            } else {
                index++;
            }
        }

        // 简化版
//        int lessEqualR = -1;
//        int index = 0;
//        int N = arr.length;
//        while (index < N) {
//            if (arr[index] <= arr[N - 1]) {
//                swap(arr, ++lessEqualR, index++);
//            } else {
//                index++;
//            }
//        }

    }

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    //------------------------------------------------------------

    // splitNum2函数:
    // p是原数组最后一个元素,想让<p放左边(区间可以无序,从-1位置开始),
    // =p放中间,>p放右边(区间可以无序,包括最后一元素)
    // p=4,划分范围:3,2,1,0,4,4,4,7,6,5

    // 2 1 3 2 1 | 4 4 4 4 | 5 7 5
    // <4 =4 >4的3个部分

    // 实现思路:
    //  1)当前数<p,当前数和<p区下一个数交换,<区右扩,当前数跳下一个
    //  2)当前数>p,当前数和>p区前一个数交换,>区左扩,当前数不动
    //   (因为交换之后该元素是右边换过来的,还没有比较,所以不能移动)
    //  3)当前数=p,直接跳过
    //  4)数组最后一个元素p和>区的第一元素交换

    // 注意:当前数和>区的边界碰上时,停止遍历
    public static void splitNum2(int[] arr) {
        int N = arr.length;
        int index = 0;

        // <p区的右边界
        int lessR = -1;
        // >p区的左边界
        int moreL = N - 1;

        // arr[N-1] 数组中最后一个元素,在遍历的过程中没有变化,只有最后一步才进行交换
        // 当前数索引和>p区的边界碰上时,停止遍历
        while (index < moreL) {
            if (arr[index] < arr[N - 1]) {
                // 当前数和<区下一个数交换,<区右扩,当前数跳下一个
                // <p区下一个数和,<p区右扩使用同一个代码++lessR实现了
                swap(arr, ++lessR, index++);
            } else if (arr[index] > arr[N - 1]) {
                // 当前数>p,当前数和>p区前一个数交换,>区左扩,当前数跳不动
                swap(arr, --moreL, index);
            } else {
                index++;
            }
        }

        // 当index==moreL时,跳出while循环,此时moreL表示>p区的第一个元素
        // 数组最后一个元素p和>p区的第一元素交换
        swap(arr, moreL, N - 1);
    }

    // -------------------------------------------------------------

    // 在splitNum2基础上演进partition
    // 将原来的0-N-1,替换成L~R上,arr[L...R]范围上,拿arr[R]做划分值
    // L....R < = >
    public static int[] partition(int[] arr, int L, int R) {
        int lessR = L - 1;
        int moreL = R;
        int index = L;
        while (index < moreL) {
            // 划分值永远在arr[R]上
            if (arr[index] < arr[R]) {
                swap(arr, ++lessR, index++);
            } else if (arr[index] > arr[R]) {
                swap(arr, --moreL, index);
            } else {
                index++;
            }
        }
        // 数组最后一个元素p和>区的第一元素交换
        swap(arr, moreL, R);

        // 关键:已经确定等于区域,而左右两侧无序区域再通过递归实现
        // 返回等于区域的开始(左边界)和结束(右边界)索引

        // 最后lessR指向<区域最后一个元素的位置,故<区域的下一个为等于区域的第一个
        // 因为涉及最后一次的交换,所以等于区域的最后位置为moreL
        return new int[]{lessR + 1, moreL};
    }

    // <p | =p | >p 利用partition反复递归
    // <p 递归
    // =p 递归
    // >p 递归
    public static void process(int[] arr, int L, int R) {
        // 递归边界
        // 不是一个有效范围,或者只有一个数,直接return
        if (L >= R) {
            return;
        }

        int[] equalE = partition(arr, L, R);
        // equalE[0]等于区域的第一数
        // equalE[1]等于区域的最后一个数

        // 划分左右两数组的边界,调用递归
        process(arr, L, equalE[0] - 1);
        process(arr, equalE[1] + 1, R);
    }

    public static void quickSort1(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process(arr, 0, arr.length - 1);
    }

    // -----------------------------------------------

    // 将递归过程定义成任务来执行
    // 定义任务类
    public static class Job {
        // 任务左边界
        public int L;
        // 任务右边界
        public int R;

        public Job(int left, int right) {
            L = left;
            R = right;
        }
    }

    // 非递归版本
    public static void quickSort2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        // 将做任务这件事放到栈中去,将其分解成小任务,周而复始计算
        Stack<Job> stack = new Stack<>();
        // 将最大的任务放进栈中
        stack.push(new Job(0, arr.length - 1));
        // 任务没有做完
        while (!stack.isEmpty()) {
            Job cur = stack.pop();
            // 求得最大任务的等于区域
            int[] equals = partition(arr, cur.L, cur.R);

            // 这里是严格大于的关系,若是等于则还是等于区域,不是小于区域
            if (equals[0] > cur.L) { // 有< 区域
                stack.push(new Job(cur.L, equals[0] - 1));
            }
            if (equals[1] < cur.R) { // 有 > 区域
                stack.push(new Job(equals[1] + 1, cur.R));
            }
        }
    }

    // --------------------------------------------------

    public static int[] netherlandsFlag(int[] arr, int L, int R) {
        if (L > R) {
            return new int[]{-1, -1};
        }
        if (L == R) {
            return new int[]{L, R};
        }
        int less = L - 1;
        int more = R;
        int index = L;
        while (index < more) {
            if (arr[index] == arr[R]) {
                index++;
            } else if (arr[index] < arr[R]) {
                swap(arr, index++, ++less);
            } else {
                swap(arr, index, --more);
            }
        }
        swap(arr, more, R); // <[R] =[R] >[R]
        return new int[]{less + 1, more};
    }

    public static void process3(int[] arr, int L, int R) {
        if (L >= R) {
            return;
        }
        swap(arr, L + (int) (Math.random() * (R - L + 1)), R);
        int[] equalArea = netherlandsFlag(arr, L, R);
        process3(arr, L, equalArea[0] - 1);
        process3(arr, equalArea[1] + 1, R);
    }

    public static void quickSort3(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process3(arr, 0, arr.length - 1);
    }

    //--------------------------------------------------------------

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

    public static void main(String[] args) {
        int[] arr01 = {7, 1, 3, 5, 4, 5, 1, 4, 2, 4, 2, 3};
        splitNum1(arr01);
        for (int i = 0; i < arr01.length; i++) {
            System.out.print(arr01[i] + " ");
        }
        // 1 3 1 2 2 3 | 7 4 5 4 4 5
        // 将<=3都放在左边,>3都放在右边
        System.out.println();
        System.out.println("============================");

        //---------------------------------------------

        int[] arr02 = {7, 1, 3, 5, 4, 5, 1, 4, 2, 4, 2, 4};
        splitNum2(arr02);
        for (int i = 0; i < arr02.length; i++) {
            System.out.print(arr02[i] + " ");
        }
        // 2 1 3 2 1 | 4 4 4 4 | 5 7 5
        // <4 =4 >4的3个部分

        System.out.println();
        System.out.println("============================");

        //----------------------------------------------------------------

        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        System.out.println("test begin");
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            int[] arr3 = copyArray(arr1);
            quickSort1(arr1);
            quickSort2(arr2);
            quickSort3(arr3);
            if (!isEqual(arr1, arr2) || !isEqual(arr1, arr3)) {
//            if (!isEqual(arr1, arr3)) {
                System.out.println("Oops!");
                succeed = false;
                break;
            }
        }
        System.out.println("test end");
        System.out.println(succeed ? "Nice!" : "Oops!");
    }
}
