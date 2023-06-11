package alg_02_train_dm._13_day_综合应用一;

import java.util.ArrayDeque;

/**
 * @Author Wuyj
 * @DateTime 2023-05-25 21:04
 * @Version 1.0
 */
public class _11_844_backspace_string_compare {
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

            1 <= S.length <= 200
            1 <= T.length <= 200
            S 和 T 只含有小写字母以及字符 '#'。

            进阶：你可以用 O(N) 的时间复杂度和 O(1) 的空间复杂度解决该问题吗？
     */

    public boolean backspaceCompare1(String s, String t) {
        return build(s).equals(build(t));
    }

    // KeyPoint 方法一 重建字符串
    // 时间复杂度：O(m + n)
    // 空间复杂度：O(m + n)
    private String build1(String str) {
        StringBuilder sb = new StringBuilder();
        for (char c : str.toCharArray()) {
            if (c != '#') {
                sb.append(c);
                // 需要保证 sb.length() > 0，否则 sb.length() - 1 可能越界
            } else if (sb.length() > 0) {
                sb.deleteCharAt(sb.length() - 1);
            }
        }
        return sb.toString();
    }

    // KeyPoint 方法二 栈
    // 时间复杂度：O(m + n)
    // 空间复杂度：O(m + n)
    private String build2(String str) {
        // ab##
        // 遇到 'a' 不处理，遇到 'b' 不处理，遇到 '#' 处理 'b'，遇到 '#' 处理 'a'
        // 前面字符的操作，暂时不知道，需要由后面字符决定 => 栈 => 先进的元素，后处理 (后进的元素，先处理)
        // 'a'，'b' 先入栈，遇到 '#'，处理栈顶元素
        ArrayDeque<Character> stack = new ArrayDeque<>();
        for (char c : str.toCharArray()) {
            if (c != '#') {
                stack.push(c);
            } else if (!stack.isEmpty()) {
                stack.pop();
            }
        }
        // 返回值为 String
        // String 有 , 空格，[] 形式 String，如：[c, a]
        return stack.toString();
    }

    // KeyPoint 方法三 双指针(从前往后遍历)
    // 时间复杂度：O(m + n)
    // 空间复杂度：O(1)
    private String build(String str) {
        char[] chars = str.toCharArray();
        // 0 ~ slow 存放：不是'#'且不用删除字符
        // slow ~ fast，存放需要删除字符
        // KeyPoint 双指针本质：划分指针区间，每个区间进行怎样操作
        int slow = -1, fast = 0;
        while (fast < chars.length) {
            if (chars[fast] != '#') {
                slow++;
                // 字符交换，为了保留需要的字符
                if (slow != fast) swap(chars, slow, fast);
                // slow 左移，最多为 -1，不能再左移
            } else if (slow > -1) {
                // chars[fast] == '#'，slow 左移，表示该位置元素删除
                slow--;
            }
            fast++;
        }
        // 最后 0 ~ slow 为：不是'#'且不用删除字符的区间
        return slow == -1 ? "" : new String(chars, 0, slow + 1);
    }

    private void swap(char[] chars, int i, int j) {
        char tmp = chars[i];
        chars[i] = chars[j];
        chars[j] = tmp;
    }

    // KeyPoint 方法四 双指针(从后往前遍历)
    // 时间复杂度：O(m + n)
    // 空间复杂度：O(1)
    public boolean backspaceCompare(String s, String t) {
        int i = s.length() - 1;
        int j = t.length() - 1;
        int skipS = 0;
        int skipT = 0;

        // s 或 t 有字符未处理，则一直往前走，循环遍历
        // KeyPoint 补充说明
        // 若 s 和 t 字符串都处理完了，则循环就会停止，这种方式对于处理两个可能长度不一致的字符串特别有效。
        // 不论哪个字符串先处理完，都可以确保另一个字符串的剩余部分会被正确处理
        while (i >= 0 || j >= 0) {

            // 回退 S 字符串的字符
            while (i >= 0) {
                // 遇到 '#'，记录需要跳过字符个数
                if (s.charAt(i) == '#') {
                    skipS++;
                    i--;
                } else if (skipS > 0) {
                    // 跳过字符，移动指针
                    skipS--;
                    i--;
                } else {
                    // i 为第一不需要删除的字符
                    break;
                }
            }

            // 回退 T 字符串的字符
            while (j >= 0) {
                if (t.charAt(j) == '#') {
                    skipT++;
                    j--;
                } else if (skipT > 0) {
                    skipT--;
                    j--;
                } else {
                    break;
                }
            }

            // 判断 S 和 T 是否相等
            // 1.若当前的 i 和 j 对应的字符不相等，提前返回 false
            if (i >= 0 && j >= 0 && s.charAt(i) != t.charAt(j)) return false;

            // 2.若有一个指针到头了，还有一个不到头，则两者必然不相等，提前返回 false
            if ((i >= 0) != (j >= 0)) return false;

            i--;
            j--;
        }
        // 若没有提前返回 false，则最终是 true
        return true;
    }
}
