package alg_01_ds_wyj._05_application._03_trie;

import java.util.*;

/**
 * @Author Wuyj
 * @DateTime 2023-03-27 14:55
 * @Version 1.0
 */
public class _642_AutocompleteSystem {

    class SentenceInfo {
        private String sentence;
        private int time;

        public SentenceInfo(String sentence, int time) {
            this.sentence = sentence;
            this.time = time;
        }
    }

    private Map<String, Integer> map;
    private String curSentence = "";

    public _642_AutocompleteSystem(String[] sentences, int[] times) {
        map = new HashMap<>();
        for (int i = 0; i < times.length; i++) {
            map.put(sentences[i], times[i]);
        }
    }

    public List<String> input(char c) {
        List<String> res = new ArrayList<>();
        if ('#' == c) {
            map.put(curSentence, map.getOrDefault(curSentence, 0) + 1);
            curSentence = "";
        } else {
            curSentence += c;
            List<SentenceInfo> list = new ArrayList<>();
            for (String sentence : map.keySet()) {
                if (sentence.startsWith(curSentence)) {
                    list.add(new SentenceInfo(sentence, map.get(sentence)));
                }
            }
            Collections.sort(list, (s1, s2) -> s1.time == s2.time ? s1.sentence.compareTo(s2.sentence) :
                    s2.time - s1.time);

            for (int i = 0; i < Math.min(3, list.size()); i++) {
                res.add(list.get(i).sentence);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        String[] sentences = {"i love you", "island", "ironman", "i love leetcode"};
        int[] times = {5, 3, 2, 2};
        _642_AutocompleteSystem test = new _642_AutocompleteSystem(sentences, times);
        System.out.println(test.input('i')); // [i love you, island, i love leetcode]
        System.out.println(test.input(' ')); // [i love you, i love leetcode]
        System.out.println(test.input('a')); // []
        System.out.println(test.input('#')); // []
    }
}
