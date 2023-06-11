package alg_02_体系班_zcy.class18_DP_Done;

public class Code02_CardsInLine {

	/*

		给定一个整型数组arr,代表数值不同的纸牌排成一条线,玩家A和玩家B依次拿走每张纸牌
		规定玩家A先拿,玩家B后拿,但是每个玩家每次只能拿走最左或最右的纸牌(一张纸牌)
		玩家A和玩家B都绝顶聪明(都尽可能想要让自己赢),请返回最后获胜者的分数

		[50,100,20,10]
		 0   1  2  3
		先手:10,100
		后手:50,20

		KeyPoint 使用自然智慧进行尝试是最靠谱的,学会尝试是解决动态规划最本质的能力
	 */

    // KeyPoint 方法一(暴力递归)
    // 根据规则,返回获胜者的分数
    public static int win1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        // 同一数组arr[L...R]的先手和后手
        int first = f1(arr, 0, arr.length - 1);
        int second = g1(arr, 0, arr.length - 1);

        // 先手不一定最大,需要比较才能知道最大
        // [10,100,10] => 先手必输
        return Math.max(first, second);
    }

    // arr[L..R],先手获得的最好分数返回
    public static int f1(int[] arr, int L, int R) {
        // 只有一张纸牌,只能拿走
        if (L == R) {
            return arr[L];
        }

        // 不止一张纸牌 => 要么拿左,要么拿右

        // 拿左=>先手获得arr[L]+后手能获得最好分数
        int p1 = arr[L] + g1(arr, L + 1, R);
        // 拿右=>先手获得arr[R]+后手能获得最好分数
        int p2 = arr[R] + g1(arr, L, R - 1);
        // 因为先手,可以选择最大值
        return Math.max(p1, p2);
    }

    // arr[L..R],后手获得的最好分数返回
    public static int g1(int[] arr, int L, int R) {
        // 只有一张牌,且是后手,对方将纸牌拿走,你只能获得0
        if (L == R) {
            return 0;
        }
        // 不止一张牌
        // 对手拿走了L位置的数,则此时在[L+1,R]上是先手
        int p1 = f1(arr, L + 1, R);
        // 对手拿走了R位置的数,则此时在[L,R-1]上是先手
        int p2 = f1(arr, L, R - 1);

        // 但是该过程零和博弈,对手在做决定,你是无法选择的,只能接受对手决定
        // 对手想要获取最好分数,则是将最小的最优给你,所以后手只能被迫做决定
        return Math.min(p1, p2);
    }

    // KeyPoint 方法二(缓存)
    /*
        分析位置依赖不要使用抽象化,使用具体案例去绕(数组[0,7])
        依赖关系不看固定值部分(arr[L],arr[R]),只看坐标变化的部分

                        f(0,7)
                        ↙    ↘
                   g(1,7)      g(0,6)
                   ↙  ↘       ↙  ↘
               f(1,6) f(2,7) f(0,5) f(1,6)

         在重复过程f(1,6),使用到动态规划有利可图
     */
    public static int win2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int N = arr.length;

        // 分析可变参数的变化范围
        // KeyPoint L和R是数组左右指针,两者只能往数组中间移动,所以其范围必然是在1~(N-1)
        //      和之前题目index达到边界(arr.length)不太一样
        int[][] fmap = new int[N][N];
        int[][] gmap = new int[N][N];

        // 表示计算过与否
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                fmap[i][j] = -1;
                gmap[i][j] = -1;
            }
        }

        int first = f2(arr, 0, arr.length - 1, fmap, gmap);
        int second = g2(arr, 0, arr.length - 1, fmap, gmap);
        return Math.max(first, second);
    }

    // arr[L..R],先手获得的最好分数返回
    public static int f2(int[] arr, int L, int R, int[][] fmap, int[][] gmap) {
        // 判断是否计算过
        if (fmap[L][R] != -1) {
            return fmap[L][R];
        }
        int ans = 0;
        // KeyPoint 修改多个if => if else,因为递归版本,因为存在return,所以是隐含二选一的关系
        //      若这里不修改成if else,则ans = arr[L]的值,都会被后面的ans=xxx覆盖
        if (L == R) {
            ans = arr[L];
        } else {
            // KeyPoint 递归->缓存过程中,只是将赋值操作替换成dp[][],递归调用过程没有发生变化
            int p1 = arr[L] + g2(arr, L + 1, R, fmap, gmap);
            int p2 = arr[R] + g2(arr, L, R - 1, fmap, gmap);
            ans = Math.max(p1, p2);
        }
        fmap[L][R] = ans;
        return ans;
    }

    // arr[L..R],后手获得的最好分数返回
    public static int g2(int[] arr, int L, int R, int[][] fmap, int[][] gmap) {
        if (gmap[L][R] != -1) {
            return gmap[L][R];
        }
        int ans = 0;
        if (L != R) {
            int p1 = f2(arr, L + 1, R, fmap, gmap); // 对手拿走了L位置的数
            int p2 = f2(arr, L, R - 1, fmap, gmap); // 对手拿走了R位置的数
            ans = Math.min(p1, p2);
        }
        gmap[L][R] = ans;
        return ans;
    }

    // KeyPoint 方法三(严格表依赖DP)
    /*
        1)假设具体案例:[7 4 16 15 1]
                      0 1  2  3 4
        2)画出fmap和gmap的dp表 => 4*4的矩阵
            确定行和列含义
        3)看边界条件,将能填写的位置先填好,fmap和gmap分别填
          初始位置:a)fmap对角线arr[L];
                  b)gmap对角线全0
          目标位置:主函数需要dp表中那个位置,标记为★
                  坐标行和列不要搞错了
        4)因为本题中:L是一个范围的左,R是一个范围的右 [L,R]
                    所以dp表中L>R是无效区域直接打'×'
        5)分析一般位置的依赖关系()
            a)在fmap表中,假设坐标位置为(1,4)进行依赖分析
              根据递归函数,fmap(1,4)依赖gmap(2,4)和(1,3)
            b)在gmap表中,假设坐标位置为(1,4)进行依赖分析
              根据递归函数,fmap(1,4)依赖fmap(2,4)和(1,3)
        KeyPoint 分析依赖关系可以省略固定值,比如:arr[l] + g1(arr, l + 1, r)中的arr[l]
             但在代码中写dp[][]依赖关系时不能将其省略
        6) 根据已有位置=>未知位置原则
            a)由fmap对角线=>gmap,依次反复
            b)由gmap对角线=>gmap,依次反复

     */
    public static int win3(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int N = arr.length;
        int[][] fmap = new int[N][N];
        int[][] gmap = new int[N][N];

        // 设置fmap对角线的值(因为gmap对象线的值都是0,直接省略了)
        for (int i = 0; i < N; i++) {
            // KeyPoint 这里直接使用单层for循环即可,因为行和列有相对固定的位置关系
            //      双层for循环i和j,是在i行确定时,依次遍历i行的每一列,这样才要双for循环
            fmap[i][i] = arr[i];
        }

        // 整体范围:列从1一直到N-1位置
        // 具体一列:1列
        // 再去分析1列需要进行操作,使用代码描述出来
        for (int startCol = 1; startCol < N; startCol++) {
            // 定义最开始位置, 因为涉及dp数组坐标,不要定义太复杂
            int L = 0;
            int R = startCol;
            // 列比行先越界,所以保证列不越界
            while (R < N) {
                fmap[L][R] = Math.max(arr[L] + gmap[L + 1][R], arr[R] + gmap[L][R - 1]);
                gmap[L][R] = Math.min(fmap[L + 1][R], fmap[L][R - 1]);
                // 顺着右对角线向下移,即行++,列++
                // 这里不需要使用两层for,因为for循环里行是和列同时变化,就能实现对角线的遍历
                L++;
                R++;
            }
        }
        // 最后return返回值看主函数要什么
        return Math.max(fmap[0][N - 1], gmap[0][N - 1]);
    }

    public static void main(String[] args) {
        int[] arr = {5, 7, 4, 5, 8, 1, 6, 0, 3, 4, 6, 1, 7};
        System.out.println(win1(arr));
        System.out.println(win2(arr));
        System.out.println(win3(arr));
    }
}
