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
        StringBuilder builder = new StringBuilder();
        ArrayDeque<Integer> numStack = new ArrayDeque<>();
        ArrayDeque<String> strStack = new ArrayDeque<>();
        for (char c : s.toCharArray()) {
            if (c >= '0' && c <= '9') {
                num = num * 10 + (c - '0');
            } else if (c == '[') {
                // 压栈
                numStack.push(num);
                strStack.push(builder.toString());
                // 清零
                num = 0;
                builder.delete(0, builder.length());
            } else if (c == ']') {
                // [ 之后，内部的 String
                String item = builder.toString();
                int count = numStack.pop();
                for (int i = 1; i < count; i++) {
                    builder.append(item);
                }
                builder.insert(0, strStack.pop());
            } else {
                builder.append(c);
            }
        }
        return builder.toString();
    }
}
