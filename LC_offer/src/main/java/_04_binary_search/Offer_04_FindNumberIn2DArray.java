package _04_binary_search;

/**
 * @Author Wuyj
 * @DateTime 2022-08-19 20:30
 * @Version 1.0
 */
public class Offer_04_FindNumberIn2DArray {
    public static void main(String[] args) {
        int[][] input = {
                {1, 4, 7, 11, 15},
                {2, 5, 8, 12, 19},
                {3, 6, 9, 16, 22},
                {10, 13, 14, 17, 24},
                {18, 21, 23, 26, 30}
        };

        System.out.println(findNumberIn2DArray(input, 5));
        System.out.println(findNumberIn2DArray(input, 20));
    }

    public static boolean findNumberIn2DArray(int[][] matrix, int target) {
        // KeyPoint 从特殊位置开始进行判断
        //      从数组的最左下角位置开始去搜索整个二维数组

        // 1、当发现当前遍历的元素大于 target 时，意味着这个元素后面的所有元素也都大于 target
        // 那么就不用去搜索这一行了
        // 2、当发现当前遍历的元素小于 target 时，意味着这个元素上面的所有元素也都小于 target
        // 那么就不用去搜索这一列了

        /*
            KeyPoint 补充：二维数组
            二维数组的长度
            int array[3][4] 代表的含义：这是一个三行四列的整型数组，它的长度为应该是分开来看的，它有三行，每一行对应不同的长度
            列如 array{{1,2,3},{4},{5,6,7,8}}

            int a= num[0].length 则它代表的是{1,2,3}的长度， a=3
            int b=num[1].length 则它代表的是{4}的长度， b=1
            int c=num[2].length 则它代表的是{5,6,7,8}的长度，c=4
        */

        // 初始化 i 和 j 为数组左下角元素
        // 最后一行
        int i = matrix.length - 1;

        // 第 0 列
        int j = 0;

        // 从数组的左下角开始出发，只要 i 和 j 没有越界继续判断
        // matrix[0].length 是第一行的最大索引+1，为 while 循环的边界
        // KeyPoint while 循环的边界，通过 i 和 j 的变化情况，找到其不能突破的临界值
        //  如 i-- 在不断递减的过程中，不能减少到其值小于 0 的情况，所以得是 i >= 0
        while (i >= 0 && j < matrix[0].length) {

            // 当发现当前遍历的元素大于 target 时，意味着这个元素后面的所有元素也都大于 target
            if (matrix[i][j] > target) {

                // 行索引向上移动一格（即 i-- ），即消去矩阵第 i 行元素
                i--;

                // 当发现当前遍历的元素小于 target 时，意味着这个元素上面的所有元素也都小于 target
            } else if (matrix[i][j] < target) {

                // 列索引向右移动一格（即 j++ ），即消去矩阵第 j 列元素
                j++;

                // 否则，说明找到目标值
            } else {
                // 直接返回 true
                return true;
            }
        }
        // 遍历了整个二维数组，没有找到目标值，返回 false
        return false;
    }
}
