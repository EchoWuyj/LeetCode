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
        List<Integer> list;
        Random random;

        public RandomizedCollection() {
            map = new HashMap<>();
            list = new ArrayList<>();
            random = new Random();
        }

        public boolean insert(int val) {
            Set<Integer> set = map.getOrDefault(val, new HashSet<>());
            set.add(list.size());
            map.put(val, set);
            list.add(val);
            return set.size() == 1;
        }

        public boolean remove(int val) {
            if (!map.containsKey(val)) return false;
            Iterator<Integer> iterator = map.get(val).iterator();
            int index = iterator.next();
            int lastNum = list.get(list.size() - 1);
            list.set(index, lastNum);

            map.get(val).remove(index);

            map.get(lastNum).remove(list.size() - 1);

            if (index != list.size() - 1) {
                map.get(lastNum).add(index);
            }
            list.remove(list.size() - 1);
            if (map.get(val).size() == 0) map.remove(val);
            return true;
        }

        public int getRandom() {
            return list.get(random.nextInt(list.size()));
        }
    }
}
