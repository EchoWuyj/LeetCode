package alg_01_ds_wyj._01_line._01_array;

/**
 * @Author Wuyj
 * @DateTime 2023-03-09 13:40
 * @Version 1.0
 */
public class ArrayUtils {

    /**
     * 将指定的元素插入到指定数组的指定位置上
     *
     * @param src
     * @param index
     * @param element
     * @return
     */
    public static int[] insertElement(int[] src, int index, int element) {
        int length = src.length;
        int[] dest = new int[length + 1];

        for (int i = 0; i < index; i++) {
            dest[i] = src[i];
        }

        dest[index] = element;

        for (int i = index; i < length; i++) {
            dest[i + 1] = src[i];
        }
        return dest;
    }

    public static int[] removeElement(int[] src, int index) {
        int[] dest = new int[src.length - 1];
        for (int i = 0; i < index; i++) {
            dest[i] = src[i];
        }
        for (int i = index; i < src.length - 1; i++) {
            dest[i] = src[i + 1];
        }
        return dest;
    }

    public static boolean isEmpty(int[] arr) {
        for (int num : arr) {
            if (num != 0) {
                return false;
            }
        }
        return true;
    }

    public static int getSize(int[] arr) {
        int size = 0;
        for (int num : arr) {
            if (num != 0) {
                size++;
            }
        }
        return size;
    }

    public static boolean contains(int[] arr, int target) {
        for (int num : arr) {
            if (num == target) {
                return true;
            }
        }
        return false;
    }
}
