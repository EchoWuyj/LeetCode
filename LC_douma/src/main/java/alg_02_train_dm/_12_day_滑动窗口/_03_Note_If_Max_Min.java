package alg_02_train_dm._12_day_滑动窗口;

/**
 * @Author Wuyj
 * @DateTime 2023-05-05 12:07
 * @Version 1.0
 */
public class _03_Note_If_Max_Min {


    // 代码中，if 判断，使用  Math.max 或者  Math.min 进行简化
    // 当使用条件语句 if 进行比较时，可以使用 Math.max 或 Math.min 函数来简化代码。

    // KeyPoint 1.使用 Math.max 找到两个数中的较大值
    public void test1() {
        int a = 5;
        int b = 8;
        int max = Math.max(a, b);

        if (a > b) {
            // 执行 a 大于 b 的情况
        } else {
            // 执行 a 不大于 b 的情况
        }
    }

    // 简化
    public void test2() {
        int a = 5;
        int b = 8;

        if (Math.max(a, b) == a) {
            // 执行 a 大于 b 的情况
        } else {
            // 执行 a 不大于 b 的情况
        }
    }

    // KeyPoint 2.使用 Math.max 找到数组中的最大值
    public void test3() {
        int[] array = {3, 9, 2, 5, 7};
        int max = array[0];

        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }
    }

    // 简化
    public void test4() {
        int[] array = {3, 9, 2, 5, 7};
        int max = array[0];

        for (int i = 1; i < array.length; i++) {
            max = Math.max(max, array[i]);
        }
    }

    // KeyPoint 3. 三个数 a、b 和 c，我们想要找出其中最大的数，并根据最大数的不同执行不同的操作

    public void test5() {
        int a = 10;
        int b = 7;
        int c = 12;

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
    }

    // 简化
    public void test6() {
        int a = 10;
        int b = 7;
        int c = 12;

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

    // 1.比较两个数的大小：如果你有一个 if 语句用于比较两个数的大小，并且根据比较结果执行不同的逻辑
    //   你可以考虑使用 Math.max 或 Math.min 来简化代码。

    // 2.查找数组中的最大值或最小值：如果你需要在一个数组中查找最大值或最小值，并使用 if 语句进行比较和更新，
    //   你可以使用 Math.max 或 Math.min 函数在循环中简化代码。

    // 3. 对比多个数的大小：如果你需要对比多个数的大小，并根据不同的比较结果执行不同的操作，
    //    你可以使用 Math.max 或 Math.min 函数进行连续的比较，而不必使用多个嵌套的 if 语句。

    // 总的来说，当你需要进行数值的比较和选择时，可以考虑使用 Math.max 或 Math.min 函数来简化代码。
    // 这样可以使代码更加简洁和易读，并减少重复的条件判断。
}
