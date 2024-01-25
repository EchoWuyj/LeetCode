package alg_02_train_dm._24_day_贪心算法一_二刷;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @Author Wuyj
 * @DateTime 2023-04-11 20:23
 * @Version 1.0
 */
public class _07_402_RemoveKdigits1 {

    // KeyPoint 方法二 贪心 + 单调栈
    // 时间复杂度：O(k + n)
    // 空间复杂度：O(n) => 空间换时间
    public String removeKdigits(String num, int k) {

        // num  4     3    9
        //      ↑     ↑
        //    D(i-1)  Di

        // num 439 k = 1
        // 若删除 4，num = 39 => 选择这种情况，即 前一个位置 元素 > 后一个位置 元素
        // 若删除 3，num = 49
        // 故删除 4，保留 3

        // KeyPoint 优化 => 单调栈

        // num： 1  4  3  2  2  3  9
        //          ↑  ↑
        //         i-1 i

        //  | 3 | ← 新加入元素 3
        //  | 4 | ← 栈顶 4 > 3，将 4 弹栈
        //  |_1_|
        //   栈

        //  | 3 |
        //  |_1_|
        //   栈

        // 每次遍历到当前元素 i 时，都是需要和前面元素 i-1 进行对比
        // 考虑使用栈(单调栈)将暂时不用删除的字符存储起来，避免往回遍历
        // => 先进栈后处理

        Deque<Character> stack = new ArrayDeque<>();
        int n = num.length();

        // KeyPoint 单调栈代码模板
        for (int i = 0; i < n; i++) {   // O(n)
            char c = num.charAt(i);
            // 注意：只要栈满足删除条件，就得不断删除，执行 while 多次循环判断，而不是 if 的单次判断
            // c 进栈前需要判断，栈顶元素是否 > c，栈顶元素为 c 的前一个元素
            while (!stack.isEmpty() && k > 0 && stack.peek() > c) {
                // 删除栈顶元素
                stack.pop();
                k--;
            }
            // 不管 while 是否满足，stack 都是需要 push 元素 c
            stack.push(c);
        }

        // 剩余 k，依次从栈顶弹出 k 个元素即可
        for (int i = 0; i < k; i++) {  // O(k)
            stack.pop();
        }

        // 使用 StringBuilder 拼接栈剩余字符
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            char c = stack.pop();
            sb.append(c);
        }
        // 反转
        // 优化：使用双端队列，从而避免反转
        sb = sb.reverse();

        // 删除前面是 0 的字符
        int len = sb.length();
        while (len != 0) {
            if (sb.charAt(0) > '0') break;
            sb.deleteCharAt(0);
            len = sb.length();
        }

        return sb.length() == 0 ? "0" : sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(new _07_402_RemoveKdigits1().removeKdigits("10200", 1));
        // 200
    }
}
