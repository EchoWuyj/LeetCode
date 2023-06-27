package alg_02_train_wyj._22_day_回溯算法一;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author Wuyj
 * @DateTime 2023-04-09 17:54
 * @Version 1.0
 */
public class _11_17_LetterCombinationsOfAPhoneNumber1 {

    private Map<Character, String> map;

    private List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();
        if (digits == null || digits.length() == 0) return res;
        map = new HashMap<>();
        map.put('2', "abc");
        map.put('3', "def");
        map.put('4', "ghi");
        map.put('5', "jkl");
        map.put('6', "mno");
        map.put('7', "pqrs");
        map.put('8', "tuv");
        map.put('9', "wxyz");

        dfs(digits, 0, new StringBuilder(), res);
        return res;
    }

    public void dfs(String digits, int index, StringBuilder sb, List<String> res) {
        if (index == digits.length()) {
            res.add(sb.toString());
            return;
        }
        char num = digits.charAt(index);
        String letters = map.get(num);
        for (char c : letters.toCharArray()) {
            sb.append(c);
            dfs(digits, index + 1, sb, res);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    public static void main(String[] args) {
        System.out.println(new _11_17_LetterCombinationsOfAPhoneNumber1()
                .letterCombinations("234"));

        // [adg, adh, adi, aeg, aeh, aei, afg, afh, afi, bdg, bdh, bdi, beg,
        // beh, bei, bfg, bfh, bfi, cdg, cdh, cdi, ceg, ceh, cei, cfg, cfh, cfi]
    }
}
