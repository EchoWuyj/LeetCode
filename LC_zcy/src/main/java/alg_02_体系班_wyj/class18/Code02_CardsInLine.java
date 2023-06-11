package alg_02_体系班_wyj.class18;

/**
 * @Author Wuyj
 * @DateTime 2023-02-28 12:15
 * @Version 1.0
 */
public class Code02_CardsInLine {
    // KeyPoint 方法一
    public static int win1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int first = f1(arr, 0, arr.length - 1);
        int second = g1(arr, 0, arr.length - 1);
        return Math.max(first, second);
    }

    public static int f1(int[] arr, int l, int r) {
        if (l == r) {
            return arr[l];
        }
        int p1 = arr[l] + g1(arr, l + 1, r);
        int p2 = arr[r] + g1(arr, l, r - 1);
        return Math.max(p1, p2);
    }

    private static int g1(int[] arr, int l, int r) {
        if (l == r) {
            return 0;
        }
        int p1 = f1(arr, l + 1, r);
        int p2 = f1(arr, l, r - 1);
        return Math.min(p1, p2);
    }

    // KeyPoint 方法二
    public static int win2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int n = arr.length;
        int[][] fmap = new int[n][n];
        int[][] gmap = new int[n][n];

        // 表示没有计算过
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                fmap[i][j] = -1;
                gmap[i][j] = -1;
            }
        }
        int first = f2(arr, 0, n - 1, fmap, gmap);
        int second = g2(arr, 0, n - 1, fmap, gmap);
        return Math.max(first, second);
    }

    public static int f2(int[] arr, int l, int r, int[][] fmap, int[][] gmap) {
        if (fmap[l][r] != -1) {
            return fmap[l][r];
        }

        int ans = 0;
        if (l == r) {
            ans = arr[l];
        } else {
            int p1 = arr[l] + g2(arr, l + 1, r, fmap, gmap);
            int p2 = arr[r] + g2(arr, l, r - 1, fmap, gmap);
            ans = Math.max(p1, p2);
        }
        fmap[l][r] = ans;
        return ans;
    }

    public static int g2(int[] arr, int l, int r, int[][] fmap, int[][] gmap) {
        if (gmap[l][r] != -1) {
            return gmap[l][r];
        }
        int ans = 0;
        if (l == r) {
            ans = 0;
        } else {
            int p1 = f2(arr, l + 1, r, fmap, gmap);
            int p2 = f2(arr, l, r - 1, fmap, gmap);
            ans = Math.min(p1, p2);
        }
        gmap[l][r] = ans;
        return ans;
    }

    // KeyPoint 方法三
    public static int win3(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int N = arr.length;
        int[][] fmap = new int[N][N];
        int[][] gmap = new int[N][N];

        for (int i = 0; i < N; i++) {
            fmap[i][i] = arr[i];
        }

        for (int i = 1; i < N; i++) {
            int l = 0;
            int r = i;
            while (r < N) {
                fmap[l][r] = Math.max(arr[l] + gmap[l + 1][r], arr[r] + gmap[l][r - 1]);
                gmap[l][r] = Math.min(fmap[l + 1][r], fmap[l][r - 1]);
                l++;
                r++;
            }
        }
        return Math.max(fmap[0][N - 1], gmap[0][N - 1]);
    }

    public static void main(String[] args) {
        int[] arr = {5, 7, 4, 5, 8, 1, 6, 0, 3, 4, 6, 1, 7};
        System.out.println(win1(arr));
        System.out.println(win2(arr));
        System.out.println(win3(arr));
    }
}
