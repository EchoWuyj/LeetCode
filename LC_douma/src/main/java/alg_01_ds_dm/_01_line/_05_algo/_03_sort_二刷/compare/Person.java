package alg_01_ds_dm._01_line._05_algo._03_sort_二刷.compare;

/**
 * @Author Wuyj
 * @DateTime 2023-05-11 18:35
 * @Version 1.0
 */

// KeyPoint 自定义 Person 类实现 Comparable，从而保证创建的 Person 对象可以比较
// 记忆：可以比较的接口，单词是 able 结尾
public class Person implements Comparable<Person> {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    // KeyPoint 实现 Comparable 接口，重写 compareTo 方法
    @Override
    public int compareTo(Person other) {

        // KeyPoint 1.原始写法(升序排列) => 本质：对应二叉搜索树

        /*
            // KeyPoint 常规逻辑 => this.age < other.age => this.age - other.age < 0，故返回 -1
            if (this.age < other.age) {
                // 说明当前对象比 other 对象小
                // 实际上只要是返间负数就可以，不一定是 -1
                return -1;
            } else if (this.age > other.age) {
                // 说明当前对象比 other 对象大
                // 实际上只要是返回正数就可以，不一定是 1
                return 1;
                // 说明当前对象和other对象一样人 return 0；
            } else {
                // 两者相等返回 0
                return 0;
            }

        */

         /* 降序排列
            if (this.age > other.age) {
                // 说明当前对象比 other 对象大
                // 实际上只要是返间负数就可以，不一定是 -1
                return -1;
            } else if (this.age < other.age) {
                // 说明当前对象比 other 对象小
                // 实际上只要是返回正数就可以，不一定是 1
                return 1;
                // 说明当前对象和other对象一样人 return 0；
            } else {
                // 两者相等返回 0
                return 0;
            }

        */

        // KeyPoint 2.简化形式(一)
        // 升序排列 => 记忆：以 this 为中心
        return this.age - other.age;

        // 降序 => 记忆：以 other 为中心
        // return other.age - this.age;

        // KeyPoint 3.简化形式(二) => 掌握
        // return Integer.compare(this.age, other.age);

    }

    // 重写 toString 方法，为了后面打印输出
    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
