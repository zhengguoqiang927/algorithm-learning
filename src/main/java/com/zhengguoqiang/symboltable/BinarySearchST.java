package com.zhengguoqiang.symboltable;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * 基于有序数组的有序符号表
 * <p>
 * 二分查找
 *
 * @param <Key>
 * @param <Value>
 */
public class BinarySearchST<Key extends Comparable<Key>, Value> {
    private Key[] keys;
    private Value[] values;
    private int N;

    public BinarySearchST(int capacity) {
        keys = (Key[]) new Comparable[capacity];
        values = (Value[]) new Object[capacity];
    }

    public int size() {
        return N;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public Value get(Key key) {
        if (isEmpty()) return null;
        int i = rank(key);
        if (i < N && keys[i].compareTo(key) == 0) {
            return values[i];
        } else {
            return null;
        }
    }

    public void put(Key key, Value value) {
        int i = rank(key);
        //Key存在更新value
        if (i < N && keys[i].compareTo(key) == 0) {
            values[i] = value;
            return;
        }
        //Key不存在，则将排名之后的元素后移，插入新元素
        for (int j = N; j > i; j--) {
            keys[j] = keys[j - 1];
            values[j] = values[j - 1];
        }
        keys[i] = key;
        values[i] = value;
        N++;
    }


    //非递归实现
    public int rank(Key key) {
        int lo = 0, hi = N - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int cmp = key.compareTo(keys[mid]);
            if (cmp < 0) hi = mid - 1;
            else if (cmp > 0) lo = mid + 1;
            else return mid;
        }
        return lo;
    }

    /**
     * 递归的二分查找
     * <p>
     * 如果表中存在该键，rank()返回该键的位置，同时也表示表中小于该键的键的数量
     * 如果表中不存在该键，rank()还是返回表中小于该键的键的数量，同时也表示在何处插入新键
     *
     * @param key
     * @param lo
     * @param hi
     * @return
     */
    public int rank(Key key, int lo, int hi) {
        if (hi < lo) return lo;
        int mid = lo + (hi - lo) / 2;
        int cmp = key.compareTo(keys[mid]);
        if (cmp < 0) return rank(key, lo, mid - 1);
        else if (cmp > 0) return rank(key, mid + 1, hi);
        else return mid;
    }

    public Key min() {
        return keys[0];
    }

    public Key max() {
        return keys[N - 1];
    }

    public Key select(int k) {
        return keys[k];
    }

    public boolean contains(Key key) {
        int i = rank(key);
        return key.compareTo(keys[i]) == 0;
    }

    public Key ceiling(Key key) {
        int i = rank(key);
        return keys[i];
    }

    public Iterable<Key> keys(Key lo, Key hi) {
        List<Key> list = new ArrayList<>();
        for (int i = rank(lo); i < rank(hi); i++) {
            list.add(keys[i]);
        }
        if (contains(hi)) {
            list.add(keys[rank(hi)]);
        }
        return list;
    }
}
