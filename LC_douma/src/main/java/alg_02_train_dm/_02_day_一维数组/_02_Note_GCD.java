package alg_02_train_dm._02_day_一维数组;

/**
 * @Author Wuyj
 * @DateTime 2023-04-17 14:47
 * @Version 1.0
 */
public class _02_Note_GCD {

    /*
         最大公约数，英文简写 GCD，全称为 "Greatest Common Divisor" '
         最大公约数：指两个或多个整数中 最大的 能够同时 整除 它们的正整数
         最大公约数也可以称为 "最大公因数"、"最大公因子"

         例如，求出数字 12 和数字 18 的最大公约数。

         首先，我们可以列出数字 12 和数字 18 的所有因数：
         数字 12 的因数为 1、2、3、4、6 和 12。 => 注意：重复情况不需要考虑，如 4、3
         数字 18 的因数为 1、2、3、6、9 和 18。

         接下来，我们找出这两组因数中共有的因数，即它们的公因数。
         根据上面列出的因数，数字 12 和数字 18 的公因数为 1、2、3 和 6。

         最后，从公因数中找出最大的数，即为数字 12 和数字 18 的最大公约数。
         在这个例子中，最大公约数为 6。因此，数字 12 和数字 18 的最大公约数为 6。

     */


    /*
         因数：指能够整除一个数，且大于等于 1 小于等于这个数本身的数

         例如，数字 12 的因数有 1、2、3、4、6 和 12，
         因为这些数能够整除 12，且它们都是大于等于1小于等于12的正整数

         整除
         如果一个数 a 能够整除另一个数 b，我们可以说 "a 是 b 的因数" 或者 "b可以被a整除"
         4 能够整除 12，因为 12 可以被 4 整除，即 12 = 4 * 3。在这种情况下，4 是 12 的因数，而 3 是商
     */

    /*

         最小公倍数
         最小公倍数：是能够同时被两个或多个数整除的最小正整数

         求两个数的最小公倍数的方法，一种常见的方法是先分解质因数，然后找出两个数分解质因数后各个质因子的最高次数，
         再将这些质因子和对应的最高次数相乘，即可得到两个数的最小公倍数。

         例如，要求数字 12 和数字 18 的最小公倍数，可以先将它们分解质因数：

         数字 12 = 2 × 2 × 3
         数字 18 = 2 × 3 × 3

         然后，找出它们分解质因数后，各个质因子的最高次数：
         数字 12 中因子 2 的最高次数为 2，因子 3 的最高次数为 1
         数字 18 中因子 2 的最高次数为 1，因子 3 的最高次数为 2

         最后，将这些质因子和对应的最高次数相乘，即可得到最小公倍数：
         最小公倍数 = 2 × 2 × 3 × 3 = 36
         因此，数字 12 和数字 18 的最小公倍数为 36。
     */
}
