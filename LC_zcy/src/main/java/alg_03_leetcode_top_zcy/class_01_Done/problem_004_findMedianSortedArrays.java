package alg_03_leetcode_top_zcy.class_01_Done;

/**
 * @Author Wuyj
 * @DateTime 2023-02-13 14:00
 * @Version 1.0
 */

// 寻找两个正序数组的中位数
//  => 给定两个大小分别为 m 和 n 的正序(从小到大)数组nums1和nums2,请你找出并返回这两个正序数组的中位数
public class problem_004_findMedianSortedArrays {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {

        // 本题需要用到两个算法原型,在原型的基础上来解决该问题
        // 1)算法原型一
        //   假设我们有一个int get(arr1,arr2,int k)函数,arr1有序,arr2有序,可以不等长,
        //   返回第k(k是从1开始的)大的数,那求两个正序数组的中位数,我们就可以借助get来完成
        //
        // 2)算法模型二
        //   为了实现算法原型一,我们需要另外一个getUpMedian(arr1,arr2)来辅助
        //   要求arr1和arr2长度一致且有序,返回上中位数
        // 3)在算法模型二的基础上实现算法原型一

        int size = nums1.length + nums2.length;

        // 判断size的奇偶,true则为偶数,false则为奇数
        boolean even = (size & 1) == 0;

        if (nums1.length != 0 && nums2.length != 0) {
            // 偶数 => 上中位 + 下中位
            // 1 2 3 4 上中位=>2;下中位=>3
            if (even) {
                return (double) (findKthNum(nums1, nums2, size / 2) + findKthNum(nums1, nums2, size / 2 + 1)) / 2D;
                // 奇数 => 中位
                // 1 2 3 4 5
            } else {
                // 5/2=2,2+1=3
                // KeyPoint 奇数情况不涉及上中位和下中位,只有一个中位数,所以不涉及double
                //      直接定位到size/2+1位置,取值即可
                return findKthNum(nums1, nums2, size / 2 + 1);
            }
            // num1长度不为0,num2长度为0
        } else if (nums1.length != 0) {
            if (even) {
                // 单个数组中求中位数,不用调用findKthNum函数
                // KeyPoint 这里涉及索引下标,需要减1
                return (double) (nums1[(size - 1) / 2] + nums1[size / 2]) / 2;
            } else {
                return nums1[size / 2];
            }
            // num2长度不为0,num1长度为0
        } else if (nums2.length != 0) {
            if (even) {
                return (double) (nums2[(size - 1) / 2] + nums2[size / 2]) / 2;
            } else {
                return nums2[size / 2];
            }
        } else {
            return 0;
        }
    }

    // 算法原型一
    public static int findKthNum(int[] arr1, int[] arr2, int kth) {

        // KeyPoint 情况一: 1<= kth <= s(短数组长度)
        // 两个数组分别拿出前kth个(对应索引为kth-1),相当于两段等长区域,调用getUpMedian函数,求整体的上中位数
        // arr1   1 2 3 4
        // arr2   1'2'3'4' 求第2大的数
        // KeyPoint arr1和arr2各自对应索引范围是0-1,不是0-2

        // KeyPoint 情况二:短数组长度 < kth <= 长数组长度
        // arr1 [1,2,3,4,5,6,7,8,9,10] 长度 10
        // arr2 [1',2',3',4',5',6',7',8',9',10',11',12',13',14',15',16',17'] 长度 17 kth = 15

        // 分析过程
        // 1) arr1 [1,2,...,10] 中所有元素都有可能为第15小
        // 2) arr2 [1',2',...,17'] 中 1',2',3',4'没有可能, 16',17'也没有可能为第15小,可能的范围 arr2 [5',6',...,15']
        // 3) arr1是10个数,arr2是11个数,需要手动判断5'是否为第15小,5'>=10,则直接返回,否则淘汰5',
        //    KeyPoint 选择比较规则:剩余长数组第一元素(5')和短数组最后一个元素进行比较(10)
        //         => arr2[5'] = arr2[kth-arr1.length] => arr2[kth-arr1.length-1]
        //         => arr1[10] = arr1[arr1.length] => arr1[arr1.length-1]
        // 4)剩余arr2和arr1调用getUpMedian函数
        //    KeyPoint 这里只能淘汰arr2中左边的5',不能淘汰arr2中右边的15',因为需要保证arr2左侧淘汰的数是5个
        //         这样才能保证求的是第15小

        // KeyPoint 情况三:长数组长度 < kth <= 两个数组长度之和
        // arr1 [1,2,3,4,5,6,7,8,9,10] 长度 10
        // arr2 [1',2',3',4',5',6',7',8',9',10',11',12',13',14',15',16',17'] 长度 17 kth = 23

        // 分析过程
        // 1) arr1 1-5都是不可能,arr1的5+arr2的17',最多为第22小 => 剩余 [6,7,8,9,10]
        //    KeyPoint 计算方法:k-arr2长度=23-17=6(剩余数组起始位置),故arr1<6的位置都不可能
        //         => arr1[6] = arr1[k-arr2.length] => arr1[k-arr2.length-1](对应数组索引还得减1)
        // 2) arr2 1'-12'都是不可能,arr2的12'+ ar1的10,最多为第22小 => 剩余 [13',14',15',16',17']
        //    KeyPoint 计算方法:k-arr1长度=23-10=13(剩余数组起始位置),故arr1<13的位置都不可能
        //         => arr2[13'] = arr2[k-arr1.length] => arr2[k-arr1.length-1] (对应数组索引还得减1)
        // 3) 虽然剩余两数组长度相等,但是不能直接调用getUpMedian函数,因为arr1中淘汰5个,arr2中淘汰12个,
        //    这里一共淘汰17个,而剩余的就是在arr1(5个元素)和arr2(5个元素),求第5小的数,则凑出来为22小的数,不是23小的数
        // 4) KeyPoint 需要在剩余数组中手动淘汰两个元素
        //    a.短数组的第一元素(6)和长数组最后一个元素比较(17')
        //      手动验证6是否为23小,即判断6>=17',若成立,则直接返回6,否则淘汰6
        //    KeyPoint 剩余 arr1 [6,7,8,9,10] 想要第23小,首先判断6(位置)是否可能,arr1淘汰5个,即有个5元素已经比6位置小
        //         想要第23小,则还需要17个元素比6位置小(5+17=22),故自然是arr1[6]和arr2[17']比较
        //         => arr1[6] = arr1[k-arr2.length] => arr1[k-arr2.length-1]
        //         => arr2[17'] = arr2[arr2.length] => arr2[arr2.length-1]
        //    b.长数组的第一元素(13')和短数组最后一个元素比较(10)
        //      手动验证13'是否为23小,即判断13'>=10,若成立,则直接返回13',否则淘汰13'
        //    KeyPoint 剩余 arr2 [13',14',15',16',17'] 想要第23小,首先判断13'(位置)是否可能,arr2淘汰12个,
        //         即有个12元素已经比13'位置小,想要第23小(12+10=22),则还需要10个元素比13'位置小,故自然是arr1[10]和arr2[13']比较
        //         => arr2[13'] = arr2[k-arr1.length] => arr2[k-arr1.length-1]
        //         => arr1[10] = arr1[arr1.length] => arr1[arr1.length-1]
        //   c. 剩下的 7,8,9,10 和 14',15',16',17' 调用 getUpMedian 函数
        //      此时arr1淘汰6个,arr2淘汰13个,一共淘汰19个,加上剩余的两数组的第4小,结果为第23小

        int[] longs = arr1.length >= arr2.length ? arr1 : arr2;
        int[] shorts = arr1.length < arr2.length ? arr1 : arr2;

        int l = longs.length;
        int s = shorts.length;

        // 情况一
        if (kth <= s) {
            //
            return getUpMedian(shorts, 0, kth - 1, longs, 0, kth - 1);
        }

        // 情况三
        if (kth > l) {
            if (shorts[kth - l - 1] >= longs[l - 1]) {
                return shorts[kth - l - 1];
            }
            if (longs[kth - s - 1] >= shorts[s - 1]) {
                return longs[kth - s - 1];
            }
            return getUpMedian(shorts, kth - l, s - 1, longs, kth - s, l - 1);
        }

        // KeyPoint 前面先将好用if判断的部分写好(kth<=s和kth>l),则剩下就是不好if判断的情况,可以偷懒

        // 情况二
        if (longs[kth - s - 1] >= shorts[s - 1]) {
            return longs[kth - s - 1];
        }
        return getUpMedian(shorts, 0, s - 1, longs, kth - s, kth - 1);

        // KeyPoint 复杂度分析:
        // arr1(N),arr2(M) 因为getUpMedian要求是等长的数组
        // 故两段数据不会大于短数组的长度,时间复杂度:O(log(短数据长度))

    }

    // 算法模型二
    // 在A,B两个数组的基础上,加了起始和终止的边界
    public static int getUpMedian(int[] A, int s1, int e1, int[] B, int s2, int e2) {

        // 函数要求输入参数e1-s1 == e2-s2两断一定等长
        // 两段等长的数组一定有序
        // 返回值为两段数组整体的上中位数

        // 补充:上中位数
        // 2,3,4,5,6,7 的中位数,先用6除以2算出第3个数是4然后再用(4+5)/2=4.5
        // 上中位数是3,下中位数是5

        // KeyPoint 情况一:两数组都是偶数个
        // 1) 举例 arr1 [1,2,3,4]和 arr2 [1',2',3',4'],arr1和arr2都是偶数个数组
        //    其中 1,2,3,4 表示元素相对大小,不是元素值

        // 2) arr1 和 arr2 的上中点进行比较
        //      KeyPoint 通过假定相对大小关系,排除一半不可能的情况,从而缩小数组范围
        //      a.若 2 = 2' 则顺序为 1,1' | 2=2'| 3,3',4,4',直接返回 2或者 2'对应的值
        //        两个长度为4的数组,则上中位数:对应这里返回第4小的数
        //      b.若 2 > 2' 则排除 3,4 和  1',2'
        //                  因为 1,2,3,4         1,2 | 3,4  3,4  3最多为第5小的数 => 不是第4小的数
        //                      1',2',3',4'  =>  1',2' | 2'   2'最多为第3小数 => 不是第4小的数
        //                 剩余的元素都是有可能的 arr1 [1,2] 和 arr2 [3',4']
        //                 再去递归调用getUpMedian函数,有结论(可证明):递归调用的结果(该上中位数)就是原数组的上中位数
        //      c.若 2 < 2' 同理

        // KeyPoint 情况二:两数组都是奇数个
        // 1) 举例 arr1 [1,2,3,4,5]和 arr2 [1',2',3',4',5'],arr1 和 arr2 都是奇数个数组
        // 2) arr1 和 arr2 的上中点进行比较
        //       a. 3 = 3' 直接返回 3 或者 3'对应的值,返回第5小的数
        //       b. 若 3 > 3' 则排除 3,4,5 和  1',2'
        //                   因为 1,2,3,4,5       1,2 | 3,4,5  3最多为第6小的数 => 不是第5小的数
        //                       1',2',3',4',5'  1',2'| 2' 最多为第4小的数 => 不是第5小的数
        //          剩余元素 arr1 [1,2] arr2 [3',4',5'] 因为不等长,无法递归
        //          故需要手动验证 3'是否为第5小的数,即:判断3'是否>=2,若成立直接返回3',否则手动淘汰3',剩余等长的元素再递归调用
        //          KeyPoint 关键:数组那个结尾和数组那个开头进行比较
        //              1<2<   3<4<5         排除 3<4<5
        //              1'<2'<3' <4'<5'      排除 1'<2'

        int mid1 = 0;
        int mid2 = 0;

        // 使用 while 迭代化简了递归
        // 因为两组数组的长度都是一样的,所以使用s1,e1还是s2,e2都是可以
        while (s1 < e1) {

            mid1 = (s1 + e1) / 2;
            mid2 = (s2 + e2) / 2;

            // 无论奇偶,上中点相等则直接返回
            if (A[mid1] == B[mid2]) {
                return A[mid1];
            }

            // 奇数长度
            // 数组[0-4]两端都包括的长度为4-0+1=5,而4-0表示的为长度,从1到4,一共4个数
            // 长度&1=1,则该数组为奇数长度,奇数最后1位必然为1,再&1,结果必然是1
            if (((e1 - s1 + 1) & 1) == 1) {
                if (A[mid1] > B[mid2]) {
                    // 关键:数组那个结尾和数组那个开头进行比较
                    if (B[mid2] >= A[mid1 - 1]) {
                        return B[mid2];
                    }
                    // 两个数组保留那一段
                    e1 = mid1 - 1; // A [1,2]
                    s2 = mid2 + 1; // B [4',5']
                    // A[mid1] < B[mid2]
                } else {
                    // KeyPoint 相反的情况是完全对称,逻辑操作是一样
                    //  只是将字母,数字调换,将A变B,将1变2,将2变1即可
                    if (A[mid1] >= B[mid2 - 1]) {
                        return A[mid1];
                    }
                    e2 = mid2 - 1;
                    s1 = mid1 + 1;
                }
                // 偶数长度
            } else {
                if (A[mid1] > B[mid2]) {
                    e1 = mid1;
                    s2 = mid2 + 1;
                } else {
                    e2 = mid2;
                    s1 = mid1 + 1;
                }
            }
        }

        // 最后跳出while循环,A和B数组都只有一个元素,两者进行比较,小的数为两段数组整体的上中位数
        // 两个数A,B的上中位数则为A,下中位数为B,故选择比较小的那个数
        return Math.min(A[s1], B[s2]);

        // KeyPoint 时间复杂度分析
        // arr1(N),arr2(N),两个数组每次都是大致砍一半,时间复杂度:O(logN)
    }
}
