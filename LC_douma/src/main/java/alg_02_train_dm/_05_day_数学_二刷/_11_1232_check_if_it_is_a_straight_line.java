package alg_02_train_dm._05_day_数学_二刷;

/**
 * @Author Wuyj
 * @DateTime 2023-04-20 20:43
 * @Version 1.0
 */
public class _11_1232_check_if_it_is_a_straight_line {
    /*
        1232. 缀点成线
        给定一个数组 coordinates ，其中 coordinates[i] = [x, y] ，
        [x, y] 表示横坐标为 x、纵坐标为 y 的点。请你来判断，
        这些点是否在该坐标系中属于同一条直线上。

        提示：
        2 <= coordinates.length <= 1000
        coordinates[i].length == 2
        -10^4 <= coordinates[i][0], coordinates[i][1] <= 10^4
        coordinates 中不含重复的点


        输入：coordinates = [[1,2],[2,3],[3,4],[4,5],[5,6],[6,7]]
        输出：true

     */

    // KeyPoint 方法一 直接模拟
    public boolean checkStraightLine(int[][] coordinates) {
        // 思路：使用数组前两点，确定一条直线，计算出其斜率 k
        //       计算出后续点与数组第一个点斜率 k'，判断 k与 k'是否相同
        // (x0,y0)
        int x0 = coordinates[0][0], y0 = coordinates[0][1];
        int deltaY = coordinates[1][1] - y0;
        int deltaX = coordinates[1][0] - x0;
        int n = coordinates.length;
        // i = 2 开始判断
        for (int i = 2; i < n; i++) {
            int deltaYi = coordinates[i][1] - y0;
            int deltaXi = coordinates[i][0] - x0;
            // 通过斜率来判断，所有的点是否在同一条直线上
            // deltaY / deltaX = deltaYi / deltaXi
            // => 因为 int 使用 /，存在取整情况，将 / 转成 *
            // => 等价转换：deltaY * deltaXi = deltaYi * deltaX
            // KeyPoint true 要求很高，全部 true 才是真的 true
            // 一个 true 不是真的 true，但是一个 false 是真的 false;
            if (deltaY * deltaXi != deltaYi * deltaX) return false;
        }
        return true;
    }
}
