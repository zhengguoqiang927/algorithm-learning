package com.zhengguoqiang.sort;

import com.zhengguoqiang.util.SortUtil;

public class Insertion {
    public static void sort(Comparable[] a) {
        int N = a.length;
//        sort(a,0,N);
        insertTwo(a);
        SortUtil.show(a);
    }

    public static void sort(Comparable[] a, int lo, int hi) {
        //对于 1 到 N-1 之间的每一个 i ，将 a[i] 与 a[0] 到 a[i-1] 中比它小的所有元素依次有序地交换。
        // 在索引 i 由左向右变化的过程中，它左侧的元素总是有序的，所以当 i 到达数组的右端时排序就完成了
        for (int i = lo + 1; i < hi; i++) {
            for (int j = i; j > lo; j--) {
                if (SortUtil.less(a[j], a[j - 1])) {
                    SortUtil.exch(a, j, j - 1);
                } else {
                    break;
                }
            }
        }
    }

    public static void insert(Comparable[] a) {
        int N = a.length;
        if (N <= 1) return;
        for (int i = 1; i < N; i++) {
            for (int j = i; j > 0; j--) {
                if (a[j].compareTo(a[j - 1]) < 0) {
                    Comparable tmp = a[j];
                    a[j] = a[j - 1];
                    a[j - 1] = tmp;
                }
            }
        }
    }

    /**
     * 交换时只需一个赋值操作的实现
     * 该算法比同样时间复杂度的冒泡排序算法要快
     * @param a
     */
    public static void insertTwo(Comparable[] a) {
        int N = a.length;
        if (N <= 1) return;
        for (int i = 1; i < N; i++) {
            Comparable value = a[i];//要插入的元素
            int j = i-1;
            for (; j >= 0; j--) {
                if (a[j].compareTo(value) > 0) {
                    a[j+1] = a[j];//将比插入元素大的元素后移
                }else {
                    break;
                }
            }
            //插入新元素，注意循环时j--多执行了一次，所以需要j+1
            a[j+1] = value;
        }
    }

    public static void main(String[] args) {
        String[] arr = new String[]{"A", "C", "F", "G", "I", "M", "B", "Z", "E"};
        sort(arr);
    }
}
