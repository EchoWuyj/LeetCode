package alg_02_train_wyj._20_day_数据结构设计;

import java.util.*;

/**
 * @Author Wuyj
 * @DateTime 2023-05-28 15:06
 * @Version 1.0
 */
public class _06_381_insert_delete_getrandom_o1_duplicates_allowed {

    class RandomizedCollection {
        Map<Integer, Set<Integer>> map;
        List<Integer> nums;
        Random random;

        public RandomizedCollection() {
            map = new HashMap<>();
            nums = new ArrayList<>();
            random = new Random();
        }

        public boolean insert(int val) {
            Set<Integer> set = map.getOrDefault(val, new HashSet<>());
            set.add(nums.size());
            nums.add(val);
            map.put(val, set);
            return set.size() == 1;
        }

        public boolean remove(int val) {
            if (!map.containsKey(val)) return false;

            Iterator<Integer> iterator = map.get(val).iterator();
            int index = iterator.next();
            int lastNum = nums.get(nums.size() - 1);
            nums.set(index, lastNum);

            map.get(val).remove(index);

            map.get(lastNum).remove(nums.size() - 1);
            if (index < nums.size() - 1) {
                map.get(lastNum).add(index);
            }

            nums.remove(nums.size() - 1);
            if (map.get(val).size() == 0) map.remove(val);
            return true;
        }

        public int getRandom() {
            return nums.get(random.nextInt(nums.size()));
        }
    }
}
