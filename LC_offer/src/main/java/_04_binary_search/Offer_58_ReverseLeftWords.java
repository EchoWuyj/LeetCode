package _04_binary_search;

import java.time.temporal.ChronoField;

/**
 * @Author Wuyj
 * @DateTime 2022-08-27 15:04
 * @Version 1.0
 */
public class Offer_58_ReverseLeftWords {
    public String reverseLeftWords(String s, int n) {
        // 1、获取字符串的长度
        int length = s.length();
        // 2、把字符串转换为字符数组的形式
        char[] chars = s.toCharArray();

        // 3、把字符数组的所有字符反转
        // 即  a b c d e f g
        // 变成了 g f e d c b a
        reverse(chars, 0, length - 1);

        // 4、再反转前 length - n 个字符
        // 即  g f e d c b a
        // 变成了 c d e f g b a
        reverse(chars, 0, length - 1 - n);

        // 5、再反转剩余的字符
        // 即  c d e f g b a
        // 变成了 c d e f g a b
        // KeyPoint reverse的过程不能有重叠的元素
        //  上次reverse是从length-1-n开始,下次reverse从length-n开始
        reverse(chars, length - n, length - 1);

        // 6、最后返回字符串结果就可以
        return new String(chars);
    }

    // 借助临时变量的方法，把字符数组进行反转
    public void reverse(char[] chars, int start, int end) {
        // 从头到尾变量所有的字符
        while (start < end) {
            // 定义一个临时变量用来保存字符数组的开始字符
            char temp = chars[start];
            // 将首尾字符交换
            chars[start] = chars[end];
            // 将首尾字符交换
            chars[end] = temp;

            start++;
            end--;
        }
    }
}
