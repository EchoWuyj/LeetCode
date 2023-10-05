package alg_02_train_wyj._22_day_回溯算法一;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-04-09 18:12
 * @Version 1.0
 */
public class _11_17_LetterCombinationsOfAPhoneNumber2 {

    private HashMap<Character, String> map;
    private List<String> res;
    private String digits;

    public List<String> letterCombinations(String digits) {
        this.digits = digits;
        res = new ArrayList<>();
        if (digits.equals("") || digits.length() == 0) return res;
        map = new HashMap<>();
        map.put('2', "abc");
        map.put('3', "def");
        map.put('4', "ghi");
        map.put('5', "jkl");
        map.put('6', "mno");
        map.put('7', "pqrs");
        map.put('8', "tuv");
        map.put('9', "wxyz");
        dfs(digits, res, 0, "");
        return res;
    }

    public void dfs(String digits, List<String> res, int index, String combination) {
        if (index == digits.length()) {
            res.add(combination);
            return;
        }
        char digitsChar = digits.charAt(index);
        String str = map.get(digitsChar);
        for (char c : str.toCharArray()) {
            dfs(digits, res, index + 1, combination + c);
        }
    }
}
