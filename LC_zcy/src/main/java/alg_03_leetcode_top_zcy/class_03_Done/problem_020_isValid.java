package alg_03_leetcode_top_zcy.class_03_Done;

import java.util.Stack;

/**
 * @Author Wuyj
 * @DateTime 2023-02-16 18:38
 * @Version 1.0
 */

// 有效的括号
public class problem_020_isValid {
    /*
        有效括号的要求
            1)左括号必须用相同类型的右括号闭合
            2)左括号必须以正确的顺序闭合
            3)每个右括号都有一个对应的相同类型的左括号

        [{()()}]()[{}]
        使用栈来解决
            1) 遇到左边 [,{,( 压栈
            2) 遇到右边 ],},) 弹栈
            3) 弹栈的括号必须能匹配
            4) 最后栈为空,匹配成功
     */
    public boolean isValid(String s) {
        if (s == null || s.length() == 0) {
            // 没有括号认为是true
            return true;
        }
        char[] chars = s.toCharArray();
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < chars.length; i++) {
            // 将每个字符提取出来进行单独判断
            char cha = chars[i];
            // 3种类型的左括号压栈
            if (cha == '(' || cha == '{' || cha == '[') {
                stack.add(cha);
                // KeyPoint 这里使用 if else 进行条件判断,并不是两个 if 独立判断
                //      针对的每个括号,根据左右不同的括号,进行不通的操作,所以使用的if else判断
                //      若是两个 if 语句,则是相互独立的判断,不符合本题的逻辑
            } else {
                // 避免stack.pop()空指针异常
                // ()) 这种情况就会空指针异常,(先压栈,匹配时再弹栈(,此时已经为空了
                // 而)是不入栈的,此时stack.pop()就会出现空指针异常
                if (stack.isEmpty()) {
                    // 提前为空,则是无效
                    return false;
                }
                // 弹出入栈的左括号
                Character last = stack.pop();
                // KeyPoint cha[i]为当前遍历到的右括号,注意是右括号,不是左括号!
                // 判断当前cha和last是否匹配,不匹配则直接返回false
                if ((cha == ')' && last != '(')
                        || (cha == '}' && last != '{')
                        || (cha == ']' && last != '[')) {
                    return false;
                }
            }
        }
        // 最后不是直接返回true,而是判断stack是否为空
        return stack.isEmpty();
    }

    /*
    两个 if 判断逻辑和 if else 逻辑的区别在于条件的执行方式
    当使用两个 if 语句时，每个条件都将独立地被判断，即使第一个条件为 true
    第二个条件也会被判断。每个条件的执行结果是互相独立的。
    KeyPoint 多个if => 每个if都得判断
                => 可能会出现对同一个需要判断的事件,进入2个if语句中,从而出现错误

    示例代码如下：

        if (condition1) {
            // 执行语句1
        }
        if (condition2) {
            // 执行语句2
        }

    当使用 if else 语句时，如果第一个条件为 true，则第二个条件将不会被判断，
    只有当第一个条件为 false 时，第二个条件才会被判断。
    KeyPoint 即 else if 不是上一个条件的前提下，如果是这个条件
         这意味着 if else 语句只会执行其中一个条件的代码块，而不会同时执行多个条件的代码块。
         "多选一"

    示例代码如下：

        if (condition1) {
            // 执行语句1
        } else if (condition2) {
            // 执行语句2
        }

    因此，如果你想同时判断多个条件，并且每个条件的执行结果是互相独立的，那么可以使用两个 if 语句；
    如果你只想执行其中一个条件的代码块，那么可以使用 if else 语句。

    KeyPoint 以后遇到多个if或者if else需要特备小心其逻辑关系

     */
}
