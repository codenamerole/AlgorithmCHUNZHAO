//给定两个字符串 s 和 t，判断它们是否是同构的。 
//
// 如果 s 中的字符可以按某种映射关系替换得到 t ，那么这两个字符串是同构的。 
//
// 每个出现的字符都应当映射到另一个字符，同时不改变字符的顺序。不同字符不能映射到同一个字符上，相同字符只能映射到同一个字符上，字符可以映射到自己本身。 
//
// 
//
// 示例 1: 
//
// 
//输入：s = "egg", t = "add"
//输出：true
// 
//
// 示例 2： 
//
// 
//输入：s = "foo", t = "bar"
//输出：false 
//
// 示例 3： 
//
// 
//输入：s = "paper", t = "title"
//输出：true 
//
// 
//
// 提示： 
//
// 
// 可以假设 s 和 t 长度相同。 
// 
// Related Topics 哈希表 
// 👍 334 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
//hash table 分别存储两个string的映射关系
class Solution {
    public boolean isIsomorphic(String s, String t) {
        Map<Character, Character> s2t = new HashMap<Character, Character>();
        Map<Character, Character> t2s = new HashMap<Character, Character>();
        int len = s.length();
        for (int i = 0; i < len; ++i) {
            char x = s.charAt(i), y = t.charAt(i);
            //注意 要分别比较两个方向 s->t t->s  (ab,cc) x
            if ((s2t.containsKey(x) && s2t.get(x) != y) || (t2s.containsKey(y) && t2s.get(y) != x)) {
                return false;
            }
            s2t.put(x, y);
            t2s.put(y, x);
        }
        return true;
    }
}
// 借助第三方 将第一个出现的字母映射成 1，第二个出现的字母映射成 2
/*
* egg e->1 g->2 122
* add a->1 d->2 122 同构
* */
class Solution {
    public boolean isIsomorphic(String s, String t) {
        char[] s_char = s.toCharArray();
        char[] t_char = t.toCharArray();
        int[] s_value = new int[256];
        int[] t_value = new int[256];
        for (int i = 0; i < s_char.length; i++) {
            if (s_value[s_char[i]] != t_value[t_char[i]]) {
                return false;
            }
            s_value[s_char[i]] = i + 1;
            t_value[t_char[i]] = i + 1;
        }
        return true;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
