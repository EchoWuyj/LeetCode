package alg_02_体系班_zcy.class27;

/**
 * @Author Wuyj
 * @DateTime 2023-02-25 17:02
 * @Version 1.0
 */
public class Code03_ZeroLeftOneStringNumber {

    /*
        KeyPoint 问题一
        给定一个数N,想象只有0和1两种字符,组成的所有长度为N的字符串
        如果某个字符串,任何0字符的左边都有1紧挨着(0的前一个必须是1,也不能隔一个是1,当然单纯全是0也不行)
        认为这个字符串达标,返回有多少达标的字符串?

        分析:
            N=1时,有0和1组成的01字符只有'0'或者'1'单个字符'0'不达标,只有'1'达标,1个
            N=2时,有0和1组成的01字符有四种'00','01','10','11',其中'10'和'11'达标2个
            N=3时,有0和1组成的01字符有八种,其中达标的为'101','110','111',3个
            N=4时,有'1010','1011','1101','1110','1111'这5个达标
            N=5时,...,8个达标

        1)观察法: 1 2 3 4 5   N
                 1 2 3 5 8 达标个数
                 观察所得:初始项为1,2的斐波那契数列 f(i) = f(i-1) + f(i-2)

        2)尝试法:定义int f(i)函数,该函数表示定义一个i长度的字符串(i个位置都没有填'0'或者'1'),
                 在i长度上自由填'0'或者'1',最终有多少个达标的(仅仅是达标情况)?
                 KeyPoint 隐含潜台词:该字符串最左边一定是'1',若是填'0',则'0'的前面已经没有字符了,
                      此时不符合达标情况
                 --------------------------------------------------------------------
                 比如n=8,由于最左侧已经规定了'1',则需要在剩下的7个位置中的第一个位置做判断
                   a)如果1位置是0,2位置必定不能为0,只能为1,那么满足f(6)潜台词,有6个格需要填,且最高位是1的情况下,有多少达标的
                   b)如果1位置是1,则满足f(7)潜台词,有7个格需要填,且最高位是1的情况下,有多少达标的
                 => 将填'0'和'1'两种情况加起来,则有 f(8) = f(7) + f(6) => f(i) = f(i-1) + f(i-2)
                 => 最终衍生出菲波那切数列的递推公式(二阶递推)

         KeyPoint 问题二        
         有一款2行,N列的区域,假设我们只有1*2大小的瓷砖,我们把该区域填满有多少种方案？

         思路:定义F(N)函数,返回2*N区域都没贴瓷砖的情况会有多少种方法数
              1)第一块瓷砖竖着摆,剩下的区域有F(N-1)种
              2)第一块瓷砖横着摆,第一块瓷砖下方区域只能横着摆,剩下的方法数自由摆放为F(N-2)

          F(N)=F(N-1)+F(N-2)斐波那契数列问题,二阶递推式
          适用递推的限制为:严格的,没有条件转移的递推

     */
    public static int getNum1(int n) {
        if (n < 1) {
            return 0;
        }
        return process(1, n);
    }

    public static int process(int i, int n) {
        if (i == n - 1) {
            return 2;
        }
        if (i == n) {
            return 1;
        }
        return process(i + 1, n) + process(i + 2, n);
    }

    public static int getNum2(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        int pre = 1;
        int cur = 1;
        int tmp = 0;
        for (int i = 2; i < n + 1; i++) {
            tmp = cur;
            cur += pre;
            pre = tmp;
        }
        return cur;
    }

    // 方法三
    public static int getNum3(int n) {
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

    public static int fi(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return 1;
        }
        int[][] base = {{1, 1},
                {1, 0}};
        int[][] res = matrixPower(base, n - 2);
        return res[0][0] + res[1][0];
    }

    public static int[][] matrixPower(int[][] m, int p) {
        int[][] res = new int[m.length][m[0].length];
        for (int i = 0; i < res.length; i++) {
            res[i][i] = 1;
        }
        int[][] tmp = m;
        for (; p != 0; p >>= 1) {
            if ((p & 1) != 0) {
                res = muliMatrix(res, tmp);
            }
            tmp = muliMatrix(tmp, tmp);
        }
        return res;
    }

    public static int[][] muliMatrix(int[][] m1, int[][] m2) {
        int[][] res = new int[m1.length][m2[0].length];
        for (int i = 0; i < m1.length; i++) {
            for (int j = 0; j < m2[0].length; j++) {
                for (int k = 0; k < m2.length; k++) {
                    res[i][j] += m1[i][k] * m2[k][j];
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        for (int i = 0; i != 20; i++) {
            System.out.println(getNum1(i));
            System.out.println(getNum2(i));
            System.out.println(getNum3(i));
            System.out.println("===================");
        }
    }
}
