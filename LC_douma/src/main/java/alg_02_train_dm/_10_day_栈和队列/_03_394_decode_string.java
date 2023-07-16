package alg_02_train_dm._10_day_栈和队列;

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

        你可以认为输入字符串总是有效的；输入字符串中没有额外的空格，且输入的方括号总是符合格式要求的。

        此外，你可以认为原始数据不包含数字，所有的数字只表示重复的次数 k ，
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
    public String decodeString(String s) {

        // 3 [ a ] 2 [ b c ]
        // 遇到数字 3，但是不知道 [] 里面存储的字符，即无法先重复几次
        // 故需要先将 3 存储起来，后面再用 => '先存后处理' => 栈

        int num = 0;
        StringBuilder res = new StringBuilder();

        // 数字栈
        ArrayDeque<Integer> numStack = new ArrayDeque<>();
        // 字符栈
        ArrayDeque<String> strStack = new ArrayDeque<>();

        // 13[ak2[bc]]
        // 2[a2[b2[m]d]]
        for (char c : s.toCharArray()) {
            if (c >= '0' && c <= '9') {
                // num 可能是多位数字，比如 num = 13
                num = num * 10 + (c - '0');
                // 按照 '['，']'，进行分类
            } else if (c == '[') {
                numStack.push(num);
                strStack.push(res.toString());
                // delete(int start, int end): [start,end)，实际 end-1
                // 因为 end 是不包括的，所以 end = res.length()，实际删除到 res.length() - 1
                res.delete(0, res.length());
                num = 0;
            } else if (c == ']') {
                String item = res.toString();
                // numStack.pop() 不能放到 for 循环的比较位置，即 i < numStack.pop()
                // 否则，每轮循环都会执行 numStack.pop() 操作，而不是只是 pop 一次
                int itemCnt = numStack.pop();
                // res 自身是一个 itemCnt，故 for 循环从 1 开始
                for (int i = 1; i < itemCnt; i++) {
                    res.append(item);
                }
                // KeyPoint res 将 strStack.pop() 插入到 res 头部，而不是追加在尾部，特别注意
                res.insert(0, strStack.pop());
            } else {
                // 字母字符，追加到 res
                res.append(c);
            }
        }

        return res.toString();
    }
}
