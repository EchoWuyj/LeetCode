package alg_02_train_wyj._13_day_综合应用一;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @Author Wuyj
 * @DateTime 2023-05-24 20:25
 * @Version 1.0
 */
public class _03_170_TwoSum1 {
    static class TwoSum {

        ArrayList<Integer> list;
        boolean isSorted;

        public TwoSum() {
            list = new ArrayList<>();
            isSorted = true;
        }

        public void add(int number) {
            list.add(number);
            isSorted = false;
        }

        public boolean find(int value) {
            if (!isSorted) {
                Collections.sort(list);
                isSorted = true;
            }
            int size = list.size();
            int left = 0, right = size - 1;
            while (left < right) {
                int sum = list.get(left) + list.get(right);
                if (sum == value) {
                    return true;
                } else if (sum < value) {
                    left++;
                } else {
                    right--;
                }
            }
            return false;
        }
    }
     public static void main(String[] args) {
        TwoSum twoSum = new TwoSum();
        twoSum.add(1);
        twoSum.add(3);
        twoSum.add(5);
        System.out.println(twoSum.find(4)); // true
        System.out.println(twoSum.find(7)); // false
    }
}
