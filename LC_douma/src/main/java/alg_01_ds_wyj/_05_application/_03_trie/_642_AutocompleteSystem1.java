package alg_01_ds_wyj._05_application._03_trie;

import java.util.*;

/**
 * @Author Wuyj
 * @DateTime 2023-03-27 15:40
 * @Version 1.0
 */
public class _642_AutocompleteSystem1 {
    class SentenceInfo {
        String sentence;
        int time;

        SentenceInfo(String sentence, int time) {
            this.sentence = sentence;
            this.time = time;
        }
    }

    class TrieNode {
        Map<Character, TrieNode> children;
        int time;

        public TrieNode() {
            this.children = new HashMap<>();
            this.time = 0;
        }
    }

    private TrieNode root;
    private String curSentence = "";

    public _642_AutocompleteSystem1(String[] sentences, int[] times) {
        root = new TrieNode();
        for (int i = 0; i < times.length; i++) {
            insert(sentences[i], times[i]);
        }
    }

    public void insert(String s, int times) {
        TrieNode cur = root;
        for (Character c : s.toCharArray()) {
            if (!cur.children.containsKey(c)) {
                cur.children.put(c, new TrieNode());
            }
            cur = cur.children.get(c);
        }
        cur.time += times;
    }

    public List<String> input(char c) {
        List<String> res = new ArrayList<>();
        if ('#' == c) {
            insert(curSentence, 1);
            curSentence = "";
        } else {
            curSentence += c;
            List<SentenceInfo> list = search(curSentence);
            Collections.sort(list, (s1, s2) -> s1.time == s2.time ? s1.sentence.compareTo(s2.sentence) :
                    s2.time - s1.time);
            for (int i = 0; i < Math.min(3, list.size()); i++) {
                res.add(list.get(i).sentence);
            }
        }
        return res;
    }

    public List<SentenceInfo> search(String s) {
        List<SentenceInfo> list = new ArrayList<>();
        TrieNode cur = root;
        for (Character c : s.toCharArray()) {
            if (!cur.children.containsKey(c)) {
                return list;
            }
            cur = cur.children.get(c);
        }
        dfs(cur, s, list);
        return list;
    }

    public void dfs(TrieNode cur, String str, List<SentenceInfo> list) {
        if (cur.time > 0) {
            list.add(new SentenceInfo(str, cur.time));
        }
        for (char c : cur.children.keySet()) {
            dfs(cur.children.get(c), str + c, list);
        }
    }

    public static void main(String[] args) {
        String[] sentences = {"i love you", "island", "ironman", "i love leetcode"};
        int[] times = {5, 3, 2, 2};
        _642_AutocompleteSystem1 test = new _642_AutocompleteSystem1(sentences, times);
        System.out.println(test.input('i')); // [i love you, island, i love leetcode]
        System.out.println(test.input(' ')); // [i love you, i love leetcode]
        System.out.println(test.input('a')); // []
        System.out.println(test.input('#')); // []
    }
}
