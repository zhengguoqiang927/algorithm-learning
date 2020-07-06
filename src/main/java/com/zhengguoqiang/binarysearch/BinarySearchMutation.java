package com.zhengguoqiang.binarysearch;

/**
 * 二分查找变种问题
 */
public class BinarySearchMutation {

    /**
     * 查找第一个等于给定值的元素
     * @param a
     * @param value
     * @return
     */
    public static int bsearchFirstValue(int[] a,int value){
       int lo = 0;
       int hi = a.length - 1;
       while (lo <= hi){
           int mid = lo + (hi - lo)/2;
           if (a[mid] == value){
               //如果mid已经在第一个位置或者mid的前一个位置小于给定值，则说明a[mid]就是要找的第一个值
               if (mid == 0 || a[mid-1] < value) return mid;
               hi = mid - 1;
           }else if (a[mid] < value){
               lo = mid + 1;
           }else {
               hi = mid -1;
           }
       }
       return -1;
    }

    /**
     * 查找最后一个等于给定值的元素
     * @param a
     * @param value
     * @return
     */
    public static int bsearchLastValue(int[] a,int value){
       int lo = 0;
       int hi = a.length - 1;
       while (lo <= hi){
           int mid = lo + ((hi - lo) >> 1);
           if (a[mid] == value){
               //如果mid已经到数组末尾或者mid的下一个位置比给定值大，则说明a[mid]就是要找的最后一个值
               if (mid == a.length - 1 || a[mid + 1] > value) return mid;
               lo = mid + 1;
           }else if (a[mid] < value){
               lo = mid + 1;
           }else {
               hi = mid - 1;
           }
       }
       return -1;
    }

    /**
     * 查找第一个大于等于给定值的元素
     * @param a
     * @param value
     * @return
     */
    public static int bsearchFirstGreaterValue(int[] a,int value){
        int lo = 0;
        int hi = a.length - 1;
        while (lo <= hi){
            int mid = lo + (hi-lo)/2;
            if (a[mid] >= value){
                if (mid == 0 || a[mid-1] < value) return mid;
                hi = mid -1 ;
            }else {
                lo = mid + 1;
            }
        }
        return -1;
    }

    public static int bsearchLastLessValue(int[] a,int value){
        int lo = 0;
        int hi = a.length - 1;
        while (lo <= hi){
            int mid = lo + (hi-lo)/2;
            if (a[mid] <= value){
                if (mid == a.length -1 || a[mid + 1] > value) return mid;
                lo = mid + 1;
            }else {
                hi = mid - 1;
            }
        }
        return -1;
    }
    public static void main(String[] args) {
        int[] array = {1,3,4,5,6,8,8,8,11,18};
        int location = bsearchLastLessValue(array, 7);
        System.out.println(location);
        double prec = 1e-7;
        System.out.println(prec);
    }
}
