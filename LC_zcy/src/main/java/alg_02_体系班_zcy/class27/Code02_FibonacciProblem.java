package alg_02_体系班_zcy.class27;

/**
 * @Author Wuyj
 * @DateTime 2023-02-25 13:54
 * @Version 1.0
 */
public class Code02_FibonacciProblem {

    /*
        斐波那契数列
        该序列的前几项是这样的:0,1,1,2,3,5,8,13,21,34,⋯
        在数学上斐波纳契数列以如下被以递归的方法定义：
            F(0)=0
            F(1)=1
            F(2)=1
            F(3)=2
         F(n)=F(n−1)+F(n−2),(n≥2,n∈N)

        KeyPoint 求斐波那契数列矩阵乘法的方法(矩阵加快速幂方法)
        1)斐波那契数列线性求解O(N) => for循环实现
        2)除了初始项F(0)=0,F(1)=1,剩下项F(n)=F(n−1)+F(n−2)是严格递推式(没有条件转移) => O(logN)解法
          利用线性代数,也可以改写出另一种表示|F(N),F(N-1)|=|F(2),F(1)|*某个二阶矩阵的(N-2)次方
        3)求出这个二阶矩阵,进而最快求出这个二阶矩阵的N-2次方

          F(n)=F(n−1)+F(n−2) n减的最多是2,故是2阶递推

          通过线性代数解决严格递推第n项

          |F3,F2|=|F2,F1|*|a,b|   (1)
                          |c,d|

          |F4,F3|=|F3,F2|*|a,b|   (2)
                          |c,d|

          0,1,1,2,3,5,8,13,21,34   F(n)数值
          0 1 2 3 4 5 6 7  8  9      n 项数

          (1)式子代入数值
          KeyPoint 注意:通过初始项求解a,b,c,d时,不能使用F(0),否则求出来的结果不正确
          |2,1|=|1,1|*|a,b|  => a+c=2
                      |c,d|     b+d=1

          (2)式子代入数值
          |3,2|=|2,1|*|a,b|  => 2a+c=3
                      |c,d|     2b+d=2

          => a=1,b=1,c=1,d=0

          由递推关系:
          |Fn,Fn-1|=|Fn-1,Fn-2|*|a,b|
                                |c,d|
                   =|Fn-2,Fn-3|*|a,b|^2
                                |c,d|
                   =......
                   =|F2,F1|*|a,b|^n-2
                            |c,d|

          求解Fn关键点:求某个矩阵的某个次方,只要矩阵某个次方求足够快,则Fn计算的就会足够快
          
          ---------------------------------------------------

          我们先思考怎么求一个数乘方怎么算比较快的问题

          快速幂转化思路:
          怎么快速求出10^75值是多少?
          利用一种精致的二分来求,75次方我们拆分成二进制形式,75=1001011

          定义temp= 10^1(temp不断自乘,得10^2,10^4,10^8,10^16,10^32)
          KeyPoint temp是依据求解次方的底数来选择的,因为求解的是10^75,所以temp=10
          定义ans=1,用于接受结果

          temp依次和75的二进制做比较,从低位到高位进行比较(按位决定是否乘入ans中)
            1)如果指数二进制某一位为1,ans=1*temp(10^1),temp自乘
            2)如果为0,temp不乘到res中,temp自乘,
            3)即temp每次判断结束(不管是否乘进res中)都是需要自乘变成新tmp
            4)最终result就是我们的结果

          75=1001011
          10^{75} = 10^{64} * 10^8 * 10^2 * 10^1 (注意指数部分相加)
          => 10^N次方,temp最多自乘log2(N)次,故可以log2(N)来了求出10^N

          ---------------------------------------------------

          矩阵次方的乘法,可以类似处理
          [a]^75=1001011,把指数变为二进制
          result初始值为单位矩阵(对角线全为1的矩阵)
           单位矩阵  | 1 0 |
                    | 0 1 |
          temp  |a|^1,|a|^2,|a|^4...
          其他处理和数字次方的处理类似

          KeyPoint 递推推广式
          F(n)=a*F(n-1)+b*F(n-2)+...+z*F(n-k),k表示最底层减到多少,我们称之为k阶递归式
          a,b,z为常数系数,k为常数,那么都有O(logN)的解,系数只会影响到我们的k阶矩阵的不同
          需要给全初始项,或者初始项不够自己计算而得

          1)3阶
            F(N)=7*F(n-1)-3*2F(n-2)+4*F(n-3) 3阶 => 对应3项Fn,F(n-1),F(n-2)
            |Fn,F(n-1),F(n-2)| = |F3,F2,F1|*|3*3|^(n-3)
            通过初始项将|3*3|的矩阵求出来

          2)i阶
            |Fn,F(n-1),...,F(n-i+1)|=|Fi,F(i-1),...,F1|*|i*i|^(n-i)

          KeyPoint 递推公式中F(n-1)与F(n-5)不连续的也是可以的
               F(n)=6F(n-1)+3F(n-5),5阶递归式,计算方式同上
     */

    // KeyPoint 斐波那契数列
    // KeyPoint 方法一:递归
    //      时间复杂度分析:总的调用次数就是递归树的所有节点个数
    //                    2^0+2^1+2^2+...+2^(N-2)=2^(N-1)-1 => O(2^N)
    public static int f1(int n) {
        // 判空条件
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return 1;
        }
        return f1(n - 1) + f1(n - 2);
    }

    // KeyPoint 方法二 for循环实现(一),时间复杂度O(N),这种做法比较好理解
    public static int fibo(int n) {
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            //  for循环的判断需要从0开始
            //  若是不将下标0的情况包含在内,则nums[i-1]和nums[i-2]越界
            if (i == 0 || i == 1)
                nums[i] = 1; // 第一和第二位填入1
            else
                // KeyPoint 注意索引下标于F(n)的关系
                nums[i] = nums[i - 1] + nums[i - 2]; // 除第一第二位外其余数据等于前两位之和
        }
        //
        return nums[n - 1];
    }

    // KeyPoint 方法二 for循环实现(二)
    public static int f2(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return 1;
        }
        // F(3)的前一项F(2)
        int res = 1;
        // F(2)的前一项F(1)
        int pre = 1;
        // 中间缓存
        int tmp = 0;
        // for循环是从F(3)开始计算
        for (int i = 3; i <= n; i++) {
            // 先将F(2)缓存
            tmp = res;
            // F(3)=F(2)+F(1)
            res = res + pre;
            // pre变成新的前一项F(2)
            pre = tmp;
        }
        return res;
    }

    // KeyPoint 方法3 矩阵乘法 O(logN)
    public static int f3(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return 1;
        }
        // 只要是斐波那契数列就是该矩阵
        // [ 1 ,1 ]
        // [ 1, 0 ]
        int[][] base = {
                {1, 1},
                {1, 0}
        };
        // 求该矩阵的N-2次方 |x y|
        //                  |z t|
        // |Fn,Fn-1| = |F2,F1| * |x y| = |1,1| * |x y| => Fn = x + y
        //                       |z t|           |z t|
        int[][] res = matrixPower(base, n - 2);
        // KeyPoint 不同的初值,导致最后表达式的系数也是不同的
        //      主要是由|F2,F1|来确定的
        return res[0][0] + res[1][0];
    }

    // 一个矩阵的p次方求解
    public static int[][] matrixPower(int[][] m, int p) {
        // 同阶矩阵相乘,其阶数保持不变
        // res按照同阶矩阵相乘后结果矩阵来定义
        int[][] res = new int[m.length][m[0].length];
        // res先成为矩阵中'1',就是单位矩阵
        for (int i = 0; i < res.length; i++) {
            res[i][i] = 1;
        }
        // 传入矩阵m用来定义temp矩阵(底数),temp矩阵1次方
        int[][] t = m;

        // p是次方值,按照位运算来分析p的二进制形式
        // 每次判断,右移一位
        for (; p != 0; p >>= 1) {
            // p的二进制低位为1,则需要temp,乘到res中
            if ((p & 1) != 0) {
                res = multiMatrix(res, t);
            }
            // temp自乘,temp阶数为1,2,4,8次方变化
            t = multiMatrix(t, t);
        }
        return res;
    }

    // 两个矩阵乘完之后的结果返回
    // KeyPoint 一般意义上的两个矩阵相乘(不限定得是两个方阵)
    //      但得保证A的列和B的行相同即Amn*Bnp,否则不能相乘
    public static int[][] multiMatrix(int[][] m1, int[][] m2) {
        // 定义mp的矩阵
        int[][] res = new int[m1.length][m2[0].length];
        // m1的行(m)
        for (int i = 0; i < m1.length; i++) {
            // m2的列(p)
            for (int j = 0; j < m2[0].length; j++) {
                // m1和m2的内侧下标(n)
                for (int k = 0; k < m2.length; k++) {
                    // 内侧下标相同
                    // 外侧表示i行和j列
                    // KeyPoint 注意这里是+=,不是单纯的=
                    res[i][j] += m1[i][k] * m2[k][j];
                }
            }
        }
        return res;
    }

    // KeyPoint 爬楼梯问题
    // KeyPoint 方法一 暴力解(递归)
    public static int s1(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return n;
        }
        return s1(n - 1) + s1(n - 2);
    }

    // KeyPoint 方法二 递推
    public static int s2(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return n;
        }
        int res = 2;
        int pre = 1;
        int tmp = 0;
        for (int i = 3; i <= n; i++) {
            tmp = res;
            res = res + pre;
            pre = tmp;
        }
        return res;
    }

    // KeyPoint 方法三 O(logN)
    public static int s3(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return n;
        }
        int[][] base = {{1, 1}, {1, 0}};

        int[][] res = matrixPower(base, n - 2);
        return 2 * res[0][0] + res[1][0];
    }

    /*
        奶牛问题:
        一个农场第一年有一只奶牛A,每一只奶牛每年会产一只小奶牛
        假设所有牛都不会死,且小牛需要3岁才能产小奶牛,求N年后牛的数量

       年数  1      2      3     4      5        6
       奶牛  A(0)  A->B  A->C  A->D   A->E     A->G
                        B(1)  B(2)   B(3)->F   B->H
                              C(1)   C(2)      C(3)->I
                                     D(1)      D(2)
                                               E(1)
                                               F(1)
       数量  1      2     3      4     6        9

       KeyPoint 规范:
            1)A(0)比较分年
            2)新的一年都将前一年的所有的奶牛依次写下,再去分析是否能产一只新的小奶牛

       思路:牛的数量的轨迹为:1,2,3,4,6,9… => 找前后项之间的规律
            F(N)=F(N-1)+F(N-3)
            KeyPoint 分析几年奶牛数量和前几年有关
            => 今年的牛F(N)等于去年的牛F(N-1)+三年前牛的数量F(N-3)
               因为三年中的牛还没有成熟不能生小牛,三阶问题k=3。

      |F4,F3,F3|=|F3,F2,F1| * |a b c|
                              |d e f|
                              |g h i|
      =>
      |4,3,2|=|3,2,1| * |a b c|
                        |d e f|
                        |g h i|

       3a+2d+1g=4
       3b+2e+1h=3
       3c+2f+1i=2

       等式不够再换一种,|F5,F4,F3|=|F4,F3,F3|*|a b c|
                                             |d e f|
                                             |g h i|
       => 4a+3d+2g=6
          4b+3e+2h=4
          4c+3f+2i=3

       => 6a+4d+3g=9
          6b+4e+3h=6
          6c+4f+3i=4

       => 求解

       KeyPoint 算法模型(目前已经讲过的模型)
       1)二叉树递归套路
       2)从左往右尝试模型(DP)
       3)范围上尝试模型(DP)
       4)样本对应模型(DP)
       5)业务限制模型(DP)
       6)斐波那契数列O(logN)

     */
    // KeyPoint 奶牛问题
    // KeyPoint 方法一 暴力解(递归)
    public static int c1(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n == 2 || n == 3) {
            return n;
        }
        return c1(n - 1) + c1(n - 3);
    }

    // KeyPoint 方法二 迭达
    public static int c2(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n == 2 || n == 3) {
            return n;
        }
        // F(N)=F(N-1)+F(N-3)
        // F(4)的前一项F(3)
        int res = 3;
        // F(3)的前一项F(2)
        int pre = 2;
        // F(2)的前一项F(1)
        int prepre = 1;

        int tmp1 = 0;
        int tmp2 = 0;
        // F(4)开始
        for (int i = 4; i <= n; i++) {
            //  F(N)=F(N-1)+F(N-3) => F(4)=F(3)+F(1)
            //  F(1)=1  F(2)=2  F(3)=3  F(4)=4  F(5)=6  F(6)=9
            //           temp2  temp1
            //  prepre   pre    res     待计算
            // F(5)=F(4)+F(2)
            // F(4)=res, F(2)=temp2

            tmp1 = res;
            tmp2 = pre;
            res = res + prepre;
            pre = tmp1;
            prepre = tmp2;
        }
        return res;
    }

    // KeyPoint 方法三 O(logN)
    public static int c3(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n == 2 || n == 3) {
            return n;
        }
        int[][] base = {
                {1, 1, 0},
                {0, 0, 1},
                {1, 0, 0}};
        int[][] res = matrixPower(base, n - 3);
        // |F3,F2,F1|为系数
        return 3 * res[0][0] + 2 * res[1][0] + res[2][0];
    }

    // 主函数
    public static void main(String[] args) {
        int n = 10;
        System.out.println(f1(n));
        System.out.println(f2(n));
        System.out.println(f3(n));
        System.out.println("===");

        System.out.println(s1(n));
        System.out.println(s2(n));
        System.out.println(s3(n));
        System.out.println("===");

        System.out.println(c1(n));
        System.out.println(c2(n));
        System.out.println(c3(n));
        System.out.println("===");
    }
}
