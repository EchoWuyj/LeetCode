package alg_03_high_frequency._01_codetop_2024_01;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @Author Wuyj
 * @DateTime 2024-01-16 21:43
 * @Version 1.0
 */
public class _74_394_decodeString {

    // 字符串解码
    // 栈
    public String decodeString(String s) {
        Deque<String> strDeque = new ArrayDeque<>();
        Deque<Integer> numDeque = new ArrayDeque<>();
        // 保存计算数值
        int num = 0;
        StringBuilder res = new StringBuilder();

        // 输入：s = "3[a]2[bc]"
        // 输出："aaabcbc"

        for (char c : s.toCharArray()) {
            // 针对每个字符进行判断，不同的字符不同的处理逻辑
            if (c >= '0' && c <= '9') {
                num = num * 10 + c - '0';
            } else if (c == '[') {
                // 压栈
                numDeque.push(num);
                strDeque.push(res.toString());
                // 清零
                num = 0;
                res.delete(0, res.length());
            } else if (c == ']') {
                // 遇到 ']'，则将最近一对中括号 '[]' 进行处理
                String item = res.toString();
                int count = numDeque.pop();
                // i 从 1 开始，item 自身算作一个
                for (int i = 1; i < count; i++) {
                    res.append(item);
                }
                // 将一轮结果插入 res 的头部
                res.insert(0, strDeque.pop());
            } else {
                // 字母字符
                res.append(c);
            }
        }
        return res.toString();
    }
}
