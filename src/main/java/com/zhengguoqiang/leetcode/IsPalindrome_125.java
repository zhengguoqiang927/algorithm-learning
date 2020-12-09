package com.zhengguoqiang.leetcode;

/**
 * 给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
 *
 * 说明：本题中，我们将空字符串定义为有效的回文串。
 *
 * 示例 1:
 *
 * 输入: "A man, a plan, a canal: Panama"
 * 输出: true
 * 示例 2:
 *
 * 输入: "race a car"
 * 输出: false
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/valid-palindrome
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class IsPalindrome_125 {
    //1:过滤 + 反转判断 时间和空间都为O(n)
    public static boolean isPalindrome1(String s){
        StringBuilder s1 = new StringBuilder();
        int length = s.length();
        for (int i=0;i<length;i++){
            char c = s.charAt(i);
            if (Character.isLetterOrDigit(c)){
                s1.append(c);
            }
        }
        StringBuffer s2 = new StringBuffer(s1).reverse();
        return s1.toString().equalsIgnoreCase(s2.toString());
    }

    //过滤 + 左右指针 时间和空间都为O(n)
    public static boolean isPalindrome2(String s){
        StringBuilder s1 = new StringBuilder();
        int length = s.length();
        for (int i=0;i<length;i++){
            char c = s.charAt(i);
            if (Character.isLetterOrDigit(c)){
                s1.append(Character.toLowerCase(c));
            }
        }
        int len = s1.length();
        int left = 0,right = len - 1;
        while (left < right){
            if (s1.charAt(left) != s1.charAt(right)){
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    //左右指针 时间O(n) 空间O(1)
    public static boolean isPalindrome3(String s){
        int len = s.length();
        int left=0,right=len-1;
        while (left < right){
            while (left < right && !Character.isLetterOrDigit(s.charAt(left))) left++;
            while (left < right && !Character.isLetterOrDigit(s.charAt(right))) right--;
            if (left < right){
                if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))){
                    return false;
                }
                left++;
                right--;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String s = "A man, a plan, a canal: Panama";
        System.out.println(isPalindrome3(s));
    }
}
