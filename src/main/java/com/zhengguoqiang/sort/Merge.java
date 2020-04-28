package com.zhengguoqiang.sort;

import com.zhengguoqiang.util.SortUtil;

public class Merge {
    //辅助数组
    private static Comparable[] aux;

    public static void sort(Comparable[] a) {
        aux = new Comparable[a.length];
        sort(a, 0, a.length - 1);
        SortUtil.show(a);
    }

    public static void sort(Comparable[] a, int lo, int hi) {
        //将数组a[lo,hi]排序
        if (lo >= hi) return;
        int mid = lo + (hi - lo) / 2;
        sort(a, lo, mid);//左半边排序
        sort(a, mid + 1, hi);//右半边排序
        //当子数组规模较小时，可以通过判断a[mid]与a[mid+1]的大小避免merge过程，如果a[mid] <= a[mid+1] 则跳过merge
        merge(a, lo, mid, hi);//归并排序
    }

    public static void merge(Comparable[] a, int low, int mid, int hi) {
        int i = low, j = mid + 1;

        //数组拷贝
        for (int k = low; k <= hi; k++) {
            aux[k] = a[k];
        }

        for (int k = low; k <= hi; k++) {
            if (i > mid) {       //左半边用尽，直接取右半边元素
                a[k] = aux[j++];
            } else if (j > hi) {  //右半边用尽，直接取左半边元素
                a[k] = aux[i++];
            } else if (SortUtil.less(aux[j], aux[i])) {//右半边的元素小于左半边的元素，取右半边元素
                a[k] = aux[j++];
            } else {//右半边元素大于等于左半边元素，取左半边元素
                a[k] = aux[i++];
            }
        }
    }

    public static void main(String[] args) {
        String[] arr = new String[]{"M", "E", "R", "G", "E", "S", "O", "R", "T", "E", "X", "A", "M", "P", "L", "E"};
        sort(arr);
    }
}
