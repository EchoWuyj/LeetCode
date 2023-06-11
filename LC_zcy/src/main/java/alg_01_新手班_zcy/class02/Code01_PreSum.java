package alg_01_新手班_zcy.class02;

/**
 * @Author Wuyj
 * @DateTime 2022-08-30 15:11
 * @Version 1.0
 */
public class Code01_PreSum {

    public static void main(String[] args) {

        // 注意这种构造数组的方式
        RangeSum1 rangeSum1 = new RangeSum1(new int[]{1, 2, 3, 4, 5});
        int result01 = rangeSum1.rangeSum(2, 3);
        System.out.println(result01);

        //---------------------------------------------

        RangeSum2 rangeSum2 = new RangeSum2(new int[]{1, 2, 3, 4, 5});
        int result02 = rangeSum2.rangeSum(2, 3);
        System.out.println(result02);
    }

    // 前缀和(实现一)遍历实现
    // 实现从数组某个索引位置累加到另外一个索引位置
    public static class RangeSum1 {
        int[] arr;

        public RangeSum1(int[] array) {
            arr = array;
        }

        // 起始索引位置L,结束索引位置R
        public int rangeSum(int L, int R) {
            int sum = 0;
            for (int i = L; i <= R; i++) {
                sum += arr[i];
            }
            return sum;
        }
    }

    //-----------------------------------------------------------

    // 前缀和 (实现二)构建前缀和数组
    // 构建一次preSum数组,方便后续很多次查询
    public static class RangeSum2 {
        // 声明前缀和数组
        int[] preSum;

        // 通过构造方法进行初始化,这样只需要初始化一次即可
        public RangeSum2(int[] array) {
            int N = array.length;
            preSum = new int[N];
            preSum[0] = array[0];
            for (int i = 1; i < N; i++) {
                // i索引的前缀和=i索引之前的和+当前数组i索引的值
                // KeyPoint for循环中数组一般使用的都是i变量,不会是常量,自己在coding时小心
                //  经典错误:preSum[i] = preSum[0] + array[i];不可能一直是preSum[0]!
                preSum[i] = preSum[i - 1] + array[i];
            }
        }

        // 查询L到R的累加和
        public int rangeSum(int L, int R) {
            // 2-4索引位置元素累加和
            // KeyPoint 涉及数组索引运算,思考是否越界,当L=0,则L-1,L-1越界
            return (L == 0) ? preSum[R] : (preSum[R] - preSum[L - 1]);

            // array[2]+array[3]+array[4]=preSum[4]-preSum[1]
            //    preSum[4] 0 1 2 3 4
            // -  preSum[1] 0 1
            // ------------------------
            // array[2]+array[3]+array[4]
        }
    }
}
