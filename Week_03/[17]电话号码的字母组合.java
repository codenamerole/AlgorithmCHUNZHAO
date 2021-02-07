//给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。 
//
// 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。 
//
// 
//
// 
//
// 示例 1： 
//
// 
//输入：digits = "23"
//输出：["ad","ae","af","bd","be","bf","cd","ce","cf"]
// 
//
// 示例 2： 
//
// 
//输入：digits = ""
//输出：[]
// 
//
// 示例 3： 
//
// 
//输入：digits = "2"
//输出：["a","b","c"]
// 
//
// 
//
// 提示： 
//
// 
// 0 <= digits.length <= 4 
// digits[i] 是范围 ['2', '9'] 的一个数字。 
// 
// Related Topics 深度优先搜索 递归 字符串 回溯算法 
// 👍 1113 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
//Round1 回溯
/*class Solution {
    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<String>();
        if (digits.length() == 0) {
            return res;
        }
        Map<Character, String> phoneMap = new HashMap<Character, String>() {{
            put('2', "abc");
            put('3', "def");
            put('4', "ghi");
            put('5', "jkl");
            put('6', "mno");
            put('7', "pqrs");
            put('8', "tuv");
            put('9', "wxyz");
        }};
        backtrack(res, new StringBuffer(), phoneMap, digits, 0);
        return res;
    }

    public void backtrack(List<String> combinations, StringBuffer combination, Map<Character, String> phoneMap, String digits, int index) {
        if (index == digits.length()) {
            combinations.add(combination.toString());
        } else {
            char digit = digits.charAt(index);
            String letters = phoneMap.get(digit);
            int count = letters.length();
            for (int i = 0; i < count; i++) {
                combination.append(letters.charAt(i));
                backtrack(combinations, combination, phoneMap, digits, index + 1);
                combination.deleteCharAt(index);
            }
        }
    }
}*/
//Round1 队列拼接
/*class Solution {
    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();
        if(digits == null || digits.length() == 0) {
            return res;
        }
        String[] letter_map = {" ","*","abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};
        res.add("");
        for(int i = 0;i < digits.length(); i++) {
            String letters = letter_map[digits.charAt(i)-'0'];
            int size = res.size();
            //将现存答案中的每个元素依次拿出来
            for(int j = 0; j < size; j++) {
                //每次都从队列中拿出第一个元素
                String tmp = res.remove(0);
                //然后跟"def"这样的字符串拼接，并再次放到队列中
                for(int k = 0; k < letters.length(); k++) {
                    res.add(tmp + letters.charAt(k));
                }
            }
        }
        return res;
    }
}*/
//leetcode submit region end(Prohibit modification and deletion)
//Round2
class Solution {
    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();
        // 注意 空串的情况
        if (digits.length() == 0) {
            return res;
        }
        Map<Character, String> map = new HashMap<Character, String>() {{
            put('2', "abc");
            put('3', "def");
            put('4', "ghi");
            put('5', "jkl");
            put('6', "mno");
            put('7', "pqrs");
            put('8', "tuv");
            put('9', "wxyz");
        }};
        backtrack(res, map, digits, 0, new StringBuffer());
        return res;
    }

    public void backtrack(List<String> list, Map<Character, String> map, String digits, int index, StringBuffer combination) {
        //terminator
        if (index == digits.length()) {
            list.add(combination.toString());
            return;
        } else {
            char digit = digits.charAt(index);
            String letters = map.get(digit);
            int count = letters.length();
            for (int i = 0; i < count; i++) {
                combination.append(letters.charAt(i));
                backtrack(list, map, digits, index + 1, combination);
                combination.deleteCharAt(index);
            }
        }
    }
}