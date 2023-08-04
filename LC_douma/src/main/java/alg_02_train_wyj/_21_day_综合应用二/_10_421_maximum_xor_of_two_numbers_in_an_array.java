package alg_02_train_wyj._21_day_综合应用二;

/**
 * @Author Wuyj
 * @DateTime 2023-05-26 21:01
 * @Version 1.0
 */
public class _10_421_maximum_xor_of_two_numbers_in_an_array {
    public static int findMaximumXOR1(int[] nums) {
        int n = nums.length;
        if (n < 2) return 0;
        int maxValue = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                maxValue = Math.max(maxValue, nums[i] ^ nums[j]);
            }
        }
        return maxValue;
    }

    public static void main(String[] args) {
        System.out.println(findMaximumXOR1(new int[]{0}));
    }

    public int findMaximumXOR2(int[] nums) {
        int n = nums.length;
        if (n < 2) return 0;
        Trie trie = new Trie();
        int maxValue = Integer.MIN_VALUE;
        for (int i = 1; i < n; i++) {
            trie.add(nums[i - 1]);
            maxValue = Math.max(maxValue, trie.search(nums[i]));
        }
        return maxValue;
    }

    class Trie {
        private class Node {
            Node zero;
            Node one;

            public Node() {

            }
        }

        private Node root;

        public Trie() {
            root = new Node();
        }

        public void add(int num) {
            Node cur = root;
            for (int i = 30; i >= 0; i--) {
                int bit = (num >> i) & 1;
                if (bit == 0) {
                    if (cur.zero == null) cur.zero = new Node();
                    cur = cur.zero;
                } else {
                    if (cur.one == null) cur.one = new Node();
                    cur = cur.one;
                }
            }
        }

        public int search(int num) {
            Node cur = root;
            int x = 0;
            for (int i = 30; i >= 0; i--) {
                int bit = (num >> i) & 1;
                if (bit == 0) {
                    if (cur.one != null) {
                        cur = cur.one;
                        x = x * 2 + 1;
                    } else {
                        cur = cur.zero;
                        x = x * 2;
                    }
                } else { // bit == 1
                    if (cur.zero != null) {
                        cur = cur.zero;
                        x = x * 2 + 1;
                    } else {
                        cur = cur.one;
                        x = x * 2;
                    }
                }
            }
            return x;
        }
    }
}
