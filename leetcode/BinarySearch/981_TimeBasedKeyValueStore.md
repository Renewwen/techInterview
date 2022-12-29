## [981. Time Based Key-Value Store](https://leetcode.com/problems/time-based-key-value-store/)

Create a timebased key-value store class TimeMap, that supports two operations.

`set(string key, string value, int timestamp)`
- Stores the key and value, along with the given timestamp.

`get(string key, int timestamp)`
- Returns a value such that set(key, value, timestamp_prev) was called previously, with timestamp_prev <= timestamp.
- If there are multiple such values, it returns the one with the largest timestamp_prev.
- If there are no values, it returns the empty string ("").

```
Example 1:

Input: 
    inputs = ["TimeMap","set","get","get","set","get","get"], 
    inputs = [[],["foo","bar",1],["foo",1],["foo",3],["foo","bar2",4],["foo",4],["foo",5]]
Output: 
    [null,null,"bar","bar",null,"bar2","bar2"]
Explanation:   
    TimeMap kv;   
    kv.set("foo", "bar", 1); // store the key "foo" and value "bar" along with timestamp = 1   
    kv.get("foo", 1);  // output "bar"   
    kv.get("foo", 3); // output "bar" since there is no value corresponding to foo at timestamp 3 and timestamp 2, then the only value is at timestamp 1 ie "bar"   
    kv.set("foo", "bar2", 4);   
    kv.get("foo", 4); // output "bar2"   
    kv.get("foo", 5); //output "bar2"   

Example 2:

Input: 
    inputs = ["TimeMap","set","set","get","get","get","get","get"], 
    inputs = [[],["love","high",10],["love","low",20],["love",5],["love",10],["love",15],["love",20],["love",25]]
Output: 
    [null,null,null,"","high","high","low","low"]
``` 

Note:
- All key/value strings are lowercase.
- All key/value strings have length in the range [1, 100]
- The timestamps for all TimeMap.set operations are strictly increasing.
- 1 <= timestamp <= 10^7
- TimeMap.set and TimeMap.get functions will be called a total of 120000 times (combined) per test case.

## Method 1: TreeMap
```java
class TimeMap {

    Map<String, TreeMap<Integer, String>> map;
    
    public TimeMap() {
        map = new HashMap<>();
    }
    
    public void set(String key, String value, int timestamp) {
        map.putIfAbsent(key, new TreeMap());
        map.get(key).put(timestamp, value);
    }
    
    public String get(String key, int timestamp) {
        TreeMap<Integer, String> treeMap = map.get(key);
        if (treeMap == null) {
            return "";
        }
        Integer floorKey = treeMap.floorKey(timestamp);
        if (floorKey == null) {
            return "";
        } else {
            return treeMap.get(floorKey);
        }
    }
}

/**
 * Your TimeMap object will be instantiated and called as such:
 * TimeMap obj = new TimeMap();
 * obj.set(key,value,timestamp);
 * String param_2 = obj.get(key,timestamp);
 */
```

## Method 2: Binary Search
```java
class TimeMap {

    static class Data {
        String value;
        Integer timestamp;
        public Data(String value, Integer timestamp) {
            this.value = value;
            this.timestamp = timestamp;
        }
    }
    
    Map<String, List<Data>> map;
    
    public TimeMap() {
        map = new HashMap<>();
    }
    
    public void set(String key, String value, int timestamp) {
        if (!map.containsKey(key)) {
            map.put(key, new ArrayList<>());
        }
        map.get(key).add(new Data(value, timestamp));
    }
    
    // Time: O(logn)
    public String get(String key, int timestamp) {
        List<Data> list = map.get(key);
        if (list == null) {
            return "";
        }
        int left = 0;
        int right = list.size() - 1;
        while (left < right - 1) {
            int mid = left + (right - left) / 2;
            Data data = list.get(mid);
            if (data.timestamp == timestamp) {
                return data.value;
            } else if (data.timestamp < timestamp) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        if (list.get(left).timestamp > timestamp) {
            return "";
        } else if (list.get(right).timestamp > timestamp) {
            return list.get(left).value;
        } else {
            return list.get(right).value;
        }
    }
}

/**
 * Your TimeMap object will be instantiated and called as such:
 * TimeMap obj = new TimeMap();
 * obj.set(key,value,timestamp);
 * String param_2 = obj.get(key,timestamp);
 */
```
