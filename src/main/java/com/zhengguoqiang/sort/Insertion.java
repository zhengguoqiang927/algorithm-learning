package com.zhengguoqiang.sort;

import com.zhengguoqiang.util.SortUtil;

public class Insertion {
    public static void sort(Comparable[] a) {
        int N = a.length;
        //对于 1 到 N-1 之间的每一个 i ，将 a[i] 与 a[0] 到 a[i-1] 中比它小的所有元素依次有序地交换。
        // 在索引 i 由左向右变化的过程中，它左侧的元素总是有序的，所以当 i 到达数组的右端时排序就完成了
        sort(a,0,N);
        SortUtil.show(a);
    }

    public static void sort(Comparable[] a,int lo,int hi){
        //对于 1 到 N-1 之间的每一个 i ，将 a[i] 与 a[0] 到 a[i-1] 中比它小的所有元素依次有序地交换。
        // 在索引 i 由左向右变化的过程中，它左侧的元素总是有序的，所以当 i 到达数组的右端时排序就完成了
        for (int i = lo + 1; i < hi; i++) {
            for (int j = i; j > lo; j--) {
                if (SortUtil.less(a[j], a[j - 1])){
                    SortUtil.exch(a, j, j - 1);
                }else {
                    break;
                }
            }
        }
    }

    public static void main(String[] args) {
        String[] arr = new String[]{"A", "C", "F", "G", "I", "M", "B", "Z", "E"};
        sort(arr);
    }
}
