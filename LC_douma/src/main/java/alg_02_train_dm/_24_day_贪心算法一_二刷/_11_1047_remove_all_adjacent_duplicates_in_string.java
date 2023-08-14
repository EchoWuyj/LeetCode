package alg_02_train_dm._24_day_贪心算法一_二刷;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @Author Wuyj
 * @DateTime 2023-04-12 16:21
 * @Version 1.0
 */
public class _11_1047_remove_all_adjacent_duplicates_in_string {
     /*
        1047. 删除字符串中的所有相邻重复项
        给出由'小写字母'组成的字符串 S，重复项删除操作会选择两个相邻且相同的字母，并删除它们。
        在 S 上反复执行重复项删除操作，直到无法继续删除。
        在完成所有重复项删除操作后返回最终的字符串。答案保证唯一。

        示例：
        输入："abbaca"
        输出："ca"
        解释：输入："abbaca" => 消除 bb 之后，aa 是相邻重复字符，同样需移除，最终输出："ca"

        输入："abcddcbaca"
        输出： abc

        提示：
        1 <= S.length <= 20000
        S 仅由小写英文字母组成。
     */

    // KeyPoint 经验总结
    // 使用'栈'求解删除字符串中重复字母，这是常见的解题模式
    // 遍历字符串过程，常常是'后进的字符先操作'，故使用'栈'解决
    // => 重构字符串，使用'栈'解决

    // KeyPoint 方法一 栈
    // 时间复杂度：O(n)
    // 空间复杂度：O(n)
    public String removeDuplicates1(String s) {
        Deque<Character> deque = new ArrayDeque<>();
        // 遍历每个字符
        for (char c : s.toCharArray()) {
            if (!deque.isEmpty() && deque.peek() == c) {
                deque.pollFirst();
            } else {
                deque.push(c);
            }
        }
        StringBuilder res = new StringBuilder();
        while (!deque.isEmpty()) res.append(deque.pollLast());
        return res.toString();
    }

    // KeyPoint 方法二 StringBuilder 模拟栈 => 击败了 86.73%
    // 时间复杂度：O(n)
    // 空间复杂度：O(n)
    public String removeDuplicates2(String s) {
        StringBuilder res = new StringBuilder();
        for (char c : s.toCharArray()) {
            // 字符数组 char[] 模拟 栈
            // 将 StringBuilder 最后一个位置作为栈顶，通过这样的方式模拟栈
            if (res.length() > 0 && res.charAt(res.length() - 1) == c) {
                res.deleteCharAt(res.length() - 1);
            } else {
                res.append(c);
            }
        }
        return res.toString();
    }

    // KeyPoint 方法三 快慢指针 => 击败了 96.91%
    // 时间复杂度：O(n)
    // 空间复杂度：O(1)
    public String removeDuplicates(String s) {

        // 通过快慢指针，直接在字符数组上操作，不用通过栈来维护遍历过的字符
        // => 不需要使用额外的空间来存储先遍历过的字符

        char[] chars = s.toCharArray();
        // 通过双指针，划定不同的区间进行维护
        // [0,slow] 两端都包括在内 => 维护不删除字符
        // fast 遍历字符数组
        int slow = -1, fast = 0;
        int n = s.length();
        while (fast < n) {
            if (slow >= 0 && chars[fast] == chars[slow]) {
                // [fast] == [slow]，
                // 两个 fast 和 slow 对应的字符都需要删除
                // 故 fast++ 同时 slow--;

                slow--;
                fast++;
            } else {
                slow++;
                // 0 ~ slow 维护不删除字符，先移动 slow，再赋值
                // 使用 chars[fast] 覆盖原数组 chars[slow]
                chars[slow] = chars[fast];
                fast++;
            }
        }
        // 需要将 chars 中的前 slow 个字符组成新的字符串
        // 字符数组，偏移位置，长度
        return new String(chars, 0, slow + 1);
    }
}
