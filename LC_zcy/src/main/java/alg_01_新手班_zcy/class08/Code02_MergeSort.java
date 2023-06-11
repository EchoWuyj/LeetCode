package alg_01_新手班_zcy.class08;

import java.util.Arrays;

/**
 * @Author Wuyj
 * @DateTime 2022-09-06 9:43
 * @Version 1.0
 */
public class Code02_MergeSort {

    // KeyPoint 递归方法实现
    public static void mergeSort1(int[] arr) {
        // 区别arr为空,arr为0
        // 给array赋一个null,相当于还是没有给它数组地址,也就是没有开辟一块数组的内存
        // array赋了一个数组的地址,但是这个数组{}是里面没有值,长度为0的数组
        if (arr == null || arr.length == 0) {
            return;
        }
        process(arr, 0, arr.length - 1);
    }

    // arr[L...R]范围上,让这个范围上的数有序！
    public static void process(int[] arr, int L, int R) {
        // 只有一个数时,直接返回,因为返回值是void,故只能return
        if (L == R) {
            return;
        }

        // int mid = (L + R) / 2;
        // 这种方式求中点存在风险,如果L和R值很大,有可能导致L+R越界
        // 所以使用下面这种写法更加安全

        // KeyPoint  运算符的优先级
        //  + - 高于 << >> >>> 高于 =
        //  所以这里使用右移>>需要加上括号
        int mid = L + (((R - L)) >> 1);

        // 递归的黑盒
        // 1)左边有序
        process(arr, L, mid);
        // 2)右边有序
        process(arr, mid + 1, R);
        // 3)merge之后整体有序
        merge(arr, L, mid, R);
    }

    // merge操作
    // 1)用指针指分别指向左边,右边数组开始位置
    // 2)比较指针指向的两个元素大小,谁小copy到辅助数组help
    //   相等copy左右都可以,默认copy左
    // 3)在指针越界时,将剩下的一侧元素copy过来即可
    // 两个数组,L~M,M+1~R
    public static void merge(int[] arr, int L, int M, int R) {
        // 辅助数组
        int[] help = new int[R - L + 1];
        // i指针指向辅助数组help
        int i = 0;

        // 两个指针(分别指向L~M,M+1~R)
        int p1 = L;
        int p2 = M + 1;

        // 两个指针都没有越界的情况
        while (p1 <= M && p2 <= R) {
            // 比较,赋值之后,三指针都后移
            // ++操作是后执行的
            help[i++] = (arr[p1] <= arr[p2]) ? arr[p1++] : arr[p2++];
        }

        // 从上个while循环跳出,要么p1越界,要么p2越界,不可能出现共同越界
        // 此时再判断谁没有越界,将剩余的元素copy到help数组中去
        // 这里虽然写了两个while,但是最终只会执行一个while
        while (p1 <= M) {
            help[i++] = arr[p1++];
        }
        while (p2 <= R) {
            help[i++] = arr[p2++];
        }

        // 将辅助数据中的元素复制会原数组中去,对原数组进行覆盖
        for (i = 0; i < help.length; i++) {

            // Java中for循环里的变量只能作用在for循环内
            // 但是for循环里的变量不能和外部变量重名,该方法中已经定义了int i
            // 所以这里for循环中i只能重新赋值i=0,而不能重复定义,或者定义为其他变量

            // arr数组索引位置是从L开始的,不是原始的0,和传入的L,R有关
            arr[L + i] = help[i];
        }
    }

    //-------------------------------------------------------

    // KeyPoint 非递归方法实现
    // 步长1,2,4,8..变化,每次以步长为一组,对构成的数组进行merge(方法同上)
    // 根据步长划分组的过程中边界条件的判断比较麻烦
    // arr 0~15 step 4
    // 0,1,2,3|4,5,6,7|8,9,10,11|12,13,14,15
    //   左1     右1       左2       右2
    public static void mergeSort2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }

        int step = 1;
        // step和数组长度比较(元素个数),而不是索引,所以不要arr.length-1;
        int N = arr.length;

        // 步长<总长度,while循环一直执行
        // 若是取等step=N,此时左组就为N,则是没有右组的,不需要归并排序
        // 所以这种情况可以不用考虑,while循环条件严格小于的关系
        while (step < N) {
            int L = 0;

            // 保证原数组中划分出来的每个左组的起始位置小于N,避免越界
            // 因为下个左组L是有移动的,是在变化的,所以需要保证<N
            while (L < N) {

                // 定义左组的右边界,对应merge方法中的M
                int M = 0;

                // KeyPoint 1)计算左组(L~M)
                // 需要考虑最后一个左组凑不齐的情况
                // 需要考虑最后一个左组的右边界可能越界的情况
                // L+step-1为每个左组最后一个索引
                // L~N-1一共有(N-1)-L+1=N-L个数,该个数(N-L)能凑够step,则是L+step-1没有越界的
                if (N - L >= step) {
                    // 因为L自己算一个数,所以末尾是要减1的,计算出来的是索引位置
                    M = L + step - 1;
                } else {
                    // 凑不够step,直接来到N-1位置
                    M = N - 1;
                }

                // 没有右组的情况,直接结束,不需要merge,直接跳出循环
                // 在外层循环执行step*2,进行下轮判断
                if (M == N - 1) {
                    // break语句用于终止循环,可以用在while循环和for循环中
                    // break只是跳出最内层循环
                    break;
                }

                // KeyPoint 2)计算右组(M+1~R)
                // 定义右组的右边界(R)
                int R = 0;

                // KeyPoint 总结:
                //  左右两组的最左侧+加上step后到达最右侧,都有可能因为step
                //  过大导致越过N-1,从而导致索引越界,所以需要进行if判断

                // M是可以直接拿过来使用的
                // M+1~R的个数:(N-1)-(M+1)+1=N-1-M
                // 避免越界
                if (N - 1 - M >= step) {
                    // R的位置:M+1+step-1=M+step
                    R = M + step;
                } else {
                    // 凑不够的情况
                    R = N - 1;
                }

                // 划分好左右组,l..M M+1...R,再去调用merge方法
                merge(arr, L, M, R);

                // 避免R+1越界,R==N-1此时已经不存在左组了,提前break
                if (R == N - 1) {
                    break;
                } else {
                    // 下一个左组从R+1位置开始
                    L = R + 1;
                }
            }

            // 避免step数值溢出
            //   N/2为总长度的一半,如果此时step大于该值,左右交换已经是最后一次了
            //   注意这里是严格大于的关系,因为N/2是向下取整的,所以N实际偏小
            //   N=17时,(N/2)=8,若按照step=8,则数组分成了三段(8,8,1),不是两端,则出现问题
            if (step > N / 2) {
                break;
            }
            step *= 2;

            // KeyPoint 时间复杂度分析
            //  步长每次从2,4,8变化去追N,step需要调整的次数log2N次数
            //  每次merge中,每个左组和右组进行merge,一次merge的复杂度O(N)
            //  总的时间复杂度为O(N*log2N)

        }
    }

    //-------------------------------------------------------

    // KeyPoint 非递归方法实现(经典实现)
    public static void mergeSort3(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int N = arr.length;
        int mergeSize = 1;
        while (mergeSize < N) {
            int L = 0;
            while (L < N) {
                if (mergeSize >= N - L) {
                    break;
                }
                int M = L + mergeSize - 1;
                int R = M + Math.min(mergeSize, N - M - 1);
                merge(arr, L, M, R);
                L = R + 1;
            }
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

    public static void main(String[] args) {
        int[] array = {3, 5, 3, 2, 8, 1, 4, 6, 7};
        printArray(array);

        // KeyPoint
        //  关于值传递和引用传递
        //    1)基本数据类型的值传递,不改变原值,因为方法调用后就会弹栈,局部变量随之消失
        //    2)引用数据类型的值传递,改变原值,因为即使方法弹栈,但是堆内存数组对象还在,可以通过地址继续访问
        //  Java中到底是传值还是传址?(面试)
        //    1)既是传值,也是传地址,基本数据类型传递的是值,引用数据类型传递的是地址
        //    2)Java中只有传值,因为地址值也是值(出去面试都说这种,支持者都是高司令(Java之父))

        // Arrays.sort将array数组的地址传入,通过地址值修改了堆中的数组元素的位置,
        // 故在原数组的基础上进行排序操作
        Arrays.sort(array);
        System.out.println("=========================");
        printArray(array);
        System.out.println("=========================");

        // -----------------------------------------------------------

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
                printArray(arr2); // 随机
                break;
            }
        }
        System.out.println("测试结束");
    }
}