package alg_01_新手班_wyj.class06;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @Author Wuyj
 * @DateTime 2022-09-10 9:29
 * @Version 1.0
 */
public class ShowComparator {
    public static void main(String[] args) {
        int[] arr = {8, 1, 4, 1, 6, 8, 4, 1, 5, 8, 2, 3, 0};
        printArray(arr);
        Arrays.sort(arr);
        printArray(arr);
        System.out.println("=================================");

        // 自己定义的对象实现排序
        Student s1 = new Student("张三", 5, 27);
        Student s2 = new Student("李四", 1, 17);
        Student s3 = new Student("王五", 4, 29);
        Student s4 = new Student("赵六", 3, 9);
        Student s5 = new Student("左七", 2, 34);

        Student[] students = {s1, s2, s3, s4, s5};
        Arrays.sort(students, new IdComparator());
        printStudents(students);
    }

    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static class Student {
        private String name;
        private int age;
        private int id;

        public Student(String name, int age, int id) {
            this.name = name;
            this.age = age;
            this.id = id;
        }
    }

    public static void printStudents(Student[] students) {
        for (int i = 0; i < students.length; i++) {
            System.out.println(students[i].name + ", " + students[i].id + ", " + students[i].age);
        }
    }

    public static class IdComparator implements Comparator<Student> {

        @Override
        public int compare(Student o1, Student o2) {
            return o1.id - o2.id;
        }
    }
}
