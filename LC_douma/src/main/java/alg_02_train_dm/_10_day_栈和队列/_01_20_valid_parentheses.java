package alg_02_train_dm._10_day_栈和队列;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author Wuyj
 * @DateTime 2023-04-24 19:56
 * @Version 1.0
 */

// 详细注释 _20_ValidParentheses
public class _01_20_valid_parentheses {
    /*
        leetcode 20 号算法题：有效的括号
        给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。

        有效字符串需满足：
        1. 左括号必须用相同类型的右括号闭合。
        2. 左括号必须以正确的顺序闭合。

        输入：s = "()"
        输出：true

        输入：s = "([)]" => 先闭合[]，再去闭合() => 有效闭合括号
        输出：false

        输入：s = "()[]{}"
        输出：true

        1 <= s.length <= 10^4
        s 仅由括号 '()[]{}' 组成
     */

    // KeyPoint 只有小括号的场景 => 简化场景
    // 时间复杂度：O(n)
    // 空间复杂度：O(n) => 最差情况 O(n)，即都是 '('
    public static boolean isValid1(String s) {
        // KeyPoint 先将整体代码框架写好，再去考虑边界条件，即特殊情况下需要注意的
        // 有效括号 => 字符长度必须是偶数
        if (s.length() % 2 == 1) return false;
        // 循环数组实现双端队列 => 模拟栈
        // Java 推荐使用 ArrayDeque 代替 Stack
        ArrayDeque<Character> stack = new ArrayDeque<>();
        for (char c : s.toCharArray()) {
            // 先遇到的'('，不做处理，后面才来处理 => 先进后处理 => 栈
            if (c == '(') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) return false;
                // 代码严谨层面 => 在 stack 在 pop之前，需要判空，可能出现异常
                // 题目逻辑层面 => 遇到的是 ')'，但'(' 已经没有了，无法 pop
                stack.pop();
            }
        }
        return stack.isEmpty();
    }

    // KeyPoint 只有小括号的场景 => 优化：只使用一个变量代替 stack
    // 时间复杂度：O(n)
    // 空间复杂度：O(1)
    public static boolean isValid2(String s) {
        if (s.length() % 2 == 1) return false;
        int leftBraceCnt = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') {
                leftBraceCnt++;
            } else {
                if (leftBraceCnt == 0) return false;
                leftBraceCnt--;
            }
        }
        return leftBraceCnt == 0;
    }

    // test
    public static void main(String[] args) {
        String str1 = "(()))";
        String str2 = "(()";
        String str3 = "(())";
        System.out.println(isValid1(str1)); // false
        System.out.println(isValid1(str2)); // false
        System.out.println(isValid1(str3)); // true
        System.out.println("=========");
        System.out.println(isValid2(str1)); // false
        System.out.println(isValid2(str2)); // false
        System.out.println(isValid2(str3)); // true
    }

    // KeyPoint 方法一 栈
    // 时间复杂度：O(n)
    // 空间复杂度：O(n)
    public boolean isValid3(String s) {
        if (s.length() % 2 == 1) return false;
        ArrayDeque<Character> stack = new ArrayDeque<>();
        for (char c : s.toCharArray()) {
            // 左括号
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            } else { //右括号
                if (stack.isEmpty()) return false;
                char top = stack.pop();
                // 判断是否是相同类型括号
                // KeyPoint 左右括号的关系不要搞反了，这个错误犯了好几次了！
                // c 对应 右括号
                // top 对应 左括号
                if (c == ')' && top != '(') return false;
                if (c == '}' && top != '{') return false;
                if (c == ']' && top != '[') return false;
            }
        }
        return stack.isEmpty();
    }

    // KeyPoint 方法二 栈 + map
    // 时间复杂度：O(n)
    // 空间复杂度：O(n)
    public boolean isValid(String s) {
        if (s.length() % 2 == 1) return false;
        // map 维护左右括号映射，代码具有扩展性
        Map<Character, Character> map = new HashMap<>();
        map.put('(', ')');
        map.put('[', ']');
        map.put('{', '}');

        ArrayDeque<Character> stack = new ArrayDeque<>();
        for (char c : s.toCharArray()) {
            if (map.containsKey(c)) {
                stack.push(c);
            } else {
                if (stack.isEmpty()) return false;
                // top => 左括号
                char top = stack.pop();
                // c => 右括号
                // map.get(top) => 右括号
                // 判断两者是否相等
                if (c != map.get(top)) return false;
            }
        }
        return stack.isEmpty();
    }
}
