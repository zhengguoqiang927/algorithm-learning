package com.zhengguoqiang.leetcode;

/**
 * 给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点(i,ai) 。在坐标内画 n 条垂直线，
 * 垂直线 i的两个端点分别为(i,ai) 和 (i, 0) 。找出其中的两条线，使得它们与x轴共同构成的容器可以容纳最多的水。
 *
 * 说明：你不能倾斜容器。
 *
 *
 * 示例 1：
 *
 *
 *
 * 输入：[1,8,6,2,5,4,8,3,7]
 * 输出：49
 * 解释：图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为49。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/container-with-most-water
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MaxArea_11 {
    /**
     * 主要需要推出计算公式：min(height[left],height[right]) * (right-left)，即左右指针中较小者 * 左右指针之间的距离
     * 移动指针的判断（假设height[right]>height[left]）：1.移动较大的指针right；2.移动较小的指针left
     *  1.移动较大的指针
     *      假如移动较大的指针，那么同样分两种情况(移动后的指针为right-1):1.height[right-1]>height[left];2.height[right-1]<height[left]
     *      针对第一种情况：公式左半部分min(height[left],height[right])不变，右半部分距离将减少，容量减小
     *      针对第二种情况：公式左半部分min(height[left],height[right])减少，右半部分距离将减少，容量减小
     *  2.移动较小的指针
     *      不管哪种移动，双指针向中间移动，距离都在变小，所能影响的就是使min(height[left],height[right])有可能变大
     *      移动较大的指针是不可能使其变大的，只有移动较小的指针才可能使其变大
     *
     * 时间复杂度：O(n),空间复杂度：O(1)
     * @param height
     * @return
     */
    public static int maxArea(int[] height){
        int left=0,right = height.length-1;
        int ans = 0;
        while (left<right){
            int area = Math.min(height[left],height[right]) * (right - left);
            ans = Math.max(ans,area);
            if (height[left] <= height[right]){
                left++;
            }else {
                right--;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] height = {1,8,6,2,5,4,8,3,7};
        System.out.println(maxArea(height));
    }
}
