package alg_02_体系班_wyj.class02;

/**
 * @Author Wuyj
 * @DateTime 2022-09-14 19:30
 * @Version 1.0
 */
public class Code02_EvenTimesOddTimes {
    public static int printOddTimesNum(int[] arr) {
        int eor = 0;
        for (int num : arr) {
            eor ^= num;
        }
        return eor;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 2, 1, 4, 5, 5};
        System.out.println(printOddTimesNum(arr));

        printNumBinary(8);
        System.out.println();
        printNumBinary(getMostRightOne(8));

        int[] arr2 = {2, 2, 3, 4, 5, 5, 7, 7, 8, 8};
        System.out.println();
        printOddTimesNum2(arr2);
    }

    public static void printNumBinary(int num) {
        for (int i = 31; i >= 0; i--) {
            System.out.print(((num >> i) & 1) == 1 ? "1" : "0");
            if (i % 4 == 0) {
                System.out.print(" ");
            }
        }
    }

    public static void printOddTimesNum2(int[] arr) {
        int eor = 0;
        for (int num : arr) {
            eor ^= num;
        }

        // 最右侧1
        int rightOne = eor & (~eor + 1);
        int one = 0;

        for (int num : arr) {
            if ((num & rightOne) != 0) {
                one ^= num;
            }
        }

        System.out.println(one + "--" + (one ^ eor));
    }

    public static int bit1Counts(int num) {
        int count = 0;
        while (num != 0) {
            int rightOne = num & (~num + 1);
            count++;
            num ^= rightOne;
        }

        return count;
    }

    public static int getMostRightOne(int num) {
        return num & (~num + 1);
    }
}
