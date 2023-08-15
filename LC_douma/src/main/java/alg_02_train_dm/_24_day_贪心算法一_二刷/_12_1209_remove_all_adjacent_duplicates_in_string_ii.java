package alg_02_train_dm._24_day_贪心算法一_二刷;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @Author Wuyj
 * @DateTime 2023-04-13 19:39
 * @Version 1.0
 */
public class _12_1209_remove_all_adjacent_duplicates_in_string_ii {

    /*
            1209 删除字符串中的所有相邻重复项 II
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

    // KeyPoint 视频缺失，代码存在

    // KeyPoint 方法一 栈
    public String removeDuplicates1(String s, int k) {

        // 不同点：栈中存的不是字符，而是计数
        // => 当前字符与前一个不同时，往栈中压入 1，否则栈顶元素加 1

        // 迭代字符串
        // 1.如果当前字符与前一个相同，栈顶元素加 1。否则，往栈中压入 1。
        // 2.如果栈顶元素等于 k，则从字符串中删除这 k 个字符，并将 k 从栈顶移除。

        StringBuilder sb = new StringBuilder(s);
        // 使用栈计数
        Deque<Integer> stack = new ArrayDeque<>();

        // KeyPoint 易错点
        // 针对 StringBuilder，其长度会随着不同的操作动态变化的
        // 故不能使用初始的固定长度 n 作为 for 循环条件限制条件，否则数组越界
        // int n = sb.length();
        // for (int i = 0; i < n; i++)

        for (int i = 0; i < sb.length(); i++) {
            if (i == 0 || sb.charAt(i) != sb.charAt(i - 1)) {
                stack.push(1);
            } else {
                // sb.charAt(i) == sb.charAt(i-1)
                // 出现相邻重复元素，更新计数
                int counts = stack.pop() + 1;
                if (counts == k) {

                    // 1.通过 API 来理解
                    // StringBuilder 的 delete API 索引范围：[start,end)
                    // end 取不到，i 索引位置，使用 i+1 表示
                    // 且想要删除子串长度为 k，即保证 end - start = k
                    // => [i+1-k,i+1]

                    // 2.通过画图理解
                    //       x         i i+1
                    //       | _ _ _ _ |  ↑
                    //    start   k 个   end
                    // x - i + 1  = k
                    // => x = k + i -1;

                    sb.delete(i + 1 - k, i + 1);

                    // KeyPoint 更新索引，不要遗忘了
                    // k = 3，i = 5
                    // 0 1 2 3 4 5
                    //           ↑
                    //       |---i|
                    //     ↑
                    //     i => i - k
                    i = i - k;
                } else {
                    // 计数没有到 k，重新放入 stack 中
                    stack.push(counts);
                }
            }
        }
        return sb.toString();
    }

    // KeyPoint 方法二 快慢指针
    // => 使用快慢指针复制字符，每次需要删除 k 个元素时，只需要将慢指针回退 k 个位置
    public String removeDuplicates(String s, int k) {
        char[] chars = s.toCharArray();
        // 使用栈计数
        Deque<Integer> stack = new ArrayDeque<>();
        int slow = 0;
        // 使用 fast 指针，遍历字符数组
        // 每次 fast 和 fast 指针同步移动
        for (int fast = 0; fast < s.length(); fast++, slow++) {
            // 1.当 count = k 时，slow 删除 k 个相邻且相同元素
            //   slow 指针回退 slow -k 位置，但是 fast 不回退
            // 2.将 fast 指针遍历到值，赋值给 chars[slow]，让其 [slow] 和 [slow-1] 比较
            //   再次判断是否有 k 个相邻且相同元素
            // 注意：slow 和 fast 不是相同位置，赋值才有意义
            if (slow != fast) chars[slow] = chars[fast];
            if (slow == 0 || chars[slow] != chars[slow - 1]) {
                stack.push(1);
            } else {
                // chars[slow] == chars[slow-1]
                int count = stack.pop() + 1;
                if (count == k) {
                    // 移动 slow 指针，后续通过数组值覆盖
                    slow = slow - k;
                } else {
                    stack.push(count);
                }
            }
        }
        // KeyPoint 注意事项
        // 1.因为字符串 s 的字符串数组 chars 修改了，所以相当于 s 变掉了
        //   所以不能使用 substring，因为 substring 针对的还是原来的 s
        //   使用 new String 重新构建新字符串
        // 2.slow 表示 count，即为 String 的长度
        //   因为最后一轮循环，fast++ 和 slow++，其中 slow 多移动一个位置，正好表示索引从 0开始
        //   故 slow 直接是 String 的长度
        return new String(chars, 0, slow);
    }
}
