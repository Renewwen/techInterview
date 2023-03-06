import java.util.*;

/**
 * 给定一个汇率表，然后两个币种，求汇率
 *
 * 解法：构造双向图，使用深度优先搜索
 */

public class Solution {

    static class CurrencyPair {
        String from;
        String to;
        double rate;
    }

    public Map<CurrencyPair, Node> map = new HashMap<>();

    public void processInput(List<CurrencyPair> currencyPairs) {
        if (currencyPairs == null || currencyPairs.size() == 0) {
            return;
        }
        for (CurrencyPair currencyPair : currencyPairs) {
            Node fromNode = map.getOrDefault(from, new Node(currencyPair.fro));
            Node toNode = map.getOrDefault(to, new Node(currencyPair.to));
            fromNode.map.put(toNode, rate);
            toNode.map.put(fromNode, 1.0 / rate);
        }
    }

    // case 1: usd --> cny
    public double exchangeRate(String from, String to) {
        if (map.containsKey(from) && map.containsKey(to)) {
            HashSet<Node> visited = new HashSet<>();
            visited.add(map.get(from));
            exchangeRate(map.get(from), map.get(to), new double[]{1.0}, visited);
        }
        return res[0];
    }

    private void exchangeRate(Node fromNode, Node toNode, double[] res, HashSet<Node> visited) {
        for (Entry<Node, Double> entry : fromNode.neighbors) {
            Node curNode = entry.getKey();
            double rate = entry.getValue();
            if (!visited.contains(curNode)) {
                res[0] = res[0] * rate;
                if (curNode == toNode) {
                    return;
                }
                visited.add(curNode);
                exchangeRate(curNode, toNode, res);
                visited.remove(curNode);
                res[0] = res[0] / rate;
            }
        }
    }

    static class Node {
        String cur;
        Map<Node, Double> neighbors;

        public Node(String cur) {
            this.cur = cur;
            this.neighbors = new HashMap<>();
        }
    }

}
