package com.zhengguoqiang.sort;

import com.zhengguoqiang.util.SortUtil;

import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.ThreadLocalRandom;

public class Quick {

    private static final int M = ThreadLocalRandom.current().nextInt(5,16);

    private static int partition(Comparable[] a, int lo, int hi) {
        //将数组切分为a[lo,i-1],a[i],a[i+1,hi]
        //左右扫描指针
        int i = lo, j = hi + 1;
        //切分元素
        Comparable v = a[lo];

        while (true) {
            //停留在不小于v的元素下标上
            while (SortUtil.less(a[++i], v)) if (i == hi) break;
            //停留在不大于v的元素下标上
            while (SortUtil.less(v, a[--j])) if (j == lo) break;
            //左右扫描指针相交时退出
            if (i >= j) break;
            SortUtil.exch(a, i, j);
        }
        //将切分元素放入正确的位置
        SortUtil.exch(a,lo,j);
        return j;
    }

    private static void sort(Comparable[] a,int lo,int hi){
//        if (hi <= lo) return;
        //小数组转换为插入排序，提高效率
        if (hi <= lo + M) {
            System.out.println("转换为插入排序：lo = " + lo +",hi = " + hi + ",M = " + M);
            Insertion.sort(a,lo,hi+1);
            return;
        }
        //切分元素
        int j = partition(a,lo,hi);
        //左半部分排序
        sort(a,lo,j-1);
        //右半部分排序
        sort(a,j+1,hi);
    }

    public static void main(String[] args) {
        String[] arr = new String[]{"Q", "U", "I", "C", "K", "S", "O", "R", "T", "E", "X", "A", "M", "P", "L", "E"};
        //混排
        Collections.shuffle(Arrays.asList(arr));
        sort(arr,0,arr.length - 1);
        SortUtil.show(arr);
    }
}
