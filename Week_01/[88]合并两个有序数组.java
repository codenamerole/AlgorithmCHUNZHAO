//给你两个有序整数数组 nums1 和 nums2，请你将 nums2 合并到 nums1 中，使 nums1 成为一个有序数组。 
//
// 初始化 nums1 和 nums2 的元素数量分别为 m 和 n 。你可以假设 nums1 的空间大小等于 m + n，这样它就有足够的空间保存来自 nu
//ms2 的元素。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
//输出：[1,2,2,3,5,6]
// 
//
// 示例 2： 
//
// 
//输入：nums1 = [1], m = 1, nums2 = [], n = 0
//输出：[1]
// 
//
// 
//
// 提示： 
//
// 
// nums1.length == m + n 
// nums2.length == n 
// 0 <= m, n <= 200 
// 1 <= m + n <= 200 
// -109 <= nums1[i], nums2[i] <= 109 
// 
// Related Topics 数组 双指针 
// 👍 740 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
//1. 先合并，再排序
/*class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        // System.arraycopy(nums2, 0, nums1, m, n);  //这个API 自己之前没掌握
        for (int i = m, j = 0; i < nums1.length; i++, j++) {
            nums1[i] = nums2[j];
        }
        Arrays.sort(nums1);
    }
}*/

//2. 双指针 一遍扫描，从前向后->比较大小->插入，且当一个数组处理完，另一个数组的其余部分直接copy
//但是新开的数组 空间O(n)
/*class Solution {
    public void merge (int[] nums1, int m, int[] nums2, int n){
        // nums1的有效数据部分
        int [] nums1_copy = new int[m];
        for (int i = 0; i < m; i++) {
            nums1_copy[i] = nums1[i];
        }
        //p1 p2操作原数组 p操作合并后的数组
        int p1 = 0;
        int p2 = 0;
        int p = 0;
        //比较大小，小的插入
        while ((p1 < m) && (p2 < n))
            nums1[p++] = (nums1_copy[p1] < nums2[p2]) ? nums1_copy[p1++] : nums2[p2++];
        //如果有剩余部分直接copy
        if (p1 < m)
            System.arraycopy(nums1_copy, p1, nums1, p1 + p2, m + n - p1 - p2);
        if (p2 < n)
            System.arraycopy(nums2, p2, nums1, p1 + p2, m + n - p1 - p2);
    }
}*/
//Round1 从后向前 空间O(1)
class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int len1 = m - 1;
        int len2 = n - 1;
        int len = m + n - 1;
        while(len1 >= 0 && len2 >= 0) {
            nums1[len--] = nums1[len1] > nums2[len2] ? nums1[len1--] : nums2[len2--];
        }
        System.arraycopy(nums2, 0, nums1, 0, len2 + 1);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
