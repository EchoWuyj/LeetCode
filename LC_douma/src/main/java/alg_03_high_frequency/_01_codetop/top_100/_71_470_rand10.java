package alg_03_high_frequency._01_codetop.top_100;

/**
 * @Author Wuyj
 * @DateTime 2024-01-16 21:42
 * @Version 1.0
 */
public class _71_470_rand10 {

    // 一个方法 rand10 生成 [1,10] 范围内的均匀随机整数
    public int rand10() {
        return f3() + 1;
    }

    public int f3() {
        int ans = 0;
        do {
            ans = f2();
            // 需要 0-9，则 ans >= 10 重新调用 f3();
        } while (ans >= 10);
        return ans;
    }

    public int f2() {
        return (f1() << 3) + (f1() << 2) + (f1() << 1) + f1();
    }

    public int f1() {
        int ans = 0;
        do {
            ans = rand7();
        } while (ans == 4);
        return ans < 4 ? 0 : 1;
    }

    // 系统提供，rand7() 可生成 [1,7]
    public int rand7() {
        return -1;
    }
}
