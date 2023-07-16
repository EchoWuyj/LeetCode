package alg_02_train_dm._10_day_栈和队列;

import java.util.ArrayDeque;
import java.util.Arrays;

/**
 * @Author Wuyj
 * @DateTime 2023-04-24 19:58
 * @Version 1.0
 */
public class _02_71_simplify_path {

     /*
        71 号算法题：简化路径
        给你一个字符串 path ，表示指向某一文件或目录的 Unix 风格 绝对路径 （以 '/' 开头），
        请你将其转化为更加简洁的规范路径。

        - 在 Unix 风格的文件系统中，一个点（.）表示当前目录本身；
        - 此外，两个点 （..） 表示将目录切换到上一级（指向父目录）
        - 两者都可以是复杂相对路径的组成部分
        - 任意多个连续的斜杠（即，'//'）都被视为单个斜杠 '/'
        - 对于此问题，任何其他格式的点（例如，'...'）均被视为文件/目录名称。
          => /home/./../cmd/path/i...

        请注意，返回的 规范路径 必须遵循下述格式：
        - 始终以斜杠 '/' 开头。
        - 两个目录名之间必须只有一个斜杠 '/' 。
        - 最后一个目录名（如果存在）不能 以 '/' 结尾。
        - 此外，路径仅包含从根目录到目标文件或目录的路径上的目录（即，不含 '.' 或 '..'）

        返回简化后得到的 规范路径 。

        KeyPoint 举例
        /home/ => /home  注意，最后一个目录名后面没有斜杠。
        /../   => /  从根目录向上一级是不可行的，因为根目录是你可以到达的最高级。
        /a/./b/../../c/ => /c

        示例 1：
        输入：path = "/home/"
        输出："/home"
        解释：注意，最后一个目录名后面没有斜杠。

        示例 2：
        输入：path = "/../"
        输出："/"
        解释：从根目录向上一级是不可行的，因为根目录是你可以到达的最高级。


        示例 3：
        输入：path = "/home//foo/"
        输出："/home/foo"
        解释：在规范路径中，多个连续斜杠需要用一个斜杠替换。

        提示：
        1 <= path.length <= 3000
        path 由英文字母，数字，'.'，'/' 或 '_' 组成。
        path 是一个有效的 Unix 风格绝对路径。

     */

    public static String simplifyPath(String path) {

        // path ="/a/./b/../../../c/"
        // => 根据 "/" 切分，处理切分后的目录
        // => 空 a . b .. .. .. c
        // => 最开始
        String[] dirs = path.split("/");
        System.out.println(Arrays.toString(dirs));
        ArrayDeque<String> stack = new ArrayDeque<>();

        for (String dir : dirs) {
            // 多种情况，使用 if 进行分支选择判断
            // KeyPoint String 不能使用 ==，而是使用 equals
            if (dir.equals("") || dir.equals(".")) {
                // 这种情况下，什么都不操作，不需要使用 continue 代码
                // 因为是 if else 分支，执行该分支之后，后续分支不会再执行
//                continue;
            } else if (dir.equals("..") && stack.isEmpty()) {
                // 已经到根目录，也是什么都不要做
//                continue;
            } else if (dir.equals("..") && !stack.isEmpty()) {
                // 弹出栈顶目录，进入前一个目录中
                // pop => 底层调用 removeFirst
                stack.pop();
            } else {
                // push => 底层调用 addFirst
                stack.push(dir);
            }
        }

        if (stack.isEmpty()) return "/";
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            // 每个目录进行拼接都是需要 "/"
            sb.append("/");
            // 因为 stack 是使用 ArrayDeque 实现，pop，push 都是从 First (栈顶) 进出
            // 考虑栈'先进后出'特点，故这里从'栈底'获取元素，而不是从'栈顶'获取元素
            sb.append(stack.removeLast());
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        simplifyPath("/a/./b/../../../c/"); // [, a, ., b, .., .., .., c]
        simplifyPath("/a/b/"); // [, a, b] 最后一个 / 后面空格不算在内的
        simplifyPath("/a/b"); // [, a, b]
    }
}
