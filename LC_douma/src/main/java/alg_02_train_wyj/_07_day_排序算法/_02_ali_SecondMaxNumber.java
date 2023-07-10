package alg_02_train_wyj._07_day_排序算法;

/**
 * @Author Wuyj
 * @DateTime 2023-05-14 9:11
 * @Version 1.0
 */
public class _02_ali_SecondMaxNumber {
    public static int getSecondMax(int[] nums) {
        int first = Integer.MIN_VALUE;
        int second = Integer.MIN_VALUE;

        for (int num : nums) {
            if (num > first) {
                second = first;
                first = num;
            } else if (num > second) {
                second = num;
            }
        }
        return second;
    }

    public static void main(String[] args) {
        int[] array = new int[]{1, 2, 3, 4, 5};
        System.out.println(getSecondMax(array)); // 4

        int[] array1 = new int[]{1, 2, 3, 4, 5, 6};
        System.out.println(getSecondMax(array1)); // 5
    }
}
