package algorithm._03_string;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;

/**
 * @Author Wuyj
 * @DateTime 2022-03-03 12:59
 * @Version 1.0
 */
public class LeetCode_316_RemoveDuplicateLetters {
    // 方法一：暴力法,贪心策略递归
    // 基本思路：
    //  (1) 我们每次都找到当前能够移到最左边的且最小的字母(判断条件:该字母之前的所有字符,在其后面都重复出现)
    //  这就是我们得到结果的第一个字母,它之前的所有重复字母会被删掉；
    //  (2) 然后我们从它以后的字符串中,使用相同的逻辑,继续寻找第二个最小的字母
    //  (3) 所以,我们在代码实现上,可以使用递归
    public String removeDuplicateLetters01(String s) {
        // 递归的基准情形
        if (s.length() == 0) {
            return "";
        }

        // 希望找到当前最左侧的字母,位置记为position
        int position = 0;

        // 遍历字符串
        for (int i = 0; i < s.length(); i++) {
            // 只有当前字母比已经找到的position位置的字母要小,才有资格继续判断
            if (s.charAt(i) < s.charAt(position)) {

                // 定义一个布尔变量,表示当前i位置的字母是否可以替换position位置的字母
                // KeyPoint 表示该字符之前的所有字符在其后面都重复出现,即在该字符之前的元素都是可以删除的
                //  该字符可以移动到最左边
                boolean isReplaceable = true;

                // KeyPoint 判断该字母之前的所有字符是否在其后面都重复出现

                // 遍历i之前的所有字母,判断是否在i后面重复出现
                // 不一定得从0开始遍历,可以从position位置开始遍历
                /*  指针    p   i
                    元素  c b c a b
                  position之前的元素c会在b之后重复出现
                  即元素c不是在a之前出现,就是在a之后出现
                  故是可以直接从position位置开始的遍历,提高执行效率
                 */
                for (int j = position; j < i; j++) {
                    // 定义一个布尔变量,表示j位置的字母是否重复出现
                    boolean isDuplicated = false;
                    // 遍历i后面的所有字母,看j位置的字母是否重复出现
                    for (int k = i + 1; k < s.length(); k++) {
                        if (s.charAt(k) == s.charAt(j)) {
                            isDuplicated = true;
                            break;
                        }
                    }
                    // 如果任一字母不重复出现,就不能替换当前position,后面的字母不用判断
                    if (!isDuplicated) {
                        isReplaceable = false;
                        break;
                    }
                }
                if (isReplaceable) position = i;
            }
        }

        // 遍历结束,position位置的字母就是结果中最左侧的元素

        /* substring()用法:
          根据指定的索引范围获取子字符串 abcdefg,包含开始不包含结束,输出的结果为字符的个数
          System.out.println(s.substring(3, 6)); abcdefg 6-3=3 从d开始后面取3位为def
         */

        // s.substring(position + 1)：将position和之前的元素都删除
        // replaceAll(s.charAt(position) + "", "")：将后续字符串中出现的元素替换成空
        return s.charAt(position) + removeDuplicateLetters01(s.substring(position + 1)
                .replaceAll(s.charAt(position) + "", ""));
    }

    // 方法二:贪心策略改进
    public String removeDuplicateLetters02(String s) {
        // 递归的基准情形
        if (s.length() == 0) return "";

        // 希望找到当前最左侧的字母,位置记为position
        int position = 0;
        // 定义一个count数组,保存所有26个字母在字符串中出现的频次
        int[] count = new int[26];
        for (int i = 0; i < s.length(); i++) {
            // 字母a保存在数组索引为0的位置,字母b保存在数组索引为1的位置
            // 同时,count[0]保存a的个数,count[1]保存b的个数
            count[s.charAt(i) - 'a']++;
        }

        // 遍历字符串,找到当前最左端字母
        for (int i = 0; i < s.length(); i++) {
            // KeyPoint 两个if之间是由先后顺序的,当前count自减之后为0,就已经break退出了
            //  若当前字符charAt(i)为最小的字符,则当前字符charAt(i)没有来及替换position

            // KeyPoint 这里有个循环遍历的过程
            // 把当前字符和position位置比较,如果更小就替换
            if (s.charAt(i) < s.charAt(position)) {
                position = i;
            }

            // 每遇到一个字符,count值就要减1,先去自减--
            // 如果遇到count减为0,就直接退出,以当前最小的字母作为最左端字符
            if (--count[s.charAt(i) - 'a'] == 0) {
                break;
            }
        }
        return s.charAt(position) +
                removeDuplicateLetters02(s.substring(position + 1)
                        .replaceAll(s.charAt(position) + "", ""));
    }

    // 方法三:使用栈进行优化
    public String removeDuplicateLetters03(String s) {
        // 定义一个字符栈,保存去重之后的结果
        Stack<Character> stack = new Stack<>();

        // 为了快速判断一个字符是否在栈中出现过,用一个Set来保存元素是否出现
        // 若重复出现过,则不用进栈,因为保存就是去重之后的结果
        HashSet<Character> seenSet = new HashSet<>();

        // 判断出栈时判断一个字符是否在某个位置之后重复出现
        // 用一个HashMap来保存字母出现在字符串的最后位置
        // 通过最后一次出现的位置和当前字符进行比较,是否在其后面
        HashMap<Character, Integer> lastOccur = new HashMap<>();

        // 遍历字符串,将最后一次出现的位置保存进map
        for (int i = 0; i < s.length(); i++) {
            // 遍历过程中每次重复的字符对应的key都会进行覆盖,从而保证是最后一次的位置
            lastOccur.put(s.charAt(i), i);
        }

        // 遍历字符串,判断每个字符是否需要入栈
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            // 只有在c没有出现过的情况下,才判断是否入栈
            if (!seenSet.contains(c)) {
                // c入栈之前,要判断之前栈顶元素,是否在后面会重复出现,如果重复出现就可以删除
                // 栈不为空,当前字符小于栈顶元素,栈顶元素最后一次出现的位置大于i的位置
                // 栈顶元素判断不是只是判断一次就行了,需要进行重复判断,所以使用while而不是使用if
                while (!stack.isEmpty() && c < stack.peek() && lastOccur.get(stack.peek()) > i) {
                    // 结果栈seenSet需要其删除
                    seenSet.remove(stack.pop());
                }
                stack.push(c);
                seenSet.add(c);
            }
        }
        // 将栈中的元素保存成字符串输出
        StringBuffer stringBuffer = new StringBuffer();
        for (Character character : stack) {
            // 原来是Character,在stringBuffer添加字符时需要转成基本数据类型
            stringBuffer.append(character.charValue());
        }

        return stringBuffer.toString();
    }

    public static void main(String[] args) {
        String str1 = "bcabc";
        String str2 = "cbacdcbc";
        LeetCode_316_RemoveDuplicateLetters removeDuplicateLetters = new LeetCode_316_RemoveDuplicateLetters();

        System.out.println(removeDuplicateLetters.removeDuplicateLetters02(str1));
        System.out.println(removeDuplicateLetters.removeDuplicateLetters02(str2));
    }
}
