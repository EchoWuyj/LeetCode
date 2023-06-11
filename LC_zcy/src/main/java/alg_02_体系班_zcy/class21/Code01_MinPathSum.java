package alg_02_体系班_zcy.class21;

/**
 * @Author Wuyj
 * @DateTime 2023-03-03 15:43
 * @Version 1.0
 */

// https://leetcode.cn/problems/minimum-path-sum

public class Code01_MinPathSum {
    /*
        最短路径和问题
        给定一个二维数组matrix,一个人必须从左上角出发,最后到达右下角,沿途只可以向下或者向右走(涉及选择,dp)
        沿途的数字都累加就是距离累加和,返回最小距离累加和

        1	3	1
        1	5	1
        4	2	1

        路径 1→3→1→1→1 是所有路径中路径和最小的,所以返回7

        KeyPoint 数组压缩技巧
     */

    // KeyPoint 补充:递归版本 => 好好理解
    public static int minPathSum(int[][] m) {
        if (m == null || m.length == 0 || m[0] == null || m[0].length == 0) {
            return 0;
        }
        return process(m, 0, 0);
    }

    // 函数:(x,y)到达最后位置时的最小路径和
    public static int process(int[][] matrix, int x, int y) {
        int n = matrix.length;
        int m = matrix[0].length;

        // 递归边界
        if (x == n - 1 && y == m - 1) {
            // 当前位置已经在右下角了,但是该点还是要算入路径中
            return matrix[x][y];
        }

        // KeyPoint 在下边界和右边界时,限制(x,y)走向,自然不用考虑越界情况
        // 当前位置处在最后一行时,只能向右走
        if (x == n - 1) {
            return matrix[x][y] + process(matrix, x, y + 1);
        }

        // 当前位置处在最后一列时,只能向下走
        if (y == m - 1) {
            return matrix[x][y] + process(matrix, x + 1, y);
        }

        // 普通位置:取左边位置和右边位置到右下角位置距离最小的那个
        int right = matrix[x][y] + process(matrix, x, y + 1);
        int down = matrix[x][y] + process(matrix, x + 1, y);

        return Math.min(right, down);
    }

    // KeyPoint 补充 dp(自己修改)
    public static int dp(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            return 0;
        }

        int n = matrix.length;
        int m = matrix[0].length;
        int[][] dp = new int[n][m];
        dp[n - 1][m - 1] = matrix[n - 1][m - 1];

        // 注意填表方向,不要弄反了,一定是已知 => 未知
        // 从下往上填最后一列
        for (int i = n - 2; i >= 0; i--) {
            dp[i][m - 1] = matrix[i][m - 1] + dp[i + 1][m - 1];
        }

        // 从右往左填最后一行
        for (int j = m - 2; j >= 0; j--) {
            dp[n - 1][j] = matrix[n - 1][j] + dp[n - 1][j + 1];
        }
        // 从下到上,从右到左
        for (int i = n - 2; i >= 0; i--) {
            for (int j = m - 2; j >= 0; j--) {
                int right = matrix[i][j] + dp[i][j + 1];
                int down = matrix[i][j] + dp[i + 1][j];
                dp[i][j] = Math.min(right, down);
            }
        }
        return dp[0][0];
    }

    // KeyPoint 方法一:dp
    public static int minPathSum1(int[][] m) {
        if (m == null || m.length == 0 || m[0] == null || m[0].length == 0) {
            return 0;
        }
        int row = m.length;
        int col = m[0].length;

        // 建立等规模的dp表
        // dp[i][j]表示从(0,0)点出发到(i,j)最省的路径和,最终返回dp[row-1][col-1]
        int[][] dp = new int[row][col];
        dp[0][0] = m[0][0];

        // 索引是从0开始,称作dp的0行
        // dp的0行 => 因为沿途只能向下/向右走,1行dp只能通过向右方式走
        for (int i = 1; i < row; i++) {
            dp[i][0] = dp[i - 1][0] + m[i][0];
        }

        // 同理 => dp的0列
        for (int j = 1; j < col; j++) {
            dp[0][j] = dp[0][j - 1] + m[0][j];
        }

        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                // 从(1,1)位置开始按照行优先,从上往下填表,每次选min(dp[左],dp[上])
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + m[i][j];
            }
        }
        return dp[row - 1][col - 1];
    }

    // KeyPoint 方法二:数组压缩 => 优化 dp
    // 优化1:因为每个位置(i,j)依赖左(i,j-1)和上(i-1,j),即只要邻近的上一行就能推出该行,之前行已经不需要了,因为不需完整二维dp
    //       -> 两行数组A和B滚动,A计算dp表0行值,使用A推出dp表1行值并记录在B中,再次复用A记录dp表2行值,依次反复
    // 优化2:因为每个位置依赖的本行右侧不回退,所以只用一行数组arr,右边直接覆盖即可
    //       arr可以从左往右推出dp的0行,比如:arr[a,b,c,d],同时arr能自身推出dp的1行,arr[a',b',c',d']
    public static int minPathSum2(int[][] m) {
        if (m == null || m.length == 0 || m[0] == null || m[0].length == 0) {
            return 0;
        }
        int row = m.length;
        int col = m[0].length;

        // 定义列数相同的arr数组
        int[] arr = new int[col];
        arr[0] = m[0][0];

        // dp中0行值计算到arr数组中,arr[a,b,c,d],相当于是0行每个位置的dp值
        for (int j = 1; j < col; j++) {
            arr[j] = arr[j - 1] + m[0][j];
        }

        // KeyPoint 总体流程:从1行开始,按照从左往右顺序填写每个位置的dp
        for (int i = 1; i < row; i++) {
            // i=1 => 从1行开始
            // arr[0] -> dp[上一行][0] -> a
            // arr[0] + m[i][0] = dp[该行][0]
            //  a + m[i][0] = a'
            // (1,1)该列位置特殊,因为没有(1,0),所以需要单列出来讨论,每行第一元素都是你这样的逻辑,m[1][0]->m[i][0]
            arr[0] += m[i][0];

            // 列是从左往右更新的
            for (int j = 1; j < col; j++) {
                // arr[j] -> dp[上一行][j] -> b (假设j=1)
                // arr[j-1] -> dp[该行][j-1] -> a'
                // min(a',b) + m[i][j] -> b' -> arr[j]
                arr[j] = Math.min(arr[j - 1], arr[j]) + m[i][j];
            }
        }
        return arr[col - 1];

        // 空间压缩

        //     ↑
        // ← (i,j)  以后凡是(i,j)位置依赖左边,上边的题都可以使用数组压缩,使用一个数组arr来记录
        //          具体步骤就代码中操作逻辑

        // ↖  ↑
        //   (i,j)  以后凡是(i,j)位置依赖左上角,上边位置的题都可以使用数组压缩,使用一个数组arr来记录
        //          dp中0行值,可以直接自己得到,因为0行没有左上角,上边的值,即arr[a,b,c,d]
        //          dp中1行值,再从最后一列,从右往左求,依次求得 d',c',b',a',因为求解d'时,其左上角,上边的值已知
        //          KeyPoint 填表原则:已知 => 未知

        // ↖  ↑
        // ← (i,j)  以后凡是(i,j)位置依赖左边,左上角,上边位置的题都可以使用数组压缩,使用一个数组arr来记录
        //          dp中0行值,每个位置(0,j)的值依赖左边前一个位置的值(0,j-1),因为没有左上角,上边的值,即得arr[a,b,c,d]
        //          dp中1行值,从左往右,计算a':先使用temp记录a(因为b'需要a,并且arr[0]会因为a'更新而被覆盖),再使用a'更新a,即arr[0]=a'
        //                            计算b':依赖a',temp(a),b,同样更新b'之前temp记录b,即temp是不断跟着右移的

        // 数组压缩(空间压缩)前提 => m*n dp二维数组
        // n(列)短 -> arr[n] 一行一行更新
        // m(行)短 -> arr[m] 一列一列更新

        // 总结:
        // 这种方法时间复杂度和之前是一样的,因为需要计算dp格子的数量没有减少,只是减少了计算dp格子时使用的实际物理空间
        // 空间压缩属于小的技巧,可以做也可以不做,只是进一步节省空间的优化方案

    }

    // for test
    public static int[][] generateRandomMatrix(int rowSize, int colSize) {
        if (rowSize < 0 || colSize < 0) {
            return null;
        }
        int[][] result = new int[rowSize][colSize];
        for (int i = 0; i != result.length; i++) {
            for (int j = 0; j != result[0].length; j++) {
                result[i][j] = (int) (Math.random() * 100);
            }
        }
        return result;
    }

    // for test
    public static void printMatrix(int[][] matrix) {
        for (int i = 0; i != matrix.length; i++) {
            for (int j = 0; j != matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int rowSize = 10;
        int colSize = 10;
        int[][] m = generateRandomMatrix(rowSize, colSize);
        System.out.println(minPathSum1(m));
        System.out.println(minPathSum2(m));
    }
}
