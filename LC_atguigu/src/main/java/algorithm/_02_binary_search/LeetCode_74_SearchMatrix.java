package algorithm._02_binary_search;

/**
 * @Author Wuyj
 * @DateTime 2022-03-02 13:11
 * @Version 1.0
 */
public class LeetCode_74_SearchMatrix {
    public boolean searchMatrix(int[][] matrix, int target) {
        // 先定义m和n,分别是矩阵的行和列
        int m = matrix.length;
        // 说明矩阵为空矩阵
        if (m == 0) {
            return false;
        }
        int n = matrix[0].length;

        // 二分查找,定义左右指针
        int left = 0;
        int right = m * n - 1;
        while (left <= right) {
            // 计算中间位置
            int mid = (left + right) / 2;
            // 计算二维矩阵中对应的行列号,取出对应元素
            int midElement = matrix[mid / n][mid % n];
            // 判断中间元素与target的大小关系
            if (midElement < target) {
                left = mid + 1;
            } else if (midElement > target) {
                right = mid - 1;
            } else {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {1, 3, 5, 7},
                {10, 11, 16, 20},
                {23, 30, 34, 50}
        };
        int target = 11;
        LeetCode_74_SearchMatrix searchMatrix = new LeetCode_74_SearchMatrix();
        System.out.println(searchMatrix.searchMatrix(matrix, target));
    }
}
