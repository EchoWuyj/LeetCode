package alg_02_train_dm._02_day_一维数组_二刷;

/**
 * @Author Wuyj
 * @DateTime 2023-07-19 19:24
 * @Version 1.0
 */
public class _02_189_rotate_array2 {

    // KeyPoint 方法二：环状替换
    public void rotate2(int[] nums, int k) {

        // 环状替换
        //         →→→ →→→
        //        ↓   ↓   ↓
        // nums： 5 6 1 2 3 4，k = 2
        //        ↑       ↑
        //        ← ← ← ← ←

        int n = nums.length;
        k = k % n;
        // 标记已处理元素个数，当所有元素都处理过一遍，环状替换结束
        // => 用于 for 循环中，循环终止条件
        int count = 0;

        // start 标记每个环状的起始点
        // 注意：for 循环中存在重复操作，没有必要循环 n 次，后续可以优化
        for (int start = 0; count < n; start++) {
            // 当前处理元素，从 start 开始
            int cur = start;
            // 记录 start 位置的元素值
            int pre = nums[start];

            // KeyPoint 解释：为什么使用 do while 循环
            // 1.使用 do while 为了至少一次循环体，因为一开始 start == cur
            // 2.若使用 while 循环，循环条件 while(start != cur)
            //   则一开始 while 循环里代码一个都不会执行的

            // 使用 do while 来实现 '循环替换'
            do {
                // start 的下个位置，即 next 索引
                int next = (cur + k) % n;
                // 交换代码
                int tmp = nums[next];
                nums[next] = pre;
                // 更新 pre 为 nums[next]，为下轮循环作准备
                pre = tmp;

                // cur 后移到 next 指针
                cur = next;
                // 已处理元素个数 + 1
                count++;
                // 若 start != cur 则一直循环，
                // 若 start == cur 则说明 cur 转了一圈回到原点，结束 while 循环
            } while (start != cur);
        }
    }

    // KeyPoint 方法二：环状替换 => 优化
    public void rotate22(int[] nums, int k) {
        int n = nums.length;
        k = k % n;

        // KeyPoint 优化
        // 1.通过数学公式计算得到 '循环次数'，而不是 for 循环 n 次
        // 2.计算循环的次数等于 n 和 k 的公约数
        int count = gcd(k, n);

        for (int start = 0; start < count; start++) {
            int curr = start;
            int prev = nums[start];
            // 循环替换
            do {
                int next = (curr + k) % n;
                int tmp = nums[next];
                nums[next] = prev;
                prev = tmp;
                curr = next;
            } while (start != curr);
        }
    }

    // gcd 功能：计算 a 和 b 的最大公约数
    // 比如
    // 6 和 2 的最大公约数是 2；
    // 10 和 4 的最大公约数是 2
    public int gcd(int a, int b) {
        // 结论：如果 a 和 b 是两个正整数，且 a>b，则有 gcd(a,b) = gcd(b, a%b)
        // a = 6, b = 3 => gcd(6,3) => gcd(3,6%3) => 3
        // KeyPoint 'baba' 结构
        return b > 0 ? gcd(b, a % b) : a;
    }
}
