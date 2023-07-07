package alg_02_train_dm._09_day_哈希查找;

/**
 * @Author Wuyj
 * @DateTime 2023-04-15 20:33
 * @Version 1.0
 */
public class _07_Note_Equals {

    /*

     Q：== 和 equals 的区别是什么？

     一、对象类型不同
        1、equals()：是超类 Object 中的方法。
        2、==：是操作符。

     二、比较的对象不同
        1、equals()：用来检测 两个对象 是否相等，即两个对象的内容是否相等。

           如果你有两个字符串对象，你想比较它们的内容是否相等，你应该使用字符串对象的 "equals" 方法，
           而不是使用 "==" 运算符。这是因为 "==" 运算符只能比较两个对象的引用地址是否相同，而不是比较
           它们的内容是否相等。

           需要注意的是：如果你想在自己的类中使用 "equals" 方法进行对象比较，你需要重写 "equals" 方法
           并按照你的需求实现比较逻辑。否则，默认情况下，"equals"方法会使用"=="运算符进行比较，
           即比较两个对象的引用地址是否相等。

        2、==：用于比较引用和比较基本数据类型时具有不同的功能，具体如下：
        （1）、基础数据类型：比较的是他们的值是否相等，比如两个 int 类型的变量，比较的是变量的值是否一样。
        （2）、引用数据类型：比较的是引用的地址是否相同，比如说新建了两个 User 对象，比较的是两个 User 的地址是否一样。

     */
}
