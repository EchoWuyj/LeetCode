package alg_02_train_wyj._10_day_栈和队列;

/**
 * @Author Wuyj
 * @DateTime 2023-04-24 20:00
 * @Version 1.0
 */
public class _01_20_valid_parentheses {

    public static boolean isValid1(String s) {
        return true;
    }

    public static boolean isValid2(String s) {
        return true;
    }

    // test
    public static void main(String[] args) {
        String str1 = "(()))";
        String str2 = "(()";
        String str3 = "(())";
        System.out.println(isValid1(str1)); // false
        System.out.println(isValid1(str2)); // false
        System.out.println(isValid1(str3)); // true
        System.out.println("=========");
        System.out.println(isValid2(str1)); // false
        System.out.println(isValid2(str2)); // false
        System.out.println(isValid2(str3)); // true
    }
}
