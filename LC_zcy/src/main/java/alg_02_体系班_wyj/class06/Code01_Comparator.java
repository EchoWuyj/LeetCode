package alg_02_体系班_wyj.class06;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeMap;

/**
 * @Author Wuyj
 * @DateTime 2022-09-20 11:49
 * @Version 1.0
 */
public class Code01_Comparator {
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

    public static class IdShengAgeJiangOrder implements Comparator<Student> {

        @Override
        public int compare(Student o1, Student o2) {
            // id,age
            return o1.id != o2.id ? (o1.id - o2.id) : (o2.age - o1.age);
        }
    }

    public static void main(String[] args) {
        int[] arr = {5, 4, 3, 2, 7, 9, 1, 0};
        Arrays.sort(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();

        Student student1 = new Student("A", 4, 40);
        Student student2 = new Student("B", 4, 21);
        Student student3 = new Student("C", 3, 12);
        Student student4 = new Student("D", 3, 62);
        Student student5 = new Student("E", 3, 42);

        Student[] arrayStu = {student1, student2, student3, student4, student5};
        System.out.println("=======第一条打印=======");

        Arrays.sort(arrayStu, new IdShengAgeJiangOrder());

        for (int i = 0; i < arrayStu.length; i++) {
            Student s = arrayStu[i];
            System.out.println(s.name + "," + s.id + "," + s.age);
        }

        System.out.println("=======第二条打印=======");

        ArrayList<Student> stuList = new ArrayList<>();
        stuList.add(student1);
        stuList.add(student2);
        stuList.add(student3);
        stuList.add(student4);
        stuList.add(student5);

        stuList.sort(new IdShengAgeJiangOrder());

        for (int i = 0; i < stuList.size(); i++) {
            Student s = stuList.get(i);
            System.out.println(s.name + "," + s.id + "," + s.age);
        }

        System.out.println("=======第三条打印=======");

        student1 = new Student("A", 4, 40);
        student2 = new Student("B", 4, 21);
        student3 = new Student("C", 4, 12);
        student4 = new Student("D", 4, 62);
        student5 = new Student("E", 4, 42);

        TreeMap<Student, String> treeMap = new TreeMap<>((s1, s2) -> (s2.age - s1.age));
        treeMap.put(student1, "我是学生1,我的名字叫A");
        treeMap.put(student2, "我是学生2,我的名字叫B");
        treeMap.put(student3, "我是学生3,我的名字叫C");
        treeMap.put(student4, "我是学生4,我的名字叫D");
        treeMap.put(student5, "我是学生5,我的名字叫E");

        for (Student stu : treeMap.keySet()) {
            System.out.println(stu.name + "," + stu.id + "," + stu.age);
        }






    }
}
