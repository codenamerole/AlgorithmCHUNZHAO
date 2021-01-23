//给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。 
//
// 示例: 
//
// 输入: [0,1,0,3,12]
//输出: [1,3,12,0,0] 
//
// 说明: 
//
// 
// 必须在原数组上操作，不能拷贝额外的数组。 
// 尽量减少操作次数。 
// 
// Related Topics 数组 双指针 
// 👍 918 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

//Round1 1. 非0元素与0交换
class Solution{
    int j = 0;
    public void moveZeroes(int[] nums){
        for (int i = 0; i < nums.length; i++) {
            if(nums[i] != 0){
                int temp = nums[j];
                nums[j] = nums[i];
                nums[i] = temp;
                j++;
            }
        }
    }
}

//Round1 2. 双指针
class Solution {
    public void moveZeroes(int[] nums) {
        int j = 0;
        for (int i = 0; i< nums.length; i++) {
            if (nums[i] != 0) {
                if(i != j) {
                    nums[j] = nums[i];
                    nums[i] = 0;
                }
                j++;
            }
        }
    }
}

//Round1 3. 滚雪球解法
class Solution {
    public void moveZeroes(int[] nums) {
        int snowBallSize = 0;
        for (int i = 0; i< nums.length; i++) {
            if (nums[i] == 0) {
                snowBallSize++;
            }
            else if (snowBallSize > 0) {
                int t = nums[i];
                nums[i] = 0;
                nums[i-snowBallSize] = t;
            }
        }
    }
}

//leetcode submit region end(Prohibit modification and deletion)
//Round2
class Solution  {
    public void moveZeroes(int[] nums){
        int j = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] != 0) {
                if (i != j){
                    nums[j] = nums[i];
                    nums[i] = 0;
                }
                j++;
            }
        }
    }
}