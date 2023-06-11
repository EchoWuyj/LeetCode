package alg_01_ds_wyj._01_line._05_algo._04_bs;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * @Author Wuyj
 * @DateTime 2023-04-03 15:21
 * @Version 1.0
 */
public class IpLocationParser {
    private static class IpLocation {
        public long startIp;
        public long endIp;
        public String locationCity;
    }

    private static ArrayList<IpLocation> sortedIpLocations = new ArrayList<>();

    static {
        try {
            BufferedReader reader =
                    new BufferedReader(new FileReader("LC_douma/data/ip_location.txt"));
            String line = null;
            while ((line = reader.readLine()) != null) {
                String[] temps = line.split(" ");
                IpLocation ipLocation = new IpLocation();
                ipLocation.startIp = ip2Score(temps[0]);
                ipLocation.endIp = ip2Score(temps[1]);
                ipLocation.locationCity = temps[2];
                sortedIpLocations.add(ipLocation);
            }
        } catch (IOException e) {
            throw new RuntimeException("解析 ip 地址库出错" + e);
        }

        Collections.sort(sortedIpLocations, new Comparator<IpLocation>() {
            @Override
            public int compare(IpLocation o1, IpLocation o2) {
                return Long.compare(o1.startIp, o2.startIp);
            }
        });
    }

    private static Long ip2Score(String ip) {
        String[] temps = ip.split("\\.");
        Long score = 256 * 256 * 256 * Long.parseLong(temps[0])
                + 256 * 256 * Long.parseLong(temps[1])
                + 256 * Long.parseLong(temps[2])
                + Long.parseLong(temps[3]);
        return score;
    }

    public static String getIpLocation(String ip) {
        long score = ip2Score(ip);
        int left = 0;
        int right = sortedIpLocations.size() - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (sortedIpLocations.get(mid).startIp <= score) {
                if (mid == sortedIpLocations.size() - 1
                        || sortedIpLocations.get(mid + 1).startIp > score) {
                    if (score <= sortedIpLocations.get(mid).endIp) {
                        return sortedIpLocations.get(mid).locationCity;
                    } else {
                        break;
                    }
                } else {
                    left = mid + 1;
                }
            } else {
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
