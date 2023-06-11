package alg_03_leetcode_top_zcy.class_09;

/**
 * @Author Wuyj
 * @DateTime 2023-02-26 13:49
 * @Version 1.0
 */
public class Problem_0073_SetMatrixZeroes {


    /*
        给定一个mxn的矩阵,如果一个元素为0,则将其所在行和列的所有元素都设为0
        请使用原地算法

        思路:
          1)使用两个变量单独记录第0行和第0列是否变0
            row0Zero开始设置false,遍历矩阵第0行发现有0,应该将第0行变0,则设置为true
            col0Zero开始设置false,遍历矩阵第0列发现有0,应该将第0列变0,则设置为true
          2)0行0列可以赋予新的含义,可以用来记录(i,j)位置为0时的行和列的位置
            比如:(i,j)位置为0,则(i,0)设置0,(0,j)设置为0
          3)最后将0行0列上设置为0位置所在行和列都设置为0,同时也将0行0列设置为0
     */

    // KeyPoint 方法一:使用两个变量
    public static void setZeroes1(int[][] matrix) {
        boolean row0Zero = false;
        boolean col0Zero = false;
        int i = 0;
        int j = 0;

        // 遍历0列
        // KeyPoint 注意:遍历0列,索引坐标是用来表示列不断变化的
        //           应该使用列索引matrix[0].length,而不是matrix.length
        //           和for循环中i或j无关,关键是行,还是列在发生变化
        for (i = 0; i < matrix[0].length; i++) {
            // KeyPoint 0固定则表示0行,j表示该行不同的位置
            //       循环条件是matrix[0].length,则只能是[0][i](列)发生变化
            if (matrix[0][i] == 0) {
                row0Zero = true;
                break;
            }
        }

        // 遍历0行
        for (i = 0; i < matrix.length; i++) {
            if (matrix[i][0] == 0) {
                col0Zero = true;
                break;
            }
        }

        // 从(1,1)位置,行优先遍历,统计0行0列信息
        for (i = 1; i < matrix.length; i++) {
            for (j = 1; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }

        // 通过0行0列信息,先修改普遍位置的值
        for (i = 1; i < matrix.length; i++) {
            for (j = 1; j < matrix[0].length; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }

        // 0行是否变0
        if (row0Zero) {
            for (i = 0; i < matrix[0].length; i++) {
                matrix[0][i] = 0;
            }
        }

        // 0列是否变0
        if (col0Zero) {
            for (i = 0; i < matrix.length; i++) {
                matrix[i][0] = 0;
            }
        }
    }

    // KeyPoint 方法二:使用一个变量
    //      备注:面试时和面试官嘚瑟,笔试这样写就是神经病

    /*
        思路:
        1)左上角是行列信息交叉位置,不能一个位置既表示0行信息,又表示0列信息
          故将左上角给0行管理,即若该位置为0,则表示0行全是0,不再代表0列上全为0
          而左上角为0,只有在该行右侧位置为0时,通过matrix[i][0]=0;设置为0的
        2)0列是否变0使用col0管理
        3)本质:还是0行和0列来作为是否变0的统计信息,只是将左上角是行列信息交叉
               位置进行了分离
     */
    public static void setZeroes2(int[][] matrix) {

        // 单独标记0列是否为0
        boolean col0 = false;

        int i = 0;
        int j = 0;

        // 所有位置都是遍历,设置matrix[i][0],matrix[0][j]
        for (i = 0; i < matrix.length; i++) {
            for (j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    // KeyPoint 之所以if条件成立,就可设置matrix[i][0]和matrix[0][j]
                    //      是因为matrix[i][j]==0时,本来i行,j列就应该设置为0
                    // i行最左侧修改成0
                    matrix[i][0] = 0;
                    // 在0列上有0,则设置为true,最后将0列全部修改成0
                    if (j == 0) {
                        col0 = true;
                    } else {
                        // 不在0列上,才去修改对应的列
                        matrix[0][j] = 0;
                    }
                }
            }
        }

        // 先修改普遍位置,从最后1行1列位置,按照行优先的顺序,从下往上修改
        // 主要是为了避免一开始就修改0行的统计信息
        for (i = matrix.length - 1; i >= 0; i--) {
            for (j = 1; j < matrix[0].length; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    // 将(i,j)位置设置为0
                    matrix[i][j] = 0;
                }
            }


        }

        // 0列单独判断,根据col0,是否将0列设置0
        if (col0) {
            for (i = 0; i < matrix.length; i++) {
                // KeyPoint 既然循环条件是matrix.length,则只能是[i][0](行)发生变化
                matrix[i][0] = 0;
            }
        }
    }
}
