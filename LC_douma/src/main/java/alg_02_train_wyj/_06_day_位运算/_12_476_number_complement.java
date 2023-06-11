package alg_02_train_wyj._06_day_位运算;

/**
 * @Author Wuyj
 * @DateTime 2023-04-23 13:37
 * @Version 1.0
 */
public class _12_476_number_complement {

    public int findComplement(int num) {
        int mask = ~0;
        while ((num & mask) > 0) mask <<= 1;
        return ~mask ^ num;
    }
}
