//给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。 
//
// 示例 1: 
//
// 输入: s = "anagram", t = "nagaram"
//输出: true
// 
//
// 示例 2: 
//
// 输入: s = "rat", t = "car"
//输出: false 
//
// 说明: 
//你可以假设字符串只包含小写字母。 
//
// 进阶: 
//如果输入字符串包含 unicode 字符怎么办？你能否调整你的解法来应对这种情况？ 
// Related Topics 排序 哈希表 
// 👍 331 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
/*
* 1. clarification 什么是异位词，是否区分大小写
* 2. possible solution
*      暴力解法 sort 看是否一致
*      hash,map 统计每个字符的频次，都一样就是
*      每个字母以ASCII码为hash值，
*        第一个单词 统计+1
*        第二个单词 响应字母统计-1 最后看是否为空
*       也可用int a[26]实现
 * */
//Round1 暴力排序O(nlogn)
/*class Solution {
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        char[] str1 = s.toCharArray();
        char[] str2 = t.toCharArray();
        Arrays.sort(str1);
        Arrays.sort(str2);
        return Arrays.equals(str1, str2);
    }
}*/
//Round1 hash table 计数排序
/*class Solution {
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        int[] table = new int[26]; //26个字母 不需取所有256ASCII字符的空间
        for (int i = 0; i < s.length(); i++) {
            table[s.charAt(i) - 'a']++;
        }
        for (int i = 0; i < t.length(); i++) {
            table[t.charAt(i) - 'a']--;
            if (table[t.charAt(i) - 'a'] < 0) {
                return false;
            }
        }
        return true;
    }
}*/
//计数排序 跟下面异曲同工 甚至不如
/*class Solution {
    public boolean isAnagram(String s, String t) {
        int[] schar = new int[26];
        int[] tchar = new int[26];
        for (int i = 0; i < s.length(); i++) {
            schar[s.charAt(i) - 'a']++;
        }
        for (int i = 0; i < t.length(); i++) {
            tchar[t.charAt(i) - 'a']++;
        }
        for (int i = 0; i < 26; i++) {
            if(schar[i] != tchar[i]) {
                return false;
            }
        }
        return true;
    }
}*/

//leetcode submit region end(Prohibit modification and deletion)
//Round2
class Solution {
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        int[] table = new int[26];
        for (int i = 0; i < s.length(); i++) {
            table[s.charAt(i) - 'a']++;  //注意 charAt API
        }
        for (int i = 0; i < t.length(); i++) {
            table[t.charAt(i) - 'a']--;
            if (table[t.charAt(i) - 'a'] < 0) {
                return false;
            }
        }
        return true;
    }
}