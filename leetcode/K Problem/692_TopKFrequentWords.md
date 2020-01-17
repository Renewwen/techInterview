## [692. Top K Frequent Words](https://leetcode.com/problems/top-k-frequent-words/)

Given a non-empty list of words, return the k most frequent elements.

Your answer should be sorted by frequency from highest to lowest. If two words have the same frequency, then the word with the lower alphabetical order comes first.

```
Example 1:

Input: ["i", "love", "leetcode", "i", "love", "coding"], k = 2
Output: ["i", "love"]
Explanation: "i" and "love" are the two most frequent words.
Note that "i" comes before "love" due to a lower alphabetical order.

Example 2:

Input: ["the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"], k = 4
Output: ["the", "is", "sunny", "day"]
Explanation: "the", "is", "sunny" and "day" are the four most frequent words,
with the number of occurrence being 4, 3, 2 and 1 respectively.
```

### Method 1: Using MinHeap
```Java
// Using minHeap 
// Time: O(nlogk)
// Space: O(n)
public List<String> topKFrequent(String[] words, int k) {
    List<String> res = new ArrayList<>();
    if (words == null || words.length == 0 || k <= 0) {
        return res;
    }
    
    // calculate the frequent of words
    Map<String, Integer> map = new HashMap<>();
    for (String word : words) {
        int tmp = map.getOrDefault(word, 0);
        map.put(word, tmp + 1);
    }
    
    // define the minHeap
    PriorityQueue<Map.Entry<String, Integer>> pq = new PriorityQueue<>(new Comparator<Map.Entry<String, Integer>>(){
        @Override
        public int compare(Map.Entry<String, Integer> e1, Map.Entry<String, Integer> e2) {
            if (e1.getValue() == e2.getValue()) {
                return e2.getKey().compareTo(e1.getKey());
            }
            return e1.getValue() < e2.getValue() ? -1 : 1;
        }
    });
    
    // Get the K frequent words
    for (Map.Entry<String,Integer> cur : map.entrySet()) {
        if (pq.size() < k) {
            pq.offer(cur);    
        } else if (cur.getValue() >= pq.peek().getValue()) {
            pq.offer(cur);    
            pq.poll();
        }   
    }
    
    // output result
    while (!pq.isEmpty()) {
        res.add(pq.poll().getKey());
    }
    // Don't forget to reverse the result!
    // MinHeap, from small to big; but we need from big to small
    Collections.reverse(res);
    return res;
}
```

### Another Way to define the minHeap
```Java
// Not using 'Map.Entry<String Integer>', but just 'String'
PriorityQueue<String> pq = new PriorityQueue<>(k, new Comparator<String>(){
    @Override
    public int compare(String s1, String s2) {
        int count1 = map.get(s1);
        int count2 = map.get(s2);
        if (count1 == count2) {
            // String a.compareTo(b): a.charAt(i) - b.charAt(i)                  
            return s2.compareTo(s1);
        }
        return count1 - count2;
    }
});

// Get the K frequent words
for (String cur : map.keySet()) {
    if (pq.size() < k) {
        pq.offer(cur);    
    } else if (map.get(cur) >= map.get(pq.peek())) {
        pq.offer(cur);    
        pq.poll();
    }   
}
```
