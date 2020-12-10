package com.zhengguoqiang.leetcode;

/**
 * 给定一个含有n个正整数的数组和一个正整数s ，找出该数组中满足其和 ≥ s 的长度最小的 连续 子数组，并返回其长度。如果不存在符合条件的子数组，返回 0。
 *
 *
 *
 * 示例：
 *
 * 输入：s = 7, nums = [2,3,1,2,4,3]
 * 输出：2
 * 解释：子数组[4,3]是该条件下的长度最小的子数组。
 *
 *
 * 进阶：
 *
 * 如果你已经完成了 O(n) 时间复杂度的解法, 请尝试 O(n log n) 时间复杂度的解法。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-size-subarray-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MinSubArrayLen_209 {

    public static int minSubArrayLen(int[] nums,int s){
        int left=0,right=0;
        int sum = nums[left];
        int ans = Integer.MAX_VALUE;
        while (left <= right && right< nums.length){
            if (sum < s){
                right++;
                if (right == nums.length) break;
                sum += nums[right];
            }else{
                ans = Math.min(ans,right-left + 1);
                sum -= nums[left];
                left++;
            }
        }
        return ans == Integer.MAX_VALUE ? 0 : ans;
    }

    public static void main(String[] args) {
        int[] nums = {2,3,1,2,4,3};
        int len = minSubArrayLen(nums, 7);
        System.out.println(len);
    }
}
