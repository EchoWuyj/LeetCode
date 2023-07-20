package alg_02_train_dm._02_day_一维数组_二刷;

/**
 * @Author Wuyj
 * @DateTime 2023-04-17 14:24
 * @Version 1.0
 */
public class _02_Note_while_do_while {

    public static void test() {
        // 使用 do-while 循环实现数字累加器
        // => 环形替换使用比较多，其他情况使用很少
        int sum = 0;
        int i = 1;
        do {
            sum += i;
            i++;
        } while (i <= 10);
        System.out.println("sum = " + sum); // sum = 55
    }

    public static void test1() {

        // 使用 while 循环实现相同的数字累加器
        int sum = 0;
        int i = 1;
        while (i <= 10) {
            sum += i;
            i++;
        }
        System.out.println("sum = " + sum); // sum = 55
    }

    /*
        两者区别
        1 do-while 循环至少会执行一次循环体，即使循环条件不成立
          => 用于需要至少执行一次循环体的情况
        2 while 循环，在循环条件不成立时将跳过整个循环体
          => 用于需要循环的次数不确定的情况
     */

    public static void main(String[] args) {
        test();
        test1();
    }
}
