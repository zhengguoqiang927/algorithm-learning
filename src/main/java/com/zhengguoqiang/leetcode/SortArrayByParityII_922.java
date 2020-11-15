package com.zhengguoqiang.leetcode;

import java.util.Arrays;

/**
 * 给定一个非负整数数组A， A 中一半整数是奇数，一半整数是偶数。
 *
 * 对数组进行排序，以便当A[i] 为奇数时，i也是奇数；当A[i]为偶数时， i 也是偶数。
 *
 * 你可以返回任何满足上述条件的数组作为答案。
 *
 *
 *
 * 示例：
 *
 * 输入：[4,2,5,7]
 * 输出：[4,5,2,7]
 * 解释：[4,7,2,5]，[2,5,4,7]，[2,7,4,5] 也会被接受。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sort-array-by-parity-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class SortArrayByParityII_922 {

    private static int[] methodOne(int[] A){
        int[] B = new int[A.length];
        int even = 0,odd = 1;
        for (int v : A){
            if (v%2 == 0){
                B[even] = v;
                even += 2;
            }else {
                B[odd] = v;
                odd += 2;
            }
        }
        return B;
    }

    private static int[] methodTwo(int[] A){
        int even = 0,odd = 1;
        for (;even < A.length;even += 2){
            if (A[even] % 2 == 1){
                while (A[odd] % 2 ==1) {
                    odd +=2;
                }
                //swap odd even
                swap(A,even,odd);
            }
        }
        return A;
    }

    private static void swap(int[] A,int i,int j){
        int tmp = A[i];
        A[i] = A[j];
        A[j] = tmp;
    }

    public static void main(String[] args) {
        int[] a = {4,2,5,7};
        int[] b = methodOne(a);
        System.out.println(Arrays.toString(b));

        int[] c = methodTwo(a);
        System.out.println(Arrays.toString(c));
    }
}
