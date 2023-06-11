package alg_02_train_wyj._21_day_综合应用二;

import java.util.*;

/**
 * @Author Wuyj
 * @DateTime 2023-05-26 20:47
 * @Version 1.0
 */
public class _09_642_AutocompleteSystem1 {

    class SentenceInfo {
        String content;
        int time;

        public SentenceInfo(String content, int time) {
            this.content = content;
            this.time = time;
        }
    }

    class TrieNode {
        Map<Character, TrieNode> map;
        int time = 0;

        public TrieNode() {
            map = new HashMap<>();
        }
    }

    TrieNode root;
    String curSentence = "";

    public _09_642_AutocompleteSystem1(String[] sentences, int[] times) {
        root = new TrieNode();
        int n = sentences.length;
        for (int i = 0; i < n; i++) {
            insert(sentences[i], times[i]);
        }
    }

    public void insert(String str, int time) {
        TrieNode cur = root;
        for (char c : str.toCharArray()) {
            if (!cur.map.containsKey(c)) {
                cur.map.put(c, new TrieNode());
            }
            cur = cur.map.get(c);
        }
        cur.time += time;
    }

    public List<String> input(char c) {
        ArrayList<String> res = new ArrayList<>();
        if (c == '#') {
            insert(curSentence, 1);
            curSentence = "";
        } else {
            curSentence += c;
            ArrayList<SentenceInfo> list = search(curSentence);

            Collections.sort(list,
                    (o1, o2) -> o1.time == o2.time ?
                            o1.content.compareTo(o2.content) : o2.time - o1.time);

            for (int i = 0; i < Math.min(3, list.size()); i++) {
                res.add(list.get(i).content);
            }
        }
        return res;
    }

    public ArrayList<SentenceInfo> search(String str) {
        ArrayList<SentenceInfo> list = new ArrayList<>();
        TrieNode cur = root;
        for (char c : str.toCharArray()) {
            if (!cur.map.containsKey(c)) {
                return list;
            }
            cur = cur.map.get(c);
        }
        dfs(cur, str, list);
        return list;
    }

    public void dfs(TrieNode cur, String res, ArrayList<SentenceInfo> list) {
        if (cur.time > 0) {
            list.add(new SentenceInfo(res, cur.time));
        }

        for (char c : cur.map.keySet()) {
            dfs(cur.map.get(c), res + c, list);
        }
    }

    public static void main(String[] args) {
        String[] sentences = {"i love you", "island", "ironman", "i love leetcode"};
        int[] times = {5, 3, 2, 2};
        _09_642_AutocompleteSystem1 test = new _09_642_AutocompleteSystem1(sentences, times);
        System.out.println(test.input('i')); // [i love you, island, i love leetcode]
        System.out.println(test.input(' ')); // [i love you, i love leetcode]
        System.out.println(test.input('a')); // []
        System.out.println(test.input('#')); // []
    }
}
