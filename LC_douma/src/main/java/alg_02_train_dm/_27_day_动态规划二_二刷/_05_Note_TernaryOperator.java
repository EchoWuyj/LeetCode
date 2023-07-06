package alg_02_train_dm._27_day_动态规划二_二刷;

/**
 * @Author Wuyj
 * @DateTime 2023-06-23 21:55
 * @Version 1.0
 */
public class _05_Note_TernaryOperator {

    /*
        三元运算符
        在 Java 中，三元运算符的返回值是一个表达式，而不是一个语句，不能执行操作()
        条件表达式 ? 表达式 1: 表达式 2;
     */

    public void test(int k) {
        int i = 10, j = 20;
        // 使用变量 res，接受 k 是否为 0 ，判断的结果(i 或 j)
        int res = (k == 0) ? i : j;

        // 若是没有变量 res 接受，且没有 return 返回，代码则报错
//         (k == 0) ? i : j;
    }

    public int test1(int k) {
        int i = 10, j = 20;
        // 若是没有变量 res 接受，但有 return 返回
        return (k == 0) ? i : j;
    }

    public int test2(int k) {
        int i = 10, j = 20;
        // 三元运算符，涉及加减操作，i-j 和 i+j 也算表达式
        return (k == 0) ? i - j : i + j;
    }

    public int test3() {
        int[] arr1 = {1, 2};
        int[] arr2 = {3, 4};
        // 加减操作
        return arr1[0] == arr2[0] ? arr2[1] - arr1[1] : arr1[0] - arr2[0];
    }

    public int test4(int k) {
        int i = 10, j = 20;
        int odd = 0, even = 0;

        // x += y，其实就是 x = x + y
        // 这里不能使用 +=，直接使用 + 即可，否则报错
//        return (k == 0) ? odd += i : even += j;
        return (k == 0) ? odd + i : even + j;
    }
}
