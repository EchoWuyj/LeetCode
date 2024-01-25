package alg_03_high_frequency._01_codetop_2024_01;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @Author Wuyj
 * @DateTime 2024-01-16 21:43
 * @Version 1.0
 */
public class _74_394_decodeString {
    public String decodeString(String s) {
        Deque<String> strDeque = new ArrayDeque<>();
        Deque<Integer> numDeque = new ArrayDeque<>();

        int num = 0;
        StringBuilder res = new StringBuilder();

        for (char c : s.toCharArray()) {
            if (c >= '0' && c <= '9') {
                num = num * 10 + c - '0';
            } else if (c == '[') {
                numDeque.push(num);
                strDeque.push(res.toString());
                num = 0;
                res.delete(0, res.length());
            } else if (c == ']') {
                String item = res.toString();
                int count = numDeque.pop();
                for (int i = 1; i < count; i++) {
                    res.append(item);
                }
                res.insert(0, strDeque.pop());
            } else {
                res.append(c);
            }
        }
        return res.toString();
    }
}
