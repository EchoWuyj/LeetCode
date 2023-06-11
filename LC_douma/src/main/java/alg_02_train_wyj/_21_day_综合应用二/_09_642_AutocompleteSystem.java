package alg_02_train_wyj._21_day_综合应用二;

import java.util.*;

/**
 * @Author Wuyj
 * @DateTime 2023-05-26 20:47
 * @Version 1.0
 */
public class _09_642_AutocompleteSystem {

    class SentenceInfo {
        String content;
        int time;

        public SentenceInfo(String content, int time) {
            this.content = content;
            this.time = time;
        }
    }

    Map<String, Integer> map;
    StringBuilder curSentence;

    public _09_642_AutocompleteSystem(String[] sentences, int[] times) {
        map = new HashMap<>();
        curSentence = new StringBuilder();
        int n = sentences.length;
        for (int i = 0; i < n; i++) {
            map.put(sentences[i], times[i]);
        }
    }

    public List<String> input(char c) {
        ArrayList<String> res = new ArrayList<>();

        if (c == '#') {
            String curSentenceStr = curSentence.toString();
            map.put(curSentenceStr, map.getOrDefault(curSentenceStr, 0) + 1);
        } else {
            curSentence.append(c);
            ArrayList<SentenceInfo> list = new ArrayList<>();
            for (String sentence : map.keySet()) {
                if (sentence.startsWith(curSentence.toString())) {
                    list.add(new SentenceInfo(sentence, map.get(sentence)));
                }
            }
            Collections.sort(list, (o1, o2) ->
                    o1.time == o2.time ? o1.content.compareTo(o2.content) : o2.time - o1.time);

            for (int i = 0; i < Math.min(3, list.size()); i++) {
                res.add(list.get(i).content);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        String[] sentences = {"i love you", "island", "ironman", "i love leetcode"};
        int[] times = {5, 3, 2, 2};
        _09_642_AutocompleteSystem test = new _09_642_AutocompleteSystem(sentences, times);
        System.out.println(test.input('i')); // [i love you, island, i love leetcode]
        System.out.println(test.input(' ')); // [i love you, i love leetcode]
        System.out.println(test.input('a')); // []
        System.out.println(test.input('#')); // []
    }
}
