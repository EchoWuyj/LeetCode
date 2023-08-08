package alg_02_train_dm._10_day_栈和队列;

import java.util.*;

/**
 * @Author Wuyj
 * @DateTime 2023-04-27 16:37
 * @Version 1.0
 */
public class _14_151_reverse_words_in_a_string1 {

    /*
        151 号算法题：翻转字符串里的单词
        给定一个字符串，逐个翻转字符串中的每个单词。

        输入："the sky is blue"
        输出："blue is sky the"

        输入："  hello world!  "
        输出："world! hello"

        输入："a good   example"
        输出："example good a"

        输入：s = "  Bob    Loves  Alice   "
        输出："Alice Loves Bob"

        提示：
        1. 无空格字符构成一个单词 。
        2. 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
        3. 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。

        请尝试使用 O(1) 额外空间复杂度的原地解法。
     */

    // KeyPoint 方法一 使用内置 API
    public String reverseWords1(String s) {

        // 输入：s = "  Bob    Loves  Alice   "
        // 1. "Bob    Loves  Alice"
        // 2. ["Bob", "Loves", "Alice"]
        // 3. ["Alice", "Loves", "Bob"]
        // 4. "Alice Loves Bob"
        // 输出："Alice Loves Bob"

        // trim() 去掉两端的空格
        s = s.trim();
        // \\s 表示 \s 表示转义字符 ,\s表示匹配任意空格(包括空格，制表符，换页符)
        // \\s+中的 '+' 表示多次匹配
        // s.split("\\s+") 将字符串 s 按照一个或多个连续的空白字符进行分割，并返回一个字符串数组
        // ["Bob", "Loves", "Alice"]
        String[] words = s.split("\\s+");
        List<String> list = Arrays.asList(words);
        // 反转，前 => 后，后 => 前
        Collections.reverse(list);
        // 按照指定空格，输出字符串
        return String.join(" ", list);
    }
}
