package alg_02_train_dm._04_day_字符串;

/**
 * @Author Wuyj
 * @DateTime 2023-08-16 12:14
 * @Version 1.0
 */
public class _11_12_integer_to_roman {
    public String intToRoman(int num) {
        // 把阿拉伯数字与罗马数字可能出现的所有情况和对应关系，放在两个数组中
        int[] nums = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] romans = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

        StringBuilder res = new StringBuilder();
        int index = 0;
        while (index < 13) {
            while (nums[index] <= num) {
                res.append(romans[index]);
                num -= nums[index];
            }
            index++;
        }
        return res.toString();
    }
}
