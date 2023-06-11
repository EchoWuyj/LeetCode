package alg_02_train_dm._06_day_位运算;

/**
 * @Author Wuyj
 * @DateTime 2023-04-23 13:44
 * @Version 1.0
 */
public class _13_405_convert_a_number_to_hexadecimal {

    /*
        405. 数字转换为十六进制数
        给定一个整数，编写一个算法将这个数转换为十六进制数。
        对于负整数，我们通常使用 补码运算 方法。

        注意:
        十六进制中所有字母(a-f)都必须是小写。
        十六进制字符串中不能包含多余的前导零。
        如果要转化的数为 0，那么以单个字符'0'来表示；
        对于其他情况，十六进制字符串中的第一个字符将不会是 0 字符。
        给定的数确保在 32 位有符号整数范围内。
        不能使用任何由库提供的将数字直接转换或格式化为十六进制的方法。

        示例 1：
        输入:
        26
        输出:
        "1a" => 没有多余的前导 0


        26 二进制 0000 0000 0000 0000 0000 0000 0001 1010
        26 16进制

        二进制  16进制
        0000     '0'
        0001     '1'
        0010     '2'
        0011     '3'
        0100     '4'
        0101     '5'
        0110     '6'
        0111     '7'
        1000     '8'
        1001     '9'
        1010     'a' -> 10进制 10
        1011     'b'
        1100     'c'
        1101     'd'
        1110     'e'
        1111     'f' -> 10进制 15

        示例 2：
        输入:
        -1
        输出:
        "ffffffff"

        -1 二进制(在计算机中使用'补码')

        -1 原码 1000 0000 0000 0000 0000 0000 0000 0001
           反码 1111 1111 1111 1111 1111 1111 1111 1110
           加 1 1111 1111 1111 1111 1111 1111 1111 1111

        -1 16进制 ffffffff

     */

    public String toHex(int num) {
        if (num == 0) return "0";
        // index => 10 进制数字
        // [index] => 16 进制字符
        char[] hexChars = {'0', '1', '2', '3', '4', '5', '6', '7',
                '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

        // KeyPoint 字符串拼接效率很低，推荐使用 StringBuilder 进行拼接
        String res = "";
        while (num != 0) {
            // ... 1111 => 最后 4 位都是 1，对应 10 进制为 15
            int index = num & 15;
            // 在前面位置拼接
            res = hexChars[index] + res;
//            res.insert(0, hexChars[index]);

            // 因为存在负数，所以需要无符号右移 4 位
            num >>>= 4;
        }
        return res;
    }

    public String toHex1(int num) {
        if (num == 0) return "0";

        char[] hexChars = {'0', '1', '2', '3', '4', '5', '6', '7',
                '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

        StringBuilder sb = new StringBuilder();
        while (num != 0) {
            int index = num & 15;
            // 在前面位置拼接，从 0 位置插入 hexChars[index]
            sb.insert(0, hexChars[index]);
            num >>>= 4;
        }
        return sb.toString();
    }
}
