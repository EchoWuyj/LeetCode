package alg_02_体系班_zcy.class05;

/**
 * @Author Wuyj
 * @DateTime 2022-09-16 21:47
 * @Version 1.0
 */
public class Code02_PartitionAndQuickSort {

    // arr[L..R]上,以arr[R]位置的数做划分值
    // 有两种划分(不要求各个区域内部有序)
    // 1)<=X 左边 | >X 右边
    // 2)<= X 左边 | =X 中间 | >X 右边

    //-------------------------------------

    // arr[L..R]上,以arr[R]位置的数做划分值
    // partition函数效果
    // 1)<= X 左 | > X 右
    // 2)<= X区域里最后一个数是X
    public static int partition(int[] arr, int L, int R) {

        // 1)<=X 左边 | >X 右边
        // 实现思路(partition)
        //  (1)当前数<=p,当前数和<=区下一个数交换,<=区右扩,当前数跳下一个
        //  (2)当前数>p,当前数直接跳下一个
        // -------------------------------------------------------

        // 递归边界条件
        if (L > R) {
            return -1;
        }
        if (L == R) {
            return L;
        }
        // 小于等于区域
        int lessEqual = L - 1;

        // KeyPoint 并不是直接从0开始,而是从L开始
        int index = L;

        while (index < R) {
            if (arr[index] <= arr[R]) {
                swap(arr, index, ++lessEqual);
            }
            // index不管怎么样都是需要前移的,不能放到if条件里面
            // 否则if条件不成立,则index没有前移,则代码有问题
            index++;
        }

        // 1,3,2,5,4
        // swap实现5,4交换 1,3,2,4,5
        // 从而保证4为分界线
        swap(arr, ++lessEqual, R);
        // 返回分界线索引,相当于已经确定的位置
        return lessEqual;
    }

    // arr[L...R] 排有序,快排1.0方式
    public static void quickSort1(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process1(arr, 0, arr.length - 1);
    }

    public static void process1(int[] arr, int L, int R) {
        if (L >= R) {
            return;
        }

        // L..R partition arr[R]为划分值
        //  <=arr[R] |arr[R]| >arr[R]
        int M = partition(arr, L, R);
        process1(arr, L, M - 1);
        process1(arr, M + 1, R);
    }

    //---------------------------------------------------------------------

    // arr[L...R] 玩荷兰国旗问题的划分,以arr[R]做划分值
    // <arr[R] | ==arr[R] | > arr[R]
    public static int[] netherlandsFlag(int[] arr, int L, int R) {

        // 2)<= X 左边 | =X 中间 | >X 右边
        // 实现思路(netherlandsFlag)
        //  (1)当前数<p,当前数和<p区下一个数交换,<区右扩,当前数跳下一个
        //  (2)当前数>p,当前数和>p区前一个数交换,>区左扩,当前数不动
        //     (因为交换之后该元素是右边换过来的,还没有比较,所以不能移动)
        //  (3)当前数=p,直接跳过(index移动)
        //  (4)当前数和右边界相碰上时,操作停止
        //     同时,将数组最后一个元素p和>区的第一元素交换

        if (L > R) { // L..R L>R
            // 不是有效范围,返回-1,-1
            return new int[]{-1, -1};
        }
        if (L == R) {
            // 以arr[R]做划分值,此时只有等于arr[R]区域
            return new int[]{L, R};
        }

        // <区的右边界
        int less = L - 1;

        // >区的左边界,一开始就将arr[R]放到>区里面
        // 故从>区从R-1位置开始比较,但是索引一开始是在R的位置
        int more = R;

        // 当前数索引
        int index = L;

        // 当前位置没有和>区的左边界撞上执行while循环,否则停止while循环
        while (index < more) {
            // arr[R]不会变化,始终使用arr[R]进行比较
            if (arr[index] == arr[R]) {
                index++;
            } else if (arr[index] < arr[R]) {
                // 前数<p,当前数和<p区下一个数交换,<区右扩,当前数跳下一个
                swap(arr, index++, ++less);
            } else {
                // 当前数>p,当前数和>p区前一个数交换,>区左扩,当前数不动
                swap(arr, index, --more);
            }
        }

        // 跳出while循环,此时index=more
        // 此时more指向的就是>区域的第一元素,将其和arr[R]交换
        // <[R] | =[R] | >[R]
        swap(arr, more, R);

        // 返回等于区域的开始和结束的索引
        return new int[]{less + 1, more};
    }

    // arr[L...R] 排有序,快排2.0方式
    public static void quickSort2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process2(arr, 0, arr.length - 1);
    }

    public static void process2(int[] arr, int L, int R) {
        if (L >= R) {
            return;
        }
        // [equalArea[0],equalArea[1]]
        // 确定的等于区域范围相较于1.0版本,等于区域范围更大
        // 从而保证确定位置的元素更多,所以效率更高
        int[] equalArea = netherlandsFlag(arr, L, R);
        process2(arr, L, equalArea[0] - 1);
        process2(arr, equalArea[1] + 1, R);
    }

    //---------------------------------------------------------------

    // arr[L...R] 排有序,快排3.0方式(真正意义上快排,随机快排)
    public static void quickSort3(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process3(arr, 0, arr.length - 1);
    }

    public static void process3(int[] arr, int L, int R) {
        if (L >= R) {
            return;
        }

        // 时间复杂度分析
        // 最好:每次都是数组中点划分满足Master公式:T(N)=O(N)+2*T(N/2),时间复杂度O(N*logN)
        // 最差:数组有序,O(N^2)
        // 随机选择,不是每次都是命中最差,最好的情况,总的来说这是概率事件
        // 最好,最差,任何一种情况只是占1/N,时间复杂度必须求所有情况的概率叠加
        // 通过计算长期的概率期望,收敛于O(N*logN)

        // 快排额外空间复杂度收敛于O(logN)
        // 递归的过程,需要将中间的划分点信息记录下来,因为在左侧递归完之后,需要中点位置,再进行右侧递归
        // 即使不使用递归的方式,使用迭代的方式,中点位置还是需要使用栈记录的
        // 最坏的情况:O(N),每个点都需要记录
        // 最好的情况:O(logN),每次都是中点,再通过不断的变量释放复用的方式搞定所有额外空间的
        // 这还是个概率事件,通过计算长期的概率期望,额外空间复杂度收敛于O(logN)

        swap(arr, L + (int) (Math.random() * (R - L + 1)), R); // (int)有个向下取整的过程
        int[] equalArea = netherlandsFlag(arr, L, R);
        process3(arr, L, equalArea[0] - 1);
        process3(arr, equalArea[1] + 1, R);
    }

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
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
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            int[] arr3 = copyArray(arr1);
            quickSort1(arr1);
            quickSort2(arr2);
            quickSort3(arr3);
            if (!isEqual(arr1, arr2) || !isEqual(arr2, arr3)) {
                succeed = false;
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Oops!");
    }
}
