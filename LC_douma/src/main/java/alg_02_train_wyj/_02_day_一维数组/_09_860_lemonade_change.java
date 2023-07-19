package alg_02_train_wyj._02_day_一维数组;

/**
 * @Author Wuyj
 * @DateTime 2023-04-29 20:14
 * @Version 1.0
 */
public class _09_860_lemonade_change {
    public boolean lemonadeChange(int[] bills) {
        int n = bills.length;
        int five = 0;
        int ten = 0;
        for (int i = 0; i < n; i++) {
            if (bills[i] == 5) {
                five++;
            } else if (bills[i] == 10) {
                if (five == 0) return false;
                else five--;
                ten++;
            } else if (bills[i] == 20) {
                if (ten > 0 && five > 0) {
                    ten--;
                    five--;
                } else if (five >= 3) {
                    five -= 3;
                } else {
                    return false;
                }
            }
        }
        return true;
    }
}
