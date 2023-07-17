package alg_02_train_wyj._10_day_栈和队列;

import java.util.ArrayDeque;

/**
 * @Author Wuyj
 * @DateTime 2023-04-25 15:21
 * @Version 1.0
 */
public class _03_394_decode_string {

    public String decodeString(String s) {
        StringBuilder res = new StringBuilder();
        ArrayDeque<Integer> numStack = new ArrayDeque<>();
        ArrayDeque<String> strStack = new ArrayDeque<>();
        int num = 0;
        for (char c : s.toCharArray()) {
            if (c >= '0' && c <= '9') {
                num = num * 10 + (c - '0');
            } else if (c == '[') {
                numStack.push(num);
                strStack.push(res.toString());
                num = 0;
                res.delete(0, res.length());
            } else if (c == ']') {
                String item = res.toString();
                int count = numStack.pop();
                for (int i = 1; i < count; i++) {
                    res.append(item);
                }
                String preStr = strStack.pop();
                res.insert(0, preStr);
            } else {
                res.append(c);
            }
        }
        return res.toString();
    }
}
