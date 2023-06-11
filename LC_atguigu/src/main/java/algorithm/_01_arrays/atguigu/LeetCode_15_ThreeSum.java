package algorithm._01_arrays.atguigu;

import java.util.*;

/**
 * @Author Wuyj
 * @DateTime 2022-02-25 20:03
 * @Version 1.0
 */
public class LeetCode_15_ThreeSum {
    // KeyPoint 方法一:暴力法(不推荐)
    // 基本思路:每个人都先去找到另一个人,然后再一起逐个去找第三个人
    // 很容易想到,实现起来就是三重循环:这个时间复杂度是 O(n^3)
    public List<List<Integer>> threeSum01(int[] nums) {
        int n = nums.length;
        //定义结果列表
        List<List<Integer>> result = new ArrayList<>();
        //三重for循环,枚举所有的三数组合
        for (int i = 0; i < n - 2; i++) {
            for (int j = i + 1; j < n - 1; j++) {
                for (int k = j + 1; k < n; k++) {
                    if (nums[i] + nums[j] + nums[k] == 0)
                        //将数组转成list
                        result.add(Arrays.asList(nums[i], nums[j], nums[k]));
                }
            }
        }
        return result;
    }

    //KeyPoint 方法二:使用哈希表保存结果(不是推荐)
    public List<List<Integer>> threeSum02(int[] nums) {
        int n = nums.length;
        List<List<Integer>> result = new ArrayList<>();

        //定义一个hash map
        //对应找的是两个数,不能是原来的integer,而是现在的list
        HashMap<Integer, List<Integer>> map = new HashMap<>();

        //遍历数组,寻找每个数对应的那个两个数(list)
        for (int i = 0; i < n; i++) {
            int thatNum = 0 - nums[i];
            if (map.containsKey(thatNum)) {
                //如果已经存在thatNum(即thatNum对应的两个数之前就已经存入),就找到了一组解
                //创建一个临时list用来接受thatNum对应的那两个数的list,同时将thatNum加入,形成一个三元list
                ArrayList<Integer> tempList = new ArrayList<>(map.get(thatNum));
                tempList.add(thatNum);
                //将三元list加入到result中
                result.add(tempList);
            }

            //把当前数对应的两数组合都保存到map里
            //遍历之前所有的数,范围是到i截止
            //之前所有的数作两两组合,是否有两数组合出现,有的话则存在map中
            for (int j = 0; j < i; j++) {
                //以两数之和作为key
                int newKey = nums[i] + nums[j];
                //如果key不存在,就直接添加进去
                // KeyPoint key存在忽略的情况不对,
                // 例如:-1 + 0 = -1 和 -3 + 2 = -1 这种情况是不能忽略的,同时也是不能覆盖的
                // HashMap中value不能是List,而是list<list>,后续的数据结构就很复杂,逻辑有点复杂
                if (!map.containsKey(newKey)) {
                    ArrayList<Integer> tempList = new ArrayList<>();
                    tempList.add(nums[i]);
                    tempList.add(nums[j]);
                    //将其加入map中
                    map.put(newKey, tempList);
                }
            }
        }
        return result;
    }

    //KeyPoint 方法三:双指针法(方法通用)
    public List<List<Integer>> threeSum03(int[] nums) {
        int n = nums.length;
        List<List<Integer>> result = new ArrayList<>();

        // 0.先对数组排序
        // 通过将数组进行排序,从而使用左右指针来依次从数组的两头进行扫描,从而判断是否有合适
        //KeyPoint 力扣是可以使用Arrays.sort方法的
        Arrays.sort(nums);

        // 1.遍历每一个元素,作为当前三元组中最小的那个（最矮个做核心）
        for (int i = 0; i < n; i++) {
            // KeyPoint 现将特殊的情况都排除在外
            // 1.1如果当前数已经大于0,直接退出循环
            if (nums[i] > 0) break;

            // 1.2如果当前数据已经出现过,直接跳过
            //nums[i] == nums[i - 1],表示:当前元素和前一个元素相同,是for循环遍历到的当前元素
            if (i > 0 && nums[i] == nums[i - 1]) continue;

            // 1.3常规情况,以当前数做最小数,定义左右指针
            int lp = i + 1;
            int rp = n - 1;
            // 只要左右指针不重叠,就继续移动指针
            while (lp < rp) {
                int sum = nums[i] + nums[lp] + nums[rp];
                // 判断sum,与0做大小对比
                if (sum == 0) {
                    // 1.3.1 等于0,就是找到了一组解
                    result.add(Arrays.asList(nums[i], nums[lp], nums[rp]));
                    // 移动左右指针
                    lp++;
                    rp--;
                    // 如果移动之后的元素相同,直接跳过
                    // lp和rp已经发生变化,但是还需要满足外层循环的判断条件
                    while (lp < rp && nums[lp] == nums[lp - 1]) lp++;
                    while (lp < rp && nums[rp] == nums[rp + 1]) rp--;
                }
                // 1.3.2 小于0,较小的数增大,左指针右移
                else if (sum < 0) {
                    lp++;
                }
                // 1.3.3 大于0,较大的数减小,右指针左移
                else {
                    rp--;
                }
            }
        }
        return result;
    }

    public void BubbleSort(int[] nums) {
        int n = nums.length;
        // 外层次数循环从1开始
        for (int i = 1; i < n; i++) {
            // 内层循环是是从0开始
            // 随着比完的次数,需要比较的元素也在减少
            for (int j = 0; j < n - i; j++) {
                // 内层元素下标的变换是基于j的,而不是基于i的
                if (nums[j] > nums[j + 1]) {
                    int temp = nums[j];
                    nums[j] = nums[j + 1];
                    nums[j + 1] = temp;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] input = {-1, 0, 1, 2, -1, -4};
        LeetCode_15_ThreeSum threeSum = new LeetCode_15_ThreeSum();
        List<List<Integer>> result = threeSum.threeSum03(input);
        System.out.println(result);
    }
}
