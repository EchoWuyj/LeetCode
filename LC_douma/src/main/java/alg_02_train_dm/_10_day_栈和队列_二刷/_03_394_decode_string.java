package alg_02_train_dm._10_day_栈和队列_二刷;

import java.util.ArrayDeque;

/**
 * @Author Wuyj
 * @DateTime 2023-04-24 21:50
 * @Version 1.0
 */
public class _03_394_decode_string {

    /*
        394 号算法题：字符串解码
        给定一个经过编码的字符串，返回它解码后的字符串。
        编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。
        注意 k 保证为正整数。

        你可以认为输入字符串总是有效的；输入字符串中没有额外的空格，
        且输入的方括号总是符合格式要求的。

        此外，你可以认为原始数据不包含数字，所有的数字只表示重复的次数 k，
        例如不会出现像 3a 或 2[4] 的输入。

        输入：s = "3[a]2[bc]"  => 编码
        输出：aaabcbc          => 解码

        输入：s = "3[a2[c]]"
        输出：accaccacc

        输入：s = "2[abc]3[cd]ef"
        输出： abcabccdcdcdef

        输入：s = "abc3[cd]xyz"
        输出：abccdcdcdxyz
    */

    // 直接模拟
    public String decodeString(String s) {

        // 3 [ a ] 2 [ b c ]
        // 遍历字符串，遇到数字 3，但是不知道 [] 里面存储的字符，即无法先重复几次
        // 故需要先将 3 存储起来，后面再用 => '先存后处理' => 栈

        int num = 0;
        StringBuilder res = new StringBuilder();

        // 使用双栈，分别存储不同的信息
        // 数字栈 => 重复次数
        ArrayDeque<Integer> numStack = new ArrayDeque<>();
        // 字符栈 => 重复字符串
        ArrayDeque<String> strStack = new ArrayDeque<>();

        // 13[ak2[bc]] => 多位数字
        // 2[a2[b2[m]d]] => 嵌套编码
        for (char c : s.toCharArray()) {
            // 根据不同字符：数字，'['，']'，字母，多种情况
            // => 使用 if 进行分支选择，执行不同的操作
            // 注意：数字范围：[0,9]，不是[1,9]
            if (c >= '0' && c <= '9') {
                // num 可能是多位数字，比如 num = 13
                // 故前面几个字符 c 都是数字字符，需要循环判断，将其作为整体 num
                num = num * 10 + (c - '0');
                // KeyPoint 按照 '['，']'，分别进行压栈和出栈操作
                // 1.遇到 '[' numStack，strStack 压栈操作
            } else if (c == '[') {
                // 数字压栈 => 因为不知道后面重复字符形式
                numStack.push(num);
                // 字符串压栈 => 压入整体字符串
                strStack.push(res.toString());
                // KeyPoint 注意：数字和字符串信息已经保存到栈中，故需要将 num 和 res 清空，方便后续存储
                // 数字重置
                num = 0;
                // res 清空 => 从 0 开始清空
                res.delete(0, res.length());
                // KeyPoint 补充说明：StringBuilder 的 delete 方法
                // delete(int start, int end): [start,end)，实际删除 end-1，end 是不包括的
                // 故 end = res.length()，实际删除 res.length() - 1

                // 2.遇到 ']' numStack，strStack 出栈操作
            } else if (c == ']') {
                // 需要拼接字符串 res，此时 res 还没有入栈
                String item = res.toString();
                // 数字出栈 => 重复次数
                int itemCnt = numStack.pop();
                // 1.res 自身是一个 item，故 for 循环从 1 开始
                // 2.numStack.pop() 不能放到 for 循环的比较位置，即 i < numStack.pop()
                //   否则，每轮循环都会执行 numStack.pop() 操作，而不是只是 pop 一次
                for (int i = 1; i < itemCnt; i++) {
                    res.append(item);
                }
                // strStack 出栈，将字符串插入到 res 头部，注意：不是追加在尾部
                // 字符串出栈 => res 前面字符串
                res.insert(0, strStack.pop());
            } else {
                // 一般的字母字符，追加到 res
                // => 不是压栈操作 strStack.push(c)
                // => 只有遇到 [，才进行压栈
                res.append(c);
            }
        }
        return res.toString();
    }
}
