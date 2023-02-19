import java.util.*;
/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */
public class Solution {
    public ArrayList<Interval> merge(ArrayList<Interval> intervals) {
        ArrayList<Interval> res = new ArrayList<>();
        if (intervals == null || intervals.size() == 0) {
            return res;
        }
        Collections.sort(intervals, new Comparator<Interval>(){
            @Override
            public int compare(Interval i1, Interval i2) {
                if (i1.start == i2.start) {
                    return 0;
                }
                return i1.start < i2.start ? -1 : 1;
            }
        });

        for (Interval interval : intervals) {
            if (res.isEmpty()) {
                res.add(interval);
            } else {
                Interval cur = res.get(res.size() - 1);
                if (interval.start <= cur.end) {
                    cur.end = Math.max(cur.end, interval.end);
                } else {
                    res.add(interval);
                }
            }
        }
        return res;
    }
}
