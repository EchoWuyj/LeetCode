package alg_02_train_wyj._10_day_栈和队列;

import java.util.ArrayDeque;

/**
 * @Author Wuyj
 * @DateTime 2023-04-24 23:25
 * @Version 1.0
 */
public class _02_71_simplify_path {
    public String simplifyPath(String path) {
        String[] dirs = path.split("/");
        ArrayDeque<String> stack = new ArrayDeque<>();
        for (String dir : dirs) {
            if (dir.equals("") || dir.equals(".")) {
            } else if (dir.equals("..") && stack.isEmpty()) {
            } else if (dir.equals("..") && !stack.isEmpty()) {
                stack.pop();
            } else {
                stack.push(dir);
            }
        }

        if (stack.isEmpty()) return "/";
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append("/");
            sb.append(stack.removeLast());
        }
        return sb.toString();
    }
}
