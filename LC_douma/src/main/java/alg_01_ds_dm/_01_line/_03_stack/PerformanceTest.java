package alg_01_ds_dm._01_line._03_stack;

import java.util.Random;

/**
 * @Author Wuyj
 * @DateTime 2023-03-09 22:57
 * @Version 1.0
 */
public class PerformanceTest {
    private static Random random = new Random();

    // 面向接口编程
    private static double testStack(Stack<Integer> stack, int cnt) {
        long startTime = System.nanoTime();

        for (int i = 0; i < cnt; i++) {
            stack.push(random.nextInt());
        }
        for (int i = 0; i < cnt; i++) {
            stack.pop();
        }

        // 支持这样定义数字
        // 一亿 100_000_000  => 10^8
        // 十亿 1000_000_000 => 10^9 (10 亿 和 数字 9 相关，故为 10 ^9)
        return (System.nanoTime() - startTime) / 1000_000_000.0;
    }

    public static void main(String[] args) {
        int cnt = 100000000;

        Stack<Integer> stack = new DynamicArrayStack<>(10);
        double time1 = testStack(stack, cnt);
        System.out.println("DynamicArrayStack 花费的时间：" + time1);

        stack = new LinkedListStack<>();
        double time2 = testStack(stack, cnt);
        System.out.println("LinkedListStack 花费的时间：" + time2);

        /*
            DynamicArrayStack 花费的时间：38.5096783
            LinkedListStack 花费的时间：66.237033 => 数据量大，则创建 Node 非常耗费时间
         */
    }
}
