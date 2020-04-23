package com.zhengguoqiang.util;

/**
 * @author zhengguoqiang
 */
public class SortUtil {
    public static boolean less(Comparable a, Comparable b){
        return a.compareTo(b) < 0;
    }

    public static void exch(Comparable[] a,int i,int j){
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public static void show(Comparable[] a){
        for (Comparable t : a) {
            System.out.print(t + " ");
        }
        System.out.println();
    }

    public static boolean isSort(Comparable[] a){
        for (int i=1;i<a.length;i++){
            if (less(a[i],a[i-1])) return false;
        }
        return true;
    }


}
