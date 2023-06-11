package alg_01_ds_wyj._05_application._01_system_design._1396_underground;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @Author Wuyj
 * @DateTime 2023-03-26 23:00
 * @Version 1.0
 */
public class _1396_UndergroundSystem {

    class Start {
        private String station;
        private int time;

        public Start(String station, int time) {
            this.station = station;
            this.time = time;
        }

        public String getStation() {
            return station;
        }

        public int getTime() {
            return time;
        }
    }

    class StartEnd {
        private String start;
        private String end;

        public StartEnd(String start, String end) {
            this.start = start;
            this.end = end;
        }

        public String getStart() {
            return start;
        }

        public String getEnd() {
            return end;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            StartEnd startEnd = (StartEnd) o;
            return Objects.equals(start, startEnd.start) &&
                    Objects.equals(end, startEnd.end);
        }

        @Override
        public int hashCode() {
            return Objects.hash(start, end);
        }
    }

    class SumAmount {
        private int sum;
        private int amount;

        public SumAmount(int sum, int amount) {
            this.sum = sum;
            this.amount = amount;
        }

        public int getSum() {
            return sum;
        }

        public int getAmount() {
            return amount;
        }

        public void setSum(int sum) {
            this.sum = sum;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }
    }

    private Map<Integer, Start> startInfo;

    private Map<StartEnd, SumAmount> table;

    public _1396_UndergroundSystem() {
        startInfo = new HashMap<>();
        table = new HashMap<>();
    }

    public void checkIn(int id, String stationName, int t) {
        startInfo.put(id, new Start(stationName, t));
    }

    public void checkOut(int id, String stationName, int t) {
        Start start = startInfo.get(id);
        StartEnd key = new StartEnd(start.getStation(), stationName);
        SumAmount value = table.getOrDefault(key, new SumAmount(0, 0));
        value.setAmount(value.getAmount() + 1);
        value.setSum(value.getSum() + (t - start.getTime()));
        table.put(key, value);
    }

    public double getAverageTime(String startStation, String endStation) {
        StartEnd key = new StartEnd(startStation, endStation);
        SumAmount value = table.get(key);
        int sum = value.getSum();
        int amount = value.getAmount();
        return 1.0 * sum / amount;
    }
}
