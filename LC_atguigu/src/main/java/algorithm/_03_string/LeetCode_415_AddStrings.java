package algorithm._03_string;

/**
 * @Author Wuyj
 * @DateTime 2022-03-02 19:21
 * @Version 1.0
 */
public class LeetCode_415_AddStrings {
    public String addStrings(String num1, String num2) {
        // 从个位往前相加,得到一位就将其放到字符串中,依次追加
        // 定义一个StringBuffer,保存最终的结果
        StringBuffer result = new StringBuffer();

        // 定义遍历两个字符串的初始位置
        // 可以理解成字符组成的数组,其中每个字符都是0-9的数字
        int i = num1.length() - 1;
        int j = num2.length() - 1;

        // 用一个变量保存当前的进位
        int carry = 0;

        // 从个位开始依次遍历所有数位
        // 只要还有数没有计算就继续,其他数位补0
        while (i >= 0 || j >= 0 || carry != 0) {
            // 取两数当前的对应数位
            // 判断i是否>=0,若<0则直接补0即可
            // 通过charAt(i)-'0'将字符要将ascii码转换为数字(ascii之间的数值差是固定)
            int n01 = i >= 0 ? num1.charAt(i) - '0' : 0;
            int n02 = j >= 0 ? num2.charAt(j) - '0' : 0;

            // 对当前数位求和
            int sum = n01 + n02 + carry;

            // 把sum的个位保存到结果中,十位作为进位保存下来
            // 对10取余得到个位
            result.append(sum % 10);
            // 除10得到的商就是十位
            carry = sum / 10;

            // 移动指针，继续遍历下一位
            i--;
            j--;
        }

        // 需要将最初倒序排列的字符串反转
        return result.reverse().toString();
    }

    public static void main(String[] args) {
        String num1 = "745346";
        String num2 = "8564";

        LeetCode_415_AddStrings addStrings = new LeetCode_415_AddStrings();
        System.out.println(addStrings.addStrings(num1, num2));

        // 底层计算是以二进制的形式存储
        // 数据类型:按照什么方式去解读一串二进制码
        char c = '0';
        // 自动类型转换
        int i = c;
        // 将0011 0000 解析成字符表示字符c;
        System.out.println(c);
        // 将0011 0000 解析成整型变量表示十进制48
        System.out.println(i);
    }
}
