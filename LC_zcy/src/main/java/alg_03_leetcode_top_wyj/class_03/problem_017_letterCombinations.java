package alg_03_leetcode_top_wyj.class_03;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-02-20 19:56
 * @Version 1.0
 */
public class problem_017_letterCombinations {
    char[][] phone = {
            {'a', 'b', 'c'},
            {'d', 'e', 'f'},
            {'g', 'h', 'i'},
            {'j', 'k', 'l'},
            {'m', 'n', 'o'},
            {'p', 'q', 'r', 's'},
            {'t', 'u', 'v'},
            {'w', 'x', 'y', 'z'}
    };

    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();
        if (digits == null || digits.length() == 0) {
            return res;
        }

        char[] digitsChars = digits.toCharArray();
        char[] path = new char[digitsChars.length];
        process(digitsChars, path, 0, res);
        return res;
    }

    public void process(char[] digitsChars, char[] path, int index, List<String> res) {
        if (index == digitsChars.length) {
            res.add(String.valueOf(path));
        } else {
            char[] letterChars = phone[digitsChars[index] - '2'];
            for (char letterChar : letterChars) {
                path[index] = letterChar;
                process(digitsChars, path, index + 1, res);
            }
        }
    }
}
