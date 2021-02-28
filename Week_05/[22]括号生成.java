//数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。 
//
// 
//
// 示例 1： 
//
// 
//输入：n = 3
//输出：["((()))","(()())","(())()","()(())","()()()"]
// 
//
// 示例 2： 
//
// 
//输入：n = 1
//输出：["()"]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= n <= 8 
// 
// Related Topics 字符串 回溯算法 
// 👍 1536 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
//递归
/*
* [1] 2n个格子，可以放左括号 右括号
*        生成所有情况 ，再判断是否合法
* [2] 边生成，边判断
*        左括号 随时添加，只要还可以分配
*        右括号 必须 左 > 右
* */
//Round1
/*class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<String>();
        _generate(0, 0,n,"", res);
        return res;
    }

    public void _generate(int left, int right, int n, String s, List<String> list){
        // terminator
        if (left == n && right == n){
            list.add(s);
            return;
        }
        //process   current logic

        //drill down
        if (left < n) {
            _generate(left + 1, right, n, s + "(", list);
        }
        if (left > right) {
            _generate(left, right + 1, n, s + ")", list);
        }
        //reverse states
    }
}*/
//leetcode submit region end(Prohibit modification and deletion)
//Round2
class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<String>();
        _generate(0, 0,n,"", res);
        return res;
    }

    public void _generate(int left, int right, int n, String s, List<String> list){
        if (left == n && right == n){
            list.add(s);
            return;
        }
        if (left < n) {
            _generate(left + 1, right, n, s + "(", list);
        }
        if (left > right) {
            _generate(left, right + 1, n, s + ")", list);
        }
    }
}
//Round3
class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<String>();
        _generate(0, 0,n,"", res);
        return res;
    }

    public void _generate(int left, int right, int n, String s, List<String> list){
        if (left == n && right == n){
            list.add(s);
            return;
        }
        //剪枝
        if (left < n) {
            _generate(left + 1, right, n, s + "(", list);
        }
        if (left > right) {
            _generate(left, right + 1, n, s + ")", list);
        }
    }
}