## [819. Most Common Word](https://leetcode.com/problems/most-common-word/)

Given a paragraph and a list of banned words, return the most frequent word that is not in the list of banned words.  It is guaranteed there is at least one word that isn't banned, and that the answer is unique.

Words in the list of banned words are given in lowercase, and free of punctuation.  Words in the paragraph are not case sensitive.  The answer is in lowercase.

```
Example:

Input: 
paragraph = "Bob hit a ball, the hit BALL flew far after it was hit."
banned = ["hit"]
Output: "ball"

Explanation: 
"hit" occurs 3 times, but it is a banned word.
"ball" occurs twice (and no other word does), so it is the most frequent non-banned word in the paragraph. 
Note that words in the paragraph are not case sensitive,
that punctuation is ignored (even if adjacent to words, such as "ball,"), 
and that "hit" isn't the answer even though it occurs more because it is banned.
```

Note:
- 1 <= paragraph.length <= 1000.
- 0 <= banned.length <= 100.
- 1 <= banned[i].length <= 10.
- The answer is unique, and written in lowercase (even if its occurrences in paragraph may have uppercase symbols, and even if it is a proper noun.)
- paragraph only consists of letters, spaces, or the punctuation symbols !?',;.
- There are no hyphens or hyphenated words.
- Words only consist of letters, never apostrophes or other punctuation symbols.

## Method 1: Regular Expression("\W+")
```java
public String mostCommonWord(String paragraph, String[] banned) {
    // assume the input are all valid
    // \w+, \W+: ONE or MORE continuous
    // \w, \W: ANY ONE word/non-word character.
    String[] input = paragraph.toLowerCase().split("\\W+");
    
    // using set to store banned
    Set<String> set = new HashSet<>();
    for (String s : banned) {
        set.add(s);
    }
    
    // add paragraph words to hash map
    Map<String, Integer> map = new HashMap<>();
    for(String word : input){
        if(!set.contains(word)){
            map.put(word, map.getOrDefault(word, 0) + 1);
        }
    }
        
    // get the most frequent word
    int max = 0; // max frequency
    String res = "";
    for(String str : map.keySet()){
        if(map.get(str) > max){
            max = map.get(str);
            res = str;
        }
    }
    
    return res;
}
```