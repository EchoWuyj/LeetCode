package alg_02_train_dm._09_day_哈希查找_二刷;

/**
 * @Author Wuyj
 * @DateTime 2023-04-24 16:44
 * @Version 1.0
 */
public class _12_Note_Comparator {
    /*

        Comparator 比较器

	    // 按照姓名排序 => 姓名是主要条件，年龄是次要条件
	    public int compareTo(Person o) {
	        // name 是 String 类型，只能使用 compareTo
	        // 姓名是主要条件，String方法中重写了 compareTo(方法)，返回值为int
	        // 年龄是次要条件，只是将语句的顺序颠倒一下
	        int num = this.name.compareTo(o.name);
	        return num == 0 ? this.age - o.age : num;

        1 升序排列 compareTo 中的代码是: int num = this.age - o.age
        2 降序排列 compareTo 中的代码是: int num = o.age - this.age

     */
}
