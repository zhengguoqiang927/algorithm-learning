package com.zhengguoqiang.offer;

/**
 * 4.二维数组的查找
 * 1 2 8 9
 * 2 4 9 12
 * 4 7 10 13
 * 6 8 11 15
 * 在上面二维数组中查找给定元素
 */
public class TwoDimensionalArraySearch {
    public static boolean find(int[][] arrays,int key){
        int rows = arrays.length;
        if (rows == 0) return false;
        int columns = arrays[0].length;
        if (columns <= 1) return false;
        boolean found = false;

        //从右上角开始查找
        int row = 0;
        int column = columns-1;
        while (row<rows && column >= 0){
            if (arrays[row][column] == key){
                found = true;
                break;
            }else if (arrays[row][column] > key){
                column--;
            }else {
                row++;
            }
        }
        return found;
    }

    public static void main(String[] args) {
        int[] arr0 = {1,2,8,9};
        int[] arr1 = {2,4,9,12};
        int[] arr2 = {4,7,10,13};
        int[] arr3 = {6,8,11,15};
        int[][] arrays = {arr0,arr1,arr2,arr3};
        System.out.println(find(arrays,5));
    }
}
