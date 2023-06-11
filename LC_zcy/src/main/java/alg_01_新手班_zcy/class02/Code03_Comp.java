package alg_01_新手班_zcy.class02;

/**
 * @Author Wuyj
 * @DateTime 2022-08-31 14:13
 * @Version 1.0
 */
public class Code03_Comp {
    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    // 选择排序
    // 每次从未排序部分找出最小的,与当前未排序部分的第一个位置上的元素互换,重复以上步骤,直至排序完毕
    public static void selectionSort(int[] arr) {

        // 先处理边界条件
        if (arr == null || arr.length < 2) {
            // 函数的返回为void,此时使用return表示终止函数
            return;
        }
        // 先在纸上将伪代码描述清楚,再去写代码
        int N = arr.length;
        // 外层for循环遍历数组中每个位置,找到每个位置上的最小值
        // 从前往后遍历
        for (int i = 0; i < N; i++) {
            int minValueIndex = i;
            // 内层for循环,将当前位置元素和数组中后面所有元素进行比较,获取最小值
            // 只有依次比较,比较完整个数组,才能获取最小值对应的索引
            for (int j = i + 1; j < N; j++) {
                minValueIndex = arr[j] < arr[minValueIndex] ? j : minValueIndex;
            }
            // 交换
            // 传入数组两个比较元素的两个索引即可,swap函数会根据索引交换其数值
            // 获取的最小值索引minValueIndex和当前位置i进行交换
            swap(arr, i, minValueIndex);
        }
    }

    // 插入排序(实现一)(推荐)
    public static void insertionSort01(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }

        int N = arr.length;
        // 外层for循环,待比较的索引依次从1一直到N-1,而索引为0就一个元素默认其是有序的
        // 索引后移而产生的新增数组元素去判断应该插入的位置
        for (int end = 1; end < N; end++) {
            // 记录新加入的元素索引位置
            int newNumIndex = end;
            // newNumIndex -1 >= 0 保证自减不越界
            // 前一个元素大于后面一个元素
            while (newNumIndex - 1 >= 0 && arr[newNumIndex - 1] > arr[newNumIndex]) {
                // 交换之后,新加入的元素已经不在原来位置了,再和前面的数字进行比较,所以是整体索引迁移
                swap(arr, newNumIndex - 1, newNumIndex);
                // 向前移动一位
                newNumIndex--;
            }
        }
    }

    //----------------------------------------------------------------

    // KeyPoint
    //  对数器:生成随机样本,自己做比对的机器
    //  通过对数器来验证排序算法是否正确
    public static int[] lenRandomValueRandom(int maxLen, int maxValue) {
        // 实现数组长度随机
        // 返回一个数组arr，arr长度[0,maxLen-1],arr中的每个值[0,maxValue-1]
        int len = (int) (Math.random() * maxLen);
        int[] ans = new int[len];

        // 实现数组中元素的值随机
        for (int i = 0; i < len; i++) {
            ans[i] = (int) (Math.random() * maxValue);
        }
        return ans;
    }

    // copy数组函数:针对的是数组中每个元素的copy,而不是数组引用的copy
    public static int[] copyArray(int[] arr) {
        int[] ans = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            ans[i] = arr[i];
        }
        return ans;
    }

    public static boolean isSorted(int[] arr) {
        if (arr.length < 2) {
            return true;
        }
        // 将最开始索引位置设置为最大值
        int max = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (max > arr[i]) {
                return false;
            }
            // 遍历的同时更新max
            // 这里更新的是索引值递增,导致元素值增大需要更新
            max = Math.max(max, arr[i]);
        }

        return true;
    }

    public static void main(String[] args) {
        int maxLen = 5;
        int maxValue = 1000;
        int testTime = 10000;

        // 循环检测
        for (int i = 0; i < testTime; i++) {
            // 先创建一数组,长度和元素都随机
            int[] arr1 = lenRandomValueRandom(maxLen, maxValue);
            // 临时保存数组,方便错误输出
            int[] tmp = copyArray(arr1);
            selectionSort(arr1);
            if (!isSorted(arr1)) {
                // 外层for循环使用变量i,在内层循环不能再使用
                // 得使用j,因为在内层for循环中可以调用到i,变量冲突
                for (int j = 0; j < tmp.length; j++) {
                    System.out.print(tmp[j] + " ");
                }
                System.out.println();
                System.out.println("选择排序错了！");
                // 打印一个错误数组例子
                break;
            }
        }
    }
}
