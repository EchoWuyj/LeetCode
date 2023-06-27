package alg_02_train_wyj._22_day_回溯算法一;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author Wuyj
 * @DateTime 2023-04-09 18:12
 * @Version 1.0
 */
public class _11_17_LetterCombinationsOfAPhoneNumber2 {

    private Map<Character, String> map = new HashMap<Character, String>() {{
        put('2', "abc");
        put('3', "def");
        put('4', "ghi");
        put('5', "jkl");
        put('6', "mno");
        put('7', "pqrs");
        put('8', "tuv");
        put('9', "wxyz");
    }};

    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();
        if (digits == null || digits.isEmpty()) return res;
        dfs(digits, 0, "", res);
        return res;
    }

    private void dfs(String digits, int index, String combination, List<String> res) {
        if (index == digits.length()) {
            res.add(combination);
            return;
        }

        char num = digits.charAt(index);
        String letters = map.get(num);
        for (char c : letters.toCharArray()) {
            dfs(digits, index + 1, combination + c, res);
        }
    }
}
