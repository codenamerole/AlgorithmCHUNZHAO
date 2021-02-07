//编写一个高效的算法来判断 m x n 矩阵中，是否存在一个目标值。该矩阵具有如下特性： 
//
// 
// 每行中的整数从左到右按升序排列。 
// 每行的第一个整数大于前一行的最后一个整数。 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3
//输出：true
// 
//
// 示例 2： 
//
// 
//输入：matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 13
//输出：false
// 
//
// 
//
// 提示： 
//
// 
// m == matrix.length 
// n == matrix[i].length 
// 1 <= m, n <= 100 
// -104 <= matrix[i][j], target <= 104 
// 
// Related Topics 数组 二分查找 
// 👍 312 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
//1. 暴力 完全转化为一维数组 然后二分查找
//Round1
/*一维坐标位置是loc,那么它在二维坐标就是[loc/col][loc%col]*/
/*class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        if (m == 0) return false;
        int n = matrix[0].length;

        int left = 0, right = m * n - 1;
        int mid, midValue;
        while (left <= right) {
            mid = (right - left) / 2 + left;
            midValue = matrix[mid / n][mid % n];
            if (target == midValue) {
                return true;
            }
            else {
                if (target < midValue) {
                    right = mid - 1;
                }
                else left = mid + 1;
            }
        }
        return false;
    }
}*/

//leetcode submit region end(Prohibit modification and deletion)
//Round2
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        if (m == 0) {
            return false;
        }
        int n = matrix[0].length;
        int left = 0, right = m * n - 1;
        int mid, midValue;
        while (left <= right) {
            mid = (right - left) / 2 + left;
            midValue = matrix[mid / n][mid % n];
            if (target == midValue) {
                return true;
            }
            else {
                if (target < midValue) {
                    right = mid - 1;
                }
                else left = mid + 1;
            }
        }
        return false;
    }
}