package alg_01_ds_dm._05_application._02_string_matching;

/**
 * @Author Wuyj
 * @DateTime 2023-03-29 16:05
 * @Version 1.0
 */
public class Test {
    public static void main(String[] args) {
        String mainStr = "douma, shake your code"; // 主串
        String patternStr = "your"; // 模式串

        // 在主串查找模式串的
        int index = mainStr.indexOf(patternStr); // 13
        System.out.println(index);

        // 字符串匹配算法包括：BF、RK、BM、KMP
        // 文本文档的搜索功能，具体使用那种字符串匹配算法，后面会进步讲
    }
}
