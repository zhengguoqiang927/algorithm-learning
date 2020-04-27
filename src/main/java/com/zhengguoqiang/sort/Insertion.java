package com.zhengguoqiang.sort;

import com.zhengguoqiang.util.SortUtil;

public class Insertion {
    public static void sort(Comparable[] a) {
        int N = a.length;
        for (int i = 1; i < N; i++) {
            for (int j = i; j > 0; j--) {
                if (SortUtil.less(a[j], a[j - 1]))
                    SortUtil.exch(a, j, j - 1);
            }
        }
        SortUtil.show(a);
    }

    public static void main(String[] args) {
        String[] arr = new String[]{"A", "C", "F", "G", "I", "M", "B", "Z", "E"};
        sort(arr);
    }
}
