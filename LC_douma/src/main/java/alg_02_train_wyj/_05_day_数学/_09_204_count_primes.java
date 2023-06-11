package alg_02_train_wyj._05_day_数学;

/**
 * @Author Wuyj
 * @DateTime 2023-04-20 22:03
 * @Version 1.0
 */
public class _09_204_count_primes {
    public int countPrimes(int n) {
        int ans = 0;
        for (int i = 2; i < n; i++) {
            if (isPrime(i)) ans++;
        }
        return ans;
    }

    public boolean isPrime(int x) {
        for (int i = 2; i < x; i++) {
            if (x % i == 0) return false;
        }
        return true;
    }

    public int countPrimes1(int n) {
        int ans = 0;
        boolean[] notPrimes = new boolean[n];
        for (int i = 2; i < n; i++) {
            if (notPrimes[i]) continue;
            ans++;
            for (int j = i + i; j < n; j += i) {
                notPrimes[j] = true;
            }
        }
        return ans;
    }
}
