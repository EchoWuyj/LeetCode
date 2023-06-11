package algorithm._07_stack_and_queue;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @Author Wuyj
 * @DateTime 2022-03-21 10:49
 * @Version 1.0
 */
public class LeetCode_20_ValidParentheses {
    //使用栈
    public boolean isValid(String s) {
        //推荐使用LinkedList的方式来实现栈的功能
        Deque<Character> stack = new LinkedList<>();

        //遍历字符串中所有字符,依次判断
        for (int i = 0; i < s.length(); i++) {
            //获取当前字符串
            char c = s.charAt(i);

            //判断当前字符是左括号还是右括号
            //如果是左括号,直接将对应的右括号入栈
            if (c == '(') {
                stack.push(')');
                //在debug中else if语句还是会判断,但是不一定执行其里面的内容,需要判断是否为true
            } else if (c == '[') {
                stack.push(']');
            } else if (c == '{') {
                stack.push('}');
            } else {

                //KeyPoint 注意两个if的前后顺序是有讲究的
                //如果栈中没有元素需要弹栈,直接返回false,针对的是只有有括号 ")]}"的情况
                if (stack.isEmpty()) return false;

                //因为没有非法字符,不是左括号,就是右括号如果是右括号,弹栈判断是否匹配
                //KeyPoint 注意stack.pop()操作是已经将元素出栈,同时进行比较是否和c相同
                // 故出栈之后,栈中就会少一个元素了,考虑到之前栈进了一个元素,现在又出栈一个元素,所以现在栈为空
                if (stack.pop() != c) return false;
            }
        }

        //直到栈中为空,表示刚好所有的左括号都匹配完成,有括号都已经弹栈出来
        //如果栈中不为空,则表示栈中还还有左括号没有匹配完成
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        LeetCode_20_ValidParentheses validParentheses = new LeetCode_20_ValidParentheses();

        System.out.println(validParentheses.isValid("()[]{}"));
        System.out.println(validParentheses.isValid("(]"));
        System.out.println(validParentheses.isValid("([)]"));
        System.out.println(validParentheses.isValid("{[]}"));
        System.out.println(validParentheses.isValid("()"));
    }
}
