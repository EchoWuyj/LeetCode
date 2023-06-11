package algorithm._01_arrays.atguigu;

/**
 * @Author Wuyj
 * @DateTime 2022-02-26 19:08
 * @Version 1.0
 */
public class LeetCode_48_RotateImage {
    // KeyPoint 方法一:数学方法（矩阵转置,再翻转每一行）
    public void rotate01(int[][] matrix) {
        int n = matrix.length;

        // 1.转置矩阵,原来矩阵的列变行,元素的顺序不发生改变
        // 以对角线元素为基准对称元素进行互换
        for (int i = 0; i < n; i++) {
            // 遍历上半三角
            for (int j = i; j < n; j++) {
                // 进行对称元素进行互换,关键是将行和列的下标进行互换
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }

        // 2.翻转每一行
        for (int i = 0; i < n; i++) {
            // 每一行中的元素只需要反转一半即可,必须且只能是一半,不然之前反转的元素又反转回去了
            // 不能整除的情况,即中间元素保持不动即可
            for (int j = 0; j < n / 2; j++) {
                int temp = matrix[i][j];
                // 同一行的前后元素进行调换,对称元素的列的下标和为n-1;
                matrix[i][j] = matrix[i][n - j - 1];
                matrix[i][n - j - 1] = temp;
            }
        }
    }

    // KeyPoint 方法二:分治思想,分为四个子矩阵分别考虑,只是使用一次循环,使用1/4矩阵数组的元素,但是时间复杂度还是O(n^2)
    public void rotate02(int[][] matrix) {
        int n = matrix.length;
        // 遍历四分之一矩阵,左上角
        // 在考虑行的时,考虑一次中间元素,但是只能考虑一次,之后列就不要再考虑了,否则就有重复了
        for (int i = 0; i < n / 2 + n % 2; i++) {// 行数
            for (int j = 0; j < n / 2; j++) {// 列数
                // 对于matrix[i][j],需要找到不同的四个矩阵中对应的另外三个位置和元素
                // 定义一个临时数组,保存对应的四个元素
                int[] temp = new int[4];
                int row = i;
                int col = j;

                // 通过for循环遍历这个临时数组,并将这4个元素存在临时数组中
                for (int k = 0; k < temp.length; k++) {
                    temp[k] = matrix[row][col];
                    // 行列转换的规律:col = newRow,row + newCol  = n - 1
                    int x = row;
                    row = col;
                    col = n - 1 - x;
                }
                // 再次遍历要处理的四个位置,将旋转之后的数据填入
                for (int k = 0; k < 4; k++) {
                    // 将temp数组中前一个元素赋值给matrix[row][col]
                    // k-1可能索引越界,k-1+4=3,再通过对4取余,只取大于4的部分
                    matrix[row][col] = temp[(k + 3) % 4];
                    // 下标旋转的过程
                    int x = row;
                    row = col;
                    col = n - 1 - x;
                }
            }
        }
    }

    // KeyPoint 方法三:改进
    //  基本思路:大家可能也发现了,我们其实没有必要分成4个矩阵来旋转
    //  这四个矩阵的对应关系,其实是一目了然的,我们完全可以在一次循环内,把所有元素都旋转到位
    public void rotate03(int[][] matrix) {
        int n = matrix.length;
        //  遍历四分之一矩阵
        for (int i = 0; i < (n + 1) / 2; i++) { // 使用(n+1)和n/2+n%2是相同效果
            for (int j = 0; j < n / 2; j++) {
                // 只使用一个临时变量实现元素之间的轮转,直接转一圈对应位置进行覆盖即可
                int temp = matrix[i][j];
                // KeyPoint 关键是数组下标的对应关系,需要将其梳理清楚;
                matrix[i][j] = matrix[n - j - 1][i]; // 将上一个位置的元素填入
                matrix[n - j - 1][i] = matrix[n - i - 1][n - j - 1];
                matrix[n - i - 1][n - j - 1] = matrix[j][n - i - 1];
                matrix[j][n - i - 1] = temp;
            }
        }
    }

    public static void main(String[] args) {
        int[][] image01 = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };

        int[][] image02 = {
                {5, 1, 9, 11},
                {2, 4, 8, 10},
                {13, 3, 6, 7},
                {15, 14, 12, 16}
        };

        LeetCode_48_RotateImage leetCode48RotateImage = new LeetCode_48_RotateImage();
        leetCode48RotateImage.rotate03(image01);
        leetCode48RotateImage.printImage(image01);

        leetCode48RotateImage.rotate03(image02);
        leetCode48RotateImage.printImage(image02);
    }

    public void printImage(int[][] image) {
        System.out.println("image: ");

        // 遍历二维数组中的每一行
        for (int[] line : image) {
            // 遍历每一行中每一点
            for (int point : line) {
                System.out.print(point + "\t");
            }
            // 在每一行打印完之后可以打印一行
            System.out.println();
        }
    }
}
