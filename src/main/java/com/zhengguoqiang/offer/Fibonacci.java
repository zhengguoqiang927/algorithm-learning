package com.zhengguoqiang.offer;

/**
 * 10.斐波那些数列
 * 从上往下计算会出现很多重复的计算，时间复杂度为n的指数（可以采用map将计算过的项缓存起来，然后在用的时候查询map中是否存在则可避免重复计算）
 * 从下往上计算即f(0),f(1),f(2),f(3)....这样计算，不会出现重复计算，时间复杂度为O(n)
 */
public class Fibonacci {

    public static long fibonacci(int n){
        if (n == 0 || n == 1) return n;
        long numOne = 0;
        long numTwo = 1;
        long result = 0;
        for(int i = 2;i<=n;i++){
            result = numOne + numTwo;
            numOne = numTwo;
            numTwo = result;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(fibonacci(100));
        System.out.println(Integer.MAX_VALUE);
    }
}
