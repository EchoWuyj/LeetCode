package alg_02_train_dm._13_day_综合应用一_二刷;

import java.util.ArrayDeque;

/**
 * @Author Wuyj
 * @DateTime 2023-05-25 21:04
 * @Version 1.0
 */
public class _11_844_backspace_string_compare1 {
       /*
            844. 比较含退格的字符串
            给定 S 和 T 两个字符串，当它们分别被输入到空白的文本编辑器后
            判断二者是否相等，并返回结果。 # 代表退格字符。
            注意：如果对空文本输入退格字符，文本继续为空。

            输入：S = "ab#c", T = "ad#c"
            输出：true

            输入：S = "ab##", T = "c#d#"
            输出：true

            输入：S = "a##c", T = "#a#c"
            输出：true

            提示：
            1 <= S.length <= 200
            1 <= T.length <= 200
            S 和 T 只含有小写字母以及字符 '#'。

            进阶：你可以用 O(N) 的时间复杂度和 O(1) 的空间复杂度解决该问题吗？
     */

    // KeyPoint 主方法 调用 辅助方法
    public boolean backspaceCompare1(String s, String t) {
        return build1(s).equals(build1(t));
    }

    // KeyPoint 方法一 重建字符串
    // 时间复杂度：O(m + n)
    // 空间复杂度：O(m + n)
    private String build1(String str) {
        StringBuilder sb = new StringBuilder();
        for (char c : str.toCharArray()) {
            if (c != '#') {
                sb.append(c);
            } else { // c == '#'
                // 需要保证 sb.length() > 0，否则 sb.length() - 1 可能越界
                // => 写代码一定需要严谨
                if (sb.length() > 0) {
                    sb.deleteCharAt(sb.length() - 1);
                }
            }
        }
        return sb.toString();
    }

    // KeyPoint 方法二 栈
    // 时间复杂度：O(m + n)
    // 空间复杂度：O(m + n)
    private String build2(String str) {

        // 'ab##'
        // 遇到 'a' 不处理，遇到 'b' 不处理，遇到 '#' 处理 'b'，遇到 '#' 处理 'a'
        // => 前面字符的操作，暂时不知道，需要由后面字符 '#' 决定
        // => 栈 => 先进的元素，后处理 (后进的元素，先处理)

        // 'a'，'b' 先入栈，遇到 '#'，处理栈顶元素
        ArrayDeque<Character> stack = new ArrayDeque<>();
        for (char c : str.toCharArray()) {
            if (c != '#') {
                stack.push(c);
            } else {
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            }
        }
        // 最终返回类型 String => 调用 toString()
        // String 形式 [c, a] => 分隔符，有逗号, 有空格
        return stack.toString();
    }
}
