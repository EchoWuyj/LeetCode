package alg_01_新手班_zcy.class06;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @Author Wuyj
 * @DateTime 2022-09-04 16:18
 * @Version 1.0
 */
public class ShowComparator2 {

    public static class Student {
        public String name;
        public int id;
        public int age;

        public Student(String name, int id, int age) {
            this.name = name;
            this.id = id;
            this.age = age;
        }
    }

    public static class MyComparator implements Comparator<Integer> {

        // 负，第一个参数在前
        // 正，第二个参数在前
        // 0, 谁放前都行

        @Override
        public int compare(Integer o1, Integer o2) {
            if (o1 < o2) {
                return 1;
            } else if (o1 > o2) {
                return -1;
            } else {
                return 0;
            }
        }
    }

    // 谁id大，谁放前！
    public static class IdComparator implements Comparator<Student> {

        // 如果返回负数，认为第一个参数应该排在前面
        // 如果返回正数，认为第二个参数应该排在前面
        // 如果返回0，认为谁放前面无所谓

        @Override
        public int compare(Student o1, Student o2) {
            if (o1.id < o2.id) {
                return 1;
            } else if (o2.id < o1.id) {
                return -1;
            } else {
                return 0;
            }
        }
    }

    public static void main(String[] args) {

        // 优先级队列(只是名字叫优先级队列,但本质是堆,默认是小根堆)
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        // 按照从小到大进行排序,时间复杂度O(logN)级别
        minHeap.add(2);
        minHeap.add(4);
        minHeap.add(7);
        minHeap.add(1);
        minHeap.add(4);
        minHeap.add(3);
        minHeap.add(5);

        // 获取将加入的数字中最小值(注意没有将最小值弹出)
        // peek山顶一直都在,所以没有弹出
        System.out.println(minHeap.peek());

        while (!minHeap.isEmpty()) {
            // 每次弹出的都是最小值
            Integer res = minHeap.poll();
            System.out.print(res + " ");
        }
        System.out.println();
        System.out.println("===============================");

        // 通过实现自定义比较器,来实现将默认的小根堆,变成大根堆
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(new MyComparator());
        maxHeap.add(2);
        maxHeap.add(4);
        maxHeap.add(7);
        maxHeap.add(1);
        maxHeap.add(4);
        maxHeap.add(3);
        maxHeap.add(5);

        // 获取将加入的数字中最大值(注意没有将最大值弹出)
        System.out.println(maxHeap.peek());

        while (!maxHeap.isEmpty()) {
            // 每次弹出的都是最大值
            Integer res = maxHeap.poll();
            System.out.print(res + " ");
        }
        System.out.println();
        System.out.println("===============================");

        // 和有序有关的结构,可以传入自定义比较器
        PriorityQueue<Student> queue = new PriorityQueue<Student>(new IdComparator());
        Student s1 = new Student("张三", 5, 27);
        Student s2 = new Student("李四", 1, 17);
        Student s3 = new Student("王五", 4, 29);
        Student s4 = new Student("赵六", 3, 9);
        Student s5 = new Student("左七", 2, 34);
        queue.add(s1);
        queue.add(s2);
        queue.add(s3);
        queue.add(s4);
        queue.add(s5);

        while (!queue.isEmpty()) {
            Student s = queue.poll();
            System.out.println(s.name + ", " + s.id + ", " + s.age);
        }

        // String 按照字典顺序进行比较的
        String str1 = "abc";
        String str2 = "b";
        System.out.println(str1.compareTo(str2));
    }
}
