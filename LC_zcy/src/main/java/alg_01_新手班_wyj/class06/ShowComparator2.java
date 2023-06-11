package alg_01_新手班_wyj.class06;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @Author Wuyj
 * @DateTime 2022-09-10 9:53
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
            return o2 - o1;
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
        //优先级队列
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        minHeap.add(2);
        minHeap.add(4);
        minHeap.add(7);
        minHeap.add(1);
        minHeap.add(4);
        minHeap.add(3);
        minHeap.add(5);

        while (!minHeap.isEmpty()) {
            System.out.print(minHeap.poll() + " ");
        }
        System.out.println();
        System.out.println("=========================");

        // 大根堆
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(new MyComparator());
        maxHeap.add(2);
        maxHeap.add(4);
        maxHeap.add(7);
        maxHeap.add(1);
        maxHeap.add(4);
        maxHeap.add(3);
        maxHeap.add(5);

        while (!maxHeap.isEmpty()) {
            System.out.print(maxHeap.poll() + " ");
        }
        System.out.println();
    }
}
