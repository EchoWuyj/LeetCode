package alg_02_train_dm._10_day_栈和队列;

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
        // 输出："Alice Loves Bob"

        // left 和 right 都是索引，故 right 从 s.length() - 1 开始
        int left = 0, right = s.length() - 1;
        // 去掉开头的空格
        while (left <= right && s.charAt(left) == ' ') left++;
        // 去掉结尾的空格
        // KeyPoint right 从右往左移动，right--
        while (left <= right && s.charAt(right) == ' ') right--;

        // 双端队列 => 使用额外空间
        Deque<String> deque = new ArrayDeque<>();
        StringBuilder word = new StringBuilder();
        while (left <= right) {
            char c = s.charAt(left);
            if (c != ' ') {
                word.append(c);
            } else if (word.length() != 0) {
                deque.offerFirst(word.toString());
                // 清空
                word.setLength(0);
            }
            // 指针前移，不能忘记！
            left++;
        }
        // 若 s 最后没有空格，即没有执行 deque.offerFirst(word.toString());
        // 需要将最后一个单词加入队列中
        deque.offerFirst(word.toString());
        return String.join(" ", deque);
    }

}
