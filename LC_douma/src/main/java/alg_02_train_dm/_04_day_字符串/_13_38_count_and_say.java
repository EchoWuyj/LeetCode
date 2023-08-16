package alg_02_train_dm._04_day_字符串;

/**
 * @Author Wuyj
 * @DateTime 2023-08-16 12:15
 * @Version 1.0
 */
public class _13_38_count_and_say {
    public String countAndSay(int n) {
        StringBuilder curr = new StringBuilder("1");
        StringBuilder prev;

        for (int i = 1; i < n; i++) {
            prev = curr;
            curr = new StringBuilder();

            char say = prev.charAt(0);
            int count = 1;
            for (int j = 1; j < prev.length(); j++) {
                if (prev.charAt(j) == say) {
                    count++;
                } else {
                    curr.append(count).append(say);
                    say = prev.charAt(j);
                    count = 1;
                }
            }
            curr.append(count).append(say);
        }
        return curr.toString();
    }
}
