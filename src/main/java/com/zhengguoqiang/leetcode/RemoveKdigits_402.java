package com.zhengguoqiang.leetcode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 给定一个以字符串表示的非负整数num，移除这个数中的 k 位数字，使得剩下的数字最小。
 *
 * 注意:
 *
 * num 的长度小于 10002 且≥ k。
 * num 不会包含任何前导零。
 * 示例 1 :
 *
 * 输入: num = "1432219", k = 3
 * 输出: "1219"
 * 解释: 移除掉三个数字 4, 3, 和 2 形成一个新的最小的数字 1219。
 * 示例 2 :
 *
 * 输入: num = "10200", k = 1
 * 输出: "200"
 * 解释: 移掉首位的 1 剩下的数字为 200. 注意输出不能有任何前导零。
 * 示例 3 :
 *
 * 输入: num = "10", k = 2
 * 输出: "0"
 * 解释: 从原数字移除所有的数字，剩余为空就是0。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/remove-k-digits
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class RemoveKdigits_402 {

    public static String methodOne(String num,int k){
        Deque<Character> deque = new LinkedList<>();
        char[] chars = num.toCharArray();
        for (int i = 0;i<num.length();i++){
            char c = num.charAt(i);
            while (!deque.isEmpty() && k>0 && deque.getLast() > c){
                deque.pollLast();
                k--;
            }
            deque.offerLast(c);
        }

        for (int i=0;i<k;i++){
            deque.pollLast();
        }

        StringBuilder stringBuilder = new StringBuilder();
        boolean isFirstZero = true;
        while (!deque.isEmpty()){
            Character character = deque.pollFirst();
            if (isFirstZero && character == '0'){
                continue;
            }
            isFirstZero = false;
            stringBuilder.append(character);
        }
        return stringBuilder.length() == 0 ? "0" : stringBuilder.toString();
    }

    public static void main(String[] args) {
        String s = "10";
        int k = 2;
        String result = methodOne(s, k);
        System.out.println(result);
    }
}
