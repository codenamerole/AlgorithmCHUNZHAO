//给定一个非负整数数组 nums ，你最初位于数组的 第一个下标 。 
//
// 数组中的每个元素代表你在该位置可以跳跃的最大长度。 
//
// 判断你是否能够到达最后一个下标。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [2,3,1,1,4]
//输出：true
//解释：可以先跳 1 步，从下标 0 到达下标 1, 然后再从下标 1 跳 3 步到达最后一个下标。
// 
//
// 示例 2： 
//
// 
//输入：nums = [3,2,1,0,4]
//输出：false
//解释：无论怎样，总会到达下标为 3 的位置。但该下标的最大跳跃长度是 0 ， 所以永远不可能到达最后一个下标。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 3 * 104 
// 0 <= nums[i] <= 105 
// 
// Related Topics 贪心算法 数组 
// 👍 1033 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
//1. 贪心 每次都走最远
class Solution {
    public boolean canJump(int[] nums) {
        int n = nums.length;
        int rightmost = 0;
        for (int i = 0; i < n; ++i) {
            if (i <= rightmost) {
                rightmost = Math.max(rightmost, i + nums[i]);
                if (rightmost >= n - 1) {
                    return true;
                }
            }
        }
        return false;
    }
}
//Round1 从后向前贪心
/*如果一个位置能够到达，那么这个位置左侧所有位置都能到达*/
class Solution {
    public boolean canJump(int[] nums) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        //pos表示需要到达的位置
        int pos = nums.length - 1;
        for (int i = nums.length - 2; i >= 0; i--) {
            if (nums[i] + i >= pos) {
                pos = i;
            }

        }
        return pos == 0;
    }
}
/*不管怎么跳，都要经过0时，也就是0是你的必经之路时，是跳不出去数组的*/
class Solution {
    public boolean canJump(int[] nums) {
        for(int i = 0; i < nums.length-1; i++){ // 最后一个是0还是非0对结果不影响
            if(nums[i] == 0){
                int j,dis = 1;
                // 检查0前边有无位置可以跳过0
                for(j = i-1; j >= 0; j--, dis++){
                    if(nums[j] > dis) break;
                }
                // 没有则失败
                if(j < 0) return false;
            }
        }
        return true;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
