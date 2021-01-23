//给定一个排序数组，你需要在 原地 删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。 
//
// 不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。 
//
// 
//
// 示例 1: 
//
// 给定数组 nums = [1,1,2], 
//
//函数应该返回新的长度 2, 并且原数组 nums 的前两个元素被修改为 1, 2。 
//
//你不需要考虑数组中超出新长度后面的元素。 
//
// 示例 2: 
//
// 给定 nums = [0,0,1,1,1,2,2,3,3,4],
//
//函数应该返回新的长度 5, 并且原数组 nums 的前五个元素被修改为 0, 1, 2, 3, 4。
//
//你不需要考虑数组中超出新长度后面的元素。
// 
//
// 
//
// 说明: 
//
// 为什么返回数值是整数，但输出的答案是数组呢? 
//
// 请注意，输入数组是以「引用」方式传递的，这意味着在函数里修改输入数组对于调用者是可见的。 
//
// 你可以想象内部操作如下: 
//
// // nums 是以“引用”方式传递的。也就是说，不对实参做任何拷贝
//int len = removeDuplicates(nums);
//
//// 在函数里修改输入数组对于调用者是可见的。
//// 根据你的函数返回的长度, 它会打印出数组中该长度范围内的所有元素。
//for (int i = 0; i < len; i++) {
//    print(nums[i]);
//}
// 
// Related Topics 数组 双指针 
// 👍 1794 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
//Round1 快慢指针 重复的第二个改为0 统一0挪到后面 删除0
/*
class Solution {
    public int removeDuplicates(int[] nums) {
        //标记为MAX
        for (int i = 0; i < nums.length; i++) {
            for(int j = i + 1; j < nums.length; j++ ){
                if(nums[i] == nums[j]) {
                    nums[j] = 65535;
                }
            }
        }
        //移动MAX
        int k = 0;
        for (int i = 0; i< nums.length; i++) {
            if (nums[i] != 65535) {
                if(i != k) {
                    nums[k] = nums[i];
                    nums[i] = 65535;
                }
                k++;
            }
        }
        //删除MAX
        int len = nums.length;
        for (int i = nums.length - 1; i >= 1; i--) {
            if(nums[i] == 65535){
                len--;
            }
        }
        return len;
    }
}
*/

//Round1 快慢指针 从头开始计数
/*class Solution {
    public int removeDuplicates(int[] nums) {
        // 区间[0,slow]中的元素都是排序数组中只出现一次的元素
        int slow = 0;
        // 快指针fast的初始值为1，因为数组是排好序的
        // 因此数组中的第一个元素是一定存在于返回数组中的。
        for (int fast = 1; fast < nums.length; fast++) {
            // 当前考察的元素nums[fast]和nums[slow]不相等时
            // 说明nums[fast]是需要放入区间[0,slow]中的
            if (nums[fast] != nums[slow]) {
                // slow++是因为区间[0,slow]是左闭右闭的
                // 因此，在slow加1之后，在将nums[fast]的值赋予nums[slow]
                slow++;
                nums[slow] = nums[fast];
            }
        }
        // j指向的是新数组中末尾的元素，即新数组最后的索引
        // 而索引从0开始，题目要求返回新数组的长度，因此返回slow+1
        return slow + 1;
    }
}
*/
//leetcode submit region end(Prohibit modification and deletion)
//Round2
class Solution {
    public int removeDuplicates(int[] nums) {
        int i = 0;
        for (int j = 1; j < nums.length; j++) {
            if (nums[j] != nums[i]) {
                i++;
                nums[i] = nums[j];
            }
        }
        return i + 1;
    }
}