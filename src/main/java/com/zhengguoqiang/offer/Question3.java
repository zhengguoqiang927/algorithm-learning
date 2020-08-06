package com.zhengguoqiang.offer;

/**
 * 数组中重复的数字
 */
public class Question3 {
    //哈希表 时间复杂度O(n),空间复杂度O(n)
    //排序 时间复杂度O(nlogn),空间复杂度O(1)

    private static int duplicate(int[] numbers){
        if (numbers.length == 0) return -1;
        for (int i=0;i< numbers.length;i++){
            while(numbers[i] != i){
                if (numbers[i] == numbers[numbers[i]]) return numbers[i];
                int temp = numbers[i];
                numbers[i] = numbers[numbers[i]];
                numbers[numbers[i]] = temp;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] test = {2,3,1,0,2,5,3};
        System.out.println(duplicate(test));
    }
}
