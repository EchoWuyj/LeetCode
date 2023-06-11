package alg_03_leetcode_top_wyj.class_09;

import com.sun.org.apache.xerces.internal.util.EntityResolverWrapper;
import com.sun.org.apache.xml.internal.security.signature.reference.ReferenceSubTreeData;
import sun.applet.Main;

/**
 * @Author Wuyj
 * @DateTime 2023-02-25 20:45
 * @Version 1.0
 */
public class Problem_0069_SqrtX {
    public static int mySqrt(int x) {
        if (x == 0) {
            return 0;
        }

        if (x <= 3) {
            return 1;
        }

        long l = 1;
        long r = x;
        long m = 0;
        long res = 1;

        while (l <= r) {
            m = (l + r) / 2;
            if (m * m <= x) {
                res = m;
                l = m + 1;
            } else {
                r = m - 1;
            }
        }
        return (int) res;
    }
}

