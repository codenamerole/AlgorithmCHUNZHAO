//给定一个字符串，你需要反转字符串中每个单词的字符顺序，同时仍保留空格和单词的初始顺序。 
//
// 
//
// 示例： 
//
// 输入："Let's take LeetCode contest"
//输出："s'teL ekat edoCteeL tsetnoc"
// 
//
// 
//
// 提示： 
//
// 
// 在字符串中，每个单词由单个空格分隔，并且字符串中不会有任何额外的空格。 
// 
// Related Topics 字符串 
// 👍 277 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String reverseWords(String s) {
        StringBuffer res = new StringBuffer();
        int length = s.length();
        int i = 0;
        while (i < length) {
            int start = i;
            //搜索空格
            while (i < length && s.charAt(i) != ' ') {
                i++;
            }
            //逆序添加
            for (int j = start; j < i; j++) {
                res.append(s.charAt(start + i - 1 - j));
            }
            //空格保留
            while (i < length && s.charAt(i) == ' ') {
                i++;
                res.append(' ');
            }
        }
        resurn res.toString();
    }
}
//leetcode submit region end(Prohibit modification and deletion)
