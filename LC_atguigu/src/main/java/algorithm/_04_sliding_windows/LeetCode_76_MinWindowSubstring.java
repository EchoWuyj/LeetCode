package algorithm._04_sliding_windows;

import java.util.HashMap;

/**
 * @Author Wuyj
 * @DateTime 2022-03-11 23:47
 * @Version 1.0
 */
public class LeetCode_76_MinWindowSubstring {
    // 方法一:暴力法,枚举s中所有子串
    // 基本思路：
    //  先不管最小和覆盖,先得保证是s的字串,将s里面所有的字串都列出来,再去和t进行比对
    //  是否覆盖t中所有字符,再去从中找到符合条件的最小的
    public String minWindow01(String s, String t) {
        // 定义最小子串,保存结果,初始为空字符串
        String minSubString = "";
        // 定义一个HashMap,保存t中字符出现的频次
        HashMap<Character, Integer> tCharFrequency = new HashMap<>();

        // 统计t中字符频次
        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            // 如果有,则使用当前值,如果没有,则使用默认值0
            int count = tCharFrequency.getOrDefault(c, 0);
            // 并且将重复的字符串数量加1
            tCharFrequency.put(c, count + 1);
        }

        // 接下来在s中搜索覆盖子串
        // 遍历所有字符,作为当前子串的起始位置
        for (int i = 0; i < s.length(); i++) {
            // 遍历i之后不小于t长度的位置,作为子串的结束位置,保证索引间的距离为t.length()即可
            // 当前字串的起始位置是包含的,将结束位置定义成不包含,相当于是前闭后开的字串截取
            // 因为结束位置没有包含在里面,所以j<=s.length(),从而保证子串能到覆盖到末尾
            for (int j = i + t.length(); j <= s.length(); j++) {
                // 统计s子串中每个字符出现的频次
                // 定义一个HashMap,保存s子串中字符出现的频次
                HashMap<Character, Integer> subStrCharFrequency = new HashMap<>();

                // 统计子串中字符频次,从起始点i开始,到终止点j结束
                for (int k = i; k < j; k++) {
                    // KeyPoint for循环中一定需要明确循环变量
                    //  这里循环变量是k而不是i
                    char c = s.charAt(k);
                    int count = subStrCharFrequency.getOrDefault(c, 0);
                    subStrCharFrequency.put(c, count + 1);
                }

                // 对比当前字串所有count值和tCharFrequency所有count值进行比较判断
                // 若当前字串中每个字符的频次都大于等于tCharFrequency中每个字符的频次
                // 则表示当前统计的字串是符合要求的覆盖字串

                // 如果当前子串符合覆盖子串的要求,并且比之前的最小子串要短,就替换,同时本身最小字串就是空的话,也是可以进行直接替换的
                if (check(tCharFrequency, subStrCharFrequency) && (minSubString.equals("") || j - i < minSubString.length())) {
                    // 使用substring进行截取时,本身就是包括i位置不包括j位置
                    minSubString = s.substring(i, j);
                }
            }
        }
        return minSubString;
    }

    // 提炼一个方法,用于检查当前子串是否是一个覆盖t的子串
    public boolean check(HashMap<Character, Integer> tFreq, HashMap<Character, Integer> subStrFreq) {
        // 遍历t中每个字符的频次,与subStr进行比较
        for (Character c : tFreq.keySet()) {
            // t中的字符可以在s中没有,故使用getOrDefault方法
            if (subStrFreq.getOrDefault(c, 0) < tFreq.get(c)) {
                return false;
            }
        }
        return true;
    }

    // 方法二:滑动窗口
    // 基本思路:起始和结束位置轮流往前滑动的过程
    //  一开始往后找时,假如当前不是覆盖字串的话,当前的结束位置从符合t.length()长度开始一直往后扩,直到找个覆盖字串
    //  找到之后,再将起始的位置往后缩小,在当前覆盖字串的基础上找到一个局部的最小覆盖字串(局部最优解),再缩小就不是
    //  覆盖字串后,再去将结束位置往后扩
    public String minWindow02(String s, String t) {
        // 定义最小子串,保存结果,初始为空字符串
        String minSubString = "";

        // 定义一个HashMap,保存t中字符出现的频次
        HashMap<Character, Integer> tCharFrequency = new HashMap<>();

        // 统计t中字符频次
        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            int count = tCharFrequency.getOrDefault(c, 0);
            tCharFrequency.put(c, count + 1);
        }

        // KeyPoint 使用双指针少用了for循环的遍历
        // 定义左右指针,指向滑动窗口的起始和结束位置
        // 此时还是左闭右开,即起始位置是包含的结束位置是不包含的
        int start = 0, end = t.length();
        // end指针不停地往后移动,移动不能超出s.length().因为是不包含,所以可以等于s.length()
        while (end <= s.length()) {
            // 定义一个HashMap,保存s子串中字符出现的频次
            HashMap<Character, Integer> subStrCharFrequency = new HashMap<>();

            // 统计子串中字符频次
            for (int k = start; k < end; k++) {
                char c = s.charAt(k);
                int count = subStrCharFrequency.getOrDefault(c, 0);
                subStrCharFrequency.put(c, count + 1);
            }

            // 如果当前子串符合覆盖子串的要求,并且比之前的最小子串要短,就替换
            if (check(tCharFrequency, subStrCharFrequency)) {
                if (minSubString.equals("") || end - start < minSubString.length()) {
                    minSubString = s.substring(start, end);
                }
                // 只要是覆盖子串,就移动初始位置,缩小窗口,寻找当前的局部最优解
                start++;
            } else {
                // 如果不是覆盖子串,需要扩大窗口,继续寻找新的子串
                end++;
            }
        }
        return minSubString;
    }

    // 方法三:滑动窗口优化
    public String minWindow03(String s, String t) {
        // 定义最小子串,保存结果,初始为空字符串
        String minSubString = "";

        // 定义一个HashMap,保存t中字符出现的频次
        HashMap<Character, Integer> tCharFrequency = new HashMap<>();

        // 统计t中字符频次
        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            int count = tCharFrequency.getOrDefault(c, 0);
            tCharFrequency.put(c, count + 1);
        }

        // KeyPoint 这里是没有完整统计字串中频次,若还是从t.length()开始,则初始化时还得
        //  从0到t.length()的字串里面字符出现的频次先去统计出来,然后再去做操作,这样不好
        // 定义左右指针,指向滑动窗口的起始和结束位置
        int start = 0, end = 1;

        // KeyPoint 在窗口中增一个字符和减一个字符,没有必要针对每个字串再去定义一个HashMap
        //  现在只是在原先的HashMap上做一个更改
        // 定义一个HashMap,保存s子串中字符出现的频次
        HashMap<Character, Integer> subStrCharFrequency = new HashMap<>();

        while (end <= s.length()) {

            // end增加之后,新增的字符,因为end是不包含的,所以需要进行end-1
            char newChar = s.charAt(end - 1);

            // 新增字符频次加1,没有必要再去一个一个统计子串中字符频次
            // 只是判断在t中的字符,没在t中且在s中的字符没有影响
            if (tCharFrequency.containsKey(newChar)) {
                subStrCharFrequency.put(newChar, subStrCharFrequency.getOrDefault(newChar, 0) + 1);
            }

            // 如果当前子串符合覆盖子串的要求,并且比之前的最小子串要短,就替换
            // KeyPoint 将原来的if修改成while,当前字串是一致符合覆盖字串的要求,则需要使得start不停地前进
            //  同时增加start<end保证start和end不重叠
            while (check(tCharFrequency, subStrCharFrequency) && start < end) {
                if (minSubString.equals("") || end - start < minSubString.length()) {
                    minSubString = s.substring(start, end);
                }

                // 对要删除的字符,频次减1
                char removedChar = s.charAt(start);

                if (tCharFrequency.containsKey(removedChar)) {
                    subStrCharFrequency.put(removedChar, subStrCharFrequency.getOrDefault(removedChar, 0) - 1);
                }

                // 只要是覆盖子串,就移动初始位置,缩小窗口,寻找当前的局部最优解
                start++;
            }
            // 如果不是覆盖子串,需要扩大窗口,继续寻找新的子串
            end++;
        }

        return minSubString;
    }

    // 方法四:进一步优化
    public String minWindow04(String s, String t) {
        // 定义最小子串,保存结果,初始为空字符串
        String minSubString = "";

        // 定义一个HashMap,保存t中字符出现的频次
        HashMap<Character, Integer> tCharFrequency = new HashMap<>();

        // 统计t中字符频次
        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            int count = tCharFrequency.getOrDefault(c, 0);
            tCharFrequency.put(c, count + 1);
        }

        // 定义左右指针,指向滑动窗口的起始和结束位置
        int start = 0, end = 1;

        // 定义一个HashMap,保存s子串中字符出现的频次
        HashMap<Character, Integer> subStrCharFrequency = new HashMap<>();

        // KeyPoint 定义一个“子串贡献值”变量,统计t中的字符在子串中出现了多少
        int charCount = 0;

        while (end <= s.length()) {

            // end增加之后,新增的字符
            char newChar = s.charAt(end - 1);

            // 新增字符频次加1
            if (tCharFrequency.containsKey(newChar)) {
                subStrCharFrequency.put(newChar, subStrCharFrequency.getOrDefault(newChar, 0) + 1);

                // KeyPoint 如果子串中频次小于t中频次,当前字符就有贡献
                //  如果大于等于t中频次,当前字符就是没有贡献了
                if (subStrCharFrequency.get(newChar) <= tCharFrequency.get(newChar))
                    charCount ++;
            }

            // 如果当前子串符合覆盖子串的要求,并且比之前的最小子串要短,就替换
            // KeyPoint 这里不再调用check()方法,通过charCount == t.length(),通过这样的方式判断其实覆盖字串
            while ( charCount == t.length() && start < end) {
                if (minSubString.equals("") || end - start < minSubString.length()) {
                    minSubString = s.substring(start, end);
                }

                // 对要删除的字符,频次减1
                char removedChar = s.charAt(start);

                if (tCharFrequency.containsKey(removedChar)) {
                    subStrCharFrequency.put(removedChar, subStrCharFrequency.getOrDefault(removedChar, 0) - 1);

                    // KeyPoint 如果子串中的频次如果不够t中的频次,贡献值减少
                    if (subStrCharFrequency.get(removedChar) < tCharFrequency.get(removedChar))
                        charCount --;
                }

                // 只要是覆盖子串,就移动初始位置,缩小窗口,寻找当前的局部最优解
                start ++;
            }
            // 如果不是覆盖子串,需要扩大窗口,继续寻找新的子串
            end ++;
        }
        return minSubString;
    }

    public static void main(String[] args) {
        String s = "ADOBECODEBANC";
        String t = "ABC";
        LeetCode_76_MinWindowSubstring minWindowSubstring = new LeetCode_76_MinWindowSubstring();
        System.out.println(minWindowSubstring.minWindow02(s, t));
    }
}
