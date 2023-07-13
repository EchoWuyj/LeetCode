package alg_02_train_wyj._05_day_数学;

/**
 * @Author Wuyj
 * @DateTime 2023-04-21 11:42
 * @Version 1.0
 */
public class _11_1232_check_if_it_is_a_straight_line {
    public boolean checkStraightLine(int[][] coordinates) {
        int x0 = coordinates[0][0], y0 = coordinates[0][1];
        int deltaX = x0 - coordinates[1][0];
        int deltaY = y0 - coordinates[1][1];

        int n = coordinates.length;
        for (int i = 2; i < n; i++) {
            int deltaXi = x0 - coordinates[i][0];
            int deltaYi = y0 - coordinates[i][1];
            if (deltaX * deltaYi != deltaY * deltaXi) return false;
        }
        return true;
    }
}
