package alg_02_train_wyj._06_day_位运算;

/**
 * @Author Wuyj
 * @DateTime 2023-04-23 16:27
 * @Version 1.0
 */
public class _13_405_convert_a_number_to_hexadecimal {

    public String toHex(int num) {
        if (num == 0) return "0";
        char[] map = {'0', '1', '2', '3', '4', '5', '6', '7', '8',
                '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        StringBuilder sb = new StringBuilder();
        while (num != 0) {
            int index = num & 15;
            sb.insert(0, map[index]);
            num >>>= 4;
        }
        return sb.toString();
    }
}
