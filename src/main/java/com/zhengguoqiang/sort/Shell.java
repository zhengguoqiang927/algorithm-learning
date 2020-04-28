package com.zhengguoqiang.sort;

import com.zhengguoqiang.util.SortUtil;

public class Shell {
    public static void sort(Comparable[] a) {
        int N = a.length;
        int h = 1;
        while (h < N / 3) h = h * 3 + 1;
        while (h >= 1) {
            for (int i = h; i < N; i++) {
                for (int j = i; j >= h && SortUtil.less(a[j], a[j - h]); j -= h) {
                    SortUtil.exch(a, j, j - h);
                }
            }
            h = h / 3;
        }
        SortUtil.show(a);
    }

    public static void main(String[] args) {
        String[] arr = new String[]{"S", "H", "E", "L", "S", "O", "R", "T", "E", "X", "A", "M", "P", "L", "E"};
        sort(arr);
    }
}
