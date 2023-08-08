package alg_02_train_dm._10_day_栈和队列_二刷;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @Author Wuyj
 * @DateTime 2023-08-08 0:53
 * @Version 1.0
 */
public class _14_151_reverse_words_in_a_string3 {

    // KeyPoint 方法三 使用双端队列(栈)
    public String reverseWords(String s) {

        // 输入：s = "  Bob    Loves  Alice   "
        // 1. "Bob    Loves  Alice"
        // 2. Deque
        //   ----------------------------
        //         Alice Loves Bob
        //   ----------------------------
        //           ↑          ↑
        //         队首       队尾
        // 输出："Alice Loves Bob"

        // left 和 right 都是索引，故 right 从 s.length() - 1 开始
        int left = 0, right = s.length() - 1;
        // 去掉开头的空格
        while (left <= right && s.charAt(left) == ' ') left++;
        // 去掉结尾的空格
        // 注意：right 从右往左移动，right--
        while (left <= right && s.charAt(right) == ' ') right--;

        // 双端队列 => 使用额外空间
        Deque<String> deque = new ArrayDeque<>();
        StringBuilder word = new StringBuilder();
        while (left <= right) {
            char c = s.charAt(left);
            if (c != ' ') {
                word.append(c);
            } else {
                // c == ' '，且 word 不为空
                if (word.length() != 0) {
                    deque.offerFirst(word.toString());
                    // 将 StringBuilder 的长度设置为 0，相当于是清空 word
                    word.setLength(0);
                }
            }
            // 指针前移，不能忘记！
            left++;
        }

        // 因为 s 最后一个单词结尾没有空格，故没有执行 deque.offerFirst(word.toString());
        // 需要单独将最后一个单词加入队列中
        deque.offerFirst(word.toString());
        // 实现 Iterable 接口集合都可以作为形参传入
        return String.join(" ", deque);
    }
}
