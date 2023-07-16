package alg_02_train_dm._10_day_栈和队列;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author Wuyj
 * @DateTime 2023-07-15 20:22
 * @Version 1.0
 */
public class _01_20_valid_parentheses3 {

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
