package com.zhengguoqiang.sort;

import com.zhengguoqiang.util.SortUtil;

public class PriorityQueueSimple<Key extends Comparable<Key>> {

    private Key[] pq;
    private int N = 0;

    public PriorityQueueSimple(int maxN) {
        this.pq = (Key[]) new Comparable[maxN + 1];
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    private boolean less(int i, int j) {
        return pq[i].compareTo(pq[j]) < 0;
    }

    private void exch(int i, int j) {
        Key t = pq[i];
        pq[i] = pq[j];
        pq[j] = t;
    }

    /**
     * 上浮
     *
     * @param k 位置为k的元素
     */
    private void swim(int k) {
        while (k > 1 && less(k / 2, k)) {
            exch(k / 2, k);
            k = k / 2;
        }
    }

    /**
     * 下沉
     *
     * @param k 位置为k的元素
     */
    private void sink(int k) {
        while (2 * k <= N) {
            int j = 2 * k;
            //取两个子结点中较大者
            if (j < N && less(j, j + 1)) j++;
            //当父结点不在小于子结点时，停止下沉
            if (!less(k, j)) break;
            exch(k, j);
            k = j;
        }
    }

    private static void sink(Comparable[] a, int k, int N) {
        while (2 * k <= N) {
            int j = 2 * k;
            if (j < N && SortUtil.less(a[j], a[j + 1])) j++;
            if (!SortUtil.less(a[k], a[j])) break;
            SortUtil.exch(a, k, j);
            k = j;
        }
    }

    public void insert(Key v) {
        pq[++N] = v;
        swim(N);
    }

    public Key delMax() {
        Key max = pq[1];//取出最大元素
//        pq[1] = pq[N--];//与最后一个结点交换
        exch(1, N--);
        pq[N + 1] = null;//防止对象游离
        sink(1);//下沉
        return max;
    }

    public static void sort(Comparable[] a) {
        int N = a.length-1;
        //构造堆
        for (int k = N / 2; k >= 1; k--) {
            sink(a, k, N);
        }

        //下沉排序
        while (N > 1) {
            SortUtil.exch(a, 1, N--);
            sink(a, 1, N);
        }
    }

    public static void main(String[] args) {
        String[] arr = {"A", "O", "R", "T", "H", "N", "G", "S", "E", "I", "P"};
        PriorityQueueSimple<String> simple = new PriorityQueueSimple<>(11);
        for (String a : arr) {
            simple.insert(a);
        }
        while (!simple.isEmpty()) {
            System.out.print(simple.delMax() + ", ");
        }
        System.out.println();
        System.out.println("===================================");

        String[] sort = {"","S", "O", "R", "T", "E", "X", "A", "M", "P", "L", "E"};
        PriorityQueueSimple.sort(sort);
        SortUtil.show(sort);
    }
}
