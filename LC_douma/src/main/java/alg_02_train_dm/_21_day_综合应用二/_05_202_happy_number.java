package alg_02_train_dm._21_day_综合应用二;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author Wuyj
 * @DateTime 2023-05-26 16:01
 * @Version 1.0
 */
public class _05_202_happy_number {
      /* 
        202. 快乐数
        编写一个算法来判断一个数 n 是不是快乐数。
        「快乐数」定义为：
            1. 对于一个正整数，每一次将该数替换为它每个位置上的数字的平方和。
            2. 然后重复这个过程直到这个数变为 1，也可能是 无限循环 但始终变不到 1。
            3. 如果可以变为  1，那么这个数就是快乐数。
        如果 n 是快乐数就返回 true ；不是，则返回 false 。


        示例 1：
        输入：n = 19
        输出：true
        解释：
        1^2 + 9^2 = 82
        8^2 + 2^2 = 68
        6^2 + 8^2 = 100
        1^2 + 0^2 + 0^2 = 1

        提示：
        1 <= n <= 2^31 - 1
     */

    // 本质：将本题抽象成单向链表，在单向链表中判断是否存在环，联想 141 号环形链表一样的
    // KeyPoint 方法一 哈希表
    public boolean isHappy1(int n) {
        // 使用哈希表，效率有所影响
        Set<Integer> set = new HashSet<>();
        // while 没有终止条件，而是在循环体内结束
        while (true) {
            // 退出条件，判断条件，放在前面
            if (n == 1) return true;
            if (set.contains(n)) return false;
            set.add(n);
            n = squareSum(n);
        }
    }

    // 求数字 n 每一位的平方和
    private int squareSum(int n) {
        int sum = 0;
        while (n != 0) {
            // % 10 是为了拿到个位数
            int num = n % 10;
            sum += num * num;
            n /= 10;
        }
        return sum;
    }

    // KeyPoint 方法二 双指针 - 快慢指针
    public boolean isHappy2(int n) {
        if (n == 1) return true;
        int slow = n;
        int fast = n;
        while (true) {
            // slow 走一步，相当于调用一次 squareSum
            slow = squareSum(slow);
            // fast 走两步，相当于调用两次 squareSum
            fast = squareSum(squareSum(fast));
            if (slow == 1 || fast == 1) return true;
            // 存在环
            if (slow == fast) return false;
        }
    }
}
