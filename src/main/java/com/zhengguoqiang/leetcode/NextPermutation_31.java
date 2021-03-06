package com.zhengguoqiang.leetcode;

/**
 * 本题要求我们实现一个算法，将给定数字序列重新排列成字典序中下一个更大的排列。
 *
 * 以数字序列 [1,2,3][1,2,3] 为例，其排列按照字典序依次为：
 *
 * [1,2,3]\\ [1,3,2]\\ [2,1,3]\\ [2,3,1]\\ [3,1,2]\\ [3,2,1]
 * [1,2,3]
 * [1,3,2]
 * [2,1,3]
 * [2,3,1]
 * [3,1,2]
 * [3,2,1]
 *
 * 这样，排列 [2,3,1][2,3,1] 的下一个排列即为 [3,1,2][3,1,2]。特别的，最大的排列 [3,2,1][3,2,1] 的下一个排列为最小的排列 [1,2,3][1,2,3]。
 *
 * 作者：LeetCode-Solution
 * 链接：https://leetcode-cn.com/problems/next-permutation/solution/xia-yi-ge-pai-lie-by-leetcode-solution/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
public class NextPermutation_31 {
    public void nextPermutation(int[] nums){
        //1.从右向左找到第一个拐点，即找到这样的顺序对(i,i+1) 使得a[i] < a[i+1]
        int i = nums.length - 2;
        while (i>=0 && nums[i] >= nums[i+1]){
            i--;
        }

        //2.找到这样的顺序对后说明a[i+1]到a[n]必然是降序排列，我们需要在(i+1,n)中找到大于a[i]的最小值a[j]，然后进行交换a[i]与a[j]
        if (i>=0){
            int j = nums.length - 1;
            while (nums[i] >= nums[j]){
                j--;
            }
            //swap nums[i] and nums[j]
            swap(nums,i,j);
        }

        //3.交换完后，a[i+1]到a[n]仍然是降序的，所以只需要将其按照升序排列即可
        //如果第一步中没有找到拐点说明该序列已经是降序排列，则直接跳过步骤2，执行步骤3将其升序排列得到最小序列值
        reverse(nums,i+1);
    }

    private void swap(int[] nums,int i,int j){
        int tmp = nums[j];
        nums[j] = nums[i];
        nums[i] = tmp;
    }

    private void reverse(int[] nums,int start){
        int left = start,right = nums.length - 1;
        while (left < right){
            //swap a[left] and a[right]
            swap(nums,left,right);
            left++;
            right--;
        }
    }
}
