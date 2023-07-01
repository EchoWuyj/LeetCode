package alg_01_ds_dm._01_line._05_algo._04_bs;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * @Author Wuyj
 * @DateTime 2023-04-03 12:34
 * @Version 1.0
 */

public class _03_IpLocationParser {

    /*
        如何快速定位 IP 对应的城市
        [202.102.134.0,202.102.134,255] 江西南昌市
        [202.102.135.0,202.102.136.255] 江西九江市
        [202.102.156.34,202.102.157.255] 江西景德镇
        [202.101.48.0,202.101.48.255] 浙江杭州市
        [202.101.49.15,202.101.51.251] 浙江温州市
        [202.102.56.0,202.102.56.255] 江西上饶市

        注意：每个 ip 区间，没有重叠的区间，否则就出现 ip 冲突了

        问题：202.101.48.198是哪一个城市的ip 呢?

        线性查找，时间复杂度 O(n) => 二分查找(数据得有序)，时间复杂度 O(logn)


        思路：
        1.将 ip 转成长整型 => 和字符串形式相比，更加方便排序和比较
        2.按照 '起始 ip' 进行升序排列
        3.找到最后一个 startIp 小于等于 score 的这个 ip 段

        [1,5]
        [6,10]
        [12,15]
        [16,18] => 最后一个 startIp 小于等于 target 的这个 ip 段
        [19,20] => 第一个 startIp 大于等于 target 的这个 ip 段 => 该 ip 段已经超过 target
        target = 18

     */

    // 定义封装好的类 IpLocation
    private static class IpLocation {
        // 定义成 public，后续通过 IpLocation.startIp 方式，
        // 直接调用，否则还得加 get 和 set，比较麻烦
        public long startIp;
        public long endIp;
        public String locationCity;

        // 若是 IpLocation 定义了有参构造，则必须定义空参构造，否则无法 new 对象
    }

    // 存储，解析排序好 IpLocation 的集合
    // 每次查找都可以基于内存中的数据，速度快，性能好
    // => 空间换时间思想
    private static ArrayList<IpLocation> list = new ArrayList<>();

    // 数据预处理(前提：ip数据库很少变动)
    // 从文件中加载数据并解析并排序，再放入到 sortedIpLocations
    // 随着类的加载执行一次即可，故使用 static 静态代码块
    static {

        // 1.读取文件，解析 ip 地址段
        try {
            BufferedReader reader =
                    new BufferedReader(new FileReader("LC_douma/data/ip_location.txt"));
            // 数据格式：202.102.134.0 202.102.134.255 江西南昌市
            String line = null;
            while ((line = reader.readLine()) != null) {
                String[] temps = line.split(" ");
                IpLocation ipLocation = new IpLocation();
                ipLocation.startIp = ip2Score(temps[0]);
                ipLocation.endIp = ip2Score(temps[1]);
                ipLocation.locationCity = temps[2];
                list.add(ipLocation);
            }
        } catch (IOException e) {
            throw new RuntimeException("解析 ip 地址库出错" + e);
        }

        // KeyPoint 2.按照 '起始 ip' 进行升序排列 => 想要使用：二分查找，则必须保证数据是有序的，故需要排序的
        // 对动态数组 ArrayList 进行排序，使用 Collections.sort，时间复杂度：O(nlogn)
        // 数据量大时，使用归并排序，虽然是 O(nlogn)，但只是一次排序
        // 注意：对于 一次写入，多次读取 => 第一时间复杂度高一点是可以接受的，只要保证后续读取操作的时间复杂度很低即可
        // 注意：匿名内部类写法，要能不借助 idea 将其写出来
        Collections.sort(list, new Comparator<IpLocation>() {
            @Override
            public int compare(IpLocation o1, IpLocation o2) {
                // IpLocation 升序排列，通过 Long.compare 方法实现
                // o1.startIp 到 o2.startIp 和形参顺序一致，即为升序排列
                return Long.compare(o1.startIp, o2.startIp);

                // 注意：
                // 使用 Lambda 表达式，o1.startIp - o2.startIp 返回类型是 long
                // 而 compare 的返回值类型 int，两者不匹配，除非将差值转成 int
                // return (int) (o1.startIp - o2.startIp);
            }
        });
    }

    // KeyPoint 关键思路：将 ip 转成长整型 => 和字符串形式相比，更加方便排序和比较
    // 如：address ='174.36.207.186'
    // (o1,o2,o3,o4) = address.split('.')
    // integer ip =    256 * 256 * 256 * o1
    //              +  256 * 256 * o2
    //              +  256 * o3
    //              +  o4
    // 返回值类型，尽量使用基本数据类型包装类型 Long，有对应的 API 能够使用
    public static Long ip2Score(String ip) {
        // 按照 . 进行分隔
        // 使用 long 接受，避免数据越界
        String[] strArr = ip.split("\\.");
        Long score = 256 * 256 * 256 * Long.parseLong(strArr[0])
                + 256 * 256 * Long.parseLong(strArr[1])
                + 256 * Long.parseLong(strArr[2])
                + Long.parseLong(strArr[3]);
        return score;
    }

    // 二分查找指定 ip 对应的城市 => 核心处理逻辑
    // 时间复杂度：O(logn)
    // 关键：在 list 中找到最后一个 startIp 小于等于 score 的这个 ip 段
    // [1,5]
    // [6,10]
    // [12,15]
    // [16,18] => 最后一个 startIp 小于等于 target 的这个 ip 段
    // [19,20] => 第一个 startIp 大于等于 target 的这个 ip 段
    // target = 18
    public static String getIpLocation(String ip) {
        // 将 ip 转成长整型
        long score = ip2Score(ip);
        // 明确：给定数据范围，查找的 target 值
        int left = 0;
        int right = list.size() - 1;
        // 在 list 中找到最后一个 startIp 小于等于 score 的这个 ip 段
        while (left <= right) {
            int mid = left + (right - left) / 2;
            // list.get(mid) 中元素是 IpLocation，需要确定是 startIp
            if (list.get(mid).startIp <= score) {
                if (mid == list.size() - 1
                        || list.get(mid + 1).startIp > score) {
                    // if 条件成立，说明 startIp <= score，但是 score 不知道是否在 endIp 内
                    // 故不能直接 return mid，而是需要 ip 地址的 endIp 范围判断
                    if (score <= list.get(mid).endIp) {
                        return list.get(mid).locationCity;
                    } else {
                        //  如果查询的 ip > [mid 对应 ip 段的 startIp]，且 ip < [mid + 1 对应 ip 段的 startIp ]
                        //  但是如果 ip > [mid 对应 ip 段的 endIp]，说明 ip 超出了 mid 对应的 ip 段
                        //  又不属于 mid + 1 对应 ip 段，故直接退出即可，说明没有对应 ip 段
                        break;
                    }
                } else {
                    left = mid + 1;
                }
            } else { // target < nums[mid]
                right = mid - 1;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(getIpLocation("202.102.56.198")); // 江西上饶市
        System.out.println(getIpLocation("202.101.48.198")); // 浙江杭州市
    }
}
