package alg_02_train_wyj._10_day_栈和队列;

import java.util.ArrayDeque;

/**
 * @Author Wuyj
 * @DateTime 2023-04-25 15:21
 * @Version 1.0
 */
public class _03_394_decode_string {

    public String decodeString(String s) {
        int num = 0;
        StringBuilder res = new StringBuilder();
        ArrayDeque<Integer> numStack = new ArrayDeque<>();
        ArrayDeque<String> strStack = new ArrayDeque<>();

        for (char c : s.toCharArray()) {
            if (c >= '0' && c <= '9') {
                num = num * 10 + (c - '0');
            } else if (c == '[') {
                numStack.push(num);
                String str = res.toString();
                strStack.push(str);
                res.delete(0, str.length());
                num = 0;
            } else if (c == ']') {
                String item = res.toString();
                int cnt = numStack.pop();
                for (int i = 1; i < cnt; i++) {
                    res.append(item);
                }

                res.insert(0, strStack.pop());
            } else {
                res.append(c);
            }
        }
        return res.toString();
    }
}
