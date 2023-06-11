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

    // 定义封装好的类 IpLocation
    private static class IpLocation {
        public long startIp;
        public long endIp;
        public String locationCity;
    }

    // 存储解析排序好 IpLocation 的集合
    // 每次查找都可以基于内存中的数据，速度快，性能好
    // KeyPoint => 空间换时间思想
    private static ArrayList<IpLocation> sortedIpLocations = new ArrayList<>();

    // KeyPoint 数据预处理(前提:ip数据库很少变动)
    // 从文件中加载数据并解析并排序，再放入到 sortedIpLocations
    // 随着类的加载执行一次即可，故使用 static 静态代码块
    static {
        try {
            // 1. 读取文件，解析 ip 地址段
            BufferedReader reader =
                    new BufferedReader(new FileReader("LC_douma/data/ip_location.txt"));
            String line = null;
            while ((line = reader.readLine()) != null) {
                String[] temps = line.split(" ");
                IpLocation ipLocation = new IpLocation();
                // 202.102.134.0 202.102.134.255 江西南昌市
                ipLocation.startIp = ip2Score(temps[0]);
                ipLocation.endIp = ip2Score(temps[1]);
                ipLocation.locationCity = temps[2];
                sortedIpLocations.add(ipLocation);
            }
        } catch (IOException e) {
            throw new RuntimeException("解析 ip 地址库出错" + e);
        }

        // 2. 按照起始 ip 进行升序排列
        // 对动态数组 ArrayList 进行排序，使用 Collections.sort
        // 时间复杂度：O(nlogn) 数据量大时，使用归并排序。虽然是O(nlogn)，但只是一次排序
        // KeyPoint 一次写入，多次读取 => 第一时间复杂度高一点是可以接受的，
        //           只要保证后续读取操作的时间复杂度很低即可
        Collections.sort(sortedIpLocations, new Comparator<IpLocation>() {
            @Override
            public int compare(IpLocation o1, IpLocation o2) {
                // IpLocation 升序排列，通过 Long.compare 方法实现，
                // o1.startIp 到 o2.startIp 和形参顺序一致，即为升序排列
                return Long.compare(o1.startIp, o2.startIp);
                // 使用 Lambda 表达式，o1.startIp - o2.startIp 返回类型是 long，和 compare 的返回值类型不匹配
            }
        });
    }

    // 将 ip 转成长整型
    public static Long ip2Score(String ip) {
        // 按照 . 进行分隔
        String[] temps = ip.split("\\.");
        Long score = 256 * 256 * 256 * Long.parseLong(temps[0])
                + 256 * 256 * Long.parseLong(temps[1])
                + 256 * Long.parseLong(temps[2])
                + Long.parseLong(temps[3]);
        return score;
    }

    // KeyPoint 二分查找指定 ip 对应的城市(核心处理逻辑)
    // 时间复杂度：O(logn)
    public static String getIpLocation(String ip) {
        long score = ip2Score(ip);
        // 3. 在 sortedIpLocations 中找到最后一个 startIp 小于等于 score 的这个 ip 段
        // KeyPoint 明确：给定数据范围，查找的 target 值
        int left = 0;
        int right = sortedIpLocations.size() - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (sortedIpLocations.get(mid).startIp <= score) {
                if (mid == sortedIpLocations.size() - 1
                        || sortedIpLocations.get(mid + 1).startIp > score) {
                    // 不是直接 return mid，而是需要 ip 地址的 endIp 范围判断
                    if (score <= sortedIpLocations.get(mid).endIp) {
                        return sortedIpLocations.get(mid).locationCity;
                    } else {
                        // KeyPoint bug 修复：如果查询的 ip > (mid 对应 ip 段的 startIp)
                        //  且 ip < (mid + 1 对应 ip 段的 startIp)，但是如果 ip > (mid 对应 ip 段的 endIp)，
                        //  说明 ip 超出了 mid 对应的 ip 段，又不属于 mid + 1 对应 ip 段直接退出即可
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
        System.out.println(getIpLocation("202.102.56.198")); //江西上饶市
        System.out.println(getIpLocation("202.101.48.198")); // 浙江杭州市
    }
}
