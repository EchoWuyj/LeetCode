package alg_01_ds_dm._05_application._01_system_design._1396_underground;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @Author Wuyj
 * @DateTime 2023-03-25 20:14
 * @Version 1.0
 */
public class _1396_UndergroundSystem {   // 力扣测试

    // KeyPoint 将题目复杂的描述信息，抽取成几个类，然后创建对象实例进行题目要求的操作

    // 进站信息：station 和 time
    // KeyPoint 注意类名需要大写
    public class Start {
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

    // 起始站和终止站
    public class StartEnd {
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

        /*
          KeyPoint HashSet原理

          1 我们使用 Set 集合都是需要去掉重复元素的, 如果在存储的时候逐个 equals() 比较, 效率较低
            哈希算法提高了去重复的效率, 降低了使用 equals() 方法的次数，既然存储的位置不同,就没有必要再调用equal方法

          2 当HashSet 调用 add() 方法存储对象的时候，先调用对象的 hashCode() 方法得到一个哈希值,然后在集合中查找是否有哈希值相同的对象
            如果没有哈希值相同的对象就，直接存入集合
            如果有哈希值相同的对象，就和哈希值相同的对象逐个进行 equals() 比较，比较结果为 false 就存入, true 则不存

          3 将自定义类的对象存入 HashSet 去重复，类中必须重写 hashCode() 和 equals() 方法，两个同时要重写
            hashCode()：属性相同的对象返回值必须相同, 属性不同的返回值尽量不同(提高效率)
            equals()：属性相同返回true, 属性不同返回false,返回false的时候存储

          4 若重写 hashcode() ,而不重写 equal() 方法，相同属性的 student 对象的 hashcode 值是相同的,
            重写的 hashcode() 方法是基于对象的属性来生成 hashcode 值的
            但是没有重写对象 equal() ,则是按照对象的地址进行比较,每个new出来的对象地址值是不同的

          5 equals方法，比较两个对象是否相等，底层调用  return (this == obj); 对象和传入对象，这两个对象的地址值进行比较
            重写equals方法，相同属性是同一个对象。地址值是 JVM 管理

         */

        // KeyPoint bug 修复：需要加上 equals 和 hashCode 两个方法
        @Override
        public boolean equals(Object o) {
            if (this == o) return true; // 调用的对象和传入的对象是同一个对象，直接返回 true
            if (o == null || getClass() != o.getClass()) return false;
            // 向下转型
            StartEnd startEnd = (StartEnd) o;
            // KeyPoint 调用 Objects.equals 方法进行比较
            return Objects.equals(start, startEnd.start) &&
                    Objects.equals(end, startEnd.end);
        }

        // KeyPoint 每个对象都有自己 hashCode()值，同一个对象的 hashCode()值相同
        //          重写 hashCode，自己定义相同的 start 和 end 为同一个对象
        //          调用 Objects.hash 方法
        @Override
        public int hashCode() {
            return Objects.hash(start, end);
        }
    }

    // start 和 end 站之间信息
    public class SumAmount {
        private int sum; // 所有的乘客数
        private int amount; // 所有乘客花的时间

        public SumAmount(int sum, int amount) {
            this.sum = sum;
            this.amount = amount;
        }

        public int getSum() {
            return sum;
        }

        public void setSum(int sum) {
            this.sum = sum;
        }

        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }
    }

    // KeyPoint 维护每个乘客的起始站信息 => 每个乘客都有一个独立进站信息，使用 Map 来存储
    // key -> id
    // value -> Start
    private Map<Integer, Start> startInfo;

    // KeyPoint 维护两个站的乘客信息：所有的乘客数，以及所有乘客花的时间
    // key -> StartEnd 起始站和终点站
    // value -> StartEnd 所有的乘客数，以及所有乘客花的时间
    private Map<StartEnd, SumAmount> table;

    // bug 修复：需加上构造方法，初始化用到的 Map
    public _1396_UndergroundSystem() {
        startInfo = new HashMap<>();
        table = new HashMap<>();
    }

    // 编号为 id 的乘客在 t 时刻进入地铁站 stationName
    public void checkIn(int id, String stationName, int t) {
        startInfo.put(id, new Start(stationName, t));
    }

    // 编号为 id 的乘客在 t 时刻离开地铁站 stationName
    public void checkOut(int id, String stationName, int t) {
        // 拿到起始点信息
        Start start = startInfo.get(id);

        // 计算花费的时间

        // 封装 start，end 信息，作为 key
        StartEnd key = new StartEnd(start.getStation(), stationName);
        // 通过 key 获取 value
        SumAmount value = table.getOrDefault(key, new SumAmount(0, 0));
        // 1.1 乘客个数加 1
        value.setAmount(value.getAmount() + 1);
        // 1.2 加上乘客花的时间
        value.setSum(value.getSum() + (t - start.getTime()));

        table.put(key, value);
    }

    // 返回地铁站 startStation 到地铁站 endStation 的平均花费时间
    public double getAverageTime(String startStation, String endStation) {
        StartEnd key = new StartEnd(startStation, endStation);
        SumAmount sumAmount = table.get(key);
        int sum = sumAmount.getSum();
        int amount = sumAmount.getAmount();
        return 1.0 * sum / amount;
    }
}
