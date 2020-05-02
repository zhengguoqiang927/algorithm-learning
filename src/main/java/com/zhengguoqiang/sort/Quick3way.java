package com.zhengguoqiang.sort;

import com.zhengguoqiang.util.SortUtil;

public class Quick3way {
    private static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) return;
        int lt = lo, i = lo + 1, gt = hi;
        Comparable v = a[lo];
        while (i <= gt) {
            int cmp = a[i].compareTo(v);
            if (cmp < 0) {
                SortUtil.exch(a, i++, lt++);
            } else if (cmp > 0) {
                SortUtil.exch(a, i, gt--);
            } else {
                i++;
            }
        }
        sort(a, lo, lt - 1);
        sort(a, gt + 1, hi);
    }

    public static void main(String[] args) {
        String[] arr = new String[]{"R", "B", "W", "W", "R", "W", "B", "R", "R", "W", "B", "R"};
        sort(arr, 0, arr.length - 1);
        SortUtil.show(arr);
    }
}
