package alg_02_train_wyj._05_day_数学;

/**
 * @Author Wuyj
 * @DateTime 2023-04-20 22:03
 * @Version 1.0
 */
public class _09_204_count_primes {
    public int countPrimes(int n) {
        int res = 0;
        for (int i = 2; i < n; i++) {
            res += isPrime(i) ? 1 : 0;
        }
        return res;
    }

    private boolean isPrime(int x) {
        for (int i = 2; i < x; i++) {
            if (x % i == 0) return false;
        }
        return true;
    }

    public int countPrimes2(int n) {
        boolean[] notPrime = new boolean[n];
        int res = 0;
        for (int i = 2; i < n; i++) {
            if (notPrime[i]) continue;
            res++;
            for (int j = i; j < n; j += i) {
                notPrime[j] = true;
            }
        }
        return res;
    }
}
