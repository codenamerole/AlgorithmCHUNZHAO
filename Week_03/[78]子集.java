//给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。 
//
// 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,2,3]
//输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
// 
//
// 示例 2： 
//
// 
//输入：nums = [0]
//输出：[[],[0]]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 10 
// -10 <= nums[i] <= 10 
// nums 中的所有元素 互不相同 
// 
// Related Topics 位运算 数组 回溯算法 
// 👍 970 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
//Round1 递归 选或不选
/*class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null) {
            return res;
        }
        dfs(res, nums, new ArrayList<Integer>(),0);
        return res;
    }

    public void dfs(List<List<Integer>> res, int[] nums,  List<Integer> list, int index) {
        //terminator
        if (index == nums.length) {
            res.add(new ArrayList<Integer>(list));
            return;
        }
        //subproblems
        dfs(res,nums,list,index + 1); //不选

        list.add(nums[index]);
        dfs(res,nums,list,index + 1); //选
        //revert state
        list.remove(list.size() - 1);
    }
}*/
//Round1 迭代 每个元素 插入已存在的答案集合
/*class Solution {
    List<Integer> t = new ArrayList<Integer>();
    List<List<Integer>> ans = new ArrayList<List<Integer>>();

    public List<List<Integer>> subsets(int[] nums) {
        int n = nums.length;
        //位运算
        for (int mask = 0; mask < (1 << n); ++mask) {
            t.clear();
            for (int i = 0; i < n; ++i) {
                if ((mask & (1 << i)) != 0) {
                    t.add(nums[i]);
                }
            }
            ans.add(new ArrayList<Integer>(t));
        }
        return ans;
    }
}*/

//leetcode submit region end(Prohibit modification and deletion)
//Round2 dfs
class Solution {
    public List<List<Integer>> subsets(int nums[]) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if (nums == null) {
            return res;
        }
        dfs(res, new ArrayList<Integer>(), nums, 0);
        return res;
    }
    public void dfs(List<List<Integer>> res, List<Integer> list, int nums[], int index) {
        if (index == nums.length) {
            res.add(new ArrayList<Integer>(list));
            return;
        }
        dfs(res, list, nums, index + 1);
        list.add(nums[index]);
        dfs(res, list, nums, index + 1);
        list.remove(list.size() - 1);
    }
}
