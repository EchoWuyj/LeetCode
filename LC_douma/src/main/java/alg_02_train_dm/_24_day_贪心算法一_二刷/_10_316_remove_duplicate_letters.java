package alg_02_train_dm._24_day_贪心算法一_二刷;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @Author Wuyj
 * @DateTime 2023-04-12 16:20
 * @Version 1.0
 */
public class _10_316_remove_duplicate_letters {
     /*
        316. 去除重复字母
        给你一个字符串 s，请你去除字符串中重复的字母，使得每个字母只出现一次。
        需保证 返回结果的字典序最小(要求不能打乱其他字符的相对位置)

        示例 1：
        输入：s = "bcabc"
        输出：abc

        示例 2：
        输入：s = "cbacdcbc"
        输出：acdb

        提示：
        1 <= s.length <= 10^4
        s 由小写英文字母组成

        KeyPoint 字典序比较

        字典序比较，字符串从高位到低位，挨个位置进行字符比较
        保证字典序最小，尽量将最小的字母放在前面

         a b c  <  b c a
         ↑         ↑

         a b c  <  a c a
           ↑         ↑
     */

    // KeyPoint 贪心 + 单调栈
    // 时间复杂度：O(n)
    // 空间复杂度：O(n)
    public String removeDuplicateLetters(String s) {

        // 贪心：为了保证字典序最小，贪心地尽量将小的字母放在前面，同时需要保证其他字符的相对位置
        // 单调栈：模拟的过程后进的元素先操作，使用单调栈维护

        // b c a b c
        //     ↑

        //   | a | ← 若 a 的后面有 c，则可以将 a 删除，将 c 放到前面
        //   | c |   => 尽量将小的字母放在前面
        //   |_b_|
        //   单调栈

        // c b a c d c b c
        //               ↑

        //   | b |
        //   | a |
        //   | c |
        //   |_a_|
        //   单调栈

        // 1.计算字符在字符串 s 中的最后索引
        // 为了方便比较字符数组中每个字符的前后位置，以字符最后的位置为准
        // s 由小写英文字母组成 => 定义数组大小为 26
        int[] lastIndex = new int[26];
        int n = s.length();
        for (int i = 0; i < n; i++) {
            // s.charAt(i) - 'a' 字符对应数字，从 0 开始
            // i 索引位置
            lastIndex[s.charAt(i) - 'a'] = i;
        }

        // 2.维护单调栈
        Deque<Character> deque = new ArrayDeque<>();
        // 用于记录字符是否已经存在于栈中
        // 进栈和弹栈操作之后，都要及时更新 exists 数组，从而保证其状态是正确的
        boolean[] exists = new boolean[26];

        // 遍历字符数组
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            // 当前字符已经在栈中，直接跳过该字符，不用进行处理
            if (exists[c - 'a']) continue;

            // 当前字符和栈顶元素比较
            // 1.当前字符 < 栈顶字符
            // 2.栈顶字符在当前字符的后面还会出现
            while (!deque.isEmpty() && c < deque.peekFirst()
                    && lastIndex[deque.peekFirst() - 'a'] > i) {
                char top = deque.pollFirst();
                // 弹栈后，维护 exists 数组
                exists[top - 'a'] = false;
            }
            deque.offerFirst(c);
            // 入栈后，维护 exists 数组
            exists[c - 'a'] = true;
        }

        // 3. 将栈中字符拼接成结果
        StringBuilder res = new StringBuilder();
        while (!deque.isEmpty()) {
            // 从栈底取元素
            res.append(deque.pollLast());
        }
        return res.toString();
    }
}
