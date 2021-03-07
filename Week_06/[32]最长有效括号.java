//给你一个只包含 '(' 和 ')' 的字符串，找出最长有效（格式正确且连续）括号子串的长度。 
//
// 
//
// 
// 
// 示例 1： 
//
// 
//输入：s = "(()"
//输出：2
//解释：最长有效括号子串是 "()"
// 
//
// 示例 2： 
//
// 
//输入：s = ")()())"
//输出：4
//解释：最长有效括号子串是 "()()"
// 
//
// 示例 3： 
//
// 
//输入：s = ""
//输出：0
// 
//
// 
//
// 提示： 
//
// 
// 0 <= s.length <= 3 * 104 
// s[i] 为 '(' 或 ')' 
// 
// 
// 
// Related Topics 字符串 动态规划 
// 👍 1168 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
//Round1 DP
/*
* dp[i] 到此位置 有效括号数
* s[i]=(  -> 0
* s[i]=) 取决前方
*       s[i-1]=( 直接配对 长度+2  dp[i] = dp[i-2]+2
*       s[i-1]=)  可能对应  ((....))
*               再看s[i-1 - dp[i-1]] 是啥，不要怕挺好理解的 i-1 这个字符再把前面已配对的抠去 即可
*               s[i-dp[i-1]-1]=(  dp[i]=dp[i-dp[i-1]-2]+2 + dp[i-1]  第一遍算到dp[i-dp[i-1]-2] 还没算(...) dp[i-1]这段 所以要一起加上
* */
class Solution {
    public int longestValidParentheses(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int n = s.length();
        int res = 0;
        int[] dp = new int[n];
        // dp[0] = 0;
        for (int i = 1; i < n; i++) {
            // 也可以 统一toCharArray() 后再string[]
            if (s.charAt(i) == ')') {
                if (s.charAt(i - 1) == '(') {
                    dp[i] = (i >= 2 ? dp[i - 2] : 0) + 2;
                }
                else if (i - dp[i - 1] > 0 && s.charAt(i - dp[i - 1] - 1) == '(') {
                    dp[i] = dp[i - 1] + ((i - dp[i - 1]) >= 2 ? dp[i - dp[i - 1] - 2] : 0) + 2;
                    }
                }
            res = Math.max(res, dp[i]);
            }
        return res;
    }
}


//Round1 栈
/*
* 对于遇到的每个 (，下标入栈
* 对于遇到的每个 )，弹出栈顶元素表示匹配了当前右括号：
*     如果栈为空，说明当前的右括号为没有被匹配的右括号，将其下标放入栈中来更新我们之前提到的「最后一个没有被匹配的右括号的下标」
*     如果栈不为空，当前右括号的下标减去栈顶元素即为「以该右括号为结尾的最长有效括号的长度」
* */
class Solution {
    public int longestValidParentheses(String s) {
        int res = 0;
        Deque<Integer> stack = new LinkedList<Integer>();
        stack.push(-1);
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                stack.pop();
                if (stack.isEmpty()) {
                    stack.push(i);
                } else {
                    res = Math.max(res, i - stack.peek());
                }
            }
        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
