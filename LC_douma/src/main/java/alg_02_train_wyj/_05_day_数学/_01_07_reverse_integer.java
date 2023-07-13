package alg_02_train_wyj._05_day_数学;

/**
 * @Author Wuyj
 * @DateTime 2023-04-20 11:22
 * @Version 1.0
 */
public class _01_07_reverse_integer {
    public int reverse(int x) {
        String str = String.valueOf(x);
        char[] strChars = str.toCharArray();
        int left = 0, right = strChars.length - 1;
        if (strChars[0] < '0' || strChars[0] > '9') left++;
        while (left < right) {
            swap(strChars, left, right);
            left++;
            right--;
        }
        long res = Long.parseLong(String.valueOf(strChars));
        if (res > Integer.MAX_VALUE || res < Integer.MIN_VALUE) return 0;
        return (int) res;
    }

    public void swap(char[] chars, int i, int j) {
        char tmp = chars[i];
        chars[i] = chars[j];
        chars[j] = tmp;
    }

    public int reverse1(int x) {
        int res = 0;
        while (x != 0) {
            int pop = x % 10;
            x = x / 10;
            if (res > Integer.MAX_VALUE / 10
                    || (res == Integer.MAX_VALUE && pop > 7)) return 0;
            if (res < Integer.MIN_VALUE / 10
                    || (res == Integer.MIN_VALUE && pop < -8)) return 0;
            res = res * 10 + pop;
        }
        return res;
    }

    public int reverse2(int x) {
        int res = 0;
        while (x != 0) {
            int pop = x % 10;
            x = x / 10;
            int newRes = res * 10 + pop;
            if ((newRes - pop) / 10 != res) return 0;
            res = newRes;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(-13 % 10);
    }
}
