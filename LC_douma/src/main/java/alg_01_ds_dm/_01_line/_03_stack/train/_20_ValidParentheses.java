package alg_01_ds_dm._01_line._03_stack.train;

import java.util.Stack;

/**
 * @Author Wuyj
 * @DateTime 2023-03-09 23:18
 * @Version 1.0
 */
public class _20_ValidParentheses {

    // KeyPoint 方法一:暴力解
    public boolean isValid1(String s) {
        // 字符串是不可变的，其中没有删除字符，需要将其转成 StringBuilder 形式
        StringBuilder sb = new StringBuilder(s);
        // 遍历次数
        int count = sb.length() / 2;
        // O(n^2)
        for (int i = 0; i < count; i++) {
            // KeyPoint 凡是出现相邻位置索引，i 和 i+1; i-1 和 i，for循环条件注意避免索引越界
            // j + 1 导致 sb.length() - 1
            for (int j = 0; j < sb.length() - 1; j++) {
                // 直接使用 sb 来遍历每个位置，而不是使用数组 str char[] str = s.toCharArray();
                // 因为 sb 在遍历的过程不断变化，而 str 却是不变的，不能保证是同步变化的
                char c1 = sb.charAt(j);
                char c2 = sb.charAt(j + 1);
                // 如果相邻的字符符合要求，则删除
                if (isMatched(c1, c2)) {
                    // delete [)，第二个位置是不包含的，所以 j + 1 再 + 1
                    sb.delete(j, j + 2);
                    break;
                }
            }
        }
        return sb.length() == 0;
    }

    private boolean isMatched(char c1, char c2) {
        if (c1 == '(')
            return c2 == ')';
        else if (c1 == '[')
            return c2 == ']';
        else if (c1 == '{')
            return c2 == '}';
        else
            return false;
    }

    // KeyPoint 方法二:栈
    // 空间换时间 => 将括号放到某个数据结构中，使用时再去从数据结构中查询
    // 先完成大体逻辑，再分析特殊情况（corner case） => 保证程序代码的正确性和提高代码的常数项时间
    public boolean isValid(String s) {
        if (s == null) return true;
        // 代码优化：如果字符串的长度为奇数的话，那么肯定不是合法的
        if (s.length() % 2 == 1) return false;
        //  另外一种写法 if ((s.length() & 1) == 1) return false;
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == ' ') continue;
            if (c == '(' || c == '{' || c == '[') {
                // 如果是左括号，则直接入栈
                stack.push(c);
                // KeyPoint 1 对于每个位置进行二选一判断，而不是两个相互独立的 if 判断
                //      2 进入 else 都是 ']'，'}'，')'
            } else {
                if (stack.isEmpty()) {
                    return false;
                }
                // 记录栈顶元素 top
                // 判断栈顶元素是否等于左括号对应的右括号
                char topElement = stack.pop();
                // 和栈顶元素 top 进行比较
                char matched = '#';
                if (c == ')')
                    matched = '(';
                else if (c == ']')
                    matched = '[';
                else
                    matched = '{';

                if (matched != topElement)
                    return false;
            }
        }
        // 如果栈不为空，那么也算没有匹配好
        return stack.isEmpty();
    }
}
