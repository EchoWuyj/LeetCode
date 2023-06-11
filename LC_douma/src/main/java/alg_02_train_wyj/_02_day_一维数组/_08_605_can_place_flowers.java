package alg_02_train_wyj._02_day_一维数组;

/**
 * @Author Wuyj
 * @DateTime 2023-04-29 18:31
 * @Version 1.0
 */
public class _08_605_can_place_flowers {
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        int i = 0;
        int length = flowerbed.length;
        while (i < length && n > 0) {
            if (flowerbed[i] == 1) {
                i += 2;
            } else if (i == length - 1 || flowerbed[i + 1] == 0) {
                n--;
                i += 2;
            } else {
                i += 3;
            }
        }
        return n <= 0;
    }
}
