package alg_03_leetcode_top_zcy.class_02_Done;

/**
 * @Author Wuyj
 * @DateTime 2023-02-15 13:22
 * @Version 1.0
 */

// 整数转罗马数字
public class problem_012_intToRoman {
    /*
        I   1
        V   5
        X   10
        L   50
        C   100  century 一百年
        D   500
        M   1000

       Ⅰ  1
       Ⅱ  2
       Ⅲ  3
       Ⅳ  4  5-1
       Ⅴ  5
       Ⅵ  6  5+1
       Ⅶ  7
       Ⅷ  8
       Ⅸ  9  10-1
       Ⅹ  10

       可以放在V(5)和X(10)的左边,来表示4和9
       可以放在L(50)和C(100)的左边,来表示40和90
       可以放在D(500)和M(1000)的左边,来表示400和900

       示例:
       3068 => MMMlXⅧ
       58 => "LVIII" L=50, V=5, III=3

     */

    public String intToRoman(int num) {

        // 构建映射表
        // 因为数字范围 1 <= num <= 3999
        // 注意这种定义二维数组的方式
        String[][] c = {
                // KeyPoint 明确二维数组行和列
                //      0行表示个位,1行表示十位,不要搞错了
                {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"}, // 1-9
                {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"}, // 10-90
                {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"}, // 100-900
                {"", "M", "MM", "MMM"}}; //1000-3000

        /*
        在大多数情况下,使用StringBuilder.append()方法进行字符串拼接
        比使用字符串连接符（例如"+"）更有效率,特别是在需要迭代拼接大量字符串时
        这是因为在Java中,字符串是不可变的,也就是说,每次对字符串进行操作都会创建一个新的字符串对象,
        这将导致在拼接大量字符串时产生大量的中间对象,从而导致额外的内存开销和性能下降
         */
        StringBuilder romans = new StringBuilder();

        // 模拟方式实现

        // 除法
        // 1234 / 1000 = 1
        // 1234 / 100 = 12
        // 1234 / 10 = 123
        // 1234 / 1 = 1234

        // 取余
        // 1234 % 10 = 4
        // 1234 % 100 = 34
        // 1234 % 1000 = 234

        romans.append(c[3][num / 1000 % 10]);
        // 3010 百位没有数值,则使用""来拼接
        romans.append(c[2][num / 100 % 10]);
        romans.append(c[1][num / 10 % 10]);
        romans.append(c[0][num % 10]);

        return romans.toString();
    }

    public static void main(String[] args) {
        System.out.println(1234 / 1000);
        System.out.println(1234 / 100);
        System.out.println(1234 / 10);
        System.out.println(1234 % 1000);
        System.out.println(1234 % 100);
        System.out.println(1234 % 10);
    }
}
