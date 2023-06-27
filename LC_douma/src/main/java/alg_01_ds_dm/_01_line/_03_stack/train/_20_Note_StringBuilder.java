package alg_01_ds_dm._01_line._03_stack.train;

/**
 * @Author Wuyj
 * @DateTime 2023-03-11 15:22
 * @Version 1.0
 */
public class _20_Note_StringBuilder {
    /*
        StringBuilder
        public char charAt(int index)

     */

    public static void main(String[] args) {
        test1();
        System.out.println("=============");
        test2();
    }

    private static void test1() {

        StringBuilder sb = new StringBuilder("abc");

        // KeyPoint 1 增

        // 在字符串的末尾追加字符串，参数的类型可以是
        // Object; String; StringBuilder; StringBuffer; CharSequence;
        // boolean; char; int; long; float; double
        sb.append(true);
        System.out.println("追加后的字符串是 : " + sb);  // 追加后的字符串是 : abctrue

        // 在指定的位置插入数据
        sb.insert(0, 'A');
        System.out.println("插入字符以后的新字符串是 : " + sb); // 插入字符以后的新字符串是 : Aabctrue

        // KeyPoint 2 删

        // 删掉指定位置间字符
        sb.delete(0, 3); // [ )
        System.out.println("删除以后的字符串是 : " + sb); // Aabctrue，删除以后的字符串是 : ctrue

        // 删除指定位置上的字符
        sb.deleteCharAt(0);
        System.out.println("删除第一个位置上的字符以后的新字符是 : " + sb); // 删除第一个位置上的字符以后的新字符是 : true

        // KeyPoint 3 改

        // 替换掉指定位置间的字符，前两个参数不可以为负数，第一个参数必须小于等于第二个参数。
        sb.replace(sb.length() - 2, sb.length() - 1, "*"); // [ )
        System.out.println("替换以后的新字符是 : " + sb); // 替换以后的新字符是 : tr*e

        // KeyPoint 4 查

        // 返回字符在字符串中第一次出现的位置索引，如果不存在返回-1
        int index = sb.indexOf("a");
        System.out.println("a第一次出现的位置索引 " + index); // a第一次出现的位置索引 -1

        //  返回字符在字符串中最后一次出现的位置索引，如果不存在则返回-1
        int lastIndex = sb.lastIndexOf("a");
        System.out.println("a在字符串中最后一次出现的位置索引是 : " + lastIndex); // a在字符串中最后一次出现的位置索引是 : -1

        // KeyPoint 5 逆序

        // 字符串逆序
        System.out.println("逆序前的字符串是 : " + sb);
        System.out.println("逆序后的字符串是 : " + sb.reverse());
    }

    private static void test2() {
        StringBuilder sb = new StringBuilder("abc");

        // KeyPoint StringBuilder 不常用 API

        // 返回指定位置字符的unicode值
        int code1 = sb.codePointAt(0);
        System.out.println("code1 : " + code1);

        // 返回指定字符前一个字符的unicode值
        int code2 = sb.codePointBefore(1);
        System.out.println("code2 : " + code2);

        // 返回指定索引之间的字符的数量，数量为endIndex-beginIndex
        int code3 = sb.codePointCount(1, 3);
        System.out.println("code3 : " + code3);
    }
}
