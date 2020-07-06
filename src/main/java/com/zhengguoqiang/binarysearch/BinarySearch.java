package com.zhengguoqiang.binarysearch;

/**
 * 二分查找
 */
public class BinarySearch {
    public static int search(int[] a, int value) {
        int lo = 0;
        int hi = a.length - 1;
        //循环条件是low <= high
        while (lo <= hi) {
            //也可以采用 low + ((high-low)>>1),效率更高，但不推荐 (low+high)/2,因为如果low和high都很大的话，两者之和可能发生溢出
            int mid = lo + (hi - lo) / 2;
            if (value == a[mid]) {
                return mid;
            } else if (value > a[mid]) {
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        return -1;
    }

    public static int searchByRecursive(int[] a, int lo, int hi, int value) {
        if (lo > hi) return -1;
        int mid = lo + (hi - lo) / 2;
        if (a[mid] == value) {
            return mid;
        } else if (a[mid] < value) {
            return searchByRecursive(a, mid + 1, hi, value);
        } else {
            return searchByRecursive(a, lo, mid - 1, value);
        }
    }

    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int location = searchByRecursive(array,0,array.length-1, 4);
        System.out.println(location);
    }
}
