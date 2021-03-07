//给定一个字符串 s 和一个非空字符串 p，找到 s 中所有是 p 的字母异位词的子串，返回这些子串的起始索引。 
//
// 字符串只包含小写英文字母，并且字符串 s 和 p 的长度都不超过 20100。 
//
// 说明： 
//
// 
// 字母异位词指字母相同，但排列不同的字符串。 
// 不考虑答案输出的顺序。 
// 
//
// 示例 1: 
//
// 
//输入:
//s: "cbaebabacd" p: "abc"
//
//输出:
//[0, 6]
//
//解释:
//起始索引等于 0 的子串是 "cba", 它是 "abc" 的字母异位词。
//起始索引等于 6 的子串是 "bac", 它是 "abc" 的字母异位词。
// 
//
// 示例 2: 
//
// 
//输入:
//s: "abab" p: "ab"
//
//输出:
//[0, 1, 2]
//
//解释:
//起始索引等于 0 的子串是 "ab", 它是 "ab" 的字母异位词。
//起始索引等于 1 的子串是 "ba", 它是 "ab" 的字母异位词。
//起始索引等于 2 的子串是 "ab", 它是 "ab" 的字母异位词。
// 
// Related Topics 哈希表 
// 👍 484 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
//Round1 滑动窗口
class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        int[] fre = new int[26];
        // 表示窗口内相差的字符的数量
        int dif = 0;
        // fre 统计频数
        for (char c : p.toCharArray()) {
            fre[c - 'a']++;
            dif++;
        }
        int left=0,right=0;
        int length = s.length();
        char[] array = s.toCharArray();

        List<Integer> result = new ArrayList<>();
        while (right < length) {
            char rightChar = array[right];
            //是p中的字符
            if (fre[rightChar-'a'] > 0) {
                fre[rightChar-'a']--;
                //差距减少
                dif--;
                right++;
                //差距减少为0时 说明窗口内为所求
                if (dif == 0) {
                    result.add(left);
                }
            }else{
                //俩种情况   第一种 rightChar 是p以外的字符串如"c" "abc" "ab" 此时 left 和 right 都应该指向 c后面的位置
                //          第二种 rightChar 是p内的字符串但是是额外的一个char如第二个"b" 例 "abb" "ab" 此时right不变 left应该指向第一个b后面的位置

                //对于第一种情况 left 和 right 都应该定位到 c  所以要恢复fre数组 同时恢复差距
                //对于第二种情况 此时fre[array[right]-'a']=0 让left移动到第一个b后面的位置 这样就融入了新的b（第二个b）

                while (fre[array[right]-'a']<=0 && left<right) {
                    fre[array[left]-'a']++;
                    left++;
                    dif++;
                }

                if (left == right ) {
                    //这个if用来检测right所处字符是否是p外的字符
                    //用来处理第二种情况
                    if (fre[array[left] - 'a'] > 0) {
                        //说明是p里的字符 跳过
                        continue;
                    }else{
                        //用来处理第一种情况 移动到这个字符后面的位置
                        left++;
                        right++;
                    }

                }
            }
        }
        return result;
    }
}

class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        if (s == null || s.length() == 0
                || p == null || p.length() == 0
                || s.length() < p.length()) {
            return new ArrayList<>();
        }
        char[] source = s.toCharArray();
        char[] pattern = p.toCharArray();
        int[] times = new int[26];
        for (char pChar : pattern) {
            times[pChar - 'a']++;
        }
        int left = 0;
        int right = 0;
        List<Integer> res = new ArrayList<>();
        int[] window = new int[26];
        while (right < source.length) {
            window[source[right] - 'a']++;
            while (window[source[right] - 'a'] > times[source[right] - 'a']) {
                window[source[left++] - 'a']--;
            }
            if (right - left + 1 == pattern.length) {
                res.add(left);
            }
            right++;
        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
