package alg_02_体系班_wyj.class19;

import com.sun.corba.se.impl.oa.poa.POAPolicyMediatorImpl_NR_USM;

import javax.script.ScriptContext;
import javax.sql.rowset.FilteredRowSet;
import java.util.HashMap;
import java.util.Locale;

/**
 * @Author Wuyj
 * @DateTime 2023-03-01 21:01
 * @Version 1.0
 */
public class Code03_StickersToSpellWord {
    public static int minStickers1(String[] stickers, String target) {
        int ans = process(stickers, target);
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    public static int process(String[] stickers, String target) {
        if (target.length() == 0) {
            return 0;
        }

        int min = Integer.MAX_VALUE;
        for (String first : stickers) {
            String rest = minus(target, first);
            if (rest.length() != target.length()) {
                min = Math.min(min, process(stickers, rest));
            }
        }
        return min + (min == Integer.MAX_VALUE ? 0 : 1);
    }

    public static String minus(String target, String source) {
        char[] t = target.toCharArray();
        char[] s = source.toCharArray();
        int[] counts = new int[26];
        for (char c : t) {
            counts[c - 'a']++;
        }
        for (char c : s) {
            counts[c - 'a']--;
        }
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < 26; i++) {
            if (counts[i] > 0) {
                for (int j = 0; j < counts[i]; j++) {
                    res.append((char) (i + 'a'));
                }
            }
        }
        return res.toString();
    }

    public static int minStickers2(String[] stickers, String target) {
        int n = stickers.length;
        int[][] scounts = new int[n][26];
        for (int i = 0; i < n; i++) {
            char[] str = stickers[i].toCharArray();
            for (char c : str) {
                scounts[i][c - 'a']++;
            }
        }

        int ans = process1(scounts, target);
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    public static int process1(int[][] stickers, String target) {
        if (target.length() == 0) {
            return 0;
        }
        char[] t = target.toCharArray();
        int[] tcounts = new int[26];
        for (char c : t) {
            tcounts[c - 'a']++;
        }
        int n = stickers.length;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            int[] sticker = stickers[i];
            if (sticker[t[0] - 'a'] > 0) {
                StringBuilder builder = new StringBuilder();
                for (int j = 0; j < 26; j++) {
                    if (tcounts[j] > 0) {
                        int nums = tcounts[j] - sticker[j];
                        for (int k = 0; k < nums; k++) {
                            builder.append((char) (j + 'a'));
                        }
                    }
                }
                String rest = builder.toString();
                min = Math.min(min, process1(stickers, rest));
            }
        }
        return min + (min == Integer.MAX_VALUE ? 0 : 1);
    }

    public static int minStickers3(String[] stickers, String target) {
        int n = stickers.length;
        int[][] scounts = new int[n][26];
        for (int i = 0; i < n; i++) {
            char[] str = stickers[i].toCharArray();
            for (char c : str) {
                scounts[i][c - 'a']++;
            }
        }
        HashMap<String, Integer> dp = new HashMap<>();
        dp.put("", 0);
        int ans = process2(scounts, target, dp);
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    public static int process2(int[][] scounts, String target, HashMap<String, Integer> dp) {
        if (dp.containsKey(target)) {
            return dp.get(target);
        }
        char[] t = target.toCharArray();
        int[] tcounts = new int[26];
        for (char c : t) {
            tcounts[c - 'a']++;
        }

        int n = scounts.length;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            int[] stickerCounts = scounts[i];
            if (stickerCounts[t[0] - 'a'] > 0) {
                StringBuilder builder = new StringBuilder();
                for (int j = 0; j < 26; j++) {
                    if (tcounts[j] > 0) {
                        int nums = tcounts[j] - stickerCounts[j];
                        for (int k = 0; k < nums; k++) {
                            builder.append((char) (j + 'a'));
                        }
                    }
                }
                String rest = builder.toString();
                min = Math.min(min, process2(scounts, rest, dp));
            }
        }

        int ans = min + (min == Integer.MAX_VALUE ? 0 : 1);
        dp.put(target, ans);
        return ans;
    }
}
