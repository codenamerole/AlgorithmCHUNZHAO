//给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,1,2]
//输出：
//[[1,1,2],
// [1,2,1],
// [2,1,1]]
// 
//
// 示例 2： 
//
// 
//输入：nums = [1,2,3]
//输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 8 
// -10 <= nums[i] <= 10 
// 
// Related Topics 回溯算法 
// 👍 572 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
//Round1
class Solution {
    private boolean[] used;
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        List<Integer> output = new ArrayList<Integer>();
        used = new boolean[nums.length];
        Arrays.sort(nums); //排序后 方便剪枝
        backtrack(nums, output, res, 0);
        return res;
    }

    public void backtrack(int[] nums, List<Integer> output, List<List<Integer>> res, int start) {
        if (start == nums.length) {
            res.add(new ArrayList<Integer>(output));
            return;
        }
        //从左往右第一个未被填过的数字
        for (int i = 0; i < nums.length; i++) {
            if (used[i] || i > 0 && nums[i] == nums[i - 1] && !used[i - 1]){ // 考虑到nums[i - 1]被撤销选择
                continue;
            }
            output.add(nums[i]);
            used[i] = true;
            backtrack(nums, output, res, start + 1);
            used[i] = false;
            output.remove(start);
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
