//给定一个字符串，找到它的第一个不重复的字符，并返回它的索引。如果不存在，则返回 -1。 
//
// 
//
// 示例： 
//
// s = "leetcode"
//返回 0
//
//s = "loveleetcode"
//返回 2
// 
//
// 
//
// 提示：你可以假定该字符串只包含小写字母。 
// Related Topics 哈希表 字符串 
// 👍 354 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
//数组
class Solution {
    public int firstUniqChar(String s) {
        char[] chs = s.toCharArray();
        int len = chs.length;
        int[] map = new int[26];

        for (int i = 0; i < len; i++) {
            int index = chs[i] - 'a';
            map[index]++;
        }
        for (int i = 0; i < len; i++) {
            int index = chs[i] - 'a';
            if (map[index] == 1) {
                return i;
            }
        }
        return -1;
    }
}

// hash
class Solution {
    public int firstUniqChar(String s) {
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        for (int i = 0; i < s.length(); ++i) {
            char ch = s.charAt(i);
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }
        for (int i = 0; i < s.length(); ++i) {
            if (map.get(s.charAt(i)) == 1) {
                return i;
            }
        }
        return -1;
    }
}


//Round1 队列
//leetcode submit region end(Prohibit modification and deletion)
//Round2 hash
class Solution {
    public int firstUniqChar(String s) {
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }
        for (int i = 0; i < s.length(); i++) {
            if (map.get(s.charAt(i)) == 1) {
                return i;
            }
        }
        return -1;
    }
}

