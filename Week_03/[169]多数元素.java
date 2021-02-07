//给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数 大于 ⌊ n/2 ⌋ 的元素。 
//
// 你可以假设数组是非空的，并且给定的数组总是存在多数元素。 
//
// 
//
// 示例 1： 
//
// 
//输入：[3,2,3]
//输出：3 
//
// 示例 2： 
//
// 
//输入：[2,2,1,1,1,2,2]
//输出：2
// 
//
// 
//
// 进阶： 
//
// 
// 尝试设计时间复杂度为 O(n)、空间复杂度为 O(1) 的算法解决此问题。 
// 
// Related Topics 位运算 数组 分治算法 
// 👍 860 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
//1. 统计频数 -> hashMap 大于 n/2 取出 前k个高频元素
/*class Solution {
    public int majorityElement(int[] nums) {
        Map<Integer, Integer> occur = new HashMap<Integer,Integer>();
        for (int num : nums){
            occur.put(num, occur.getOrDefault(num, 0) + 1); //注意 API写法
        }
        for (Map.Entry<Integer,Integer> entry: occur.entrySet()) {
            int num = entry.getKey(), count = entry.getValue();
            if (count > nums.length / 2) {
                return num;
            }
        }
        return  -1;
    }
}*/
//2. 排序 排序后，下标为 [n/2] 的一定是众数
/*class Solution {
    public int majorityElement(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length / 2];
    }
}*/
//Round1 投票法 开心消消乐 O(n) O(1)
/*class Solution {
    public int majorityElement(int[] nums) {
        int res = nums[0]
        int count = 1;
        for(int i = 1; i < nums.length; i++){
            //如果相等，就累计数量
            if(nums[i] == res){
                count++;
            }else{
                //如果不等，就相互抵消一个
                if(count > 0) {
                    count--;
                }else{
                    //当计数为零时，更换目标数，暂用下一个数
                    res = nums[i];
                    count = 1;
                }
            }
        }
        return res;
    }
}*/
//Round1 分治
/*
* 不断分为左右两部分，直到最后长度为1
* 将选出的两个众数中选择数量更多的
* */
/*class Solution {
    private int countInRange(int[] nums, int num, int lo, int hi) {
        int count = 0;
        for (int i = lo; i <= hi; i++) {
            if (nums[i] == num) {
                count++;
            }
        }
        return count;
    }

    private int majorityElementRec(int[] nums, int lo, int hi) {
        // base case; the only element in an array of size 1 is the majority
        // element.
        if (lo == hi) {
            return nums[lo];
        }

        // recurse on left and right halves of this slice.
        int mid = (hi - lo) / 2 + lo;
        int left = majorityElementRec(nums, lo, mid);
        int right = majorityElementRec(nums, mid + 1, hi);

        // if the two halves agree on the majority element, return it.
        if (left == right) {
            return left;
        }

        // otherwise, count each element and return the "winner".
        int leftCount = countInRange(nums, left, lo, hi);
        int rightCount = countInRange(nums, right, lo, hi);

        return leftCount > rightCount ? left : right;
    }

    public int majorityElement(int[] nums) {
        return majorityElementRec(nums, 0, nums.length - 1);
    }
}*/

//leetcode submit region end(Prohibit modification and deletion)
//Round2 分治
class Solution {
    public int majorityElement(int[] nums) {
        return getMajorityElement(nums, 0, nums.length - 1);
    }

    public int getCounts(int[] nums, int num, int low, int high) {
        int count = 0;
        for (int i = low; i <= high; i++) {  //<= ===
            if (nums[i] == num) {
                count++;
            }
        }
        return count;
    }

    public int getMajorityElement(int[] nums, int low, int high) {
        //terminator
        if (low == high) {
            return nums[low];
        }

        //subproblems
        int mid = (high - low) / 2 + low;
        int left = getMajorityElement(nums,low, mid);
        int right = getMajorityElement(nums, mid + 1, high);

        //current logic
        if (left == right) {
            return left;
        }

        int leftCount = getCounts(nums, left, low, high); //注意 这次范围是low->high 不是0->mid,mid+1->high
        int rightCount = getCounts(nums, right, low, high);
        return leftCount > rightCount ? left : right;
    }
}
//Round2 投票
/*class Solution {
    public int majorityElement(int[] nums) {
        int res = nums[0];
        int count = 1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == res){
                count++;
            }else{
                if(count > 0) {
                    count--;
                }else{
                    res = nums[i];
                    count = 1;
                }
            }
        }
        return res;
    }
}*/
