package algorithm._06_hashmap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * @Author Wuyj
 * @DateTime 2022-03-17 13:27
 * @Version 1.0
 */
public class LeetCode_136_SingleNumber {
    //方法一:暴力法
    public int singleNumber01(int[] nums) {
        //定义一个列表,保存当前所有出现过一次的元素
        ArrayList<Integer> singleNumList = new ArrayList<>();

        //遍历所有元素
        for (Integer num : nums) {
            //这种做法实现的前提:其他的元素都是只出现两次,第一次出现时保存,第二次出现删除
            // 若出现三次,这种方法就不行了,因为第三次保存和第一次保存时情况是一样的
            if (singleNumList.contains(num)) {
                //如果已经出现过,删除列表中的元素
                //remove(int index) 移除索引对应的元素
                //remove(Object o) 移除元素
                //故将for循环中的int num : nums 改成 integer num : nums
                singleNumList.remove(num);
            } else {
                //没有出现过,直接保存
                singleNumList.add(num);
            }
        }
        return singleNumList.get(0);
    }

    //方法二:保存单独的元素到HashMap
    //HashMap最经典的使用方法:方便去查询一个元素是否在HashMao中
    public int singleNumber02(int[] nums) {
        HashMap<Integer, Integer> singleNumMap = new HashMap<>();
        for (int num : nums) {
            //HashMap对于没有存入的key,返回值为null
            if (singleNumMap.get(num) != null) {
                singleNumMap.remove(num);
            } else {
                singleNumMap.put(num, 1);
            }
        }
        //此时的HashMap只有一个元素,通过keySet()获取key的集合,再去通过遍历获取元素值
        return singleNumMap.keySet().iterator().next();
    }

    //方法三:用set去重,a = 2*(a+b+c)-(a+b+c+b+c)
    public int singleNumber03(int[] nums) {
        //定义一个HashSet进行去重
        HashSet<Integer> set = new HashSet<>();
        int arraySum = 0;
        int setSum = 0;

        //1.遍历数组元素,保存到set,并直接求和
        for (int num : nums) {
            //数组元素进行set集合中时,set集合对其进行去重处理
            set.add(num);
            //对数组中所有的元素进行累加
            arraySum += num;
        }

        for (Integer integer : set) {
            setSum += integer;
        }

        return setSum * 2 - arraySum;
    }

    //方法四:数学方法(做异或)
    public int singleNumber04(int[] nums) {
        int result = 0;
        //遍历所有数据,按位做异或
        for (int num : nums) {
            result ^= num;
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = {4, 1, 2, 1, 2};
        LeetCode_136_SingleNumber singleNumber = new LeetCode_136_SingleNumber();
        System.out.println(singleNumber.singleNumber03(nums));
    }
}