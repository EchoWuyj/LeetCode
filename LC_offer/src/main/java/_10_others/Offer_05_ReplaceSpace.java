package _10_others;

/**
 * @Author Wuyj
 * @DateTime 2022-08-21 13:01
 * @Version 1.0
 */
public class Offer_05_ReplaceSpace {
    public static void main(String[] args) {
        String str = "We are happy.";
        System.out.println(replaceSpace(str));
    }

    public static String replaceSpace(String input) {
        StringBuilder res = new StringBuilder();
        for (char c : input.toCharArray()) {
            // equals() between objects of inconvertible types 'String' and 'char'
            // equals 是 " " 字符串特有的方法，但是这里确实字符，所以不能使用 equals
            if (' ' == c) {
                res.append("%20");
            } else {
                res.append(c);
            }
        }
        return res.toString();
    }

    // return s.replace(" ","%20"); hhhhh 笑死
}
