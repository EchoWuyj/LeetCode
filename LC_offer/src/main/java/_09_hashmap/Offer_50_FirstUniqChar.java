package _09_hashmap;

import java.util.HashMap;

/**
 * @Author Wuyj
 * @DateTime 2022-08-27 14:43
 * @Version 1.0
 */
public class Offer_50_FirstUniqChar {
    public char firstUniqChar(String s) {
        // 设置哈希表用来记录字符串中每个字符是否只出现过一次
        // key 为字符
        // value 为 boolean 类型
        // true 代表当前遍历的字符中，该字符出现了一次
        // false 代表当前遍历的字符中，该字符出现了两次或两次以上

        // KeyPoint 集合中不能装入基本数据类型,需要使用基本数据类型的包装类
        HashMap<Character, Boolean> dic = new HashMap<>();

        // 首先将字符串转换为字符数组的形式，方便遍历
        char[] charArray = s.toCharArray();

        // 通过两次循环实现
        // 第一次循环对字符进行赋值true false
        for (char c : charArray) {

            // 1、首先以该字符作为 key ，在字典中进行查找是否存在
            // KeyPoint 只是单纯判断是否存在key值
            boolean result = dic.containsKey(c);

            // 2、如果该字符在字典中已经存在
            // 那么说明当前遍历的字符中，该字符出现了两次或两次以上
            if (result) {
                dic.put(c, false);
            } else {
                dic.put(c, true);
            }
        }

        // 第二次循环判断字符的 true false
        for (char c : charArray) {

            // 以该字符作为 key ，在字典中进行查找对应的 value
            Boolean result = dic.get(c);

            // 如果发现 value 值为 true
            // 那么说明找到了第一个只出现一次的字符
            if (result) {
                // 返回这个字符
                return c;
            }
        }

        // 否则说明所有字符都出现了两次或两次以上，或者字符串为空，那么返回 ' '
        return ' ';
    }
}
