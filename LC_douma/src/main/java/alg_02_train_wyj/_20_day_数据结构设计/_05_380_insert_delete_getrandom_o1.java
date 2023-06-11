package alg_02_train_wyj._20_day_数据结构设计;

import java.util.*;

/**
 * @Author Wuyj
 * @DateTime 2023-05-28 14:18
 * @Version 1.0
 */
public class _05_380_insert_delete_getrandom_o1 {

    class RandomizedSet {

        Map<Integer, Integer> map;
        List<Integer> nums;
        Random random;

        public RandomizedSet() {
            map = new HashMap<>();
            nums = new ArrayList<>();
            random = new Random();
        }

        public boolean insert(int val) {
            if (map.containsKey(val)) return false;
            map.put(val, nums.size());
            nums.add(val);
            return true;
        }

        public boolean remove(int val) {
            if (!map.containsKey(val)) return false;
            int index = map.get(val);

            int lastNum = nums.get(nums.size() - 1);
            nums.set(index, lastNum);
            map.put(lastNum, index);

            nums.remove(nums.size() - 1);
            map.remove(val);
            return true;
        }

        public int getRandom() {
            return nums.get(random.nextInt(nums.size()));
        }
    }
}
