package alg_02_train_dm._12_day_滑动窗口;

/**
 * @Author Wuyj
 * @DateTime 2023-05-05 12:07
 * @Version 1.0
 */
public class _03_Note_If_Max_Min {

    // KeyPoint  if 和 Math.max 或者 Math.min 转化
    // 代码中，if 判断，使用  Math.max 或者  Math.min 进行简化
    // 当使用条件语句 if 进行比较时，可以使用 Math.max 或 Math.min 函数来简化代码

    // KeyPoint 1.使用 Math.max 找到两个数中的较大值
    public void test1() {
        int a = 5;
        int b = 8;
        int max = 0;

        // KeyPoint 本质：找较大值

//        if (a > b) {
//            max = a;
//        } else {
//            max = b;
//        }

        // KeyPoint 简化
        max = Math.max(a, b);
    }

    // KeyPoint 2.使用 Math.max 找到数组中的最大值
    public void test2() {
        int[] array = {3, 9, 2, 5, 7};
        int max = array[0];

        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
            // KeyPoint 简化
            // max = Math.max(max, array[i]);
        }
    }

    // KeyPoint 3. 三个数 a、b 和 c，我们想要找出其中最大的数，并根据最大数的不同执行不同的操作

    public void test5() {
        int a = 10;
        int b = 7;
        int c = 12;

        // 若是直接通过 if 判断大小值，嵌套逻辑很复杂
        if (a > b) {
            if (a > c) {
                // 执行 a 最大的情况
            } else {
                // 执行 c 最大的情况
            }
        } else {
            if (b > c) {
                // 执行 b 最大的情况
            } else {
                // 执行 c 最大的情况
            }
        }

        // KeyPoint 简化
        // 1.直接获取 max
        // 2.再去对 max 是 a，b，c 的那种进行判断
        int max = Math.max(Math.max(a, b), c);

        if (max == a) {
            // 执行 a 最大的情况
        } else if (max == b) {
            // 执行 b 最大的情况
        } else {
            // 执行 c 最大的情况
        }
    }

    // KeyPoint 总结：以下场景可以考虑 Math.max 或 Math.min 进行转化

    // 1.比较两个数的大小：Math.max 或 Math.min => 简化代码
    // 2.数组中的最大值或最小值：Math.max 或 Math.min => 简化代码
    // 3.比较多个数的大小： Math.max 或 Math.min => 避免多个嵌套的 if 语句

    // 总结
    // 在数值比较或选择时，可以考虑使用 Math.max 或 Math.min 来简化代码
    // => 使代码更加简洁和易读，并减少重复的条件判断
}
