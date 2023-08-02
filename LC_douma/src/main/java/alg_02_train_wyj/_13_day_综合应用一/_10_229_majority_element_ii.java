package alg_02_train_wyj._13_day_综合应用一;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author Wuyj
 * @DateTime 2023-05-30 17:59
 * @Version 1.0
 */
public class _10_229_majority_element_ii {
    public List<Integer> majorityElement(int[] nums) {
        List<Integer> res = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        int n = nums.length;
        for (int key : map.keySet()) {
            if (map.get(key) > n / 3) {
                res.add(key);
            }
        }
        return res;
    }

    public List<Integer> majorityElement1(int[] nums) {
        int candidate1 = 0, candidate2 = 0;
        int count1 = 0, count2 = 0;
        for (int num : nums) {
            if (num == candidate1) {
                count1++;
            } else if (num == candidate2) {
                count2++;
            } else {
                if (count1 == 0) {
                    candidate1 = num;
                    count1++;
                } else if (count2 == 0) {
                    candidate2 = num;
                    count2++;
                } else {
                    count1--;
                    count2--;
                }
            }
        }
        count1 = 0;
        count2 = 0;
        for (int num : nums) {
            if (num == candidate1) count1++;
            else if (num == candidate2) count2++;
        }

        List<Integer> res = new ArrayList<>();
        int n = nums.length;
        if (count1 > n / 3) res.add(candidate1);
        if (count2 > n / 3) res.add(candidate2);
        return res;
    }
}