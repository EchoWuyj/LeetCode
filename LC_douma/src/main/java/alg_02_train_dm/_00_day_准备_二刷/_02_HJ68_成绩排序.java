package alg_02_train_dm._00_day_准备_二刷;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @Author Wuyj
 * @DateTime 2023-08-09 22:26
 * @Version 1.0
 */
public class _02_HJ68_成绩排序 {
    static class Student {
        private String name;
        private int grade;

        public Student(String name, int grade) {
            this.name = name;
            this.grade = grade;
        }

        public int getGrade() {
            return grade;
        }

        // 重写 toString
        public String toString() {
            return name + " " + grade;
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        while (input.hasNext()) {
            // next() 返回值类型是 String，需要将其转成int
            int n = Integer.parseInt(input.next());
            // KeyPoint 另外一种实现 => nextLine();
            // int n = Integer.parseInt(input.nextLine());
            int flag = Integer.parseInt(input.next());
            Student[] students = new Student[n];
            for (int i = 0; i < n; i++) {
                // 直接使用 next() 读取 fang 90该行，并且按照空格切分分别赋值
                students[i] = new Student(input.next(), Integer.parseInt(input.next()));
                // KeyPoint 另外一种实现 => nextLine();
//                String line = input.nextLine();
//                String[] info = line.split(" ");
//                students[i] = new Student(info[0], Integer.parseInt(info[1]));
            }

            if (flag == 1) {
                // 按照成绩升序排列
                // 推荐使用 Lambda 表达式写法，比较简单
                Arrays.sort(students, (s1, s2) -> s1.getGrade() - s2.getGrade());
            } else {
                // 按照成绩降序排列
                Arrays.sort(students, (s1, s2) -> s2.getGrade() - s1.getGrade());
            }

            for (Student student : students) {
                System.out.println(student);
            }
        }

        // 输入：
        // 3
        // 1
        // fang 90
        // yang 50
        // ning 70

        // 输出：
        // yang 50
        // ning 70
        // fang 90

    }
}
