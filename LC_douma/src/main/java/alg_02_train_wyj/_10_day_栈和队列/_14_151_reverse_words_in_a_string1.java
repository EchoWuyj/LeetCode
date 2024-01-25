package alg_02_train_wyj._10_day_栈和队列;

import java.util.*;

/**
 * @Author Wuyj
 * @DateTime 2023-04-28 18:00
 * @Version 1.0
 */
public class _14_151_reverse_words_in_a_string1 {
    // KeyPoint 方法一
    public String reverseWords1(String s) {
        s = s.trim();
        String[] words = s.split("\\s+");
        List<String> list = Arrays.asList(words);
        Collections.reverse(list);
        return String.join(" ", list);
    }


}
