package com.zhengguoqiang.sort;

import com.zhengguoqiang.util.SortUtil;

/**
 * 冒泡排序
 */
public class Bubble {
    public static void sort(Comparable[] a){
        int N = a.length;
        if (N <=1 ) return;
        for (int i=0;i<N;i++){
            //提前退出冒泡循环的标志位
            boolean flag = false;
            for (int j=0;j<N-1-i;j++){
                if (a[j].compareTo(a[j+1]) > 0){
                    Comparable tmp = a[j+1];
                    a[j+1] = a[j];
                    a[j] = tmp;
                    flag = true;//表示有数据交换
                }
            }
            if (!flag) break;//没有数据交换，表示数组已经有序，直接退出循环
        }
        SortUtil.show(a);
    }

    public static void main(String[] args) {
        String[] arr = new String[]{"A","C","F","G","I","M","B","Z","E"};
        sort(arr);
    }
}
