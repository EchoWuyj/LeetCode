package alg_02_train_dm._24_day_贪心算法一;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @Author Wuyj
 * @DateTime 2023-04-13 19:39
 * @Version 1.0
 */
public class _12_1209_remove_all_adjacent_duplicates_in_string_ii {

    /*
        给你一个字符串 s，「k 倍重复项删除操作」将会从 s 中选择 k 个相邻且相等的字母，
        并删除它们，使被删去的字符串的左侧和右侧连在一起。
        你需要对 s 重复进行无限次这样的删除操作，直到无法继续为止。
        在执行完所有删除操作后，返回最终得到的字符串。
        本题答案保证唯一。

        输入：s = "abcd", k = 2
        输出："abcd"
        解释：没有要删除的内容。

        输入：s = "deeedbbcccbdaa", k = 3
        输出："aa"
        解释：
        先删除 "eee" 和 "ccc"，得到 "ddbbbdaa"
        再删除 "bbb"，得到 "dddaa"
        最后删除 "ddd"，得到 "aa"

        输入：s = "pbbcggttciiippooaais", k = 2
        输出："ps"

     */

    // KeyPoint 方法一 栈
    // 关键：栈中存的不是字符，而是计数 => 当前字符与前一个不同时，往栈中压入 1，否则栈顶元素加 1。
    // 迭代字符串：
    // 1.如果当前字符与前一个相同，栈顶元素加 1。否则，往栈中压入 1。
    // 2.如果栈顶元素等于 k，则从字符串中删除这 k 个字符，并将 k 从栈顶移除。
    public String removeDuplicates1(String s, int k) {
        StringBuilder res = new StringBuilder(s);
        // 使用栈计数
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < res.length(); i++) {
            if (i == 0 || res.charAt(i) != res.charAt(i - 1)) {
                stack.push(1);
            } else {
                int counts = stack.pop() + 1;
                if (counts == k) {
                    // start 包括，end 不包括
                    // | _ _ _ |  i+1
                    //   k 个     end
                    // start = i+1-k
                    // KeyPoint 抽象字母 k 实在绕不清楚，通过具体数字来分析规律
                    res.delete(i - k + 1, i + 1);
                    // 更新索引
                    i = i - k;
                } else {
                    stack.push(counts);
                }
            }
        }
        return res.toString();
    }

    // KeyPoint 方法二 快慢指针 => 使用快慢指针复制字符，每次需要删除 k 个元素时，只需要将慢指针回退 k 个位置
    // 1.初始慢指针 slow 等于 0
    // 2.使用快指针 fast 遍历字符串
    //   令 s[fast] = s[slow]
    //   如果 s[slow] = s[slow - 1]，则栈顶元素加 1
    //      否则，栈中压入 1
    //   如果计数器等于 k，slow = slow - k，并弹出栈顶元素
    // 3.返回字符串的前 slow 个字符
    public String removeDuplicates(String s, int k) {
        char[] chars = s.toCharArray();
        // 使用栈计数
        Deque<Integer> stack = new ArrayDeque<>();
        int slow = 0;
        for (int fast = 0; fast < s.length(); slow++, fast++) {
            // fast 删除 k 个元素时，slow 跟着 fast 回退
            chars[fast] = chars[slow];
            if (fast == 0 || chars[fast] != chars[fast - 1]) {
                stack.push(1);
            } else {
                int count = stack.pop() + 1;
                if (count == k) {
                    fast = fast - k;
                } else {
                    stack.push(count);
                }
            }
        }
        return new String(chars, 0, slow);
    }
}
