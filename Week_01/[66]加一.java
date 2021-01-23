//给定一个由 整数 组成的 非空 数组所表示的非负整数，在该数的基础上加一。 
//
// 最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。 
//
// 你可以假设除了整数 0 之外，这个整数不会以零开头。 
//
// 
//
// 示例 1： 
//
// 
//输入：digits = [1,2,3]
//输出：[1,2,4]
//解释：输入数组表示数字 123。
// 
//
// 示例 2： 
//
// 
//输入：digits = [4,3,2,1]
//输出：[4,3,2,2]
//解释：输入数组表示数字 4321。
// 
//
// 示例 3： 
//
// 
//输入：digits = [0]
//输出：[1]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= digits.length <= 100 
// 0 <= digits[i] <= 9 
// 
// Related Topics 数组 
// 👍 616 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
/*
* 末位无进位，则末位加一即可，因为末位无进位，前面也不可能产生进位，比如 45 => 46
  末位有进位，在中间位置进位停止，则需要找到进位的典型标志，
       即为当前位 %10后为 0，则前一位加 1，直到不为 0 为止，比如 499 => 500
  末位有进位，并且一直进位到最前方导致结果多出一位，
  *    对于这种情况，需要在第 2 种情况遍历结束的基础上，进行单独处理，比如 999 => 1000
* */
//Round1
/*class Solution {
    public int[] plusOne(int[] digits) {
        int len = digits.length;
        for(int i = len - 1; i >= 0; i--) {
            digits[i]++;
            digits[i] %= 10;
            if(digits[i]!= 0)
                return digits;
        }
        //如果最后全是0，从头补一个
        digits = new int[len + 1];
        digits[0] = 1;
        return digits;
    }
}*/
//leetcode submit region end(Prohibit modification and deletion)
//Round2
class Solution {
    public int[] plusOne(int[] digits) {
        int len = digits.length;
        for(int i = len - 1; i >= 0; i--) {
            digits[i]++;
            digits[i] %= 10;
            if(digits[i]!= 0)
                return digits;
        }
        digits = new int[len + 1];
        digits[0] = 1;
        return digits;
    }
}