package alg_02_train_wyj._21_day_综合应用二;

import java.util.*;

/**
 * @Author Wuyj
 * @DateTime 2023-05-26 20:47
 * @Version 1.0
 */
public class _09_642_AutocompleteSystem1 {

    class SentenceInfo {
        String sentence;
        int time;

        public SentenceInfo(String sentence, int time) {
            this.sentence = sentence;
            this.time = time;
        }
    }

    class Node {
        Map<Character, Node> map;
        int times = 0;

        public Node() {
            map = new HashMap<>();
        }
    }

    private Node root;
    private String input = "";

    public _09_642_AutocompleteSystem1(String[] sentences, int[] times) {
        root = new Node();
        int n = sentences.length;
        for (int i = 0; i < n; i++) {
            insert(sentences[i], times[i]);
        }
    }

    public void insert(String input, int times) {
        Node cur = root;
        for (char c : input.toCharArray()) {
            if (!cur.map.containsKey(c)) {
                cur.map.put(c, new Node());
            }
            cur = cur.map.get(c);
        }
        cur.times += times;
    }

    public List<String> input(char c) {
        List<String> res = new ArrayList<>();
        if (c == '#') {
            insert(input, 1);
            input = "";
        } else {
            input += c;
            List<SentenceInfo> list = search(input);
            Collections.sort(list, (o1, o2) -> o1.time == o2.time ?
                    o1.sentence.compareTo(o2.sentence) :
                    o2.time - o1.time);

            // for test
//            for (SentenceInfo info : list) {
//                System.out.println("sentence = " + info.sentence);
//                System.out.println("time = " + info.time);
//            }

            for (int i = 0; i < Math.min(3, list.size()); i++) {
                res.add(list.get(i).sentence);
            }
        }
        return res;
    }

    public List<SentenceInfo> search(String input) {
        List<SentenceInfo> list = new ArrayList<>();
        Node cur = root;
        for (char c : input.toCharArray()) {
            if (!cur.map.containsKey(c)) {
                return list;
            }
            cur = cur.map.get(c);
        }

//        for (Map.Entry entry : cur.map.entrySet()) {
//            System.out.println("key = " + entry.getKey());
//            System.out.println("value = " + entry.getValue());
//        }

        dfs(cur, input, list);
        return list;
    }

    private void dfs(Node cur, String input, List<SentenceInfo> list) {
        if (cur.times > 0) {
            list.add(new SentenceInfo(input, cur.times));
        }
        for (char c : cur.map.keySet()) {
            dfs(cur.map.get(c), input + c, list);
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
