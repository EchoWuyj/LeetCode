package alg_02_train_dm._10_day_栈和队列_二刷;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * @Author Wuyj
 * @DateTime 2023-07-15 20:22
 * @Version 1.0
 */
public class _01_20_valid_parentheses2 {

    // KeyPoint 方法一:暴力解 => 双指针
    public boolean isValid(String s) {
        // 字符串是不可变的，其中没有删除字符功能
        // 故需要将其转成 StringBuilder 形式
        StringBuilder sb = new StringBuilder(s);
        // 遍历次数
        int count = sb.length() / 2;
        // O(n^2)
        for (int i = 0; i < count; i++) {
            // 凡是出现相邻位置索引，i 和 i+1; i-1 和 i
            // => 注意 for 循环条件注意避免索引越界，比如：j<len 导致 j+1 越界
            // 使用 j 和 j+1 指针，每次对两个括号比较，匹配则删除
            int len = sb.length();
            // KeyPoint 注意：len 不能定义到外面
            // 因为每轮 for 循环，sb 长度都会发生变化，不是一开始 sb 的 len
            for (int j = 0; j < len - 1; j++) {
                char c1 = sb.charAt(j);
                char c2 = sb.charAt(j + 1);
                // 若相邻括号字符，符合有效括号，则删除
                if (isMatched(c1, c2)) {
                    // delete [ )，第二个位置是不包含的，所以 j+1 再 +1
                    // 注意：
                    // 1.使用 sb 来遍历每个位置，而不是使用字符数组
                    //   char[] chars = s.toCharArray();
                    // 2.因为 sb 在存在删除字符，字符元素在遍历的过程不断变化
                    //   而 chars 却是不变的，不能保证是同步变化的
                    sb.delete(j, j + 2);
                    // 结束内层循环
                    break;
                }
            }
        }
        // KeyPoint 凡是有相同开头变量，自己得小心
        // 容易将 sb 和 s 相混淆
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
    // 先完成大体逻辑，再分析特殊情况(corner case)
    // => 保证程序代码的正确性和提高代码的常数项时间
    public boolean isValid1(String s) {
        if (s == null) return true;
        // 代码优化：如果字符串的长度为奇数的话，那么肯定不是合法的
        if (s.length() % 2 == 1) return false;
        // 判断奇数，另外一种写法
        // if ((s.length() & 1) == 1) return false;
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            // 如果是左侧类型括号，包括三种类型 '('，'{'，'['，则直接入栈
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
                // KeyPoint 加深 if else 理解
                // 1.对于每个位置进行二选一判断，而不是两个相互独立的 if 判断
                // 2.进入 else 都是 ']'，'}'，')'
            } else {
                if (stack.isEmpty()) return false;
                // 记录栈顶元素 top
                char top = stack.pop();
                // 判断括号是否匹配
                // 注意：左右括号的关系不要搞反了，这个错误犯了好几次了！
                // 1.c => 右括号
                // 2.top => 左括号
                // KeyPoint 排除逻辑，有一个为 false，整体为 false
                if (c == ')' && top != '(') return false;
                if (c == '}' && top != '{') return false;
                if (c == ']' && top != '[') return false;
            }
        }
        // 如果栈不为空，则说明栈中还有左括号，则也算没有匹配好
        return stack.isEmpty();
    }

    // KeyPoint 方法三 栈 + map
    // 时间复杂度：O(n)
    // 空间复杂度：O(n)
    public boolean isValid2(String s) {
        if (s.length() % 2 == 1) return false;
        // map 维护左右括号映射，代码具有扩展性
        Map<Character, Character> map = new HashMap<>();
        // 左侧类型括号为 key => '('，'{'，'['
        map.put('(', ')');
        map.put('[', ']');
        map.put('{', '}');

        ArrayDeque<Character> stack = new ArrayDeque<>();
        for (char c : s.toCharArray()) {
            // c 是 map 的 key => c 是左侧类型括号，压栈
            if (map.containsKey(c)) {
                stack.push(c);
            } else {
                if (stack.isEmpty()) return false;
                // top => 栈中元素 => 左括号 => map 的 key
                char top = stack.pop();
                // top => 左括号 => map.get(top) => 右括号
                // c 右括号
                // 判断两者是否相等 => c 与 map.get(top)
                if (c != map.get(top)) return false;
            }
        }
        return stack.isEmpty();
    }
}
