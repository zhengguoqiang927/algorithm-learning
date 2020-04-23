package com.zhengguoqiang.sort;

import com.zhengguoqiang.util.SortUtil;

/**
 * @author zhengguoqiang
 */
public class Selection {
    public static void sort(Comparable[] a) {
        int N = a.length;
        for (int i = 0; i < N; i++) {
            //min用来标记最小元素的下标
            int min = i;
            for (int j = i + 1; j < N; j++) {
                if (SortUtil.less(a[j], a[min])) min = j;
            }
            SortUtil.exch(a,i,min);
        }
        SortUtil.show(a);
    }

    public static void main(String[] args) {
        String[] arr = new String[]{"A","C","F","G","I","M","B","Z","E"};
        sort(arr);
    }
}
