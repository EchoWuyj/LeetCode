package alg_02_train_wyj._09_day_哈希查找;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author Wuyj
 * @DateTime 2023-04-15 15:49
 * @Version 1.0
 */
public class _04_136_single_number {
    public int singleNumber1(int[] nums) {
        if (nums.length == 1) return nums[0];
        for (int i = 0; i < nums.length; i++) {
            boolean isExist = true;
            for (int j = 0; j < nums.length; j++) {
                if (i != j && nums[i] == nums[j]) {
                    isExist = false;
                    break;
                }
            }
            if (isExist) return nums[i];
        }
        return -1;
    }

    public int singleNumber2(int[] nums) {
        if (nums.length == 1) return nums[0];
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (i == 0 && nums[i] != nums[i + 1]) return nums[i];
            else if (i == nums.length - 1 && nums[i] != nums[i - 1]) return nums[i];
            else if (i > 0 && nums[i] != nums[i - 1] && nums[i] != nums[i + 1]) return nums[i];
        }
        return -1;
    }

    public int singleNumber3(int[] nums) {
        if (nums.length == 1) return nums[0];
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) map.put(num, map.getOrDefault(num, 0) + 1);
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() == 1) return entry.getKey();
        }
        return -1;
    }

    public int singleNumber(int[] nums) {
        int single = 0;
        for (int num : nums) {
            single ^= num;
        }
        return single;
    }
}
