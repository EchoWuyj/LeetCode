package alg_02_体系班_zcy.class19_DP_Done;

public class Code02_ConvertToLetterString {

	/*

        规定1和A对应,2和B对应,3和C对应...26和Z对应
        那么一个数字字符串比如"111"就可以转化为:"AAA","KA"和"AK"
        给定一个只有数字字符组成的字符串str,返回有多少种转化结果

        KeyPoint 模型:从左往右,每个位置进行尝试(暴力尝试)
                 要么单个数字转化,要么两个数字转化(不一定转化成功,需要条件限制)
	 */

    // KeyPoint 方法一:暴力递归
    // str只含有数字字符0~9,返回多少种转化方案
    public static int number(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        return process(str.toCharArray(), 0);
    }

    // str[0..index-1]转化无需过问
    // str[index..]去转化,返回的是有多少种转化方法
    public static int process(char[] str, int index) {
        // index越界,找到一种转化方法
        if (index == str.length) {
            return 1;
        }

        // KeyPoint i没到最后,说明有字符

        // 边界条件 => str是包括0的,但是转化  范围:1->A,2->B,...,26->Z,其中没有'0'
        // 因此遇到'0',则说明之前的决定有问题,直接返回0,表示没有找到一种转化方法
        if (str[index] == '0') {
            return 0;
        }

        // str[index]!='0',转换存在2种情况
        // 1)情况一:index位置单转后(index已经使用),index+1位置做决定一共有多少种有效方案
        int ways = process(str, index + 1);

        // 2)情况二:index和index+1一起转(两者共同对应一个字符)
        // 但是这种情况不是一定成立,有条件限制:
        //    a)index+1不越界
        //    b)(index,index+1)对应的字符不超过26
        // str[index]-'0',即字符转数值,同时运算过程注意加括号
        if (index + 1 < str.length && (str[index] - '0') * 10 + str[index + 1] - '0' < 27) {
            // index和index+1已转,从index+2位置判断
            // 将两种情况相加,得到index位置的转化方法
            ways += process(str, index + 2);
        }
        return ways;
    }

    // KeyPoint 方法二:dp
    public static int dp(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] str = s.toCharArray();
        int N = str.length;
        int[] dp = new int[N + 1];
        dp[N] = 1;

        // 任何一个i依赖后面位置,因此是从右往左填,填到0位置停止
        // KeyPoint dp[N]位置已经确定好了,直接从N-1开始
        for (int i = N - 1; i >= 0; i--) {
            // 递归代码中 if (str[index] == '0') return 0,因为数组初始化过程已经设置好0
            // 所以for循环直接跳过,判断下个位置,因此后面代码逻辑是在if (str[i] != '0') 条件下执行的
            // KeyPoint 递归转dp,不能将递归中有的的if判断省略(搞丢了)
            if (str[i] != '0') {
                // i=N-1,i+1=N,且N位置已知
                int ways = dp[i + 1];
                if (i + 1 < str.length && (str[i] - '0') * 10 + str[i + 1] - '0' < 27) {
                    ways += dp[i + 2];
                }
                // 最后需要将ways赋值
                dp[i] = ways;
            }
        }
        return dp[0];
    }

    public static void main(String[] args) {
        System.out.println(number("7210231231232031203123"));
        System.out.println(dp("7210231231232031203123"));
    }
}
