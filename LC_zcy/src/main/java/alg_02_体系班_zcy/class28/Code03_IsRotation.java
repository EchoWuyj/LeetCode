package alg_02_体系班_zcy.class28;

public class Code03_IsRotation {

    /*
		给定两个字符串,s和goal.如果在若干次旋转操作之后,s能变成goal,那么返回true
		s的旋转操作就是将s最左边的字符移动到最右边
		例如:若s='abcde',在旋转一次之后结果就是'bcdea'
     */

    public static boolean isRotation(String a, String b) {
        if (a == null || b == null || a.length() != b.length()) {
            return false;
        }
        String b2 = b + b;
        // 注意是不等关系
        return getIndexOf(b2, a) != -1;
        /*
          str1=123456,两个str1+str1拼接st1',
          st1'=123456123456,在str1'中任何长度为6字串都是str1的旋转串(穷举了所有旋转串)
          1 2 3 4 5 6 1 2 3 4 5 6
          i         j
            i         j
              i         j
                i         j
                  i         j
                    i         j
                      i         j
         */
    }

    // KMP Algorithm
    public static int getIndexOf(String s, String m) {
        if (s.length() < m.length()) {
            return -1;
        }
        char[] ss = s.toCharArray();
        char[] ms = m.toCharArray();
        int si = 0;
        int mi = 0;
        int[] next = getNextArray(ms);
        while (si < ss.length && mi < ms.length) {
            if (ss[si] == ms[mi]) {
                si++;
                mi++;
            } else if (next[mi] == -1) {
                si++;
            } else {
                mi = next[mi];
            }
        }
        return mi == ms.length ? si - mi : -1;
    }

    public static int[] getNextArray(char[] ms) {
        if (ms.length == 1) {
            return new int[]{-1};
        }
        int[] next = new int[ms.length];
        next[0] = -1;
        next[1] = 0;
        int pos = 2;
        int cn = 0;
        while (pos < next.length) {
            if (ms[pos - 1] == ms[cn]) {
                next[pos++] = ++cn;
            } else if (cn > 0) {
                cn = next[cn];
            } else {
                next[pos++] = 0;
            }
        }
        return next;
    }

    public static void main(String[] args) {
        String str1 = "yunzuocheng";
        String str2 = "zuochengyun";
        System.out.println(isRotation(str1, str2));
    }
}
