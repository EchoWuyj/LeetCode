package alg_02_train_dm._00_day_准备_二刷;

import java.util.Scanner;

/**
 * @Author Wuyj
 * @DateTime 2023-08-09 22:44
 * @Version 1.0
 */
public class _03_Scanner_API {

    // KeyPoint Scanner_API 汇总
    // 1.hasNextXxx() 和 nextXxx()
    // 2.hasNext() 和 next()
    //  补充说明：next() 方法
    // 3.hasNextLine() 和 nextLine()
    // 4.nextInt() 和 nextLine()
    //  修复 bug 实现一
    //  修复 bug 实现二
    // 5.hasNext() 和 hasNextLine()

    // KeyPoint 1.hasNextXxx() 和 nextXxx()
    public static void test1() {

        // hasNextXxx() 判断待输入项，其中 Xxx 可以是 Int，Double 等
        // nextXxx()  获取下一个输入项，同上
        // => 两者搭配使用

        // 使用 nextInt() 读取整数时，它会尝试读取输入中的连续数字字符
        // 直到遇到非数字字符为止(如：空格、换行符、字母等)

        // 例如
        // 若输入为 "123 456"，调用 nextInt() 将返回整数 123，然后停在空格处。
        // 若再次调用 nextInt()，它将尝试读取 456，然后返回整数 456

        Scanner input = new Scanner(System.in);

        // 以 int 为例
        if (input.hasNextInt()) {
            int output1 = input.nextInt();
            System.out.println(output1);
            int output2 = input.nextInt();
            System.out.println(output2);
        } else {
            System.out.println("输入的类型错误");
        }

        // 测试用例
        // 输入
        // 4 56
        // 输出
        // 4
        // 56
    }

    // KeyPoint 2.hasNext() 和 next()
    public static void test2() {
        // next() 不光是在接收键盘输入的内容，而且还是在进行扫描分割，默认的分隔符是空格
        // 遇到 \r\n (回车) 作为结束标志，返回值类型 String
        Scanner input = new Scanner(System.in);
        while (input.hasNext()) {
            String str = input.next();
            System.out.println(str + 'A');
        }

        // 测试用例
        // 输入
        // abc def
        // 输出
        // abcA
        // defA

    }

    // KeyPoint 补充说明：next() 方法
    public static void test2_1() {
        // next() 方法在扫描时，跳过分隔符和回车字符
        // 并且只有在遇到下一个 非分隔符的字符时 才开始真正的扫描
        Scanner input = new Scanner(System.in);
        System.out.println("input id :");
        int id = input.nextInt();
        // 后读取键盘输入的字符串
        System.out.println("input name :");
        String name = input.next();
        System.out.println("id = " + id + " name =[" + name + "]");

        // input id :
        // 13
        // input name :
        // wuyongjian
        // id = 13 name =[wuyongjian]

        // input id :
        // 13 34
        // input name :
        // id = 13 name =[34]
    }

    // KeyPoint 3.hasNextLine() 和 nextLine()
    public static void test3() {
        // 总体功能：从标准输入读取多行文本，并将每行文本打印回标准输出
        Scanner input = new Scanner(System.in);
        // 在这个例子中 hasNextLine() 方法用于判断是否还有下一行可读
        // 其中 "一行" 的结束被定义为输入中的换行符 (\n) 或者 文件结尾
        // 当输入结束时，hasNextLine() 方法将返回 false，循环结束
        while (input.hasNextLine()) {
            // nextLine() 方法用于读取该行，并将其存储在 line 变量中，然后将其输出到控制台
            String line = input.nextLine();
            System.out.println(line);
        }

        // 测试用例
        // 输入
        // Hello
        // 输出
        // Hello

        // 注意：一轮循环之后，程序没有结束，控制台等待你再次输入

        // 输入
        // 输出
    }

    // KeyPoint 4.nextInt() 和 nextLine()
    public static void test4() {

        // nextInt()  获取一个 int 类型的值
        // nextLine()  获取一个 String 类型的值(没有分隔符，读到行尾)

        Scanner input = new Scanner(System.in);
        System.out.println("input id :");
        int id = input.nextInt();
        System.out.println("input name :");
        String name = input.nextLine();
        System.out.println("id = " + id + " name =[" + name + "]");

        // input id :
        // 10
        // input name :
        // id = 10 name =[]

        // KeyPoint 解释说明
        // 1.nextInt() 是键盘录入整数的方法
        //   录入 10 的时候，其实在键盘上录入的是 10 和 \r\n 回车(换行符) 用来确认输入
        //   nextInt() 也是通过 \r\n 作为结束标志，但是 nextInt() 方法只获取10

        // 2.nextLine() 是键盘录入字符串的方法，可以接收任意类型，通过 \r\n 获取一行
        //    nextLine()只要遇到 \r\n 就证明一行结束，其实并没有接受到字符串

        // KeyPoint 解决方案
        // 1.键盘录入的都是字符串，都用 nextLine 方法，有需要将整数字符串转换成整数
        // 2.同时 nextInt() 在录入不为整数时，存在异常的情况，而 nextLine() 是不存在这样的问题的

        // 总结
        // 1.尽量不要在 nextInt() 方法后，紧跟着使用 nextLine()
        // 2.尽量把 nextLine() 类型放前面，nextInt() 放后面，实在不行
        //   nextInt() 后面要跟一个 nextLine 方法 "消化" 掉那个多余的字符串

    }

    // KeyPoint 修复 bug 实现一
    public static void test4_1() {
        Scanner input = new Scanner(System.in);
        System.out.println("input id :");
        String strId = input.nextLine();
        int id = Integer.parseInt(strId);
        System.out.println("input name :");
        String name = input.nextLine();
        System.out.println("id = " + id + " name =[" + name + "]");

        // input id :
        // 10
        // input name :
        // wuyongjian
        // id = 10 name =[wuyongjian]

    }

    // KeyPoint 修复 bug 实现二
    public static void test4_2() {
        Scanner input = new Scanner(System.in);
        System.out.println("input id :");
        int id = input.nextInt();
        // 消化多余 \r\n
        input.nextLine();
        System.out.println("input name :");
        String name = input.nextLine();
        System.out.println("id = " + id + " name =[" + name + "]");

        // input id :
        // 10
        // input name :
        // wuyongjian
        // id = 10 name =[wuyongjian]
    }

    // KeyPoint 5.hasNext() 和 hasNextLine()
    public static void test5() {
        // hasNext() 方法会判断接下来是否有非空字符
        //           如果有则返回 true，否则返回 false

        // hasNextLine() 方法会根据行匹配模式，判断接下来是否有一行(包括空行)
        //               如果有则返回 true，否则返回 false

        Scanner input = new Scanner(System.in);

        // 当使用 hasNextLine 方法实现判断是否还有数据时 => 会报错
        while (input.hasNextLine()) {
            //...
        }

        // 当将 hasNextLine 修改为 hasNext() 方法时 => 程序恢复正常
        while (input.hasNext()) {
            //...
        }

        // 测试用例：7 15 9 5
        // 在 linux 系统中文件的结尾会有一个换行符 \n，也就是说从System.in输入流中真正读取到的数据流是这样的
        // 实际 check 测试用例：7 15 9 5\n

        // 程序在处理完 5 之后，输入流中就只剩下一个换行符 \n 了，在处理完 5 之后，while再去进行循环判断
        // 此时 hasNext() 方法和 hasNextLine() 方法去判断得到的结果就产生了差异

        // hasNext() 方法会认为之后再没有非空字符，会返回一个 false
        // hasNextLine() 方法会认为换行符 \n 是一个空行，符合行的匹配模式则会返回一个 true
        //               但实际上由于之后再没有数据了，所以会在读取输入流的时候发生异常，从而导致整个运行报错

        // 代码建议
        // 1.采用 hasNextXxxx() 的话，后面也要用 nextXxxx():
        // 2.比如前面用 hasNextLine()，那么后面要用 nextLine() 来处理输入;
    }

    public static void main(String[] args) {
        test1();

        test2();
        test2_1();

        test3();

        test4();
        test4_1();
        test4_2();
    }
}
