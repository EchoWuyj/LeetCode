package alg_01_ds_wyj._01_line._05_algo._04_bs;

import java.io.BufferedReader;
import java.io.FileReader;
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
        Long startIp;
        Long endIp;
        String locationCity;

        IpLocation() {
        }

        IpLocation(Long startIp, Long endIp, String locationCity) {
            this.startIp = startIp;
            this.endIp = endIp;
            this.locationCity = locationCity;
        }
    }

    private static ArrayList<IpLocation> list = new ArrayList<>();

    static {
        try {
            BufferedReader reader =
                    new BufferedReader(new FileReader("LC_douma/data/ip_location.txt"));
            String line = null;
            while ((line = reader.readLine()) != null) {
                String[] strArr = line.split(" ");
                IpLocation ipLocation = new IpLocation();
                ipLocation.startIp = ip2Score(strArr[0]);
                ipLocation.endIp = ip2Score(strArr[1]);
                ipLocation.locationCity = strArr[2];
                list.add(ipLocation);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        Collections.sort(list, new Comparator<IpLocation>() {
            @Override
            public int compare(IpLocation o1, IpLocation o2) {
                return Long.compare(o1.startIp, o2.startIp);
            }
        });
    }

    public static Long ip2Score(String ip) {
        String[] strArr = ip.split("\\.");
        Long Score = 256 * 256 * 256 * Long.parseLong(strArr[0])
                + 256 * 256 * Long.parseLong(strArr[1])
                + 256 * Long.parseLong(strArr[2])
                + Long.parseLong(strArr[3]);

        return Score;
    }

    public static String getIpLocation(String ip) {
        Long score = ip2Score(ip);
        int left = 0, right = list.size() - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (list.get(mid).startIp <= score) {
                if (mid == list.size() - 1 || list.get(mid + 1).startIp > score) {
                    if (score <= list.get(mid).endIp) {
                        return list.get(mid).locationCity;
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
        System.out.println(getIpLocation("202.102.56.198")); // 江西上饶市
        System.out.println(getIpLocation("202.101.48.198")); // 浙江杭州市
    }
}
