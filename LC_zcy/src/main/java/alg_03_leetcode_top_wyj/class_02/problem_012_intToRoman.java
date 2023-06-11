package alg_03_leetcode_top_wyj.class_02;

import javax.management.DynamicMBean;

/**
 * @Author Wuyj
 * @DateTime 2023-02-21 23:24
 * @Version 1.0
 */
public class problem_012_intToRoman {
    public String intToRoman(int num) {
        String[][] str = {
                {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"},
                {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"},
                {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"},
                {"", "M", "MM", "MMM"} //1000
        };

        StringBuilder res = new StringBuilder();
        res.append(str[3][num / 1000 % 10]);
        res.append(str[2][num / 100 % 10]);
        res.append(str[1][num / 10 % 10]);
        res.append(str[0][num % 10]);
        return res.toString();
    }
}
